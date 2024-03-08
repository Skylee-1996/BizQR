console.log("customerOrderHistory.js in");
const tableId = document.getElementById("tableId").value;
const storeId = document.getElementById("storeId").value;
async function getOrderHistoryFromServer(storeId, tableId){
    const resp = await fetch("/customer/getOrderHistory/"+storeId+"/"+tableId);

    return await resp.json();
}

function postOrderHistoryToClient(storeId, tableId) {
    getOrderHistoryFromServer(storeId, tableId).then(result => {
        let divContent = ""; // div 내용을 담을 변수 초기화
        if (result.length > 0) {
            console.log(result);

            for (let order = 0; order < result.length; order++) {
                console.log(`${result[order].ohlist}`);

                divContent += `<div class="accordion-item">`;
                divContent += `<h2 class="accordion-header" id="heading${order}">`;
                divContent += `<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${order}" aria-expanded="false" aria-controls="flush-collapse${order}">`;
                for (let i = 0; i < result[order].ohlist.length; i++) {
                    if (i.toLocaleString() === `${result[order].ohlist.length - 1}`) {
                        divContent += `${result[order].ohlist[i].orderId}`;
                    }
                }
                divContent += `</button>`;
                divContent += `</h2>`;
                divContent += `<div id="flush-collapse${order}" class="accordion-collapse collapse" aria-labelledby="heading${order}" data-bs-parent="#accordionFlushExample">`;
                divContent += `<div class="accordion-body">`;
                if(result[order].ohlist.length > 0) {
                    for (let i = 0; i < result[order].ohlist.length; i++) {
                        divContent += `메뉴 이름 = "${result[order].ohlist[i].menuName}"<br>`; // <br>을 사용하여 줄 바꿈
                        divContent += `메뉴 수량 = "${result[order].ohlist[i].menuAmount}"<br>`;
                        divContent += `메뉴 단가 = "${result[order].ohlist[i].menuPrice}"<br>`;
                        if (i.toLocaleString() === `${result[order].ohlist.length - 1}`) {
                            divContent += `총 가격 = "${result[order].ohlist[i].totalPrice}"<br>`;
                            divContent += `고객 요청 = "${result[order].ohlist[i].userRequest}"<br>`;
                            divContent += `주문 상태 = ${result[order].ohlist[i].orderStatus === 1 ? "주문완료" : "대기중"}`;
                        }
                    }
                }
                divContent += `</div>`;
                divContent += `</div>`;
                divContent += `</div>`;
            }
        } else {
            divContent = "주문 내역이 없습니다.";
        }
    // 실제 DOM 업데이트
    document.getElementById("accordionFlushExample").innerHTML = divContent;
    });
}

postOrderHistoryToClient(storeId, tableId);