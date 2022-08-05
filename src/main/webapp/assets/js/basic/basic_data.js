$(function(){
    $(".gr_add").click(function(){
        if(!confirm("장르 정보를 추가하시겠습니까?")) return;
        $.ajax({
            url:"/api/genre_add?name="+$(".gr_name").val(),
            type:"put",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $(".wri_add").click(function(){
        if(!confirm("작가 정보를 추가하시겠습니까?")) return;
        $.ajax({
            url:"/api/writer_add?name="+$(".wri_name").val(),
            type:"put",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })

    $(".del_genre").click(function(){
        if(!confirm("장르 정보를 삭제하시겠습니까?")) return;
        $.ajax({
            url:"/api/genre_del?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
    $(".del_writer").click(function(){
        if(!confirm("작가 정보를 삭제하시겠습니까?")) return;
        $.ajax({
            url:"/api/writer_del?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})