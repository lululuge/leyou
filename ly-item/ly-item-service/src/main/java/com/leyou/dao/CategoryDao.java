package com.leyou.dao;

import com.leyou.domain.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface CategoryDao extends Mapper<Category> {
    /**
     * 维护中间表（删除每一个叶子结点对应的商品分类）
     * @param cid
     */
    @Delete("delete from tb_category_brand where category_id = #{cid} ")
    void deleteByCategoryIdInCategoryBrand(@Param("cid") Long cid);
}
