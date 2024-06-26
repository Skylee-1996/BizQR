console.log("hi");
let FirstTabName;
let storeId = document.getElementById("storeId").value;
// 모달 열기 함수
function openModal(item) {
    // 항목 데이터를 입력 필드에 채워넣기
    const itemNameInput = document.getElementById('modal-item-name');
    const itemPriceInput = document.getElementById('modal-item-price');

    itemNameInput.placeholder = "메뉴 이름을 입력해주세요.."; // h2 요소의 텍스트를 placeholder로 설정
    itemPriceInput.placeholder = "메뉴 가격을 입력해주세요..";

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
        item.querySelector('.menu-name').textContent = document.getElementById('modal-item-name').value;
        item.querySelector('.menu-price').textContent = document.getElementById('modal-item-price').value;
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
document.addEventListener('DOMContentLoaded', function() {
    const storeId = document.getElementById('storeId').value;
    postTabList(storeId); // 이 함수 내에서 FirstTabName이 설정되고, 그 후 postItemList 호출
});


function postTabList(storeId) {
    getTabListFromServer(storeId).then(result => {
        console.log(result);
        const tabBox = document.getElementById("tab");

        if (result.length > 0) {
            tabBox.innerHTML = ''; // 초기화

            const iconLeftDiv = document.createElement('div');
            iconLeftDiv.className = 'icon left';
            iconLeftDiv.innerHTML = '<i id="left" class="fa-solid fa-angle-left"></i>';
            tabBox.appendChild(iconLeftDiv);

            const tabsBoxUl = document.createElement('ul');
            tabsBoxUl.className = 'tabs-box';

            result.forEach((tabName, index) => {
                const tabLi = document.createElement('li');
                tabLi.className = 'tab';
                if (index === 0) {
                    FirstTabName = tabName; // 첫 번째 탭 이름 설정
                    console.log(FirstTabName); // 여기서 로그 확인 가능
                    tabLi.classList.add('active');

                    postItemList(storeId, FirstTabName); // 첫 번째 탭에 대한 아이템 리스트 요청
                }
                tabLi.textContent = tabName;
                tabLi.addEventListener('click', function() {
                    document.querySelectorAll('.tab').forEach(tab => tab.classList.remove('active'));
                    this.classList.add('active');
                });
                tabsBoxUl.appendChild(tabLi);
            });

            tabBox.appendChild(tabsBoxUl);

            const iconRightDiv = document.createElement('div');
            iconRightDiv.className = 'icon right';
            iconRightDiv.innerHTML = '<i id="right" class="fa-solid fa-angle-right"></i>';
            tabBox.appendChild(iconRightDiv);

            // 아이콘 클릭 이벤트와 스크롤 이벤트 추가
            const updateIconVisibility = () => {
                const maxScrollLeft = tabsBoxUl.scrollWidth - tabsBoxUl.clientWidth;
                iconLeftDiv.style.display = tabsBoxUl.scrollLeft > 0 ? 'block' : 'none';
                iconRightDiv.style.display = tabsBoxUl.scrollLeft < maxScrollLeft ? 'block' : 'none';
            };

            iconLeftDiv.addEventListener('click', () => {
                tabsBoxUl.scrollLeft -= 100; // 왼쪽으로 스크롤, 값은 조정 가능
                updateIconVisibility();
            });

            iconRightDiv.addEventListener('click', () => {
                tabsBoxUl.scrollLeft += 100; // 오른쪽으로 스크롤, 값은 조정 가능
                updateIconVisibility();
            });

            // 최초 로딩 시 아이콘 가시성 업데이트
            updateIconVisibility();

            // 사용자가 수동으로 스크롤할 때 아이콘 가시성 업데이트
            tabsBoxUl.addEventListener('scroll', updateIconVisibility);
        }
    });
}


async function getTabListFromServer(storeId){
    try {
        console.log("getTabList");
        const resp = await fetch("/customer/tabList/"+storeId);

        return await resp.json();
    } catch (error) {
        console.log(error);
    }
}

function postItemList(storeId, tabName) {
    getItemListFromServer(storeId, tabName).then(result => {
        console.log(result);
        const listProduct = document.querySelector(".listProduct");

        if (result.length > 0) {
            listProduct.innerHTML = "";

            for (let i = 0; i < result.length; i++) {
                let menu = `
                <div class="menu-list" id="menu-list">
                    <div class="menu-img">
                        <img src="" alt="메뉴 사진">
                    </div>
                    <div class="menu-info">
                    <input type="hidden" class="menuId" value="${result[i].menuId}">
                        <div class="menu-name">${result[i].menuName}</div>
                        <input type="hidden" class="menuName" value="${result[i].menuName}">
                            <div class="menu-price">${result[i].menuPrice}</div>
                            <input type="hidden" class="menuPrice" value="${result[i].menuPrice}">
                    </div>
                </div>`;

                listProduct.innerHTML += menu;
            }

        } else {
            listProduct.innerHTML = `<div>목록이 비어있습니다.</div>`;
        }
    })
}

async function getItemListFromServer(storeId, tabName){
    try {
        console.log("getItemList");
        const resp = await fetch("/customer/itemList/"+storeId+"/"+tabName);

        return await resp.json();
    } catch (error) {
        console.log(error);
    }
}








document.addEventListener('DOMContentLoaded', function () {

    const addItem = document.getElementById("addItem");
    const listProduct = document.querySelector(".listProduct");
    let activeTab = document.querySelector(".Tab"); // Track the active tab
    let itemId = 0; // 항목 ID를 추적하기 위한 변수

    // addItem 이벤트 리스너
    addItem.addEventListener('click', () => {
        const item = document.createElement("div");
        item.className = "item";
        item.setAttribute('data-id', itemId++); // 각 항목에 고유 ID 부여
        item.innerHTML = `
        <div class="menu-list" id="menu-list">
            <div class="menu-img">
                <img src="" alt="/image/pig.png"/>
            </div>
            <div class="menu-info">
                <div class="menu-name">메뉴이름</div>
                <div class="menu-price">메뉴가격</div>
            </div>
        </div>
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
        < div

        class

        = "Tab" >
            < button

        class

        = "removeTab" > < span

        class

        = "material-icons-sharp" > remove < /span></
        button >
        < h3 > Tab < /h3>
    </div>
        `;

        // Remove 버튼에 이벤트 리스너 추가
        tab.querySelector('.removeTab').addEventListener('click', function () {
            sidebar.removeChild(tab);
        });

        tab.addEventListener('click', ()=>{
            if (activeTab) {
                activeTab.classList.remove('active');
            }
            tab.classList.add('active');
            activeTab = tab;
        })


        sidebar.appendChild(tab);

        if (activeTab) {
            activeTab.classList.remove('active');
        }
        tab.classList.add('active');
        activeTab = tab;
        document.getElementById('tabName').value = '';
    });

    document.getElementById('tabName').addEventListener('keyup', function() {
        const tabNameValue = this.value; // 입력 필드의 현재 값
        const activeTab = document.querySelector('.tab.active'); // 활성화된 탭 찾기

        if (activeTab) {
            const tabTitle = activeTab.querySelector('h3'); // 활성화된 탭의 h3 요소 찾기
            if (tabTitle) {
                tabTitle.textContent = tabNameValue; // h3 요소의 텍스트를 입력 필드의 값으로 설정
            }
        }
    });


});
document.addEventListener('click', (event) => {
    console.log(event);

    if (event.target.classList.contains("tab")) {
        let tabName = event.target.innerText;
        console.log(tabName);

        postItemList(storeId, tabName);
    }

});


function addMenu() {
    const formData = new FormData();
    const storeName = document.getElementById("storeName").value;
    const storeId = document.getElementById("storeId").value;
    const tabName = document.getElementById("tabName").value;
    const menuName = document.getElementById("modal-item-name").value; // 메뉴 이름
    const menuPrice = document.getElementById("modal-item-price").value; // 메뉴 가격
    const imageFile = document.getElementById("modal-item-image-upload").files[0]; // 이미지 파일 가져오기

    // 상점 이름, ID, 탭 이름, 메뉴 이름, 메뉴 가격, 이미지 파일을 formData에 추가
    formData.append('storeName', storeName);
    formData.append('storeId', storeId);
    formData.append('tabName', tabName);
    formData.append('menuName', menuName); // 메뉴 이름 추가
    formData.append('menuPrice', menuPrice); // 메뉴 가격 추가
    if (imageFile) {
        formData.append('image', imageFile); // 이미지 파일이 있을 경우만 추가
    }



    // 서버로 formData 전송
    fetch('/store/addMenu', {
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
document.getElementById('modal-save').addEventListener('click', function() {
    addMenu();
});
document.getElementById('modal-close').addEventListener('click', function() {
    closeModal();
});



