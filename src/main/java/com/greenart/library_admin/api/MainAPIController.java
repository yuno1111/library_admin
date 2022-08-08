package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.mapper.MainMapper;

@RestController
@RequestMapping("/api")
public class MainAPIController {
    @Autowired MainMapper main_mapper;
    @GetMapping("/qna_list")
    public Map<String,Object> getDashBoard(
    @RequestParam @Nullable Integer page , 
    @RequestParam @Nullable String keyword
    ){
        Map<String,Object> resulMap = new LinkedHashMap<String,Object>();
        if(page==null)page=1;
        resulMap.put("page", page);
        resulMap.put("keyword", keyword);
        resulMap.put("qnaList", main_mapper.selectAllQuestion(keyword, (page-1)*10));
        resulMap.put("qnaCnt", main_mapper.selectQuestionByCnt(keyword));
        return resulMap;
    }
}
