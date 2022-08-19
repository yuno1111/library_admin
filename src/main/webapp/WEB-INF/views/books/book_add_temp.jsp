<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/books/book_add_temp.js"></script>
</head>
<body>
    <main>
        <div class="book_add_header_area">
            <section class="book_add_header_item">
                <div class="book_header_left">
                    <p>책 표지 등록</p>
                    <form class="book_cover_form">
                        <input type="file" name="file" class="book_cover_add">
                    </form>
                    <div class="book_cover_area">

                    </div>
                    <p>책 타이틀</p>
                    <input type="text" class="book_title" placeholder="책 제목을 입력하세요">
                    <p>출판</p>
                    <input type="text" class="publisher_name" placeholder="출판사를 입력하세요">
                    <p>작가</p>
                    <select class="writer_info">
                        <c:forEach items="${writerList}" var="item">
                            <option value="${item.wri_seq}">${item.wri_name}</option>
                        </c:forEach>
                    </select>
                    <p>장르</p>
                    <select class="genre_info">
                        <c:forEach items="${genreList}" var="item">
                            <option value="${item.gr_seq}">${item.gr_name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="book_header_right">
                    <button class="book_add">업로드</button>
                    <p>책 소개</p>
                    <textarea class="book_explain" cols="30" rows="10"></textarea>
                </div>
            </section>
        </div>
        <div class="book_content_area">
            <p>책 내용</p>
            <div class="book_content_item">
                <textarea cols="30" rows="10" class="book_content"></textarea>
            </div>
        </div>
    </main>
</body>
</html>