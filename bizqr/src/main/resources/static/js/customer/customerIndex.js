// 모달 열기 함수
function openModal() {
    document.getElementById('modal').style.display = 'block';
}

// 모달 닫기 함수
function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

// 모달 바깥 클릭 시 닫기
window.onclick = function(event) {
    const modal = document.getElementById('modal');

    if (event.target === modal) {
        modal.style.display = "none";
    }
}