<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/qna/notice_list.js"></script>
</head>
<body>
    <main>
        <a href="/notice/form">공지 추가 페이지</a>
        <div class="search_box">
            <form action="/notice/list" method="get">
                <input type="text" name="keyword" placeholder="제목 검색" value="${keyword}">
                <button type="submit">검색</button>
            </form>
        </div>
        <div class="notice_list_wrap">
            <div class="notice_list_area">
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td>제목</td>
                            <td>작성 아이디</td>
                            <td>작성일</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="item" varStatus="stat">
                            <tr>
                                <td>${stat.count}</td>
                                <td>
                                    <a href="/notice/form?seq=${item.ni_seq}">${item.ni_title}</a>
                                </td>
                                <td>${item.aai_id}</td>
                                <td>
                                    <fmt:formatDate value="${item.ni_reg_dt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <button class="del_notice" data-seq="${item.ni_seq}">삭제</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="pager_area">
            <c:forEach begin="1" end="${pageCnt}" var="i">
                <a href="/notice/list/?page=${i}&keyword=${keyword}" class="pager">${i}</a>
            </c:forEach>
        </div>
    </main>
</body>
</html>