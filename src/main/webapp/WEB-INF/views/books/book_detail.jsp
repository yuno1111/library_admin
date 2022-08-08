<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/book/book_detail.css">
    <script src="/assets/js/books/book_detail.js"></script>
    <script>
        let book_seq ="${bookList.bi_seq}";
    </script>
    <c:forEach items="${coverList}" var="item">
        <script>
            book_covers.push({seq:'${item.seq}', book_covers:'${item.book_covers}'});
        </script>
    </c:forEach>
    <c:forEach items="${contentList}" var="item" varStatus="stat">
        <textarea id="contentData${stat.count}" hidden>${item.content}</textarea>
        <script>
                book_content_list.push({
                content:$("#contentData${stat.count}").val(), 
                order:"${item.order}"
            });
        </script>
    </c:forEach>
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
                        <c:forEach items="${coverList}" var="item">
                        <div class="book_img" filename="${item.book_covers}"  style="background-image:url('/images/book_cover/${item.book_covers}')">
                            <button onclick="deleteImg('${item.book_covers}', '${item.seq}')">&times;</button>
                        </div>
                    </c:forEach>
                    </div>
                    <p>책 타이틀</p>
                    <input type="text" class="book_title" placeholder="책 제목을 입력하세요" value="${bookList.bi_title}">
                    <p>출판</p>
                    <input type="text" class="publisher_name" placeholder="출판사를 입력하세요" value="${bookList.bi_pb_name}">
                    <p>작가</p>
                    <select class="writer_info">
                        <c:forEach items="${writerList}" var="item">
                            <option value="${item.wri_seq}" <c:if test="${item.wri_seq == bookList.bi_wri_seq}">selected</c:if>>${item.wri_name}</option>
                        </c:forEach>
                    </select>
                    <p>장르</p>
                    <select class="genre_info">
                        <c:forEach items="${genreList}" var="item">
                            <option value="${item.gr_seq}"  <c:if test="${item.gr_seq == bookList.bi_gr_seq}">selected</c:if>>${item.gr_name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="book_header_right">
                    <button class="book_modify">수정하기</button>
                    <p>책 소개</p>
                    <textarea class="book_explain" cols="30" rows="10">${bookList.bi_explain}</textarea>
                </div>
            </section>
        </div>
        <div class="book_content_area">
            <p>책 내용</p>
            <div class="book_content_item">
                <textarea cols="30" rows="10" class="book_content"></textarea>
            </div>
            <button class="book_content_prev" disabled>이전</button>
            <button class="book_content_next">다음</button>
            <p></p>
            <span class="book_page">1</span>
            <span class="sep">/</span>
            <span class="total">${bookList.bi_end_page}</span>
        </div>
    </main>
</body>
</html>