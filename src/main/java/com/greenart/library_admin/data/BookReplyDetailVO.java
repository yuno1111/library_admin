package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class BookReplyDetailVO {
    private Integer bre_seq;
    private Integer bre_bi_seq;
    private Integer bre_rd_seq;
    private String bre_text;
    private Date bre_reg_dt;
    private Date bre_mod_dt;

    private Integer bi_seq;
    private String bi_title;
    private String bc_img_file;
    private String rd_id;
}
