<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><s:text name="Global.Order_Details"/></title>
    <link rel="stylesheet" href="./static/css/order_print.css" type="text/css">
    <script src="https://cdn.bootcss.com/vue/2.5.17-beta.0/vue.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  	<link rel="stylesheet" href="https://unpkg.com/element-ui@2.4.6/lib/theme-chalk/index.css">
  	<script src="https://unpkg.com/element-ui@2.4.6/lib/index.js"></script>
    <script type="text/javascript" src="/home/static/js/qs.js"></script>
</head>
<body>
<!--startprint-->
<div class="main" id="app" v-cloak>
    <!--标题-->
    <h1 class="title">{{orderInfo.supplierShowName}}</h1>
    <!--详细信息-->
    <div class="order-details">

        <ul class="order-info-group">
            <li class="sub-title"><s:text name="Order_Information"/>:</li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Order_Number"/>:</div><span class="content">{{orderInfo.number}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="User_Name"/>:</div><span class="content">{{orderInfo.usrEmail}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Order_Time"/>:</div><span class="content">{{changeTime(orderInfo.date)}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Order_Status"/>:</div><span class="content">{{changeStatusToTxt(orderInfo.status)}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Product_Total_Price"/>:</div><span class="content">{{orderInfo.subtotal}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Freight"/>:</div><span class="content">{{orderInfo.shippingCharges || "$0.00"}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Insurance_Premium"/>:</div><span class="content">{{orderInfo.Insurance || "$0.00"}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Total_Order_Price"/>:</div><span class="content">{{orderInfo.total}}</span>
            </li>
            <!-- <li class="detail-item">
              <div class="name">订单备注内容 :</div><span class="content">xxxxx</span>
            </li> -->
        </ul>

        <ul class="order-info-group" v-if="orderInfo.shipAddress">
            <li class="sub-title"><s:text name="purchase_line.addrsstype.0"/>:</li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.First_Name"/>:</div><span class="content">{{orderInfo.shipAddress.name +" "+ orderInfo.shipAddress.surname}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Address"/>:</div><span class="content">{{orderInfo.shipAddress.country.name +' '+ orderInfo.shipAddress.region.name +' '+ orderInfo.shipAddress.city +' '+ orderInfo.shipAddress.address}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Postal_Code"/>:</div><span class="content">{{orderInfo.shipAddress.postalCode}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Phone"/>:</div><span class="content">{{orderInfo.shipAddress.phone}}</span>
            </li>
        </ul>

        <ul class="order-info-group" v-if="orderInfo.billAddress">
            <li class="sub-title"><s:text name="purchase_line.addrsstype.1"/>:</li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.First_Name"/>:</div><span class="content">{{orderInfo.billAddress.name +" "+ orderInfo.billAddress.surname}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Address"/>:</div><span class="content">{{orderInfo.billAddress.country.name +' '+ orderInfo.billAddress.region.name +' '+ orderInfo.billAddress.city +' '+ orderInfo.billAddress.address}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Postal_Code"/> :</div><span class="content">{{orderInfo.billAddress.postalCode}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Phone"/> :</div><span class="content">{{orderInfo.billAddress.phone}}</span>
            </li>
        </ul>

        <ul class="order-info-group">
            <li class="sub-title"><s:text name="Global.Delivery_Method"/>:</li>
            <li class="detail-item" v-if="orderInfo.expressNum">
              <div class="name"><s:text name="Waybill_Number"/>:</div><span class="content">{{orderInfo.expressNum}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Delivery_Method"/>:</div><span class="content">{{orderInfo.deliveryType}}</span>
            </li>
            <li class="detail-item">
              <div class="name"><s:text name="Global.Payment_Method"/>:</div><span class="content">{{orderInfo.payType && orderInfo.payType.name}}</span>
            </li>
            <li class="detail-item" v-if="orderInfo.pagRemark">
              <div class="name"><s:text name="Package_Remarks"/>:</div><span class="content">{{orderInfo.pagRemark}}</span>
            </li>
        </ul>
        <div class="flex normal-style">
          <div class="name"><s:text name="Order_Note_Content"/>:</div><span class="content" style="color: #777;">{{orderInfo.remark?orderInfo.pagRemark:""}}</span>
        </div>
    </div>

    <div class="mt30">
        <table  class="table-goods">
          <colgroup>
            <col width="5%">
            <col width="45%">
            <col width="20%">
            <col width="10%">
            <col width="8%">
            <col width="12%">
          </colgroup>

          <tbody>
            <tr class="table-goods-th">
                <td><s:text name="Serial_Number"/></td>
                <td><s:text name="Global.Product"/></td>
                <td><s:text name="prodDetail"/></td>
                <td><s:text name="Global.Price"/></td>
                <td><s:text name="Global.Quantity"/></td>
                <td><s:text name="cart.subtotal"/></td>
            </tr>
            <!-- <tr class="table-goods-tr">
                <td>1</td>
                <td>
                    <img class="goods-img" src="" alt="">女士时尚金属扣莱卡拼接女鞋 侧拉链 欧美风 休闲风跟长筒靴 秋冬星品
                </td>
                <td>女性（us/es）9.5/41</td>
                <td>{{orderInfo.currencySymbol}} 10.00</td>
                <td>500</td>
                <td>{{orderInfo.currencySymbol}} 5000.00</td>
            </tr> -->
            <template v-if="orderInfo.lines && orderInfo.lines.length>0">
              <tr  class="table-goods-tr" v-for="(goods,index) in orderInfo.lines">
                <td>{{index+1}}</td>
                <td>
                  <!-- <img class="goods-img" :src="goods.spec.images" alt=""> -->
                  {{goods.spec.productName}}
                </td>
                <td>{{goods.spec.keyName}}</td>
                <td>{{goods.spec.price}}</td>
                <td>{{goods.qty}}</td>
                <td>{{goods.subtotal}}</td>
              </tr>
            </template>
          </tbody>
        </table>

        <div class="total-info"><s:text name="user.orderTotal"/>：
            <span class="total-num">{{orderInfo.itemsCount}}</span>
            <span class="total-count">{{orderInfo.total}}</span>
        </div>

        <table class="table-operate">
            <colgroup>
              <col width="20%">
              <col width="15%">
              <col width="15%">
              <col width="50%">
            </colgroup>

            <thead>
              <tr class="table-operate-th">
                <th><s:text name="user.time"/></th>
                <th><s:text name="user.status"/></th>
                <th><s:text name="Operator"/></th>
                <th><s:text name="products.description"/></th>
              </tr>
            </thead>

            <tbody>
              <template v-if="orderInfo.historys && orderInfo.historys.length">
                <tr class="table-operate-tr" v-for="item in orderInfo.historys">
                  <td>{{changeTime(item.date)}}</td>
                  <td>{{changeStatusToTxt(item.status)}}</td>
                  <td>{{item.operator}}</td>
                  <td>{{item.descrip}}</td>
                </tr>
              </template>
            </tbody>
        </table>
    </div>
</div>
	<!--endprint-->
<script>
  Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
      "M+": this.getMonth() + 1, //月份
      "d+": this.getDate(), //日
      "H+": this.getHours(), //小时
      "m+": this.getMinutes(), //分
      "s+": this.getSeconds(), //秒
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度
      "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" +
        o[k]).substr(("" + o[k]).length)));
    return fmt;
  }

  function getQueryString(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var reg_rewrite = new RegExp("(^|/)" + name + "/([^/]*)(/|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      var q = window.location.pathname.substr(1).match(reg_rewrite);
      if(r != null){
          return unescape(r[2]);
      }else if(q != null){
          return unescape(q[2]);
      }else{
          return null;
      }
  }

  new Vue({
    el: "#app",
    data:{
      orderInfo:{}
    },
    methods:{
      // 将状态码转换为对应的文字信息
      changeStatusToTxt(status){
        switch(status){
          case 0:
          return "<s:text name='orders.status.0'/>";
          case 1:
          return "<s:text name='orders.status.1'/>";
          case 2:
          return "<s:text name='orders.status.2'/>";
          case 3:
          return "<s:text name='orders.status.3'/>";
          case 4:
          return "<s:text name='orders.status.4'/>";
          case 5:
          return "<s:text name='orders.status.5'/>";
          case 6:
          return "<s:text name='orders.status.6'/>";
          case 7:
          return "<s:text name='orders.status.7'/>";
        }
      },
      // 时间戳变时间
      changeTime(timeNum){
        return new Date().Format("yyyy/MM/dd HH:mm");
      }
    },
    mounted(){
      axios.post('/home/odr_OdrOrder_print',Qs.stringify({
        orderId: getQueryString("orderId")
      }, {allowDots: true}))
      .then((res)=>{
        if(res.data.ret == 1 ){
          this.orderInfo = res.data.result;
          this.$nextTick(()=>{
          		var bdhtml=window.document.body.innerHTML;//获取当前页的html代码
				var sprnstr="<!--startprint-->";//设置打印开始区域
				var eprnstr="<!--endprint-->";//设置打印结束区域
				var prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+17); //从开始代码向后取html
				prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
				window.document.body.innerHTML=prnhtml;
				window.print();
				window.document.body.innerHTML=bdhtml;
          })
        }else{
          this.$message({
            message: '获取失败,'+ res.data.msg,
            type: 'warning'
          });
        }
      })
      .catch((res)=>{
          console.log("err")
      });
    }
  })
</script>
</body>
</html>
