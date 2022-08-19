$(function(){
    $(".rd_status_edit").click(function(){
        $(".user_status_edit_popup").show();
        $(".user_status_edit_popup").html("");
        $.ajax({
            url:"/api/user/status?seq="+$(this).attr("data-seq"),
            type:"get",
            success:function(r){
                let tag = 
                    '<button class="user_popup_cancel">&times;</button>'+
                    '<p>유저 아이디</p>'+
                    '<p>'+
                        '<input type="text" value="'+r.list.rd_id+'" disabled>'+
                    '</p>'+
                    '<p>'+
                        '<select class="user_status">'+
                            '<option value="1">정상</option>'+
                            '<option value="2">정지</option>'+
                            '<option value="3">영구정지</option>'+
                            '<option value="4">탈퇴대기</option>'+
                        '</select>'+
                        '</p>'+
                        '<p>사유</p>'+
                        '<textarea class="user_reason" cols="30" rows="10"></textarea>'+
                        '<button class="reset_rd_pwd" data-seq="'+r.list.rd_seq+'">비밀번호 초기화</button>'+
                        '<button class="user_status_edit" data-seq="'+r.list.rd_seq+'">변경</button>';
                        $(".user_status_edit_popup").html(tag);
                        $(".user_status option").eq((r.list.rd_status)-1).prop("selected",true);

                        $(".user_status_edit").click(function(){
                            let data = {
                                rse_rd_seq:$(".user_status_edit").attr("data-seq"),
                                rse_reason:$(".user_reason").val(),
                                rd_seq:$(this).attr("data-seq"),
                                status:$(".user_status option:selected").val()
                            }
                            $.ajax({
                                url:"/api/user_status/edit",
                                type:"put",
                                contentType:"application/json",
                                data:JSON.stringify(data),
                                success:function(r){
                                    alert(r.message);
                                    location.reload();
                                }
                            })
                        });

                        $(".reset_rd_pwd").click(function(){
                            if(!confirm("정말 유저 비밀번호를 초기화 시키겠습니까?"))return;

                            $.ajax({
                                url:"/api/user/pwd_reset?seq="+$(this).attr("data-seq"),
                                type:"patch",
                                success:function(r){
                                    alert(r.message);
                                }
                            })
                        })

                    }
                })
            });
    $(".user_popup_cancel").click(function(){$(".user_status_edit_popup").hide()});

    $(".rd_del").click(function(){
        if(!confirm("정말 해당 유저를 삭제하시겠습니까?")) return;

        $.ajax({
            url:"/api/user/delete?seq="+$(this).attr("data-seq"),
            type:"delete",
            success:function(r){
                alert(r.message);
                location.reload();
            }
        })
    })
})