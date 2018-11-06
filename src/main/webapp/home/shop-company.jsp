<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0039)https://www.shoestp.com/m/sien/product/ -->
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="renderer" content="webkit">
  <meta name="keywords" content="">
  <meta name="description" content="">
  <title> <c:if test="${supView.webSizeTitle !=''}">
   ${supView.webSizeTitle}
  </c:if>
    <c:if test="${supView.webSizeTitle ==''}">
	  An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies
  </c:if></title>
  <link href="./static/css/global.css" rel="stylesheet" type="text/css">
  <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
  <link href="./static/css/user.css" rel="stylesheet" type="text/css">
  <link href="./static/css/style.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="./static/css/animate.min.css">
  <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
  <link rel="stylesheet" href="./static/css/color.css" type="text/css">
  <link href="./static/css/index.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
  <!-- 新引入的css -->
  <link href="./static/css/style_new.css" rel="stylesheet" type="text/css">
  <!-- 轮播插件 -->
  <script type="text/javascript" src="./static/js/jquery.SuperSlide.2.1.1.js"></script>
</head>

<body class="lang_en w_1200">




  <%@ include file="/home/template/web-top.jsp" %>
  <%@ include file="/home/template/new-header.jsp" %>
  <%@ include file="/home/template/shop-header.jsp" %>



  <div>

    <div class="clean">
    </div>
    <div class="wide">
      <!-- 商家-信息1 -->
      <div class="enterprise-info-wrap">
        <h3 class="enterprise-info-title">
          <s:text name="CompanyInformation"/> </h3>
        <div class="enterprise-info-content divide-harf">
          <div class="divide-harf-item">
            <div class="info-item">
              <h5 class="title">
                <s:text name="purchase.company"/> </h5>
              <div class="text"><c:if test="${supView.name != 'null'}">${supView.showName}</c:if></div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="supplier.businesstyp"/> </h5>
              <div class="text">
               <c:if test="${supView.businessTyp != 'null'}">${supView.businessTyp}</c:if> </div>
            </div>
			<c:set var="symbolNoLogin" value="********"/>
            <div class="info-item">
              <h5 class="title">
                <s:text name="Global.Address"/> </h5>
              <div class="text">
              	<c:if test="${env.login == null}">
              		${symbolNoLogin}
              	</c:if>
              	<c:if test="${env.login != null}">
              		<c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
              	</c:if>
               </div>
            </div>
            <div class="info-item">
              <h5 class="title">
                <s:text name="supt7"/> </h5>
              <div class="text">
              	<c:if test="${env.login == null}">
              		${symbolNoLogin}
              	</c:if>
              	<c:if test="${env.login != null}">
              		<c:if test="${supView.mainProd != 'null'}">${supView.mainProd}</c:if>
              	</c:if>

              </div>
            </div>
            <div class="info-item">
              <h5 class="title">
                <s:text name="supt8"/> </h5>
              <div class="text">
              		<c:if test="${env.login == null}">
	              		${symbolNoLogin}
	              	</c:if>
	              	<c:if test="${env.login != null}">
	              		<c:if test="${supView.prodPattern != 'null'}">${supView.prodPattern}</c:if>
	              	</c:if>

              </div>
            </div>
            <div class="info-item">
              <h5 class="title">
                <s:text name="developerNumber"/> </h5>
              <div class="text">
              		<c:if test="${env.login == null}">
	              		${symbolNoLogin}
	              	</c:if>
	              	<c:if test="${env.login != null}">
	              		<c:if test="${supView.developer != 'null'}">${supView.developer}</c:if>
	              	</c:if>

              </div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="supplier.totalemployees"/> </h5>
              <div class="text">
              		<c:if test="${env.login == null}">
	              		${symbolNoLogin}
	              	</c:if>
	              	<c:if test="${env.login != null}">
	              		<c:if test="${supView.totalEmployees != 'null'}">${supView.totalEmployees}</c:if>
	              	</c:if>

              </div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="supplier.annualsales"/> </h5>
              <div class="text">
              		<c:if test="${env.login == null}">
	              		${symbolNoLogin}
	              	</c:if>
	              	<c:if test="${env.login != null}">
	              		<c:if test="${supView.annualSales != 'null'}">${supView.annualSales}</c:if>
	              	</c:if>

              </div>
            </div>
          </div>

          <div class="divide-harf-item">
            <div class="info-item">
              <h5 class="title">
                <s:text name="supplier.companyestablishtime"/> </h5>
              <div class="text">
               <c:if test="${supView.companyEstablishTime != 'null'}">${supView.companyEstablishTime}</c:if> </div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="supplier.top3markets"/> </h5>
              <div class="text"><c:if test="${supView.top3Markets != 'null'}">${supView.top3Markets}</c:if>
              </div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="supplier.materials"/> </h5>
              <div class="text"><c:if test="${supView.materials != 'null'}">${supView.materials}</c:if>
              </div>
            </div>

          </div>
        </div>
        <!-- 商家-信息1 - end -->

        <!-- 商家-信息2 -->
        <div class="enterprise-info-wrap  mb60">
          <h3 class="enterprise-info-title">
            <s:text name="authenticationInformation"/> </h3>
          <div class="enterprise-info-content">
            <div class="info-item">
              <h5 class="title">
                <s:text name="On-siteInspection"/> </h5>
              <div class="text">
                <s:text name="On-siteInspection1"/>
              </div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="businessLicense"/> </h5>
              <div class="text">
              	<c:if test="${env.login == null}">
              		${symbolNoLogin}
              	</c:if>
              	<c:if test="${env.login != null}">
              		<s:text name="RegistrationNo"/>. <c:if test="${supView.creditCode != 'null'}">${supView.creditCode}</c:if>
	                <br> <s:text name="purchase.company"/>: <c:if test="${supView.name != 'null'}">${supView.showName}</c:if>
	                <br> <s:text name="issue"/>: <c:if test="${supView.authTime != 'null'}">${supView.authTime}</c:if>
	                <br> <s:text name="supplier.registeredcapital"/>:<c:if test="${supView.registeredCapital != 'null'}">${supView.registeredCapital}</c:if>
	                <br> <s:text name="Global.Country"/>
	                <br> ${country }
	                <br> <s:text name="registeredAddress"/>
	                <br> <c:if test="${supView.companyAddr != 'null'}">${supView.companyAddr}</c:if>
	                <br> <s:text name="YearEstablished"/>
	                <br> <c:if test="${supView.companyEstablishTime != 'null'}">${supView.companyEstablishTime}</c:if>
	                <br> <s:text name="LegalRepresentative"/>
	                <br> <c:if test="${supView.entity != 'null'}">${supView.entity}</c:if>
	                <br> <s:text name="LegalForm"/>
	                <br> <c:if test="${supView.companyType != 'null'}">${supView.companyType}</c:if><!-- COMPANY_ADDR -->
	                <!-- <br> Issuing Authority
	                <br> tai'an City daiyue District Administration for Industry and Commerce -->
              	</c:if>
              </div>
            </div>

            <div class="info-item">
              <h5 class="title">
                <s:text name="OperationalAddress"/> </h5>
              <div class="text">
              	<c:if test="${env.login == null}">
              		${symbolNoLogin}
              	</c:if>
              	<c:if test="${env.login != null}">
              		<c:if test="${supView.location != 'null'}">${supView.location }</c:if>
              	</c:if>

              </div>
            </div>
            <a name="data"></a>
            <!-- 供应商信息 -->
            <div class="supplier-info-list">


            <c:if test="${supView.isAuth == 1}">
              <div class="supplier-info-item">
                <h5 class="title">
                  <i class="icon icon-defence-safe"></i>
                  <span><s:text name="On-siteInspection"/></span>
                </h5>
                <div class="text">
                  <s:text name="On-siteInspection2"/>
                </div>
              </div>
              <div class="supplier-info-item">
                <h5 class="title">
                  <i class="icon icon-certification"></i>
                  <span><s:text name="Certification"/></span>
                </h5>
                <div class="text">
                  <s:text name="On-siteInspection3"/>
                </div>
              </div>
              </c:if>

              <div class="supplier-info-item">
                <h5 class="title">
                  <i class="icon icon-years icon-years-07"></i>
                  <span><s:text name="AgeLimit"/></span>
                </h5>
                <div class="text">
                  <s:text name="On-siteInspection4"/>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 商家-信息2 - end -->
      </div>
    </div>



    <%@ include file="/home/template/new-foot.jsp" %>
		<script type="text/javascript" src="./static/js/module.js"></script>
		<script type="text/javascript" src="./static/js/review.js"></script>
		<script type="text/javascript" src="./static/js/lightbox.min.js"></script>
		<!-- <script type="text/javascript" src="./static/js/addthis_widget.js"></script> -->

<script type="text/javascript">
$(function (){
	var type = sessionStorage.getItem("type");
	if(type == "1"){
		sessionStorage.setItem("type","");
		window.scrollTo(0, 840)
	}
})

</script>  ${supView.traceCode}
</body>

</html>
