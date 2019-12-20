package com.leyou.web.controller;

import com.leyou.domain.Category;
import com.leyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询商品分类列表
     * @param pid
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid") Long pid) {
        if (pid == -1) {
            // 查询最后一条数据并返回
            return ResponseEntity.ok(categoryService.queryLastCategory());
        } else {
            return ResponseEntity.ok(categoryService.queryCategoryListByPid(pid));
        }
    }

    /**
     * 更新商品分类名称
     * @param category
     */
    @PutMapping
    public ResponseEntity<Void> updateCategory(Category category) {
        System.out.println(category);
        categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 新增分类(注意此功能存在一个bug，点击新增按钮之后需要手动刷新再进行名称修改)
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveCategory(Category category) {
        categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("cid/{cid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("cid") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据品牌id查询该品牌的分类信息（用于修改品牌时的数据回显）
     * @param bid
     * @return
     */
    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> list = categoryService.queryCategoryByBrandId(bid);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }
}
