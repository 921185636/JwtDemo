package com.lst.jwt.demo.util;

import com.lst.jwt.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtil {
    private static long expireTime = 60 * 1000;
    private static String secret_key = "lstsdbd-1996@2020.wtf";

    public static Claims parseJwt(String jwt) throws Exception {
        try {
            SecretKey secretKey = KEY();
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();

            return claims;
        } catch (Exception e) {
            throw new Exception("解析token失败");
        }
    }

    public static String createJwt(User user) {
        SecretKey secretKey = KEY();
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        Date expireDate = new Date(now + expireTime);
        //签名算法
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        //生成token
        JwtBuilder jwtBuilder = Jwts.builder()
                .claim("username", user.getUsername())
                .setExpiration(expireDate)
                .setIssuedAt(nowDate)
                .signWith(hs256, secretKey);

        return jwtBuilder.compact();
    }

    public static SecretKey KEY() {
        String key = Base64Codec.BASE64.encode(secret_key.getBytes());
        System.out.println("key: " + key);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), 0, key.length(), "AES");
        return secretKey;
    }

}
