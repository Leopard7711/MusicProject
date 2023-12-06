<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="admin my-page p-5">
		<h2 class ="mb-3">음악 추가</h2>
		<div id="music-upload-form">
                <h4>MP3 파일</h4><input type="file" name="music" accept=".mp3*" class="form-control mb-2" style="width: 30vh;">
                <h4>JPG 파일</h4><input type="file" name="photo" accept="image/*" class="form-control mb-2" style="width: 30vh;">
                <h4>제목</h4><input type="text" name="title" class="form-control mb-2" style="width: 30vh;">
                <h4>아티스트</h4><input type="text" name="artist" class="form-control mb-2" style="width: 30vh;">
                <h4>앨범</h4><input type="text" name="album" class="form-control mb-2" style="width: 30vh;">
                <h4>장르</h4><input type="text" name="genre" class="form-control mb-2" style="width: 30vh;">
                <h4>URL 이름</h4><input type="text" name="url" class="form-control mb-2" style="width: 30vh;">
                <button id="admin-upload-button" class="btn mt-2" style="color:white;">음악 업로드</button>
         </div>
		
		<h2 class = "mt-5 mb-3">유저 관리</h2>
		<table class="table table-bordered table-striped table-dark ">
		    <thead>
		        <tr>
		            <th colspan="4" class="text-center text-white bg-blue">회원목록</th>
		        </tr>
		        <tr class="text-center bg-black">
		            <th style="width: 30%;">ID</th>
		            <th style="width: 30%;">EMAIL</th>
		            <th style="width: 30%;">JOIN DATE</th>
		            <th style="width: 10%;">REMOVE</th>
		        </tr>
		    </thead>
		    <tbody id="userList">
        
   			 </tbody>
		</table>
		
	</div>
</body>
</html>