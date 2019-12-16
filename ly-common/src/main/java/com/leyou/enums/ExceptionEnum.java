package com.leyou.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    CATEGORY_NOT_FOUND(404, "商品分类未查到！"),
    BRAND_NOT_FOUND(404, "品牌不存在！"),
    BRAND_SAVE_ERROR(404, "品牌新增失败！")
    ;
    // 状态码
    private int code;
    // 异常信息
    private String msg;

}
