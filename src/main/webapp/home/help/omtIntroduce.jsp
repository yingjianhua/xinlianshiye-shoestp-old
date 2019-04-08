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
    <div class="msg">
      <div class="nav">
        <h1>帮助文章</h1>
        <h3><a href="xmgIntroduce.jsp">鞋贸港介绍</a></h3>
        <h3 class="sanji"><a href="getInquery.jsp">如何快速获得询盘</a></h3>
        <h3 class="sanji"><a href="changePassword.jsp">账号的密码如何修改</a></h3>
        <h3 class="sanji"><a href="findPassword.jsp">如何找回密码</a></h3>
        <h3 class="sanji"><a href="productAudit.jsp">产品审核</a></h3>
        <h3 class="sanji"><a href="changePro.jsp">产品发布后能不能修改</a></h3>
        <h3 class="acitved"><a href="omtIntroduce.jsp">瓯贸通介绍</a></h3>
      </div>
      <div class="content">
          <h3>瓯贸通介绍</h3>
          <h4>您的进出口服务专家</h4>
          瓯贸通致力于企业健康良性发展，对每一个通过瓯贸通出口的企业包括法务，税务，商务在内的引导型服务。打造一站式的综合服务平台。<br><br>
          <h4>快速报关：</h4>利用信息化手段助您为货物进行专业把关申报数据，同时提供专属清关高峰大数据分析，报关更快捷。<br><br>
          <h4>综合金融：</h4>促进银行和企业融资业务对接，打通融资渠道，孵化中小鞋业。解决企业交易中的资金周转及综合金融服务。<br><br>
          <h4>物流监管：</h4>每个物流环节可以实时监控，可以更高效的开展工作，更快速的解决问题，整个物流的过程变得更加高效清晰，无需担心清关拥堵。<br><br>
          <h4>高效退税</h4>安全高效，快速结算，提升退税操作效率。让交易更加顺畅，没有后顾之忧。<h5>更多详情：请点击<a href="http://www.wzsomt.com/">http://www.wzsomt.com/</a>。</h5>
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