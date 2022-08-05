package com.greenart.library_admin.data.request;

import java.util.List;

import com.greenart.library_admin.data.Book_infoVO;

import lombok.Data;

@Data
public class BookRequestVO {
    private Book_infoVO book_info;
    private List<String> book_covers;
    private List<BookContentListVO> book_content_list;
}
