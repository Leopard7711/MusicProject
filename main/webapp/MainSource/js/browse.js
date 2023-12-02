function displaySearchResults(data) {
    const resultsContainer = document.getElementById('search-results');
    resultsContainer.innerHTML = '';
    resultsContainer.className = 'row';

    if (data.length === 0) {
        alert('검색 결과가 없습니다.');
    } else {
        data.forEach(music => {
            const musicElement = createMusicElement(music);
            resultsContainer.appendChild(musicElement);
        });
    }
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
