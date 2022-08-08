package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class BookCommentVO {
    private Integer bre_seq;
    private Integer bre_bi_seq;
    private Integer bre_rd_seq;
    private String bre_text;
    private Date bre_reg_dt;
    private Date bre_mod_dt;
    private Integer rd_seq;
    private String rd_id;
    private String rd_nickname;
}
