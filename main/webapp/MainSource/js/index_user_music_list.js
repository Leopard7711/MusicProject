

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

    
    const playIcon = document.createElement('i');
    playIcon.className = 'fa-solid fa-play';
	playIcon.addEventListener('click', function() {
		
        loadMusic(music,listItem,true);
       
    });
    listItem.appendChild(playIcon);
    
    

    const textContainer = document.createElement('div');
    textContainer.className = 'text-center';

 
    const title = document.createElement('p');
    title.textContent = music.title;
    textContainer.appendChild(title);

  
    const artist = document.createElement('p');
    artist.textContent = music.artist;
    textContainer.appendChild(artist);

    listItem.appendChild(textContainer);


    const deleteIcon = document.createElement('i');
    deleteIcon.className = 'fa-solid fa-xmark';
    deleteIcon.addEventListener('click',function(){
		deleteMusicFromList(userInfo.id, currentListName, music.id) 
	});
    listItem.appendChild(deleteIcon);

    return listItem;
}

function searchUserMusicList(userId) {
    fetch(`/UserMusicListSearchCon?userId=${userId}`)
    .then(response => response.json())
    .then(names => {
        listNames = names; 
        updateCurrentListName(); 
    })
    .catch(error => console.error('Error:', error));
}



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
		listNames.push("첫번째 리스트");
	}
	
    getUserMusicList(userInfo.id,currentListName);
};


function deleteMusicFromList(userId, listName, musicId) {
   
    fetch(`/UserMusicListRemoveCon?userId=${userId}&listName=${listName}&musicId=${musicId}`, {
        method: 'GET'
    })
    .then(response => {
       
        mainsectionFetch(currentContentUrl);
        getUserMusicList(userId, listName);
    })
    .catch(error => console.error('Error:', error));
}

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
            if(userInfo.id==='admin'){
				mainsectionFetch(currentContentUrl); 
			}
            updateCurrentListName();
           
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
	mainsectionFetch(currentContentUrl);
	updateCurrentListName(); 
	
	
    
}