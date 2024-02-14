console.log("hi");

// 모달 열기 함수
function openModal(item) {
    // 항목 데이터를 입력 필드에 채워넣기
    document.getElementById('modal-item-name').value = item.querySelector('h2').textContent;
    document.getElementById('modal-item-price').value = item.querySelector('.price').textContent;


    const imagePreview = document.getElementById('image-preview');
    if (imagePreview) {
        const itemImage = item.querySelector('img');
        if (itemImage) {
            imagePreview.src = itemImage.src; // 이미지 미리보기 요소의 src 업데이트
            imagePreview.style.display = 'block'; // 이미지 미리보기 보이기
        }
    }

    // 모달 보이기
    document.getElementById('modal').style.display = 'block';

    // 모달 저장 버튼에 이벤트 리스너 추가
    document.getElementById('modal-save').addEventListener('click', function(e) {
        e.preventDefault(); // 폼 기본 제출 방지
        // 입력된 값으로 항목 데이터 업데이트
        item.querySelector('h2').textContent = document.getElementById('modal-item-name').value;
        item.querySelector('.price').textContent = document.getElementById('modal-item-price').value;
        // 이미지 미리보기에서 설정된 이미지 src를 사용하여 항목의 이미지 업데이트
        item.querySelector('img').src = document.getElementById('image-preview').src;
        closeModal(); // 모달 닫기
    }, { once: true });
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

document.getElementById('modal-item-image-upload').addEventListener('change', function(event) {
    const reader = new FileReader(); // FileReader 인스턴스 생성
    reader.onload = function(e) {
        // 이미지 미리보기 요소를 찾아서 파일 내용을 src로 설정
        const preview = document.getElementById('image-preview');
        preview.src = e.target.result;
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



function collectAndSendItemsData() {
    const formData = new FormData();
    const storeName = document.getElementById("storeName").value;
    const storeId = document.getElementById("storeId").value;
    const imageFile = document.getElementById('modal-item-image-upload').files[0]; // 이미지 파일 선택

    // 상점 이름과 ID를 formData에 추가
    formData.append('storeName', storeName);
    formData.append('storeId', storeId);

    // 이미지 파일을 formData에 추가
    if(imageFile) {
        formData.append('image', imageFile);
    }

    // 항목 데이터를 formData에 추가
    const items = document.querySelectorAll('.item');
    Array.from(items).forEach((item, index) => {
        formData.append(`items[${index}][menuName]`, item.querySelector('h2').textContent);
        formData.append(`items[${index}][price]`, item.querySelector('.price').textContent);
        // 이미지 URL은 직접 서버로 전송하지 않고, 대신 이미지 파일을 전송
    });

    // 서버로 formData 전송
    fetch('/store/create', {
        method: 'POST',
        body: formData // Content-Type은 multipart/form-data로 자동 설정됩니다.
    })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', "저장성공");
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// '데이터 전송' 버튼 클릭 이벤트에 연결
document.getElementById('submitBtn').addEventListener('click', function() {
    collectAndSendItemsData();
});

// 예시용으로 '데이터 전송' 버튼 클릭 이벤트에 연결
document.getElementById('submitBtn').addEventListener('click', function() {
    collectAndSendItemsData();
});