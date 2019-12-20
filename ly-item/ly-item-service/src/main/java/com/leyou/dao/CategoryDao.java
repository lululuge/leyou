package com.leyou.dao;

import com.leyou.domain.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CategoryDao extends Mapper<Category> {
    /**
     * 维护中间表（删除每一个叶子结点对应的商品分类）
     * @param cid
     */
    @Delete("delete from tb_category_brand where category_id = #{cid} ")
    void deleteByCategoryIdInCategoryBrand(@Param("cid") Long cid);

    /**
     * 查询表中最后一条商品分类
     * @return
     */
    @Select("select * from tb_category where id = (select Max(id) from tb_category)")
    List<Category> queryLastCategory();

    /**
     * 根据品牌id查询该品牌的分类信息（用于修改品牌时的数据回显）
     * @param bid
     * @return
     */
    @Select("select * from tb_category where id in (select category_id from tb_category_brand where brand_id = #{bid})")
    List<Category> queryCategoryByBrandId(@Param("bid") Long bid);
}
