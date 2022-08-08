let pwd_dub_chk = false;
$(function(){
    $(".admin_account_add").click(function(){$(".admin_account_add_popup").show()})
    $(".cancel_add_popup").click(function(){$(".admin_account_add_popup").hide()})

    $(".add_admin_account").click(function(){
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
        if(!pwd_dub_chk) {
            $(".aai_pwd_chk+.error").css("display", "block");
        }
        else {
            $(".aai_pwd_chk+.error").css("display", "");
        }

        let data = {
            aai_id:$(".aai_id").val(),
            aai_pwd:$(".aai_pwd").val(),
            aai_role:$(".aai_role option:selected").val(),
        }
        $.ajax({
            url:"/api/admin/join",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                $(".admin_account_add_popup").hide();
                location.reload();
            }
        })
    })

    $(".aai_pwd_chk").keyup(function(){
        if($(".aai_pwd_chk").val() != $(".aai_pwd").val()){
            $(".aai_pwd_chk+.error").css("display", "block");
            pwd_dub_chk = false;
        }
        else if($(".aai_pwd_chk").val() == $(".aai_pwd").val()){
            $(".aai_pwd_chk+.error").css("display", "");
            pwd_dub_chk = true;
        }
    })

    $(".del_admin_account").click(function(){
        if(!confirm("관리자 계정을 삭제하시겠습니까?")) return;
        $.ajax({
            url:"/api/admin/delete?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message)
                location.reload();
            }
        })
    })
})