let current = 1;
$(function(){
    getQnaList(null);
    makePager();
    $(".search_id").click(function(){
        getQnaList(null);
        makePager();
    })
    $(".total_upload_book_list_item").click(function(){$(".total_upload_book_list_popup").show()})
    $(".books_summary_list_cancel").click(function(){$(".total_upload_book_list_popup").hide()})

    $(".pager_first").click(function(){
        let keyword = $(".keyword").val();
        if(keyword=='') keyword == '%%';
        $.ajax({
            url:"/api/qna_list?keyword="+keyword,
            type:"get",
            success:function(r){
                    getQnaList(1);
                    current = 1;
                    $(".search_pager_area").html("");
                    for(let i = 1; i<=r.endPage; i++){
                        let tag = '';
                        if(i == current) {
                            tag = '<button class="pager current">'+i+'</button>';
                        }
                        else {
                            tag = '<button class="pager">'+i+'</button>';
                        }
                        $(".search_pager_area").append(tag);
                    }
                    $(".pager").click(function(){
                        current = $(this).html();
                        pager($(this).html());
                    })
            }
        })

    })
    $(".pager_end").click(function(){
        let keyword = $(".keyword").val();
        if(keyword=='') keyword == '%%';
        $.ajax({
            url:"/api/qna_list?keyword="+keyword,
            type:"get",
            success:function(r){
                    getQnaList(r.qnaCnt);
                    $(".search_pager_area").html("");
                    current = r.qnaCnt;
                    for(let i = r.qnaCnt-5; i<=r.qnaCnt; i++){
                        let tag = '';
                        if(i == current) {
                            tag = '<button class="pager current">'+i+'</button>';
                        }
                        else {
                            tag = '<button class="pager">'+i+'</button>';
                        }
                        $(".search_pager_area").append(tag);
                    }
                    $(".pager").click(function(){
                        current = $(this).html();
                        pager($(this).html());
                    })
            }
        })

    })
})



function getQnaList(page) {
    let keyword = $(".keyword").val();
    if(keyword=='') keyword = null;

    let url = "/api/qna_list"
    if(page != null && keyword == null) {url += "?page="+page;} 
    if(page != null && keyword != null) {url += "?page="+page+"&"+"keyword="+keyword;} 
    if(keyword != null && page == null) {url += "?keyword="+keyword;} 

    $(".qna_list_item").html("");
    let check = '';

    $.ajax({
        url:url,
        type:"get",
        success:function(r){
            $(".keyword").val(r.keyword);
            for(let i=0; i<r.qnaList.length; i++){
                if(r.qnaList[i].qi_check==0){
                    check = '답변대기'
                }
                if(r.qnaList[i].qi_check==1){
                    check = '답변완료'
                }
                let tag =
                '<tr>'+
                    '<td>'+r.qnaList[i].qi_seq+'</td>'+
                    '<td>'+
                        '<a href="/question/qna_detail?seq='+r.qnaList[i].qi_seq+'">'+r.qnaList[i].qi_title+'</a>'+
                    '</td>'+
                    '<td>'+
                        ''+r.qnaList[i].id+''+
                    '</td>'+
                    '<td>'+check+'</td>'+
                    '<td>'+makeDateString(new Date(r.qnaList[i].qi_reg_dt))+'</td>'+
                '</tr>';
                $(".qna_list_item").append(tag);
            }
        }
    })
}

function makePager(){
    let keyword = $(".keyword").val();
    if(keyword=='') keyword = null;
    let url = "/api/qna_list";
    if(keyword != null) url += "?keyword="+keyword;
    $.ajax({
        url:url,
        type:"get",
        success:function(r){
            $(".search_pager_area").html("");
            for(let i = r.startPage; i<=r.endPage; i++){
                let tag = '<button class="pager">'+i+'</button>';
                $(".search_pager_area").append(tag);
            }
            $(".pager").eq(0).addClass("current");
            $(".pager").click(function(){
                current = $(this).html();
                pager($(this).html());
            })
        }
    })
}

function pager(page){
    let keyword = $(".keyword").val();
    if(keyword=='') keyword == '%%';
    $.ajax({
        url:"/api/qna_list?keyword="+keyword+"&page="+page,
        type:"get",
        success:function(r){
            $(".search_pager_area").html("");
            for(let i = r.startPage; i<=r.endPage; i++){
                let tag = ''
                if(i == current) {
                    tag = '<button class="pager current">'+i+'</button>';
                }
                else {
                    tag = '<button class="pager">'+i+'</button>';
                }
                $(".search_pager_area").append(tag);
            }
            getQnaList(r.page);
            $(".pager").click(function(){
                current = $(this).html();
                pager($(this).html());
            })
        }
    })
}

function makeDateStr(dt) {
    return dt.getFullYear()+"-"+leadingZero(dt.getMonth()+1)+"-"+leadingZero(dt.getDate())
}