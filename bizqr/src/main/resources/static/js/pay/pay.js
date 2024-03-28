

var IMP = window.IMP;
IMP.init("imp88712442");

function requestPay() {
    let nameVal = document.getElementById('name').value;
    let emailVal = document.getElementById('email').value;
    let companyVal = document.getElementById('company').value;
    let storeNameVal = document.getElementById('storeName').value;
    let storeAddressVal = document.getElementById('storeAddress').value;
    let storeTypeVal = document.querySelector('input[name="storeType"]:checked').value;
    let ownerNumVal = document.getElementById('ownerNum').value;
    let storeNumVal = document.getElementById('storeNum').value;
    let subscribeVal = document.querySelector('input[name="subscribe"]:checked').value;
    let isRegisteredVal = 0;

    let currentDate = new Date();

// 년, 월, 일, 시, 분, 초를 추출
    let year = currentDate.getFullYear(); // 년
    let month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // 월 (0부터 시작하므로 +1, 두 자리로 표시)
    let day = ('0' + currentDate.getDate()).slice(-2); // 일 (두 자리로 표시)
    let hours = ('0' + currentDate.getHours()).slice(-2); // 시 (두 자리로 표시)
    let minutes = ('0' + currentDate.getMinutes()).slice(-2); // 분 (두 자리로 표시)
    let seconds = ('0' + currentDate.getSeconds()).slice(-2); // 초 (두 자리로 표시)

    let nowTime = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;

// 결과 출력
    console.log(nowTime);

    console.log(nameVal);
    console.log(emailVal);
    console.log(companyVal);
    console.log(storeNameVal);
    console.log(storeAddressVal);
    console.log(storeTypeVal);
    console.log(ownerNumVal);
    console.log(storeNumVal);
    console.log(subscribeVal);
    console.log(isRegisteredVal);

    let merchant_uid = "bizqr" + new Date().getTime();
    let itemNameVal = subscribeVal === 'monthly' ? '1개월' : '1년';
    let amountVal = subscribeVal === 'monthly' ? 100 : 1000;

    IMP.request_pay(
        {
            pg: "html5_inicis.INIpayTest",
            pay_method: "card",
            merchant_uid: merchant_uid,
            name: itemNameVal,
            amount: amountVal,
            buyer_email: emailVal,
            buyer_name: nameVal,
            buyer_tel: ownerNumVal,
            buyer_addr: storeAddressVal,
            buyer_postcode: "null",
        },
        function (rsp) {
            // callback
            //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.
            if(rsp.success){
                alert('결제 완료. 결제 내역' + rsp.imp_uid);

                let registerData = {
                    email: emailVal,
                    name: nameVal,
                    company: companyVal,
                    storeName: storeNameVal,
                    storeAddress: storeAddressVal,
                    storeType: storeTypeVal,
                    subscribe: subscribeVal,
                    ownerNum: ownerNumVal,
                    storeNum: storeNumVal,
                    paidTime: nowTime,
                    merchantUid: merchant_uid
                };

                postRegisterSuccess(registerData).then(result => {
                    // 신청 db 저장 성공시
                    // 결제 db 저장 시도
                    if(result === '1'){
                        let data = {
                            impUid: rsp.imp_uid,
                            merchantUid: merchant_uid,
                            buyerEmail: emailVal,
                            buyerName: nameVal,
                            buyerCompany: companyVal,
                            buyerAddress: storeAddressVal,
                            buyerOwnerTelNum: ownerNumVal,
                            buyerStoreTelNum: storeNumVal,
                            itemName: itemNameVal,
                            itemAmount: amountVal,
                            paidTime: nowTime
                        };
                        7
                        postStorePaySuccess(data);

                        window.location.href = '/';
                    }
                })

            }else{
                alert('실패' + rsp.error_msg);
            }
        }
    );
}

async function postRegisterSuccess(registerData){
    try {
        const url = "/store/registerSuccess";
        const config = {
            method: 'post',
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(registerData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    }catch (error){
        console.log(error);
    }
}

function postStorePaySuccess(data){
    try {
        const url = "/payment/storePay/success";
        const config = {
            method: 'post',
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(data)
        };

        fetch(url, config)
            .catch(error => {
                console.log("error");
            })
    }catch (error){
        console.log(error);
    }
}