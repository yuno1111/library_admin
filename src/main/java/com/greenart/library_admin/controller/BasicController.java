package com.greenart.library_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.greenart.library_admin.mapper.BasicMapper;

@Controller
public class BasicController {
    @Autowired BasicMapper basic_mapper;
    @GetMapping("/basic")
    public String getBasic(Model model){
    model.addAttribute("genreList", basic_mapper.selectGenre());
    model.addAttribute("writerList", basic_mapper.selectWriter());
    return "/basic/basic_data";
    }
}
