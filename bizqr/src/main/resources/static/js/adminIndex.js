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
            const registerNum = button.dataset.id;
            const action = button.value;

            try {
                const response = await fetch(`/admin/approve/`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ registerNum, action })
                });

                if (!response.ok) {
                    throw new Error('서버 응답이 실패했습니다.');
                }

                const data = await response.json();

                if (data.success) {
                    // 성공적으로 처리된 경우
                    const row = button.closest('tr').querySelector('td:last-child');
                    row.textContent = action === '승인' ? '승인완료' : '승인거절';
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

// document.addEventListener('click', (e)=>{
//     if(e.target.classList.contains('app')){
//         // 수정
//         // 타겟에 가장 가까운 li찾기 : 내 번틍이 포함되어있는 li 찾기
//         let td = e.target.closest('td');
//         // nextSibling : 같은 부모의 다음 형제 객체를 반환
//         let cmtTeext = li.querySelector('.fw-bold').nextSibling;
//         console.log(cmtText);
//         // nodeValue : 현재 선택한 노드의 value 반환/
//         document.getElementById('cmtTextMod').value = cmtTeext.nodeValue;
//         document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
//     }else if(e.target.id=='cmtModBtn'){
//         // 모달 수정 버튼
//         let cmtDataMod={
//             registerNum: e.target.dataset.registerNum,
//             isRegistered: document.querySelector('.btn').value
//         };
//         //비동기 통신
//         editCommentToServer(cmtDataMod).then(result =>{
//             if(result == "1"){ // 수정 성공
//                 alert("댓글 수정 완료");
//                 //모달창을 닫기
//                 document.querySelector('.btn-close').click();
//             }else{
//                 alert("댓글 수정 실패");
//                 //모달창을 닫기
//                 document.querySelector('.btn-close').click();
//             }
//             //수정된 댓글 뿌리기 page=1
//             spreadCommentList(bnoVal);
//         })
//     }else if(e.target.classList.contains('del')){
//         // 삭제
//         let li = e.target.closest('li');
//         let cnoVal = li.dataset.cno;
//         removeCommentFromServer(cnoVal).then(result=>{
//             if(result==="1"){
//                 alert("댓글 삭제 성공");
//                 spreadCommentList(bnoVal);
//             }
//         })
//     }else if(e.target.id == 'moreBtn'){
//         spreadCommentList(bnoVal, parseInt(e.target.dataset.page));
//     }
// })
// async function editCommentToServer(cmtDataMod){
//     try {
//         const url = '/comment/edit';
//         const config={
//             method:'put',
//             headers:{
//                 'content-type' : 'application/json; charset=utf-8'
//             },
//             body: JSON.stringify(cmtDataMod)
//         };
//         const resp = await fetch(url, config);
//         const result = resp.text();
//         return result;
//     } catch (error) {
//         console.log(error);
//     }
// }








