package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.data.Book_infoVO;
import com.greenart.library_admin.data.request.BookRequestVO;
import com.greenart.library_admin.data.request.RecommendBooksList;
import com.greenart.library_admin.mapper.BookMapper;

@RestController
@RequestMapping("/api")
public class BookAPIController {
    @Autowired BookMapper book_mapper;
    @PutMapping("/book_add")
    @Transactional
    public Map<String,Object> putAddbook(@RequestBody BookRequestVO datas){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        Book_infoVO book = datas.getBook_info();
        book_mapper.insertBookInfo(book);
        book_mapper.insertBookCover(datas.getBook_covers(), book.getBi_seq());
        book_mapper.insertBookContent(datas.getBook_content_list(), book.getBi_seq());

        resultMap.put("status", true);
        resultMap.put("message", "책 정보를 추가하였습니다.");
        return resultMap;
    }

    @GetMapping("/recommend_books")
    public Map<String,Object> getRecommendBooks(
        @RequestParam @Nullable Integer page , 
        @RequestParam @Nullable String keyword
    ){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        if(page==null)page=1;

        resultMap.put("pageCount", book_mapper.selectBooksCnt(keyword));
        resultMap.put("booksList", book_mapper.selectAllBooks(keyword, (page-1)*10));
        return resultMap;
    }

    @PutMapping("/recommend_books/add")
    public Map<String,Object> putRecommendList(@RequestBody RecommendBooksList datas){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.insertRecommendBooks(datas.getAr_bi_seq(), datas.getAr_title());
        resultMap.put("status", true);
        resultMap.put("message", "추천 도서 리스트 정보를 추가하였습니다.");
        return resultMap;
    }
}
