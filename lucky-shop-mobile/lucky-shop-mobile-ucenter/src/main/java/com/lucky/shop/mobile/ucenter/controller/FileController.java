package com.lucky.shop.mobile.ucenter.controller;

import com.lucky.shop.common.core.dto.ResponseResult;
import com.lucky.shop.mobile.ucenter.domain.Base64File;
import com.lucky.shop.mobile.ucenter.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author Guo Huaijian
 * @Date 2020/6/24 2:44
 */
@RestController
@RequestMapping("mobile/ucenter/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "upload")
    public ResponseResult upload(@RequestPart("file") MultipartFile multipartFile) {
        Object file = fileService.uploadFile(multipartFile);
        return ResponseResult.success(file);
    }

    /**
     * 上传文件
     *
     * @param base64File
     * @return
     */
    @PostMapping(value = "upload/base64")
    public ResponseResult uploadUploadFileBase64(@RequestBody Base64File base64File) {
        Object base64 = fileService.uploadUploadFileBase64(base64File);
        return ResponseResult.success(base64);
    }

    /**
     * 下载文件
     *
     * @param fileName
     */
    @GetMapping(value = "download")
    public ResponseResult download(@RequestParam("fileName") String fileName) {
        fileService.download(fileName);
        return ResponseResult.success();
    }

    /**
     * 获取base64图片数据
     *
     * @param fileName
     * @return
     */
    @GetMapping(value = "getImgBase64")
    public ResponseResult getImgBase64(@RequestParam("idFile") String fileName) {
        Object imgBase64 = fileService.getImgBase64(fileName);
        return ResponseResult.success(imgBase64);

    }

    /**
     * 获取图片流
     *
     * @param response
     * @param fileName
     */
    @GetMapping(value = "getImgStream")
    public ResponseResult getImgStream(HttpServletResponse response, @RequestParam("idFile") String fileName) {
        fileService.getImgStream(response, fileName);
        return ResponseResult.success();
    }
}
