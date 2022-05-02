package com.bajie.boot.util;

import cn.hutool.http.ContentType;
import com.bajie.base.http.response.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author bajie
 * @date 2022-05-02 3:03 下午
 * @since 1.0.0
 */
@Slf4j
public class HtttpServletUtils {

    /**
     * 返回参数
     *
     * @param resp       、
     * @param resultCode 、
     * @throws IOException 、
     */
    public static void setResp(HttpServletResponse resp, ResultCode resultCode) throws IOException {
        resp.setStatus(HttpStatus.OK.value());
        resp.setHeader(CONTENT_TYPE, ContentType.JSON.getValue());

        String result = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(getRespMap(resultCode.getCode(), resultCode.getMsg()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(result.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param status 状态码
     * @param msg    信息
     * @return 、
     */
    private static Map<String, Object> getRespMap(int status, String msg) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", status);
        map.put("msg", msg);
        return map;
    }

    /**
     * 获取请求参数
     *
     * @param request 、
     * @return 、
     */
    public static String getRequestBody(HttpServletRequest request) {
        String requestBody = "";
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            try {
                requestBody = IOUtils.toString(wrapper.getContentAsByteArray(), String.valueOf(Charsets.UTF_8));
            } catch (IOException e) {
                // NOOP
            }
        }
        return replaceBlank(requestBody);
    }

    /**
     * 获取返回参数
     *
     * @param response 、
     * @return 、
     */
    public static String getResponseBody(HttpServletResponse response) {
        String responseBody = "";
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            try {
                responseBody = IOUtils.toString(wrapper.getContentAsByteArray(), String.valueOf(Charsets.UTF_8));
            } catch (IOException e) {
                log.error(" responseBody 转化异常:{}", ExceptionUtils.getStackTrace(e));
            }
        }
        return replaceBlank(responseBody);
    }

    /**
     * \n 回车(\u000a)
     * \t 水平制表符(\u0009)
     * \s 空格(\u0008)
     * \r 换行(\u000d)
     *
     * @param str 字符串
     * @return 剔除空格回车换行后的字符串
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = compile("\t|\r|\n|\\s*");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
