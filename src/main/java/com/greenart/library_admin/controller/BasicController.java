package com.greenart.library_admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/logout")
    public String getLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/admin/account/list")
    public String getAdminAccountList(Model model,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer page
    ){
        if(page==null)page=1;
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", basic_mapper.selectAllAdminAccount(keyword, (page-1)*10));
        model.addAttribute("pageCnt", basic_mapper.selectAllAdminAccountPageCnt(keyword));
        return "/basic/admin_account_list";
    }
    @GetMapping("/user/account/list")
    public String getUserList(Model model,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer page
    ){
        if(page==null)page=1;
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", basic_mapper.selectAllUserAccount(keyword, (page-1)*10));
        model.addAttribute("pageCnt", basic_mapper.selectAllUserAccountPageCnt(keyword));
        return "/basic/user_account_list";
    }
    @GetMapping("/user/account/detail")
    public String getUserListBySeq(@RequestParam Integer seq){
        return "/basic/user_account_detail";
    }
    
}
