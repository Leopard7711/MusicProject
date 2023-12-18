document.addEventListener("DOMContentLoaded", function() {
    var preloadedImages = []; 
    var currentIndex = 0; 


    function preloadImages(imagePaths) {
        return Promise.all(imagePaths.map(function(path) {
            return new Promise(function(resolve, reject) {
                var img = new Image();
                img.onload = function() {
                    resolve(img);
                };
                img.onerror = reject;
                img.src = path;
            });
        }));
    }


    function changeBackground() {
        var selectedImage = preloadedImages[currentIndex];

        document.body.style.transition = 'background 0.5s ease'; 
        document.body.style.backgroundImage = 'url("' + selectedImage.src + '")';
        document.body.style.backgroundSize = 'cover';
        console.log('Background changed to:', selectedImage.src);

        currentIndex = (currentIndex + 1) % preloadedImages.length;
    }


    function scheduleNextChange() {
        setTimeout(function() {
            changeBackground();
            scheduleNextChange();
        }, 6000); 
    }


    fetch('background.jsp') 
        .then(response => response.text())
        .then(text => {
            var imagePaths = text.split('\n')
                                 .filter(fileName => fileName.trim() !== '')
                                 .map(fileName => 'img/background/' + fileName);    
                
            return preloadImages(imagePaths);
        })
        .then(loadedImages => {
            preloadedImages = loadedImages;
            changeBackground();
            scheduleNextChange();
        })
        .catch(error => console.error('Failed to load images:', error));
});