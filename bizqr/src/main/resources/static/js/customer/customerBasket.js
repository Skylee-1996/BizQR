console.log("customerBasket.js in");

document.addEventListener('click', (e)=>{
    console.log(e.target.id);
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
})