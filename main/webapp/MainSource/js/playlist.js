function fetchUserMusicLists(userId) {
    fetch('/UserMusicListSearchCon?userId=' + userId)
        .then(response => response.json())
        .then(listNames => {
            listNames.forEach(listName => {
                fetchUserMusicListDetails(userId, listName);
            });
        })
        .catch(error => console.error('Error fetching user music lists:', error));
}

function fetchUserMusicListDetails(userId, listName) {
    fetch('/UserMusicListGetCon?userId=' + userId + '&listName=' + encodeURIComponent(listName))
        .then(response => response.json())
        .then(musicList => {
            displayUserMusicList(musicList, listName);
        })
        .catch(error => console.error('Error fetching user music list details:', error));
}

function initPlaylist(){
	
	if(userInfo == null){
		alert("로그인을 해주세요");
		mainsectionFetch('home.jsp');
		homeButtonCick();
		return;
	}
	const parent=document.getElementById('playlist-parent')
	parent.innerHTML='';
	
	fetchUserMusicLists(userInfo.id);
}



function displayUserMusicList(musicList, listName) {
    const parent=document.getElementById('playlist-parent')
    
    
    
    const listContainer = document.createElement('div');
    listContainer.className = 'container album-container mt-5';
	parent.appendChild(listContainer);
	
	
    const title = document.createElement('h2');
    title.textContent = listName;
    title.className = 'album-container-title mb-5';
    listContainer.appendChild(title);

    let rowDiv = createRowDiv(); 
    let itemCount = 0;

    musicList.forEach(music => {
        if (itemCount === 4) { 
            listContainer.appendChild(rowDiv);
            rowDiv = createRowDiv(); 
            itemCount = 0; 
        }

        const musicElement = createPlaylistMusicElement(music,listName);
        rowDiv.appendChild(musicElement);
        itemCount++;
    });


    if (itemCount > 0) {
        listContainer.appendChild(rowDiv);
    }


}

function createRowDiv() {
    const rowDiv = document.createElement('div');
    rowDiv.className = 'row';
    return rowDiv;
}


function createPlaylistMusicElement(music,listName) {
        const colDiv = document.createElement('div');
        colDiv.className = 'album col-md-3 d-flex flex-column align-items-center';

		colDiv.addEventListener('click', () => {
       		deleteMusicAtPlaylist(music.id,listName);
   		});

        const img = document.createElement('img');
        img.src = "music/image/"+music.url+".jpg";
        
        colDiv.appendChild(img);

        const textContainer = document.createElement('div');
        textContainer.className = 'album-text-container';

        const title = document.createElement('h4');
        title.textContent = music.title;
        textContainer.appendChild(title);

        const artist = document.createElement('p');
        artist.textContent = music.artist;
        textContainer.appendChild(artist);

        colDiv.appendChild(textContainer);

        return colDiv;
    }
    
    
 function deleteMusicAtPlaylist(musicId, listName){
	console.log(`/UserMusicListRemoveCon?userId=${userInfo.id}&listName=${listName}&musicId=${musicId}`);
	 fetch(`/UserMusicListRemoveCon?userId=${userInfo.id}&listName=${listName}&musicId=${musicId}`, {
        method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        alert("음악을 리스트에서 지웠습니다");
        initPlaylist();
        getUserMusicList(userInfo.id, listName);
    })
    .catch(error => console.error('Error:', error));
 }