$(function(){
    $(".login").click(function(){

        if(isEmpty($(".aai_id").val())) {
            $(".aai_id+.error").css("display", "block");
        }
        else {
            $(".aai_id+.error").css("display", "");
        }
        if(isEmpty($(".aai_pwd").val())) {
            $(".aai_pwd+.error").css("display", "block");
        }
        else {
            $(".aai_pwd+.error").css("display", "");
        }



        let data = {
            aai_id:$(".aai_id").val(),
            aai_pwd:$(".aai_pwd").val()
        }

        $.ajax({
            url:"/api/login",
            type:"post",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                if(r.status){
                    location.href="/dash_board";
                }
            }
        })
    })
})