package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class Question_infoVO {
    private Integer qi_seq;
    private Integer qi_rd_seq;
    private String qi_text;
    private Integer qi_check;
    private String qi_title;
    private Date qi_reg_dt;

    private String id;
}
