// 手机端rem
var width = document.documentElement.clientWidth;
var bili = width/750;
var html = document.querySelector('html');
var rem = 16;
html.style.fontSize = rem + 'px';
var __bili = bili/rem;
document.documentElement.style.setProperty('--bili', __bili+"rem");
