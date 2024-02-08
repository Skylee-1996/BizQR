console.log("customerBasket.js in");

document.addEventListener('click', (e)=>{
    console.log(e.target.id);
    const shopTab = document.getElementById("shop-tab");
    const packingTab = document.getElementById("packing-tab");
    const deliverTab = document.getElementById("deliver-tab");

    if(e.target.id === "shop-tab"){
        shopTab.style.borderTop = "solid 2px #9595c3";
        shopTab.style.borderRight = "solid 2px #9595c3";
        shopTab.style.backgroundColor = "white";

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
        packingTab.style.borderRight = "none";

    }
})