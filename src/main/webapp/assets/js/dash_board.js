$(function(){
    getQnaList(null);
    makePager();
    $(".search_id").click(function(){
        getQnaList(null);
        makePager();
    })
    $(".total_upload_book_list_item").click(function(){$(".total_upload_book_list_popup").show()})
    $(".books_summary_list_cancel").click(function(){$(".total_upload_book_list_popup").hide()})
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
                    '<td>'+(i+1)+'</td>'+
                    '<td>'+
                        '<a href="/question/qna_detail?seq='+r.qnaList[i].qi_seq+'">'+r.qnaList[i].qi_title+'</a>'+
                    '</td>'+
                    '<td>'+
                        ''+r.qnaList[i].id+''+
                    '</td>'+
                    '<td>'+check+'</td>'+
                    '<td>'+makeDateStr(new Date(r.qnaList[i].qi_reg_dt))+'</td>'+
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
            for(let i = 0; i<r.qnaCnt; i++){
                let tag = '<button class="pager">'+(i+1)+'</button>';
                $(".search_pager_area").append(tag);
            }

            $(".pager").eq(0).addClass("current");

            $(".pager").click(function(){
                $(".pager").removeClass("current");
                $(this).addClass("current");
                getQnaList($(this).html());
            })
        }
    })
}

function makeDateStr(dt) {
    return dt.getFullYear()+"-"+leadingZero(dt.getMonth()+1)+"-"+leadingZero(dt.getDate())
}