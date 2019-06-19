package com.cloudwalk.shark.config.jwt;

import com.cloudwalk.shark.config.TokenUtils;
import com.cloudwalk.shark.config.annotation.LoginRequired;
import com.cloudwalk.shark.model.User;
import com.cloudwalk.shark.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RDeque;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;



public abstract class AbstractAuthenticationInterceptor extends HandlerInterceptorAdapter {

    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService;

    @Autowired
    public RedissonClient redissonClient;

    public static final String PREFIX = "uni_token_";

    public static final String PREFIX_LOCK = "uni_token_lock_";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if(methodAnnotation!=null) {
            return checkToken(request, response) && isAccessAllowed(request, response);
        }else{
            return true;
        }
    }

    /**
     * 检查是否携带token 以及token是否失效
     *
     * @param request
     * @param response
     * @return
     */
    public boolean checkToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            sendJsonResponse(response, 401, "你无权访问");
            return false;
        }
        // 校验token是否存在
        RBucket<User> rBucket = redissonClient.getBucket(token);
        User user = rBucket.get();

        if (user == null) {
            sendJsonResponse(response, 403, "无效令牌");
            return false;
        }
        request.setAttribute("currentUser", user);
        return true;
    }

    /**
     * 是否多个登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public abstract boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws Exception;


    public static void sendJsonResponse(HttpServletResponse resp, int code, String message) {
        sendJsonResponse(resp, String.format(jsonTemplate(), code, message));
    }

    private static void sendJsonResponse(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private static String jsonTemplate() {
        return "{\"code\":%s,\"msg\":\"%s\",\"data\":null,\"errors\":null}";
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}