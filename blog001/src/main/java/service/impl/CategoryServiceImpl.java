package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.ResponseResult;
import com.blog.entry.Category;
import com.blog.entry.vo.CategoryVo;
import com.blog.utils.BeanCopyUtils;
import mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import service.CategoryService;

import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2024-09-12 19:07:47
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public ResponseResult listAllCategory() {
        List<Category> list = list();
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public ResponseResult getCategoryById(Long id) {
        return null;
    }
}


