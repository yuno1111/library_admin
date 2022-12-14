package com.greenart.library_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.greenart.library_admin.mapper.MainMapper;

@Controller
public class MainController {
    @Autowired MainMapper main_mapper;
    @GetMapping("/")
    public String getLogin(){
        return "/index";
    }
    @GetMapping("/dash_board")
    public String getDashBoard(Model model
    ){
        model.addAttribute("userCnt", main_mapper.selectAllUserCnt());
        model.addAttribute("bookCnt", main_mapper.selectAllBooksCnt());
        model.addAttribute("newBookList", main_mapper.selectNewbooks());
        return "/dash_board";
    }
}
