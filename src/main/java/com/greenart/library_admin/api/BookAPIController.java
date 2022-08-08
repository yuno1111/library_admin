package com.greenart.library_admin.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.library_admin.data.Book_infoVO;
import com.greenart.library_admin.data.request.BookCoverListVO;
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
        datas.getBook_covers().get(0).setIs_poster(1);
        book_mapper.insertBookCover(datas.getBook_covers(),book.getBi_seq());
        book_mapper.insertBookContent(datas.getBook_content_list(), book.getBi_seq());

        resultMap.put("status", true);
        resultMap.put("message", "책 정보를 추가하였습니다.");
        return resultMap;
    }
    @PatchMapping("/book_modify")
    @Transactional
    public Map<String,Object> patchBook(@RequestBody BookRequestVO datas){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        Book_infoVO book = datas.getBook_info();
        book_mapper.patchBookInfo(book);
        book_mapper.deleteBookContentsByBi_seq(book.getBi_seq());
        book_mapper.deleteBookCoverByBi_seq(book.getBi_seq());
        datas.getBook_covers().get(0).setIs_poster(1);
        book_mapper.insertBookCover(datas.getBook_covers(),book.getBi_seq());
        book_mapper.insertBookContent(datas.getBook_content_list(), book.getBi_seq());
        resultMap.put("status", true);
        resultMap.put("message", "책 정보를 수정하였습니다.");
        return resultMap;
    }
    @PutMapping("/book_cover/add")
    public Map<String,Object> addBookCovers(@RequestBody BookCoverListVO data) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.insertBookCovers(data);
        resultMap.put("seq", data.getBc_seq());
        return resultMap;
    }
    @GetMapping("/book/comment/list")
    public Map<String, Object> selectAlbumCommentList(
        @RequestParam Integer seq, @RequestParam @Nullable Integer page) {
        if(page == null) page = 1;
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Integer pageCnt = book_mapper.selectBookCommentPageCnt(seq);
        if(pageCnt == 0) pageCnt=1;
        resultMap.put("list",book_mapper.selectBookCommentInfo(seq, (page-1)*10));
        resultMap.put("pageCnt", pageCnt);
        return resultMap;
    }
    @DeleteMapping("/book/comment/delete")
    public Map<String,Object> delBookComment(@RequestParam Integer seq) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.deleteBookCommentBySeq(seq);
        resultMap.put("status", true);
        resultMap.put("message", "댓글을 삭제하였습니다.");
        return resultMap;
    }
    @DeleteMapping("/book_delete")
    public Map<String,Object> delBook(@RequestParam Integer seq) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.deleteBook(seq);
        resultMap.put("status", true);
        resultMap.put("message", "책을 삭제하였습니다.");
        return resultMap;
    }
    @DeleteMapping("/book_cover/delete")
    public Map<String,Object> delBookCovers(@RequestParam Integer seq) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.deleteBookCovers(seq);
        resultMap.put("status", true);
        resultMap.put("message", "책 커버를 삭제하였습니다.");
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
        resultMap.put("message", "추천 도서 목록을 추가하였습니다.");
        return resultMap;
    }
    @DeleteMapping("/recommend_books/delete")
    public Map<String,Object> deleteRecommendList(@RequestParam String title){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        book_mapper.deleteRecommendList(title);
        resultMap.put("status", true);
        resultMap.put("message", "추천 도서 목록을 삭제하였습니다.");
        return resultMap;
    }
}
