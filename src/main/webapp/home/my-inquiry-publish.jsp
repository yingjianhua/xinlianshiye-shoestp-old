<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- Google Tag Manager -->

    <script type="text/javascript" async="" src="./static/js/js.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/conversion_async.js">
    </script>
    <script type="text/javascript" async="" src="./static/js/analytics.js">
    </script>
    <%-- <script type="text/javascript" async="" src="./static/js/tracking.js">
    </script> --%>
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market--Shoestp.com,gathering 300 professional shoes manufacture companies
    </title>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js">
    </script>
    <script type="text/javascript" src="./static/js/lang/en.js">
    </script>
    <script type="text/javascript" src="./static/js/main.js">
    </script>
    <!--<link href="//www.shoestp.com/static/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script src="./static/js/swiper.min.js" type="text/javascript">
    </script>
    <script src="./static/js/lazyload.min.js" type="text/javascript">
    </script>
    <!-- <script type="text/javascript">
        $(function () {
            products_list_obj.init();
        });
    </script> -->
    <%-- <script src="./static/js/saved_resource" type="text/javascript">
    </script> --%>
    <script src="./static/js/qs.js" type="text/javascript">
    </script>
    <link href="./static/css/row_4.css" rel="stylesheet" type="text/css">
    <link href="./static/css/inquiry-publish.css" rel="stylesheet" type="text/css">

    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.6/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui@2.4.6/lib/index.js"></script>
    <style media="screen">
        /* 上传图片 预览的样式调整 */
        .el-upload--picture-card {
            border-width: 0;
            width: 90px;
            height: 90px;
        }

        .el-upload-list--picture-card .el-upload-list__item {
            width: 90px;
            height: 90px;
            margin-bottom: 0;
        }

        .el-upload-list--picture-card .el-upload-list__item-thumbnail {
            object-fit: contain;
        }

        /* 为了与原先样式对其 */
        select[name="Country"] {
            width: 237px;
            border: 1px solid rgb(221, 221, 221);
        }

        .null {
            border: 1px solid red !important;
        }
    </style>

    <%--统计代码--%>
</head>

<body class="lang_en w_1200">

<%@ include file="/home/template/web-top.jsp" %>

<%@ include file="/home/template/new-header.jsp" %>

<div id="main" class="wide">
    <div id="lib_user" class="clearfix">
        <div id="lib_user_crumb" class="widget">
            <ul class="crumb_box clearfix">
                <li class="home">
                    <a href="/home/usr_UsrPurchase" title="Home"><s:text name="Global.Home"/>
                        <i></i>
                    </a>
                </li>
                <li class="crumb1">
                    <a href="/home/usr_UsrPurchase_userIndex" title="My Account"><s:text
                            name="Global.My_Account"/>
                        <i></i>
                    </a>
                </li>
                <li class="crumb2 root">
                    <a href="/home/usr_UsrConsult_publishView" title="My Inquiry/RFQ"><s:text
                            name="my-inquiry-publish.View_Inquiry"/>
                        <i></i>
                    </a>
                </li>
            </ul>
        </div>

        <%@ include file="template/account/lib-user-menu.jsp" %>

        <div id="lib_user_main">
            <h1 class="lib_user_title"><s:text name="my-inquiry-publish.View_Inquiry"/></h1>
            <div id="lib_user_msg" class="clearfix">

                <div class="main_left">
                    <header>
                        <div>
                            <img src="./static/images/RFQ.png" alt="">
                        </div>
                        <div>
                            <h2 class="header-title2"><s:text name="Global.Request_A_Quote"/></h2>
                            <p style="font-size:14px;color: #666;"><s:text
                                    name="Global.One_Request"/></p>
                        </div>
                    </header>
                    <div>
                        <h1 class="header-title1"><s:text
                                name="my-inquiry-publish.Tell_The_Supplier_What_You_Need"/></h1>
                        <p class="font-bold" style="margin-bottom:10px;font-size: 18px;"><s:text
                                name="my-inquiry-publish.Complete_Your_Inquiry"/></p>
                        <p style="font-size:14px;color: #666;"><s:text
                                name="my-inquiry-publish.Information_Specific"/></p>
                    </div>
                    <form action="" method="post" enctype="multipart/form-data" id="inquiry_form">
                        <div class="form_header marginBottom flex">
                            <div class="input-wrap marginRight flex-grow required">
                                <input type="text"
                                       id="title"
                                       placeholder=" <s:text name='my-inquiry-publish.Keyword'/>"
                                       class="inputHeight"
                                       notnull=""
                                       v-model.lazy.trim="submitObj.v.title">
                            </div>
                            <input type="button"
                                   class="inputHeight btn-white-gradient btn-select-pdt"
                                   value="<s:text name='my-inquiry-publish.Select_Product'/>">
                        </div>


                        <div class="form_header marginBottom">
                            <div class="input-wrap marginRight required" style="width: 40%;">
                                <input type="text" placeholder=" <s:text name='Global.Name'/>"
                                       class="inputHeight"
                                       notnull=""
                                       v-model.lazy.trim="submitObj.v.name">
                            </div>
                            <div class="input-wrap required" style="width: 40%;">
                                <input type="email" placeholder=" <s:text name='Global.Email'/>"
                                       id="email"
                                       class="inputHeight" style="border: 1px solid #ddd;"
                                       notnull=""
                                       v-model.lazy.trim="submitObj.v.email">
                            </div>
                        </div>
                        <div class="form_header marginBottom">
                            <div class="input-wrap required">
                                <select name="Country" notnull="" class="inputHeight"
                                        notnull=""
                                        v-model="submitObj.v.country">
                                    <option value="undefined">---<s:text
                                            name="Global.Please_Choose"/>---
                                    </option>
                                    <option :value="country.id" v-for="country in countryList">
                                        {{country.name}}
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form_main marginBottom">
                            <div class="input-wrap required">
                          <textarea name="" id="" class="form_textarea"
                                    placeholder=" <s:text name='my-inquiry-publish.Demand'/>"
                                    notnull=""
                                    v-model.lazy.trim="submitObj.v.content"
                          ></textarea>
                            </div>

                            <%-- 添加 勾选商品后的图片显示 --%>
                            <div class="chooseImgWrap clearfix">
                                <div class="fl" style="width: 90px;height: 90px; margin-right: 8px;"
                                     v-if="selectGoodsIndex != -1">
                                    <img :src="'${envConfig.imageBaseUrl}'+goodsList[selectGoodsIndex].imgs.split(',')[0]"
                                         style="width: 100%;height: 100%;object-fit: contain;">
                                </div>

                                <div class="chooseImg fl">
                                    <el-upload
                                            action="/home/usr_UsrConsult_upload"
                                            list-type="picture-card"
                                            :on-success="handlePictureCardPreview"
                                            :limit="5"
                                            :on-remove="handleRemove">
                                        <%-- <i class="el-icon-plus"></i> --%>
                                        <img src="./static/images/upImg.png"
                                             style="width: 100%;height: 100%;object-fit: contain;">
                                    </el-upload>
                                </div>
                            </div>

                        </div>
                        <div class="form_footer marginBottom">
                            <div class="input-wrap required marginRight" style="width: 40%;">
                                <input type="text"
                                       placeholder=" <s:text name='my-inquiry-publish.Input_Quantity'/>"
                                       class="inputHeight"
                                       notnull="" name="Subject"
                                       v-model.lazy.trim.number="submitObj.v.quantity">
                            </div>
                            <div class="input-wrap required marginRight" style="width: 40%;">
                                <input type="text"
                                       placeholder=" <s:text name='Global.Verification_Code'/>"
                                       class="inputHeight"
                                       notnull=""
                                       v-model.lazy.trim="submitObj.vCode">
                            </div>
                            <div class="form_code">
                                <img src="/servlet/verify.img"
                                     onclick="this.src=&quot;/servlet/verify.img?r=&quot;+Math.random();"
                                     id="imgVcode"
                                     style="cursor:pointer; width: 100%; height: 100%; object-fit: contain;">
                            </div>
                        </div>
                        <div>
                            <div>
                                <input type="button" :disabled="flag"
                                       value="<s:text name='my-inquiry-publish.Submit_Inquiry'/>"
                                       class="submit" @click="submit">
                            </div>
                        </div>
                    </form>
                    <div class="footer">
                        <div>
                            <h3 class="header-title2"><s:text
                                    name="my-inquiry-publish.How_Fast"/></h3>
                            <div>
                                <span><s:text name="my-inquiry-publish.Submit_Inquiry"/></span> →
                                <span><s:text name="compareQuotes"/></span> → <span><s:text
                                    name="my-inquiry-dialog.Contact_Supplier"/></span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="main_right">
                    <div class="main_rightT">
                        <h3 class="header-title2" style="margin-top:0"><s:text
                                name="my-inquiry-publish.About_Publishing"/></h3>
                        <s:text name="my-inquiry-publish.About_Publishing0"/>
                        <!-- <p>Here, you can ask the price and delivery</p>
                        <p>deadline of products in our website.Besides,</p>
                        <p>you can post buy offers or customize the products.</p>
                        <p>After receiving your requirements,</p>
                        <p>we will match the suitable shoes factories for you.</p> -->
                    </div>
                    <div class="main_rightB">
                        <h3 class="header-title2"><s:text name="Global.Contact_Us"/></h3>
                        <p><s:text name="Global.Phone"/>：+86-577-85887585 <s:text
                                name="Global.Fax"/>：+86-577-89711316</p>
                        <p><s:text name="Global.Email"/>：marketing@shoestp.com</p>
                        <s:text name="my-inquiry-publish.Thanks_Attention"/>
                        <!-- <p>Thanks for your interest in our website</p>
                        <p>Any further suggestions are sincerely</p>
                        <p>welcomed by us xiuchuan@shoestp.cn</p> -->
                    </div>
                    <div class="main_rightB">
                        <s:text name="my-inquiry-publish.Learn_More"/>
                        <!-- <p>Please let suppliers know your detailed</p>
                        <p>requirements.You may include:</p>
                        <p>color,size,material,grade/standard,etc</p> -->
                    </div>
                    <div class="main_rightB">
                        <s:text name="my-inquiry-publish.Improve_Inquiry"/>
                        <!--  <p>Attachments like product</p>
                         <p>pictures/images would improve your</p>
                         <p>RFQ.</p> -->
                    </div>
                </div>


                <div class="modal"></div>
                <!-- 弹框 - select商品 -->
                <div class="pop-frame pop-frame-select-goods">
                    <div class="pop-frame-header">
                        <!-- 关闭按钮 -->
                        <div class="pop-frame-close">&times;</div>
                    </div>
                    <div class="pop-frame-content flex-start">
                        <div class="goods-list">
                            <div class="goods-item"
                                 :class="[goods.id==param_product_id?'selected':'']"
                                 :data-goods-id="goods.id"
                                 :data-product-id="goods.productId"
                                 :data-goods-index="index"
                                 v-for="(goods,index) in goodsList"
                                 :key="goods.id"
                                 @click="clickPdt"
                            >
                                <img class="btn-goods-delete" :data-goods-id="goods.id"
                                     src="./static/images/ico/del.png">
                                <div class="goods-pic">
                                    <img :src="'${envConfig.imageBaseUrl}'+goods.imgs.split(',')[0] || '/home/static/themes/default/mobile/images/1ed13be5ce.jpg.500x500.jpg'"
                                         alt="">
                                </div>
                                <div class="goods-descript">
                                    <div class="goods-name">{{goods.name}}</div>
                                    <div class="goods-color">

                                    </div>
                                    <div class="goods-spec">
                                        {{goods.code}}
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="" v-show="goodsList.length==0">
                            There is no goods to be selected.
                        </div>
                    </div>
                    <div class="pop-frame-footer">
                        <label for="use-title" class="fl">
                            <input type="checkbox" id="use-title">
                            <span><s:text
                                    name="my-inquiry-publish.Use_Product_Name_As_Title"/></span>
                        </label>
                        <div class="btn btn-red btn-confirm" @click="submitSelectedGoods"><s:text
                                name='Global.Submit'/></div>
                        <div class="btn btn-red btn-cancel"><s:text name="Global.Cancel"/></div>
                    </div>
                </div>
                <!-- 弹框 - select商品 - end -->


                <!-- 弹框 - 是否确认 -->
                <div class="pop-frame pop-frame-delete-product pop-small" style="display:none">
                    <div class="pop-frame-header">
                        <!-- 关闭按钮 -->
                        <div class="pop-frame-close">&times;</div>
                    </div>
                    <div class="pop-frame-content flex-start"
                         style="padding: 15px;text-align: center;">
                        Are you sure?
                    </div>
                    <div class="pop-frame-footer tx">
                        <div class="btn btn-red btn-confirm"><s:text name='Global.Submit'/></div>
                        <div class="btn btn-red btn-cancel"><s:text name="Global.Cancel"/></div>
                    </div>
                </div>
                <!-- 弹框 - select商品 - end -->


            </div>
        </div>
    </div>
</div>

<%@ include file="/home/template/new-foot.jsp" %>

<div id="hj_top" style="opacity: 0;">
    <img src="/home/static/images/hj_top.png">
</div>
<script>
  function gtag_report_conversion(url) {
    var callback = function () {
      if (typeof(url) != 'undefined') {
        window.location = url;
      }
    };
    gtag('event', 'conversion', {
      'send_to': 'AW-783435725/2sU4CMb_io8BEM2PyfUC',
      'event_callback': callback
    });
    return false;
  }
</script>

<script type="text/javascript">
  var vm = new Vue({
    el: "#lib_user_main",
    data: {
      imgsToUpload: []       // 需要upload的img - 显示在页面上
      , rq_mark: true        // 开关
      , goodsList: []        //供选择的商品列表
      , selectGoodsIndex: -1        //勾选的商品下标
      , countryList: []        //城市列表
      , param_product_id: -1 //传过来的id(询盘商品列表对应的id)
      , submitObj: {          // 需要上传给后台的对象
        vCode: "",
        v: {
          title: "",
          image: "",
          product: undefined,
          name: "",
          email: "",
          country: undefined,
          quantity: undefined,
          content: "",
          supplierId: -1  //商店进来时，携带 商店参数
        }
      },
      flag: false,
    },
    methods: {
      // elementui 上传功能 *2 - 删除操作
      handleRemove(file, fileList) {
        // 清空imgs数组
        this.imgsToUpload = [];
        // 删除图片后，将授予的图片地址放入数组
        $.each(fileList, (i, v) => {
          this.imgsToUpload.push(v.response.result.url)
        })

        if (!(this.selectGoodsIndex >= 0 && this.imgsToUpload.length >= 4)) {
          $(".chooseImg .el-upload.el-upload--picture-card").show();
        }
      },
      // elementui 上传功能 *2 - 上传成功操作
      handlePictureCardPreview(file) {
        if (file.ret != 1) return;

        // 添加图片后，在前面显示 img
        this.imgsToUpload.push(file.result.url);

        // 勾选商品后 - 上传的图片4张时，隐藏添加按钮
        if (this.selectGoodsIndex >= 0) {
          if (this.imgsToUpload.length >= 4) {
            $(".chooseImg .el-upload.el-upload--picture-card").hide();
          }
          // 没有勾选商品时，上传5张后再隐藏
        } else if (this.imgsToUpload.length >= 5) {
          $(".chooseImg .el-upload.el-upload--picture-card").hide();
        }
      },

      // 点击商品 显示选中 or 取消选中
      clickPdt(e) {
        let currentTarget = $(e.currentTarget);
        // 点击的是删除
        if ($(e.target).hasClass("btn-goods-delete")) {
          this.$confirm('<s:text name="my-inquiry-publish.Confirm_Delete"/>', {
            confirmButtonText: '<s:text name="Global.Yes"/>',
            cancelButtonText: '<s:text name="Global.No"/>',
            type: 'warning'
          }).then(() => {
            // 删除goods
            axios.post('/home/pdt_PdtConsultPdtList_deletes',
                Qs.stringify({ids: currentTarget.data("goodsId")}))
            .then((res) => {
              // 删除成功
              if (res.data.ret == 1) {
                currentTarget[0].style.display = "none";
                // 如果删除的是已选中的商品，清空显示在页面上的商品信息
                if (this.goodsList[this.selectGoodsIndex].id == e.target.dataset.goodsId) {
                  this.selectGoodsIndex = -1;
                  this.submitObj.v.product = "";
                }
                // this.goodsList.splice( currentTarget.data("goodsIndex"), 1 );
                this.$message({
                  type: 'success',
                  message: 'delete success!'
                });
              } else {
                this.$message({
                  type: 'success',
                  message: 'delete fail!'
                });
              }
            })
            .catch((err) => {
              console.log("delete goods err");
            });
          })
          // 否则是选中 事件
        } else {
          $(e.currentTarget).toggleClass("selected").siblings(".goods-item").removeClass(
              "selected");
        }
      },

      // 选好商品后提交商品 （确认弹框）
      submitSelectedGoods() {
        // 如果选中商品，复制图片地址 及 goodsId
        let target = $(".pop-frame-select-goods .goods-item.selected");
        if (target.length > 0) {
          this.selectGoodsIndex = target.data("goodsIndex");
          this.submitObj.v.product = target.data("productId");
          // 如果勾选了"use products title"
          if ($("#use-title").prop("checked")) {
            this.submitObj.v.title = target.find(".goods-name").text();
          }

          // 否则清空上传的img地址 及 goodsId
        } else {
          this.selectGoodsIndex = -1;
          this.submitObj.v.product = "";
        }

        // 模态框隐藏
        $(".modal").fadeOut();
        $(".pop-frame-select-goods").fadeOut();
      },

      // 整个form提交
      submit() {
        this.flag = true;
        if (!this.isFormatRight()) {
          this.flag = false;
          return;
        }
        gtag_report_conversion()
        // 拼接多图 地址
        // this.submitObj.v.image =   this.imgsToUpload.join(",");
        var goodsIndex = $(".pop-frame-select-goods .goods-item.selected").data("goodsIndex"); //获取选中的商品所在list的下标
        // 提交时，如果勾选了商品，则上传的图片第一张为该商品图，后面4张为上传的图片
        if ($(".pop-frame-select-goods .goods-item.selected").length > 0) {
          if (this.imgsToUpload.length > 4) {
            this.submitObj.v.image = this.goodsList[goodsIndex].imgs.split(',')[0] + ","
                + this.imgsToUpload.slice(0, 4).join(",");
          } else if (this.imgsToUpload.length == 0) {
            this.submitObj.v.image = this.goodsList[goodsIndex].imgs.split(',')[0]
          } else {
            this.submitObj.v.image = this.goodsList[goodsIndex].imgs.split(',')[0] + ","
                + this.imgsToUpload.join(",");
          }
        } else {
          this.submitObj.v.image = this.imgsToUpload.join(",");
        }

        // 如果没有选择goods，or没有上传图片
        if (this.submitObj.v.image.length == 0) {
          this.flag = false;
          this.$message.error('<s:text name="my-inquiry-publish.Some_Pictures"/>');
          return
        }

        if (this.rq_mark) {
          this.rq_mark = false;

          axios.post('/home/usr_UsrConsult_publish',
              Qs.stringify(this.submitObj, {allowDots: true}))
          .then((res) => {
            this.rq_mark = true;

            // 提交成功时
            if (res.data.ret == 1) {
              // 提示信息
              this.$message({
                showClose: true,
                message: '<s:text name='my-inquiry-publish.Successfully_Released'/>',
                type: 'success'
              });
              setTimeout(function () {
                window.location.href = '/home/usr_UsrConsult_listView';
              }, 2000)
              // 未登录时
            } else if (res.data.ret == -1) {
              if (this.param_product_id != -1) {
                window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView?product_id='
                    + this.param_product_id;
              } else {
                window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
              }
              // 提交失败时
            } else {
              this.flag = false;
              this.$alert(res.data.msg, {
                confirmButtonText: '<s:text name="my-inquiry-publish.Ok"/>'
              });
              $("#imgVcode").attr("src", "/servlet/verify.img?r=" + Math.random())
            }

          })
          .catch((err) => {
            this.flag = false;
            this.rq_mark = true;
          })
        }
      },

      // 判断form内容是否符合格式
      isFormatRight() {
        let inquiry_form = $('#inquiry_form');
        let notnull = $('*[notnull]', inquiry_form);
        notnull.removeClass('null');

        var status = 0;
        // 判断是否为空
        notnull.each((index, element) => {
          if ($(element).val() == '' || $(element).val() == "undefined") {
            $(element).addClass('null').focus();

            status = 1;
            // 提示信息
            this.$message.error('<s:text name="Global.Please_Complete_The_Form"/>');
            return false;
          } else {
            $(element).removeClass('null');
          }
        });

        // 标题长度限制
        if ($('#title').val().trim().length > 200) {
          $('#title').addClass('null').focus();
          status = 1;
          // 提示信息
          this.$message.error('<s:text name="Global.Please_Complete_The_Form"/>');
        }

        var Email = $('#email', inquiry_form);
        if (/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(
            Email.val()) == false) {
          Email.addClass('null').focus();
          status = 1;
          // 提示信息
          this.$message.error('<s:text name="Global.Correct_Email_Address"/>');
        }
        var quantity = $('input[name=Subject]', inquiry_form);
        if (/^[0-9]*$/.test(quantity.val()) == false || quantity.val() > 2147483647) {
          quantity.addClass('null').focus();
          status = 1;
          // 提示信息
          this.$message.error('<s:text name="my-inquiry-publish.Correct_Quantity"/>');
        }

        if (status) {
          return false;
        }
        return true;
      }

    },
    mounted() {
      // 获取国家列表
      axios.post('/home/plt_PltCountry_list')
      .then((res) => {
        this.countryList = res.data.result;
      })
      .catch((err) => {
        console.log("get countryList err");
      });

      // 获取商品列表
      this.submitObj.v.supplierId = getQueryString("supplierId") ? getQueryString("supplierId")
          : -1;
      axios.get('/home/pdt_PdtConsultPdtList_list',
          {params: {supplierId: this.submitObj.v.supplierId}})
      .then((res) => {
        console.log("res", res)
        if (res.data.ret == 1) {
          this.goodsList = res.data.result;

          // 如果是在商品页 添加了inquiry商品后 直接点进来的，则默认勾选该商品
          if (getQueryString("product_id")) {
            this.param_product_id = getQueryString("product_id");
            $.each(res.data.result, (index, value) => {
              if (value.id == this.param_product_id) {
                $("#use-title").prop("checked", true);
                this.selectGoodsIndex = index;
                this.submitObj.v.product = value.productId;
                this.submitObj.v.title = value.name;
              }
            })
          }
        } else if (res.data.ret == -1) {
          if (this.param_product_id != -1) {
            window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView?product_id='
                + this.param_product_id;
          } else {
            window.location.href = '/home/usr_UsrPurchase_sign?jumpUrl=/home/usr_UsrConsult_publishView';
          }
        }
      })
      .catch((err) => {
        console.log("get goodsList err");
      });

      // 点击选择商品title
      $(".btn-select-pdt").click(function () {
        // 清空之前选中的，如果之前选中并提交过，则选中该goods
        $(".pop-frame-select-goods .goods-item").removeClass("selected");
        if (vm.selectGoodsIndex != -1) {
          $(".pop-frame-select-goods .goods-item").eq(vm.selectGoodsIndex).addClass("selected");
        }
        $("#use-title").prop("checked", "checked");//liyichao
        $(".modal,.pop-frame-select-goods").fadeIn();
      })
      // 取消弹框 and 模态框
      $(".pop-frame .pop-frame-close,.pop-frame .btn-cancel").click(function () {
        $(".modal,.pop-frame,.pop-frame-select-goods").fadeOut();
      })
      // select 商品事件
      // $(".pop-frame-select-goods .goods-list").delegate(".goods-pic,.goods-descript", "click", function(){
      //   $(this).closest(".goods-item").toggleClass("selected3")
      //   // $(this).closest(".goods-item").toggleClass("selected").siblings(".goods-item").removeClass("selected");
      // })
      // select 商品 删除事件
      // $(".pop-frame-select-goods").delegate(".btn-goods-delete", "click", function(e){
      //   e.stopPropagation();
      // })
    }
  })
</script>
</body>

</html>
