<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/dash_board.js"></script>
</head>
<body>
    <main>
        <div class="total_list_wrap">
            <section class="total_list_area"></section>
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
            <div class="search_pager_area">
            </div>
        </div>
    </main>
</body>
</html>