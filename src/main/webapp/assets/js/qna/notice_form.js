$(function(){
    $(".ni_add").click(function(){
        if(!confirm("공지를 등록하시겠습니까?")) return;

        let data = {
            ni_title:$(".ni_title").val(),
            ni_aai_seq:$(".ni_aai_seq").val(),
            ni_text:$(".ni_text").val()
        }

        $.ajax({
            url:"/api/notice/add",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/notice/list";
            }
        })
    })

    $(".ni_mod").click(function(){
        if(!confirm("공지를 수정하시겠습니까?")) return;

        let data = {
            ni_seq:$(this).attr("data-seq"),
            ni_title:$(".ni_mod_title").val(),
            ni_text:$(".ni_mod_text").val()
        }

        $.ajax({
            url:"/api/notice/modify",
            type:"patch",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/notice/list";
            }
        })
    })
})