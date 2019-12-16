package com.leyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.dao.BrandDao;
import com.leyou.domain.Brand;
import com.leyou.enums.ExceptionEnum;
import com.leyou.exception.LyException;
import com.leyou.service.BrandService;
import com.leyou.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    /**
     * 分页查询品牌
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @Override
    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤(根据搜索条件进行过滤)
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            // 自定义过滤条件
            example.createCriteria().orLike("name", "%" + key + "%").
                    orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)) {
            // 声明使用升序（asc）还是降序（desc）
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Brand> list = brandDao.selectByExample(example); // 此时list中为当前页面的品牌数据
        if (CollectionUtils.isEmpty(list)) {
            // 查询结果为空则抛出自定义异常
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        // 返回分页结果
        return new PageResult<Brand>(new PageInfo<Brand>(list).getTotal(), list);
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     */
    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        // 在品牌表中实现新增
        brand.setId(null);
        int count = brandDao.insert(brand);
        if (count != 1) {
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        // 在中间表中实现新增
        for (Long cid : cids) {

        }
    }
}
