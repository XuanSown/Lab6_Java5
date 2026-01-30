package com.example.lab6.controller;

import com.example.lab6.dao.CategoryDAO;
import com.example.lab6.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryDAO dao;

    @RequestMapping("/category/index")
    public String index(Model model){
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id){
        Category item = dao.findById(id).orElse(new Category());
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    @RequestMapping("/category/create")
    public String create(Category item, Model model){
        if(item.getId() == null || item.getId().isEmpty() || item.getName().isEmpty()){
            model.addAttribute("message", "Vui lòng nhập ID và Tên");
            return "forward:/category/index";
        }
        if(dao.existsById(item.getId())){
            model.addAttribute("message", "ID tồn tại");
            return "forward:/category/index";
        }
        dao.save(item);
        return "redirect:/category/index";
    }

    @RequestMapping("/category/update")
    public String update(Category item, Model model){
        if (item.getId().isBlank() || item.getName().isBlank()) {
            model.addAttribute("message", "Vui lòng nhập đầy đủ Mã và Tên để cập nhật!");
            model.addAttribute("items", dao.findAll());
            return "category/index";
        }
        if (!dao.existsById(item.getId())) {
            model.addAttribute("message", "Mã loại hàng này không tồn tại!");
            model.addAttribute("items", dao.findAll());
            return "category/index";
        }

        dao.save(item);
        return "redirect:/category/edit/" + item.getId();
    }

    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id){
        dao.deleteById(id);
        return "redirect:/category/index";
    }
}
