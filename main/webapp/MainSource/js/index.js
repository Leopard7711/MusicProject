let userInfo=null;
const userInfoLoadedEvent = new Event('userInfoLoaded');


document.addEventListener('DOMContentLoaded', function() {
    var menuLinks = document.querySelectorAll('.sidebar-menu-list');


	getUserInfo()
    .then(info => {
        if (info.result === "success") {
            userInfo = info;
             
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
            event.preventDefault(); 
            var contentUrl = this.getAttribute('href'); 
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
        var contents = document.querySelectorAll(".debug"); 
        contents.forEach(function(content) {
          content.style.border = "2px dashed red";
          
        });
      });

 
      
		homeButtonCick();
		
    
});

function changeMenuColor(menuId) {
    var navLinks = document.querySelectorAll('.nav-link');

    // 모든 링크와 아이콘에서 'active' 클래스 제거
    navLinks.forEach(function(navLink) {
        navLink.classList.remove('active');
        var icon = navLink.previousElementSibling;
        if (icon && icon.tagName === 'I') {
            icon.classList.remove('active');
        }
    });

    // menuId에 해당하는 메뉴 항목에 'active' 클래스 추가
    if (menuId != null) {
        var currentMenu = document.getElementById(menuId);
        if (currentMenu) {
            var navLink = currentMenu.querySelector('.nav-link');
            var icon = currentMenu.querySelector('i');
            
            if (navLink) {
                navLink.classList.add('active');
            }
            if (icon) {
                icon.classList.add('active');
            }
        }
    }
}
function homeButtonCick(){
	var homeButton = document.getElementById('home-button');
    
       
        if(homeButton) {
            homeButton.click();
        }
}
	
function mainsectionFetch(contentUrl) {
fetch(contentUrl)
    .then(function(response) {
        return response.text();
    })
    .then(function(data) {
        document.querySelector('.mainsection').innerHTML = data;
        console.log('sa');
        
        
   		menuIdName = null;
   		
        switch(contentUrl){
			case 'home.jsp':
				fetchMusicList();
				menuIdName='home'
				break;
			case 'mypage.jsp':
				menuIdName=null;
				initMyPage();
				break;
			case 'browse.jsp':
				
				initBrowse();
				menuIdName='browse'
				break;
			case 'playlist.jsp':
				
				initPlaylist();
				menuIdName='playlist'
				break;
			case 'friend.jsp':
				
				initFriend();
				menuIdName='friend'
				break;
		}
        changeMenuColor(menuIdName);
    })
    .catch(function(error) {
        console.error('Error:', error);
    });
};