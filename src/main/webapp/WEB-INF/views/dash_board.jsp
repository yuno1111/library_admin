<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/dash_board.js"></script>
    <link rel="stylesheet" href="/assets/css/dash_board.css">
</head>
<body>
    <main>
        <div class="total_list_wrap">
            <section class="total_list_area">
                <div class="total_join_user_item">
                    <p>가입 회원 수</p>
                    <p>${userCnt}</p>
                </div>
                <div class="total_upload_book_list_item">
                    <p>등록 도서 수</p>
                    <p>${bookCnt}</p>
                </div>
            </section>
            <div class="total_upload_book_list_popup" hidden>
                <div class="book_summary_list_wrap">
                <button class="books_summary_list_cancel">&times;</button>
                <h1>신규 도서</h1>
                <section class="books_summary_list">
                    <c:forEach items="${newBookList}" var="item">
                        <a href="/books/book/summary?seq=${item.bi_seq}" class="books_summary_item">
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
                </div>
            </div>
        </div>
        <div class="qna_list_wrap">
            <div class="search_box">
                    <input type="text" class="keyword" placeholder="문의 제목 검색" value="${keyword}">
                    <button class="search_id">검색</button>
            </div>
            <table class="qna_list_area">
                <thead>
                    <tr>
                        <td>번호</td>
                        <td>문의제목</td>
                        <td>유저아이디</td>
                        <td>답변여부</td>
                        <td>등록일</td>
                    </tr>
                </thead>
                <tbody class="qna_list_item">
                    
                </tbody>
            </table>
            <button class="pager_first">처음</button>
            <div class="search_pager_area">
            </div>
            <button class="pager_end">끝</button>
        </div>
    </main>
</body>
</html>