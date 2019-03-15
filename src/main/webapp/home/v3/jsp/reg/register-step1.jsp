<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="/home/v3/static/css/reg/index.css"/>
</head>
<body class="bg-gray w_1240 page-register_1">
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
<div id="app">
    <div class="placeholder wide" style="height: 93px;line-height: 93px;">
        <img class="logo" src="/home/v3/static/images/login/o2otoplogo.png" alt=""
             style="width: 204px;height: 41px;">
    </div>

    <main class="main wide">
        <!-- 注册流程概览 -->
        <ul class="step-overview">
            <li class="step-item active">
                1
                <div class="title">请设置用户名</div>
            </li>
            <li class="step-item">
                2
                <div class="title">填写账号信息</div>
            </li>
            <li class="step-item">
                3
                <div class="title">注册成功</div>
            </li>
        </ul>

        <!-- 第一步的内容 - 验证自己后台的code -->
        <div class="step-one-wrap" v-show="step == 1">
            <el-form class="my-validate-form" size="medium"
                     ref="registerForm" label-width="135px"
                     :hide-required-asterisk="true"
                     :model="registerForm"
                     :rules="registerFormRules">
                <el-form-item id="email"
                              label="Your Email Address" prop="email">
                    <el-input placeholder="Email"
                              v-model="registerForm.email">
                    </el-input>
                </el-form-item>

                <el-form-item class="verification-code-wrap01" label="Verification Code" prop="code">
                    <div class="verification-code-wrap">
                        <el-input placeholder="Verification code"
                                  v-model="registerForm.code">
                        </el-input>
                        <img :src="codeUrl" alt="Verification code"
                             class="pic-code">
                        <img src="/home/v3/static/images/login/icon_re.png" alt="icon recycle"
                             class="icon-recycle"
                             @click="codeUrl = codeUrl + '?r=' + Math.random()">
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="goStep2">Continue</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 第一步的内容 - 验证自己后台的code - end -->

        <!-- 第二步的内容 - 验证邮箱code -->
        <div class="step-two-wrap" v-show="step == 2">
            <div class="operate-info-box">
                <div class="title">
                    A confirmation email has been sent to your
                    <br>
                    mailbox <a class="email" target="_blank" :href="mailDetailAddr">{{registerForm.email}}</a>
                </div>
                <div class="tips">
                    Please check your email box and continue your registration within 24 hours.
                </div>
                <div class="btn-wrap">
                    <el-button size="medium" type="primary" class="btn"
                               @click="toCheckEmail">Go check email
                    </el-button>


                    <el-popover
                            placement="bottom-start"
                            :visible-arrow="false"
                            popper-class="my-popover-tips"
                            width="300"
                            trigger="hover"
                            v-model="isReceiveTipsShow">
                        <ul class="tips-list">
                            <li class="tip-item">
                                Please check your spam folder
                            </li>
                            <li class="tip-item">
                                <span>If you have not received theemail,</span>

                                <div class="btn-resend" @click="resend">
                                    {{countDown>0?"Emall has been sent again":"Click here to resend the emall"}}
                                </div>
                                <div v-if="countDown > 0">
                                    You could send again after {{countDown}}s
                                </div>

                            </li>
                            <li class="tip-item">
                                Have not received?
                                <span class="go-back" @click="goBack">Try using another emall address.</span>
                            </li>
                        </ul>
                        <div class="tips" slot="reference">
                            Having problem receiving email？
                        </div>
                    </el-popover>

                </div>
            </div>

            <!-- 没接受到信息的tips -->
            <div class="no-receive-tips">
                <h3 class="title">Not received the email verification code?</h3>
                <div class="tips-content">
                    <p>1. Your email code may take ten minutes to get to (depending on your email service provider),
                        please do not click again.
                    </p>
                    <p>2. Please check your email address is valid, or whether it into the dustbin/junk mail folder, or
                        your email inbox is full.
                    </p>
                    <p>3. Lost four network anomalies may lead to mail, please submit a request again or try a different
                        browser or later browser cookies are clear.
                    </p>
                    <p>4. Contact your email service provider, check to see if the verification code email has been
                        blocked.
                    </p>
                </div>
            </div>
        </div>
        <!-- 第二步的内容 - 验证邮箱code - end -->

    </main>
</div>
<script>
    const emailAddrList = {
        'qq.com': 'http://mail.qq.com',
        'gmail.com': 'http://mail.google.com',
        'sina.com': 'http://mail.sina.com.cn',
        '163.com': 'http://mail.163.com',
        '126.com': 'http://mail.126.com',
        'yeah.net': 'http://www.yeah.net/',
        'sohu.com': 'http://mail.sohu.com/',
        'tom.com': 'http://mail.tom.com/',
        'sogou.com': 'http://mail.sogou.com/',
        '139.com': 'http://mail.10086.cn/',
        'hotmail.com': 'http://www.hotmail.com',
        'live.com': 'http://login.live.com/',
        'live.cn': 'http://login.live.cn/',
        'live.com.cn': 'http://login.live.com.cn',
        '189.com': 'http://webmail16.189.cn/webmail/',
        'yahoo.com.cn': 'http://mail.cn.yahoo.com/',
        'yahoo.cn': 'http://mail.cn.yahoo.com/',
        'eyou.com': 'http://www.eyou.com/',
        '21cn.com': 'http://mail.21cn.com/',
        '188.com': 'http://www.188.com/',
        'foxmail.com': 'http://www.foxmail.com',
        'outlook.com': 'http://www.outlook.com'
    };

    new Vue({
        el: "#app",
        data: {
            step: 1, //进行到第几步 1-本地code 2-邮箱code
            codeUrl: "/servlet/verify.img", // 本地验证码 - 刷新用
            mailDetailAddr: "", // 邮箱跳转地址
            isReceiveTipsShow: false, //第二步中，鼠标移入时的提示框
            countDownTimer: null, //重新发送 计时器
            countDown: 0, //计时器
            registerForm: {
                email: '',
                code: '',
            },
            registerFormRules: {
                email: [
                    {required: true, message: 'Email can\'t be empty!', trigger: 'blur'}, {
                        pattern: /^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$/,
                        message: 'E-mail format is incorrect',
                        trigger: 'blur'
                    }
                ],
                code: [
                    {required: true, message: 'Code can\'t be empty!', trigger: 'blur'}
                ],
            },
        },
        mounted() {
            // 是否是第三方注册
            let isThirdRegister = this.GetQueryString("isThird");
            console.log("isThirdRegister")
            console.log(isThirdRegister)
            // 不是第三方则清空第三方的storage数据
            // 否则后续页面可以直接获取保存的数据
            if (!isThirdRegister) {
                localStorage.removeItem("thirdName")
                localStorage.removeItem("thirdId")
            }
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
            // 读取链接带参
            GetQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURI(unescape(r[2]));
                return null;
            },

            // 发送验证码至邮箱
            sendCodeToEmail() {
                axios.post('/home/usr_UsrMain_sendEm', Qs.stringify({
                    email: this.registerForm.email,
                    checkCode: this.registerForm.code,
                    type: 0,
                }))
                    .then((res) => {
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg);
                            return
                        }
                        ;

                        localStorage.setItem("registerEmail", this.registerForm.email);

                        // 生成对应邮箱跳转地址
                        let mailAddr = this.registerForm.email.split('@')[1];  //获取邮箱域名
                        for (var key in emailAddrList) {
                            if (key == mailAddr) {
                                console.log(emailAddrList[key])
                                this.mailDetailAddr = emailAddrList[key]
                            }
                        }

                        this.step = 2;
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            },

            //第一步 - 验证自己的code
            goStep2() {
                this.$refs.registerForm.validate((valid) => {
                    if (valid) {
                        console.log('goStep2');
                        this.sendCodeToEmail();
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },
            // 重新发送
            resend() {
                if (this.countDown > 0) return;
                this.sendCodeToEmail();

                // 倒计时
                clearInterval(this.countDownTimer)
                this.countDown = 60;
                this.countDownTimer = setInterval(() => {
                    this.countDown--;
                    if (this.countDown < 0) {
                        clearInterval(this.countDownTimer)
                    }
                }, 1000)
            },
            // 返回上一步 - 重填信息
            goBack() {
                this.step = 1;
                // this.registerForm.email = "";
                // this.registerForm.code = "";
                this.codeUrl = this.codeUrl + '?r=' + Math.random();
                this.isReceiveTipsShow = false;
                this.$refs.registerForm.resetFields();
            },
            toCheckEmail() {
                window.open(this.mailDetailAddr);
            },

        }
    })

</script>
</body>
</html>