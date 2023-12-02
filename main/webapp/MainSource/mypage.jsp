<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
    <!-- 필요한 CSS 및 JavaScript 파일을 여기에 포함시킬 수 있습니다 -->
</head>
<body>
    <div class="my-page pt-5 ps-5 pe-5">
        <div id="upload-form">
                <input type="file" name="photo" accept="image/*" class="form-control mb-2" style="width: 30vh;">
                <button id="upload-button" class="btn mt-2">사진 업로드</button>
         </div>

            <h3 id="datetime" class=" mt-5"></h3>

            <form id="password-change-form" class=" mt-5" >
                <input type="password" name="currentPassword" placeholder="현재 비밀번호" required class="form-control mb-2" style="width: 30vh;">
                <input type="password" name="newPassword" placeholder="새 비밀번호" required class="form-control mb-2" style="width: 30vh;">
                <input type="password" name="confirmNewPassword" placeholder="새 비밀번호 확인" required class="form-control mb-2" style="width: 30vh;">
                <button type="submit" class="btn mt-2">비밀번호 변경</button>
            </form>
			
            <button id="delete-account-button" class="btn mt-5" style="background-color:#ECDBBA;">회원 탈퇴</button>
            <button id="clear-list" class="btn mt-5 ms-2" style="background-color:#ECDBBA;">리스트 초기화</button>
       
    </div>

  
</body>
</html>
