package com.greenart.library_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.Answer_infoVO;
import com.greenart.library_admin.data.NoticeInfoVO;
import com.greenart.library_admin.data.Question_infoVO;

@Mapper
public interface QnaMapper {
    // QNA AREA
    public Question_infoVO selectQnaBySeq(Integer seq);
    public Answer_infoVO selectAnswerBySeq(Integer seq);

    public void insertAnswerInfo(Answer_infoVO data);
    public void updateAnserInfo(Answer_infoVO data);
    public void updateQuestionStatus(Integer seq);
    // 

    // Notice AREA
    public void insertNoticeInfo(NoticeInfoVO data);
    public void updateNoticeInfo(NoticeInfoVO data);
    public void deleteNoticeInfo(Integer seq);

    public List<NoticeInfoVO> selectAllNoticeInfo(String keyword, Integer offset);
    public Integer selectAllNoticePageCnt(String keyword);
    public NoticeInfoVO selectNoticeInfoBySeq(Integer seq);

    
    // 
}
