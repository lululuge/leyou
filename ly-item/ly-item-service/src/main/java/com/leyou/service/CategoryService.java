package com.leyou.service;

import com.leyou.domain.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    /**
     * 根据父节点id查询商品分类列表
     * @param pid
     * @return
     */
    List<Category> queryCategoryListByPid(Long pid);

    /**
     * 更新商品分类名称
     * @param category
     */
    @Transactional
    void updateCategory(Category category);

    /**
     * 新增商品分类
     * @param category
     */
    @Transactional
    void saveCategory(Category category);

    /**
     * 删除分类
     * @param id
     */
    @Transactional
    void deleteCategory(Long id);
}
