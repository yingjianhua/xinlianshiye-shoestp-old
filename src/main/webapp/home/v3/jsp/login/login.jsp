<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"/>
<link rel="stylesheet" href="/home/v3/static/css/login/login.css">
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<body class="bg-gray  page-login">
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>

<div id="app" class="flex-column-layout">
    <index-top></index-top>
    <!-- // 第三方 - hellojs -->
    <!-- test -->
    <!-- <div>
        <button id='profile1' @click="loginByThird('facebook')">登陆facebook</button>
        <button @click="out('facebook');">退出登陆facebook</button>

        <button id='profile2' @click="loginByThird('google')">登陆google</button>
        <button @click="out('google');">退出登陆google</button>

        <button id='profile3' @click="loginByThird('linkedin')">登陆linkedin</button>
        <button @click="out('linkedin');">退出登陆linkedin</button>
    </div> -->

    <!-- flex布局，高度100% -->
    <div class="main w_1240" style="width: 100%">
        <div class="login-page-content">
            <div class="wide">
                <div class="login-page-content-inner" style="overflow: hidden;">
                    <img src="/home/v3/static/images/login/logoin_txt.png" alt="" class="welcome-txt-pic">

                    <!-- 右侧表单面板 -->
                    <div class="login-form-panel">
                        <div class="join-free">
                            <%--<a href="/home/usr_UsrMain_register">Join Free</a>--%>
                            <a @click="toRegister" href="javascript: void(0);">Join Free</a>
                        </div>

                        <!-- form 表单 -->
                        <el-form ref="loginForm" class="login-form"
                                 label-position="top" size="medium"
                                 :hide-required-asterisk="true"
                                 :model="loginForm" :rules="loginFormRules">
                            <!-- 防止用户名、密码自动填充 -->
                            <%--<input type="text" name="catch-name" class="incase-autocomplete-input">--%>
                            <%--<input type="password" name="catch-psd" class="incase-autocomplete-input">--%>

                            <el-form-item label="Email" prop="email">
                                <el-input v-model.trim="loginForm.email"
                                          @keyup.enter.native="submit"
                                          placeholder="Email address or member ID" auto-complete="on"></el-input>
                            </el-form-item>

                            <el-form-item label="Password" prop="psd">
                                <el-input v-model="loginForm.psd"
                                          onkeyup="this.value=this.value.replace(/\s+/g,'')"
                                          @keyup.enter.native="submit"
                                          placeholder="Password" auto-complete="on" type="password"></el-input>
                            </el-form-item>
                        </el-form>

                        <div class="forget-psd-wrap">
                            <a href="/home/usr_UsrMain_forget" class="forget-psd">
                                Forgot your password?
                            </a>
                        </div>
                        <el-button type="primary" size="medium" type="submit" class="btn-login"
                                   @click="submit">Sign In
                        </el-button>

                        <div class="login-form-bottom">
                            <div class="title">Sign in with</div>
                            <ul class="share-list">
                                <li class="share-item">
                                    <a href="javascript: void(0);">
                                        <img src="/home/v3/static/images/login/icon_f.png" alt="">
                                    </a>
                                </li>
                                <li class="share-item">
                                    <!-- <a href="javascript: void(0);" @click="loginByThird('google')">
                                        <img src="/home/v3/static/images/login/icon_g.png" alt="">
                                    </a> -->
                                    <a href="javascript: void(0);">
                                        <img src="/home/v3/static/images/login/icon_g.png" alt="">
                                    </a>
                                </li>
                                <li class="share-item">
                                    <a href="javascript: void(0);">
                                        <img src="/home/v3/static/images/login/icon_in.png" alt="">
                                    </a>
                                </li>
                                <li class="share-item">
                                    <a href="javascript: void(0);">
                                        <img src="/home/v3/static/images/login/icon_niao.png" alt="">
                                    </a>
                                </li>

                                <li class="share-item user-supplier">
                                    <!-- test -->
                                    <a href="/newseller/">
                                        I am a supplier
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 右侧表单面板 - end -->
                </div>
            </div>
        </div>
    </div>
    <div style="grow: 1;"></div>
    <index-bottom></index-bottom>
</div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>

<script>
    // 密码验证
    // const validatePsd = (rule, value, callback) => {
    //     // 正式的密码验证
    //     if (value === '') {
    //         callback(new Error('Password can\'t be empty!'));
    //     } else if (value.length < 6 || value.length > 20) {
    //         callback(new Error('Please enter password within 6 to 20 characters'));
    //     }else{
    //         callback();
    //     }
    // };
    var app = new Vue({
        el: "#app",
        data: {
            loginForm: {
                email: '',
                psd: '',
            },
            loginFormRules: {
                email: [
                    {required: true, message: 'Email can\'t be empty!', trigger: 'blur'}, {
                        pattern: util_regular_obj.register.email,
                        message: 'E-mail format is incorrect',
                        trigger: 'blur'
                    }
                ],
                psd: [
                    // {validator: validatePsd, trigger: 'change'},
                    { required: true, message: 'Password can\'t be empty', trigger: 'blur' },
                    {
                        pattern: util_regular_obj.register.psd,
                        message: 'Please enter 6 to 20 characters',
                        trigger: 'blur'
                    }
                    // { min: 6, max: 20, message: 'Please enter 6 to 20 characters', trigger: 'blur' }
                ],
            },
        },
        mounted() {
            // 第三方 - hellojs test
            hello.init({
                facebook: "801883060180295",
                linkedin: "81xpp0e4b5z1fh",
                /*  windows: WINDOWS_CLIENT_ID,*/
                google: "658319317089-5q7feihmh4luf3rjqgli352eb3plia6m.apps.googleusercontent.com"
            }, {
                redirect_uri: "https://2w3u801940.zicp.vip/xiemaogang/forgetPsd/login.html"
            });
        },
        methods: {
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return "https://image.shoestp.com" + v + params
            },

            // 点击去注册
            toRegister(){
                // 保存是从哪个页面点击去注册的 - 到时候注册完成时跳转回来
                localStorage.setItem("whichPageToRegister", window.location.href);
                window.location.href = "/home/usr_UsrMain_register"
            },

            // 第三方 - hellojs
            loginByThird(thirdName) {
                var self = this;
                var facebook = hello(thirdName);
                facebook.login().then(function (e) {
                    console.log("facebook's login")
                    console.log(e)
                    // get user profile data
                    return facebook.api('me');
                }).then(function (thirdInfo) {
                    console.log("facebook's login")
                    console.log(thirdInfo)
                    // 第三方成功返回用户信息后，判断是否已在本项目注册
                    // 参数为第三方的名字“facebook”，及第三方返回的ID
                    if (thirdInfo.id) {
                        // 将第三方信息保存在本地 - test
                        localStorage.setItem("thirdName", thirdName);
                        localStorage.setItem("thirdId", thirdInfo.id);
                        self.login({thirdName: thirdName, thirdId: thirdInfo.id})
                    }
                });
            },

            // 第三方 - hellojs
            out(network) {
                hello(network).logout().then(function () {
                    alert('Signed out');
                }, function (e) {
                    alert('Signed out error: ' + e.error.message);
                });
            },

            // 自己的登录接口
            login(thirdData) {
                let postData;
                // 当选择第三方登录时：
                if (thirdData) {
                    postData = {
                        thirdName: thirdData.thirdName,
                        thirdId: thirdData.thirdId,
                    }
                    // 选择普通登录时：
                } else {
                    postData = {
                        loginName: this.loginForm.email,
                        pwd: this.loginForm.psd,
                    }
                    localStorage.removeItem("thirdName");
                    localStorage.removeItem("thirdId");
                }
                // 这里与登录弹窗不同 - 此处是取地址栏中的jumpUrl= - 那边直接传参形式
                //jumpUrl传给后台后，后台会返回jumpUrl，并前端进行跳转
                if( util_function_obj.GetQueryString("jumpUrl") ){
                    postData.jumpUrl = util_function_obj.GetParamsFullUrl("jumpUrl=");
                }

                axios.post('/home/usr_UsrMain_login', Qs.stringify(postData))
                    .then((res) => {
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg || "Login error, please try again later");
                            return
                        }
                        ;

                        // 已注册时，直接登录 - 并跳转
                        // 第三方账号已注册时，直接登录 - sign感觉后台逻辑不对！！！一直是true
                        if (res.data && res.data.sign) {
                            // 前端有传jumpUrl时，后台亦会返回跳转地址
                            if (res.data.jumpUrl){
                                window.location.href = res.data.jumpUrl;
                            }else{
                                window.location.href = "/";
                            }
                        } else {
                            // 未注册时：
                            //普通登录提示：该账户未注册，请重试
                            if (!thirdData) {
                                this.$message.error('The account is not registered, please confirm that the account is correct.');
                                // 第三方直接携带第三方id至注册页面
                            } else {
                                // test
                                window.location.href = "/home/usr_UsrMain_register?isThird=true"
                            }
                        }
                    })
                    .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                    });
            },

            // 提交账号信息
            submit() {
                this.$refs.loginForm.validate((valid, errObj) => {
                    // 验证全部正确时
                    if (valid) {
                        console.log('submit');
                        this.login();
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },

        },
    })

</script>
</body>
</html>
