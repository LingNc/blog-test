package service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.ResponseResult;
import com.blog.entry.Category;


/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2024-09-12 19:07:47
 */
public interface CategoryService extends IService<Category> {

    ResponseResult listAllCategory();

    ResponseResult getCategoryById(Long id);
}


