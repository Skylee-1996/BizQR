console.log("boardComment.js in");
document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText == ''){
        alert("댓글을 입력해주세요");
        cmtText.focus();
        return false;
    } else {
        let cmtData={
            bno: bnoVal,
            nickName: document.getElementById('cmtWriter').innerText,
            content: cmtText.value
        };
        //전송
        console.log(nickName);
        postCommentToServer(cmtData).then(result=>{
            if(result === '1'){
                alert("댓글 등록 성공~!");
            }
            //뿌리기
            spreadCommentList(bnoVal);
        })
    }
});

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method: 'post',
            headers:{
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch('/comment/'+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
};

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result); //ph cmtList
        const ul = document.getElementById('cmtListArea'); //전체 태그
        if(result.cmtList.length > 0){
            if(page == 1){ //페이지가 1일때
                ul.innerHTML = ''; //원래 있던 댓글 내용 지우도록 설정
            }

            for (let cvo of result.cmtList) {
                let li = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.nickName}">`;
                li += `<div class="ms-2 me-auto">`;
                li += `<div class="fw-bold">${cvo.nickName}</div>`;
                li += `<div>${cvo.content}</div>`;
                li += `<div class="d-flex justify-content-between align-items-center">`;
                li += `<span class="badge bg-dark rounded-pill">${cvo.modAt}</span>`;
                li += `<div class="btn-group" role="group">`;
                li += `<button type="button" class="mod" id="mod">Edit</button>`;
                li += `<button type="button" class="del" id="del">Delete</button>`;
                li += `</div>`;
                li += `</div>`;
                li += `</div>`;
                li += `</li>`;
                ul.innerHTML += li;
            }


            //댓글에 대한 페이지 처리
            let moreBtn = document.getElementById('moreBtn');
            //현재 페이지 번호가 전체 페이지 번호보다 작다면...
            //아직 나와야할 페이지가 더 있다면...
            if(result.pgvo.pageNo < result.endPage){
                //숨김 속성 해지
                moreBtn.style.visibility = 'visible'; //표시
                //페이지 하나 올리기
                moreBtn.dataset.page = page+1;
            } else {
                moreBtn.style.visibility = 'hidden'; //표시 xx
            }
        } else {
            let li = `<li class="list-group-item">Comment List Empty</li>`;
            ul.innerHTML = li;
        }
    })
};

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('mod')){
        //수정
        let li = e.target.closest('li');
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        let newText = prompt("Enter your new comment:", cmtText.nodeValue);
        if (newText !== null) {
            let cmtDataMod = {
                cno: li.dataset.cno,
                content: newText
            };
            editCommentToServer(cmtDataMod).then(result=>{
                if(result == '1'){
                    alert("댓글 수정 완료!");
                    spreadCommentList(bnoVal);
                }
            })
        }
    } else if(e.target.classList.contains('del')){
        //댓글 삭제
        let li = e.target.closest('li');
        let cno = li.dataset.cno;
        let nickName = li.dataset.nickName;
        console.log(cno+' / '+nickName);
        deleteCommentToServer(cno, nickName).then(result=>{
            if(result == '1'){
                alert("댓글이 삭제되었습니다.");
            } else if(result == '0'){
                alert("댓글 삭제 실패...");
            }
            spreadCommentList(bnoVal);
        })
    } else if(e.target.id == 'moreBtn'){
        spreadCommentList(bnoVal, parseInt(e.target.dataset.page));
    }
});

//댓글 삭제
async function deleteCommentToServer(cno, nickName){
    try {
        const url = "/comment/del/"+cno+"/"+nickName;
        const config = {
            method: 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}


async function editCommentToServer(cmtDataMod){
    try {
        const url = "/comment/edit";
        const config = {
            method: 'put',
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
