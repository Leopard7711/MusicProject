let audioSource;
let audioPlayer;
let musicImg;
let musicTitle;
let musicArtist;

document.addEventListener('userInfoLoaded', function() {
	
	audioPlayer = document.getElementById('audioPlayer');
    audioSource = document.getElementById('audioSource');
	
	musicImg=document.getElementById('playing-music-img');
	musicTitle=document.getElementById('playing-music-title');
	musicArtist=document.getElementById('playing-music-artist');
});



function loadMusicEmpty(){
	musicImg.src="music/image/img_not_found.png";
	musicTitle.innerHTML= "아직 음악이 없습니다";
    musicArtist.innerHTML= "음악을 골라주세요";
    audioSource.src="";
}
function playMusic(){
	
	audioPlayer.play();
}

function loadMusic(music,listItem,playNow = false){
    
    
    fetch('/MusicProject/MusicStreamingCon?id='+music.id)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // JSON 형식의 응답을 기대하는 경우
        })
        .then(data => {
            // 음악 스트리밍 URL을 <audio> 태그의 소스로 설정
    
            audioSource.src = data.streamingUrl; // 서블릿에서 반환된 스트리밍 URL
           	audioPlayer.load();
           
            musicImg.src="music/image/"+data.url+".jpg";
            
            musicTitle.innerHTML= data.title;
            musicArtist.innerHTML= data.artist;
           
         
           	
           	audioPlayer.onloadeddata = function() {
				if(playNow){
					playMusic();
				}
				
                highlightPlayingItem(listItem);
                
            };
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
		
    
    audioPlayer.onended = function() {
        playNextMusic(); // 다음 음악 재생 함수
    };
}


function highlightPlayingItem(item) {
    // 이전에 재생 중인 항목의 하이라이트를 제거
    const previousPlayingItem = document.querySelector('.playing');
    if (previousPlayingItem) {
        previousPlayingItem.classList.remove('playing');
    }
	console.log(item);
    // 현재 재생 중인 항목에 하이라이트 추가
    item.classList.add('playing');
}

function playNextMusic() {
    const playingItem = document.querySelector('.playing');
    if (playingItem) {
        const nextItem = playingItem.nextElementSibling;
        if (nextItem) {
			
            const nextMusic = JSON.parse(nextItem.dataset.music);
            console.log(nextMusic); // nextItem에 music 데이터가 있어야 함
            loadMusic(nextMusic,nextItem,true);
            
            
        }
    }
}