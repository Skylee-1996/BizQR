console.log("hi");

// 모달 열기 함수
function openModal(item) {
    // 항목 데이터를 입력 필드에 채워넣기
    document.getElementById('modal-item-name').value = item.querySelector('h2').textContent;
    document.getElementById('modal-item-price').value = item.querySelector('.price').textContent;

    // 모달 보이기
    document.getElementById('modal').style.display = 'block';

    // 모달 저장 버튼에 이벤트 리스너 추가
    document.getElementById('modal-save').addEventListener('click', function(e) {
        e.preventDefault(); // 폼 기본 제출 방지
        // 입력된 값으로 항목 데이터 업데이트
        item.querySelector('h2').textContent = document.getElementById('modal-item-name').value;
        item.querySelector('.price').textContent = document.getElementById('modal-item-price').value;
        item.querySelector('img').src = document.getElementById('modal-item-image').value;
        closeModal(); // 모달 닫기
    }, { once: true }); // 이 리스너가 한 번 실행된 후 제거되도록 설정
}

document.getElementById('modal-close').addEventListener('click', function() {
    closeModal(); // 모달 닫기 함수 호출
});


// 모달 닫기 함수
function closeModal() {
    document.getElementById('modal').style.display = 'none';
}




// 모달 바깥 클릭 시 닫기
window.onclick = function (event) {
    const modal = document.getElementById('modal');
    if (event.target === modal) {
        modal.style.display = "none";
    }
}


document.getElementById('modal-item-image-upload').addEventListener('change', function(event) {
    const reader = new FileReader(); // FileReader 인스턴스 생성
    reader.onload = function(e) {
        const preview = document.getElementById('image-preview');
        preview.src = e.target.result; // 이미지 미리보기에 파일 내용을 src로 설정
        preview.style.display = 'block'; // 이미지 미리보기 보이기
    };
    // 선택된 파일이 있는 경우, FileReader로 읽기
    if (event.target.files[0]) {
        reader.readAsDataURL(event.target.files[0]);
    }
});





document.addEventListener('DOMContentLoaded', function () {
    const addItem = document.getElementById("addItem");
    const addTab = document.querySelector(".addTab");
    const listProduct = document.querySelector(".listProduct");
    let itemId = 0; // 항목 ID를 추적하기 위한 변수

    // addItem 이벤트 리스너
    addItem.addEventListener('click', () => {
        const item = document.createElement("div");
        item.className = "item";
        item.setAttribute('data-id', itemId++); // 각 항목에 고유 ID 부여
        item.innerHTML = `
            <img src="/image/pig.png" alt="">
            <h2>NAME PRODUCT</h2>
            <div class="price">000원</div>
            <button class="modifyItem">수정하기</button>
            <button class="removeItem">삭제</button>
        `;

        // 수정하기 버튼에 이벤트 리스너 추가
        item.querySelector('.modifyItem').addEventListener('click', function () {
            openModal(item);
        });

        // 삭제 버튼에 이벤트 리스너 추가
        item.querySelector('.removeItem').addEventListener('click', function () {
            listProduct.removeChild(item);
        });

        listProduct.appendChild(item);
    });

    // addTab 이벤트 리스너
    addTab.addEventListener('click', () => {
        const tab = document.createElement("div");
        const sidebar = document.querySelector(".sidebar");
        tab.className = "tab";
        tab.innerHTML = `
            <div class="Tab">
                <button class="removeTab"><span class="material-icons-sharp">remove</span></button>
                <h3>Tab</h3>
            </div>
        `;

        // Remove 버튼에 이벤트 리스너 추가
        tab.querySelector('.removeTab').addEventListener('click', function () {
            sidebar.removeChild(tab);
        });

        sidebar.appendChild(tab);
    });
});
