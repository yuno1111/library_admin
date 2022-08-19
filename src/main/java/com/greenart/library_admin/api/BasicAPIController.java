package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.data.AdminAccountInfoVO;
import com.greenart.library_admin.data.ReaderStatusEditVO;
import com.greenart.library_admin.data.RecurringPaymentVO;
import com.greenart.library_admin.mapper.BasicMapper;
import com.greenart.library_admin.util.AESAlgorithm;


@RestController
@RequestMapping("/api")
public class BasicAPIController {
    private static String[] makePwd = {"a","b","c","d","e","f","g","h","i","j","k","l","1","2","3","4","5","6","7","8","9","0"};
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

    @PutMapping("/payment_add")
    public Map<String,Object> putPayment(@RequestBody RecurringPaymentVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.insertPayment(data);
        resultMap.put("status", true);
        resultMap.put("message", "구독권 정보를 추가하였습니다.");
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
    @DeleteMapping("/payment_del")
    public Map<String,Object> deletePayment(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.deletePayment(seq);
        resultMap.put("status", true);
        resultMap.put("message", "구독권 정보를 삭제하였습니다.");
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
    public Map<String,Object> deleteAdminAccount(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.deleteAdminAccount(seq);
        resultMap.put("status", true);
        resultMap.put("message", "관리자계정을 삭제하였습니다");
        return resultMap;
    }

    @DeleteMapping("/user/delete")
    public Map<String,Object> deleteUserAccount(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.deleteUserAccount(seq);
        resultMap.put("status", true);
        resultMap.put("message", "유저계정을 삭제하였습니다");
        return resultMap;
    }
    @GetMapping("/user/status")
    public Map<String,Object> getUserStatus(@RequestParam Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        resultMap.put("list", basic_mapper.selectReaderStatus(seq));
        return resultMap;
    }
    @PutMapping("/user_status/edit")
    public Map<String,Object> putUserStatus(@RequestBody ReaderStatusEditVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        basic_mapper.insertReaderStatusEdit(data);
        basic_mapper.updateReaderStatus(data.getRd_seq(), data.getStatus());
        resultMap.put("status", true);
        resultMap.put("message", "유저 상태를 변경하였습니다.");
        return resultMap;
    }
    @PatchMapping("/user/pwd_reset")
    public Map<String,Object> putUserStatus(@RequestParam Integer seq) throws Exception{
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        int r = (int)(Math.random()*2);
        String tempPwd = "";
        for(int i=0; i<8; i++){
            if(r == 0) {
                tempPwd += makePwd[(int)(Math.random()*makePwd.length)];
            }
            else {
                tempPwd += makePwd[(int)(Math.random()*makePwd.length)];
            }
        } 
        resultMap.put("status", true);
        resultMap.put("message", "임시 비밀번호는 : "+tempPwd+" 입니다.");
        String realPwd =  AESAlgorithm.Encrypt(tempPwd);
        basic_mapper.updateReaderPwd(seq, realPwd);
        return resultMap;
    }
}
