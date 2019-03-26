<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords"
          content="fashion wholesale soes,cheap wholesale shoes,wholesale shoes,shoes customization,shoes trade,shoes,Manufacturers,shoestp">
    <meta name="description"
          content="foreign trade experts online service! ShoeSTP provides you with customization and wholesale for all kinds of high-quality shoes from 300 professional foreign trade shoes companies!">
    <title>
        An Online B2B market
    </title>

    <%-- <link href="./static/css/css" rel="stylesheet" type="text/css"> --%>
    <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
    <link href="./static/css/user.css" rel="stylesheet" type="text/css">
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="./static/css/color.css" type="text/css">
    <link rel="stylesheet" href="./static/css/animate.min.css" type="text/css">
    <link rel="stylesheet" href="./static/css/swiper.min.css" type="text/css">
    <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js">
    </script>
    <script src="./static/js/swiper.min.js" type="text/javascript">
    </script>
    <script src="./static/js/lazyload.min.js" type="text/javascript">
    </script>

    <style type="text/css">
        /* .supbanner *,
        .sup_location *,
        .sup_main * {
            font-family: 'Open Sans', Verdana;
        } */

        .wide {
            width: 1200px;
            min-width: 1200px;
        }

        .slideBox_3 {
            overflow: hidden;
            position: relative;
        }

        .slideBox_3 .hd {
            height: 15px;
            overflow: hidden;
            position: absolute;
            bottom: 15px;
            z-index: 1;
        }

        .slideBox_3 .hd ul {
            overflow: hidden;
            zoom: 1;
            float: left;
        }

        .slideBox_3 .hd ul li {
            float: left;
            margin-left: 5px;
            width: 8px;
            height: 8px;
            border: 1px solid #fff;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            background: none;
            cursor: pointer;
        }

        .slideBox_3 .hd ul li:first-child {
            margin-left: 0;
        }

        .slideBox_3 .hd ul li.on {
            background: #fff;
            color: #fff;
        }

        .slideBox_3 .bd {
            position: relative;
            /* height: 100%; */
            z-index: 0;
        }

        .slideBox_3 .bd ul li a {
            display: block;
            background-position: center top;
            background-repeat: no-repeat;
        }
    </style>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<jsp:include page="template/web-top.jsp"></jsp:include>
<!-- .new_header -->
<div class="supbanner">
    <div class="item">
        <div style="overflow:hidden;">
            <div id="slideBox_3" class="slideBox_3">
                <div class="hd" style="left: 955px;"></div>
                <div class="bd" style="width: 1920px;">
                    <ul style="width: 1920px;">
                        <li style="width: 1920px;">
                            <a href="javascript:;">
                                <img src="./static/images/448cb9109d.jpg" alt="" style="width: 1920px;">
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="sup_main">
    <index-top></index-top>
    <div class="wide">
        <div class="new_suppx">
            <div id="suside_rm">
                <div class="bar">
                    <img src="/home/static/images/ico/bars.png" alt="" style="width: 28px;height: 28px;">
                </div>
                <div class="title"><s:text name="recommend"/>
                    <div class="close"></div>
                </div>
                <div class="list">
                    <%--    原先动态的  暂做静态处理 --%>
                    <%--        <c:forEach var="d" items="${supplierDto.recommendList}">
                               <div class="item clean">
                                   <div class="pi0 fl">
                                       <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" target="_blank">
                                           <img src="${envConfig.imageBaseUrl}${d.logo}">
                                       </a>
                                   </div>
                                   <div class="name fr">
                                       <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" target="_blank">${d.name}</a>
                                   </div>
                               </div>
                           </c:forEach>--%>
                    <div class="item clean">
                        <div class="pi0 fl">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=38" target="_blank">
                                <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/ee39200944.jpg">
                            </a>
                        </div>
                        <div class="name fr">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=38" target="_blank">中武鞋业</a>
                        </div>
                    </div>
                    <div class="item clean">
                        <div class="pi0 fl">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=33" target="_blank">
                                <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/61e11d65cd.jpg">
                            </a>
                        </div>
                        <div class="name fr">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=33" target="_blank">奥康国际</a>
                        </div>
                    </div>
                    <div class="item clean">
                        <div class="pi0 fl">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=279" target="_blank">
                                <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/7ba79feb71.png">
                            </a>
                        </div>
                        <div class="name fr">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=279" target="_blank">浙江剑鲨鞋业有限公司</a>
                        </div>
                    </div>
                    <div class="item clean">
                        <div class="pi0 fl">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=301" target="_blank">
                                <img src="${envConfig.imageBaseUrl}/u_file/1808/photo/a37c34f89d.jpg">
                            </a>
                        </div>
                        <div class="name fr">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=301" target="_blank">温州展豪鞋业有限公司</a>
                        </div>
                    </div>
                    <div class="item clean">
                        <div class="pi0 fl">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=28" target="_blank">
                                <img src="${envConfig.imageBaseUrl}/u_file/1710/photo/f5fcec0bb1.jpg">
                            </a>
                        </div>
                        <div class="name fr">
                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=28" target="_blank">温州卓诗尼鞋业有限公司</a>
                        </div>
                    </div>


                </div>
            </div>
            <!-- .suside_rm -->
            <div class="nftitle clean">
                <span class="t0"><s:text name="recommend"/></span>
            </div>
            <!-- .htitle -->
            <div class="scrollbox" style="height:274px;" id="iibox">
                <div class="lbtn btn"></div>
                <div class="rbtn btn"></div>
                <div style="width:100%; overflow:hidden; position:relative;">
                    <div class="suplist_pro clean sbox2" style="width:10000%;">
                        <div class="ii fl">
                            <%--                             <c:forEach --%>
                            <%--                                     items="${supplierDto.recommendList}" --%>
                            <%--                                     var="d" --%>
                            <%--                             > --%>
                            <!--                                 <div class="list_item fl" style="margin-bottom:0"> -->
                            <!--                                     <div class="ht clean"> -->
                            <!--                                         <div class="fl pic"> -->
                            <%--                                             <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" --%>
                            <!--                                                target="_blank"> -->
                            <%--                                                 <img src="${envConfig.imageBaseUrl}${d.logo}"> --%>
                            <!--                                             </a> -->
                            <!--                                         </div> -->
                            <!--                                         <div class="info fr"> -->
                            <!--                                             <div class="name"> -->
                            <%--                                                 <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" --%>
                            <%--                                                    target="_blank">${d.name}</a> --%>
                            <!--                                             </div> -->
                            <!--                                             <div class="ti2 clean"> -->
                            <!--                                                 <div class="ti2_left fl"> -->
                            <!--                                                     <div class="ti2p"> -->
                            <%--                                                         <s:text name="comment.product"/> --%>
                            <!--                                                     </div> -->
                            <!--                                                     <div class="ti2n"> -->
                            <%--                                                         <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" --%>
                            <%--                                                            target="_blank">${d.proDuctCount}</a> --%>
                            <!--                                                     </div> -->
                            <!--                                                 </div> -->
                            <!--                                                 .ti2_left -->
                            <!--                                                 <div class="ti2_right fr"> -->
                            <!--                                                     <div class="ti2p"> -->
                            <%--                                                         <s:text name="ProductsStyle"/> --%>
                            <!--                                                     </div> -->
                            <!--                                                     <div class="ti2i"> -->
                            <%--                                                         <s:if test="d.prodpattern!=null"> --%>
                            <%--                                                             ${d.prodpattern} --%>
                            <%--                                                         </s:if> --%>
                            <%--                                                         <s:else> --%>
                            <%--                                                             I18N --%>
                            <%--                                                         </s:else> --%>
                            <!--                                                     </div> -->
                            <!--                                                 </div> -->
                            <!--                                                 .ti2_right -->
                            <!--                                             </div> -->
                            <!--                                         </div> -->
                            <!--                                     </div> -->
                            <!--                                     .ht -->
                            <!--                                     <div class="probox clean"> -->
                            <%--                                         <c:forEach --%>
                            <%--                                                 items="${d.proDuctDtos}" --%>
                            <%--                                                 var="dd" --%>
                            <%--                                         > --%>
                            <!--                                             <div class="item fl"> -->
                            <%--                                                     <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${dd.pkey}" --%>
                            <%--                                                 <a href="/${dd.rewrite}" --%>
                            <!--                                                    target="_blank"> -->
                            <%--                                                     <img src="${envConfig.imageBaseUrl}${dd.picture}"> --%>
                            <!--                                                 </a> -->
                            <!--                                             </div> -->
                            <%--                                         </c:forEach> --%>
                            <!--                                     </div> -->
                            <!--                                 </div> -->

                            <%--                             </c:forEach> --%>
                            <div class="list_item fl" id="listitems1" style="margin-bottom:0">
                                <div class="ht clean">
                                    <div class="fl pic">
                                        <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=283"
                                           target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1808/photo/564fc63d74.jpg">
                                        </a>
                                    </div>
                                    <div class="info fr">
                                        <div class="name">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=283"
                                               target="_blank">温州市丰盛鞋业有限公司</a>
                                        </div>
                                        <div class="ti2 clean">
                                            <div class="ti2_left fl">
                                                <div class="ti2p">
                                                    <s:text name="comment.product"/>
                                                </div>
                                                <div class="ti2n">
                                                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=283"
                                                       target="_blank">19</a>
                                                </div>
                                            </div>
                                            <!-- .ti2_left -->
                                            <div class="ti2_right fr">
                                                <div class="ti2p">
                                                    <s:text name="ProductsStyle"/>
                                                </div>
                                                <div class="ti2i">
                                                    OEM
                                                </div>
                                            </div>
                                            <!-- .ti2_right -->
                                        </div>
                                    </div>
                                </div>
                                <!-- .ht -->
                                <div class="probox clean">
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=6761" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1808/products/14/7c1d3eec5c.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=6766" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1808/products/14/366eeccafe.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=6767" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1808/products/14/5fa096ba61.jpg">
                                        </a>
                                    </div>

                                </div>
                            </div>

                            <div class="list_item fl" id="listitems2" style="margin-bottom:0">
                                <div class="ht clean">
                                    <div class="fl pic">
                                        <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=23"
                                           target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/f44b4e85f9.png">
                                        </a>
                                    </div>
                                    <div class="info fr">
                                        <div class="name">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=23"
                                               target="_blank">温州市亿力洲鞋业有限公司</a>
                                        </div>
                                        <div class="ti2 clean">
                                            <div class="ti2_left fl">
                                                <div class="ti2p">
                                                    <s:text name="comment.product"/>
                                                </div>
                                                <div class="ti2n">
                                                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=23"
                                                       target="_blank">10</a>
                                                </div>
                                            </div>
                                            <!-- .ti2_left -->
                                            <div class="ti2_right fr">
                                                <div class="ti2p">
                                                    <s:text name="ProductsStyle"/>
                                                </div>
                                                <div class="ti2i">
                                                    ODM
                                                </div>
                                            </div>
                                            <!-- .ti2_right -->
                                        </div>
                                    </div>
                                </div>
                                <!-- .ht -->
                                <div class="probox clean">
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=340" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1709/products/26/8e21d4de82.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=344" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1709/products/27/32e53fb695.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=343" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1709/products/27/8eff14dd8b.jpg">
                                        </a>
                                    </div>

                                </div>
                            </div>

                            <div class="list_item fl" id="listitems3" style="margin-bottom:0">
                                <div class="ht clean">
                                    <div class="fl pic">
                                        <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=30"
                                           target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/1a4a1ebf69.jpg">
                                        </a>
                                    </div>
                                    <div class="info fr">
                                        <div class="name">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=30"
                                               target="_blank">浙江大博文鞋业有限公司</a>
                                        </div>
                                        <div class="ti2 clean">
                                            <div class="ti2_left fl">
                                                <div class="ti2p">
                                                    <s:text name="comment.product"/>
                                                </div>
                                                <div class="ti2n">
                                                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=30"
                                                       target="_blank">53</a>
                                                </div>
                                            </div>
                                            <!-- .ti2_left -->
                                            <div class="ti2_right fr">
                                                <div class="ti2p">
                                                    <s:text name="ProductsStyle"/>
                                                </div>
                                                <div class="ti2i">
                                                    OBM
                                                </div>
                                            </div>
                                            <!-- .ti2_right -->
                                        </div>
                                    </div>
                                </div>
                                <!-- .ht -->
                                <div class="probox clean">
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=450" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1711/products/28/a6f98ddfcc.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=455" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1711/products/28/b8d652c8a7.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=465" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1711/products/28/6abdfbdb49.jpg">
                                        </a>
                                    </div>

                                </div>
                            </div>


                            <div class="list_item fl" id="listitems4" style="margin-bottom:0">
                                <div class="ht clean">
                                    <div class="fl pic">
                                        <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=16"
                                           target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/d2e044e034.jpg">
                                        </a>
                                    </div>
                                    <div class="info fr">
                                        <div class="name">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=16"
                                               target="_blank">温州市康睿鞋业有限公司</a>
                                        </div>
                                        <div class="ti2 clean">
                                            <div class="ti2_left fl">
                                                <div class="ti2p">
                                                    <s:text name="comment.product"/>
                                                </div>
                                                <div class="ti2n">
                                                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=16"
                                                       target="_blank">68</a>
                                                </div>
                                            </div>
                                            <!-- .ti2_left -->
                                            <div class="ti2_right fr">
                                                <div class="ti2p">
                                                    <s:text name="ProductsStyle"/>
                                                </div>
                                                <div class="ti2i">
                                                    OEM
                                                </div>
                                            </div>
                                            <!-- .ti2_right -->
                                        </div>
                                    </div>
                                </div>
                                <!-- .ht -->
                                <div class="probox clean">
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=530" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1712/products/05/4646e4c07c.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=543" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1712/products/06/a26e348ed3.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=578" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1712/products/22/30258718b7.jpg">
                                        </a>
                                    </div>

                                </div>
                            </div>


                            <div class="list_item fl" id="listitems5" style="margin-bottom:0">
                                <div class="ht clean">
                                    <div class="fl pic">
                                        <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=34"
                                           target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1807/photo/1a4a1ebf69.jpg">
                                        </a>
                                    </div>
                                    <div class="info fr">
                                        <div class="name">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=34"
                                               target="_blank">飛耀童鞋</a>
                                        </div>
                                        <div class="ti2 clean">
                                            <div class="ti2_left fl">
                                                <div class="ti2p">
                                                    <s:text name="comment.product"/>
                                                </div>
                                                <div class="ti2n">
                                                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=34"
                                                       target="_blank">45</a>
                                                </div>
                                            </div>
                                            <!-- .ti2_left -->
                                            <div class="ti2_right fr">
                                                <div class="ti2p">
                                                    <s:text name="ProductsStyle"/>
                                                </div>
                                                <div class="ti2i">
                                                    OBM
                                                </div>
                                            </div>
                                            <!-- .ti2_right -->
                                        </div>
                                    </div>
                                </div>
                                <!-- .ht -->
                                <div class="probox clean">
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=551" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1712/products/11/5af8215863.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=556" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1712/products/11/e8bcd4f964.jpg">
                                        </a>
                                    </div>
                                    <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=667" target="_blank">
                                            <img src="${envConfig.imageBaseUrl}/u_file/1801/products/08/f0d8168eaa.jpg">
                                        </a>
                                    </div>

                                </div>
                            </div>


                        </div>
                    </div>
                    <!-- .sbox -->
                </div>
            </div>
            <!-- .scrollbox -->
        </div>

        <div class="new_suppx">
            <div class="nftitle clean">
                <span class="t0"><s:text name="Manufacturers"/></span>
                <div class="fr suppx_cate">
                    <div class="title fl">
                        <s:text name="Screnning"/> :
                    </div>
                    <div class="cate fl">
                        <div class="t" style="background:none; padding-right:8px;">
                            <s:text name="category"/>
                        </div>
                    </div>
                    <div class="cate fl" id="scren_cate">
                        <div class="t">
                            <s:text name="all_category"/>
                        </div>
                        <div class="ct">
                            <div class="i" cateid="-1">
                                <s:text name="all_category"/>
                            </div>
                            <c:forEach
                                    items="${supplierDto.category}"
                                    var="d">
                                <div class="i" cateid="${d.id}">
                                        ${d.name}
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
            <!-- .htitle -->
            <div class="suplist_pro clean" id="main_suplist_pro">
                <%-- <c:forEach
                        items="${supplierDto.manufacturersList}"
                        var="d"
                >
                    <div class="list_item fl" style="margin-bottom:0">
                        <div class="ht clean">
                            <div class="fl pic">
                                <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}"
                                   target="_blank">
                                    <img src="${envConfig.imageBaseUrl}${d.logo}">
                                </a>
                            </div>
                            <div class="info fr">
                                <div class="name">
                                    <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}"
                                       target="_blank">${d.name}</a>
                                </div>
                                <div class="ti2 clean">
                                    <div class="ti2_left fl">
                                        <div class="ti2p">
                                            <s:text name="comment.product"/>
                                        </div>
                                        <div class="ti2n">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}"
                                               target="_blank">${d.proDuctCount}</a>
                                        </div>
                                    </div>
                                    <!-- .ti2_left -->
                                    <div class="ti2_right fr">
                                        <div class="ti2p">
                                            <s:text name="ProductsStyle"/>
                                        </div>
                                        <div class="ti2i">
                                                ${d.prodpattern}
                                        </div>
                                    </div>
                                    <!-- .ti2_right -->
                                </div>
                            </div>
                        </div>
                        <!-- .ht -->
                        <div class="probox clean">
                            <c:forEach
                                    items="${d.proDuctDtos}"
                                    var="dd"
                            >
                                <div class="item fl">
                                        <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${dd.pkey}"
                                    <a href="${dd.rewrite}"

                                       target="_blank">
                                        <img src="${envConfig.imageBaseUrl}${dd.picture}">
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </c:forEach> --%>
            </div>
            <div class="fkvm" id="newsupmore" data-page="1" data-total="4" data-cateid="0">
                <a href="javascript:void(0);"><s:text name="read_more"/></a>
            </div>
        </div>
        <!-- .new_suppx -->
    </div>
    <index-bottom></index-bottom>
</div>
<!-- #sup_main -->
<%--<jsp:include page="template/new-foot.jsp"></jsp:include>--%>
<div id="hj_top" style="opacity: 0; bottom: 10%;">
    <img src="./static/images/hj_top.png">
</div>


<script type="text/javascript" src="./static/js/jQuery.blockUI.js"></script>
<script type="text/javascript" src="./static/js/jquery.SuperSlide.js"></script>
<script type="text/javascript">

    jQuery(document).ready(function () {
        jQuery(".slideBox_3").slide({
            mainCell: ".bd ul",
            effect: "fade",
            autoPlay: true,
            interTime: 5000
        });
    });
</script>
<script type="text/javascript">
    var page = 1;
    var limit = 6;
    var cated = -1;
    var self = this;

    window.onload = function () {
        getData();
    }

    $("#newsupmore").on("click", function () {
        self.page = ++self.page;
        getData();
    })

    $("#scren_cate .ct .i").on("click", function () {
        $("#main_suplist_pro").html('');
        self.cated = $(this).attr("cateid");
        self.page = 1;
        getData();
    })

    function getData() {
        var start = this.page == 1 ? 0 : (this.page - 1) * this.limit;
        var param = {"start": start, "limit": limit, "cated": cated};
        var self = this;
        $.ajax({
            url: '/home/usr_UsrSupplier_getSupplierList',
            type: 'get',
            data: param,
            dataType: 'JSON',
            success: function (data) {
                if (data.items.length == 0) {
                    layer.msg(lang_obj.mobile.No_Data, {time: 1200})
                    return;
                }
                renderSupplierList(data.items)
            }
        })
    }

    function renderSupplierList(items) {
        $.each(items, function (i, v) {
            var _div = "<div class=\"list_item fl\" style=\"margin-bottom:0\">\n" +
                "                        <div class=\"ht clean\">\n" +
                "                            <div class=\"fl pic\">\n" +
                "                                <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                "                                   target=\"_blank\">\n" +
                "                                    <img src='${envConfig.imageBaseUrl}" + v.logo + "'>\n" +
                "                                </a>\n" +
                "                            </div>\n" +
                "                            <div class=\"info fr\">\n" +
                "                                <div class=\"name\">\n" +
                "                                    <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                "                                       target=\"_blank\">" + v.name + "</a>\n" +
                "                                </div>\n" +
                "                                <div class=\"ti2 clean\">\n" +
                "                                    <div class=\"ti2_left fl\">\n" +
                "                                        <div class=\"ti2p\">\n" +
                "                                            " + lang_obj.supplier.products + "\n" +
                "                                        </div>\n" +
                "                                        <div class=\"ti2n\">\n" +
                "                                            <a href=\"/home/usr_UsrSupplier_gtSupIndex?pkey=" + v.pkey + "\"\n" +
                "                                               target=\"_blank\">" + v.proDuctCount + "</a>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <!-- .ti2_left -->\n" +
                "                                    <div class=\"ti2_right fr\">\n" +
                "                                        <div class=\"ti2p\">\n" +
                "                                            " + lang_obj.supplier.productsStyle + "\n" +
                "                                        </div>\n" +
                "                                        <div class=\"ti2i\">\n" +
                "                                                " + v.prodpattern + "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <!-- .ti2_right -->\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- .ht -->\n" +
                "                        <div class=\"probox clean\">\n"
            var prbox = "";
            $.each(v.proDuctDtos, function (i, v) {
                prbox +=
                    "                                <div class=\"item fl\">\n" +
                    "                                    <a href=\"/home/pdt_PdtProduct_gtProductsInfo?id=" + v.pkey + "\"\n" +
                    "                                       target=\"_blank\">\n" +
                    "                                        <img src=\"${envConfig.imageBaseUrl}" + v.picture + "\">\n" +
                    "                                    </a>\n" +
                    "                                </div>\n"

            })
            _div += prbox + " </div>\n" + "                    </div>"
            $('#main_suplist_pro').append(_div);
        })
    }

    $('#suside_rm .close').click(function (e) {
        e.stopPropagation();
        $('#suside_rm .bar').css('display', 'block');
        $('#suside_rm .list').css('display', 'none');
        $('#suside_rm .title').css('display', 'none');
    });
    $('#suside_rm .bar').click(function (e) {
        e.stopPropagation();
        $('#suside_rm .title').css('display', 'block');
        $('#suside_rm .list').css('display', 'block');
        $('#suside_rm .bar').css('display', 'none');
    });

    $('.lbtn.btn').click(function () {
            $('#listitems1').css('display', 'block');
            $('#listitems2').css('display', 'block');
            $('#listitems4').css('display', 'none');
            $('#listitems5').css('display', 'none');
        }
    );
    $('.rbtn.btn').click(
        function () {

            $('#listitems1').css('display', 'none');
            $('#listitems2').css('display', 'none');
            $('#listitems4').css('display', 'block');
            $('#listitems5').css('display', 'block');

        }
    );

</script>
<%-- <script type="text/javascript">
    index_scr('#iibox', '.sbox2', '.ii', '#iibox .lbtn', '#iibox .rbtn');
</script> --%>
<script type="text/javascript" src="./static/js/module.js"></script>
<script type="text/javascript" src="./static/js/review.js"></script>
<script type="text/javascript" src="./static/js/lightbox.min.js"></script>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:"#sup_main"
    })
</script>
<%-- <script type="text/javascript" src="./static/js/addthis_widget.js"></script> --%>

</body>


</html>
