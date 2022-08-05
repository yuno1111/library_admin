package com.greenart.library_admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.Answer_infoVO;
import com.greenart.library_admin.data.Question_infoVO;

@Mapper
public interface QnaMapper {
    public Question_infoVO selectQnaBySeq(Integer seq);
    public Answer_infoVO selectAnswerBySeq(Integer seq);

    public void insertAnswerInfo(Answer_infoVO data);
    public void updateAnserInfo(Answer_infoVO data);
    public void updateQuestionStatus(Integer seq);
}
