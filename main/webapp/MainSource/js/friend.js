

function initFriend(){
	
	if(userInfo == null){
		alert("로그인을 해주세요");
		mainsectionFetch('home.jsp');
		homeButtonCick();
		return;
	}
	loadFriendAndRequests(userInfo.id);
    document.getElementById('sendRequestButton').addEventListener('click', sendFriendRequest);
}


function loadFriendAndRequests(userId) {
    fetch('/FriendshipSearchCon?userId=' + userId)
    .then(response => response.json())
    .then(data => {
        if (data.error) {
            console.error("서버 에러:", data.error);
            return;
        }

        const friendListElement = document.getElementById('friendList');
        const requestListElement = document.getElementById('friendRequestList');
        friendListElement.innerHTML = '';
        requestListElement.innerHTML = ''; 

     	
        if(data.friendRequests && data.friendRequests.length > 0){
            data.friendRequests.forEach(item => {
                const listItem = document.createElement('li');
                listItem.className = 'friend-list-li ';
                
                 
				
                if (item.datetime == null) {
                    
                    if(item.userId!==userInfo.id){
						return;
					}
                    
                    const name= document.createElement('h3');
                    name.innerHTML=item.friendId;
                  	listItem.appendChild(name);
               		
                    const removeButton = document.createElement('button');
					removeButton.addEventListener('click', function() {
						
						removeFriendShip(item.friendId);
						
					});
					
					removeButton.className = 'btn friend-no-button btn-sm';
					removeButton.innerHTML = '<i class="fa-solid fa-x"></i>'; // 거부 아이콘
					listItem.appendChild(removeButton);
					
					friendListElement.appendChild(listItem);
					
                    
                } else {
					
					if(item.friendId!==userInfo.id){
						return;
					}
                    // 친구 요청인 경우
                    listItem.textContent = item.from;

					const name = document.createElement('h3');
					name.innerHTML = item.userId;
					listItem.appendChild(name);
					
					const acceptButton = document.createElement('button');
					acceptButton.className = 'btn friend-ok-button btn-sm';
					acceptButton.innerHTML = '<i class="fa-solid fa-check"></i>'; // 수락 아이콘
					acceptButton.addEventListener('click', function() {
						
						applyFriendRequest(item.userId);
						
					});
					
					listItem.appendChild(acceptButton);
					
					const rejectButton = document.createElement('button');
					rejectButton.addEventListener('click', function() {
						
						deleteFriendRequest(item.userId);
						
					});
					
					
					rejectButton.className = 'btn friend-no-button btn-sm';
					rejectButton.innerHTML = '<i class="fa-solid fa-x"></i>'; // 거부 아이콘
					listItem.appendChild(rejectButton);
					
					
					
					requestListElement.appendChild(listItem);
                }
            });
        } else {
            console.log("친구요청이 없습니다.");
        }
    });
}
function sendFriendRequest() {
    var userId = userInfo.id; 
    var friendId = document.getElementById('friendIdInput').value; 

    if (friendId==='') {
        alert('친구 ID를 입력해주세요.');
        return;
    }
	
    var formData = new URLSearchParams();
    formData.append('action', 'send');
    formData.append('userId', userId);
    formData.append('friendId', friendId);

    fetch('/FriendshipRequestCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('친구요청이 전송되었습니다');
            // 추가적인 UI 업데이트 또는 알림
        } else {
            alert('친구요청에 실패하였습니다:', data.error);
        }
    })
    .catch(error => console.error('Error:', error));
}

function deleteFriendRequest(friendId) {
	
    var formData = new URLSearchParams();
    formData.append('action', 'delete');
    formData.append('userId', friendId);
    formData.append('friendId', userInfo.id);

    fetch('/FriendshipRequestCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            loadFriendAndRequests(userInfo.id);
            alert("요청이 제거되었습니다");
        } else {
            
        }
    })
    .catch(error => console.error('Error:', error));
}

function applyFriendRequest(friendId){
	var formData = new URLSearchParams();
    formData.append('userId', userInfo.id);
    formData.append('friendId', friendId);
	console.log(userInfo.id,friendId);
    fetch('/FriendshipApplyCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            loadFriendAndRequests(userInfo.id);
            alert("요청이 수락되었습니다");
        } else {
            
        }
    })
    .catch(error => console.error('Error:', error));
}
function removeFriendShip(friendId){
	
	if(!confirm("정말 친구를 삭제하겠습니까?")){
		return true;
	}
	
	var formData = new URLSearchParams();
    formData.append('userId', userInfo.id);
    formData.append('friendId', friendId);
	console.log(userInfo.id,friendId);
    fetch('/FriendshipRemoveCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            loadFriendAndRequests(userInfo.id);
            alert("친구가 삭제되었습니다");
        } else {
            
        }
    })
    .catch(error => console.error('Error:', error));
}