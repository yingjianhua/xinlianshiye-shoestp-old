// 页面加载时
window.onload = function () {
    changeRem();
};
// 页面大小变化时
window.onresize = function () {
    changeRem();
};
// 页面宽度变化显示内容的比例
function changeRem() {
    var width = document.documentElement.clientWidth;
    var scroll = window.innerWidth - document.documentElement.clientWidth;//滚动条宽度
    var bili = (width + scroll)/1920;//设计稿与页面宽对比
    var html = document.querySelector('html');
    var rem = 16;
    html.style.fontSize = rem + 'px';
    var __bili = bili/rem;
    document.documentElement.style.setProperty('--bili', __bili+"rem");
}


