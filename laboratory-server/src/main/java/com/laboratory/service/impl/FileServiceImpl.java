package com.laboratory.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.laboratory.exception.LabException;
import com.laboratory.model.constant.MinioConstant;
import com.laboratory.result.ResultCodeEnum;
import com.laboratory.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wb
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;

    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            // 获取文件名
            String filename = getFileName(multipartFile.getOriginalFilename());

            // 上传文件
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(MinioConstant.BUCKET_NAME)
                    .object(filename)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .build());

            // 返回文件路径
            return MinioConstant.HOST + "/" + MinioConstant.BUCKET_NAME + "/" + filename;
        } catch (Exception e) {
            throw new LabException(ResultCodeEnum.FILE_ERROR);
        }
    }

    @Override
    public void download(String filePath) {
    }


    /**
     * 传入文件名返回新的文件名
     *
     * @param fileName 文件名
     * @return 新的文件名
     */
    public static String getFileName(String fileName) {
        if (fileName == null) {
            throw new LabException(ResultCodeEnum.FILE_NOT_FIND);
        }

        // 获取当前时间戳
        long currentTime = System.currentTimeMillis();

        // 生成随机数
        String randomNumber = RandomUtil.randomNumbers(5);

        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        // 返回新的文件名
        return currentTime + randomNumber + suffix;
    }
}
