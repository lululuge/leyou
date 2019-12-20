package com.leyou.service;

import com.leyou.domain.SpecGroup;
import com.leyou.domain.SpecParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecService {

    /**
     * 查询规格分组
     * @param cid
     * @return
     */
    List<SpecGroup> querySpecGroupByCategoryId(Long cid);

    /**
     * 删除规格分组
     * @param id
     */
    @Transactional
    void deleteSpecGroupById(Long id);

    /**
     * 更新规格分组
     * @param specGroup
     */
    @Transactional
    void updateSpecGroup(SpecGroup specGroup);

    /**
     * 新增分组
     * @param specGroup
     */
    @Transactional
    void saveSpecGroup(SpecGroup specGroup);

    /**
     * 根据规格分组id查询规格参数
     * @param gid
     * @return
     */
    List<SpecParam> querySpecParamsByGroupId(Long gid);

    /**
     * 根据id删除规格参数
     * @param id
     * @return
     */
    void deleteSpecParamById(Long id);

    /**
     * 更新规格参数
     * @return
     */
    void updateSpecParam(SpecParam specParam);

    /**
     * 新增规格参数
     * @param specParam
     * @return
     */
    void saveSpecParam(SpecParam specParam);
}
