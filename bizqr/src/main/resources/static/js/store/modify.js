document.getElementById('store-image-upload').addEventListener('change', function(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('image-preview');
        output.src = reader.result;
        output.style.display = 'block'; // 이미지 미리보기를 표시합니다.
    };
    reader.readAsDataURL(event.target.files[0]);
});