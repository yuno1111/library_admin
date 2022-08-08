package com.greenart.library_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.BookSummaryVO;
import com.greenart.library_admin.data.Question_infoVO;

@Mapper
public interface MainMapper {
    public List<Question_infoVO> selectAllQuestion(String keyword, Integer offset);
    public Integer selectQuestionByCnt(String keyword);

    public Integer selectAllUserCnt();
    public Integer selectAllBooksCnt();
    
    public List<BookSummaryVO> selectNewbooks();
}
