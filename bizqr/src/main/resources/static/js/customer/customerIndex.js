// async function addList() {
//     try {
//         // 서버로부터 메뉴 데이터를 비동기적으로 가져옵니다.
//         const response = await fetch('/customer/customerIndex');
//         const menus = await response.text();
//
//         // 메뉴 데이터를 기반으로 HTML을 생성합니다.
//         const div = document.getElementById("menu-container");
//         const menuHtml = `
//             <div class="menu-list" id="menu-list">
//                 <div class="menu-img">
//                     <img src="" alt="메뉴 사진">
//                 </div>
//                 <div class="menu-info">
//                     <div class="menu-name">돼지갈비찜</div>
//                     <input type="hidden" class="itemName" name="itemName" value="1">
//                     <div class="menu-price">25000</div>
//                     <input type="hidden" class="itemPrice" name="itemPrice" value="1">
//                 </div>
//             </div>
//             `;
//         div.innerHTML += menuHtml;
//
//     } catch (error) {
//         console.error('메뉴를 불러오는데 실패했습니다.', error);
//     }
// }


// 리스트 추가 버튼에 이벤트 리스너 추가
// document.getElementById("listAdd").addEventListener('click', () => {
//     addList();
// });

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

    if(menuList){
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

// 탭 js
const tabsBox = document.querySelector(".tabs-box"),
    allTabs = tabsBox.querySelectorAll(".tab"),
    arrowIcons = document.querySelectorAll(".icon i");
let isDragging = false;
const handleIcons = (scrollVal) => {
    let maxScrollableWidth = tabsBox.scrollWidth - tabsBox.clientWidth;
    arrowIcons[0].parentElement.style.display = scrollVal <= 0 ? "none" : "flex";
    arrowIcons[1].parentElement.style.display = maxScrollableWidth - scrollVal <= 1 ? "none" : "flex";
}
arrowIcons.forEach(icon => {
    icon.addEventListener("click", () => {
        // if clicked icon is left, reduce 350 from tabsBox scrollLeft else add
        let scrollWidth = tabsBox.scrollLeft += icon.id === "left" ? -340 : 340;
        handleIcons(scrollWidth);
    });
});
allTabs.forEach(tab => {
    tab.addEventListener("click", () => {
        tabsBox.querySelector(".active").classList.remove("active");
        tab.classList.add("active");
    });
});
const dragging = (e) => {
    if (!isDragging) return;
    tabsBox.classList.add("dragging");
    tabsBox.scrollLeft -= e.movementX;
    handleIcons(tabsBox.scrollLeft)
}
const dragStop = () => {
    isDragging = false;
    tabsBox.classList.remove("dragging");
}
tabsBox.addEventListener("mousedown", () => isDragging = true);
tabsBox.addEventListener("mousemove", dragging);
document.addEventListener("mouseup", dragStop);