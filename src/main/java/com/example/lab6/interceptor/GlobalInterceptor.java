package com.example.lab6.interceptor;

import com.example.lab6.dao.CategoryDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
//chia sẻ danh mục cho all page
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryDAO dao;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        request.setAttribute("categories", dao.findAll());
    }
}
