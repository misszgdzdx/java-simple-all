package com.github.io.shiro.shirojwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.io.shiro.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 生成验证jwt工具类
 */
@Component
public class JwtUtil {

    private static long EXPIRE_TIME;

    /**
     * 读取配置的时间，分钟
     */
    @Value("${jwt.expire.time}")
    private void setExpireTime(String time) {
        if (StringUtils.isBlank(time)) {
            time = Constants.JWT_EXPIRE_TIME;
        }
        JwtUtil.EXPIRE_TIME = Long.parseLong(time);
    }

    /**
     * 验证token
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取token保存的用户信息
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成签名 + 设置过期时间
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME * 60 * 1000);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.create()
                      .withClaim("username", username)
                      .withExpiresAt(date)
                      .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}