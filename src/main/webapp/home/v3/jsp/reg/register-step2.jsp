<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/home/v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<link rel="stylesheet" href="/home/v3/static/css/reg/index.css"/>
</head>
<body class="bg-gray w_1240 page-register_2">
<jsp:include page="/home/v3/nav-nobody.jsp"></jsp:include>
<div id="app">
    <div class="placeholder wide" style="height: 93px;line-height: 93px;">
        <a href="/">
            <img class="logo" src="/home/v3/static/images/login/o2otoplogo.png" alt=""
                 style="width: 204px;height: 41px;">
        </a>
    </div>

    <main class="main wide">
        <!-- 注册流程概览 -->
        <ul class="step-overview">
            <li class="step-item">
                1
                <div class="title">
                    {{registerForm.user == 'supplier'?"请设置用户名":"Please Set The Username"}}
                </div>
            </li>
            <li class="step-item active">
                2
                <div class="title">
                    {{registerForm.user == 'supplier'?"填写账号信息":"Please Fill In The Account Information"}}
                </div>
            </li>
            <li class="step-item">
                3
                <div class="title">
                    {{registerForm.user == 'supplier'?"注册成功":"Registration Success"}}
                </div>
            </li>
        </ul>

        <!-- 填写账号信息 -->
        <div class="">
            <el-form class="count-info-form my-validate-form" size="medium"
                     ref="registerForm" label-width="135px"
                     :hide-required-asterisk="true"
                     :model="registerForm"
                     :rules="registerFormRules">
                <!-- 防止用户名、密码自动填充 -->
                <%--<input type="text" name="catch-name" class="incase-autocomplete-input">--%>
                <input type="password" name="catch-psd" class="incase-autocomplete-input">

                <el-form-item class="text-form-item have-info"
                              :label="registerForm.user=='buyer'?'Email':'邮箱'">
                    <b>{{registerForm.email}}</b>
                </el-form-item>

                <!-- 密码 -->
                <el-form-item class="pas-wrap"
                              :label="registerForm.user=='buyer'?'Password':'登录密码'"
                              id="psd" prop="psd">
                    <el-input :placeholder="registerForm.user=='buyer'?'Please enter your password':'请输入您的密码'"
                              type="password"
                              onkeyup="this.value=this.value.replace(/\s+/g,'')"
                              v-popover:popover-psd-strength
                              v-model.trim="registerForm.psd">
                    </el-input>
                    <!-- 密码强度显示 -->
                    <div class="isPsdRight" v-if="psdStrength.isPsdRight">
                        {{registerForm.user=='buyer'?'Strength':'密码强度'}}
                        <span class="red">
								{{registerForm.user=='buyer'?
									(psdStrength.acount>=3?'Strong':psdStrength.acount==2?'Medium':'Weak'):
									(psdStrength.acount>=3?'强':psdStrength.acount==2?'中':'弱')}}
							</span>
                    </div>
                </el-form-item>

                <!-- 重复密码 -->
                <el-form-item :label="registerForm.user=='buyer'?'Confirm Password':'密码确认'"
                              id="psd2" prop="psd2">
                    <div class="verification-code-wrap">
                        <el-input type="password"
                                  :placeholder="registerForm.user=='buyer'?'Please retype your password':'请再次输入您的密码'"
                                  v-model.trim="registerForm.psd2">
                        </el-input>
                    </div>
                </el-form-item>

                <!-- 人物选择 -->
                <el-form-item class="text-form-item"
                              :label="registerForm.user=='buyer'?'I am a':'我是'">
                    <label class="native-label">
                        <input type="radio" name="user" value="supplier" v-model="registerForm.user">
                        <%--{{registerForm.user=='buyer'?'Supplier':'供应商'}}--%>
                        供应商
                    </label>
                    <label class="native-label">
                        <input type="radio" name="user" value="buyer" v-model="registerForm.user">
                        <%--{{registerForm.user=='buyer'?'Buyer':'买家'}}--%>
                        Buyer
                    </label>
                </el-form-item>

                <!-- 只买家显示 -->
                <!-- 名字 -->
                <template v-if="registerForm.user=='buyer'">
                    <el-form-item label="Full name" key="fullName"
                                  id="fullName" prop="fullName">
                        <el-input placeholder="Please input fullname"
                                  v-model.trim="registerForm.fullName">
                        </el-input>
                    </el-form-item>

                    <!-- 国家 -->
                    <el-form-item label="Country Name" key="country"
                                  id="country" prop="country">
                        <el-select v-model.trim="registerForm.country" style="width: 100%;"
                                   placeholder="Please select the country">
                            <el-option
                                    v-for="country in countryList"
                                    :label="country.name"
                                    :value="country.id"
                                    :key="country.id"
                            ></el-option>
                        </el-select>
                    </el-form-item>

                    <!-- 电话 -->
                    <el-form-item label="Tel" class="tel-wrap">
                        <div class="tel-inner">
                            <el-form-item class="tel-inner1" id="telPrefix1" key="telPrefix1" prop="telPrefix1">
                                <el-input
                                        v-model.trim="registerForm.telPrefix1">
                                </el-input>
                            </el-form-item>

                            <span>-</span>

                            <el-form-item class="tel-inner2" id="telPrefix2" key="telPrefix2" prop="telPrefix2">
                                <el-input
                                        v-model.trim="registerForm.telPrefix2">
                                </el-input>
                            </el-form-item>

                            <span>-</span>

                            <el-form-item class=" tel-inner3" id="tel" prop="tel" key="tel">
                                <el-input placeholder="Please enter your telephone"
                                          v-model.trim="registerForm.tel">
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-form-item>
                </template>


                <!-- 只供应商显示 -->
                <template v-else>
                    <!-- 供应商公司全称 -->
                    <el-form-item label="公司全称" key="supCompanyName"
                                  id="supCompanyName" prop="supCompanyName">
                        <el-input placeholder="请输入营业执照上的公司名称"
                                  v-model.trim="registerForm.supCompanyName">
                        </el-input>
                    </el-form-item>

                    <!-- 供应商联系人姓名 -->
                    <el-form-item label="联系人姓名">
                        <div class="two-division">
                            <el-form-item id="supFirstName" key="supFirstName" prop="supFirstName" class="half">
                                <el-input placeholder="姓"
                                          v-model.trim="registerForm.supFirstName">
                                </el-input>
                            </el-form-item>
                            <el-form-item id="supLastName" key="supLastName" prop="supLastName" class="half">
                                <el-input placeholder="名"
                                          v-model.trim="registerForm.supLastName">
                                </el-input>
                            </el-form-item>
                        </div>
                    </el-form-item>

                    <!-- 供应商 电话 -->
                    <el-form-item label="电话号码" key="supTel"
                                  id="supTel" prop="supTel">
                        <el-input placeholder="请输入电话号码"
                                  v-model.trim="registerForm.supTel">
                        </el-input>
                    </el-form-item>

                    <!-- 供应商地址 -->
                    <el-form-item label="联系地址">
                        <div class="three-division">
                            <el-form-item id="supAddrCountry" prop="supAddrCountry" class="one-third"
                                          key="supAddr-wrap"
                                          error="">
                                <el-select v-model.trim="registerForm.supAddrCountry"
                                           key="supAddrCountry"
                                           placeholder="请选择省">
                                    <el-option
                                            v-for="province in provinceList"
                                            :label="province.name"
                                            :value="province.id"
                                            :key="province.id"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item id="supAddrCity" prop="supAddrCity" class="one-third">
                                <el-select v-model.trim="registerForm.supAddrCity"
                                           :disabled="registerForm.supAddrCountry<=0"
                                           key="supAddrCity"
                                           placeholder="请选择市">
                                    <el-option
                                            v-for="city in cityList"
                                            :label="city.name"
                                            :value="city.id"
                                            :key="city.id"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item id="supAddrTown" prop="supAddrTown" class="one-third">
                                <el-select v-model.trim="registerForm.supAddrTown"
                                           :disabled="registerForm.supAddrCountry<=0 || registerForm.supAddrCity<=0"
                                           key="supAddrTown"
                                           placeholder="请选择县">
                                    <el-option
                                            v-for="town in townList"
                                            :label="town.name"
                                            :value="town.id"
                                            :key="town.id"
                                    ></el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                    </el-form-item>

                    <!-- 具体地址 -->
                    <el-form-item class="detail-addr-input" id="supAddrDetail" prop="supAddrDetail" key="supAddrDetail">
                        <el-input type="textarea" resize="none"
                                  placeholder="请填写详细地址"
                                  v-model.trim="registerForm.supAddrDetail">
                        </el-input>
                    </el-form-item>

                </template>

                <!-- 本地code -->
                <el-form-item :label="registerForm.user=='buyer'?'Verification Code':'验证码'" id="code" prop="code"
                              key="code">
                    <div class="verification-code-wrap">
                        <el-input
                                :placeholder="registerForm.user=='buyer'?'Please enter the code':'请填写验证码'"
                                v-model.trim="registerForm.code">
                        </el-input>
                        <img :src="codeUrl" alt="Verification code"
                             class="pic-code">
                        <img src="/home/v3/static/images/login/icon_re.png" alt="icon recycle"
                             class="icon-recycle"
                             @click="refreshCode">
                    </div>
                </el-form-item>

                <!-- 协议书 -->
                <div class="agreement">
                    <label class="label" style="box-sizing: border-box;">
                        <input type="checkbox" checked v-model="isAgreeAgreement">
                    </label>
                    <!-- 英文显示 -->
                    <div class="agreement-list" v-if="registerForm.user=='buyer'">
                        <p>Upon creating my account, I agree to:</p>
                        <p>
                            - The 
                            <%--<span class="link">shoestp.com User Agreement</span>--%>
                            <a class="link" href="/home/cnt_CntSglPageCategory_gosglpageshoestp?pkey=9" target="_blank">shoestp.com User Agreement</a>
                        </p>
                        <p>- Receive emails related to shoestp.com membership and services</p>
                    </div>
                    <!-- 中文显示 -->
                    <div class="agreement-list" v-else>
                        <p>创建网站账号的同时，我同意：</p>
                        <p>
                            - 遵守
                            <%--<span class=a"link">shoestp.com</span>--%>
                            <a class="link" href="/home/cnt_CntSglPageCategory_gosglpageshoestp?pkey=9" target="_blank">shoestp.com</a>
                            会员协议
                        </p>
                        <p>- 愿意接收相关来自shoestp.com的会员及服务邮件</p>
                    </div>
                </div>

                <el-form-item>
                    <el-button type="primary" @click="submit">
                        {{registerForm.user=='buyer'?'Continue':'下一步'}}
                    </el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 填写账号信息 - end -->

    </main>


    <!-- 密码强度提示 -->
    <el-popover
            placement="right"
            ref="popover-psd-strength"
            width="322"
            trigger="focus"
            popper-class="my-popover-tips-psd">
        <!-- 密码强度条 -->
        <div class="psd-strength">
            <div class="title">{{registerForm.user=='buyer'?'Strength':'密码强度'}}</div>
            <ul class="strength-bar">
                <li class="bar"
                    :class="{active: psdStrength.acount >= (index+1)}"
                    v-for="(strength, index) in (psdStrength.arr.length || 3)">
                </li>
            </ul>
            <div class="strength">
                {{registerForm.user=='buyer'?
                (psdStrength.acount>=3?'Strong':psdStrength.acount==2?'Medium':'Weak'):
                (psdStrength.acount>=3?'强':psdStrength.acount==2?'中':'弱')}}
            </div>
        </div>
        <ul class="psd-strength-tips-list">
            <!-- <li class="tip-item" v-for="(tip, index) in psdStrength.tips">
                {{tip}}
                <img src="/home/v3/static/images/forgetPassword/icon_dui_green.png" class="isRight" alt="" v-if="psdStrength.arr[index]">
                <img src="/home/v3/static/images/forgetPassword/icon_cuo_blue.png" class="isError" alt="" v-else>
            </li> -->
            <li class="tip-item">
                {{registerForm.user=='buyer'?'6 to 20 characters':'6到20个字符'}}
                <img src="/home/v3/static/images/forgetPassword/icon_dui_green.png" class="isRight" alt="" v-if="psdStrength.arr[0]">
                <img src="/home/v3/static/images/forgetPassword/icon_cuo_blue.png" class="isError" alt="" v-else>
            </li>
            <li class="tip-item">
                {{registerForm.user=='buyer'?
                'Only consists of letters,numbers and symbols':
                '仅限字母、数字、以及符号'}}

                <img src="/home/v3/static/images/forgetPassword/icon_dui_green.png" class="isRight" alt="" v-if="psdStrength.arr[1]">
                <img src="/home/v3/static/images/forgetPassword/icon_cuo_blue.png" class="isError" alt="" v-else>
            </li>
            <li class="tip-item">
                {{registerForm.user=='buyer'?
                'Contains at least two of the following:letters numbers and symbols':
                '至少包含以下中的两种: 字母、数字、以及符号'}}
                <img src="/home/v3/static/images/forgetPassword/icon_dui_green.png" class="isRight" alt="" v-if="psdStrength.arr[2]">
                <img src="/home/v3/static/images/forgetPassword/icon_cuo_blue.png" class="isError" alt="" v-else>
            </li>
        </ul>
    </el-popover>
    <!-- 密码强度提示 - end -->
</div>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    // 密码验证
    const validatePsd = (rule, value, callback) => {
        // 计算密码强度
        app.psdStrength.arr = [];
        app.psdStrength.score = 0;
        app.psdStrength.acount = 0;

        // 密码强度 - 是否符合条件*1
        if (value.length>=6 && value.length<=20) {
            app.psdStrength.arr.push(true)
        }else {
            app.psdStrength.arr.push(false)
        }

        // 密码强度 - 是否符合条件*2
        if (/^[a-zA-Z0-9_\-!@#$%^\~\`&*),.?(_+=\/>\\<\[\]\{\|\};':"]+$/.test(value)) {
            app.psdStrength.arr.push(true)
        } else {
            app.psdStrength.arr.push(false)
        }

        // 密码强度 - 是否符合条件*3
        // if (/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/.test(value)) {
        if (/^(?![0-9]+$)(?![a-zA-Z]+$)(?![_\-!@#$%^\~\`&*),.?(_+=\/>\\<\[\]\{\|\};':"]+$)[a-zA-Z0-9_\-!@#$%^\~\`&*),.?(_+=\/>\\<\[\]\{\|\};':"]{1,}$/.test(value)) {
            app.psdStrength.arr.push(true)
        } else {
            app.psdStrength.arr.push(false)
        }

        // 密码强度得分
        // 密码长度
        if(value.length>=6 && value.length<10){
            app.psdStrength.score += 10;
        }
        if(value.length>=10){
            app.psdStrength.score += 20;
        }
        // 是否含数字
        if(/[0-9]+/.test(value)){
            app.psdStrength.score += 10;
        }
        // 是否含小写字母
        if(/[a-z]+/.test(value)){
            app.psdStrength.score += 10;
        }
        // 是否含大写字母
        if(/[A-Z]+/.test(value)){
            app.psdStrength.score += 10;
        }
        // 是否含特殊字符
        if(/[#_\-!@$%^\~\`&*),.?(_+=\/>\\<\[\]\{\|\};':"]+/.test(value)){
            app.psdStrength.score += 10;
        }

        // 根据得分判断密码强弱
        if(app.psdStrength.score <= 30){
            app.psdStrength.acount = 1;
        }else if(app.psdStrength.score <= 40){
            app.psdStrength.acount = 2;
        }else if(app.psdStrength.score > 40){
            app.psdStrength.acount = 3;
        }

        // 正式的密码验证
        if (value === '') {
            app.psdStrength.isPsdRight = false;
            callback(new Error(app.registerForm.user == 'buyer' ? 'Password can\'t be empty!' : '密码不能为空'));
        // } else if (value.length < 6 || value.length > 20) {
        } else if (!(util_regular_obj.register.psd.test(value))) {
            app.psdStrength.isPsdRight = false;
            callback(new Error(app.registerForm.user == 'buyer' ? 'Please set password within 6 to 20 characters' : '请设置6-20位的密码'));
        // } else if( !(/^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)]|[])+$)([^(0-9a-zA-Z)]|[]|[a-zA-Z]|[0-9]){6,}$/.test(value))  ){
        // } else if( (/^[^\s]{6,20}$/.test(value))  ){
            // app.psdStrength.isPsdRight = false;
            // callback(new Error(app.registerForm.user == 'buyer' ? 'Please enter two of the three formats: numbers, letters and symbols.' : '请输入以下三种格式中的两种：数字、字母及符号'));
        }else{
            app.psdStrength.isPsdRight = true;
            if (app.registerForm.psd2 !== '') {
                app.$refs.registerForm.validateField('psd2');
            }
            callback();
        }
    };

    // 确认密码验证
    const validatePsd2 = (rule, value, callback) => {
        // 正式的密码验证
        if (value === '') {
            callback(new Error(app.registerForm.user == 'buyer' ? 'Please enter password again' : '请再次输入密码'));
        } else if (value !== app.registerForm.psd) {
            callback(new Error(app.registerForm.user == 'buyer' ? 'The two passwords are inconsistent!' : '两次密码不一致'));
        } else {
            callback();
        }
    };
    // 姓名 - 名字验证
    const validateSupFirstName = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('联系人姓氏不可为空'));
        }else if(app.registerForm.supLastName != ""){
            app.$refs.registerForm.validateField('supLastName');
            callback();
        }else{
            callback();
        }
    };
    // 姓名 - 名字验证
    const validateSupLastName = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('联系人名字不可为空'));
        } else if ( !util_regular_obj.register.nameGlobal.test( app.registerForm.supFirstName + app.registerForm.supLastName ) ) {
            callback(new Error('请填写真实姓名'));
        } else {
            callback();
        }
    };

    // 电话号码验 - buyer - 电话 + 2个前缀
    const validateTelPrefix1 = (rule, value, callback) => {
        // 正式的密码验证
        if (value === '') {
            callback();
        }else{
            app.$refs.registerForm.validateField('tel');
            callback();
        }
    };
    const validateTelPrefix2 = (rule, value, callback) => {
        app.$refs.registerForm.validateField('tel');
        callback();
    };
    const validateTel = (rule, value, callback) => {
        if (value === '') {
            // callback(new Error(app.registerForm.user == 'buyer' ? 'Telephone number can\'t be empty!' : '电话号码不能为空'));
            callback();
        }else if(!util_regular_obj.register.phoneAreaCode.test(app.registerForm.telPrefix1+"-"+app.registerForm.telPrefix2+app.registerForm.tel)){
            callback(new Error('Telephone number\'s format is incorrect，please check the prefix-tel and phone'));
        } else {
            // app.$refs.registerForm.validateField('telPrefix1');
            // app.$refs.registerForm.validateField('telPrefix2');
            callback();
        }
    };

    // code码验 - 提示信息为中英文 - 静态改不了message - 只能动态写了！
    const validateCode = (rule, value, callback) => {
        if (value === '') {
            callback(new Error(app.registerForm.user == 'buyer' ? 'Code can\'t be empty!' : '验证码不能为空'));
        } else {
            callback();
        }
    };

    var app = new Vue({
        el: "#app",
        data: {
            testB: "nihao1",
            isAgreeAgreement: true, //是否勾选已读
            codeUrl: "/servlet/verify.img", // 本地验证码 - 刷新用
            psdStrength: { //密码强度
                isPsdRight: false, //密码是否符合要求
                arr: [],    //密码强度 三个规则的匹配 true、false
                score: 0, //密码得分
                acount: 0, //密码强度
            },
            registerForm: {
                email: '',
                psd: '',
                psd2: '',
                user: 'supplier',   //buyer 、 supplier
                code: '',

                // 以下为买家信息
                fullName: '',
                country: '',
                telPrefix1: '',
                telPrefix2: '',
                tel: '',

                // 以下为供应商信息
                supCompanyName: '', //供应商公司全称
                supFirstName: '', //供应商 联系人姓氏
                supLastName: '', //供应商 联系人名字
                supTel: '', //供应商 联系电话
                supAddrCountry: '', //供应商 省
                supAddrCity: '', //供应商 市
                supAddrTown: '', //供应商 区县
                supAddrDetail: '', //供应商 详细地址
            },
            registerFormRules: {
                psd: [
                    {validator: validatePsd, trigger: 'change'},
                ],
                psd2: [
                    {validator: validatePsd2, trigger: 'blur'}
                ],
                fullName: [
                    {required: true, message: 'FullName can\'t be empty!', trigger: 'blur'},
                    {
                        pattern: util_regular_obj.register.nameGlobal,
                        message: 'Please enter the real name',
                        trigger: 'blur'
                    }
                ],
                country: [
                    {required: true, message: 'Country can\'t be empty!', trigger: 'change'}
                ],
                telPrefix1: [
                    {validator: validateTelPrefix1, trigger: 'blur'},
                    // {required: true, message: 'Telephone prefix can\'t be empty!', trigger: 'blur'}
                ],
                telPrefix2: [
                    {validator: validateTelPrefix2, trigger: 'blur'},
                    // {required: true, message: 'Telephone prefix can\'t be empty!', trigger: 'blur'}
                ],
                tel: [
                    {validator: validateTel, trigger: 'blur'},
                ],
                code: [
                    {validator: validateCode, trigger: 'blur'},
                ],

                //下面是联系商的rule
                supCompanyName: [
                    {required: true, message: '公司名称不可为空', trigger: 'blur'},
                    {
                        pattern: util_regular_obj.register.companyName,
                        message: '请输入正确的公司名称',
                        trigger: 'blur'
                    }
                ],
                supFirstName: [
                    // {required: true, message: '联系人姓名不可为空', trigger: 'blur'},
                    // {
                    //     pattern: util_regular_obj.register.nameGlobal,
                    //     message: '请填写真实姓名',
                    //     trigger: 'blur'
                    // }
                    {validator: validateSupFirstName, trigger: 'blur'}
                ],
                supLastName: [
                    // {required: true, message: '联系人姓名不可为空', trigger: 'blur'},
                    // {
                    //     // pattern: /^[\u4E00-\u9FA5]{1,6}$/,
                    //     pattern: util_regular_obj.register.nameGlobal,
                    //     message: '请填写真实姓名',
                    //     trigger: 'blur'
                    // }
                    {validator: validateSupLastName, trigger: 'blur'}
                ],
                supTel: [
                    {required: true, message: '电话号码不可为空!', trigger: 'blur'}, {
                        pattern: util_regular_obj.register.phoneChina,
                        // pattern: /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/,
                        message: '电话号码格式不正确',
                        trigger: 'blur'
                    }
                ],
                supAddrCountry: [
                    {required: true, message: '请选择具体的省', trigger: 'change'}
                ],
                supAddrCity: [
                    {required: true, message: '请选择具体的城市',}
                ],
                supAddrTown: [
                    {required: true, message: '请选择具体的区县', trigger: 'change'}
                ],
                supAddrDetail: [
                    {required: true, message: '请填写详细的联系地址', trigger: 'blur'}
                ],
            },
            myErrorRules: { //省市区 提交时才验证
                supAddrCountry: "请选择具体的省",
                supAddrCity: "请选择具体的城市",
                supAddrTown: "请选择具体的区县",
            },
            countryList: [], //省列表
            provinceList: [], //省列表
            cityList: [], //市列表
            townList: [], //区列表
        },
        mounted() {
            this.getCountryList();
            this.getProvinceList();
            console.log("registerEmail's:")
            this.registerForm.email = this.GetQueryString("email") || localStorage.getItem("registerEmail")
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
            // 刷新验证码
            refreshCode() {
                if (this.codeUrl.indexOf("?r=")) {
                    this.codeUrl = this.codeUrl.split("?r=")[0] + '?r=' + Math.random();
                } else {
                    this.codeUrl = this.codeUrl + '?r=' + Math.random();
                }
            },

            // 节流节流 - 连续点击时，不生效 - 过了ms时间后点击才生效
            throttle(cb, ms) {
                var self = this;
                let startTime = Date.now()
                return function () {
                    args = arguments;
                    let currentTime = Date.now()
                    let remaining = ms - (currentTime - startTime)
                    clearTimeout(self.throttleTimer)
                    if (remaining <= 0) {
                        cb.apply(self, args);
                        startTime = Date.now()
                    } else {
                        self.throttleTimer = setTimeout(() => {
                            cb.apply(self, args);
                        }, remaining)
                    }
                }
            },

            // 获取省列表
            getProvinceList() {
                axios.get('/home/plt_PltProvinces_list')
                    .then((res) => {
                        console.log("getProvinceList suc")
                        console.log(res.data.result)
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg || "Get province list error,please try again later");
                            return
                        }
                        ;
                        this.provinceList = res.data.result;
                    })
                    .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                    });
            },
            // 获取省对应的市列表
            getCityList(provinceId) {
                axios.get('/home/plt_PltCity_findByProvince', {
                    params: {
                        province: provinceId
                    }
                })
                    .then((res) => {
                        console.log("getCityList suc")
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg || "Get city list error,please try again later");
                            return
                        }
                        ;
                        this.cityList = res.data.result;
                    })
                    .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                    });
            },
            // 获取市对应的区列表
            getTownList(cityId) {
                axios.get('/home/plt_PltArea_findByCity', {
                    params: {
                        city: cityId
                    }
                })
                    .then((res) => {
                        console.log("getTownList suc")
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg || "Get town list error,please try again later");
                            return
                        }
                        ;
                        this.townList = res.data.result;
                    })
                    .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                    });
            },
            // 获取国家列表 - buyer用
            getCountryList() {
                axios.get('/home/plt_PltCountry_list',)
                    .then((res) => {
                        console.log("获取国家列表 suc")
                        if (res.data.ret != 1) {
                            this.$message.error(res.data.msg || "Get country list error,please try again later");
                            return
                        }
                        ;
                        this.countryList = res.data.result;
                    })
                    .catch((error) => {
                        this.$message.error(error || 'Network error,please try again later');
                    });
            },

            // 提交账号信息
            submit() {
                this.$refs.registerForm.validate((valid, errObj) => {
                    // 验证全部正确时
                    if (valid) {
                        // 请勾选已读
                        if (!this.isAgreeAgreement) {
                            this.registerForm.user == 'buyer' ? 'Confirm Password' : '密码确认'
                            this.$message.error(this.registerForm.user == 'buyer' ? 'Please confirm that you have read the relevant terms and conditions.' : '请确认您已阅读相关条款');
                            return
                        }

                        // buyer\supplier传送的数据不同
                        let postData = {};
                        if (this.registerForm.user == "buyer") {
                            postData = {
                                pwd: this.registerForm.psd,
                                pwdA: this.registerForm.psd2,
                                checkCode: this.registerForm.code,
                                telPre: null,  //此处为buyer信息
                                telMid: null,
                                telAft: null,
                                uid: getParams("uid", ""),
                                bean: {
                                    email: this.registerForm.email,
                                    identity: this.registerForm.user == "buyer" ? 0 : 1,
                                    nickname: this.registerForm.fullName, //此处为buyer信息
                                    country: this.registerForm.country,
                                    // facebookUserId: 0,
                                    // googleUserId: 0,
                                }
                            }
                            // 电话可以不写（不传值），写了才传3个值
                            if(this.registerForm.tel){
                                postData.telPre = this.registerForm.telPrefix1;
                                postData.telMid = this.registerForm.telPrefix2;
                                postData.telAft = this.registerForm.tel;
                            }
                        } else {
                            postData = {
                                pwd: this.registerForm.psd,
                                pwdA: this.registerForm.psd2,
                                checkCode: this.registerForm.code,
                                firstName: this.registerForm.supFirstName, //此处为供应商信息
                                lastName: this.registerForm.supLastName,
                                uid: getParams("uid", ""),
                                bean: {
                                    email: this.registerForm.email,
                                    identity: this.registerForm.user == "buyer" ? 0 : 1,
                                    company: this.registerForm.supCompanyName,  //此处为供应商信息
                                    telphone: this.registerForm.supTel,
                                    province: this.registerForm.supAddrCountry,
                                    city: this.registerForm.supAddrCity,
                                    zone: this.registerForm.supAddrTown,
                                    address: this.registerForm.supAddrDetail,
                                    // facebookUserId: 0,
                                    // googleUserId: 0,
                                }
                            }
                        }

                        // 第三方注册时携带对应的参数id
                        // test - 未测试
                        let thirdName = localStorage.getItem("thirdName");
                        let thirdId = localStorage.getItem("thirdId");
                        if (thirdName && thirdId) {
                            postData.bean[thirdName + "UserId"] = thirdId;
                        }

                        axios.post('/home/usr_UsrMain_regist', Qs.stringify(postData, {allowDots: true}))
                            .then((res) => {
                                this.refreshCode();
                                if (res.data.ret != 1) {
                                    this.$message.error(res.data.msg || "Register error,please try again later");
                                    return
                                }
                                ;
                                // 保存密码，注册成功后直接登录用
                                localStorage.setItem("registerEmail", this.registerForm.email)
                                localStorage.setItem("registerPsd", this.registerForm.psd)
                                // 注册成功跳转至成功页面
                                window.location.href = window.location.href + "&code=status&regType=" +  this.registerForm.user
                            })
                            .catch((error) => {
                                this.refreshCode();
                                this.$message.error(error || 'Network error,please try again later');
                            });

                        console.log('submit');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });

            },

        },
        watch: {
            ['registerForm.user']: {
                handler(newVal) {
                    // 清空验证后，验证显示后的form-item
                    this.$refs.registerForm.clearValidate();
                    let keysArr = Object.keys(this.registerFormRules);
                    this.$nextTick(() => {
                        for (var i = 0, len = keysArr.length; i < len; i++) {
                            let value = this.registerForm[keysArr[i]];
                            let idDom = document.getElementById(keysArr[i]);
                            // 当该item有值 && 该对象存在时进行验证（v-if=true）
                            if (value && idDom) {
                                this.$refs.registerForm.validateField(keysArr[i]);
                            }
                        }
                    })
                }
            },
            ['registerForm.supAddrCountry']: {
                handler(newVal) {
                    console.log("probince change:")
                    console.log(newVal)
                    this.registerForm.supAddrCity = "";
                    this.registerForm.supAddrTown = "";
                    this.getCityList(newVal);
                }
            },
            ['registerForm.supAddrCity']: {
                handler(newVal) {
                    console.log("city change:")
                    console.log(newVal)
                    this.registerForm.supAddrTown = "";
                    this.getTownList(newVal);
                }
            },
        }
    })

</script>
</body>
</html>
