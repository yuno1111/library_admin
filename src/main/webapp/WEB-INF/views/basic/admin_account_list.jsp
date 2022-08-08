<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/basic/admin_account_list.js"></script>
</head>
<body>
    <main>
        <button class="admin_account_add">관리자 추가</button>
        <div class="admin_account_list_wrap">
            <div class="search_box">
                <form action="/admin/account/list" method="get">
                    <input type="text" name="keyword" placeholder="관리자 아이디 검색" value="${keyword}">
                    <button type="submit">검색</button>
                </form>
            </div>
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td>아이디</td>
                            <td>역할</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="item" varStatus="stat">
                        <tr>
                            <td>${stat.count}</td>
                            <td>${item.aai_id}</td>
                            <td>
                                <c:if test="${item.aai_role == 1}">관리자</c:if>
                                <c:if test="${item.aai_role == 2}">슈퍼관리자</c:if>
                                <c:if test="${item.aai_role == 99}">마스터</c:if>
                            </td>
                            <td>
                                <button class="del_admin_account" data-seq="${item.aai_seq}">삭제</button>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            <div class="pager_area">
                <c:forEach begin="1" end="${pageCnt}" var="i">
                    <a href="/admin/account/list/?page=${i}&keyword=${keyword}" class="pager">${i}</a>
                </c:forEach>
            </div>
        </div>
        <div class="admin_account_add_popup" hidden>
            <div class="admin_account_add_item">
                <button class="cancel_add_popup">&times;</button>
                <p>아이디</p>
                <input type="text" class="aai_id" placeholder="아이디 입력">
                <p class="error" hidden>아이디를 올바르게 입력하세요.</p>
                <p>비밀번호</p>
                <input type="password" class="aai_pwd" placeholder="비밀번호 입력">
                <p class="error" hidden>비밀번호를 올바르게 입력하세요.</p>
                <p>비밀번호 확인</p>
                <input type="password" class="aai_pwd_chk" placeholder="비밀번호 입력">
                <p class="error" hidden>비밀번호가 일치하지 않습니다.</p>
                <p>관리자 역할</p>
                <select class="aai_role">
                    <option value="1">관리자</option>
                    <option value="2">슈퍼관리자</option>
                    <option value="99">마스터</option>
                </select>
                <button class="add_admin_account">추가</button>
            </div>
        </div>
    </main>
</body>
</html>