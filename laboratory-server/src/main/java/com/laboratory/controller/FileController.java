package com.laboratory.controller;

import com.laboratory.result.Result;
import com.laboratory.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @author wb
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("upload")
    public Result<String> upload(@RequestPart("file") MultipartFile file) {
        return Result.success(fileService.upload(file));

    }

    @GetMapping("download/{filePath}")
    public void download(@PathVariable String filePath) {
        fileService.download(filePath);
    }
}
