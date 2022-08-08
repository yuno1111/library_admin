package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class ReaderInfoVO {
    private Integer rd_seq;
    private Integer rd_rp_seq;
    private String rd_id;
    private String rd_pwd;
    private String rd_name;
    private String rd_phone;
    private Integer rd_gen;
    private Integer rd_role;
    private Integer rd_status;
    private String rd_nickname;
    private Date rd_reg_dt;
    private Date rd_mod_dt;
    private String rd_profile;
}
