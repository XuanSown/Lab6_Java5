package com.example.lab6.interceptor;

import com.example.lab6.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
//chặn user chưa login
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(session.getAttribute("user") == null) {
            //lưu lại trang đang muốn vào
            session.setAttribute("securityUri", request.getRequestURI());
            response.sendRedirect("/account/login");
            return false;
        }
        return true;
    }
}
