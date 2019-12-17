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
    BRAND_SAVE_ERROR(500, "品牌新增失败！"),
    UPLOAD_FILE_ERROR(500, "文件上传失败！"),
    INVALID_FILE_TYPE(400, "无效的文件类型！")
    ;
    // 状态码
    private int code;
    // 异常信息
    private String msg;

}
