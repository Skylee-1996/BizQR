document.addEventListener("DOMContentLoaded", function() {
    let fileInput = document.getElementById("files"); // 파일 입력 요소 가져오기
    let fileList = document.getElementById("files-list"); // 파일 목록 요소 가져오기
    let numOfFiles = document.getElementById("num-of-files"); // 선택된 파일 개수 요소 가져오기

    fileInput.addEventListener("change", () => {
        fileList.innerHTML = "";
        numOfFiles.textContent = `${fileInput.files.length} Files Selected`; // 선택된 파일 개수 업데이트

        for (let i of fileInput.files) {
            let reader = new FileReader();
            let listItem = document.createElement("li"); // 새로운 목록 항목 생성
            let fileName = i.name; // 파일 이름
            let fileSize = (i.size / 1024).toFixed(1); // 파일 크기 (KB 단위)

            // 파일 크기가 1024KB(1MB) 이상인 경우 MB 단위로 변환하여 표시
            if (fileSize >= 1024) {
                fileSize = (fileSize / 1024).toFixed(1); // 파일 크기 (MB 단위)
                listItem.innerHTML = `<p>${fileName}</p><p>${fileSize}MB</p>`; // 파일 정보 설정
            } else {
                listItem.innerHTML = `<p>${fileName}</p><p>${fileSize}KB</p>`; // 파일 정보 설정
            }

            fileList.appendChild(listItem); // 목록에 항목 추가
        }
    });

    document.getElementById('trigger').addEventListener('click', ()=>{
        document.getElementById('files').click(); // 파일 업로드 버튼 클릭 시 파일 입력란 클릭 이벤트 발생
    });

    const regExp = /\.(exe|sh|bat|js|dll|msi)$/; // 실행파일 막기
    const maxSize = 1024*1024*20; // 최대 파일 크기 (20MB)

    function fileValidation(fileName, fileSize){
        if(regExp.test(fileName) || fileSize > maxSize){
            return 0; // 업로드 불가능
        } else {
            return 1; // 업로드 가능
        }
    }

    document.addEventListener('change',(e)=>{
        if(e.target.id == 'files'){
            const fileObject = document.getElementById('files').files; // 선택된 파일 객체 가져오기
            document.getElementById('regBtn').disabled = false; // 등록 버튼 활성화

            const div = document.getElementById('fileZone'); // 파일 목록을 표시할 요소
            div.innerHTML = ''; // 파일 목록 비우기

            let ul = `<ul>`; // 목록 생성 시작

            let isOk = 1;
            for(let file of fileObject){
                let validResult = fileValidation(file.name, file.size);
                isOk *= validResult;
                ul += `<li>`;
                ul += `${file.name}`;
                ul += `<span>${file.size}Byte</span>`;
                ul += `</li>`;
            }
            ul += `</ul>`;
            div.innerHTML = ul;

            if(isOk == 0){ document.getElementById('regBtn').disabled = true; } } }); });