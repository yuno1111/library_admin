package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class ReaderStatusEditVO {
    private Integer rse_seq;
    private Integer rse_rd_seq;
    private String rse_reason;
    private Date rse_reg_dt;

    private Integer rd_seq;
    private Integer status;
}
