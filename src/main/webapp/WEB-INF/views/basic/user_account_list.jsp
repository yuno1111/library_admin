<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/basic/user_list.js"></script>
</head>
<body>
    <main>
        <div class="user_account_list_wrap">
            <div class="search_box">
                <form action="/user/account/list" method="get">
                    <input type="text" name="keyword" placeholder="유저 아이디 검색" value="${keyword}">
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
                            <td></td>
                            <td></td>
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
                                <c:if test="${item.rd_status == 2}">정지</c:if>
                                <c:if test="${item.rd_status == 3}">영구정지</c:if>
                                <c:if test="${item.rd_status == 4}">탈퇴대기</c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${item.rd_reg_dt}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <button class="rd_del" data-seq="${item.rd_seq}">삭제</button>
                            </td>
                            <td>
                                <button class="rd_status_edit" data-seq="${item.rd_seq}">상태변경</button>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            <div class="pager_area">
                <a href="/user/account/list/?page=1&keyword=${keyword}">처음</a>
                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <c:if test="${i == page}">
                        <a href="/user/account/list/?page=${i}&keyword=${keyword}" class="currentPage" style="color: red;">${i}</a>
                    </c:if>
                    <c:if test="${i != page}">
                        <a href="/user/account/list/?page=${i}&keyword=${keyword}" class="pager">${i}</a>
                    </c:if>
                </c:forEach>
                <a href="/user/account/list/?page=${totalPage}&keyword=${keyword}">끝</a>
            </div>
        </div>
        <div class="user_status_edit_popup" hidden>
            <button class="user_popup_cancel">&times;</button>
            <p>유저 아이디</p>
            <p>
                <input type="text" value="" disabled>
            </p>
            <p>
                <select class="user_status">
                    <option value="1">정상</option>
                    <option value="2">정지</option>
                    <option value="3">영구정지</option>
                    <option value="4">탈퇴대기</option>
                </select>
            </p>
            <p>사유</p>
            <textarea class="user_reason" cols="30" rows="10"></textarea>
            <button class="reset_rd_pwd">비밀번호 초기화</button>
            <button class="user_status_edit">변경</button>
        </div>
    </main>
</body>
</html>