<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id='join-container' class="join-form">
	
 		<h2 class="text-center mb-2">회원가입</h2>   
 		<p class="text-center mb-4">아이디와 비밀번호는 8자 이상 20자 이하입니다</p> 
		<form>
			<div class="join-form-group">
	            <input type="email" id='email-input' class="form-control bt-2 mb-4" placeholder="이메일" required="required" name="email">      		
	           
	           
	            
	        </div>
	        <div class="join-form-group">
	         	<input type="text" id='id-input' class="form-control bt-2 mb-4" placeholder="아이디" required="required" name="id">   
	        </div>
	        <div class="join-form-group">
	         	<input type="password" id='password-input' class="form-control bt-2 mb-4" 
	            placeholder="비밀번호" required="required" name="password" autocomplete="username">
	        </div>
	        <div class="join-form-group">
	        	<input type="password" id='password-input-rewrite' class="form-control bt-2 mb-4" 
	            placeholder="비밀번호 확인" required="required" name="password-rewrite" autocomplete="username">
	        </div>
		
		</form>
		 
		
		
        
        <div class="join-button-group">
        	<button type="button" id='btn-exit' class="btn">닫기</button>
            <button type="button" id='btn-goto-login' class="btn">로그인</button>
            <button type="button" id='btn-join' class="btn">가입</button>
            
   		</div>
   	</div>
</body>
</html>