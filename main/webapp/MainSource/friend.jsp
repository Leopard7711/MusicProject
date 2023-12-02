<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="sendFriendRequestDiv" class="my-4 mt-0 p-5">
	    <h4>친구 요청 보내기</h4>
	    <input type="text" id="friendIdInput" class="form-control" placeholder="친구의 ID 또는 이름">
	    <button id="sendRequestButton" class="btn mt-3" style="background-color:#C84B31;">친구 요청 보내기</button>
	</div>
	<div class="friend-lists-container row"> 
        <div class="friend-list col-md-5">
            <h2 class="mt-4 ms-4">친구 목록</h2>
            <ul id="friendList" class="friend-list-ul">
               
            </ul>
        </div>

        <div class="friend-list col-md-5">
            <h2 class="mt-4 ms-4">친구 요청</h2>
            <ul id="friendRequestList" class="friend-list-ul">
              
            </ul>
        </div>
    </div>
</body>
</html>
