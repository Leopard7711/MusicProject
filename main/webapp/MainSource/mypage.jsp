<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>My Page</title>
    <!-- 필요한 CSS 및 JavaScript 파일을 여기에 포함시킬 수 있습니다 -->
</head>
<body>
    <div class="my-page">
        <!-- AJAX를 통해 제출될 파일 업로드 폼 -->
        <div id="upload-form">
            <input type="file" name="photo" accept="image/*">
            <input type="button" value="사진 업로드" id="upload-button">
        </div>
        
        <h3 id="datetime"></h3>
        
        <form id="password-change-form">
            <input type="password" name="currentPassword" placeholder="현재 비밀번호" required>
		   <input type="password" name="newPassword" placeholder="새 비밀번호" required>
		   <input type="password" name="confirmNewPassword" placeholder="새 비밀번호 확인" required>
		   <input type="submit" value="비밀번호 변경">
        </form>
    </div>

  
</body>
</html>