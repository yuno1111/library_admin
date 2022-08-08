$(function () {
    getBookComments();
    makePager();
    

})

function getBookComments(page) {
    if (page == undefined) page = 1;
    $(".book_comment_area").html("");
    $.ajax({
        url: "/api/book/comment/list?seq=" + bi_seq + "&page=" + page,
        type: "get",
        success: function (r) {
            console.log(r);
            if (r.list.length == 0) {
                let tag =
                    '<div class="book_comment_item">' +
                    '<h1>등록된 댓글 및 평점이 없습니다.</h1>' +
                    '</div>'
                $(".book_comment_area").append(tag);
            }
            for (let i = 0; i < r.list.length; i++) {
                let list = r.list[i];
                let user = (list.rd_nickname == null ? list.rd_id : list.rd_nickname) + '(' + list.rd_id + ')';
                let tag =
                    '<div class="book_comment_item">' +
                    '<div class="comment_content">' +
                    '<p class="comment">' + list.bre_text + '</p>' +
                    '<p class="user">' +
                    '<span class="user">' + user + '</span>' +
                    '<span class="sep">|</span>' +
                    '<span class="reg_dt">' + list.bre_reg_dt + '</span>' +
                    '<span class="sep">|</span>' +
                    '<span class="mod_dt">' + list.bre_mod_dt + '</span>' +
                    '<span class="sep">|</span>' +
                    '<button class="del_comment" data-seq="'+list.bre_seq+'">삭제</button>'+
                    '</p>' +
                    '</div>' +
                    '</div>';
                    $(".book_comment_area").append(tag);
            }
            $(".del_comment").click(function(){
                if(!confirm("댓글을 삭제 하시겠습니까?"))return;
                $.ajax({
                    url:"/api/book/comment/delete?seq="+$(this).attr("data-seq"),
                    type:"delete",
                    success:function(r){
                        alert(r.message);
                        location.reload();
                    }
                })
            })
        }
    })
}

function makePager(){
    $.ajax({
        url:"/api/book/comment/list?seq="+bi_seq,
        type:"get",
        success:function(r){
            $(".book_comment_page_area").html("");
            for(let i = 0; i<r.pageCnt; i++){
                let tag = '<button class="pager">'+(i+1)+'</button>';
                $(".book_comment_page_area").append(tag);
            }

            $(".pager").eq(0).addClass("current");

            $(".pager").click(function(){
                $(".pager").removeClass("current");
                $(this).addClass("current");
                getBookComments($(this).html());
            })
        }
    })
}