$(function(){
    $(".add_answer").click(function(){
        if(!confirm("답변을 등록하시겠습니까?")) return;
        
        let data = {
            an_qi_seq:$(this).attr("data-seq"),
            an_aai_seq:$(".an_aai_seq").val(),
            an_text:$(".qna_answer").val()
        }

        $.ajax({
            url:"/api/answer",
            type:"put",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/dash_board";
            }
        })
    })
    $(".mod_answer").click(function(){
        if(!confirm("답변을 수정하시겠습니까?")) return;
        
        let data = {
            an_seq:$(this).attr("data-seq"),
            an_text:$(".mod_qna_answer").val()
        }

        $.ajax({
            url:"/api/answer",
            type:"patch",
            contentType:"application/json",
            data:JSON.stringify(data),
            success:function(r){
                alert(r.message);
                location.href="/dash_board";
            }
        })
    })
})