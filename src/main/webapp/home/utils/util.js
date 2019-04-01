var util_function_obj={
    // 参数jumpUrl为login后跳转的页面 - 不传时login后刷新当前页
    //          如点击 按钮buy，判断并login后跳转至购物车
    alertWhenNoLogin: function(that,jumpUrl,callback){

        // 如果在登录页点击的，不弹出登录框，直接刷新登录页
        if( window.location.pathname.indexOf("usr_UsrPurchase_sign")!=-1 ){
            window.location.reload();
            return;
        }
        // 显示登录弹框 - jsp引入的vue实例
        loginBoxVue.showLoginBox = true;
        loginBoxVue.jumpUrl = jumpUrl || null;
        // that.$alert('Please login to operate', {
        //     confirmButtonText: 'Ok',
        //     customClass: "my-custom-element-alert-class fs-content-18",
        //     center: true,
        //     callback: action => {
        //         if(action=="confirm"){
        //             // 有传值过来时，直接拼接值
        //             if(jumpUrl){
        //                 window.location.href = "/home/usr_UsrPurchase_sign?jumpUrl=" + jumpUrl
        //             }
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

    // 获取地址中字段： 从第一个params后，开始截取至最后（不包含params的"key="）
    // (如获取登录时 需要跳转回的地址 - 从jumpUrl=后开始截取至最后)
    GetParamsFullUrl: function(params,defaultParams){
        if(!params && !defaultParams) return "/";
        var searchStr = params || defaultParams;
        var searchUrl = window.location.search.substr(1);
        if (searchUrl.indexOf(searchStr)==-1){
            return "/"
        }
        return searchUrl.substr( searchUrl.indexOf(searchStr) + searchStr.length);
    },

    // 供应商有些地方不能进入 - 询盘之类的
    supplierCantEnter(that,url,msg){
        if(sysConfig && sysConfig.user && sysConfig.user.user_type == 1){
            that.$alert(msg || "Sorry, the supplier cannot operate",{
                confirmButtonText: 'Ok',
                customClass: "my-custom-element-alert-class fs-content-18",
                center: true,
                callback: action =>{
                    return
                }
            });
            return
        }
        window.location.href = url;
    },

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
    // 获取字节数 - 计算长度 - 换行
    getByteLen(str) {
        if (!str) return null;

        let byteLen = 0;
        for (let i = 0; i < str.length; i++) {
            // 中文算2个字节，其余算1个
            if (str[i].match(/[^\x00-\xff]/gi) != null) {
                byteLen += 1
            } else {
                byteLen += 0.5
            }
        }
        return Math.floor(byteLen)

    },

    // 节流函数 - 计时器
    _throttleTimeout: null,
    // 节流函数 - 上次执行时间点
    _throttlePrevious: 0,
    // 节流函数 - 不管连续点击，wait时间内只生效一次
    _throttle: function(func, wait, options) {
        wait = wait || 1000;
        var context, args, result;

        if (!options) options = {};
        // 延迟执行函数
        var later = function() {
            // 若设定了开始边界不执行选项，上次执行时间始终为0
            util_function_obj._throttlePrevious = options.leading === false ? 0 : Date.now();
            util_function_obj._throttleTimeout = null;
            result = func.apply(context, args);
            if (!util_function_obj._throttleTimeout) context = args = null;
        };
        return function() {
            var now = Date.now();
            // 首次执行时，如果设定了开始边界不执行选项，将上次执行时间设定为当前时间。
            if (!util_function_obj._throttlePrevious && options.leading === false) util_function_obj._throttlePrevious = now;
            // 延迟执行时间间隔
            var remaining = wait - (now - util_function_obj._throttlePrevious);
            context = this;
            args = arguments;
            // 延迟时间间隔remaining小于等于0，表示上次执行至此所间隔时间已经超过一个时间窗口
            // remaining大于时间窗口wait，表示客户端系统时间被调整过
            if (remaining <= 0 || remaining > wait) {
                clearTimeout(util_function_obj._throttleTimeout);
                util_function_obj._throttleTimeout = null;
                util_function_obj._throttlePrevious = now;
                result = func.apply(context, args);
                if (!util_function_obj._throttleTimeout) context = args = null;
                //如果延迟执行不存在，且没有设定结尾边界不执行选项
            } else if (!util_function_obj._throttleTimeout && options.trailing !== false) {
                util_function_obj._throttleTimeout = setTimeout(later, remaining);
            }
            return result;
        };
    }
};


var util_regular_obj = {
    register:{
        psd: /^[^\s]{6,20}$/,   //密码
        phoneChina: /^1\d{10}$/,  //国内手机
        phoneAreaCode: /^[+\d]?\d{1,3}-\d{6,16}$/,  //含国家区号手机 示例:0086-12345678,+86-12345678
        email: /^[\w]{1,32}@\w{1,15}.\w{2,5}$/,  //邮箱正则
        nameChina: /^[\u4E00-\u9FA5]{2,6}$/,  //姓名-中古
        nameGlobal: /^[^`~!@#$%^&*()_+?><":\]\[}\{].{0,31}[^`~!@#$%^&*()_+?><":\]\[}\{]$/,  //姓名
        companyName: /^[^`~!@#$%^&*()_+?><":\]\[}\{].{0,51}[^`~!@#$%^&*()_+?><":\]\[}\{]$/,  //公司名称

        positiveInteger: /^\+?[1-9][0-9]{0,9}$/, //正整数 - 最大10位数 - 表单数量用
        priceDecimal: /^([1-9]{1}\d{0,5}|([0]{1}))(\.(\d){1,2})?$/,	//6位整数、2位小数 - 价格用
    }
};
