package com.greenart.library_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.BookSummaryVO;
import com.greenart.library_admin.data.Book_infoVO;
import com.greenart.library_admin.data.RecommendSummaryVO;
import com.greenart.library_admin.data.request.BookContentListVO;

@Mapper
public interface BookMapper {
    public void insertBookInfo(Book_infoVO data);
    public void insertBookCover(List<String> data,Integer seq);
    public void insertBookContent(List<BookContentListVO> data, Integer seq);
    public void insertRecommendBooks(List<Integer> data,String title);

    public List<Book_infoVO> selectAllBooks(String keyword,Integer offset);
    public List<RecommendSummaryVO> selectAllRecommendList(String keyword,Integer offset);
    public List<BookSummaryVO> selectAllRecommendBooks(String title);
    public Integer selectBooksCnt(String keyword);
    public Integer selectAllRecommendListCnt(String keyword);
}
