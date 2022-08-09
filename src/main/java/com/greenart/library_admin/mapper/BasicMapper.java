package com.greenart.library_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.greenart.library_admin.data.AdminAccountInfoVO;
import com.greenart.library_admin.data.BookReplyDetailVO;
import com.greenart.library_admin.data.ReaderInfoVO;
import com.greenart.library_admin.data.RecurringPaymentVO;
import com.greenart.library_admin.data.genre_info;
import com.greenart.library_admin.data.writer_info;

@Mapper
public interface BasicMapper {
    public void insertGenre(String name);
    public void insertWriter(String name);
    public void deleteGenre(Integer seq);
    public void deleteWriter(Integer seq);
    public List<genre_info> selectGenre();
    public List<writer_info> selectWriter();

    public AdminAccountInfoVO selectAdminLogin(String id, String pwd);
    public List<AdminAccountInfoVO> selectAllAdminAccount(String keyword,Integer offset);
    public Integer selectAllAdminAccountPageCnt(String keyword);
    public void insertAdminAccount(AdminAccountInfoVO data);
    public void deleteAdminAccount(Integer seq);

    public List<ReaderInfoVO> selectAllUserAccount(String keyword,Integer offset);
    public Integer selectAllUserAccountPageCnt(String keyword);
    public BookReplyDetailVO selectUserCommentBySeq(Integer seq);

    public List<RecurringPaymentVO> selectAllPayment();
    public void insertPayment(RecurringPaymentVO data);
    public void deletePayment(Integer seq);
}
