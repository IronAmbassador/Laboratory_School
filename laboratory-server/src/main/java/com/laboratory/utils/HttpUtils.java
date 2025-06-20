package com.laboratory.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * http 工具类
 */
@Slf4j
public class HttpUtils {

    /**
     * 获取浏览器名字和版本
     *
     * @return {@link String}
     */
    public static String getBrowserNameAndVersion() {
        HttpServletRequest request = CommonUtils.getRequest();
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getBrowser() + " " + ua.getVersion();
    }

    /**
     * 通过网络获取 IP 地址
     */
    public static String getIpByNetwork() {
        String ip = "127.0.0.1";
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            ip = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取请求的 IP 地址
     */
    public static String getIpAddress() {
        HttpServletRequest request = CommonUtils.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                // 根据网卡取本机配置的 IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("获取IP地址异常，{}", e.getMessage());
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        // 本机访问
        if ("localhost".equalsIgnoreCase(ip) || "127.0.0.1".equalsIgnoreCase(ip) || "0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
            // 根据网卡取本机配置的IP
            InetAddress inet;
            try {
                inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取本机IP地址异常，{}", e.getMessage());
            }
        }
        // 如果查找不到 IP,可以返回 127.0.0.1，可以做一定的处理，但是这里不考虑
        if (ip == null) {
            return "127.0.0.1";
        }
        return ip;
    }


    /**
     * 根据IP地址 获取归属地
     */
    public static String getIpPossessionByFile(String ip) {
        if (StrUtil.isNotEmpty(ip)) {
            try {
                // 1、创建 searcher 对象
                Searcher searcher = Searcher.newWithBuffer(ResourceUtil.readBytes("ip2region.xdb"));
                // 2、查询
                long sTime = System.nanoTime();
                String region = searcher.search(ip);
                long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
                region = region.replace("|0", "");
                //log.info("{地区: {}, IO操作数: {}, 耗时: {} μs}", region, searcher.getIOCount(), cost);
                return region;
            } catch (Exception e) {
                log.error("获取IP地址异常：{} ", e.getMessage());
                throw new RuntimeException("获取IP地址异常");
            }
        }
        return "未知";
    }


    public static void main(String[] args) {
        String ip = "113.96.0.0";
        System.out.println(getIpPossessionByFile(ip));
    }


}
