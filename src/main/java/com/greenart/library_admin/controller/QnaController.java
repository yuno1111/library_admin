package com.greenart.library_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.library_admin.mapper.QnaMapper;

@Controller
public class QnaController {
    @Autowired QnaMapper qna_mapper;
    @GetMapping("/question/qna_detail")
    public String getQnaBySeq(Model model,@RequestParam Integer seq){
        model.addAttribute("list", qna_mapper.selectQnaBySeq(seq));
        model.addAttribute("answerList", qna_mapper.selectAnswerBySeq(seq));
        return "/qna/qna_detail";
    }
    @GetMapping("/notice/list")
    public String getBookList(Model model,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer page
    ){
        if(page==null) page=1;
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", qna_mapper.selectAllNoticeInfo(keyword, (page-1)*10));
        model.addAttribute("pageCnt", qna_mapper.selectAllNoticePageCnt(keyword));
        return "/qna/notice_list";
    }
    @GetMapping("/notice/form")
    public String getNoticeAdd(Model model,@RequestParam @Nullable Integer seq){
        model.addAttribute("list", qna_mapper.selectNoticeInfoBySeq(seq));
        return "/qna/notice_form";
    }
}
