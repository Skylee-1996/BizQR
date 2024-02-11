async function addList() {
    try {
        // 서버로부터 메뉴 데이터를 비동기적으로 가져옵니다.
        const response = await fetch('/customer/customerIndex');
        const menus = await response.text();

        // 메뉴 데이터를 기반으로 HTML을 생성합니다.
        const div = document.getElementById("menu-container");
        const menuHtml = `
                <div class="menu-list" onclick="openModal()">
                    <div class="menu-img">
                        <img src="" alt="메뉴 사진">
                    </div>
                    <div class="menu-info">
                        <div class="menu-name">돼지갈비찜</div>
                        <input type="hidden" name="itemName" value="돼지갈비찜">
                        <div class="menu-price">25000</div>
                        <input type="hidden" name="itemPrice" value="25000">
                    </div>
                </div>
            `;
        div.innerHTML += menuHtml;
    } catch (error) {
        console.error('메뉴를 불러오는데 실패했습니다.', error);
    }
}




// 리스트 추가 버튼에 이벤트 리스너 추가
document.getElementById("listAdd").addEventListener('click', () => {
    addList();
});

// 모달 열기 함수
function openModal() {
    document.getElementById('modal').style.display = 'block';
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

document.getElementById("menu-list").addEventListener('click', (event) => {
    let div = event.target.closest('input');
    console.log(div);

    let itemName = div.querySelector('.menu-name');
    console.log(itemName);

    // <div className="modal-menu-img">
    //     <img src="" alt="메뉴 사진"/>
    // </div>
    // <div className="modal-menu-info">
    //     <div className="modal-menu-name">품명</div>
    //     <input type="hidden" name="itemName" value="hi"/>
    //
    //     <div className="modal-menu-price">가격</div>
    //     <input type="hidden" name="itemPrice" value="25000"/>
    // </div>
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