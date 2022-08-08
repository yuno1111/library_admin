<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/book/book_summary.css">
    <script>
        let bi_seq = ${list.bi_seq};
    </script>
    <script src="/assets/js/books/book_summary.js"></script>
</head>
<body>
    <main>
        <div class="book_simple_list_wrap">
            <div class="book_simple_list_left_area">
                <div class="swiper main_banner">
                    <div class="swiper-wrapper">
                        <c:forEach items="${coverList}" var="item">
                            <div class="swiper-slide">
                                <div class="book_simple_img_area" style="background-image: url('/images/book_cover/${item.book_covers}');"></div>
                            </div>
                                </c:forEach>
                            </div>
                            <div class="swiper-button-next"></div>
                            <div class="swiper-button-prev"></div>
                    </div>
                </div>
                <div class="book_simple_list_right_area">
                    <p>제목</p>
                    <p>${list.bi_title}</p>
                    <p>저자</p>
                    <p>${list.wri_name}</p>
                    <p>장르</p>
                    <p>${list.gr_name}</p>
                    <p>출판사</p>
                    <p>${list.bi_pb_name}</p>
                    <p>출간일</p>
                    <p>
                        <fmt:formatDate value="${list.bi_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </p>
                    <p>유저 구독수</p>
                    <p>추가예정</p>
                    <p>책 소개</p>
                    <textarea cols="30" rows="10" disabled>${list.bi_explain}</textarea>
                </div>
            </div>
        <a href="/books/book/detail?seq=${list.bi_seq}">수정하기</a>
        <div class="book_comment_area">

        </div>
        <div class="book_comment_page_area">

        </div>
    </main>
    <script>
        const swiper = new Swiper('.swiper', {
            loop: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
            }
        });
    </script>
</body>

</html>