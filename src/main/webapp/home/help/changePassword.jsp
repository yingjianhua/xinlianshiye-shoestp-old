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
          <h3 class="acitved"><a href="changePassword.jsp">账号的密码如何修改</a></h3>
          <h3><a href="findPassword.jsp">如何找回密码</a></h3>
          <h3><a href="productAudit.jsp">产品审核</a></h3>
          <h3><a href="changePro.jsp">产品发布后能不能修改</a></h3>
          <h3><a href="omtIntroduce.jsp">瓯贸通介绍</a></h3>
        </div>
        <div class="content">
          登入商家后台进入账户设置，在下方的密码设置中，输入原密码和新密码即可。
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