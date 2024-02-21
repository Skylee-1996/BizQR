const OrderStoreId = document.getElementById("storeId").value;
const CustomerTableId = document.getElementById("tableId").value;
console.log(OrderStoreId);
console.log(CustomerTableId);

async function getItemListFromServer(storeId, tabName, tableId){
    try {
        console.log("getItemList");
        const resp = await fetch("/customer/itemList/"+storeId+"/"+tabName+"/"+tableId);

        console.log(resp.json());

        return await resp.json();
    } catch (error) {
        console.log(error);
    }
}

function postItemList(storeId, tabName, tableId) {
    getItemListFromServer(storeId, tabName, tableId).then(result => {
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

document.addEventListener('DOMContentLoaded', function() {
    const tabName = "집";

    postItemList(OrderStoreId, tabName, CustomerTableId);
});

// 모달 열기 함수
function openModal(menuName, menuPrice) {
    document.getElementById('modal').style.display = 'block';

    console.log(menuName);
    console.log(menuPrice);

    document.getElementById("modal-menu-name").innerText = menuName;
    document.getElementById("modal-menu-price").innerText = menuPrice;
    document.getElementById("modalItemName").value = menuName;
    document.getElementById("modalItemPrice").value = menuPrice;
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

        postItemList(storeId, tabName, tableId);
    }


    if (menuList) {
        let menuName = menuList.querySelector('.menuName').value;
        let menuPrice = menuList.querySelector('.menuPrice').value;

        console.log(menuName);
        console.log(menuPrice);

        document.getElementsByClassName('.menu-list').onclick = openModal(menuName, menuPrice);
    }

})


document.getElementById("submitDiv").addEventListener("click", () => {
    console.log("submitDiv");
    document.getElementById("submitBtn").click();
})