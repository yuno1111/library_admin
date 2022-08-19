package com.greenart.library_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.BookCommentVO;
import com.greenart.library_admin.data.BookSummaryVO;
import com.greenart.library_admin.data.Book_infoVO;
import com.greenart.library_admin.data.RecommendSummaryVO;
import com.greenart.library_admin.data.request.BookContentListVO;
import com.greenart.library_admin.data.request.BookCoverListVO;

@Mapper
public interface BookMapper {
    public void insertBookInfo(Book_infoVO data);
    public void insertBookCover(List<BookCoverListVO> data,Integer seq);
    public void insertBookContent(List<BookContentListVO> data, Integer seq);
    public void insertBookTextFile(String text, Integer seq);
    public void insertRecommendBooks(List<Integer> data,String title);

    public void patchBookInfo(Book_infoVO data);
    public void deleteBookCoverByBi_seq(Integer seq);
    public void deleteBookContentsByBi_seq(Integer seq);

    public List<Book_infoVO> selectAllBooks(String keyword,Integer offset);
    public List<RecommendSummaryVO> selectAllRecommendList(String keyword,Integer offset);
    public List<BookSummaryVO> selectAllRecommendBooks(String title);
    public Integer selectBooksCnt(String keyword);
    public Integer selectAllRecommendListCnt(String keyword);
    public void deleteRecommendList(String title);

    public Book_infoVO selectBooksBySeq(Integer seq);
    public Book_infoVO selectSummaryBooksBySeq(Integer seq);
    public List<BookCoverListVO> selectBooksCoverBySeq(Integer seq);
    public List<BookContentListVO> selectBookContentBySeq(Integer seq);
    public String selectBookTextFileBySeq(Integer seq);
    public void deleteBookCovers(Integer seq);
    public void deleteBook(Integer seq);
    public void insertBookCovers(BookCoverListVO data);

    public List<BookCommentVO> selectBookCommentInfo(Integer seq, Integer offset);
    public Integer selectBookCommentPageCnt(Integer seq);
    public void deleteBookCommentBySeq(Integer seq);
}
