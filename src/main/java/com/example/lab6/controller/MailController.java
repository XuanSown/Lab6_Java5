package com.example.lab6.controller;

import com.example.lab6.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {
    @Autowired
    MailService mailService;

    @RequestMapping("/mail/view")
    public String demoMail(){
        return "mail/view";
    }

    @PostMapping("/mail/send")
    public String send(Model model,
                       @RequestParam("to") String to,
                       @RequestParam("subject") String subject,
                       @RequestParam("body") String body){
        try {
            mailService.send(to, subject, body);
            model.addAttribute("message", "thành công!");
        } catch (Exception e) {
            model.addAttribute("message","Lỗi: " + e.getMessage());
        }
        return "mail/view";
    }
}
