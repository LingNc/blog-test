package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.Comment;
import com.blog.entry.dto.CommentDto;
import com.blog.entry.vo.CommentVo;
import com.blog.entry.vo.PageVo;
import com.blog.enums.AppHttpCodeEnum;
import com.blog.excption.SystemException;
import com.blog.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import service.CommentService;
import service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-09-13 14:26:01
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private UserService userService;

    @Override
    public ResponseResult commentList(String articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId,-1);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        List<CommentVo> list = commentVoList.stream().map(c -> {
            c.setChildren(getChildren(c.getId()));
            return c;
        }).toList();
        return ResponseResult.okResult(new PageVo(list, page.getTotal()));
    }

    @Override
    public ResponseResult sendComment(CommentDto commentDto) {
        if(StringUtils.hasText(commentDto.getContent())) {
            Comment comment = BeanCopyUtils.copyBean(commentDto, Comment.class);
            comment.setDelFlag(0);
            save(comment);
            return ResponseResult.okResult();
        }else {
            throw  new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
    }

    private List<CommentVo> getChildren(Long parentId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,parentId);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        return toCommentVoList(list);
    }

    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        List<CommentVo> list = commentVoList.stream().map(commentVo -> {
            commentVo.setNickname(userService.getById(commentVo.getCreateBy()).getNickName());
            if (commentVo.getToCommentUserId() != -1) {
                commentVo.setToCommentUserName(userService.getById(commentVo.getToCommentUserId()).getNickName());
            }
            return commentVo;
        }).toList();
        return list;
    }
}


