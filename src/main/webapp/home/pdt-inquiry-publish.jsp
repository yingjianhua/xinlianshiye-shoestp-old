<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="v3/header.jsp" />
<script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
<script src="/home/v2/static/js/base/qs.js"></script>
<style>
        .el-form-item__content{
            margin-left: 0 !important;
        }
        .el-upload--picture-card {
            border-width: 0;
            width: 90px;
            height: 90px;
            line-height: 90px;
        }
        .el-form-item{
            margin-bottom:0;
        }
        .el-upload-list--picture-card .el-upload-list__item-thumbnail{
            object-fit: contain;
        }
        .el-upload-list--picture-card .el-upload-list__item{
            width: 90px;
            height: 90px;
            margin-bottom: 0;
        }
    body,html,ul,ol,li,span,a,div,dl,dd,dt,p,h1,h2,h3,h4,h5,h6,select,option{
    padding: 0px;
    margin: 0px;
    box-sizing: border-box;
}
html,body{
    width:100%;
    height:100%;
}
body{
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
input{
    outline: none;
    border: none;
}
/* input[type="tel"],input[type="number"],input[type="text"]{
    padding: 5px 0;
} */
img,i,input{vertical-align: middle;}
ul{
    list-style: none;
}
.new-style-page a,.new-style-page a:link,.new-style-page a:visited,.new-style-page a:hover,.new-style-page a:active{
    display: inline-block;
    color: inherit;
    text-decoration: none;
}
.clearfix{zoom:1}
.clearfix:after{
    visibility: hidden;
    display: block;
    font-size: 0;
    content: " ";
    clear: both;
    height: 0;
}
[v-cloak]{
	display: none;
}
.fl{
    float: left;
}
.fr{
    float:right;
}
.block{
  display: block;
}
.tc{
  text-align: center;
}
/* 两行省略号 */
.ellipsis_2{
  overflow : hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
/* 3行省略号 */
.ellipsis_3{
  overflow : hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.img-contain{
  width: 100%;
  height: 100%;
  -o-object-fit: contain;
  object-fit: contain;
}
.por{
  position: relative;
}

    .xpMain {
  font-family: Lato-Regular;
  height: 100%; }
  .xpMain a,
  .xpMain a:link,
  .xpMain a:visited,
  .xpMain a:hover,
  .xpMain a:active {
    display: inline-block;
    color: inherit;
    text-decoration: none; }
  .xpMain .flexSb {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
        -ms-flex-align: center;
            align-items: center;
    -webkit-box-pack: justify;
        -ms-flex-pack: justify;
            justify-content: space-between; }
  .xpMain .w_1200 {
    width: 1200px;
    min-width: 1200px;
    margin: 0 auto; }
  .xpMain .w_1920 {
    width: 1920px;
    max-width: 1920px;
    margin: 0 auto; }
  .xpMain .xp-form {
    margin: 30px auto 20px auto;
    color: #232323;
    font-family: ArialMT;
    font-size: 14px;
    border-radius: 3px;
    border: solid 1px #ddd;
    background: #fff;
    font-size: 16px; }
    .xpMain .xp-form .textCenter {
      text-align: center; }
    .xpMain .xp-form .upImg {
      height: 100%;
      margin: 30px 0;
      padding: 0 30px; }
    .xpMain .xp-form .info {
      padding: 20px 30px;
      border-bottom: 1px solid #ddd; }
    .xpMain .xp-form .title {
      padding: 10px 30px; }
    .xpMain .xp-form .detail-box {
      width: 1200px;
      margin: 0 !important;
      padding: 20px 0;
      border-bottom: 1px solid #ddd; }
      .xpMain .xp-form .detail-box .detail {
        max-width: 500px; }
        .xpMain .xp-form .detail-box .detail a:hover {
          color: #409EFF; }

/*# sourceMappingURL=index.css.map */
    </style>

<body class="xpMain">
<jsp:include page="v3/nav.jsp"></jsp:include>

    <main id="xpApp" class="w_1200">
        <el-form :model="form" :rules="rules" ref="form" label-width="100px" class="xp-form">
            <!-- 第一行 -->
            <div class="info">
                <!-- 供应商头像 -->
                <img :src="image(supData.supLogo)" alt="" style="width:40px;height:40px;">
                <!-- 供应商名字 -->
                <span style="margin:0 10px;">{{supData.supName}}</span>
                <!-- 供应商等级 -->
                <%--<span style="font-size:12px;">{{supData.suplevel}}</span>--%>
            </div>
            <!-- 第二行 -->
            <el-row :gutter="100" style="margin:10px -50px 10px -50px;">
                <div class="title">
                    <el-col :span="14">
                        <div>Product information</div>
                    </el-col>
                    <el-col class="line" :span="5">
                        <div>Quantity</div>
                    </el-col>
                    <el-col :span="4">
                        <div>Unit</div>
                    </el-col>
                </div>
            </el-row>
            <el-row class="detail-box" :gutter="100" type="flex" align="middle">
                <el-col :span="2">
                    <div style="box-shadow:#333 0px 0px 10px;width:60px;height:60px;">
                        <!-- 商品图片 -->
                        <img :src="image(supData.image,'?x-oss-process=image/resize,w_60,h_60') + (supData.type==3 ?'/blur,r_5,s_30':'')" alt="" style="width:100%;height:100%;object-fit: contain;">
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="ellipsis_2 detail">
                        <a :href="'/home/pdt_PdtProduct_gtProductsInfo?id=' + supData.id" target="_blank">
                            <!-- 商品名字 -->
                            {{supData.title}}
                        </a>
                    </div>
                </el-col>
                <el-col class="" :span="5">
                    <el-form-item label="" prop="quantity">
                        <el-input size="medium" placeholder="Quantity" v-model.trim="form.quantity"
                            clearable>
                        </el-input>
                    </el-form-item>
                </el-col>
                <el-col class="" :span="5">
                    <el-form-item label="" prop="unitType">
                        <el-select v-model="form.unitType" placeholder="Unit" size="medium">
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <!-- 第三行 -->
            <el-row>
                <div style="padding:0 30px;">
                    <el-col :span="24">
                        <div style="margin:20px 0;">
                            <span style="color:red;">*</span> Message: Enter product details such as color, size,
                            materials
                            etc. and other
                            specification requirements to receive an accurate quote.
                        </div>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="" prop="descriotion">
                            <el-input placeholder="Enter product details such as color, size,materials etc. and other specification requirements to receive an accurate quote."
                                type="textarea" :rows="8" v-model.lazy.trim="form.descriotion"></el-input>
                        </el-form-item>
                    </el-col>
                </div>
            </el-row>
            <!-- 第四行 -->
            <el-row type="flex" justify="start">
                <el-col :span="24">
                    <div class="upImg">
                        <el-upload action="/home/usr_UsrConsult_upload" list-type="picture-card" :on-success="handlePictureCardPreview"
                            :limit="5" :on-remove="handleRemove" :before-upload="beforeUpload" accept=".jpg,.jpeg,.png">
                            <img src="/home/v3/static/images/upImg.png" style="width: 100%;height: 100%;object-fit: contain;">
                        </el-upload>
                    </div>
                </el-col>
            </el-row>
            <!-- 第五行 -->
            <el-row type="flex" justify="end">
                <div style="padding:30px;">
                    <el-form-item>
                        <el-button class="my-primary-btn" :disabled="flag" type="primary" @click="submitForm('form')">Send inquiry now</el-button>
                    </el-form-item>
                </div>
            </el-row>
            <el-input placeholder="" style="display:none;" v-model.lazy.trim="form.pdtId"></el-input>
            <el-input placeholder="" style="display:none;" v-model.lazy.trim="form.title"></el-input>
        </el-form>
        <div style="color:red">* Recommend matching suppliers if this supplier doesn't contact me on Message Center
            within 24 hours. RFQ</div>
    </main>
    <script>
        new Vue({
            el: "#xpApp",
            data(){
                return{
                    flag : false,
                     imgsToUpload: [], // 需要upload的img - 显示在页面上
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
                    pdtId: '',
                    quantity: '',
                    unitType: '',
                    descriotion: '',
                    title: '',
                    images: ''
                },
                rules: {
                    quantity: [
                            {required: true,message: 'Please enter the quantity',trigger: 'blur'},
                            { pattern: util_regular_obj.register.positiveInteger, message: "Please enter the positive integer，and can\'t beyond 10 digits" }
                        ],
                    unitType: [{
                        required: true,
                        message: 'Please select a unit',
                        trigger: 'change'
                    }],
                    descriotion: [{
                        required: true,
                        message: 'Please fill in the message',
                        trigger: 'blur'
                    }]
                },
                id: null,
                supData: [],
                }
            },
            mounted(){
                if (sessionStorage['Temp_Pdt_publish_form'] &&sessionStorage['Temp_Pdt_publish_form']!=''&&sessionStorage['Temp_Pdt_publish_form']!='null'){
                    this.form=JSON.parse(sessionStorage['Temp_Pdt_publish_form'])
                }
                // 进来页面获取到供应商信息
                var self = this;
                self.id = self.getQueryString("product_id");
                axios.post('/home/rfq_RFQConsult_getPdtInfo', Qs.stringify({
                        id: self.id,
                    }))
                    .then(function (res) {
                        console.log(res);
                        if(res.data.ret != 1){
                            self.$message.error('Failed to get information, please refresh the page and try again');
                        }else{
                            self.supData = res.data.result;
                            self.form.title = self.supData.title;
                        }
                    })
                    .catch(function (error) {
                        self.$message.error("Network error, please refresh the page and try again");
                        console.log(error);
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
                    return sysConfig.baseImageUrl + v + params
                },
                // elementui 上传功能 *2 - 删除操作
                handleRemove(file, fileList) {
                    // 清空imgs数组
                    this.imgsToUpload = [];
                    // 删除图片后，将授予的图片地址放入数组
                    $.each(fileList, (i, v) => {
                        console.log(v)
                        this.imgsToUpload.push(v.response.result.url)
                    })

                    if ((this.imgsToUpload.length <= 4)) {
                        $(".upImg .el-upload.el-upload--picture-card").show();
                    }
                },
                // elementui 上传功能 *2 - 上传成功操作
                handlePictureCardPreview(response, file, fileList) {
                    console.log(response)
                    console.log(file)
                    console.log(fileList)
                    // if (response.ret != 1) {
                    //     this.$message.error('Image upload failed, please refresh the page and try again');
                    //     return;
                    // }
                    if (response.ret != 1) return;
                    // 添加图片后，在前面显示 img
                    this.imgsToUpload.push(file.response.result.url);
                    if (this.imgsToUpload.length >= 5) {
                        $(".upImg .el-upload.el-upload--picture-card").hide();
                    }
                    console.log(this.imgsToUpload)

                },
                // 上传图片文件之前
                beforeUpload(file) {
                    if (!sysConfig || !sysConfig.user) {
                        sessionStorage['Temp_Pdt_publish_form']=JSON.stringify(this.form)
                        util_function_obj.alertWhenNoLogin(this);
                        return
                    }
                    let size = file.size / 1024;
                    if (size > 500) {
                        this.$message.error('Image size cannot exceed 500k');
                        return false;
                    }
                },
                submitForm(formName) { // 表单提交
                    if (!sysConfig || !sysConfig.user) {
                        sessionStorage['Temp_Pdt_publish_form']=JSON.stringify(this.form)
                        util_function_obj.alertWhenNoLogin(this);
                        return
                    }else{
                        if(sysConfig.user.user_type == 1){
                            this.$alert("Please register or login your buyer account if you want making enquiries.",{
                                confirmButtonText: 'Ok',
                                customClass: "my-custom-element-alert-class fs-content-18",
                                center: true,
                                callback: action =>{
                                    return
                                }
                            });
                            return
                        }else{
                            let url;
                            if (this.supData.type == 3) {
                                url = "/home/rfq_RFQConsult_putPrivateInquiry"
                            } else {
                                url = "/home/rfq_RFQConsult_putInquiry"
                            }
                            this.$refs[formName].validate((valid) => {
                                if (valid) {
                                    this.flag = true;
                                    console.log(this.form)
                                    this.form.images = this.imgsToUpload.join(",");
                                    this.form.pdtId = this.id;
                                    axios.post(url, this.form)
                                        .then((res) => {
                                            console.log(res)
                                            // 提交成功时
                                            if (res.data.ret == 1) {
                                                // 提示信息
                                                this.$message({
                                                    showClose: true,
                                                    message: 'Successfully released',
                                                    type: 'success'
                                                });
                                                setTimeout(function () {
                                                    sessionStorage.removeItem('Temp_Pdt_publish_form')
                                                    window.location.href = util_function_obj.GetParamsFullUrl('backUrl=','/');
                                                }, 1500)
                                            }else if (res.data.ret == -1) {
                                                sessionStorage['Temp_Pdt_publish_form'] = JSON.stringify(this.form)
                                                util_function_obj.alertWhenNoLogin(this);
                                                return
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
                                            this.$message.error("Network error, please refresh the page and try again");
                                            console.log(err)
                                        })
                                } else {
                                    console.log('error submit!!');
                                    return false;
                                }
                            });
                        }
                    }
                },
                getQueryString: (name) => {
                    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                    let reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
                    let r = window.location.search.substr(1).match(reg);
                    let q = window.location.pathname.substr(1).match(reg_rewrite);
                    if (r != null) {
                        return unescape(r[2]);
                    } else if (q != null) {
                        return unescape(q[2]);
                    } else {
                        return null;
                    }
                }
            }
        })
    </script>
</body>

</html>
