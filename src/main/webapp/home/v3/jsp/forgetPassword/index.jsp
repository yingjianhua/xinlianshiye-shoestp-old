    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <jsp:include page="/home/v3/header.jsp"/>
    <link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
    <link rel="stylesheet" href="/home/v3/static/css/forgetPassword/index.css"/>
    <body style="background-color: rgb(245, 245, 245);">
    <jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
    <div id='login'>
        <index-top></index-top>
        <div class="statecon">
            <div class="states">
                <div class="hen left"></div>
                <div class="state" v-for="item,index in loginstate">
                    <img :src="item.loginimg" :class="{showimg:state >=
                            index}" alt="">
                    <h3 :class="{showh3:state >= index}">{{item.title}}</h3>
                </div>
                <div class="hen right"></div>
            </div>
        </div>
        <div class="judgeEmail">
            <div class="judge-email" v-show="!judgeEmailShow && state == 0">
                <p class="hint">
                    <img src="/home/v3/static/images/forgetPassword/icon_gantana.png" name="gantana"
                         alt="">
                    Enter your email address that you used to create the
                    Shoestp account <br>
                    This Email address has not been registered Shoestp
                    account.
                </p>
                <div class="form">
                    <div class="form-item">
                        <span class="form-item_label">Your Email Address</span>
                        <input type="text" class="form-item_input"
                               v-model.trim="email" @blur="judgeEmail" style="border:1px solid gray">
                        <span class="form-item_hint">{{promptMessage}}</span>
                    </div>
                    <div class="form-item">
                        <span class="form-item_label"></span>
                        <span class="register-hint">
                                    This email address isn't registered. Please
                                    enter another one or <a
                                class="register-hint-btn"
                                href="/home/usr_UsrMain_register">register a new account</a>
                                </span>
                    </div>
                    <div class="form-item">
                        <span class="form-item_label">Verification Code</span>
                        <input type="text" class="form-item_input
                                    form-item_code" v-model.trim="authCode" style="border:1px solid gray">
                        <span class="code-image" @click="codechange"><img
                                :src="imgCode" alt=""></span>
                    </div>
                    <div class="form-item">
                        <span class="form-item_label"></span>
                        <a class="button" href="javascript:void(0);"
                           @click="changeContinue">Continue</a>
                    </div>
                </div>
            </div>

            <div class="judge-email" v-show="judgeEmailShow && state == 0">
                <p class="hint">
                    <img src="/home/v3/static/images/forgetPassword/icon_gantana.png" name="gantana"
                         alt="">
                    Checking your inbox for verification codeemail <br>
                    and enter this code to reset your password
                </p>
                <div class="form">
                    <div class="form-item">
                        <span class="form-item_label boldface">Email</span>
                        <span class="email boldface">{{email}}</span>
                    </div>
                    <div class="form-item">
                        <span class="form-item_label">Verification Code</span>
                        <input type="text" class="form-item_input
                                    form-item_codePass" style="border: 1px solid gray;" v-model.trim="promptMessage">
                        <span class="send-button" @click="getemailCode">Get
                                    verification code again <span v-if="again != 0">{{again}}
                                        s</span></span>
                    </div>
                    <div class="form-item">
                        <span class="form-item_label"></span>
                        <span class="form-item_error" v-show="errorShow">
                                    <img src="/home/v3/static/images/forgetPassword/icon_cuo.png" name="cuo"
                                         alt="">
                                    {{errMessage}}</span>
                    </div>
                    <div class="form-item">
                        <span class="form-item_label"></span>
                        <a class="button" href="javascript:void(0);"
                           @click="changeSubmit">submit</a>
                    </div>
                </div>
                <div class="announcements">
                    <h2 class="announcements-h2">Not received the email
                        verification code? </h2>
                    <p class="announcements-p">
                        1. Your email code may take ten minutes to get to
                        (depending on your email service provider), please
                        do
                        not click again.
                    </p>
                    <p class="announcements-p">
                        2. Please check your email address is valid, or
                        whether it into the dustbin/junk mail folder, or
                        your
                        email inbox is full.
                    </p>
                    <p class="announcements-p">
                        3. Lost four network anomalies may lead to mail,
                        please submit a request again or try a different
                        browser or later browser cookies are clear.
                    </p>
                    <p class="announcements-p">
                        4. Contact your email service provider, check to see
                        if the verification code email has been
                        blocked.
                    </p>
                </div>
            </div>
        </div>
        <div class="passwordcon" v-if="state == 1">
            <div class="formcon">
                <div class="input">
                    <h4 class="label fb">Email</h4>
                    <h4 class="label email fb">{{email}}</h4>
                </div>
                <div class="input">
                    <h4 class="label">New password</h4>
                    <input type="password" v-model.trim="password"
                           onkeyup="this.value=this.value.replace(/\s+/g,'')"
                           @input="passwordtest" placeholder="Please input  password">
                    <div class="showmsg">
                        <%--<img src="/home/v3/static/images/forgetPassword/loginright.png" alt=""--%>
                             <%--v-if="password">--%>
                        <img src="/home/v3/static/images/forgetPassword/loginright.png" alt="" v-if="password
                        && checkpass">
                        <img src="/home/v3/static/images/forgetPassword/loginerr.png" alt="" v-if="password
                        && !checkpass">
                        <h4 class="msg">{{passwordStrength}}</h4>
                    </div>
                </div>
                <div class="input">
                    <h4 class="label">Confirm password</h4>
                    <input type="password" v-model.trim="checkpasword"
                           onkeyup="this.value=this.value.replace(/\s+/g,'')"
                           @blur="checkpassword" @keyup.enter="checkpassword"
                           placeholder="Please input password again">
                    <div class="showmsg">
                        <img src="/home/v3/static/images/forgetPassword/loginright.png" alt="" v-if="hidden
                                    && check">
                        <img src="/home/v3/static/images/forgetPassword/loginerr.png" alt="" v-if="hidden
                                    && !check">
                        <h4 class="msg">{{checkpasswordtext}}</h4>
                    </div>
                </div>
            </div>
            <div class="confirm" @click="submitpassword">Confirm</div>
        </div>
        <div class="checksuccess" v-if="state == 2">
            Password recovery successful, <a href="/">Return</a> immediately
        </div>
        <index-bottom></index-bottom>
    </div>
    <script src="/home/v3/static/js/index-top.js"></script>
    <script src="/home/v3/static/js/index-bottom.js"></script>

    <script>
        new Vue({
            el: '#login',
            data: {
                email: '', // 邮箱地址
                authCode: '', // 验证码
                imgCode: '/servlet/verify.img?r=0.5714606853753319',
                promptNavShow: 1,
                judgeEmailShow: false, // 判断邮箱是否有通过
                emailCode: null,
                promptMessage: '', // 输入的邮箱验证码
                errorShow: false, // 错误提示
                again: 0,//重新获取验证码

                password: '',//密码
                passwordStrength: '',//密码强度文本
                checkpasword: '',//确认密码
                check: false,//确认新密码和确认密码是否一致的状态
                hidden: false,//对与错的图标的隐藏
                checkpasswordtext: '',//确认密码文本
                errMessage:'',
                checkpass:true,

                state: 0,
                loginstate: [
                    {
                        loginimg: '/home/v3/static/images/forgetPassword/loginimg1.png',
                        title: 'Verification users'
                    },
                    {
                        loginimg: '/home/v3/static/images/forgetPassword/loginimg2.png',
                        title: 'Reset password'
                    },
                    {
                        loginimg: '/home/v3/static/images/forgetPassword/loginimg3.png',
                        title: 'Successful'
                    }
                ]
            },
            methods: {
                // 判断邮箱是否注册
                judgeEmail() {
                    this.email = this.email.toLowerCase();
                    // let regular = /.+@[a-z0-9\.]+\.(com|cn|net)$/;
                    let regular = util_regular_obj.register.email;
                    if (!this.email) {
                        this.promptMessage = 'Email cannot be empty'
                        return false
                    } else if (!regular.test(this.email)) {
                        this.promptMessage = "Please enter the correct email address!"
                        return false
                    } else {
                        this.promptMessage = ""
                        return true
                    }
                },
                //验证码变化
                codechange: function () {
                    this.imgCode = '/servlet/verify.img?r=' + Math.random();
                },
                // 提交验证邮箱
                changeContinue() {
                    const than = this
                    if (than.judgeEmail()) {
                        axios.post('/home/usr_UsrMain_sendEm',
                            Qs.stringify({
                                email: than.email,
                                type: 1, //1：忘记密码的输入邮箱，2：忘记密码的获取验证码
                                checkCode: than.authCode // 验证邮箱的验证码
                            })
                        ).then(res => {
                            if (res.data.ret == 1) {
                                than.judgeEmailShow = true;
                                than.getemailCode();
                            }else{
                                if(res.data.msg){
                                    than.$message({
                                        message: res.data.msg,
                                        type: 'warning'
                                    });
                                }else{
                                    than.$message({
                                        message: 'Verification Failed ',
                                        type: 'warning'
                                    });
                                }
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                        // than.judgeEmailShow = true;
                    }
                },
                //发送邮箱验证码
                getemailCode: function () {
                    const than = this
                    if (this.again == 0) {
                        axios.post('/home/usr_UsrMain_sendEm',
                            Qs.stringify({
                                email: than.email,
                                type: 2, //1：忘记密码的输入邮箱，2：忘记密码的获取验证码
                                checkCode: than.promptMessage // 发送到邮箱的验证码
                            })
                        ).then(res => {
                            if(res.data.ret == 1){
                                than.timeCode = res.data.timeCode
                                than.again = 60
                                than.timeout();
                            }else{
                                if(res.data.msg){
                                    than.$message({
                                        message: res.data.msg,
                                        type: 'warning'
                                    });
                                }else{
                                    than.$message({
                                        message: 'Verification Failed ',
                                        type: 'warning'
                                    });
                                }
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                    }
                },
                //获取验证码倒计时
                timeout: function () {
                    let self = this;
                    let againtime = setTimeout(function () {
                        if (self.again > 0) {
                            self.again--;
                            self.timeout();
                        } else {
                            clearTimeout(againtime)
                        }
                    }, 1000)
                },
                // 提交邮箱和邮件验证码
                changeSubmit: function () {
                    const than = this
                    axios.post('/home/usr_UsrMain_subValid',
                        Qs.stringify({
                            email: than.email,
                            code: than.promptMessage
                        })
                    ).then(res => {
                        if (res.data.ret == 1) {
                            this.errorShow =  false
                            this.state++;
                        }else{
                            this.errorShow = true
                            this.errMessage = res.data.msg || 'Verification code error';
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                },
                //验证密码强度
                passwordtest: function () {
                    this.checkpass = true;
                    this.hidden = false;
                    let lv = 0;
                    if (this.password.match(/[a-zA-Z]/g)) {
                        lv++;
                    }
                    if (this.password.match(/[0-9]/g)) {
                        lv++;
                    }
                    if (this.password.match(/(.[^a-zA-Z0-9])/g)) {
                        lv++;
                    }
                    if (this.password.length < 6) {
                        lv = 0;
                    }
                    if (lv > 3) {
                        lv = 3;
                    }
                    if (this.password == '') {
                        this.passwordStrength = 'Password can\'t be empty'
                        this.checkpass = false;
                        return;
                    } else if (lv == 0 || lv == 1) {
                        this.passwordStrength = 'Strength: Weak'
                    } else if (lv == 2) {
                        this.passwordStrength = 'Strength: Medium'
                    } else if (lv == 3) {
                        this.passwordStrength = 'Strength: Strong'
                    }

                    if( !util_regular_obj.register.psd.test(this.password) ){
                        this.passwordStrength = "Please set password within 6 to 20 characters"
                        this.checkpass = false;
                    }
                },
                //确认密码
                checkpassword: function () {
                    if(!this.checkpasword){
                        this.hidden = false;
                        this.check = false;
                        this.checkpasswordtext = 'Please enter password again';
                        return;
                    }
                    if (this.password === this.checkpasword) {
                        this.hidden = true;
                        this.check = true;
                        this.checkpasswordtext = '';
                    } else {
                        this.hidden = false;
                        this.check = false;
                        // this.checkpass = false;
                        this.checkpasswordtext = 'The two passwords are inconsistent';
                    }
                },
                //提交确认密码
                submitpassword: function (val) {
                    this.checkpassword();
                    if (this.password == '') {
                        this.passwordStrength = 'Password cannot be empty';
                        this.hidden = false;
                        this.check = false;
                        this.checkpass = false;
                        return false;
                    }
                    if (this.password.length < 6) {
                        this.passwordStrength = 'Please set password within 6 to 20 characters';
                        this.hidden = false;
                        this.check = false;
                        this.checkpass = false;
                        return false;
                    }
                    // if (!(this.password.match(/[a-z]/g) && this.password.match(/[0-9]/g))) {
                    //     this.passwordStrength = 'The password consists of Numbers and letters';
                    //     this.hidden = false;
                    //     this.check = false;
                    //     this.checkpass = false;
                    //     return false;
                    // }
                    if (this.check) {
                        this.checkpass = true;
                        const than = this
                        axios.post('/home/usr_UsrMain_updPwd',
                            Qs.stringify({
                                pwd: than.password,
                                pwdA: than.checkpasword,
                                email: than.email,
                                code: than.promptMessage
                            })
                        ).then(res => {
                            if (res.data.ret == 1) {
                                than.state++
                            }else{
                                if(res.data.msg){
                                    than.$message({
                                        message: res.data.msg,
                                        type: 'warning'
                                    });
                                }else{
                                    than.$message({
                                        message: 'Password reset failed ',
                                        type: 'warning'
                                    });
                                }
                            }
                        }).catch(err => {
                            console.log(err)
                        })
                    }
                },
            },
            mounted() {
            }
        })
    </script>
    </body>
    </html>