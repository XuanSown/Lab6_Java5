package com.example.lab6.interceptor;

import com.example.lab6.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;
@Component
//ghi user login
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Account user = (Account) request.getSession().getAttribute("user");
        String username = (user != null) ? user.getFullname() : "Guest";

        System.out.println("Log: User " + username + " truy cập " + request.getRequestURI() + " vào lúc " + new Date());
        return true;
    }
}
