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
    .el-button:focus, .el-button:hover{
        color: #ffffff;
        border-color: #10389c;
        background-color: #10389c;
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
<script src="/home/v3/static/js/index-top.js"></script>
<div id="personalCenter" class="clearfix" v-cloak>
    <index-top></index-top>
    <div class="user-menu fl">
       <div class="user-menu-title"><img src="/home/v3/static/images/user/icon_account.png" alt="" style="margin:0 8px 2px 0;">My Account
        </div>
        <div class="user-menu-item"><a style="color:#10389c;" href="/home/usr_UsrPurchase_userIndex">Home <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrMessages_center">Message Center <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_contacts">Contacts <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrFavorites_myfavorite">My Favourites <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
        <div class="user-menu-item"><a href="/home/usr_UsrPurchase_usrSetting">Account Settings <img src="/home/v3/static/images/user/icon_right.png" alt=""></a></div>
    </div>
    <div class="user-main fr">
        <!-- 头部信息 -->
        <div class="user-info clearfix flexCc">
            <div class="user-info-item flexCc">
                <div class="flexCc">
                    <div class="avatar-box" ref="avatarUpload">
                        <div class="avatar">

                            <img @click="clickShowUploadAvatar"
                                 :src="userInfo.avatar?image(userInfo.avatar):'/home/v3/static/images/user/toux_me.png'"
                                 alt="">
                        </div>
                        <div class="user-info-item-name" style="color: #232323;">
                            {{userInfo.nickname?userInfo.nickname:'Nickname'}}
                        </div>
                         <transition name="user-fade-in">
                        <div class="avatar-upload clearfix  " v-if="isShowAvatarUpload">
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
                    </transition>
                    </div>
                    <div class="icon-id">
                        <a href="/home/usr_UsrPurchase_usrSetting">
                             <img src="/home/v3/static/images/user/icon_id.png" alt="">
                        </a>
                    </div>

                </div>
            </div>
            <div class="user-info-item flexCc">
                 <a href="/home/usr_UsrMessages_center">
                    <div>
                        <div class="flexCc">
                            <span style="font-size:36px;">{{userInfo.unreadMessagersCount}}</span>
                            <img src="/home/v3/static/images/user/icon_message.png" alt="" style="margin-left:10px;">
                        </div>
                        <div class="user-info-item-name">
                            Unread Messagers
                        </div>
                    </div>
                </a>
            </div>
            <div class="user-info-item flexCc">
                <a href="/home/usr_UsrPurchase_contacts">
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
                  </a>
            </div>
        </div>
        <!-- 问题表单 -->
        <div class="quotation">
            <div class="quotation-title clearfix">
                <h3 class="fl">Request for Quotation</h3>
            </div>
            <el-form ref="form" :model="form" :rules="rules" label-width="220px" class="quotation-form">
                <el-form-item label="Product Name :" prop="title">
                    <el-input v-model.trim="form.title" placeholder="Please Enter The Product Name."></el-input>
                </el-form-item>
                <el-form-item label="Product Detailed Specification :" prop="descriotion">
                    <el-input type="textarea" :rows="6" v-model.trim="form.descriotion"
                              placeholder="Please enter your Proct/Service Details"></el-input>
                </el-form-item>
                <el-form-item label="Estimates Order Quantity :" prop="quantity">
                    <el-row>
                        <el-col :span="7">
                            <el-input v-model.trim="form.quantity" placeholder="Please enter the quantity"></el-input>
                        </el-col>
                        <el-col :span="5" :offset="1">
                            <el-form-item prop="unit">
                                    <el-select v-model="form.unit" placeholder="Unit">
                                            <el-option
                                            v-for="item in options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                          </el-option>
                                    </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button :disabled="flag" type="button" @click="submitForm('form')">Confirm</el-button>
                    <a @click="ToRFQ" style="color: #9fafd7;font: size 12px;cursor: pointer;">Add more requirements</a>
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
                <template v-if="favoriteList.length <= 0">
                    <div class="favorites-list" style="font-size: 20px;color: #7f7f7f;text-align:center;line-height:169px;">No collection items</div>
                </template>
                <template v-else>
                    <a href="/home/usr_UsrFavorites_myfavorite"><img src="/home/v3/static/images/user/icon_left_d.png" alt=""></a>
                    <ul class="favorites-list clearfix">
                        <li class="favorites-item" v-for="(item,index) in favoriteList" :key="index">
                            <a :href="'/home/pdt_PdtProduct_gtProductsInfo?id='+item.pdtPkey" target="_blank">
                                <div class="favorites-item-img">
                                    <img :src="image(item.img,'?x-oss-process=image/resize,m_pad,h_150,w_150')" alt="">
                                </div>
                                <div class="favorites-item-name">
                                    {{item.name}}
                                </div>
                            </a>
                        </li>
                    </ul>
                    <a href="/home/usr_UsrFavorites_myfavorite"><img src="/home/v3/static/images/user/icon_right_d.png" alt=""></a>
                </template>
            </div>
        </div>
        <!-- 个性化设置 功能没做  先隐藏 -->
        <!-- <div class="personalize">
            <h3>Hi {{userInfo.nickname}}</h3>
            <p>Please complete your personalized profile to enjoy carefully selected product recommendations, access to
                selected & verified Trade Assurance sellers, and more.</p>
            <a href="javascript: void(0);">Personalize Now</a>
        </div> -->
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
            var validateQuantity = (rule, value, callback) => {
                let re = /^\+?[1-9][0-9]*$/;
                if (!value) {
                    return callback(new Error('Please enter the quantity'));
                }
                if(!re.test(value)){
                    return callback(new Error("Can't start with 0, can't have decimal point"));
                }
                callback();
            };
            return{
                flag : false, 
                isShowAvatarUpload: false, // 头像上传框
                options: [{
                        value: "1",
                        label: "Pairs"
                    },
                        {
                            value: "2",
                            label: "Forty-Foot Container"
                        },
                        {
                            value: "3",
                            label: "Twenty-Foot Container"
                        },
                    ],
            form: {
                title: '',
                descriotion: '',
                quantity: '',
                unit: '1',
                currency: 1,
                pay_type: 1,
                shipping_type: 1
            },
            userInfo: [],
            favoriteList: [], //收藏列表
            rules: { //表单验证
                title: [{
                    required: true,
                    message: 'Please enter the product name',
                    trigger: 'blur'
                },],

                descriotion: [{
                    required: true,
                    message: 'Please enter your Proct/Service Details',
                    trigger: 'blur'
                }],
                quantity: [{required: true,validator: validateQuantity, trigger: 'blur' }],
                unit: [{
                    required: true,
                    message: 'Please select a unit',
                    trigger: 'change'
                }],
            },
            }

        },
         created(){
             
            document.addEventListener('click',(e)=>{
            //     console.log("this.$refs.dl.contains(e.target)");
            // console.log(this.$refs.dl.contains(e.target));
            if(!this.$refs.avatarUpload.contains(e.target)){
                this.isShowAvatarUpload = false;
                if(this.userInfo.avatar){
                    this.userInfo.avatar = this.userInfo.avatar;
                }else{
                    this.userInfo.avatar = ''
                }
            }
        })
        },
        mounted() {
            if(!isLogin){
                util_function_obj.alertWhenNoLogin(this);
                return
             }
            this.getUserInfo();
            this.getFavoriteList();
        },
        methods: {
            // 跳转RFQ   
            ToRFQ(){
                let url = "/home/usr_UsrConsult_publishView?title=&quantity=null&chooesValue=1"+ "&backUrl=" + window.location.href
                util_function_obj.supplierCantEnter(this, url);
             },
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
                        if (res.data.ret == 1) {
                            self.userInfo = res.data.result;
                        }  else {
                            self.$message.error(res.data.msg || "Failed to get personal information, please refresh the page and try again");
                        }
                    })
                    .catch(function (error) {
                        self.$message.error("Network error, please refresh the page and try again");
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
                         if (res.data.ret == 1) {
                            self.favoriteList = res.data.result.items;
                        } else {
                            self.$message.error(res.data.msg || "Failed to get the list of favorites, please refresh the page and try again");
                            return
                        }
                    })
                    .catch(function (error) {
                        self.$message.error("Network error, please refresh the page and try again");
                        console.log(error);
                    });
            },
            submitForm(formName) { //表单提交
                if(!isLogin){ 
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }else{
                        // 登录了
                        if(sysConfig.user.user_type == 1){
                            this.$alert("Sorry, the supplier cannot submit the form",{
                                confirmButtonText: 'Ok',
                                customClass: "my-custom-element-alert-class fs-content-18",
                                center: true,
                                callback: action =>{
                                    return
                                }
                            });
                            return
                        }else{
                            // 普通用户
                            this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.flag = true;
                        let data = JSON.stringify(this.form)
                        // console.log(this.form)
                        // console.log('submit!');
                        axios.post('/home/rfq_RFQConsult_putRFQInquiry', data,
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
                                    setTimeout(function () {
                                        window.location.reload();
                                    }, 1500)
                                }
                                // 提交失败时
                                 else {
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
                        }
                    }
                
               
            },
            // 头像上传
            handleAvatarSuccess(res, file) {
                // console.log(res)
                // console.log(res.ret)
                // console.log(res.result.url)
                // console.log(file)
                // this.userInfo.avatar = res.result.url
                // console.log(this.userInfo.avatar)
                if(res.ret == 1){
                    this.$set(this.userInfo,"avatar",res.result.url)
                }else{
                    this.$message.error('Avatar upload failed, please refresh the page and try again');
                }
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt3M = file.size / 1024 / 1024 < 3;
                if (!isJPG) {
                    this.$message.error('Upload avatar image can only be JPG format');
                }
                if (!isLt3M) {
                    this.$message.error("Upload avatar image size can't exceed 3MB!");
                }
                return isJPG && isLt3M;
            },
            clickUploadAvatar(avatar) {
                if(!isLogin){
                    util_function_obj.alertWhenNoLogin(this);
                    return
                }
                var self = this;
                axios.post('/home/usr_Purchase_editAvatar', Qs.stringify({
                    avatar,
                }))
                    .then(function (res) {
                        // console.log(res);
                        if (res.data.ret != 1) {
                            self.$message.error(res.data.msg || "Avatar upload failed, please refresh the page and try again");
                            return
                        }
                        ;
                        self.start = 0;
                        self.$message.success("Submitted Successfully");
                        self.isShowAvatarUpload = !self.isShowAvatarUpload
                        self.getUserInfo();
                    })
                    .catch(function (error) {
                        this.$message.error("Network error, please refresh the page and try again");
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
