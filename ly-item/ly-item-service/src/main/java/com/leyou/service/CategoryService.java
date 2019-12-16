package com.leyou.service;

import com.leyou.domain.Category;
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
    void updateCategory(Category category);
}
