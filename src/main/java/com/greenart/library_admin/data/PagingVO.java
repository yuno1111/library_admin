package com.greenart.library_admin.data;

import lombok.Data;

@Data
public class PagingVO {
    private final Integer pageSize = 5;
    private Integer totalPage;
    private Integer page;
    private Integer totalCount;
    private Integer startPage;
    private Integer endPage;

    public PagingVO(Integer totalPage,Integer page, Integer totalCount){
        this.totalPage = totalPage;
        this.page = page;
        this.totalCount = totalCount;

        this.startPage = (page-(pageSize+1))+1;
        if(this.startPage <= 9){
            this.startPage = 1;
        }
        this.endPage = pageSize*2;
        if(page>=10){
            this.startPage = (page-pageSize);
            this.endPage = page + pageSize;
        }

        if(totalPage < page) {
        page = totalPage;
        }

        if ( this.endPage > totalPage) {
        this.endPage = totalPage;
        }
    }

    


}
