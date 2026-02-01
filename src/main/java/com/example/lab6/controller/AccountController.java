package com.example.lab6.controller;

import com.example.lab6.dao.AccountDAO;
import com.example.lab6.entity.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @Autowired
    AccountDAO dao;
    @Autowired
    HttpSession session;

    @GetMapping("/account/login")
    public String login(){
        return "account/login";
    }
    @PostMapping("/account/login")
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password){
        try {
            Account user = dao.findById(username).get();
            if (!user.getPassword().equals(password)) {
                model.addAttribute("message","sai password!");
                return "account/login";
            }
            session.setAttribute("user", user);
            //quay lại trang muốn vào nếu có
            Object securityUri = session.getAttribute("securityUri");
            if (securityUri != null) {
                session.removeAttribute("securityUri");
                return "redirect:" + securityUri.toString();
            }
            return "redirect:/product/search-and-page"; //trang chu
        } catch (Exception e) {
            model.addAttribute("message", "sai username hoặc password!");
        }
        return "account/login";
    }

    @RequestMapping("/account/logout")
    public String logout(){
        session.removeAttribute("user");
        session.removeAttribute("securityUri");
        return "redirect:/account/login";
    }
}
