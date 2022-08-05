package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.data.Answer_infoVO;
import com.greenart.library_admin.mapper.QnaMapper;

@RestController
@RequestMapping("/api")
public class QnaAPIController {
    @Autowired QnaMapper qna_mapper;
    @PutMapping("/answer")
    @Transactional
    public Map<String,Object> putAnswer(@RequestBody Answer_infoVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        qna_mapper.insertAnswerInfo(data);
        qna_mapper.updateQuestionStatus(data.getAn_qi_seq());
        resultMap.put("status", true);
        resultMap.put("message", "답변이 등록되었습니다.");
        return resultMap;
    }
    @PatchMapping("/answer")
    public Map<String,Object> patchAnswer(@RequestBody Answer_infoVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        qna_mapper.updateAnserInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "답변이  수정되었습니다.");
        return resultMap;
    }
}
