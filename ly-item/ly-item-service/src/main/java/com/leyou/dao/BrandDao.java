package com.leyou.dao;

import com.leyou.domain.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BrandDao extends Mapper<Brand> {
    /**
     * 新增/编辑品牌维护中间表
     * @param cid
     * @param bid
     * @return
     */
    @Insert("insert into  tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 删除品牌维护中间表
     * @param id
     */
    @Delete("delete from tb_category_brand where brand_id = #{bid}")
    void deleteByBrandIdInCategoryBrand(@Param("bid") Long id);

}
