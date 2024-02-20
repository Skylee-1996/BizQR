console.log("customerBasket.js in");

document.getElementById("add-menu").addEventListener('click', ()=> {
    location.href = "/customer/customerIndex";
})

document.addEventListener('click', (e)=>{
    const shopTab = document.getElementById("shop-tab");
    const packingTab = document.getElementById("packing-tab");
    const deliverTab = document.getElementById("deliver-tab");

    const orderShop = document.getElementById("order-shop");
    const orderPacking = document.getElementById("order-packing");
    const orderDeliver = document.getElementById("order-deliver");

    if(e.target.id === "shop-tab"){
        shopTab.style.borderTop = "solid 2px #9595c3";
        shopTab.style.borderRight = "solid 2px #9595c3";
        shopTab.style.borderBottom = "none";
        shopTab.style.backgroundColor = "white";

        packingTab.style.borderTop = "solid 2px #8a8a8c";
        packingTab.style.borderBottom = "solid 2px #9595c3";
        packingTab.style.borderRight = "none";
        packingTab.style.borderLeft = "none";
        packingTab.style.backgroundColor = "#e7e7e7";

        deliverTab.style.borderLeft = "solid 2px #c3c3c5";
        deliverTab.style.borderBottom = "solid 2px #9595c3";
        deliverTab.style.borderTop = "solid 2px #8a8a8c";
        deliverTab.style.backgroundColor = "#e7e7e7";

        orderDeliver.style.display = "none";
        orderPacking.style.display = "none";
        orderShop.style.display = "block";

    }else if(e.target.id === "packing-tab"){
        packingTab.style.borderTop = "solid 2px #9595c3";
        packingTab.style.borderLeft = "solid 2px #9595c3";
        packingTab.style.borderRight = "solid 2px #9595c3";
        packingTab.style.borderBottom = "none";
        packingTab.style.backgroundColor = "white";

        shopTab.style.borderTop = "solid 2px #8a8a8c";
        shopTab.style.borderBottom = "solid 2px #9595c3";
        shopTab.style.borderRight = "none";
        shopTab.style.backgroundColor = "#e7e7e7";

        deliverTab.style.borderLeft = "none";
        deliverTab.style.borderBottom = "solid 2px #9595c3";
        deliverTab.style.borderTop = "solid 2px #8a8a8c";
        deliverTab.style.backgroundColor = "#e7e7e7";

        orderDeliver.style.display = "none";
        orderPacking.style.display = "block";
        orderShop.style.display = "none";

    }else if(e.target.id === "deliver-tab"){
        deliverTab.style.borderTop = "solid 2px #9595c3";
        deliverTab.style.borderLeft = "solid 2px #9595c3";
        deliverTab.style.borderBottom = "none";
        deliverTab.style.backgroundColor = "white";

        shopTab.style.backgroundColor = "#e7e7e7";
        shopTab.style.borderBottom = "solid 2px #9595c3";
        shopTab.style.borderTop = "solid 2px #8a8a8c";
        shopTab.style.borderRight = "solid 2px #c3c3c5";

        packingTab.style.backgroundColor = "#e7e7e7";
        packingTab.style.borderTop = "solid 2px #8a8a8c";
        packingTab.style.borderBottom = "solid 2px #9595c3";
        packingTab.style.borderLeft = "none";
        packingTab.style.borderRight = "none";

        orderDeliver.style.display = "block";
        orderPacking.style.display = "none";
        orderShop.style.display = "none";
    }

    if(e.target.closest("button").classList.contains("minus")){
        let menuAmount = e.target.closest(".amount-info").querySelector(".menu-amount").value

        const basket = e.target.closest("button").parentElement.parentElement;    //바구니 하나
        const subTotal = basket.querySelector(".sub-total");    //소계
        const basketPrice = basket.querySelector(".basket-info").querySelector(".basket-price");    //메뉴 가격

        const mainTotal = document.querySelector(".order-amount");    //합계가격
        let totalPrice = 0;

        if(menuAmount > 1){
            menuAmount--;
        }

        console.log(menuAmount);

        e.target.closest(".amount-info").querySelector(".menu-amount").value = menuAmount;  //수량 조정

        //.toLocalString은 1000단위로 콤마(,)를 붙혀서 String 형태로 변환해준다
        subTotal.querySelector(".item-price").innerText = (menuAmount * parseInt(basketPrice.innerText)).toLocaleString(); //소계 div의 innerText
        subTotal.querySelector(".itemPrice").value = menuAmount * parseInt(basketPrice.innerText);      //소계 input의 value

        document.querySelectorAll(".itemPrice").forEach(value => {
            console.log(value);
            totalPrice += parseInt(value.value);
        });

        mainTotal.querySelector(".total-price").innerText = totalPrice.toLocaleString();
        mainTotal.querySelector(".totalPrice").value = totalPrice;
    }else if(e.target.closest("button").classList.contains("plus")){
        let menuAmount = e.target.closest(".amount-info").querySelector(".menu-amount").value

        const basket = e.target.closest("button").parentElement.parentElement;    //basket
        const subTotal = basket.querySelector(".sub-total");
        const basketPrice = basket.querySelector(".basket-info").querySelector(".basket-price");

        const mainTotal = document.querySelector(".order-amount");    //합계가격
        let totalPrice = 0;

        menuAmount++;

        console.log(menuAmount);

        e.target.closest(".amount-info").querySelector(".menu-amount").value = menuAmount;

        subTotal.querySelector(".item-price").innerText = (menuAmount * parseInt(basketPrice.innerText)).toLocaleString(); //소계 div의 innerText
        subTotal.querySelector(".itemPrice").value = menuAmount * parseInt(basketPrice.innerText);      //소계 input의 value

        document.querySelectorAll(".itemPrice").forEach(value => {
            console.log(value);
            totalPrice += parseInt(value.value);
        });

        mainTotal.querySelector(".total-price").innerText = totalPrice.toLocaleString();
        mainTotal.querySelector(".totalPrice").value = totalPrice;
    }else if(e.target.closest("button").classList.contains("basket-del")){
        console.log("basket del");
    }

})

document.getElementById("order-shop").addEventListener('click', ()=>{
    document.getElementById("orderForm").submit();
})

document.getElementById("request-bar").addEventListener("input", ()=>{
    let limit = document.getElementById("request-bar").value.length;

    if(limit <= 30){
        document.getElementById("request-limit").innerText = `(${limit}/30)`;
    }
})


