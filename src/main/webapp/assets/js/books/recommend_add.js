let books_seq = new Array();
$(function(){
    $(".add_recommend_book").click(function(){
        $(".book_list_popup").show();
        getBooksList('','');
    })
    $(".cancel_book_popup").click(function(){
        $(".book_list_popup").hide();
    })
    $(".book_search").click(function(){
        let keyword = $(".book_title_search").val();
        getBooksList(keyword,1);
    })

    $(".add_recommend_list").click(function(){
        if(!confirm("추천 도서 리스트를 추가하시겠습니까?"))return;
            let data = {
                ar_title:$(".recommend_title").val(),
                ar_bi_seq:books_seq
            }
            $.ajax({
                url:"/api/recommend_books/add",
                type:"put",
                contentType:"application/json",
                data:JSON.stringify(data),
                success:function(r){
                    alert(r.message);
                    location.href="/books/recommend/list";
                }
            })
    })

})



function getBooksList(keyword,page) {
    $.ajax({
        url:"/api/recommend_books?keyword="+keyword+"&page="+page,
        type:"get",
        success:function(r){
            $(".add_book_popup tbody").html("");
            for(let i=0; i<r.booksList.length; i++){
                let tag = 
                    '<tr>'+
                        '<td>'+
                            '<img width="60" src="/images/book_cover/'+r.booksList[i].bc_img_file+'">'+
                        '</td>'+
                        '<td>'+r.booksList[i].bi_title+'</td>'+
                        '<td>'+r.booksList[i].wri_name+'</td>'+
                        '<td>'+
                            '<button class="book_select" data-seq="'+r.booksList[i].bi_seq+'" data-name="'+r.booksList[i].bi_title+'"'+
                            'data-file="'+r.booksList[i].bc_img_file+'">선택</button>'+ 
                        '</td>'+
                    '</tr>';
                $(".add_book_popup tbody").append(tag);
            }
            $(".book_page_area").html("");
            for(let i=0; i<r.pageCount; i++) {
                let tag = '<button class="book_pager">'+(i+1)+'</button>'
                $(".book_page_area").append(tag);
            }
            $(".book_pager").click(function(){
                let page = $(this).html();
                getBooksList(keyword,page);
            })
            $(".book_select").click(function(){
                let tag = 
                '<tr>'+
                    '<td>'+
                        '<img width="60" src="/images/book_cover/'+$(this).attr("data-file")+'">'+
                    '</td>'+
                    '<td>'+$(this).attr("data-name")+'</td>'+
                '</tr>';
                books_seq.push(Number($(this).attr("data-seq")));
                alert("도서를 추가하였습니다");
                $(".add_book_list tbody").append(tag);
                if(!$("#recommend_add_img_area").html()==""){
                    $("#recommend_add_img_area").html("");
                    document.getElementById('recommend_add_img_area').style.backgroundImage="url('/images/book_cover/"+$(this).attr("data-file")+"')";
                }
            })
        }
    })
}