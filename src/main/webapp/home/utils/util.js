var util_function_obj={
    alertWhenNoLogin: function(that,jumpBackUrl,callback){
        that.$alert('Please login to operate', {
            confirmButtonText: 'Ok',
            customClass: "my-custom-element-alert-class fs-content-18",
            center: true,
            callback: action => {
                if(action=="confirm"){
                    window.location.href = jumpBackUrl
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
        return searchUrl.substr( searchUrl.indexOf(searchStr) + searchStr.length)
    },


}
