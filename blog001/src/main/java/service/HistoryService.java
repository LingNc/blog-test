package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entry.History;


/**
 * (History)表服务接口
 *
 * @author makejava
 * @since 2024-09-12 18:15:50
 */
public interface HistoryService extends IService<History> {

    void saveHistoryById(Long id);
}


