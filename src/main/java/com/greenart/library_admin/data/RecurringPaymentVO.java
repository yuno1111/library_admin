package com.greenart.library_admin.data;

import java.util.Date;

import lombok.Data;

@Data
public class RecurringPaymentVO {
    private Integer rp_seq;
    private String rp_name;
    private Date rp_end_dt;
    private Integer rp_period;
    private Integer rp_role;
}
