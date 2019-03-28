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
        <h3 class="acitved"><a href="getInquery.jsp">如何快速获得询盘</a></h3>
        <h3><a href="changePassword.jsp">账号的密码如何修改</a></h3>
        <h3><a href="findPassword.jsp">如何找回密码</a></h3>
        <h3><a href="productAudit.jsp">产品审核</a></h3>
        <h3><a href="changePro.jsp">产品发布后能不能修改</a></h3>
        <h3><a href="omtIntroduce.jsp">瓯贸通介绍</a></h3>
      </div>
      <div class="content">
        <h4>1、曝光</h4>需要有询盘，首先得先让客户看到您的产品，所以提升曝光量是达到增加询盘的基础；<br>如何提升公司曝光：上传更多优质产品，优化产品等措施。<br><br>
        <h4>2、点击：</h4>客户在搜索过程中看到了您的产品，如果不点进去查看，也是很少会直接发起询盘的，所以有了曝光以后，我们还要想办法去提升客户的点击率；<br>如何提升点击<br><br>
        <h5>成为认证供应商</h5>加入鞋贸港认证诚信供应商体系之后，让买家在未交易前就了解到企业本身的资信状况，增加交易机会。<h5>产品优化</h5>
        首先来分析搜索list页面买家都关注哪些点，买家一般会比较关注产品图片、名称、供应商认证等级等几个方面，可从这几点方面进行重点优化，提升买家体验感以便更好吸引买家查看产品详情<br><br>
        <h4>3、反馈（询盘）</h4>从目前买家的一些情况数据来看，买家主要比较关注商务条款、质量控制，其次为供应商的基本信息，所以您可以在您的产品详细描述中增加这些内容，比如增加质检流程、认证信息、数据展示等等<br><br>
        <h4>4、RFQ</h4>平台RFQ实时更新，守株待兔不如主动出击。
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