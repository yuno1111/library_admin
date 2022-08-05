package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.mapper.BasicMapper;


@RestController
@RequestMapping("/api")
public class BasicAPIController {
    @Autowired BasicMapper basic_mapper;
    @PutMapping("/genre_add")
    public Map<String,Object> putGenre(@RequestParam String name){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.insertGenre(name);
        resultMap.put("status", true);
        resultMap.put("message", "장르 정보를 추가하였습니다.");
        return resultMap;
    }

    @PutMapping("/writer_add")
    public Map<String,Object> putWriter(@RequestParam String name){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.insertWriter(name);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보를 추가하였습니다.");
        return resultMap;
    }

    @DeleteMapping("/genre_del")
    public Map<String,Object> deleteGenre(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.deleteGenre(seq);
        resultMap.put("status", true);
        resultMap.put("message", "장르 정보를 삭제하였습니다.");
        return resultMap;
    }
    @DeleteMapping("/writer_del")
    public Map<String,Object> deleteWriter(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.deleteWriter(seq);
        resultMap.put("status", true);
        resultMap.put("message", "작가 정보를 삭제하였습니다.");
        return resultMap;
    }
}
