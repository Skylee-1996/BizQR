<<<<<<< HEAD:bizqr/src/main/resources/static/js/boardRegister.js

=======
>>>>>>> origin/main:bizqr/src/main/resources/static/js/board/boardRegister.js
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js" />

document.addEventListener("DOMContentLoaded", function() {
    let fileInput = document.getElementById("file-input");
    fileInput.addEventListener("change", () => {
        for (let file of fileInput.files) {
            uploadImage(file);
        }
    });
});

const uploadImage = (file) => {
    let reader = new FileReader();
    reader.onload = function(e) {
        let imageData = e.target.result;

        editor.insertText(`![${file.name}](${imageData})\n`);
    };
    reader.readAsDataURL(file);
};

const handleEditorChange = async () => {
    const content = editor.getMarkdown();
    try {
        const response = await fetch('/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ content }),
        });
        if (response.ok) {
            console.log('에디터 내용이 저장되었습니다.');
        } else {
            console.error('에디터 내용 저장에 실패했습니다.');
        }
    } catch (error) {
        console.error('에디터 내용 저장 중 오류가 발생했습니다:', error);
    }
};

const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'markdown',
    previewStyle: 'vertical'
});

editor.on('change', handleEditorChange);




