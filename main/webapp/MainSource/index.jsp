<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.project.musicProject.MemberDTO"%>
<%@ page import="java.io.File" %>
<%@ page import="com.project.musicProject.MemberDTO" %>
<!DOCTYPE html>
<html>
<head>
	
    <meta charset="UTF-8">
    <title>My Bootstrap Page</title>
    <base href="/MainSource/">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/index_style.css">
    <link rel="stylesheet" href="css/home_style.css">
    <link rel="stylesheet" href="css/login_style.css">
    <link rel="stylesheet" href="css/join_style.css">
    <link rel="stylesheet" href="css/mypage_style.css">
    <script src="https://kit.fontawesome.com/19f16a6e82.js"></script>
    <script src="js/index.js"></script>
    <script src="js/mypage.js"></script>
    <script src="js/user.js"></script>
    <script src="js/index_user_music_list.js"></script>
	<script src="js/index_musicplayer.js"></script>
	
	<script src="js/login.js"></script>
	<script src="js/home.js"></script>
</head>



<body>
   	
    <nav class="debug navbar navbar-expand-lg navbar-light">
        <div class="container-fluid d-flex justify-content-between align-items-center" >
            <!-- 검색 입력란 -->
            <div class="input-group mb-3" style="width: 300px;">
                <input type="text" class="form-control" placeholder="검색" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div>
    
            <!-- 중앙 버튼 -->
            <div class="d-flex justify-content-center ">
                <button id="changeStyleButton" class="btn btn-secondary mr-2">보더 보이기</button>
                
            </div>
    
            <!-- 오른쪽 아
            이콘 -->
            <div>
                <i class="fa-solid fa-triangle-exclamation pe-3"></i>
                <i class="fa-solid fa-gear pe-3"></i>
            </div>
        </div>
    </nav>
    <div class="content container-fluid">
        <div class="row">
            <aside class="debug col-md-2 sidebar-left"  >
                <div class="sidebar-user-info">
                	
       
        			<img src="img/user_icon.png" alt="User Photo" id="sidebar-user-photo">
    		
					<div id="logined-user-name" class="ms-5 float-left"></div>
					<form id="logout-form" action="/LogoutCon" method="get" class="float-right">
					    <button type="submit"><i class="fa-solid fa-right-from-bracket"></i></button>
					</form>
					
					<button id="login-button" class="no-button-design float-left"><h3 class="ms-5 mb-5">Login</h3></button>
                    <h5 id="greeting" class="ms-5 float-left"></h5>
                </div>
                
                <ul class="nav flex-column sidebar-menu  float-none">
                    <li class="nav-item">
                        <i class="fa-solid fa-house active"></i>
                        <a class="sidebar-menu-list nav-link active" id='home-button' href="home.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        <a class="sidebar-menu-list nav-link" href="browse.jsp">Browse</a>
                    </li>
                    <li class="nav-item">
                        <i class="fa-solid fa-list"></i>
                        <a class="sidebar-menu-list  nav-link" href="#">Playlists</a>
                    </li>
                    <li class="nav-item">
                        <i class="fa-solid fa-headset"></i>
                        <a class="sidebar-menu-list nav-link" href="#">Live</a>
                    </li>
                    <li class="nav-item">
                        <i class="fa-solid fa-user-group"></i>
                        <a class="sidebar-menu-list nav-link" href="#">Friends</a>
                    </li>
                </ul>
                
            </aside>
            <main class="debug col-md-7 mainsection  ps-5 pe-5 ">

            </main>
            <aside class="debug col-md-3 sidebar-music-player pt-5 text-center">
                <div id="plyaing-music-img-plate" class="d-flex justify-content-center">
                    <img id="playing-music-img" src="music/image/img_not_found.png" width="60%" class="img-fluid" style="background-color: #ECDBBA;">
                </div>
                <div  class="playing-music d-flex justify-content-center mt-3">
                    <audio id="audioPlayer" controls>
			            <source id="audioSource" src="" type="audio/mpeg">
			            
			        </audio>
                    
                </div>
                
                <div class="sidebar-music-info d-flex flex-column justify-content-center">
                    <h1 id="playing-music-title"></h1>
                    <h3 id="playing-music-artist" style="color: #ECDBBA;"></h3>
                </div>
                
                
				<div id="music-user-list-name" class="d-flex align-items-center justify-content-between">
				    <i class="fa-solid fa-caret-left"></i>
				    <span id="music-user-list-span" class="d-flex mt-3 align-items-center">
				    	<p id="music-user-list-currentname" >리스트 이름</p>
				        <i class="ms-2 fa-solid fa-plus"></i>
				    </span>
				    <i class="fa-solid fa-caret-right"></i>
				</div>
                
                <ul id="music-user-list" class=" list-group d-inline-block">
			        
			    </ul>
                
            </aside>
        </div>
    </div>
    
    <div id="login-join-overlay-container" class="hidden">
    <div id="login-join-form-container"></div>
	</div>

	
</body>





</html>
    