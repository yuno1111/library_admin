<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
    <main>
        <div class="user_account_list_wrap">
            <div class="search_box">
                <form action="/user/account/list" method="get">
                    <input type="text" name="keyword" placeholder="관리자 아이디 검색" value="${keyword}">
                    <button type="submit">검색</button>
                </form>
            </div>
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td>프로필</td>
                            <td>아이디</td>
                            <td>유저상태</td>
                            <td>가입일</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="item" varStatus="stat">
                        <tr>
                            <td>${stat.count}</td>
                            <td>프로필</td>
                            <td>
                                <a href="/user/account/detail?seq=${item.rd_seq}">${item.rd_id}</a>
                            </td>
                            <td>
                                <c:if test="${item.rd_status == 1}">정상</c:if>
                                <c:if test="${item.rd_status == 2}">탈퇴대기</c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${item.rd_reg_dt}" pattern="yyyy-MM-dd"/>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            <div class="pager_area">
                <c:forEach begin="1" end="${pageCnt}" var="i">
                    <a href="/user/account/list/?page=${i}&keyword=${keyword}" class="pager">${i}</a>
                </c:forEach>
            </div>
        </div>
    </main>
</body>
</html>