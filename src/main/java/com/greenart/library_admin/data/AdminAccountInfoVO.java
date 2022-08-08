package com.greenart.library_admin.data;

import lombok.Data;

@Data
public class AdminAccountInfoVO {
    private Integer aai_seq;
    private String aai_id;
    private String aai_pwd;
    private Integer aai_role;
}
