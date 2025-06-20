package com.laboratory.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.laboratory.exception.LabException;
import com.laboratory.listener.BaseListener;
import com.laboratory.result.ResultCodeEnum;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 公共工具类
 *
 * @author wb
 * @since 2024-07-20
 */
public class CommonUtils {

    /**
     * 获取 request
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }


    /**
     * 导出数据到Excel
     *
     * @param response       响应对象
     * @param fileNamePrefix 文件名前缀
     * @param headDataClass  Excel头部数据类
     * @param dataList       数据列表
     * @param <T>            Excel映射类泛型
     * @param <S>            数据列表泛型
     */
    public static <T, S> void exportDataToExcel(HttpServletResponse response, String fileNamePrefix, Class<T> headDataClass, List<S> dataList) {
        try {
            // 生成文件名
            String timestamp = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
            String randomSuffix = RandomUtil.randomNumbers(5);
            String filename = fileNamePrefix + "-" + timestamp + randomSuffix;

            // 编码文件名以支持中文
            String encodedFileName = URLEncoder.encode(filename, "UTF-8");

            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName + ".xlsx");

            // 写入Excel到输出流
            EasyExcel.write(response.getOutputStream())
                    .head(headDataClass)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet(fileNamePrefix + "-sheet")
                    .doWrite(dataList);
        } catch (Exception e) {
            throw new LabException(ResultCodeEnum.DATA_EXPORT_ERROR);
        }
    }


    /**
     * 导入数据
     *
     * @param filePath      文件路径
     * @param headDataClass Excel头部数据类
     * @param baseListener  监听器
     * @param <T>           Excel映射泛型
     */
    public static <T> void importDataToExcel(String filePath, Class<T> headDataClass, BaseListener<T, ?> baseListener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(filePath).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("请求失败: " + response);
            }

            ResponseBody body = response.body();

            if (body == null) {
                throw new RuntimeException("响应体为空");
            }

            EasyExcel.read(body.byteStream(), headDataClass, baseListener).excelType(ExcelTypeEnum.XLSX).sheet().doRead();

        } catch (Exception e) {
            throw new LabException(ResultCodeEnum.DATA_IMPORT_ERROR);
        }
    }

}
