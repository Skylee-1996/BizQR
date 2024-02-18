console.log("admin index in~~!!");

const sideMenu = document.querySelector('aside');
const menuBtn = document.getElementById('menu-btn');
const closeBtn = document.getElementById('close-btn');

const darkMode = document.querySelector('.dark-mode');

menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
});

closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
});

darkMode.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode-variables');
    darkMode.querySelector('span:nth-child(1)').classList.toggle('active');
    darkMode.querySelector('span:nth-child(2)').classList.toggle('active');
})

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.app, .ref').forEach(button => {
        button.addEventListener('click', async function() {
            const registerNum = button.dataset.rnum;
            console.log(registerNum);
            const isRegistered = button.value;

            try {
                const response = await fetch(`/admin/approve`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ registerNum: Number(registerNum), isRegistered })
                });

                if (!response.ok) {
                    throw new Error('서버 응답이 실패했습니다.');
                }

                const data = await response.json();
                console.log(data);

                if (data == "1") {
                    // 성공적으로 처리된 경우
                    const td = button.closest('td'); // 클릭된 버튼이 포함된 td 요소를 찾음
                    if (isRegistered === '1') {
                        td.innerHTML = '승인완료'; // 승인 버튼 클릭 시 '승인완료' 텍스트로 변경
                    } else if (isRegistered === '2') {
                        td.innerHTML = '승인거절'; // 거절 버튼 클릭 시 '승인거절' 텍스트로 변경
                    }

                } else {
                    // 처리에 실패한 경우
                    alert('처리에 실패했습니다.');
                }
            } catch (error) {
                console.error(error);
                alert('오류가 발생했습니다.');
            }
        });
    });
});









