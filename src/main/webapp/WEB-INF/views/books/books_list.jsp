<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/books/book_list.js"></script>
</head>
<body>
    <main>
        <div class="books_list_wrap">
            <a href="/books/book_add">책 추가하기</a>
            <div class="search_box">
                <form action="/books/book/list" method="get">
                    <input type="text" name="keyword" placeholder="도서 제목 검색" value="${keyword}">
                    <button type="submit">검색</button>
                </form>
            </div>
            <section>
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td></td>
                            <td>도서 제목</td>
                            <td>장르</td>
                            <td>작가</td>
                            <td>출판사</td>
                            <td>출간일</td>
                            <td>유저 구독수</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="item" varStatus="stat">
                            <tr>
                                <td>${stat.count}</td>
                            <td style="background-image: url('/images/book_cover/${item.bc_img_file}'); width: 60px; height: 60px; background-position: center;
                            background-repeat: no-repeat;">
                            </td>
                            <td>
                                <a href="/books/book/summary?seq=${item.bi_seq}">${item.bi_title}</a>
                            </td>
                            <td>
                                ${item.gr_name}
                            </td>
                            <td>
                                ${item.wri_name}
                            </td>
                            <td>
                                ${item.bi_pb_name}
                            </td>
                            <td>
                                <fmt:formatDate value="${item.bi_reg_dt}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                나중에추가
                            </td>
                            <td>
                                <button data-seq="${item.bi_seq}" data-name="${item.bi_title}" class="del_book">삭제</button>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
            <div class="pager_area">
                <c:forEach begin="1" end="${pageCnt}" var="i">
                    <a href="/books/book/list/?page=${i}&keyword=${keyword}" class="pager">${i}</a>
                </c:forEach>
            </div>
        </div>
    </main>
</body>
</html>