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
    fetchUsers();
}
function areInputsFilled() {
        
        const inputs = document.querySelectorAll('#upload-form input[type="text"], #upload-form input[type="file"]');

        
        for (let input of inputs) {
            if (input.value === '') {
                return false; 
            }
        }
        return true; 
    }
    
    
function fetchUsers() {
        fetch('/MemberInfoListGetCon') 
            .then(response => response.json())
            .then(data => {
                const userListEl = document.getElementById('userList');
                userListEl.innerHTML = '';
                data.forEach(user => {
                    userListEl.innerHTML += `
                        <tr class="text-center">
                            <td>${user.id}</td>
                            <td>${user.email}</td>
                            <td>${user.joinDate}</td>
                            <td><button class="btn btn-danger" onclick="deleteUser('${user.id}')">삭제</button></td>
                        </tr>
                    `;
                });
            });
    }

   
function deleteUser(id) {
    var formData = new URLSearchParams();
    formData.append('userId', id);

    fetch('/MemberDeleteCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded' 
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        alert('계정이 삭제되었습니다');
        if (data.status === 'success') {
            fetchUsers(); 
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('삭제 실패');
    });
};