package com.leyou.service;

import com.leyou.domain.Brand;
import com.leyou.vo.PageResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BrandService {

    /**
     * 分页查询品牌
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    /**
     * 新增品牌
     * @param brand
     * @param cids
     */
    @Transactional
    void saveBrand(Brand brand, List<Long> cids);

    /**
     * 删除品牌
     * @param id
     */
    @Transactional
    void deleteBrand(Long id);

    /**
     * 编辑品牌
     * @param brand
     * @param cids
     */
    void editBrand(Brand brand, List<Long> cids);
}
