package com.cloudwalk.shark.config.jwt;

import com.cloudwalk.shark.config.TokenUtils;
import com.cloudwalk.shark.config.annotation.LoginRequired;
import com.cloudwalk.shark.model.User;
import com.cloudwalk.shark.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    public final static String ACCESS_TOKEN = "accessToken";
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if(methodAnnotation!=null) {
            // 判断是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getHeader("Authorization");
            if (null == accessToken) {
                throw new RuntimeException("无token，请重新登录");
            } else {
                // 从Redis 中查看 token 是否过期
                Claims claims;
                try {
                    claims = TokenUtils.parseJWT(accessToken);
                } catch (ExpiredJwtException e) {
                    response.setStatus(401);
                    throw new RuntimeException("token失效，请重新登录");
                } catch (SignatureException se) {
                    response.setStatus(401);
                    throw new RuntimeException("token令牌错误");
                }
                String userName = claims.getId();
                User user = userService.findUserByName(userName);
                if (user == null) {
                    response.setStatus(401);
                     throw new RuntimeException("用户不存在，请重新登录");
                }
                // 当前登录用户@CurrentUser
                request.setAttribute("currentUser", user);
                return true;
            }
        }else{
            return true;
        }
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