$(function(){
    $(".del_book").click(function(){
        title = $(this).attr("data-name");
        if(!confirm(title+" (을/를) 삭제하시겠습니까?")) return;

        $.ajax({
            url:"/api/book_delete?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})