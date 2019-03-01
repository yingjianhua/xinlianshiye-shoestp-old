<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="v3/header.jsp"></jsp:include>
<link rel="stylesheet" href="/home/v3/static/css/user/ureset.css"/>
<link rel="stylesheet" href="/home/v3/static/css/user/uindex.css"/>
<link rel="stylesheet" href="/home/v2/static/css/nav/new-top-nav-style.css"/>
<style>
    .el-badge__content.is-fixed.is-dot {
        right: 10px;
        top: 5px;
    }

    .el-button {
        width: 173px;
        height: 40px;
        font-size: 18px;
        background-color: #10389c;
        border-radius: 3px;
        color: #ffffff;
        margin-right: 10px;
    }

    .el-textarea__inner {
        resize: none
    }

    .el-upload img {
        width: 100%;
        height: 100%;
        object-fit: contain;
    }

</style>

</head>

<body>
<jsp:include page="v3/nav-nobody.jsp"></jsp:include>
<div id="personalCenter" class="clearfix" v-cloak>
    <div class="user-menu fl">
       <div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account
        </div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favoutities <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
    </div>
    <div class="user-main fr">
        <!-- 头部信息 -->
        <div class="user-info clearfix flexCc">
            <div class="user-info-item flexCc">
                <div class="flexCc">
                    <div class="avatar-box">
                        <div class="avatar">
                            <img @click="clickShowUploadAvatar"
                                 :src="userInfo.avatar?image(userInfo.avatar):'/home/v3/static/images/user/toux_me.png'"
                                 alt="">
                        </div>
                        <div class="user-info-item-name" style="color: #232323;">
                            {{userInfo.nickname}}
                        </div>
                        <div class="avatar-upload clearfix ripple fadeIn" v-if="isShowAvatarUpload">
                            <!-- action="https://jsonplaceholder.typicode.com/posts/" -->
                            <div class="fl upload-box">
                                <div>
                                    <el-upload
                                            drag
                                            class="avatar-uploader"
                                            :show-file-list="false"
                                            action="/home/usr_Purchase_upload"
                                            :on-success="handleAvatarSuccess"
                                            :before-upload="beforeAvatarUpload">
                                        <img :src="userInfo.avatar?image(userInfo.avatar):'/home/v3/static/images/user/upload-avatar.png'"
                                             class="avatar">
                                        <!-- <i v-else class="el-icon-plus avatar-uploader-icon"></i> -->
                                        <!-- <img v-else src="/home/v3/static/images/user/upload-avatar.png" alt=""> -->
                                    </el-upload>
                                </div>
                                <div class="upload-button" @click="clickUploadAvatar(userInfo.avatar)">submit</div>
                            </div>
                            <div class="fl upload-description">
                                <p>Click the button or drag the image onto the dotted box to upload</p>
                                <p>Upload JPG format,sized no larger than 3MB</p>
                            </div>
                        </div>
                    </div>
                    <div class="icon-id"><img src="/home/v3/static/images/user/icon_id.png" alt=""></div>

                </div>
            </div>
            <div class="user-info-item flexCc">
                <div>
                    <div class="flexCc">
                        <span style="font-size:36px;">{{userInfo.unreadMessagersCount}}</span>
                        <img src="/home/v3/static/images/user/icon_message.png" alt="" style="margin-left:10px;">
                    </div>
                    <div class="user-info-item-name">
                        Unread Messagers
                    </div>
                </div>
            </div>
            <div class="user-info-item flexCc">
                <div>
                    <div class="flexCc">
                        <span style="font-size:36px;">{{userInfo.requestsFromConnectionsCount}}</span>
                        <!-- <el-badge is-dot> -->
                        <el-badge>
                            <img src="/home/v3/static/images/user/icon_alarm.png" alt="" style="margin-left:10px;">
                        </el-badge>
                    </div>
                    <div class="user-info-item-name">
                        Requests from Connections
                    </div>
                </div>
            </div>
        </div>
        <!-- 问题表单 -->
        <div class="quotation">
            <div class="quotation-title clearfix">
                <h3 class="fl">Request for Quotation</h3>
            </div>
            <el-form ref="form" :model="form" :rules="rules" label-width="220px" class="quotation-form">
                <el-form-item label="Product Name :" prop="title">
                    <el-input v-model="form.title" placeholder="Please Enter The Product Name."></el-input>
                </el-form-item>
                <el-form-item label="Product Detailed Specification :" prop="descriotion">
                    <el-input type="textarea" :rows="6" v-model="form.descriotion"
                              placeholder="Please enter your Proct/Service Details"></el-input>
                </el-form-item>
                <el-form-item label="Estimates Order Quantity :" prop="quantity">
                    <el-row>
                        <el-col :span="7">
                            <el-input v-model="form.quantity"></el-input>
                        </el-col>
                        <el-col :span="5" :offset="1">
                            <el-form-item prop="unit">
                                <el-select v-model="form.unit" placeholder="Pairs">
                                    <el-option label="Pairs" value="1"></el-option>
                                    <el-option label="Forty-Foot Container" value="2"></el-option>
                                    <el-option label="Twenty-Foot Container" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button type="" @click="submitForm('form')">Confirm</el-button>
                    <a href="/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1"
                       style="color: #9fafd7;font: size 12px;" target="_blank">Add more requirements</a>
                </el-form-item>
            </el-form>
        </div>
        <!-- 收藏 -->
        <div class="favorites">
            <div class="favorites-title">
                <img src="/home/v3/static/images/user/icon_like_star.png" alt="">
                Favorites
            </div>
            <div class="favorites-main flexSb">
                <a href="" target="_blank"><img src="/home/v3/static/images/user/icon_left_d.png" alt=""></a>
                <ul class="favorites-list clearfix">
                    <li class="favorites-item" v-for="(item,index) in favoriteList" :key="index">
                        <a href="" target="_blank">
                            <div class="favorites-item-img">
                                <img :src="image(item.img,'?x-oss-process=image/resize,m_pad,h_150,w_150')" alt="">
                            </div>
                            <div class="favorites-item-name">
                                {{item.name}}
                            </div>
                        </a>
                    </li>
                </ul>
                <a href="" target="_blank"><img src="/home/v3/static/images/user/icon_right_d.png" alt=""></a>
            </div>
        </div>
        <!-- 个性化设置 -->
        <div class="personalize">
            <h3>Hi {{userInfo.nickname}}</h3>
            <p>Please complete your personalized profile to enjoy carefully selected product recommendations, access to
                selected & verified Trade Assurance sellers, and more.</p>
            <a href="" target="_blank">Personalize Now</a>
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
        data: {
            isShowAvatarUpload: false, // 头像上传框
            form: {
                title: '',
                descriotion: '',
                quantity: '',
                unit: '',
                currency: 1,
                pay_type: 1,
                shipping_type: 1
            },
            userInfo: [],
            favoriteList: [], //收藏列表
            rules: { //表单验证
                title: [{
                    required: true,
                    message: '请输入标题',
                    trigger: 'blur'
                },],

                descriotion: [{
                    required: true,
                    message: '请输入内容',
                    trigger: 'blur'
                }],
                quantity: [{
                    required: true,
                    message: '请输入数量',
                    trigger: 'blur'
                }],
                unit: [{
                    required: true,
                    message: '请选择单位',
                    trigger: 'change'
                }],
            },
        },
        mounted() {
            this.getUserInfo();
            this.getFavoriteList();
        },
        methods: {
            clickShowUploadAvatar() {  // 点击头像出现 头像上传
                this.isShowAvatarUpload = !this.isShowAvatarUpload
                // if(this.isShowAvatarUpload == false){
                //   this.getUserInfo();
                // }
            },
            getUserInfo() { // 获取个人信息
                var self = this;
                // axios.get('/home/usr_Purchase_profile')
                axios.get('/home/usr_Purchase_profile')
                    .then(function (res) {
                        console.log(res);
                        self.userInfo = res.data.result;
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            getFavoriteList() { // 获取收藏列表
                var self = this;
                axios.get('/home/usr_Purchase_favorite', {
                    params: {
                        start: 0,
                        limit: 5,
                    }
                })
                    .then(function (res) {
                        console.log(res);
                        if (res.data.ret == -1) {
                            window.location.href =
                                '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
                        } else if (res.data.ret == 1) {
                            self.favoriteList = res.data.result.items;
                        } else {
                            self.$message.error(res.data.msg);
                            return
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            submitForm(formName) { //表单提交
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let data = JSON.stringify(this.form)
                        console.log(this.form)
                        console.log('submit!');
                        axios.post('/home/rfq_RFQConsult_putRFQInquiry', data,
                            {headers: {'Content-Type': 'application/json'}}
                        )
                            .then((res) => {
                                console.log(res)
                                // 提交成功时
                                if (res.data.ret == 1) {
                                    // 提示信息
                                    this.$message({
                                        showClose: true,
                                        message: '提交成功',
                                        type: 'success'
                                    });
                                    // setTimeout(function () {
                                    //   gtag_report_conversion()
                                    //   window.location.href =
                                    //     '/home/usr_UsrConsult_listView';
                                    // }, 2000)
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 2000)
                                    // 未登录时
                                } else if (res.data.ret == -1) {
                                    window.location.href =
                                        '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
                                    // 提交失败时
                                } else {
                                    this.$alert(res.data.msg, {
                                        confirmButtonText: 'OK'
                                    });
                                }

                            })
                            .catch((err) => {
                                console.log(err)
                            })
                    } else {
                        console.log('error submit!!');
                        // if (!this.form.quantity) {
                        //   this.$message.error('请输入数量');
                        // } else if (!this.form.unit) {
                        //   this.$message.error("请选择单位");
                        // } else if (!this.form.descriotion) {
                        //   this.$message.error('请输入内容');
                        // }else if (!this.form.title) {
                        //   this.$message.error("请输入标题");
                        // }
                        // return false;
                    }
                });
            },
            // 头像上传
            handleAvatarSuccess(res, file) {
                console.log(res)
                console.log(res.result.url)
                console.log(file)
                this.userInfo.avatar = res.result.url
                console.log(this.userInfo.avatar)

            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt3M = file.size / 1024 / 1024 < 3;
                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt3M) {
                    this.$message.error('上传头像图片大小不能超过 3MB!');
                }
                return isJPG && isLt3M;
            },
            clickUploadAvatar(avatar) {
                var self = this;
                axios.post('/home/usr_Purchase_editAvatar', Qs.stringify({
                    avatar,
                }))
                    .then(function (res) {
                        console.log(res);
                        if (res.data.ret != 1) {
                            self.$message.error(res.data.msg);
                            return
                        }
                        ;
                        self.start = 0;
                        self.$message.success("提交成功");
                        self.isShowAvatarUpload = !self.isShowAvatarUpload
                        self.getUserInfo();
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            image(v, params) {
                if (!v) {
                    return ""
                }
                if (!params) {
                    params = ""
                }
                return "https://image.shoestp.com" + v + params
            },
        }
    })
</script>
</body>

</html>
