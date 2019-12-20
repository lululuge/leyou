package com.leyou.service.impl;

import com.leyou.dao.CategoryDao;
import com.leyou.domain.Category;
import com.leyou.enums.ExceptionEnum;
import com.leyou.exception.LyException;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    /**
     * 根据父节点id查询商品分类列表
     * @param pid
     * @return
     */
    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        // 自定义查询条件，select方法会将对象中的非空属性作为查询条件
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryDao.select(category);
//        System.out.println(categoryList);
        // 判断查询结果是否为空
        if (CollectionUtils.isEmpty(categoryList)) {
            // 抛出自定义异常
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }

    /**
     * 更新商品分类名称
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        // 该方法只更新category对象中的非空属性
        categoryDao.updateByPrimaryKeySelective(category);
    }

    /**
     * 新增商品分类
     * @param category
     */
    @Override
    public void saveCategory(Category category) {
        /**
         * 1.将新增节点数据插入到数据库中
         * 2.将插入节点的父节点的isParent属性设置为true
         */
        // 插入新增节点(此处手动设置id，为了防止删除了最后一条数据，下一次自增长的id不连续)
        category.setId(categoryDao.queryLastCategory().get(0).getId() + 1);
        categoryDao.insert(category);
        // 设置父节点
        Category parent = new Category();
        parent.setId(category.getParentId());
        parent.setIsParent(true);
        categoryDao.updateByPrimaryKeySelective(parent);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteCategory(Long id) {
        // 根据id查询出当前节点
        Category currentNode = categoryDao.selectByPrimaryKey(id);
        // 判断该节点是否为父节点
       if (currentNode.getIsParent()) {
           // 是父节点
           // 查询该节点的所有叶子结点
           List<Category> leafNodes = new ArrayList<Category>();
           queryLeafNodes(currentNode, leafNodes);
           // 查询该节点的所有子结点
           List<Category> childNodes = new ArrayList<Category>();
           queryChildNodes(currentNode, childNodes);
           // 删除所有子节点
           for (Category childNode : childNodes) {
               categoryDao.delete(childNode);
           }
           // 维护中间表（在中间表中删除每一个叶子结点所对应的商品分类）
           for (Category leafNode : leafNodes) {
               categoryDao.deleteByCategoryIdInCategoryBrand(leafNode.getId());
           }

       } else {
           // 不是父节点
           // 先删除该结点
           categoryDao.delete(currentNode);
           // 维护中间表
           categoryDao.deleteByCategoryIdInCategoryBrand(currentNode.getId());
           // 判断该结点的父结点的子结点个数是否为0
           Category parentNode = categoryDao.selectByPrimaryKey(currentNode.getParentId());
           // 查询子节点个数
           List<Category> childNodes = new ArrayList<Category>();
           queryChildNodes(parentNode, childNodes);
           int childNodesNum = childNodes.size() - 1;
           if (childNodesNum == 0) {
               // 若子节点个数为0，设置其isParent属性为false
               parentNode.setIsParent(false);
           }
       }
    }

    /**
     * 查询某节点的所有子节点(包含当前节点)
     * @param currentNode
     * @param childNodes
     */
    private void queryChildNodes(Category currentNode, List<Category> childNodes) {
        childNodes.add(currentNode);
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId", currentNode.getId());
        List<Category> list = categoryDao.selectByExample(example);
        for (Category category : list) {
            queryChildNodes(category, childNodes);
        }
    }

    /**
     * 查询某节点的所有叶子节点
     * @param currentNode
     * @param leafNodes
     */
    private void queryLeafNodes(Category currentNode, List<Category> leafNodes) {
        if (!currentNode.getIsParent()) {
            // 当前节点不是父节点
            leafNodes.add(currentNode);
        }
        // 自定义查询条件
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId", currentNode.getId());
        // 查询到该节点的子节点集合
        List<Category> list = categoryDao.selectByExample(example);
        for (Category category : list) {
            queryLeafNodes(category, leafNodes);
        }
    }

    /**
     * 查询表中最后一条商品分类
     * @return
     */
    @Override
    public List<Category> queryLastCategory() {
        return categoryDao.queryLastCategory();
    }

    /**
     * 根据品牌id查询该品牌的分类信息（用于修改品牌时的数据回显）
     * @param bid
     * @return
     */
    @Override
    public List<Category> queryCategoryByBrandId(Long bid) {
        return categoryDao.queryCategoryByBrandId(bid);
    }
}
