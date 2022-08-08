<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/qna/notice_form.js"></script>
</head>
<body>
    <main>
        <c:if test="${list == null}">
            <div class="notice_add_wrap">
                <div class="notice_add_item">
                    <p>제목</p>
                    <input type="text" class="ni_title">
                    <p>작성자 아이디</p>
                    <input type="text" value="${admin.aai_id}" disabled>
                    <input type="number" value="${admin.aai_seq}" class="ni_aai_seq" hidden>
                    <p>내용</p>
                    <textarea class="ni_text" cols="30" rows="10" style="width: 700px; height: 600px; resize: none;"></textarea>
                    <button class="ni_add">공지 등록</button>
                </div>
            </div>
        </c:if>
        <c:if test="${list != null}">
            <div class="notice_mod_wrap">
                <div class="notice_mod_item">
                    <p>제목</p>
                    <input type="text" class="ni_mod_title" value="${list.ni_title}">
                    <p>작성자 아이디</p>
                    <input type="text" value="${list.aai_id}" disabled>
                    <input type="number" value="${list.ni_aai_seq}" class="ni_aai_seq_mod" hidden>
                    <p>내용</p>
                    <textarea class="ni_mod_text" cols="30" rows="10" style="width: 700px; height: 600px; resize: none;">${list.ni_text}</textarea>
                    <button class="ni_mod" data-seq="${list.ni_seq}">공지 수정</button>
                </div>
            </div>
        </c:if>
    </main>
</body>
</html>