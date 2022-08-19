<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/common/util.js"></script>
    <script src="/assets/js/login.js"></script>
</head>
<body>
    <main>
        <div class="login_box">
            <h1>Admin Login</h1>
            <p>아이디</p>
            <input type="text" class="aai_id" placeholder="아이디를 입력하세요">
                <p class="error" hidden>아이디를 올바르게 입력하세요.</p>
            <p>비밀번호</p>
            <input type="password" class="aai_pwd" placeholder="비밀번호를 입력하세요">
                <p class="error" hidden>비밀번호를 올바르게 입력하세요.</p>
            <button class="login">로그인</button>
        </div>
    </main>
</body>
</html>