var util_function_obj={
    alertWhenNoLogin: function(that,jumpBackUrl,callback){
        that.$alert('Please login to operate', {
            confirmButtonText: 'Ok',
            customClass: "my-custom-element-alert-class fs-content-18",
            center: true,
            callback: action => {
                if(action=="confirm"){
                    var fullUrl = window.location.href;
                    // 有传值过来时，直接拼接值
                    if(jumpBackUrl){
                        window.location.href = "/home/usr_UsrPurchase_sign?" + jumpBackUrl
                    // 否则回跳地址为点击弹窗时的地址 - 以"/home"开始
                    }else if( fullUrl.indexOf("/home/") != -1 ){
                        jumpBackUrl = fullUrl.substr( fullUrl.indexOf("/home/") );
                        window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + jumpBackUrl
                    }
                }else{
                    return false
                }
            }
        });
        callback && callback();
    },

    // 读取链接带参
    GetQueryString: function(name){
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  decodeURI(unescape(r[2]));
        return null;
    },

    // 获取登录时 需要跳转回的地址 - 从jumpUrl开始截取至最后
    GetLoginJumpBackUrl: function(){
        var searchStr = "jumpUrl=";
        var searchUrl = window.location.search.substr(1);
        return searchUrl.substr( searchUrl.indexOf(searchStr) + searchStr.length);
    },

    // 获取地址参数 - 登录时用 - 返回该地址
    // GetSearchUrlParams: function(){
    //     return window.location.search.substr(1);
    // },

    // 图片地址加前缀-后缀
    image: function(url, w, h, param) {
        var postfixUrl = ""; //后缀
        if (!url) {
            return ""
        }
        if (w && h) {
            postfixUrl = "?x-oss-process=image/resize,w_" + w + ",h_" + h;
        }else if(w){
            postfixUrl = "?x-oss-process=image/resize,w_" + w + ",h_" + w;
        }
        if(param){
            postfixUrl += param;
        }
        return "https://image.shoestp.com" + url + postfixUrl;
    },
}
