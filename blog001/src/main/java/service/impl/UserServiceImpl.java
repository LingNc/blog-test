package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.*;
import com.blog.entry.dto.ArticleDto;
import com.blog.entry.dto.UserDto;
import com.blog.entry.vo.*;
import com.blog.enums.AppHttpCodeEnum;
import com.blog.excption.SystemException;
import com.blog.utils.BeanCopyUtils;
import com.blog.utils.JwtUtil;
import com.blog.utils.RedisCache;
import com.blog.utils.SecurityUtils;
import jakarta.annotation.Resource;
import mapper.ArticleMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringUtils;
import service.*;

import java.util.*;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-09-08 15:57:33
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Resource
    private UserAndUserService userAndUserService;

    @Override
    public ResponseResult login(User user) {
        if(!StringUtils.hasText(user.getUserName()) || !StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME_PASSWORD);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)) {
            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Integer id = loginUser.getUser().getId();
        String  jwt = JwtUtil.createJWT(String.valueOf(id));
        redisCache.setCacheObject("bloglogin:"+id,loginUser);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //TODO 有待优化 可以尝试工具类

        LambdaQueryWrapper<UserAndUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAndUser::getUid,userInfoVo.getId());
        List<UserAndUser> userAndUser = userAndUserService.list(queryWrapper);
        userInfoVo.setConcern(userAndUser.size());
        LambdaQueryWrapper<UserAndUser> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UserAndUser::getConcernedId,userInfoVo.getId());
        List<UserAndUser> userAndUser1 = userAndUserService.list(queryWrapper1);
        userInfoVo.setFan(userAndUser1.size());
        //
        return ResponseResult.okResult(new BlogUserLoginVo(jwt,userInfoVo));
    }

    @Override
    public ResponseResult getUserInfo(int id) {
        User user = getById(id);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //TODO 优化 可以尝试工具类
        LambdaQueryWrapper<UserAndUser> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(UserAndUser::getUid,userInfoVo.getId());
        List<UserAndUser> userAndUser = userAndUserService.list(queryWrapper2);
        userInfoVo.setConcern(userAndUser.size());
        LambdaQueryWrapper<UserAndUser> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UserAndUser::getConcernedId,userInfoVo.getId());
        List<UserAndUser> userAndUser1 = userAndUserService.list(queryWrapper1);
        userInfoVo.setFan(userAndUser1.size());
        //
        return ResponseResult.okResult(userInfoVo);
    }

    private Map<String, Integer> EmailAndCode = new ConcurrentReferenceHashMap<>();
    private Map<Integer, Date> CodeAndDate = new ConcurrentReferenceHashMap<>();

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public ResponseResult identifyCode(String email) {
        if(!StringUtils.hasText(email)) {
            throw  new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(EmailAndCode.containsKey(email)) {
            Date now = new Date();
            Integer preCode = EmailAndCode.get(email);
            Date preDate = CodeAndDate.get(preCode);
            if((now.getTime()-preDate.getTime()) >= 60*1000){
                EmailAndCode.remove(email);
                CodeAndDate.remove(preCode);
            }else {
                throw new SystemException(AppHttpCodeEnum.EMAIL_TIME);
            }
        }
        Random random = new Random();
        int identifyCode = 10000 + random.nextInt(90000);
        EmailAndCode.put(email, identifyCode);
        CodeAndDate.put(identifyCode,new Date());
        //TODO  优化 可以尝试工具类

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("identifyCode");
        message.setText(String.valueOf(identifyCode));
        message.setFrom("a17388110647@163.com");
        javaMailSender.send(message);


        return ResponseResult.okResult();
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult register(UserDto userDto) {
        if(!StringUtils.hasText(userDto.getUserName()) || !StringUtils.hasText(userDto.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME_PASSWORD);
        }
        if(!StringUtils.hasText(userDto.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(userDto.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!EmailAndCode.containsKey(userDto.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_MATCH);
        }
        if(!EmailAndCode.get(userDto.getEmail()).equals(userDto.getIdentifyCode())){
            throw new SystemException(AppHttpCodeEnum.CODE_NOT_MATCH);
        }
        if(userNameExist(userDto.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(EmailExist(userDto.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        save(user);
        EmailAndCode.remove(userDto.getEmail());
        CodeAndDate.remove(userDto.getIdentifyCode());
        return ResponseResult.okResult();
    }

    @Resource
    private ArticleService articleService;


    @Override
    public ResponseResult UserArticleList(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, SecurityUtils.getUserId());
        queryWrapper.orderByDesc(Article::getIsTop);
        queryWrapper.orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(pageNum, pageSize);
        articleService.page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, ArticleVo.class);
        List<ArticleVo> list = articleVos.stream().map(articleVo -> {
            articleVo.setStar(findStarCount(articleVo.getId()));
            articleVo.setLike(findLikeCount(articleVo.getId()));
            return articleVo;
        }).toList();
        return ResponseResult.okResult(new PageVo(list,page.getTotal()));
    }

    @Override
    public ResponseResult getUserInfoByMe() {
        User user = getById(SecurityUtils.getUserId());
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //TODO 优化 可以尝试工具类
        LambdaQueryWrapper<UserAndUser> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(UserAndUser::getUid,userInfoVo.getId());
        List<UserAndUser> userAndUser = userAndUserService.list(queryWrapper2);
        userInfoVo.setConcern(userAndUser.size());
        LambdaQueryWrapper<UserAndUser> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(UserAndUser::getConcernedId,userInfoVo.getId());
        List<UserAndUser> userAndUser1 = userAndUserService.list(queryWrapper1);
        userInfoVo.setFan(userAndUser1.size());
        //
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult UserArticle(Long id) {
        Article article = articleService.getById(id);
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        articleVo.setStar(findStarCount(articleVo.getId()));
        articleVo.setLike(findLikeCount(articleVo.getId()));
        Integer cacheMapValue = redisCache.getCacheMapValue("Article:viewCount", id.toString());
        articleVo.setViewCount(Long.valueOf(cacheMapValue));
        return ResponseResult.okResult(articleVo);
    }


    @Override
    public ResponseResult updateUserArticle(ArticleDto articleDto) {
        Article article1 = articleService.getById(articleDto.getId());
        if(!article1.getCreateBy().equals(SecurityUtils.getUserId())) {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        articleService.updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult deleteUserArticle(Long id) {
        Article article = articleService.getById(id);
        if(!article.getCreateBy().equals(SecurityUtils.getUserId())) {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        articleService.removeById(id);
        LambdaQueryWrapper<UserArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticle::getArticleId,id);
        userArticleService.remove(queryWrapper);
        return ResponseResult.okResult();
    }

    @Resource
    private HistoryService historyService;

    @Resource
    private ArticleServiceImpl articleServiceImpl;

    @Override
    public ResponseResult history(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(History::getUid, SecurityUtils.getUserId());
        queryWrapper.orderByDesc(History::getCreateTime);
        List<History> list = historyService.list(queryWrapper);
        List<Long> articleIds = list.stream().map(History::getArticleId).toList();
        LambdaQueryWrapper<Article> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(articleIds.size()>0, Article::getId, articleIds);
        Page<Article> page = new Page<>(pageNum, pageSize);
        articleService.page(page,queryWrapper1);
        List<Article> articles = page.getRecords();
        List<ArticleVo2> articleVo2s = BeanCopyUtils.copyBeanList(articles, ArticleVo2.class);
        List<ArticleVo2> list1 = articleVo2s.stream().map(a -> {
            a.setLike(findLikeCount(a.getId()));
            a.setStar(findStarCount(a.getId()));
            a.setCreateName(articleServiceImpl.findCreateNameByArticleId(a.getId()));
            return a;
        }).toList();
        return ResponseResult.okResult(new PageVo(list1,page.getTotal()));
    }

    @Override
    public ResponseResult getFans(Integer id) {
        LambdaQueryWrapper<UserAndUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAndUser::getConcernedId,id);
        List<UserAndUser> list = userAndUserService.list(queryWrapper);
        List<Integer> userIds = list.stream().map(UserAndUser::getUid).toList();
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(User::getId, userIds);
        List<User> list1 = list(queryWrapper1);
        List<UserVo> userVos = BeanCopyUtils.copyBeanList(list1, UserVo.class);
        return ResponseResult.okResult(userVos);
    }

    @Override
    public ResponseResult getConcern(Integer id) {
        LambdaQueryWrapper<UserAndUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAndUser::getUid,id);
        List<UserAndUser> list = userAndUserService.list(queryWrapper);
        List<Integer> userIds = list.stream().map(UserAndUser::getConcernedId).toList();
        LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(User::getId, userIds);
        List<User> list1 = list(queryWrapper1);
        List<UserVo> userVos = BeanCopyUtils.copyBeanList(list1, UserVo.class);
        return ResponseResult.okResult(userVos);
    }

    @Override
    public ResponseResult concern(Integer id) {
        UserAndUser userAndUser = new UserAndUser(SecurityUtils.getUserId(), id);
        userAndUserService.save(userAndUser);
        return ResponseResult.okResult();
    }

    @Resource
    private UserArticleLikeService userArticleLikeService;
    public Long findLikeCount(Long ArticleId) {
        LambdaQueryWrapper<UserArticleLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticleLike::getArticleId, ArticleId);
        return userArticleLikeService.count(queryWrapper);
    }

    @Resource
    private UserArticleService userArticleService;
    public Long findStarCount (Long ArticleId){
        LambdaQueryWrapper<UserArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserArticle::getArticleId, ArticleId);
        return userArticleService.count(queryWrapper);
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName, userName);
        return count(queryWrapper) > 0;
    }
    private boolean EmailExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getEmail, email);
        return count(queryWrapper) > 0;
    }
}


