console.log("customerCalling.js in");

document.getElementById("request-bar").addEventListener("input", ()=>{
    let limit = document.getElementById("request-bar").value.length;

    if(limit <= 30){
        document.getElementById("request-limit").innerText = `(${limit}/30)`;
    }
})

document.getElementById("order-shop").addEventListener('click', ()=>{
    document.getElementById("orderForm").submit();
})