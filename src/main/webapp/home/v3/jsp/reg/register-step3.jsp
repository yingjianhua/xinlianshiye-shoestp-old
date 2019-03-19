<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="/home/v3/static/css/reg/index.css"/>
<style>
    .step3-tips-wrap {
        padding-top: 50px;
        font-size: 16px;
        text-align: center;
        font-weight: bold;
    }

    .step3-tips-wrap a {
        color: #35c;
        cursor: pointer;
        text-decoration: underline;
    }

    .step3-tips-wrap a:hover {
        color: #66b1ff;
    }

    .step3-tips-wrap .timer {
        display: inline-block;
        min-width: 30px;
        white-space: nowrap;
    }
</style>

</head>
<body class="bg-gray  page-register_2">
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
<div id="app" class="w_1240">
    <div class="placeholder wide" style="height: 93px;line-height: 93px;">
        <img class="logo" src="/home/v3/static/images/login/o2otoplogo.png" alt=""
             style="width: 204px;height: 41px;">
    </div>

    <main class="main wide" style="min-height: 600px;">
        <!-- 注册流程概览 -->
        <ul class="step-overview">
            <li class="step-item">
                1
                <div class="title">请设置用户名</div>
            </li>
            <li class="step-item">
                2
                <div class="title">填写账号信息</div>
            </li>
            <li class="step-item active">
                3
                <div class="title">注册成功</div>
            </li>
        </ul>

        <!-- 注册成功提示信息 -->
        <div class="step3-tips-wrap">
            Registered Successfully！
            <a @click="goToIndexPage">Go to login</a>
            <div class="timer">
					<span v-if="countDown >= 0">
						{{countDown}}
					</span>
            </div>
        </div>
        <!-- 注册成功提示信息 - end -->

    </main>
</div>
<div id="indexbottom" style="margin-top: 93px">
    <index-bottom></index-bottom>
</div>

<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el: "#indexbottom"
    })
</script>
<script>

    var app = new Vue({
        el: "#app",
        data: {
            countDown: 10, //倒计时
            countDownTimer: null, //倒计时计数器
        },
        mounted() {
            // 成功后直接登录
            setTimeout(()=>{
                this.autoLogin();
            },1000);
            // 倒计时
            this.countDownTimer = setInterval(() => {
                this.countDown--;
                if (this.countDown < 0) {
                    this.goToIndexPage();
                    clearInterval(this.countDownTimer)
                }
            }, 1000)
        },
        methods: {
            // 成功后跳转会 点击进行注册的页面
            goToIndexPage() {
                // test
                window.location.href = "/";
                // alert("To index page")
            },
            autoLogin() {
                let registerEmail = localStorage.getItem("registerEmail");
                let registerPsd = localStorage.getItem("registerPsd");
                if (!registerEmail || !registerPsd) return;

                axios.post('/home/usr_UsrMain_login', Qs.stringify({
                    loginName: registerEmail,
                    pwd: registerPsd,
                }))
                    .then((res) => {
                        // if (res.data.ret != 1) {
                        //     this.$message.error(res.data.msg);
                        //     return
                        // };
                        localStorage.removeItem("registerEmail")
                        localStorage.removeItem("registerPsd")
                    })
                    .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                    });
            },
        }
    })

</script>
</body>
</html>
