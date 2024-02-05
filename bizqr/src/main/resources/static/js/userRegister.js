console.log("user register in~~!!");

let checkEmailSuccess = 0;
let checkPwdSuccess = 0;

document.getElementById('checkEmail').addEventListener('click', ()=>{
    let email = document.getElementById('e');
    let checkEmail = email.value;
    console.log(checkEmail);

    let emailText = document.getElementById('emailText');
    if(checkEmail == null || checkEmail == ''){
        emailText.style.color = 'red';
        emailText.innerText = "이메일을 입력해주세요.";
        checkSuccess();
    }

    checkEmailToServer(checkEmail).then(result => {
        if(result == '0'){
            emailText.style.color = 'red';
            emailText.innerText = "중복된 이메일입니다.";
            checkEmailSuccess = 0;
            checkSuccess();
        }else if(result == '1'){
            emailText.style.color = 'green';
            emailText.innerText = "등록 가능한 이메일입니다.";
            checkEmailSuccess = 1;
            checkSuccess();
        }
    })

})

document.getElementById('p1').addEventListener('keyup', () => {
    checkPwd();
    checkSuccess();
})

document.getElementById('p2').addEventListener('keyup', () => {
    checkPwd();
    checkSuccess();
})

function checkPwd(){
    let pwdOne = document.getElementById('p1').value;
    let pwdTwo = document.getElementById('p2').value;

    let pwdText = document.getElementById('pwdText');
    if(pwdOne != pwdTwo){
        pwdText.style.color = 'red';
        pwdText.innerText = "비밀번호가 다릅니다.";
        checkPwdSuccess = 0;
    }else{
        pwdText.style.color = 'green';
        pwdText.innerText = "비밀번호가 같습니다.";
        checkPwdSuccess = 1;
    }
}

async function checkEmailToServer(email){
    try {
      const resp = await fetch("/member/checkEmail/" + email);
      const result = await resp.text();
      return result;
    } catch (error){
        console.log(error);
    }
}

function  checkSuccess(){
    let signUp = document.getElementById('signUp');
    if(checkEmailSuccess == 1 && checkPwdSuccess == 1){
        signUp.disabled = false;
    }else{
        signUp.disabled = true;
    }
}