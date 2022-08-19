package com.greenart.library_admin.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import com.greenart.library_admin.data.PagingVO;
import com.greenart.library_admin.data.Question_infoVO;
import com.greenart.library_admin.data.ReaderInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.mapper.BasicMapper;
import com.greenart.library_admin.mapper.MainMapper;

@RestController
@RequestMapping("/api")
public class MainAPIController {
    private static String[] makeID = {"a","b","c","d","e","f","g","h","i","j","k","l","1","2","3","4","5","6","7","8","9","0"};
    private static String[] first_name = {"김","이","박","최","정","강","조","윤","장","임","한","오","서","신","권"};
    private static String[] middle_name = {"주","정","승","소","윤","민","하","호","정","서","홍","기","인","태","상"};
    private static String[] last_name = {"민","진","혁","원","미","성","람","진","준","숙","원","호","수","은","율"};
    private String makeName() {
        String name = "";
        name += first_name[(int)(Math.random()*first_name.length)];
        name += middle_name[(int)(Math.random()*middle_name.length)];
        name += last_name[(int)(Math.random()*last_name.length)];
        return name;
    }
    private String makeId(Integer len) {
        int r = (int)(Math.random()*2);
        String id = "";
        for(int i=0; i<len; i++){
            if(r == 0) {
                id += makeID[(int)(Math.random()*makeID.length)];
            }
            else {
                id += makeID[(int)(Math.random()*makeID.length)];
            }
        }
        return id;
    }
    public static Date makeDateTime(Integer min, Integer max, Boolean genTime){
        Integer diff = max - min;
        Integer year = (int)(Math.random()*diff+min);
        Integer month = (int)(Math.random()*12);
        Integer date = (int)(Math.random()*28+1);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, date);
        if(!genTime){
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
        }
        else{
            c.set(Calendar.HOUR, (int)(Math.random()*24));
            c.set(Calendar.MINUTE, (int)(Math.random()*60));
            c.set(Calendar.SECOND, (int)(Math.random()*60));
        }
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    @Autowired BasicMapper basic_mapper;
    @Autowired MainMapper main_mapper;
    @GetMapping("/qna_list")
    public Map<String,Object> getDashBoard(
    @RequestParam @Nullable Integer page , 
    @RequestParam @Nullable String keyword
    ){
        Map<String,Object> resulMap = new LinkedHashMap<String,Object>();
        if(page==null)page=1;
        resulMap.put("keyword", keyword);
        
        int totalPage = main_mapper.selectQuestionByCnt(keyword);
        int totalCount = main_mapper.selectAllQuestionCount(keyword);
        
        PagingVO pager = new PagingVO(totalPage, page, totalCount);
        resulMap.put("qnaList", main_mapper.selectAllQuestion(keyword, (page-1)*10));
        resulMap.put("qnaCnt",pager.getTotalPage() );
        resulMap.put("qnaCount", pager.getTotalCount());
        resulMap.put("page", page);
        resulMap.put("startPage", pager.getStartPage());
        resulMap.put("endPage", pager.getEndPage());
        return resulMap;
    }

    // @GetMapping("/user/generate")
    // public Map<String,Object> getUserGenerate(@RequestParam Integer cnt){
    //     Map<String,Object> resulMap = new LinkedHashMap<String,Object>();
    //     List<ReaderInfoVO> users = new ArrayList<ReaderInfoVO>();
    //     for(int i=0; i<cnt; i++){
    //         ReaderInfoVO user = new ReaderInfoVO();
    //         user.setRd_id(makeId((int)(Math.random()*6+6)));
    //         user.setRd_pwd("4g9CwF3G17sk3kn/7DQTeQ==");
    //         user.setRd_name(makeName());
    //         user.setRd_gen(((int)(Math.random()*3+1)));
    //         user.setRd_birth(makeDateTime(1950, 2010,false));
    //         user.setRd_reg_dt(makeDateTime(2020, 2022,false));
    //         users.add(user);
    //         main_mapper.insertReaderInfo(users);
    //     }
    //     return resulMap;


    // }

    // @GetMapping("/Qna/generate")
    // public Map<String,Object> getQnaGenerate(@RequestParam Integer cnt){
    //     Map<String,Object> resulMap = new LinkedHashMap<String,Object>();
    //     List<Question_infoVO> qna = new ArrayList<Question_infoVO>();
    //     for(int i=0; i<cnt; i++){
    //         Question_infoVO q = new Question_infoVO();
    //         q.setQi_rd_seq((int)(Math.random()*100+30));
    //         q.setQi_text("내용");
    //         q.setQi_title("문의제목"+i);
    //         qna.add(q);
    //         main_mapper.insertQnaInfo(qna);
    //     }

    //     return resulMap;
    // }
}
