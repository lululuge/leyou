package com.leyou.service.impl;

import com.leyou.dao.CategoryDao;
import com.leyou.domain.Category;
import com.leyou.enums.ExceptionEnum;
import com.leyou.exception.LyException;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    /**
     * 根据父节点id查询商品分类列表
     * @param pid
     * @return
     */
    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        // 自定义查询条件，select方法会将对象中的非空属性作为查询条件
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryDao.select(category);
//        System.out.println(categoryList);
        // 判断查询结果是否为空
        if (CollectionUtils.isEmpty(categoryList)) {
            // 抛出自定义异常
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }

    /**
     * 更新商品分类名称
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        // 该方法只更新category对象中的非空属性
        categoryDao.updateByPrimaryKeySelective(category);
    }
}
