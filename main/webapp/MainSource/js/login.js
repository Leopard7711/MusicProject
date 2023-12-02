document.addEventListener('DOMContentLoaded', function() {
    var loginButton = document.getElementById('login-button');
    if (loginButton) {
        loginButton.addEventListener('click', function() {
            
            loadLogin();
        });
    }
});

function loadLogin(){
	fetch('login.jsp')
		        .then(response => {
		            if (response.ok) {
		                return response.text();
		            }
		            throw new Error('Network response was not ok.');
		        })
		        .then(html => {
					
					
					
		            document.getElementById('login-join-form-container').innerHTML = html;
		            document.getElementById('login-join-overlay-container').style.display = 'block';
		            
		           	document.getElementById('btn-login').addEventListener('click',function(){
						   	var id= document.getElementById('id-input');
					   		var password = document.getElementById('password-input');
						   if(id.value===""||password.value===""){
								alert('모두 입력해주세요.');
								return false;
							}
						   
						   
						   login();
					   })
					document.getElementById('btn-goto-join').addEventListener('click',function(){
						  	loadJoin();
					   })
					document.getElementById('btn-exit').addEventListener('click',function(){
						  document.getElementById('login-join-form-container').innerHTML = "";
						  document.getElementById('login-join-overlay-container').style.display='none';
					   })
						   
					   
		            
		        })
		        .catch(error => {
		            
	        });
			
}
function loadJoin(){
	fetch('join.jsp')
		        .then(response => {
		            if (response.ok) {
		                return response.text();
		            }
		            throw new Error('Network response was not ok.');
		        })
		        .then(html => {
					//var login_container = document.getElementById('join-container')
					
		            document.getElementById('login-join-form-container').innerHTML = html;
		            document.getElementById('login-join-overlay-container').style.display = 'block';
		            
		           	document.getElementById('btn-goto-login').addEventListener('click',function(){
						   loadLogin();
						   
					   })
				   	document.getElementById('btn-join').addEventListener('click',function(){
						   	
					   		var email = document.getElementById('email-input');
					   		var id= document.getElementById('id-input');
					   		var password = document.getElementById('password-input');
						    var confirmPassword = document.getElementById('password-input-rewrite');
							
							
							if(id.value===""||email.value===""||password.value===""||confirmPassword.value===""){
								alert('모두 입력해주세요.');
								return false;
							}
							
							
							
						    if(password.value !== confirmPassword.value) {
						        alert('비밀번호가 일치하지 않습니다.');
						         // 폼 제출을 방지
						        password.value="";
						        confirmPassword.value="";
						        return false;
						    }
						    
						    var idCheck=(id.value.length>=8&&id.value.length<=20);
						    var passwordCheck=(password.value.length>=8&&password.value.length<=20);
						    if(!idCheck||!passwordCheck){
								alert('아이디와 비밀번호는 8자 이상 20자 이하입니다');
								return false;
							}
							
							if(password.value===id.value){
								alert('아이디와 비밀번호는 같을 수 없습니다');
								return false;
							}
						    
						    if(email.validity.typeMismatch) {
					        	alert('올바른 이메일 형식이 아닙니다.');
					        	email.value="";
					        	return false; 
					   		}
						    
						    join();
				
				   })
					document.getElementById('btn-exit').addEventListener('click',function(){
					   document.getElementById('login-join-form-container').innerHTML = "";
					   document.getElementById('login-join-overlay-container').style.display='none';
				   })
					 
					
		            
		        })
		        .catch(error => {
		            
	        });
}

function login() {
    
    const id = document.getElementById("id-input").value;
    const password = document.getElementById("password-input").value;

    
    const data = JSON.stringify({
        id: id,
        password: password
    });

    

    fetch('/LoginCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' 
        },
        body: data 
        
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(responseData => {
        
        if (responseData.status === "success") {
            
            window.location.href = "index.jsp";
        } else {
			alert("로그인 정보가 올바르지 않습니다");
            document.getElementById("id-input").value = "";
            document.getElementById("password-input").value="";
        }
    })
    .catch(error => {
        console.error('Fetch Error:', error);
    });
}

function join(){
	const _email = document.getElementById("email-input").value;
	const _id = document.getElementById("id-input").value;
    const _password = document.getElementById("password-input").value;

   
    const data = JSON.stringify({
		email:_email,
        id: _id,
        password: _password
    });
	console.log(data);
    fetch('/JoinCon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' 
        },
        body: data 
        
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(responseData => {
       
        switch (responseData.status) {
		  case "success":
			    alert("가입되었습니다");
	            loadLogin();
		    break;
		  case "email_exists":
		    	alert("동일한 이메일이 존재합니다");
		    break;
		  case "id_exists":
		   		alert("동일한 아이디가 존재합니다");
		    break;
		  default:
		    	alert("가입에 실패하였습니다");
		}
        
    })
    .catch(error => {
        console.error('Fetch Error:', error);
    });
	
	
}