package com.leyou.service.impl;

import com.leyou.dao.SpecGroupDao;
import com.leyou.dao.SpecParamDao;
import com.leyou.domain.SpecGroup;
import com.leyou.domain.SpecParam;
import com.leyou.enums.ExceptionEnum;
import com.leyou.exception.LyException;
import com.leyou.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service("specService")
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecGroupDao specGroupDao;

    @Autowired
    private SpecParamDao specParamDao;
    /**
     * 查询规格分组
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> querySpecGroupByCategoryId(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> list = specGroupDao.select(specGroup);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return list;
    }

    /**
     * 删除规格分组
     * @param id
     */
    @Override
    public void deleteSpecGroupById(Long id) {
        specGroupDao.deleteByPrimaryKey(id);
    }

    /**
     * 更新规格分组
     * @param specGroup
     */
    @Override
    public void updateSpecGroup(SpecGroup specGroup) {
        specGroupDao.updateByPrimaryKeySelective(specGroup);
    }

    /**
     * 新增分组
     * @param specGroup
     */
    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        specGroupDao.insert(specGroup);
    }

    /**
     * 根据规格分组id查询规格参数
     * @param gid
     * @return
     */
    @Override
    public List<SpecParam> querySpecParamsByGroupId(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> list = specParamDao.select(specParam);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return list;
    }

    /**
     * 根据id删除规格参数
     * @param id
     * @return
     */
    @Override
    public void deleteSpecParamById(Long id) {
        specParamDao.deleteByPrimaryKey(id);
    }

    /**
     * 更新规格参数
     * @return
     */
    @Override
    public void updateSpecParam(SpecParam specParam) {
        specParamDao.updateByPrimaryKeySelective(specParam);
    }

    /**
     * 新增规格参数
     * @param specParam
     * @return
     */
    @Override
    public void saveSpecParam(SpecParam specParam) {
        specParamDao.insert(specParam);
    }
}
