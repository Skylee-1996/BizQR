const OrderStoreId = document.getElementById("storeId").value;
const CustomerTableId = document.getElementById("tableId").value;
let FirstTabName;
console.log(OrderStoreId);
console.log(CustomerTableId);

async function getMenuPriceFromServer(storeId, tableId){
    try {
        console.log("getMenuPrice");
        const resp = await fetch("/customer/getMenuPrice/"+storeId+"/"+tableId);

        return await resp.text();
    }catch (e) {
        console.log(e);
    }
}

function menuPriceToClient(storeId, tableId){
    getMenuPriceFromServer(storeId, tableId).then(result => {
        console.log(result);
        document.getElementById("menuPrice").innerText = result.toString();
    })
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

                    postItemList(OrderStoreId, FirstTabName); // 첫 번째 탭에 대한 아이템 리스트 요청
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

async function getBasketCountFromServer(storeId, tableId){
    try {
        console.log("getBasketCount");
        const resp = await fetch("/customer/basketCount/"+storeId+"/"+tableId);

        return await resp.json();
    } catch (error) {
        console.log(error);
    }
}

function postBasketCount(storeId, tableId){
    getBasketCountFromServer(storeId, tableId).then(result => {
        document.getElementById("my-select").innerText = result.toString();
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

function postItemList(storeId, tabName) {
    getItemListFromServer(storeId, tabName).then(result => {
        console.log(result);
        const article = document.getElementById("menu-container");

        if (result.length > 0) {
            article.innerHTML = '';

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

                article.innerHTML += menu;
            }

        } else {
            article.innerHTML = `<div>목록이 비어있습니다.</div>`;
        }
    })
}

// DOMContentLoaded 이벤트 리스너에서 postTabList 호출
document.addEventListener('DOMContentLoaded', function() {
    postTabList(OrderStoreId); // 이 함수 내에서 FirstTabName이 설정되고, 그 후 postItemList 호출
    postBasketCount(OrderStoreId, CustomerTableId);
    // postItemList(OrderStoreId, FirstTabName); 이 부분을 삭제하고, postTabList 안에서 호출
    menuPriceToClient(OrderStoreId, CustomerTableId);
});

// 모달 열기 함수
function openModal(menuName, menuPrice, menuId) {
    document.getElementById('modal').style.display = 'block';

    console.log(menuName);
    console.log(menuPrice);

    document.getElementById("modal-menu-name").innerText = menuName;
    document.getElementById("modal-menu-price").innerText = menuPrice;
    document.getElementById("modalItemName").value = menuName;
    document.getElementById("modalItemPrice").value = menuPrice;

    document.getElementById("modalMenuId").value = menuId;
}

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

document.addEventListener('click', (event) => {
    let menuList = event.target.closest('.menu-list');

    if(event.target.classList.contains("tab")){
        let tabName = event.target.innerText;
        console.log(tabName);

        postItemList(OrderStoreId, tabName);
    }


    if (menuList) {
        let menuName = menuList.querySelector('.menuName').value;
        let menuPrice = menuList.querySelector('.menuPrice').value;
        let menuId = menuList.querySelector('.menuId').value;

        console.log(menuName);
        console.log(menuPrice);
        console.log(menuId);

        document.getElementsByClassName('.menu-list').onclick = openModal(menuName, menuPrice, menuId);
    }else if(event.target.closest("button").classList.contains("plus")){
        let menuAmount = document.getElementById("menuAmount").value;

        menuAmount++;

        document.getElementById("menuAmount").value = menuAmount;
    }else if(event.target.closest("button").classList.contains("minus")){
        let menuAmount = document.getElementById("menuAmount").value;

        if(menuAmount>0){
            menuAmount--;
        }

        document.getElementById("menuAmount").value = menuAmount;
    }

})


document.getElementById("submitDiv").addEventListener("click", () => {
    console.log("submitDiv");
    document.getElementById("submitBtn").click();
})
