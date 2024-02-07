document.addEventListener("DOMContentLoaded", function() {
    let fileInput = document.getElementById("file-input"); // 파일 입력 요소 가져오기
    let fileList = document.getElementById("files-list"); // 파일 목록 요소 가져오기
    let numOfFiles = document.getElementById("num-of-files"); // 선택된 파일 개수 요소 가져오기

    fileInput.addEventListener("change", () => {
        fileList.innerHTML = ""; // 파일 목록 비우기
        numOfFiles.textContent = `${fileInput.files.length}개의 파일 선택됨`; // 선택된 파일 개수 업데이트

        for (let i of fileInput.files) {
            let reader = new FileReader(); // 파일을 읽을 FileReader 객체 생성
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
});

/* text edit */
function editorLoding(title, contents) {
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: document.getElementById('contents'), // Textarea ID where the HTML editor will enter.
        sSkinURI: "/css/smarteditor2-master/workspace/package/SmartEditor2Skin.html", // HTML editor is the skin URL.
        htParams: {
            // Whether to use toolbar (true: use/ false: do not use)
            bUseToolbar: true,
            // Whether to use the input window size adjustment bar (true: used / false: not used)
            bUseVerticalResizer: true,
            // Whether to use the mode tab (Editor | HTML | TEXT) (true: used/ false: not used)
            bUseModeChanger: true,
            fOnBeforeUnload: function () {

            }
        },

        /**
         * Save data in editor when editing
         */
        fOnAppLoad: function () {
            //This part will be used when implementing edit mode. Implement the value to be filled in after loading is complete.
            oEditors.getById["contents"].exec("PASTE_HTML", [contents]); //After loading, insert contents into txtContent.
        },

        fCreator: "createSEditor2"
    });
}