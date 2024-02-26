console.log("customerLayout.js");

const url = window.location.href;

let checkUrl = url.substring(url.indexOf("/")+1, url.indexOf("/", 2));

console.log(checkUrl);

if(checkUrl === "customerIndex"){
    console.log("customerIndex in");

    document.getElementById("backBtn").style.display = "none";
}else{
    document.getElementById("company-name").style.margin = "0 13% 0 11%";
}