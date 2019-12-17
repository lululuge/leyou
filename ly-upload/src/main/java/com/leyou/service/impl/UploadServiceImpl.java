package com.leyou.service.impl;

import com.leyou.enums.ExceptionEnum;
import com.leyou.exception.LyException;
import com.leyou.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service("uploadService")
@Slf4j
public class UploadServiceImpl implements UploadService {

    // 允许上传的文件类型
    private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg", "image/png", "image/bmp");

    /**
     * 上传图片
     * @param file
     * @return
     */
    @Override
    public String uploadImage(MultipartFile file) {
        try {
            // 校验文件类型
            String contentType = file.getContentType();
            if (!ALLOW_TYPES.contains(contentType)) {
                // 类型不匹配抛出异常
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            // 校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream()); // 读取图片内容
            if (image == null) {
                // 图片内容内容为null抛出异常
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            // 设置文件保存路径
            File dest = new File("C:\\Users\\34759\\Desktop\\leyou_upload_image", file.getOriginalFilename());
            // 保存文件到本地
            file.transferTo(dest);
        } catch (IOException e) {
            // 上传失败
            log.error("文件上传失败！", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
        // 返回文件路径
        return null;
    }
}
