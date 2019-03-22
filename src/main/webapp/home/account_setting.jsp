<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v3/static/css/user/ureset.css"/>
<link rel="stylesheet" href="/home/v3/static/css/user/uindex.css"/>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<style>
    .el-button--primary {
        width: 100px;
        height: 36px;
        background-color: #10389c;
        border-radius: 4px;
        color: #ffffff;
        border-color: #10389c;
    }
   
    .el-button--primary:hover {
        color: #ffffff;
        border-color: #10389c;
        background-color: #10389c;
    }
    .el-button--primary.is-disabled, .el-button--primary.is-disabled:active, .el-button--primary.is-disabled:focus, .el-button--primary.is-disabled:hover{
        color: #ffffff;
        border-color: #10389c;
        background-color: #10389c;
    }
    
</style>

</head>

<body>
<jsp:include page="v3/nav-nobody.jsp"></jsp:include>
<script src="/home/v3/static/js/index-top.js"></script>
<div id="personalCenter" class="clearfix" v-cloak>
    <index-top></index-top>
    <div class="user-menu fl">
       <div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account
        </div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favourites <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a style="color:#10389c;" href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
    </div>
    <div class="setting-main fr">
        <div class="account-setting-box">
            <h1>Account Settings</h1>
            <div class="section1">
                <el-form status-icon :model="form1" :rules="rules" ref="form1" label-width="170px" class="">
                <el-row>
                    <el-col :span="12" :offset="6">
                        <el-form-item label="Gender" prop="gender">
                            <el-select v-model="form1.gender" placeholder="Please select gender">
                                <el-option label="confidentiality" value="0"></el-option>
                                <el-option label="men" value="1"></el-option>
                                <el-option label="women" value="2"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="First Name:" prop="firstName">
                            <el-input v-model.trim="form1.firstName" placeholder="Please enter a firstName"/>
                        </el-form-item>
                        <el-form-item label="Surname:" prop="surname">
                            <el-input v-model.trim="form1.surname" placeholder="Please enter a surname"/>
                        </el-form-item>
                    </el-col>
                   </el-row>
                        <el-row>
                                <el-col :span="12" :offset="6">
                                    <el-form-item label="Phone:" prop="phone">
                                <el-input v-model.trim="form1.phone" placeholder="Please enter your phone"/>
                            </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                    <div style="font-size:10px;line-height: 41.38px;margin-left: 10px;color: #999;">Example:0086-12345678,+86-12345678</div>
                                </el-col>
                        </el-row>
                        <el-row>
                                <el-col :span="12" :offset="6">
                            <el-form-item label="Company:" prop="company">
                                <el-input v-model.trim="form1.company" placeholder="Please enter the company name"/>
                            </el-form-item>
                            <el-form-item label="Address:" prop="address">
                                <el-input v-model.trim="form1.address" placeholder="Please enter the address"/>
                            </el-form-item>
                            <el-form-item>
                                <el-button :disabled="flag" type="primary" @click="submitForm1('form1')">Save</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div class="section2">
                <el-row>
                    <el-col :span="4">
                        <div style="line-height:40px;">Change Email Address</div>
                    </el-col>
                    <el-col :span="12" :offset="2">
                        <el-form status-icon :model="form2" :rules="rules" ref="form2" label-width="170px" class="">
                            <el-form-item label="Existing Password:" prop="password">
                                <el-input type="password" v-model.trim="form2.password" placeholder="Please enter your password"/>
                            </el-form-item>
                            <el-form-item label="New Email Address:" prop="email">
                                <el-input type="email" v-model.trim="form2.email" placeholder="Please input the email address"/>
                            </el-form-item>
                            <el-form-item>
                                <el-button :disabled="flag" type="primary" @click="submitForm2('form2')">Save</el-button>
                            </el-form-item>
                        </el-form>
                    </el-col>
                </el-row>
            </div>
            <div class="section3">
                <el-row>
                    <el-col :span="4">
                        <div style="line-height:40px;">Change Password</div>
                    </el-col>
                    <el-col :span="12" :offset="2">
                        <el-form status-icon :model="form3" :rules="rules" ref="form3" label-width="170px" class="">
                            <el-form-item label="Existing Password:" prop="password">
                                <el-input type="password" v-model.trim="form3.password" placeholder="Please enter your password"/>
                            </el-form-item>
                            <el-form-item label="New Password:" prop="newPassword">
                                <el-input type="password" v-model.trim="form3.newPassword" placeholder="Please enter a new password"/>
                            </el-form-item>
                            <el-form-item label="Confirm Password:" prop="ckPwd">
                                <el-input type="password" v-model.trim="form3.ckPwd" placeholder="Please enter the confirmation password"/>
                            </el-form-item>
                            <el-form-item>
                                <el-button :disabled="flag" type="primary" @click="submitForm3('form3')">Save</el-button>
                            </el-form-item>
                        </el-form>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</div>
<script src="/home/v3/static/js/index-bottom.js"></script>
<div id="bottom" style="margin-top: 25px">
    <index-bottom></index-bottom>
</div>
<script>
    new Vue({
        el: "#bottom"
    })
</script>

<script>
    new Vue({
        el: "#personalCenter",
        data() {
            var validatePhone = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('Please input the phone number'));
                    return
                }
                if(!(util_regular_obj.register.phoneAreaCode.test(this.form1.phone))){
                    callback(new Error('Please enter the correct phone number'));
                    return
                }else{
                    callback()
                }
            };
            var validateEmail = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('Please input the email address'));
                    return
                }
                setTimeout(() => {
                    if (!(util_regular_obj.register.email.test(this.form2.email))) {
                        callback(new Error('Please enter the correct mailbox format'))
                    } else {
                        callback()
                    }
                }, 100)
            };
            var validatePass = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('Please enter your password'));
                }else if(!(util_regular_obj.register.psd.test(this.form3.newPassword))){
                    callback(new Error('Please enter a 6 to 20 digit password, no spaces'));
                } else {
                    if (this.form3.ckPwd !== '') {
                        this.$refs.form3.validateField('ckPwd');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('Please enter your password again'));
                } else if (value !== this.form3.newPassword) {
                    callback(new Error('Inconsistent input password twice!'));
                } else if(!(util_regular_obj.register.psd.test(this.form3.ckPwd))){
                    callback(new Error('Please enter a 6 to 20 digit password, no spaces'));
                }else {
                    callback();
                }
            };
            return {
                flag : false, 
                accountInfo: [], // 账号设置信息
                form1: {
                    gender: '',  //性别
                    surname: '', // 姓氏
                    firstName: '', // 名字
                    phone: '', // 手机
                    company: '', // 公司
                    address: '', // 地址
                },
                form2: {
                    password: '', // 密码
                    email: '',  // 新的邮箱
                },
                form3: {
                    password: '',  // 现在的密码
                    newPassword: '',  // 新的密码
                    ckPwd: '',  // 确认密码
                },
                rules: { //表单验证
                    firstName: [{required: true,message: 'Please enter a firstName',trigger: 'blur'},
                        { pattern: util_regular_obj.register.nameGlobal, message: 'Name cannot exceed 32 digits' }
                    ],
                    surname: [{ required: true,message: 'Please enter a surname',trigger: 'blur'},
                        { pattern: util_regular_obj.register.nameGlobal, message: 'Name cannot exceed 32 digits' }
                    ],
                    
                    phone:[{validator: validatePhone, trigger: 'blur', required: true,}],
                    address: [{message: 'Please enter the address',trigger: 'blur'},
                        { pattern: /^[^!@~`%^&*()+|\\}{":?/].{0,180}$/, message: 'Cannot enter special symbols, up to 180 digits' }
                    ],
                    company: [{required: true,message: 'Please enter the company name',trigger: 'blur'},
                        { pattern: util_regular_obj.register.companyName, message: 'Name cannot exceed 50 digits' }
                    ],
                    gender: [{
                        message: 'Please select gender',
                        trigger: 'change'
                    }],
                    password: [
                        {required: true,message: 'Please enter your password',trigger: 'blur'},
                        { pattern: util_regular_obj.register.psd, message: 'Please enter the correct password' }
                    ],
                    email: [{validator: validateEmail, trigger: 'blur', required: true,}],
                    newPassword: [
                        {validator: validatePass, trigger: ['blur', 'change'], required: true,}
                    ],
                    ckPwd: [
                        {validator: validatePass2, trigger: ['blur', 'change'], required: true,}
                    ],
                },

            }

        },
        mounted() {
            this.getAccountSettings();
        },
        methods: {
            getAccountSettings() { // 获取个人账号设置信息
                let self = this;
                axios.get('/home/usr_Purchase_accountProfile')
                    .then(function (res) {
                        console.log(res);
                        if(res.data.ret != 1){
                            self.$message.error("Failed to get information, please refresh the page and try again");
                            return;
                        }else{
                            self.accountInfo = res.data.result;
                            self.form1.gender = res.data.result.gender.toString();
                            self.form1.firstName = res.data.result.firstName;
                            self.form1.surname = res.data.result.surname;
                            self.form1.phone = res.data.result.phone;
                            self.form1.company = res.data.result.company;
                            self.form1.address = res.data.result.address;
                            self.form2.email = res.data.result.email;
                        }
                    })
                    .catch(function (error) {
                        self.$message.error("Network error, please refresh the page and try again");
                        console.log(error);
                    });
            },
            submitForm1(formName) { // 第一部分表单提交    信息
                if(!sysConfig.user){
                    util_function_obj.alertWhenNoLogin(this);
                        return
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.flag = true;
                        let data = JSON.stringify(this.form1)
                        // console.log('submit!');
                        // console.log(data)
                        axios.post('/home/usr_Purchase_editAccount', data,
                            {headers: {'Content-Type': 'application/json'}}
                        )
                            .then((res) => {
                                // console.log(res)
                                // 提交成功时
                                if (res.data.ret == 1) {
                                    // 提示信息
                                    this.$message({
                                        showClose: true,
                                        message: 'Submitted successfully',
                                        type: 'success'
                                    });
                                    // setTimeout(function () {
                                    //   gtag_report_conversion()
                                    //   window.location.href =
                                    //     '/home/usr_UsrConsult_listView';
                                    // }, 2000)
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500)
                                } else {
                                    this.flag = false;
                                    this.$alert(res.data.msg || "Failed to submit the form, please refresh the page and try again", {
                                        confirmButtonText: 'OK',
                                        customClass: "my-custom-element-alert-class fs-content-18",
                                    });
                                }
                            })
                            .catch((err) => {
                                this.flag = false;
                                self.$message.error("Network error, please refresh the page and try again");
                                console.log(err)
                            })
                    } else {
                        console.log('error submit!!');
                        // if (!this.form1.firstName) {
                        //   this.$message.error('名字不能为空');
                        // } else if (!this.form1.company) {
                        //   this.$message.error("公司不能为空");
                        // } else if (!this.form1.phone) {
                        //   this.$message.error('手机不能为空');
                        // }else if (!this.form1.surname) {
                        //   this.$message.error('姓氏不能为空');
                        // }
                        // return false;
                    }
                });
            },
            submitForm2(formName) { // 第二部分表单提交    邮箱
                if(!sysConfig.user){
                    util_function_obj.alertWhenNoLogin(this);
                        return
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.flag = true;
                        // console.log('submit!');
                        axios.post('/home/usr_Purchase_changeEmail', Qs.stringify({
                            email: this.form2.email,
                            password: this.form2.password
                        }))
                            .then((res) => {
                                // console.log(res)
                                // 提交成功时
                                if (res.data.ret == 1) {
                                    // 提示信息
                                    this.$message({
                                        showClose: true,
                                        message: 'Submitted successfully',
                                        type: 'success'
                                    });
                                    // setTimeout(function () {
                                    //   gtag_report_conversion()
                                    //   window.location.href =
                                    //     '/home/usr_UsrConsult_listView';
                                    // }, 2000)
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500)
                                } else {
                                    this.flag = false;
                                    this.$alert(res.data.msg || "Failed to submit the form, please refresh the page and try again", {
                                        confirmButtonText: 'OK',
                                        customClass: "my-custom-element-alert-class fs-content-18",
                                    });
                                }
                            })
                            .catch((err) => {
                                this.flag = false;
                                self.$message.error("Network error, please refresh the page and try again");
                                console.log(err)
                            })
                    } else {
                        console.log('error submit!!');
                        // if (!this.form2.password) {
                        //   this.$message.error('密码不能为空');
                        // } else if (!this.form2.email) {
                        //   this.$message.error("邮箱不能为空");
                        // } else if (!(/[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/.test(this.form2.email))) {
                        //   this.$message.error("请输入正确的邮箱地址!");
                        // }
                        // return false;
                    }
                });
            },
            submitForm3(formName) { // 第三部分表单提交    密码
                if(!sysConfig.user){
                    util_function_obj.alertWhenNoLogin(this);
                        return
                }

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.flag = true;
                        axios.post('/home/usr_Purchase_changePassword', Qs.stringify({
                            password: this.form3.password,
                            newPassword: this.form3.newPassword
                        }))
                            .then((res) => {
                                // console.log(res)
                                // 提交成功时
                                if (res.data.ret == 1) {
                                    // 提示信息
                                    this.$message({
                                        showClose: true,
                                        message: 'Submitted successfully',
                                        type: 'success'
                                    });
                                    // setTimeout(function () {
                                    //   gtag_report_conversion()
                                    //   window.location.href =
                                    //     '/home/usr_UsrConsult_listView';
                                    // }, 2000)
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500)
                                    // 未登录时
                                }  else {
                                    this.flag = false;
                                    this.$alert(res.data.msg || "Failed to submit the form, please refresh the page and try again", {
                                        confirmButtonText: 'OK',
                                        customClass: "my-custom-element-alert-class fs-content-18",
                                    });
                                }

                            })
                            .catch((err) => {
                                this.flag = false;
                                self.$message.error("Network error, please refresh the page and try again");
                                console.log(err)
                            })
                    } else {
                        // console.log('error submit!!');
                        // console.log(this.form3.password.length)
                        // console.log(this.form3.ckPwd.length)
                        // if (!this.form3.password) {
                        //   this.$message.error('密码不能为空');
                        // } else if (this.form3.password.length <= 8) {
                        //   this.$message.error("密码长度最少8位数");
                        // }else if (!this.form3.newPassword) {
                        //   this.$message.error("新密码不能为空");
                        // }else if (this.form3.newPassword.length <= 8) {
                        //   this.$message.error("新密码长度最少8位数");
                        // }else if (!this.form3.ckPwd) {
                        //   this.$message.error("确认密码不能为空");
                        // }else if (this.form3.ckPwd.length <= 8) {
                        //   this.$message.error("确认密码长度最少8位数");
                        // }
                        // return false;
                    }
                });
            },
        }
    })
</script>
</body>

</html>
