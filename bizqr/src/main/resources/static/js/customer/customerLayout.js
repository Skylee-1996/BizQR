console.log("customerLayout.js");

// const url = window.location.href;
// let firstSlash = url.indexOf("/"+1); // 프로토콜 다음 첫 번째 `/` 위치를 찾습니다.
// let secondSlash = url.indexOf("/", firstSlash + 1); // 그 다음 `/` 위치를 찾습니다.
// let path = url.substring(firstSlash, secondSlash);
//
// console.log(path);
//
// if(path === "customerIndex"){
//     console.log("customerIndex in");
//
//     document.getElementById("backBtn").style.display = "none";
// }else{
//     document.getElementById("company-name").style.margin = "0 13% 0 11%";
// }

document.getElementById('slideBtn').addEventListener('click', function() {
    const slideDiv = document.getElementById('slideDiv');
    const overlay = document.getElementById('overlay');
    // 'slide-in' 클래스를 추가하고 오버레이 활성화
    slideDiv.classList.toggle('slide-in');
    overlay.classList.add('overlay-active');
});

document.getElementById('backBtn').addEventListener('click', function() {
    const slideDiv = document.getElementById('slideDiv');
    const overlay = document.getElementById('overlay');
    // 'slide-in' 클래스를 제거하고 오버레이 비활성화
    slideDiv.classList.remove('slide-in');
    overlay.classList.remove('overlay-active');
});

// 오버레이 클릭 시 패널과 오버레이 모두 비활성화
document.getElementById('overlay').addEventListener('click', function() {
    const slideDiv = document.getElementById('slideDiv');
    const overlay = document.getElementById('overlay');
    slideDiv.classList.remove('slide-in');
    overlay.classList.remove('overlay-active');
});