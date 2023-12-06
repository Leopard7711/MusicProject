function initMyPage() {
    console.log("init");
    document.getElementById('upload-button').addEventListener('click', function () {
        var fileInput = document.querySelector('input[type="file"]');
        var file = fileInput.files[0];

        if (file) {
            var formData = new FormData();
            formData.append('photo', file);

            fetch('/MemberImageInsertCon', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    
                    if (data.uploadStatus === 'success') {
                        
                        updateUserProfilePicture();
                        alert('사진이 성공적으로 업로드되었습니다.');
                    } else {
                        // 업로드 실패 처리
                        alert('사진 업로드에 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('업로드 중 오류가 발생했습니다.');
                });
        }
    });
    
    
    document.getElementById("datetime").innerHTML = "가입 날짜 : "+userInfo.datetime;
    
    document.getElementById('password-change-form').addEventListener('submit', function(e) {
        e.preventDefault();

        var formData = new FormData(this);
    	var urlEncodedData = new URLSearchParams(formData);
    	
	    var currentPassword = formData.get('currentPassword');
	    var newPassword = formData.get('newPassword');
	    var confirmNewPassword = formData.get('confirmNewPassword');
		console.log(currentPassword+";"+userInfo.password);
		if(currentPassword !== userInfo.password){
			alert('기존 비밀번호가 맞지 않습니다');
	        return;
		}
	
	    // 새 비밀번호와 비밀번호 확인이 일치하는지 확인
	    if (newPassword !== confirmNewPassword) {
	        alert('새 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
	        return;
	    }
	
	    // 서버에 요청 보내기
	    fetch('/MemberPasswordChangeCon', {
	        method: 'POST',
	        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
	        },
	        body: urlEncodedData
	    })
	    .then(response => response.json())
	    .then(data => {
	        if(data.status === 'success') {
				
				getUserInfo()
			    .then(info => {
			        if (info.result === "success") {
			            userInfo = info;
			            
			            alert('비밀번호가 성공적으로 변경되었습니다.');
			        } else {
			            
			        }
			        InitUserSection();
			      
			        document.dispatchEvent(userInfoLoadedEvent);
			    })
			    .catch(error => {
			        console.error('Error:', error);
			    });
				
				
	            
	        } else {
	            alert('비밀번호 변경 실패: ' + data.message);
	        }
	    })
	    .catch(error => {
	        console.error('Error:', error);
	        alert('비밀번호 변경 중 오류가 발생했습니다.');
	    });
    });
    
	document.getElementById('delete-account-button').addEventListener('click', function() {
    if (confirm('정말로 탈퇴하시겠습니까?')) {
        if (confirm('회원 탈퇴 후에는 복구할 수 없습니다. 계속하시겠습니까?')) {

            // Prepare form data
            var formData = new URLSearchParams();
            formData.append('userId', userInfo.id); 

            fetch('/MemberDeleteCon', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formData
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    alert('회원 탈퇴가 완료되었습니다.');
                     window.location.href = '/LogoutCon'
                } else {
                    alert('탈퇴 처리 중 오류가 발생했습니다: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('탈퇴 처리 중 오류가 발생했습니다.');
            });
        }
    }
});



	document.getElementById('clear-list').addEventListener('click', function() {
	    if (confirm('정말로 음악 목록을 초기화하시겠습니까?')) {
	        
	        var listName = "exampleListName"; 
	
	        fetch('/UserMusicListClearCon', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded'
	            },
	            body: 'listName=' + encodeURIComponent(listName)
	        })
	        .then(response => response.json())
	        .then(data => {
	            if(data.status === 'success') {
	                alert('음악 목록이 성공적으로 초기화되었습니다.');
	                window.location.href = 'index.jsp';
	                
	            } else {
	                alert('목록 초기화 실패');
	            }
	        })
	        .catch(error => {
	            console.error('Error:', error);
	            alert('목록 초기화 중 오류가 발생했습니다.');
	        });
	    }
	});



}



