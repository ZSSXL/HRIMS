package com.zss.hrims.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author ZSS
 * @date 2022/7/22 13:47
 * @desc java-jwt 工具
 */
@SuppressWarnings("unused")
public class TokenUtil {

    private static final String ISSUER;
    private static final String SECRET;
    private static final String SUBJECT;


    /**
     * 有效期 - 3天
     */
    private static final long EXPIRATION_DATE = 1000L * 60L * 60L * 24L * 3L * 10L;

    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = TokenUtil.class
                .getClassLoader()
                .getResourceAsStream("config/jwt-config.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ISSUER = properties.getProperty("jwt.issuer");
        SECRET = properties.getProperty("jwt.secret-key");
        SUBJECT = properties.getProperty("jwt.subject");
    }

    /**
     * 生成Token - 三天失效
     *
     * @param map 自定义键值对
     * @return String
     */
    public static String createToken(Map<String, String> map) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_DATE));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue());
        }
        return builder.sign(algorithm);
    }

    /**
     * 通过token获取其中的key对应的值
     *
     * @param token 认证的token
     * @param key   对应的键
     * @return claim对象
     */
    public static Claim getClaim(String token, String key) throws JWTVerificationException {
        DecodedJWT jwt = getDecoded(token);
        return jwt.getClaim(key);
    }

    /**
     * 验证Token是否过期失效
     *
     * @param token token
     * @return boolean
     */
    public static boolean isValid(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        } else {
            try {
                getDecoded(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * 获取 decoded
     *
     * @param token token串
     * @return DecodedJWT
     */
    private static DecodedJWT getDecoded(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }
}