<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 商家-头部 -->
<div class="enterprise-header">
    <div class="wide flexible">
        <c:if test='${not empty supView.logo}'>
            <div class="enterprise-logo pic_box">
                <img src="${envConfig.imageBaseUrl}${supView.logo}?x-oss-process=image/resize,m_pad,h_130,w_165"
                     alt="">
                <span></span>
            </div>
        </c:if>


        <div class="enterprise-information">
            <c:if test='${not empty supView.logo}'>
                <i class="bar"></i>
            </c:if>
            <span class="enterprise-Name">${supView.showName}</span>
            <!-- <i class="icon icon-certification-o"></i>
            <i class="icon icon-defence-safe-o"></i>
            <i class="icon icon-years icon-years-07"></i> -->
            <c:if test="${supView.isAuth == 1}">
                <a id="c1" data="${supView.pkey }">
                    <img class="icon" src="/home/static/images/certification-o.png" alt="">
                </a>

                <a id="c2" data="${supView.pkey }">
                    <img class="icon" src="/home/static/images/defence-safe-o.png" alt="">
                </a>
            </c:if>
            <!-- <img class="icon" src="/static/themes/t050/images/four_pic/years-07.png" alt=""> -->
            <c:if test="${not empty supView.authAge && supView.authAge > 0}">
                <a class="icon-years-limit" id="c3" data="${supView.pkey }">
                    <img class="icon" src="/home/static/images/doller_pic_o.png" alt="">
                    <span class="years-num">${supView.authAge}</span>
                    <span class="years-unit">YRS</span>
                </a>
            </c:if>
            <script type="text/javascript">

              $("#c1").click(function () {
                sessionStorage.setItem("type", "1");
                window.location.href = "/home/usr_UsrSupplier_gtSupInfo?pkey=" + $(this).attr(
                    "data");
              })

              $("#c2").click(function () {
                sessionStorage.setItem("type", "1");
                window.location.href = "/home/usr_UsrSupplier_gtSupInfo?pkey=" + $(this).attr(
                    "data");
              })
              $("#c3").click(function () {
                sessionStorage.setItem("type", "1");
                window.location.href = "/home/usr_UsrSupplier_gtSupInfo?pkey=" + $(this).attr(
                    "data");
              })


            </script>
        </div>

        <!-- 按钮-联系供应商 -->
        <!-- 按钮-开始下单 -->
        <a class="btn btn-begin-order" onclick="ToContactSupplier()"></a>
           <script>
                 // 跳转供应商表单
                 function  ToContactSupplier (){
                 let url = "/home/usr_UsrSupplier_goContactSupplier?supplierPkey=${supView.pkey}&backUrl=" + window.location.href;
                 util_function_obj.supplierCantEnter(this, url);
             }
            </script>
    </div>
</div>
<!-- 商家-顶部导航 -->
<div class="enterprise-nav">
    <div class="wide">
        <ul class="clean enterprise-nav-list">
            <li class="enterprise-nav-item">
                <a class="" href="/home/usr_UsrSupplier_gtSupIndex?pkey=${supView.pkey}"><s:text
                        name="companyHome"/></a>
            </li>
            <li class="enterprise-nav-item">
                <a href="/home/usr_UsrSupplier_gtSupPro?pkey=${supView.pkey}"><s:text
                        name="shop-header.Product_Center"/></a>
            </li>
            <li class="enterprise-nav-item">
                <a href="/home/usr_UsrSupplier_gtSupInfo?pkey=${supView.pkey}"><s:text
                        name="companyProfile"/></a>
            </li>
            <%--<li class="enterprise-nav-item">--%>
                <%--<a href="/home/usr_UsrSupplier_gtSupContact?pkey=${supView.pkey}"><s:text--%>
                        <%--name="Global.Contact_Us"/></a>--%>
            <%--</li>--%>

            <%--<c:if test="${supView.prmAuthrity == true}">--%>
            <%--<li class="enterprise-nav-item GroupPurchase">--%>
            <%--<a href="/home/usr_UsrSupplier_gtSupGroup?pkey=${supView.pkey}"><s:text name="Global.Joint_Procurement"/></a>--%>
            <%--</li>--%>
            <%--</c:if>--%>

        </ul>
    </div>
</div>
