package com.spcrey.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spcrey.pojo.Result;
import com.spcrey.utils.AliyunOssUtil;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {

        String originalFilename = file.getOriginalFilename();

        if(originalFilename != null) {
            String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            String url = AliyunOssUtil.uploadFile(filename, file.getInputStream());
            return Result.success(url);
        }
        else{
            return Result.error("file is none");
        }
    }
    
}
