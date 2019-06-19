package com.cloudwalk.shark.config.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    /**
     * 过期时间，单位毫秒
     */
    public static final long EXPIRE_TIME_MS = 604800 * 1000;

    /**
     * 签名秘钥
     */
    public static final String SECRET = "shark";

    /**
     * 生成token
     *
     * @param id 一般传入userName
     * @return
     */
    public static String createJwtToken(String id) {
        String issuer = "www.xxxx.com";
        String subject = "xxxx@126.com";
        long ttlMillis = 3600000;
        return createJwtToken(id, issuer, subject, ttlMillis);
    }

    /**
     * 生成Token
     *
     * @param id        编号
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 签发时间 （有效时间，过期会报错）
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Map<String,Object> claimsMap = new HashMap();
        claimsMap.put("userName",id);
        claimsMap.put("createTime",nowMillis);
        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .addClaims(claimsMap)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    // Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Claim getClaim(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(TokenUtils.createJwtToken("11111"));
    }
}