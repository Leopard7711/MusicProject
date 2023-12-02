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
    
    
    fetch('/MusicStreamingCon?id='+music.id)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); 
        })
        .then(data => {
           
            audioSource.src = data.streamingUrl; 
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
        playNextMusic();
    };
}


function highlightPlayingItem(item) {
    
    const previousPlayingItem = document.querySelector('.playing');
    if (previousPlayingItem) {
        previousPlayingItem.classList.remove('playing');
    }
	
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