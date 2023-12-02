<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Browse Response</title>
</head>
<body >


   <form id="search-form" class="p-5">
            <div class="mb-3">
                <label for="search-category" class="form-label">검색 카테고리</label>
                <select id="search-category" class="form-select">
                    <option value="title">제목</option>
                    <option value="artist">아티스트</option>
                    <option value="genre">장르</option>
                    <option value="album">앨범</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="search-keyword" class="form-label">검색 키워드</label>
                <input type="text" id="search-keyword" class="form-control" placeholder="음악 검색...">
            </div>
            <button type="submit" class="btn mt-2" style="background-color:#C84B31; color:white;">검색</button>
    </form>
	<h2 class="mt-2 px-4" >검색 결과</h2>
	<div class="container album-container" class="mt-5 px-4">
        <div id="search-results">
    	
    
    
    	</div>
        
   	</div>
    

</body>
</html>