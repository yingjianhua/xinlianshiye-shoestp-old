<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"/>
<link rel="stylesheet" href="/home/v3/static/css/login/login.css">
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<body class="bg-gray  page-login">
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
<div id="app" class="flex-column-layout min-height100">
    <index-top></index-top>
    <!-- // 第三方 - hellojs -->
    <!-- test -->
    <!-- <div>
        <button id='profile1' @click="login('facebook')">登陆facebook</button>
        <button @click="out('facebook');">退出登陆facebook</button>

        <button id='profile2' @click="login('google')">登陆google</button>
        <button @click="out('google');">退出登陆google</button>

        <button id='profile3' @click="login('linkedin')">登陆linkedin</button>
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
                            <!-- test -->
                            <a href="/home/usr_UsrMain_register">Join Free</a>
                        </div>

                        <!-- form 表单 -->
                        <el-form ref="loginForm" class="login-form"
                                 label-position="top" size="medium"
                                 :hide-required-asterisk="true"
                                 :show-message="false"
                                 :model="loginForm" :rules="loginFormRules">
                            <!-- 防止用户名、密码自动填充 -->
                            <input type="text" name="catch-name" class="incase-autocomplete-input">
                            <input type="password" name="catch-psd" class="incase-autocomplete-input">

                            <el-form-item label="Email" prop="email">
                                <el-input v-model="loginForm.email" placeholder="Email address or member ID"></el-input>
                            </el-form-item>

                            <el-form-item label="Password" prop="psd">
                                <el-input v-model="loginForm.psd" placeholder="Password" type="password"></el-input>
                            </el-form-item>
                        </el-form>

                        <div class="forget-psd-wrap">
                            <!-- test -->
                            <a href="/home/usr_UsrMain_forget" class="forget-psd">
                                Forgot your password?
                            </a>
                        </div>
                        <el-button type="primary" size="medium" class="btn-login"
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
                                    <!-- <a href="javascript: void(0);" @click="login('google')">
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
    var app = new Vue({
        el: "#app",
        data: {

            loginForm: {
                email: '',
                psd: '',
            },
            loginFormRules: {
                email: [
                    {required: true, message: '', trigger: 'blur'}
                ],
                psd: [
                    {required: true, message: '', trigger: 'blur'}
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

            // 第三方 - hellojs
            login(thirdName) {
                var self = this;
                var facebook = hello(thirdName);
                facebook.login().then(function (e) {
                    console.log("facebook's login")
                    console.log(e)
                    // get user profile data
                    return facebook.api('me');
                }).then(function (p) {
                    console.log("facebook's login")
                    console.log(p)
                    // 第三方成功返回用户信息后，判断是否已在本项目注册
                    // 参数为第三方的名字“facebook”，及第三方返回的ID
                    if (p.id) {
                        // 将第三方信息保存在本地
                        localStorage.setItem("thirdName", thirdName);
                        localStorage.setItem("thirdId", p.id);
                        self.loginPost({thirdName: thirdName, thirdId: p.id})
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
            loginPost(thirdData) {
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
                }

                axios.post('/home/usr_UsrMain_login', Qs.stringify(postData))
                    .then((res) => {
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg);
                            return
                        }
                        ;

                        // 已注册时，直接登录 - 并跳转
                        // 第三方账号已注册时，直接登录 - sign感觉后台逻辑不对！！！一直是true
                        if (res.data.sign) {
                            // test
                            window.location.href = "/";
                            // alert("To Index Page")
                        } else {
                            // 未注册时：
                            //普通登录提示：该账户未注册，请重试
                            if (!thirdData) {
                                this.$message.error('The account is not registered, please try again');
                                // 第三方直接携带第三方id至注册页面
                            } else {
                                // test
                                window.location.href = "./register-step1.html?isThird=true"
                            }
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },

            // 提交账号信息
            submit() {
                this.$refs.loginForm.validate((valid, errObj) => {
                    // 验证全部正确时
                    if (valid) {
                        console.log('submit');
                        this.loginPost();
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