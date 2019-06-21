package com.cloudwalk.shark.config.aspect.request.limit;

import com.cloudwalk.shark.common.util.date.DatePattern;
import com.cloudwalk.shark.config.annotation.RequestLimit;
import com.cloudwalk.shark.config.exception.RequestLimitException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.config.annotation
 * @date:2019/6/21
 */
@Aspect
@Component
public class RequestLimitContract {
    private static final Logger logger = LoggerFactory.getLogger("RequestLimitLogger");
    private Map<String, Integer> redisTemplate=new ConcurrentHashMap<>();

    final public AtomicInteger maxWaitInSecond = new AtomicInteger(0);
    ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        try {

            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            if(redisTemplate.get(key)==null || redisTemplate.get(key)==0){
                redisTemplate.put(key,1);
            }else{
                redisTemplate.put(key,redisTemplate.get(key)+1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                scheduledExecutorService.schedule(new Runnable() {
                    //创建一个新的计时器任务。
                    @Override
                    public void run() {
                        System.out.println("第"+maxWaitInSecond+"清除key:"+ DateFormatUtils.format(new Date(), DatePattern.YYYY_MM_DD_HH_MM_SS.getPattern()));
                        redisTemplate.remove(key);
                    }
                },limit.time(), TimeUnit.MILLISECONDS);
                //安排在指定延迟后执行指定的任务。task : 所要安排的任务。10000 : 执行任务前的延迟时间，单位是毫秒。
            }
            if (count > limit.count()) {
                System.out.println("第"+maxWaitInSecond+"超时限制:"+ DateFormatUtils.format(new Date(), DatePattern.YYYY_MM_DD_HH_MM_SS.getPattern()));

                //logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常: ", e);
        }finally {
            System.out.println("第"+maxWaitInSecond+"次访问拦截;"+DateFormatUtils.format(new Date(), DatePattern.YYYY_MM_DD_HH_MM_SS.getPattern()));
            maxWaitInSecond.getAndIncrement();
        }
    }
}