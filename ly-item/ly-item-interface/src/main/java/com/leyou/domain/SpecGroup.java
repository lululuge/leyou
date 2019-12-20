package com.leyou.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 规格分组的实体类
 */
@Table(name = "tb_spec_group")
@Data
public class SpecGroup {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id; // 主键
    private Long cid; // 商品分类id
    private String name; // 规格组名称
}
