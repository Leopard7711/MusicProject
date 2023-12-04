 function uploadFiles() {
        // FormData 객체를 사용하여 파일 업로드
        
        const form = document.getElementById('music-upload-form');
	    const formData = new FormData();
	
	    // 파일 필드 추가
	    const musicFile = form.querySelector('input[name="music"]').files[0];
	    const photoFile = form.querySelector('input[name="photo"]').files[0];
	    if (musicFile) formData.append('music', musicFile);
	    if (photoFile) formData.append('photo', photoFile);
	
	    // 텍스트 입력 필드 추가
	    const textInputs = form.querySelectorAll('input[type="text"]');
	    textInputs.forEach(input => {
	        formData.append(input.name, input.value);
	        
	    });
		for (let [key, value] of formData.entries()) {
		    
		}
        // 파일 업로드 요청
        fetch('/MusicInsertCon', {
            method: 'POST',  
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            alert("음악이 추가되었습니다");
            
        })
        .catch(error => {
            alert("음악이 추가되지 않았습니다");
        });
    }
function initAdmin(){
	document.getElementById('admin-upload-button').addEventListener('click', function(event) {
        event.preventDefault();
        if (!areInputsFilled()) {
            alert('모든 필드를 채워주세요.');
            return;
        }
        
        const url = document.querySelector('input[name="url"]').value;
		const data = new URLSearchParams();
        data.append('url', url);
 		fetch('/MusicInsertCheckCon', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data
        })
        .then(response => response.json())
        .then(data => {
            if (data.urlExists) {
                alert('이미 존재하는 파일입니다.');
            } else {        
                uploadFiles();
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
}
function areInputsFilled() {
        // 모든 필요한 입력 필드 선택
        const inputs = document.querySelectorAll('#upload-form input[type="text"], #upload-form input[type="file"]');

        // 모든 입력 필드가 채워져 있는지 확인
        for (let input of inputs) {
            if (input.value === '') {
                return false; // 채워지지 않은 필드가 있으면 false 반환
            }
        }
        return true; // 모든 필드가 채워져 있으면 true 반환
    }
    