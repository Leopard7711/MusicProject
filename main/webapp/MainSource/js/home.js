
function fetchMusicList() {
        fetch('/UserMusicListGetCon?userId=admin&listName=best')
        .then(response => response.json())
        .then(data => {
            const albumContainer = document.querySelector('.album-container');
            let rowDiv = createRowDiv(); 

            data.forEach((music, index) => {
                
                if (index > 0 && index % 4 === 0) {
                    albumContainer.appendChild(rowDiv); 
                    rowDiv = createRowDiv();
                }

                const musicElement = createMusicElement(music);
                rowDiv.appendChild(musicElement); // 음악 항목을 현재 row에 추가
            });

            albumContainer.appendChild(rowDiv); // 마지막 row 추가
        })
        .catch(error => console.error('Error:', error));
    }

    function createRowDiv() {
        const rowDiv = document.createElement('div');
        rowDiv.className = 'row';
        return rowDiv;
    }

    function createMusicElement(music) {
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
    

    
    