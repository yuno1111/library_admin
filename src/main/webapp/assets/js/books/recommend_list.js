$(function(){
    $(".books_list_del").click(function(){
        if(!confirm("추천 도서 목록을 삭제하시겠습니까?")) return;
        $.ajax({
            url:"/api/recommend_books/delete?title="+$(this).attr("data-name"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})