package com.leyou.web.controller;

import com.leyou.domain.SpecGroup;
import com.leyou.domain.SpecParam;
import com.leyou.enums.ExceptionEnum;
import com.leyou.exception.LyException;
import com.leyou.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spec")
public class SpecController {
    @Autowired
    private SpecService specService;

    /**
     * 查询规格分组
     * @param cid
     * @return
     */
    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupByCategoryId(@PathVariable("cid") Long cid) {
        List<SpecGroup> list = specService.querySpecGroupByCategoryId(cid);
        return ResponseEntity.ok(list);
    }

    /**
     * 删除规格分组
     * @param id
     * @return
     */
    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteSpecGroupById(@PathVariable("id") Long id) {
        specService.deleteSpecGroupById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 更新规格分组
     * @param specGroup
     * @return
     */
    @PutMapping("/group")
    public ResponseEntity<Void> updateSpecGroup(SpecGroup specGroup) {
//        System.out.println(specGroup);
        specService.updateSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 新增分组
     * @param specGroup
     * @return
     */
    @PostMapping("/group")
    public ResponseEntity<Void> saveSpecGroup(SpecGroup specGroup) {
        System.out.println(specGroup);
        specService.saveSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 根据规格分组id查询规格参数
     * @param gid
     * @return
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> querySpecParamsByGroupId(@RequestParam("gid") Long gid) {
        List<SpecParam> list = specService.querySpecParamsByGroupId(gid);
        return ResponseEntity.ok(list);
    }

    /**
     * 根据id删除规格参数
     * @param id
     * @return
     */
    @DeleteMapping("/param/{id}")
    public ResponseEntity<Void> deleteSpecParamById(@PathVariable("id") Long id) {
        specService.deleteSpecParamById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 更新规格参数
     * @return
     */
    @PutMapping("/param")
    public ResponseEntity<Void> updateSpecParam(SpecParam specParam) {
//        System.out.println(specParam);
        specService.updateSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 新增规格参数
     * @param specParam
     * @return
     */
    @PostMapping("/param")
    public ResponseEntity<Void> saveSpecParam(SpecParam specParam) {
//        System.out.println(specParam);
        specService.saveSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
