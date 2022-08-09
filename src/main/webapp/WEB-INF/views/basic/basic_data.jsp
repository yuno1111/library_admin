<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/basic/basic_data.js"></script>
</head>
<body>
    <main>
        <div class="basic_info_wrap">
            <div class="genre_info_item">
                <p>장르 명</p>
                <input type="text" class="gr_name" placeholder="장르 이름 입력">
                <button class="gr_add">등록</button>
                <p>장르 리스트</p>
                <div class="genre_list_area">
                    <c:forEach items="${genreList}" var="item" varStatus="stat">
                        <p>${item.gr_name}</p>
                        <button class="del_genre" data-seq="${item.gr_seq}">삭제</button>
                    </c:forEach>
                </div>
            </div>
            <div class="writer_info_item">
                <p>작가 명</p>
                <input type="text" class="wri_name" placeholder="작가 이름 입력">
                <button class="wri_add">등록</button>
                <p>작가 리스트</p>
                <div class="writer_list_area">
                    <c:forEach items="${writerList}" var="item" varStatus="stat">
                        <p>${item.wri_name}</p>
                        <button class="del_writer" data-seq="${item.wri_seq}">삭제</button>
                    </c:forEach>
                </div>
            </div>
            <div class="payment_info_item">
                <p>구독권 이름</p>
                <input type="text" class="rp_name" placeholder="구독권 이름 입력">
                <p>구독 기간</p>
                <input type="number" class="rp_period">분
                <p>구독권 유형</p>
                <select class="rp_role">
                    <option value="1">일반</option>
                    <option value="2">vip</option>
                    <option value="3">vvip</option>
                </select>
                <button class="rp_add">등록</button>
                <p>구독권 리스트</p>
                <div class="rp_list_area">
                    <c:forEach items="${paymentList}" var="item" varStatus="stat">
                        <p>${item.rp_name}</p>
                        <p>${item.rp_period}분</p>
                        <c:if test="${item.rp_role == 1}">일반</c:if>
                        <c:if test="${item.rp_role == 2}">vip</c:if>
                        <c:if test="${item.rp_role == 3}">vvip</c:if>
                        <button class="del_payment" data-seq="${item.rp_seq}">삭제</button>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</body>
</html>