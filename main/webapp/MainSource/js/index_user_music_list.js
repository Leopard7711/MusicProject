

let currentListName = null;
let currentListItem;
let currentListIndex = 0; 
let listNames = [];

function getUserMusicList(userId,listName) {
	
	
	
    fetch(`/UserMusicListGetCon?userId=${userId}&listName=${listName}`)
    .then(response => response.json())
    .then(data => {
        const musicList = document.getElementById('music-user-list');
        musicList.innerHTML = ''; // 기존 목록 초기화

        data.forEach(music => {
            const listItem = createMusicListItem(music);        
            musicList.appendChild(listItem); // 음악 항목을 리스트에 추가
        });
        
        if (data.length > 0) {
            
            const firstListItem = musicList.firstChild;
            loadMusic(data[0],firstListItem);
        }
        else{
			loadMusicEmpty();
		}
        
    })
    .catch(error => console.error('Error:', error));
}
document.addEventListener('userInfoLoaded', function() {
	console.log(userInfo);
    if(userInfo !=null){
		searchUserMusicList(userInfo.id);
		getUserMusicList(userInfo.id,currentListName);
	}
    
    
    document.querySelector('.fa-caret-left').addEventListener('click', () => {
    if (currentListIndex > 0) {
        currentListIndex--;
        updateCurrentListName();
    }
	});
	
	// '>' 버튼 클릭 이벤트
	document.querySelector('.fa-caret-right').addEventListener('click', () => {
	    if (currentListIndex < listNames.length - 1) {
	        currentListIndex++;
	        updateCurrentListName();
	    }
	});
	    
    document.querySelector('.fa-plus').addEventListener('click', function() {
    const newListName = prompt("새로운 리스트의 이름을 입력하세요:");
    if (newListName) {
        createMusicListName(newListName);
    }
	});
    
});



function createMusicListItem(music) {
	
	
	
    const listItem = document.createElement('li');
    listItem.className = 'music-user-list-item d-flex align-items-center';
 
	listItem.dataset.music = JSON.stringify(music);;
    // 재생 아이콘 생성
    
    const playIcon = document.createElement('i');
    playIcon.className = 'fa-solid fa-play';
	playIcon.addEventListener('click', function() {
		
        loadMusic(music,listItem,true);
       
    });
    listItem.appendChild(playIcon);
    
    
    // 텍스트 컨테이너 생성
    const textContainer = document.createElement('div');
    textContainer.className = 'text-center';

    // 곡 제목
    const title = document.createElement('p');
    title.textContent = music.title;
    textContainer.appendChild(title);

    // 아티스트 이름
    const artist = document.createElement('p');
    artist.textContent = music.artist;
    textContainer.appendChild(artist);

    listItem.appendChild(textContainer);

    // 삭제 아이콘 생성
    const deleteIcon = document.createElement('i');
    deleteIcon.className = 'fa-solid fa-xmark';
    deleteIcon.addEventListener('click',function(){
		deleteMusicFromList(userInfo.id, currentListName, music.id) 
	});
    listItem.appendChild(deleteIcon);

    return listItem;
}
// 검색 !!!!!!
function searchUserMusicList(userId) {
    fetch(`/UserMusicListSearchCon?userId=${userId}`)
    .then(response => response.json())
    .then(names => {
        listNames = names; 
        updateCurrentListName(); 
    })
    .catch(error => console.error('Error:', error));
}


// 업데이트!!!!!!!
function updateCurrentListName() {
    const currentNameElement = document.getElementById('music-user-list-currentname');
    if (listNames.length > 0) {
        currentNameElement.textContent = listNames[currentListIndex];
        currentListName= listNames[currentListIndex];
    }
    else{
		firstListName="첫번째 리스트";
		currentNameElement.textContent= firstListName;
		currentListName=firstListName;
	}
    getUserMusicList(userInfo.id,currentListName);
};

// 삭제!!!!!!
function deleteMusicFromList(userId, listName, musicId) {
    // 서버에 삭제 요청을 보내는 fetch 호출
    fetch(`/UserMusicListRemoveCon?userId=${userId}&listName=${listName}&musicId=${musicId}`, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        // 리스트에서 항목 삭제
        getUserMusicList(userId, listName);
    })
    .catch(error => console.error('Error:', error));
}
// 삽입 !!!!!!!!!!
function insertToMusicList(musicId) {
	
	if(!checkUserInfo()){
		return false;
	}
		
    fetch(`/UserMusicListInsertCon?userId=${userInfo.id}&listName=${currentListName}&musicId=${musicId}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => {
        if(data.success) {
            alert("음악이 리스트에 추가되었습니다.");
            updateCurrentListName();
            // 필요한 경우 UI 업데이트
        } else {
            alert("음악이 리스트에 이미 존재합니다.");
        }
    })
    .catch(error => console.error('Error:', error));
}



function createMusicListName(listName) {
	
	if(!checkUserInfo()){
		return false;
	}
	
		
	
	listNames.push(listName);
	
	
	currentListIndex = listNames.indexOf(listName);
	updateCurrentListName(); 
	
	
    
}