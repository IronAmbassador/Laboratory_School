package com.laboratory.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wb
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param multipartFile 文件
     * @return 文件地址
     */
    String upload(MultipartFile multipartFile);

    /**
     * 下载文件
     *
     * @param filePath 文件地址
     */
    void download(String filePath);
}
