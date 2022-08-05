package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class Book_infoVO {
    private Integer bi_seq;
    private Integer bi_wri_seq;
    private Integer bi_gr_seq;
    private String bi_title;
    private String bi_pb_name;
    private Date bi_reg_dt;
    private String bi_explain;
    private Integer bi_end_page;

    private String gr_name;
    private String wri_name;
    private String bc_img_file;
}
