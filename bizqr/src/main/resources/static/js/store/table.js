document.addEventListener("DOMContentLoaded", function() {
    const qrInput = document.querySelector(".form input"),
        generateBtn = document.querySelector(".form button"),
        qrImg = document.querySelector(".qr-code img"),
        storeNameInput = document.getElementById("storeName"), // 사용되지 않지만 예시로 포함
        storeIdInput = document.getElementById("storeId"),
        tableNumInput = document.getElementById("tableNum");
    let preValue;

    // 입력 필드 값 변경 감지 및 qrInput에 URL 설정
    const updateQrInput = () => {
        const storeId = storeIdInput.value.trim();
        const tableNum = tableNumInput.value.trim();
        const url = `http://localhost:8090/customer/${storeId}/${tableNum}`;
        qrInput.value = url;
    };

    // storeId와 tableNum 입력 필드에 대한 이벤트 리스너 추가
    [storeIdInput, tableNumInput].forEach(input => {
        input.addEventListener("input", updateQrInput);
    });

    generateBtn.addEventListener("click", () => {
        let qrValue = qrInput.value.trim();
        if(!qrValue || preValue === qrValue) return;
        preValue = qrValue;
        generateBtn.innerText = "Generating QR Code...";
        qrImg.src = `https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=${encodeURIComponent(qrValue)}`;
        qrImg.onload = () => {
            document.querySelector(".container").classList.add("active");
            generateBtn.innerText = "Generate QR Code";
        };
    });

    qrInput.addEventListener("keyup", () => {
        if(!qrInput.value.trim()) {
            document.querySelector(".container").classList.remove("active");
            preValue = "";
        }
    });
});
