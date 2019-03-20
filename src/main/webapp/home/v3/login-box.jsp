<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--全局登录弹窗--%>
<link rel="stylesheet" href="/home/v3/static/css/login/login.css">
<div id="login-box-global">
    <el-dialog
            custom-class="popup-login-box-global"
            :visible.sync="showLoginBox"
            :show-close="false"
            width="400px">
        <div class="login-form-panel">
            <div class="join-free">
                <!-- test -->
                <a href="/home/usr_UsrMain_register">Join Free</a>
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
                    <el-input v-model="loginForm.email" placeholder="Email address or member ID" auto-complete="on"></el-input>
                </el-form-item>

                <el-form-item label="Password" prop="psd">
                    <el-input v-model="loginForm.psd" onkeyup="this.value=this.value.replace(/\s+/g,'')"
                              placeholder="Password" auto-complete="on" type="password"></el-input>
                </el-form-item>
            </el-form>

            <div class="forget-psd-wrap">
                <!-- test -->
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
                </ul>
            </div>
        </div>
    </el-dialog>
</div>

<script>
    var loginBoxVue = new Vue({
        el: "#login-box-global",
        data: {
            showLoginBox: false,

            loginForm: {
                email: '',
                psd: '',
            },
            loginFormRules: {
                email: [
                    {required: true, message: 'Email can\'t be empty!', trigger: 'blur'}, {
                        pattern: /^[\w]{1,16}@+\w{1,15}.\w{2,5}$/,
                        message: 'E-mail format is incorrect',
                        trigger: 'blur'
                    }
                ],
                psd: [
                    // {validator: validatePsd, trigger: 'change'},
                    { required: true, message: 'Password can\'t be empty', trigger: 'blur' },
                    { min: 6, max: 20, message: 'Please enter 6 to 20 characters', trigger: 'blur' }
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
                // 这里与登录页面不同 - 那边是取地址栏中的jumpUrl - 此处直接传参形式
                // if( util_function_obj.GetQueryString("jumpUrl") ){
                //     postData.jumpUrl = util_function_obj.GetLoginJumpBackUrl();
                // }

                axios.post('/home/usr_UsrMain_login', Qs.stringify(postData))
                    .then((res) => {
                    if (res.data.ret != 1) {
                    this.$message.error(res.data.msg || "Login error, please try again later");
                    return
                }else{
                        window.location.reload()
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
                    this.$message.error(error || 'Network error,please try again later');
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