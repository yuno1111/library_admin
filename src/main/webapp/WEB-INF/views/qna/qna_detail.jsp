<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/qna/qna_detail.js"></script>
</head>
<body>
    <main>
        <div class="qna_detail_header">
            <p>${list.qi_title}</p><span>${list.id}</span>
            <span>
                <fmt:formatDate value="${list.qi_reg_dt}" pattern="yyyy-MM-dd"/> 
            </span>
        </div>
        <div class="qna_detail_content">
            <p>
                ${list.qi_text}
            </p>
        </div>
        <div class="qna_answer_area">
            <c:if test="${answerList == null}">
            <input type="number" class="an_aai_seq" value="${admin.aai_seq}" hidden>
            <input type="text" value="${admin.aai_id}" disabled>
            <textarea class="qna_answer" cols="30" rows="10"></textarea>
            <button class="add_answer" data-seq="${list.qi_seq}">답변등록</button>
            </c:if>
            <c:if test="${answerList != null}">
            <input type="text" value="${answerList.aai_id}" disabled>
            <textarea class="mod_qna_answer" cols="30" rows="10">${answerList.an_text}</textarea>
            <button class="mod_answer"  data-seq="${answerList.an_seq}">답변수정</button>
            </c:if>
        </div>
    </main>
</body>
</html>