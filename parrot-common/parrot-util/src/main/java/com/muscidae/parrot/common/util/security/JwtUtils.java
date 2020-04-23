package com.muscidae.parrot.common.util.security;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

/**
 * @author muscidae
 * @date 2020/4/2 00:24
 * @copyright ©2020
 * @description Jwt工具类
 */
public final class JwtUtils {

    private JwtUtils(){ }

    /**
     * @author muscidae
     * @date 2020/4/7 18:13
     * @description 构建Jwt
     * @param claims 负荷载体
     * @param expire 存活时间/秒
	 * @param signatureAlgorithm 加密类型
	 * @param secret 全局私钥
     * @return java.lang.String
     */
    public String compact(Map<String, Object> claims, Long expire,
                              final SignatureAlgorithm signatureAlgorithm, final String secret){
        return compact(null, claims, null,
                null, null, null, null, 1000 * expire, null, System.currentTimeMillis(),
                signatureAlgorithm, secret);
    }

    /**
     * @author muscidae
     * @date 2020/4/2 1:14
     * @description 构建Jwt
     * @param id jwt唯一ID
	 * @param issuer jwt签发者
	 * @param subject jwt所面向的用户, 放登录的用户名等
     * @param audience 接收者url地址
	 * @param expire 存活时间/时间戳
	 * @param notBefore 指定至少在...之前时间/时间戳
     * @param issuedAt 签发时间/时间戳
	 * @param header 设置jwt头部信息, 不设置自动生成
	 * @param claims 负荷载体
	 * @param signatureAlgorithm 加密类型
	 * @param secret 全局私钥
     * @return java.lang.String
     */
    public String compact(Map<String, Object> header, Map<String, Object> claims, String payload,
                              String id, String issuer, String subject, String audience, Long expire, Long notBefore, Long issuedAt,
                              final SignatureAlgorithm signatureAlgorithm, final String secret){
        JwtBuilder builder = Jwts.builder();
        if (null != header && !header.isEmpty())
            builder.setHeader(header);
        if (null != claims && !claims.isEmpty())
            builder.setClaims(claims);
        if (null != payload && !payload.isEmpty())
            builder.setPayload(payload);
        if (null != id && !id.isEmpty())
            builder.setId(id);
        if (null != issuer && !issuer.isEmpty())
            builder.setIssuer(issuer);
        if (null != subject && !subject.isEmpty())
            builder.setSubject(subject);
        if (null != audience && !audience.isEmpty())
            builder.setAudience(audience);
        if (null != expire)
            builder.setExpiration(new Date(System.currentTimeMillis() + expire));
        if (null != notBefore)
            builder.setNotBefore(new Date(notBefore));
        if (null != issuedAt)
            builder.setIssuedAt(new Date(issuedAt));
        if (null != signatureAlgorithm && null != secret && !secret.isEmpty())
            builder.signWith(signatureAlgorithm, secret);
        return builder.compact();
    }

    /**
     * @author muscidae
     * @date 2020/4/7 17:14
     * @description jwt解密
     * @param token 调用凭据
	 * @param secret 全局私钥
     * @return io.jsonwebtoken.Claims
     */
    public Claims parser(final String token, final String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public enum Singleton{
        INSTANCE;
        private JwtUtils jwtUtils;
        Singleton(){ jwtUtils = new JwtUtils(); }
        public JwtUtils newInstance(){ return jwtUtils; }
    }

}
