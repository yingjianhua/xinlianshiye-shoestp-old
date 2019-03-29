<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <meta charset='utf-8' />
  <meta name='viewport'
    content='width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no'>
  <meta http-quiv='X-UA-Compatible' content='IE=edge,chrome=1'>
  <meta name='keywords' content=''>
  <meta name=' escription' content=''>
  <script src='./js/vue.js'></script>
  <link rel='stylesheet' href='css/index.css'>
  <link rel='stylesheet' href='css/element.css'>
  <title></title>
  <jsp:include page="/home/v3/header.jsp"/>
</head>
<body>
<jsp:include page="/home/v3/nav.jsp"></jsp:include>
  <div id='help'>
    <index-top></index-top>
    <div class="main">
      <h1>帮助文章</h1>
    <div class="msg">
      <div class="nav">
        <h3><a href="xmgIntroduce.jsp">鞋贸港介绍</a></h3>
        <h3><a href="getInquery.jsp">如何快速获得询盘</a></h3>
        <h3><a href="changePassword.jsp">账号的密码如何修改</a></h3>
        <h3><a href="findPassword.jsp">如何找回密码</a></h3>
        <h3><a href="productAudit.jsp">产品审核</a></h3>
        <h3 class="acitved"><a href="changePro.jsp">产品发布后能不能修改</a></h3>
        <h3><a href="omtIntroduce.jsp">瓯贸通介绍</a></h3>
      </div>
      <div class="content">
        <h4>一、产品正在审核中：审核中的状态无法修改或删除产品信息；</h4>
        <h4>二、产品已审核完毕：审核完毕后您可以在管理产品下编辑修改或删除，具体操作如下：</h4><br>
        <h5>1.编辑产品</h5>
        <div class="intend1"></div>1) 登录商家后台——产品管理——产品列表；<br>
        <div class="intend1"></div>2) 找到待修改的产品，点击右侧的“编辑”按钮；<br>
        <div class="intend1"></div>3) 在“修改产品”页面，修改相关的产品信息，编辑完成后，点击“修改”即可。<br><br>
        <h5>2. 删除产品</h5>
        <div class="intend1"></div>登陆商家——产品管理——产品列表，勾选要删除的产品点击“删除”。<br>
        <div class="intend1"></div>O2O的产品不能修改，下架需要平台审核<br><br>
        <h5>做好产品来源管理:</h5>
        <div class="intend1"></div>对于生产型企业<br>
        <div class="intend1"></div>了解自己生产的产品外观，技术，或者表是否已被注册知识产权；对于本公司研发的产品积极去注册知识产权。<br>
        <div class="intend1"></div>做好在线营销管理:<br>
        <div class="intend2"></div>1. 设置公司英文名或申请一级域名时，不要出现他人注册商标。<br>
        <div class="intend2"></div>2. 发布产品时，不要使用或是对他人原创的图片、文字、视频进行二次剪辑。<br>
        <div class="intend2"></div>3. 产品被审核退回时，积极处理。<br>
        <div class="intend1"></div>未经授权的品牌信息建议不要出现在产品信息中，以避免侵权导致产品审核退回/被第三方投诉；<br><br>
        <h4>重点关注内容：</h4>
        <div class="intend1"></div>1、配件产品请准确表述 “适用于” 关系，不仅仅只是使用 for，而是标题、关键词、属性、图片等，均需要准确表述 “适用于” 关系；<br>
        <div class="intend1"></div>2、品牌合作/雇佣关系，需准确表达，不要直接发布品牌产品；<div class="intend1"></div>3、图片和产品分组也属于产品信息，同样需要注意侵权问题。
      </div>
    </div>
    </div>
    <index-bottom></index-bottom>
  </div>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
  <script>
    new Vue({
      el: '#help',
      data: {},
      methods: {},
      mounted() {}
    })
  </script>
</body>

</html>