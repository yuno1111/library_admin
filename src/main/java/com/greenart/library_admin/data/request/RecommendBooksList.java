package com.greenart.library_admin.data.request;

import java.util.List;

import lombok.Data;

@Data
public class RecommendBooksList {
    private List<Integer> ar_bi_seq;
    private String ar_title;
}
