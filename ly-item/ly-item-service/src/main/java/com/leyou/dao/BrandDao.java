package com.leyou.dao;

import com.leyou.domain.Brand;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BrandDao extends Mapper<Brand> {
}
