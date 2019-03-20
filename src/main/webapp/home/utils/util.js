var util_function_obj={
    alertWhenNoLogin: function(that,jumpBackUrl,callback){
        // 显示登录弹框 - jsp引入的vue实例
        loginBoxVue.showLoginBox = true;
        // that.$alert('Please login to operate', {
        //     confirmButtonText: 'Ok',
        //     customClass: "my-custom-element-alert-class fs-content-18",
        //     center: true,
        //     callback: action => {
        //         if(action=="confirm"){
        //             var fullUrl = window.location.href;
        //             var shoestp = "www.shoestp.com/";
        //             var testUrl = "192.168.100.48";
        //             var port = ":8080";
        //             // 有传值过来时，直接拼接值
        //             if(jumpBackUrl){
        //                 window.location.href = "/home/usr_UsrPurchase_sign?" + jumpBackUrl
        //             // 否则回跳地址为点击弹窗时的地址 - 以"/home"开始
        //             }else if( fullUrl.indexOf("/home/") != -1 ){
        //                 jumpBackUrl = fullUrl.substr( fullUrl.indexOf("/home/") );
        //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + jumpBackUrl
        //             // 商品详情页地址难受
        //             }else if(fullUrl.indexOf(shoestp) != -1){
        //                 jumpBackUrl = fullUrl.substr( fullUrl.indexOf(shoestp) + shoestp.length );
        //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + jumpBackUrl
        //             // 测试网站
        //             }else if(fullUrl.indexOf(testUrl) != -1){
        //                 jumpBackUrl = fullUrl.substr( fullUrl.indexOf(testUrl) + testUrl.length );
        //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + jumpBackUrl
        //             // 本地服务器
        //             }else if(fullUrl.indexOf(port) != -1){
        //                 jumpBackUrl = fullUrl.substr( fullUrl.indexOf(port) + port.length );
        //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + jumpBackUrl
        //             }else{
        //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl="
        //             }
        //         }else{
        //             return false
        //         }
        //     }
        // });
        // callback && callback();
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
