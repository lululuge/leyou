package com.leyou.dao;

import com.leyou.domain.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface CategoryDao extends Mapper<Category> {

}
