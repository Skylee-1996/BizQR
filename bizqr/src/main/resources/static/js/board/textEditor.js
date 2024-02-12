console.log("hi editor js")
const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType:"wysiwyg",
    hideModeSwitch: true,
    initialValue: content,
    previewStyle: 'vertical',
    placeholder: '내용을 입력해주세요.',
    language:"ko-KR",
    hooks: {
        addImageBlobHook: async (blob, callback) => {
            try {
                const formData = new FormData();
                formData.append('image', blob);

                const response = await fetch('/tui-editor/image-upload', {
                    method: 'POST',
                    body: formData
                });
                const filename = await response.text();
                console.log('서버에 저장된 파일명: ', filename);

                const imageUrl = `/tui-editor/image-print?filename=${filename}`;
                callback(imageUrl, 'image alt attribute');
            } catch (error) {
                console.log('업로드 실패...', error);
            }
        }
    }
});

async function savePost(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 방지

    const title = document.getElementById("title").value;
    const email = document.getElementById("email").value;
    const nickName = document.getElementById("nickName").value;
    const content = editor.getMarkdown();

    const postData = {
        title: title,
        email: email,
        nickName: nickName,
        content: content
    };

    try {
        const response = await fetch('/board/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });

        if(response.ok) {
            const result = await response.text();
            alert("게시글이 저장 되었습니다.");
            window.location.href = '/board/list';
        } else {
            // 오류 처리
            console.error('서버 오류:', response.statusText);
        }
    } catch (error) {
        console.error('저장 실패:', error);
    }
}
