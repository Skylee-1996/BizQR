document.addEventListener("DOMContentLoaded", function() {
    const qrInput = document.querySelector(".form input"),
        generateBtn = document.querySelector(".form button"),
        qrImg = document.querySelector(".qr-code img"),
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
        const storeId = storeIdInput.value.trim();
        const tableNum = tableNumInput.value.trim();
        let qrValue = qrInput.value.trim();
        if(!qrValue || preValue === qrValue) return;
        preValue = qrValue;
        generateBtn.innerText = "Generating QR Code...";
        qrImg.src = `https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=${encodeURIComponent(qrValue)}`;
        qrImg.onload = () => {
            document.querySelector(".qr").classList.add("active");
            generateBtn.innerText = "Generate QR Code";
        };

        if(tableNumInput === ''){
            alert("테이블 개수를 입력해주세요.");
            tableNumInput.focus();
            return false;
        } else {
            insertTableNum(storeId,tableNum).then(result=>{
                if(result === '1'){
                    alert("테이블이 등록되었습니다.");
                }
            })

        }
    })

    async function insertTableNum(storeId,tableNum) {
        try {
            const resp = await fetch('/store/insertTable/' + storeId + "/" + tableNum);
            return await resp.json();
        } catch (error) {
            console.log(error);
        }

    }

    qrInput.addEventListener("keyup", () => {
        if(!qrInput.value.trim()) {
            document.querySelector(".container").classList.remove("active");
            preValue = "";
        }
    });

    });

