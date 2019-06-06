package com.cloudwalk.shark.aspect;

import com.alibaba.fastjson.JSONObject;
import com.cloudwalk.shark.common.utils.JSONSensitiveUtils;
import com.google.common.base.Charsets;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class HttpTraceLogFilter extends OncePerRequestFilter implements Ordered {

    private static final String[] NEED_TRACE_PATH_PREFIX = {"/validate","/encrypt"};
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
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
            status = response.getStatus();
        } finally {
            String path = request.getRequestURI();
            for(String tracePathPrefix :NEED_TRACE_PATH_PREFIX) {
                if (path.startsWith(tracePathPrefix) && !Objects.equals(IGNORE_CONTENT_TYPE, request.getContentType())) {

                    String requestBody = IOUtils.toString(request.getInputStream(), Charsets.UTF_8);
                    log.info(requestBody);
                    //1. 记录日志
                    HttpTraceLog traceLog = new HttpTraceLog();
                    traceLog.setPath(path);
                    traceLog.setMethod(request.getMethod());
                    long latency = System.currentTimeMillis() - startTime;
                    traceLog.setTimeTaken(latency);
                    traceLog.setTime(LocalDateTime.now().toString());
                    traceLog.setParameterMap(JsonMapper.INSTANCE.toJson(request.getParameterMap()));
                    traceLog.setStatus(status);
                    traceLog.setRequestBody(getRequestBody(request));
                    traceLog.setResponseBody(getResponseBody(response));
                    log.info("Http trace log: {}", JsonMapper.INSTANCE.toJson(traceLog));
                    break;
                }
            }
            updateResponse(response);
        }
    }

    private boolean isRequestValid(HttpServletRequest request) {
        try {
            new URI(request.getRequestURL().toString());
            return true;
        } catch (URISyntaxException ex) {
            return false;
        }
    }

    private String getRequestBody(HttpServletRequest request) {
        return getBody(request,null);
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private String getBody(HttpServletRequest request, HttpServletResponse response) {
        ContentCachingRequestWrapper requestWrapper = null;
        ContentCachingResponseWrapper responseWrapper = null;
        String contentType = "";
        String body = "";
        try {
            if (request != null) {
                requestWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
                if (requestWrapper != null) {
                    body = IOUtils.toString(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
                    contentType = request.getContentType();
                }
            }
            if (response != null) {
                responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
                if (responseWrapper != null) {
                    body = IOUtils.toString(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
                    contentType = response.getContentType();
                }
            }
            switch (contentType) {
                case MediaType.APPLICATION_FORM_URLENCODED:
                    break;
                // 暂时只支持application/json的请求脱敏
                case MediaType.APPLICATION_JSON:
                    JSONObject jsonObject = JSONObject.parseObject(body);
                    if (!jsonObject.isEmpty()) {
                        return JSONSensitiveUtils.changeSensitiveMsg(jsonObject).toJSONString();
                    }
                    break;
                default:
                    System.out.println(response.getContentType());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return body;
    }


    private String getResponseBody(HttpServletResponse response) {
        return getBody(null,response);
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        Objects.requireNonNull(responseWrapper).copyBodyToResponse();
    }


    @Data
    private static class HttpTraceLog {

        private String path;
        private String parameterMap;
        private String method;
        private Long timeTaken;
        private String time;
        private Integer status;
        private String requestBody;
        private String responseBody;
    }


}
