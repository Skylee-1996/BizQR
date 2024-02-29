console.log("customerBasket.js in");

const tableId = document.getElementById("tableId").value;
const storeId = document.getElementById("storeId").value;

document.getElementById("add-menu").addEventListener('click', ()=> {
    location.href = "/customer/"+storeId+"/"+tableId;
})

async function menuAmountToServer(itemData){   //menuId로 바꿀 예정
    const url = "/customer/menuAmount";
    const config={
        method: "POST",
        headers:{
            'content-type':'application/json'
        },
        body: JSON.stringify(itemData)
    };
    const resp = await fetch(url, config);

    return resp.json();
}

document.addEventListener('click', (e)=>{


    const basket = e.target.closest("button").parentElement.parentElement;    //바구니 하나
    const menuName = basket.querySelector(".basket-name").innerText;  //메뉴 이름   menuId로 바꿀 예정
    const subTotal = basket.querySelector(".sub-total");    //소계
    const basketPrice = basket.querySelector(".basket-info").querySelector(".basket-price");    //메뉴 가격

    const mainTotal = document.querySelector(".order-amount");    //합계가격



    if(e.target.closest("button").classList.contains("minus")){
        let menuAmount = e.target.closest(".amount-info").querySelector(".menu-amount").value;
        let totalPrice = 0;

        if(menuAmount > 1){
            menuAmount--;
        }

        console.log(menuAmount);

        e.target.closest(".amount-info").querySelector(".menu-amount").value = menuAmount;  //수량 조정

        let itemData = {
            storeId: storeId,
            tableId: tableId,
            menuName: menuName,
            menuAmount: menuAmount
        }

        try {
            menuAmountToServer(itemData).then(result => {
                console.log("성공", result);
            }).catch(error => {
                console.error(error); // fetch 실패 시 오류 처리
            });

        } catch (error) {
            console.log(error); // menuAmountToServer 함수 실행 중 발생하는 오류 처리
        }


        //.toLocalString은 1000단위로 콤마(,)를 붙혀서 String 형태로 변환해준다
        subTotal.querySelector(".item-price").innerText = (menuAmount * parseInt(basketPrice.innerText)).toLocaleString(); //소계 div의 innerText
        subTotal.querySelector(".itemPrice").value = menuAmount * parseInt(basketPrice.innerText);      //소계 input의 value

        document.querySelectorAll(".itemPrice").forEach(result => {
            console.log(result);
            totalPrice += parseInt(result.value);
        });

        mainTotal.querySelector(".total-price").innerText = totalPrice.toLocaleString();
        mainTotal.querySelector(".totalPrice").value = totalPrice;
    }else if(e.target.closest("button").classList.contains("plus")){
        let menuAmount = e.target.closest(".amount-info").querySelector(".menu-amount").value;
        let totalPrice = 0;

        menuAmount++;

        console.log(menuAmount);

        e.target.closest(".amount-info").querySelector(".menu-amount").value = menuAmount;

        let itemData = {
            storeId: storeId,
            tableId: tableId,
            menuName: menuName,
            menuAmount: menuAmount
        }

        try {
            menuAmountToServer(itemData).then(result => {
                console.log("성공", result);
            }).catch(error => {
                console.error(error); // fetch 실패 시 오류 처리
            });

        } catch (error) {
            console.log(error); // menuAmountToServer 함수 실행 중 발생하는 오류 처리
        }


        subTotal.querySelector(".item-price").innerText = (menuAmount * parseInt(basketPrice.innerText)).toLocaleString(); //소계 div의 innerText
        subTotal.querySelector(".itemPrice").value = menuAmount * parseInt(basketPrice.innerText);      //소계 input의 value

        document.querySelectorAll(".itemPrice").forEach(result => {
            console.log(result);
            totalPrice += parseInt(result.value);
        });

        mainTotal.querySelector(".total-price").innerText = totalPrice.toLocaleString();
        mainTotal.querySelector(".totalPrice").value = totalPrice;
    }else if(e.target.closest("button").classList.contains("basket-del")){
        console.log("basket del");
        const deleteBtn = e.target.closest("button");

        const menuId = deleteBtn.getAttribute('data-menuId');

        basketDeleteFromServer(menuId, tableId, storeId);
        basket.remove();
    }

})

async function basketDeleteFromServer(menuId, tableId, storeId){
    console.log(menuId);
    console.log(tableId);
    console.log(storeId);

    try {
        const url = '/customer/basketDel/'+menuId+"/"+tableId+"/"+storeId;
        const config={
            method:'delete'
        };
        const resp = await fetch(url, config);
        return resp.text();
    } catch (error) {
        console.log(error);
    }
}

document.getElementById("order-shop").addEventListener('click', ()=>{
    document.getElementById("orderForm").submit();
})

document.getElementById("request-bar").addEventListener("input", ()=>{
    let limit = document.getElementById("request-bar").value.length;

    if(limit <= 30){
        document.getElementById("request-limit").innerText = `(${limit}/30)`;
    }
})


