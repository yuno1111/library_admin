<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/book/recommend_list.css">
    <script src="/assets/js/books/recommend_list.js"></script>
</head>
<body>
    <main>
        <a href="/books/recommend/list/add">도서 추천 리스트 추가하기</a>
        <div class="search_box">
            <form action="/books/recommend/list" method="get">
                <input type="text" name="keyword" placeholder="추천리스트 제목 검색" value="${keyword}">
                <button type="submit">검색</button>
            </form>
        </div>
        <section class="books_recommend_list">
            <c:forEach items="${list}" var="item">
                <a href="/books/list/detail?title=${item.ar_title}" class="books_summary_item">
                    <div class="books_summary_detail">
                        <div class="books_summary_img"
                            style="background-image: url('/images/book_cover/${item.bc_img_file}');">
                        </div>
                        <div class="books_summary_txt">
                            <p class="books_list_name">${item.ar_title}</p>
                        </a>
                            <button class="books_list_del" data-name="${item.ar_title}">삭제</button>
                        </div>
                    </div>
            </c:forEach>
        </section>
        <div class="pager_area">
            <c:forEach begin="1" end="${pageCnt}" var="i">
                <a href="/books/recommend/list/?page=${i}&keyword=${keyword}" class="pager">${i}</a>
            </c:forEach>
        </div>
    </main>
</body>
</html>