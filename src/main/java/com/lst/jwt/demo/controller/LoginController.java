package com.lst.jwt.demo.controller;

import com.lst.jwt.demo.database.UserData;
import com.lst.jwt.demo.entity.User;
import com.lst.jwt.demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {
    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        //模拟数据库验证
        if (UserData.userMap.containsKey(user.getUsername())) {
            String jwt = JwtUtil.createJwt(user);
            System.out.println("生成的jwt: " + jwt);
            return jwt;
        }

        return "认证失败";
    }

    @RequestMapping("/needToken")
    public String check(HttpServletRequest request) throws Exception {
        String authorization = request.getHeader("Authorization");
        Claims claims = JwtUtil.parseJwt(authorization);
        return  claims.toString();
    }
}
