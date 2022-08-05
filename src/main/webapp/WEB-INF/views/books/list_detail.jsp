<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/book/list_detail.css">
</head>
<body>
    <main>
        <section class="books_summary_list">
            <c:forEach items="${list}" var="item">
                <a href="/books/book/detail?seq=${item.bi_seq}" class="books_summary_item">
                    <div class="books_summary_detail">
                        <div class="books_summary_img"
                            style="background-image: url('/images/book_cover/${item.bc_img_file}');">
                        </div>
                        <div class="books_summary_txt">
                            <p class="books_title_name">${item.bi_title}</p>
                            <p class="books_writer_name">${item.wri_name}</p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </section>
    </main>
</body>
</html>