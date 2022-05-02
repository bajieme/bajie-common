package com.bajie.boot.filter;

import com.bajie.base.utils.DateUtils;
import com.bajie.base.utils.HtttpServletUtils;
import com.bajie.base.utils.SignUtils;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author bajie
 * @date 2022-05-02 2:55 下午
 * @since 1.0.0
 */
@Slf4j
public class HttpTraceLogFilter extends OncePerRequestFilter implements Ordered {
    /**
     * 记录日志请求 /api
     */
    private static final String NEED_TRACE_PATH_PREFIX = "/api";
    private static final String IGNORE_CONTENT_TYPE = "multipart/form-data";


    private final MeterRegistry registry;

    public HttpTraceLogFilter(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 耗时计算开始
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if (!isRequestValid(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        try {
            filterChain.doFilter(request, response);
            status = response.getStatus();
        } finally {
            // 耗时计算结束
            stopWatch.stop();
            String path = request.getRequestURI();
            String contentType = request.getContentType();
            log.info("请求contentType: {}", request.getContentType());
            log.info("请求URI: {}", path);
            if (path.startsWith(NEED_TRACE_PATH_PREFIX) && !Objects.equals(IGNORE_CONTENT_TYPE, contentType)) {

                //1. 记录日志
                HttpTraceLog traceLog = new HttpTraceLog();
                traceLog.setPath(path);
                traceLog.setUrl(request.getRequestURL().toString());
                traceLog.setHeaders(SignUtils.getHeaders(request));
                traceLog.setMethod(request.getMethod());
                traceLog.setStopWatch(stopWatch);
                traceLog.setParameterMap(SignUtils.concatParameterMap(request));
                traceLog.setStatus(status);
                traceLog.setRequestBody(HtttpServletUtils.getRequestBody(request));
                traceLog.setResponseBody(HtttpServletUtils.getResponseBody(response));

                log(traceLog);
            }
            updateResponse(response);
        }
    }

    /**
     * 打印日志
     *
     * @param traceLog 日志信息
     */
    private void log(HttpTraceLog traceLog) {
        log.info("{}", "============================================= Http Start  =============================================");
        log.info("时间: {}", DateUtils.formatMilliPlus(traceLog.getStopWatch().getStartTime()));
        log.info("headers: {}", traceLog.getHeaders().toString());
        log.info("methos: {}", traceLog.getMethod());
        log.info("url: {}", traceLog.getUrl());
        log.info("path: {}", traceLog.getPath());
        log.info("parameterMap: {}", SignUtils.concatStr(traceLog.getParameterMap()));
        log.info("requestBody: {}", traceLog.getRequestBody());
        log.info("HttpStatus: {}", traceLog.getStatus());
        log.info("responseBody: {}", traceLog.getResponseBody());
        log.info("耗时：{} 毫秒", traceLog.getStopWatch().getTime(TimeUnit.MILLISECONDS));
        log.info("{}", "============================================= Http End   =============================================");
    }

    private boolean isRequestValid(HttpServletRequest request) {
        try {
            new URI(request.getRequestURL().toString());
            return true;
        } catch (URISyntaxException ex) {
            return false;
        }
    }

    /**
     * 返回参数
     *
     * @param response 、
     * @throws IOException 、
     */
    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        Objects.requireNonNull(responseWrapper).copyBodyToResponse();
    }

    @Data
    private static class HttpTraceLog {

        private String path;
        private String url;
        private Map<String, String> headers;
        private Map<String, String> parameterMap;
        private String method;
        private StopWatch stopWatch;
        private Integer status;
        private String requestBody;
        private String responseBody;

    }
}
