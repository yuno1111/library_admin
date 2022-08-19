package com.greenart.library_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenart.library_admin.mapper.BasicMapper;
import com.greenart.library_admin.mapper.BookMapper;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired BasicMapper basic_mapper;
    @Autowired BookMapper book_mapper;
    @GetMapping("/book_add")
    public String getBookAdd(Model model){
        model.addAttribute("genreList", basic_mapper.selectGenre());
        model.addAttribute("writerList", basic_mapper.selectWriter());
        return "/books/book_add";
    }
    @GetMapping("/book_add/temp")
    public String getBookAddTemp(Model model){
        model.addAttribute("genreList", basic_mapper.selectGenre());
        model.addAttribute("writerList", basic_mapper.selectWriter());
        return "/books/book_add_temp";
    }
    @GetMapping("/book_detail/temp")
    public String getBookDetailTemp(Model model,@RequestParam Integer seq){
        model.addAttribute("seq", seq);
        return "/books/book_detail_temp";
    }
    @GetMapping("/book/summary")
    public String getBookSummary(Model model,@RequestParam Integer seq){
        model.addAttribute("list", book_mapper.selectSummaryBooksBySeq(seq));
        model.addAttribute("coverList", book_mapper.selectBooksCoverBySeq(seq));
        return "/books/book_summary";
    }

    @GetMapping("/book/detail")
    public String getBookDetail(Model model,@RequestParam Integer seq){
        model.addAttribute("bookList", book_mapper.selectBooksBySeq(seq));
        model.addAttribute("coverList", book_mapper.selectBooksCoverBySeq(seq));
        model.addAttribute("contentList", book_mapper.selectBookContentBySeq(seq));
        model.addAttribute("genreList", basic_mapper.selectGenre());
        model.addAttribute("writerList", basic_mapper.selectWriter());
        return "/books/book_detail";
    }
    @GetMapping("/book/list")
    public String getBookList(Model model,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer page
    ){
        if(page==null) page=1;
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", book_mapper.selectAllBooks(keyword,(page-1)*10));
        model.addAttribute("pageCnt", book_mapper.selectBooksCnt(keyword));
        return "/books/books_list";
    }

    @GetMapping("/recommend/list")
    public String getBooksRecommend(Model model,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer page
    ){
        if(page==null)page=1;
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", book_mapper.selectAllRecommendList(keyword,(page-1)*10));
        model.addAttribute("pageCnt", book_mapper.selectAllRecommendListCnt(keyword));
        return "/books/recommend_list";
    }

    @GetMapping("/list/detail")
    public String getListDetail(Model model,@RequestParam String title){
        model.addAttribute("list", book_mapper.selectAllRecommendBooks(title));
        return "/books/list_detail";
    }

    @GetMapping("recommend/list/add")
    public String getBooksRecommendAdd(){
        return "/books/recommend_add";
    }
}
