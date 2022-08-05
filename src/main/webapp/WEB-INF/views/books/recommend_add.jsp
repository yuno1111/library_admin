<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/books/recommend_add.js"></script>
    <link rel="stylesheet" href="/assets/css/book/recommend_add.css">
</head>
<body>
    <main>
        <div class="recommend_add_wrap">
            <div class="recommend_add_left">
                <div id="recommend_add_img_area">
                    첫번째 도서 이미지가 추가됩니다.
                </div>
                <p>리스트 제목</p>
                <input type="text" class="recommend_title" placeholder="리스트 제목을 입력하세요">
                <button class="add_recommend_list">리스트 생성</button>
            </div>
            <div class="recommend_add_right">
                <button class="add_recommend_book">도서 추가하기</button>
                <table class="add_book_list">
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="book_list_popup" hidden>
            <div class="book_list_area">
                <button class="cancel_book_popup">&times;</button>
                <h2>도서 제목 검색</h2>
                <input type="text" placeholder="제목을 입력하세요" class="book_title_search">
                <button class="book_search">검색</button>
                <table class="add_book_popup">
                    <tbody>
                        
                    </tbody>
                </table>
                <div class="book_page_area">
    
                </div>
            </div>
        </div>
    </main>
</body>
</html>