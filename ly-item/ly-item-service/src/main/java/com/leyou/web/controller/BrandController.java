package com.leyou.web.controller;

import com.leyou.domain.Brand;
import com.leyou.service.BrandService;
import com.leyou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc)
    {
        return ResponseEntity.ok(brandService.queryBrandByPage(key, page, rows, sortBy, desc));
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @DeleteMapping("/bid/{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("bid") Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 编辑品牌
     * @param brand
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> editBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.editBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
