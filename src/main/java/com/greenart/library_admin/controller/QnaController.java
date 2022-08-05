package com.greenart.library_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
