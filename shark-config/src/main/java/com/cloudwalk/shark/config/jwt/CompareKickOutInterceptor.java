package com.cloudwalk.shark.config.jwt;

import com.cloudwalk.shark.config.TokenUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 比较时间戳
 *
 * @author YCKJ1733
 */
public class CompareKickOutInterceptor extends AbstractAuthenticationInterceptor {
    @Override
    public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        String username = TokenUtils.getUsername(token);
        String userKey = PREFIX + username;
        RBucket<String> bucket = redissonClient.getBucket(userKey);
        String redisToken = bucket.get();

        if (token.equals(redisToken)) {
            return true;

        } else if (StringUtils.isBlank(redisToken)) {
            bucket.set(token);

        } else {
            Long redisTokenUnixTime = TokenUtils.getClaim(redisToken, "createTime").asLong();
            Long tokenUnixTime = TokenUtils.getClaim(token, "createTime").asLong();

            // token > redisToken 则覆盖
            if (tokenUnixTime.compareTo(redisTokenUnixTime) > 0) {
                bucket.set(token);

            } else {
                // 注销当前token
                RBucket rBucket = redissonClient.getBucket(token);
                rBucket.delete();
                sendJsonResponse(response, 4001, "您的账号已在其他设备登录");
                return false;
            }
        }
        return true;

    }
}
