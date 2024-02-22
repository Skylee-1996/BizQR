async function updatePost(event) {
    event.preventDefault();
    const bno = document.getElementById("bno").value;
    const title = document.getElementById("title").value;
    const email = document.getElementById("email").value;
    const content = editor.getMarkdown();

    const postData = {
        bno: bno,
        title: title,
        email: email,
        content: content
    };

    try {
        const response = await fetch('/board/modify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(postData)
        });

        if(response.ok) {
            const result = await response.text();
            alert("게시글이 수정 되었습니다.");
            window.location.href = '/board/list';
        } else {
            // 오류 처리
            console.error('서버 오류:', response.statusText);
        }
    } catch (error) {
        console.error('수정 실패:', error);
    }
}