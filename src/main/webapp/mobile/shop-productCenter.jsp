<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en" data-dpr="1">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <link href="/home/static/themes/default/mobile/css/style_new.css" rel="stylesheet" type="text/css">
</head>
<body style="font-size: 12px;">
<div class="page page-enterprise-product flexible flex-column fill">
    <!-- 顶上 - 固定的头部条 -->
    <%@ include file="/mobile/template/header.jsp" %>
    <!-- 顶上 - 固定的头部条 -end -->
    <!-- 公司头部 - 含背景图 -->
    <div class="scroll flex-grow">
        <div class="enterprise-top-header flexible flex-c-center">
            <!-- 公司logo -->
            <div class="logo">
                <img src="<c:if test='${not empty supView.logo}'>${envConfig.imageBaseUrl}${supView.logo}</c:if><c:if test='${empty supView.logo}'>/home/static/images/headimg.jpg</c:if>"
                     alt="logo">
            </div>
            <!-- 公司简介信息 -->
            <div class="enterprise-profile">
                <div class="text">
                    ${supView.showName}
                </div>
                <!-- 图标组 -->

                <div class="icon-group flexible flex-c-center">
                    <c:if test="${not empty supView.authAge && supView.authAge > 0}">
                        <!-- 自制年限图标 -->
                        <div class="icon icon-years-limit-group">
                            <a href="/m/spain/company.html#data" target="_blank">
                                <img src="/home/static/themes/default/mobile/images/doller_pic_o.png" alt="">
                                <span class="years-num">${supView.authAge}</span>
                                <span class="years-unit">YRS</span>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${supView.isAuth == 1}">
                        <!-- 自制年限图标 -->
                        <a href="#" target="_blank">
                            <img class="icon" src="/home/static/themes/default/mobile/images/defence-safe-o.png"
                                 alt="defence-safe">
                        </a>
                        <a href="#" target="_blank">
                            <img class="icon" src="/home/static/themes/default/mobile/images/certification-o.png"
                                 alt="certification">
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- 顶部导航条  -->
        <%@ include file="template/shop-nav.jsp" %>


        <!-- 顶部导航条 - end -->
        <!-- 主内容 -->
        <div class="flex-grow">
            <div class="main">

                <!--- 工具栏 -->
                <ul class="tool-group">
                    <li class="tool-item">
                                <span class="tool-name">
                                    <!-- Sort by -->
                                    <s:text name="sort_by"/>
                                </span>
                        <div class="tool-content tool-drop-down-wrap">
                            <div class="tool-drop-down-btn">
                                        <span class="select-text" id="sort">
                                            <!-- 已选择的内容 -->
                                            <!-- Sort by -->
                                            <s:text name="most_popular"/>
                                        </span>
                                <i class="icon icon-arrow-down">
                                </i>
                            </div>
                            <div class="tool-drop-down-content">
                                <ul>
                                    <li>
                                        <a href="javascript:chooseThisSort(0);">
                                            <!-- Most Popular -->
                                            <s:text name="most_popular"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:chooseThisSort(1);">
                                            <s:text name="products.sales"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:chooseThisSort(2);">
                                            <!--  Favorites -->
                                            <s:text name="Favorites"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:chooseThisSort(3);">
                                            <!-- New -->
                                            <s:text name="new"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:chooseThisSort(4);">
                                            <!-- Price -->
                                            <s:text name="cart.price"/>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <li class="tool-item">
                                <span class="tool-name">
                                    <!-- Classify -->
                                    <s:text name="article.article_category"/>
                                </span>
                        <div class="tool-content tool-drop-down-wrap">
                            <div class="tool-drop-down-btn">
                                        <span class="select-text" id="category">
												<!-- All Categories -->
												<s:text name="All_Categories"/>
                                        </span>
                                <i class="icon icon-arrow-down">
                                </i>
                            </div>
                            <div class="tool-drop-down-content">
                                <ul class="category">
                                    <li><a onclick="chooseThisCat('')"><s:text name="allCategories"/></a></li>
                                    <c:forEach items="${topDiyCat}" var="cat">
                                        <li><a onclick="chooseThisCat(${cat.pkey},'${cat.name}')">${cat.name}</a></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
                <!-- 工具栏 - end -->
                <!-- 商品栏 -->
                <div class="goods-show-wrap goods-show-col-2 col-two flexible">
                    <%-- <c:forEach items="${productList}" var="pro">
                        <div class="goods-item-box">
                        <div class="goods-item">
                            <div class="goods-pic">
                                <a href="/home/pdt_PdtProduct_gtProductsInfo?id=${pro.pkey}"
                                title="${pro.name}">
                                    <c:forTokens items="${pro.picture}" delims="," var="pic" end="0">
                                        <img src="${envConfig.imageBaseUrl}${pic}">
                                    </c:forTokens>
                                </a>
                                <span>
                                </span>
                                <i class="badge badge-hot">
                                </i>
                            </div>
                            <div class="goods-info-wrap">
                                <div class="descript">
                                    <a href=""
                                    title="${pro.name}"
                                    target="_blank">
                                        ${pro.name}
                                    </a>
                                </div>
                                <div class="price">
                                    ${pro.curPrice}
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach> --%>

                </div>
                <!-- 商品栏 - end -->
                <div class="click_more" page="2" style="width:30em;">
			                    <span id="viewMore" style="display: inline-block;">
			                        <!-- View More -->
			                        View More
			                    </span>
                </div>
            </div>
            <!-- main - end -->
        </div>
        <!-- 主内容 - flex-grow - end -->
    </div>
    <!-- 公司头部 - 含背景图 - end -->
    <%@ include file="/mobile/template/foot_menu.jsp" %>
    <!-- 底部 - end -->
</div>
</body>
<script type="text/javascript">
    var pkey = '${supView.pkey}';
    var page = 1;
    var cated = -1;
    var rankingBasis = -1;
    var type = 1;
    var allPage = 0;

    window.onload = function () {
        getData();
    }

    function getData() {
        var param = {
            "pkey": pkey,
            "page": page,
            "limit": 8,
            "cated": cated,
            "rankingBasis": rankingBasis,
            "basis": type
        };
        var self = this;
        $.ajax({
            url: '/home/pdt_PdtProduct_getProductBySup',
            type: 'post',
            data: param,
            dataType: 'JSON',
            success: function (data) {
                if (data.items.length == 0) {
                    //$("#noGoods").show();
                } else {
                    renderProduct(data.items);
                    self.allPage = data.pageAll;
                }
            }
        })
    }

    function renderProduct(items) {
        $.each(items, function (i, val) {
            var div = '<div class="goods-item-box">' +
                '<div class="goods-item">' +
                '<div class="goods-pic">' +
                '<a href="/' + val.rewrite + '"' +
                'title="' + val.pdt.name + '">' +
                '<img src="' + getFirstPic(val.pdt.picture) + '" >' +
                '</a>' +
                '<span>' +
                '</span>' +
                '<i class="badge ' + getHot(val.pdt.isHot) + '">' +
                '</i>' +
                '</div>' +
                '<div class="goods-info-wrap">' +
                '<div class="descript">' +
                '<a href=""' +
                'title="' + val.pdt.name + '"' +
                'target="_blank">' +
                val.pdt.name +
                '</a>' +
                '</div>' +
                '<div class="price">' +
                <%--'${env.currency.symbols}' + val.pdt.curPrice +--%>
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
            $(".goods-show-wrap").append(div);
        })
    }

    function getHot(val) {
        return val == 1 ? 'badge-hot' : '';
    }

    function getFirstPic(str) {
        if (str == '') {
            return '/home/static/images/noImage.png';
        } else {
            if (str.indexOf(",") != -1) {
                return '${envConfig.imageBaseUrl}' + str.split(",")[0];
            } else {
                return '${envConfig.imageBaseUrl}' + str;
            }
        }
    }


    function chooseThisSort(sort) {
        switch (type) {
            case 1:
                type = 0;
                break;
            case 0:
                type = 1;
                break;
        }
        this.rankingBasis = sort;
        var sortName = '<s:text name="most_popular"/>';
        switch (sort) {
            case 1:
                sortName = '<s:text name="sales"/>';
                break;
            case 2:
                sortName = '<s:text name="fav"/>';
                break;
            case 3:
                sortName = '<s:text name="new"/>';
                break;
            case 4:
                sortName = '<s:text name="price"/>';
                break;
            default:
                sortName = '<s:text name="most_popular"/>';
        }
        $("#sort").text(sortName)
        self.page = 1;
        $(".goods-show-wrap").html('');
        getData();
    }

    function chooseThisCat(cat, catName) {
        this.cated = cat;
        $("#category").text(catName);
        self.page = 1;
        $(".goods-show-wrap").html('');
        getData();
    }

    // 点击选择下拉框按钮，显示下拉内容
    $(".tool-group .tool-drop-down-btn").click(function () {
        $(".tool-group .tool-drop-down-content").hide();
        $(".tool-group .tool-drop-down-btn").removeClass("active");
        $(this).toggleClass("active")
            .siblings(".tool-drop-down-content").toggle();
    })

    // 获得点击事件
    $(document).click(function (e) {
        var target = $(e.target);
        // 如果点击的是 选择下拉框中，则不干什么；如果点击的是其余地方，则将显示出来的下拉内容隐藏掉
        if (target.closest(".tool-drop-down-wrap").length !== 0) return;
        $(".tool-group .tool-drop-down-content").hide();
        $(".tool-group .tool-drop-down-btn").removeClass("active");
        // e.stopPropagation();
    })
    var self = this;
    $(".click_more").on("click", function () {
        self.page = ++self.page;
        if (self.page > self.allPage) {
            self.page = --self.page;
            layer.open({content: "已加载所有商品", skin: 'msg', time: 2});
            return;
        }
        getData();
        /* var page = parseInt($(this).attr("page"));
        var param = {};
        param.id =

        ${supView.pkey};
        	param.page = page;
        	param.limit = 8;
        	if(category != undefined && category != ""){
	        	param.cated = category;
        	}
        	if(sort != undefined && sort != ""){
	        	param.sort = sort;
        	}
        	var self = $(this);
        	$.ajax({
        		url:'/home/pdt_PdtProduct_gtSupPro',
        		type:'post',
        		data:param,
        		dataType:'json',
        		success:function(data){
        			if(data.items.length == 0){
        				$(".click_more").hide();
        				layer.open({content: "已加载所有商品",skin: 'msg',time:2});
        				return;
        			}
        			$.each(data.items,function(i,val){
	        			var div = '';
        				div = '<div class="goods-item-box">'+
			                            '<div class="goods-item">'+
			                        '<div class="goods-pic">'+
			                            '<a href="/home/pdt_PdtProduct_gtProductsInfo?id='+val.pkey+'"'+
			                            'title="'+val.name+'">'+
			                            	'<img src="

        ${envConfig.imageBaseUrl}'+getPic(val.picture)+'" />'+
			                            '</a>'+
			                            '<span>'+
			                            '</span>'+
			                            '<i class="badge badge-hot">'+
			                            '</i>'+
			                        '</div>'+
			                        '<div class="goods-info-wrap">'+
			                            '<div class="descript">'+
			                                '<a href=""'+
			                                'title="'+val.name+'"'+
			                                'target="_blank">'+
			                                    val.name+
			                                '</a>'+
			                            '</div>'+
			                            '<div class="price">'+
			                                val.curPrice+
			                            '</div>'+
			                        '</div>'+
			                    '</div>'+
			                '</div>';
	        			$(".goods-show-wrap").append(div);

        			})

        			$(self).attr("page",++page);
        		}
        	}) */
    })

    function getPic(str) {
        return str.split(",")[0];
    }

    /*  $(function(){
         $.ajax({
             url:'/home/pdt_PdtCat_selBsicCat',
             type:'POST',
             dataType:'JSON',
             success:function(data){
                 var li = '<li><a onclick="chooseThisCat(\'\')">All Categories</a></li>';
                 $.each(data.items,function(i,val){
                     if(typeof(val.categoryUp) == "undefined"){
                         li = li + '<li><a onclick="chooseThisCat('+val.pkey+')">'+val.name+'</a></li>';
                     }
                 });
                 $(".category").html(li);
             }
         });
     }); */
</script>

</html>
