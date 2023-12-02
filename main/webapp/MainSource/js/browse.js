function displaySearchResults(data) {
    const resultsContainer = document.getElementById('search-results');
    resultsContainer.innerHTML = '';
    resultsContainer.className = 'row';

    if (data.length === 0) {
        alert('검색 결과가 없습니다.');
    } else {
        data.forEach(music => {
            const musicElement = createBrowseMusicElement(music);
            resultsContainer.appendChild(musicElement);
        });
    }
}

function createBrowseMusicElement(music) {
        const colDiv = document.createElement('div');
        colDiv.className = 'album col-md-3 d-flex flex-column align-items-center';

		colDiv.addEventListener('click', () => {
       		insertToMusicList(music.id); // 여기서 음악을 리스트에 추가하는 함수 호출
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

function initBrowse(){
	document.getElementById('search-form').addEventListener('submit', function(e) {
    e.preventDefault();
	
    var category = document.getElementById('search-category').value;
    var keyword = document.getElementById('search-keyword').value;
    var data = JSON.stringify({ category: category, keyword: keyword });
	
	if(keyword===""){
		alert("입력된 값이 없습니다");
		return;
	}
	
    fetch('/MusicSearchCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: data
    })
    .then(response => response.json())
    .then(data => {
        displaySearchResults(data);
       
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
}
