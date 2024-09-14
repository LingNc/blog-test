package service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.History;
import com.blog.utils.SecurityUtils;
import mapper.HistoryMapper;
import org.springframework.stereotype.Service;
import service.HistoryService;

import java.util.Date;
import java.util.List;

/**
 * (History)表服务实现类
 *
 * @author makejava
 * @since 2024-09-12 18:15:50
 */
@Service("historyService")
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Override
    public void saveHistoryById(Long id) {
        LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(History::getUid,SecurityUtils.getUserId());
        queryWrapper.eq(History::getArticleId,id);
        List<History> list = list(queryWrapper);
        if(list.size()==0){
            History history = new History(SecurityUtils.getUserId(),id,new Date());
            save(history);
        }
    }
}


