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
        <h3 class="acitved"><a href="xmgIntroduce.jsp">鞋贸港介绍</a></h3>
        <h3 class="sanji"><a href="getInquery.jsp">如何快速获得询盘</a></h3>
        <h3 class="sanji"><a href="changePassword.jsp">账号的密码如何修改</a></h3>
        <h3 class="sanji"><a href="findPassword.jsp">如何找回密码</a></h3>
        <h3 class="sanji"><a href="productAudit.jsp">产品审核</a></h3>
        <h3 class="sanji"><a href="changePro.jsp">产品发布后能不能修改</a></h3>
        <h3><a href="omtIntroduce.jsp">瓯贸通介绍</a></h3>
      </div>
      <div class="content">
        <h3>鞋贸港介绍</h3>
        新联-鞋贸港贯彻一带一路，实施“走出去”“引进来”的发展思路，整合温州乃至全中国优质鞋企打造全球联合采购机制。与全球各个国际合伙人建立国际采购站，将线上与线下结合。<br><br>
        <h4>O2O:</h4>
        鞋贸港O2O实现了线上线下的完美结合，去除所有中间环节，迎合了国际买家希望和工厂直接合作、采购优价实的产品，工厂希望与买家直接对接、建立自己的国际销售渠道的要求。<br>利用大数据精准狙击买家需求，从而提升订单成交率。<br>鞋贸港O2O目前分为2各区块：设计区和低价区。设计区集中鞋企研发新品鞋款，此类鞋款的针对欧美地区以设计为主的采购商；低价区是以非洲、印度、拉美等地区作为销售市场，用超低价格吸引大采购商批购买。鞋贸港O2O可以满足不同类型的买家需求，<br>O2O目标就是真正将线上的流量转化成商家的订单，做到线上付款线下看款的商业模式。<br><br>
        <h4>SVS: </h4>根据外贸工厂的研发能力、企业规模、产量等为衡量条件，完善诚信供应商体系。从而达到精准询盘的目的。<br>更多详情：请点击
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