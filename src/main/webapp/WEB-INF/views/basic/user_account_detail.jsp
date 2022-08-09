<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/basic/user_account_detail.css">
    <script>
        $(function(){
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
        })
    </script>
</head>
<body>
    <main>
        <div class="user_comment_wrap">
            <div class="user_comment_book_area">
                <a href="/books/book/summary?seq=${list.bi_seq}">
                    <div class="book_cover_area" style="background-image: url('/images/book_cover/${list.bc_img_file}');"></div>
                    <p>${list.bi_title}</p>
                </a>
            </div>
            <div class="user_comment_item">
                <div class="comment_content">
                <p class="comment">${list.bre_text}</p>
                <p class="user">
                <span class="user">${list.rd_id}</span>
                <span class="sep">|</span>
                <span class="reg_dt">
                    <fmt:formatDate value="${list.bre_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
                <span class="sep">|</span>
                <span class="mod_dt">
                    <fmt:formatDate value="${list.bre_mod_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
                <span class="sep">|</span>
                <button class="del_comment" data-seq="${list.bre_seq}">삭제</button>
                </p>
                </div>
            </div>
        </div>
    </main>
</body>
</html>