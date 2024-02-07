document.getElementById('modBtn').addEventListener('click',()=>{
    document.getElementById('title').readOnly = false;
    document.getElementById('content').readOnly = false;

    let fileList = document.querySelectorAll('#fileList li');
    console.log(fileList);
    fileList.forEach(fileItem => {
        let deleteFileBtn = document.createElement('button');
        deleteFileBtn.setAttribute('type', 'button');
        deleteFileBtn.classList.add('btn');
        deleteFileBtn.innerText = 'X';
        console.log(fileItem.getAttribute("data-uuid"));
        deleteFileBtn.setAttribute("data-uuid", fileItem.getAttribute("data-uuid"));
        fileItem.appendChild(deleteFileBtn);

        async function removeFileToServer(uuid){
            try {
                let url = "/board/file/"+uuid;
                let config = {
                    method:"delete"
                }
                const resp= await fetch(url,config);
                const result = await resp.text();
                return result;

            } catch (error) {
                console.log(error);
            }
        }

        deleteFileBtn.addEventListener('click', async () => {
            let uuid = fileItem.dataset.uuid;
            console.log(uuid);

            try {
                const result = await removeFileToServer(uuid);
                if (result === "1") {
                    fileItem.remove();
                    alert("파일 삭제 성공");
                } else {
                    alert("파일 삭제 실패");
                }
            } catch (error) {
                console.log(error);
                alert("파일 삭제 시도 중 에러 발생!");
            }
        });
    });

    let modSubmitBtn = document.createElement('button');
    modSubmitBtn.setAttribute('type', 'submit');
    modSubmitBtn.classList.add('btn');
    modSubmitBtn.innerText = "Submit";
    document.getElementById('modForm').appendChild(modSubmitBtn);

    // Remove the existing modBtn and delBtn
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();
});