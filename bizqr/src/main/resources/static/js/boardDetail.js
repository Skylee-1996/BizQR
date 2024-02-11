const Viewer = toastui.Editor;

const viewer = new Viewer({
    el: document.querySelector('#viewer'),
    viewer: true,
    initialEditType:"wysiwyg",
    hideModeSwitch: true,
    height: '600px',
    initialValue: content,
    toolbarItems: [],
    language:"ko-KR"
});