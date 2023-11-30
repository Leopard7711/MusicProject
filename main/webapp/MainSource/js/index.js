let userInfo=null;
const userInfoLoadedEvent = new Event('userInfoLoaded');


document.addEventListener('DOMContentLoaded', function() {
    var menuLinks = document.querySelectorAll('.sidebar-menu-list');


	getUserInfo()
    .then(info => {
        if (info.result === "success") {
            userInfo = info;
             // 사용자 정보가 성공적으로 로드된 후에 실행할 함수
        } else {
            console.log("사용자 정보를 가져오는데 실패했습니다.");
        }
        InitUserSection();
      
        document.dispatchEvent(userInfoLoadedEvent);
    })
    .catch(error => {
        console.error('Error:', error);
    });
	
    menuLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // 기본 링크 동작 방지
            var contentUrl = this.getAttribute('href'); // 링크의 href 속성 사용
            mainsectionFetch(contentUrl);
        }); 
    });


	
	
	var logined_user_name=document.getElementById('logined-user-name')
	if(logined_user_name!=null){
		logined_user_name.addEventListener('click',function(){
        mainsectionFetch("mypage.jsp");
    	});
	}
	
	document.getElementById("changeStyleButton").addEventListener("click", function() {
        var contents = document.querySelectorAll(".debug"); // 클래스 이름으로 모든 요소 선택
        contents.forEach(function(content) {
          content.style.border = "2px dashed red"; // 각 요소에 스타일 적용
          // 여기에 다른 CSS 속성 변경을 추가할 수 있습니다.
        });
      });

      // 모든 .nav-link 요소를 선택합니다.
      var navLinks = document.querySelectorAll('.nav-link');
      
      
        navLinks.forEach(function(navLink) {
          navLink.addEventListener('click', function(event) {
            // 먼저 모든 링크와 관련된 i 태그에서 'active' 클래스를 제거합니다.
            navLinks.forEach(function(link) {
              link.classList.remove('active');
              var icon = link.previousElementSibling; // 가정: i 태그가 a 태그 바로 앞에 있음
              if (icon.tagName === 'I') {
                icon.classList.remove('active');
              }
            });
      
            // 현재 클릭된 링크와 관련된 i 태그에 'active' 클래스를 추가합니다.
            this.classList.add('active');
            var currentIcon = this.previousElementSibling;
            if (currentIcon.tagName === 'I') {
              currentIcon.classList.add('active');
            }
      
            event.preventDefault(); // 기본 링크 동작을 방지
          });
        });
	
		var homeButton = document.getElementById('home-button');
    
        // 페이지가 로드될 때 Home 버튼을 자동으로 클릭합니다.
        if(homeButton) {
            homeButton.click();
        }
    
});


	
function mainsectionFetch(contentUrl) {
fetch(contentUrl)
    .then(function(response) {
        return response.text();
    })
    .then(function(data) {
        document.querySelector('.mainsection').innerHTML = data;
        console.log('sa');
        switch(contentUrl){
			case 'home.jsp':
				fetchMusicList();
				break;
			case 'mypage.jsp':
				
				initMyPage();
				break;
		}
        
    })
    .catch(function(error) {
        console.error('Error:', error);
    });
};