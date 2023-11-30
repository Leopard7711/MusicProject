function getUserInfo() {
    return fetch('/MemberInfoGetCon')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    });
}

function checkUserInfo(){
	if(userInfo!=null){
		return true;
	}
	else{
		alert("로그인을 해주세요");
		return false;
	}

}


function InitUserSection(){

    var loginedUserName = document.getElementById("logined-user-name");
    var logoutForm = document.getElementById("logout-form");
    var greeting = document.getElementById("greeting");
    var loginButton = document.getElementById("login-button");
	
    if (userInfo!=null) {
        // userId가 null이 아닌 경우
        loginedUserName.textContent = userInfo.id + "님";
        logoutForm.style.display = "block";
        greeting.textContent = "반갑습니다";
        loginButton.style.display = "none";
        updateUserProfilePicture();
    } else {
        // userId가 null인 경우
        
        loginedUserName.style.display = "none";
        logoutForm.style.display = "none";
        greeting.style.display = "none";
        loginButton.style.display = "block";
    }
    
    
}

function updateUserProfilePicture() {
    const profileImgElement = document.getElementById('sidebar-user-photo');
    if (profileImgElement) {
        // 새 이미지 경로 생성. 캐시를 방지하기 위한 무작위 파라미터 포함
        const imageUrl = `/MainSource/img/user_image/${userInfo.id}.jpg?${new Date().getTime()}`;

        // 이미지 로드 실패 시 기본 이미지로 대체
        profileImgElement.onerror = function() {
            this.src = 'img/user_icon.png';
        };

        // 새 이미지 경로 설정
        profileImgElement.src = imageUrl;
    }
}