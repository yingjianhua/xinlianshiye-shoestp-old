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
        <a href="/">
            <img class="logo" src="/home/v3/static/images/login/o2otoplogo.png" alt=""
                 style="width: 204px;height: 41px;">
        </a>
    </div>

    <main class="main wide" style="min-height: 600px;">
        <!-- 注册流程概览 -->
        <ul class="step-overview">
            <li class="step-item">
                1
                <div class="title">
                    {{type == 'supplier'?"请设置用户名":"Please Set The Username"}}
                </div>
            </li>
            <li class="step-item">
                2
                <div class="title">
                    {{type == 'supplier'?"填写账号信息":"Please Fill In The Account Information"}}
                </div>
            </li>
            <li class="step-item active">
                3
                <div class="title">
                    {{type == 'supplier'?"注册成功":"Registration Success"}}
                </div>
            </li>
        </ul>

        <!-- 注册成功提示信息 -->
        <div class="step3-tips-wrap">
            Registered Successfully！

            <template v-if="type == 'supplier'">
                <a @click="goToOpenShop">Open Shop Immediately</a>
                <div class="timer">
                        <span v-if="countDown >= 0">
                            {{countDown}}
                        </span>
                </div>
            </template>
            <template v-else>
                <a @click="goToIndexPage">Go To Index Page</a>
                <div class="timer">
                        <span v-if="countDown >= 0">
                            {{countDown}}
                        </span>
                </div>
            </template>
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
            type: "buyer", //注册人的类型 -  buyer 、 supplier
        },
        mounted() {
            let type = util_function_obj.GetQueryString("regType");
            if(type){
                this.type = type;
            }
            // 成功后直接登录
            setTimeout(()=>{
                this.autoLogin();
            },200);
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
            // 成功后跳转会回到 点击进行注册的页面
            goToIndexPage() {
                let whichPageToRegister = localStorage.getItem("whichPageToRegister") || "/";
                localStorage.removeItem("whichPageToRegister");
                window.location.href = whichPageToRegister;
            },
            // 点击前往开店流程，取消则直接跳转至首页
            goToOpenShop() {
                clearInterval(this.countDownTimer);

                this.$confirm('Are you sure to open shop now?', {
                    confirmButtonText: 'Confirm',
                    cancelButtonText: 'Cancel',
                    center: true,
                    customClass: "my-custom-element-alert-class fs-content-18",
                    callback: action => {
                        if(action == 'confirm'){
                            window.location.href = '/home/usr_UsrSupplier_supplierEntry';
                        }else{
                            window.location.href = '/';
                        }
                    }
                });
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
