package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeInfoVO {
    private Integer ni_seq;
    private Integer ni_aai_seq;
    private String ni_text;
    private Date ni_reg_dt;
    private String ni_title;

    private String aai_id;
}
