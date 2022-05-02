package com.bajie.base.utils;

import com.bajie.base.annotation.NotRepeatSubmit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 签名工具类
 *
 * @author bajie
 * @date 2022-05-02 2:56 下午
 * @since 1.0.0
 */
public class SignUtils {

    private static final Logger log = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 按参数名升续拼接参数
     *
     * @param request 、
     * @return 、
     */
    public static Map<String, String> concatParameterMap(HttpServletRequest request) {
        Map<String, String> paramterMap = new HashMap<>(50);
        request.getParameterMap().forEach((key, value) -> paramterMap.put(key, value[0]));
        return paramterMap;
    }

    /**
     * 获取要签名的字符串
     *
     * @param map 、
     * @return 、
     */
    public static String concatStr(Map<String, String> map) {
        Map<String, String> paramterMap = new HashMap<>(50);
        try {
            map.forEach(paramterMap::put);
        } catch (Exception e) {
            log.info("签名参数必须是String类型才会签名返回");
            return null;
        }
        // 按照key升续排序，然后拼接参数
        Set<String> keySet = paramterMap.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            String value = paramterMap.get(k);
            if (StringUtils.isNotBlank(value)) {
                if (value.trim().length() > 0) {
                    // 参数值为空，则不参与签名
                    sb.append(k).append("=").append(value.trim()).append("&");
                }
            }
        }
        if (sb.length() > 1) {
            // 删除最后一个&
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取方法上的@NotRepeatSubmit注解
     *
     * @param handler 、
     * @return 、
     */
    public static NotRepeatSubmit getNotRepeatSubmit(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            return method.getAnnotation(NotRepeatSubmit.class);
        }
        return null;
    }

    /**
     * @param data 反射的对象,获取对象的字段名和值
     * @throws IllegalArgumentException 、
     * @throws IllegalAccessException   、
     */
    public static Map<String, String> getFields(Object data) throws IllegalAccessException, IllegalArgumentException {
        if (data == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>(100);
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(data);
            if (field.get(data) != null) {
                map.put(name, value.toString());
            }
        }
        return map;
    }

    /**
     * 获取headers
     *
     * @param request 、
     * @return 、
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headerMap = new HashMap<>(16);
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Enumeration<String> headers = request.getHeaders(name);
            while (headers.hasMoreElements()) {
                headerMap.put(name, headers.nextElement());
            }
        }
        return headerMap;
    }
}
