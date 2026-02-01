package com.example.lab6.controller;

import com.example.lab6.dao.OrderDetailDAO;
import com.example.lab6.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ReportController {
    @Autowired
    OrderDetailDAO dao;
    @RequestMapping("/report/inventory")
    public String inventory(Model model){
        List<Report> items = dao.getInventory();
        model.addAttribute("items", items);
        return "report/inventory";
    }
}
