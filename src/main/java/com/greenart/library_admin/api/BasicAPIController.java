package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.data.AdminAccountInfoVO;
import com.greenart.library_admin.mapper.BasicMapper;
import com.greenart.library_admin.util.AESAlgorithm;


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

    @PostMapping("/login")
    public Map<String,Object> postAdminLogin(@RequestBody AdminAccountInfoVO data, HttpSession session) throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();

        data.setAai_pwd(AESAlgorithm.Encrypt(data.getAai_pwd()));
        AdminAccountInfoVO admin = basic_mapper.selectAdminLogin(data.getAai_id(), data.getAai_pwd());

        if(admin == null) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        resultMap.put("message", "로그인 성공");
        session.setAttribute("admin", admin);
        return resultMap;
    }
    @PutMapping("/admin/join")
    public Map<String,Object> putAdminJoin(@RequestBody AdminAccountInfoVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.insertAdminAccount(data);
        resultMap.put("status", true);
        resultMap.put("message", "관리자를 추가하였습니다");
        return resultMap;
    }
    @DeleteMapping("/admin/delete")
    public Map<String,Object> putAdminJoin(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.deleteAdminAccount(seq);
        resultMap.put("status", true);
        resultMap.put("message", "관리자계정을 삭제하였습니다");
        return resultMap;
    }
}
