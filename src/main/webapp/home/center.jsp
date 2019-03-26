<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<meta name="description" content="">
	<meta name="author" content="">
	<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies</title>
    <link href="./static/css/style.css" rel="stylesheet" type="text/css">
        <link href="./static/css/global.css" rel="stylesheet" type="text/css">
    <link href="./static/css/global(1).css" rel="stylesheet" type="text/css">
     <script type="text/javascript" src="./static/js/jquery-1.7.2.min.js"></script>
	<style type="text/css">
		.title_all {
			width: 300px;
			margin: 50px auto;
		}

		.content_all {
			width: 1140px;
			margin: auto;
		}

		#footer {
			background: #eff1f1;
			width: 100%;
			height: 280px;
			padding-top: 10px;
		}

		.button_contact {
			width: 300px;
			margin: auto;
			text-align: center;
		}

		.button_all {
			background: #7ac5c5;
			width: 200px;
			height: 50px;
			border-radius: 25px;
			border: none;
			color: #fff;
			font-size: 18px;
			font-weight: bold;
		}

		.area {
			text-align: center;
			margin-bottom: 80px;
		}

		.area a {
			color: #fff;
		}

		.more {
			border: none;
			padding: 10px;
			margin-top: 20px;
			background: #7ac5c5;
			position: relative;
			top: 10px;
		}
	</style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title></title>
    <style type="text/css">

        .clearfix:after {
            content: "";
            height: 0;
            line-height: 0;
            display: block;
            visibility: hidden;
            clear: both;
        }

        .clearfix {
            zoom: 1;
        }

        .title_all {
            width: 300px;
            margin: 50px auto;
        }

        .pc-content_all {
            width: 1140px;
            margin: auto;
        }

        #pc-footer {
            background: #eff1f1;
            width: 100%;
            height: 280px;
            padding-top: 10px;
        }

        .button_contact {
            width: 300px;
            margin: auto;
            text-align: center;
        }

        .button_all {
            background: #7ac5c5;
            width: 200px;
            height: 50px;
            border-radius: 25px;
            border: none;
            color: #fff;
            font-size: 18px;
            font-weight: bold;
        }

        .pc-title {
            width: 1200px;
            margin: 0 auto;
            text-align: center;
            height: 200px;
            padding: 75px 0;
            box-sizing: border-box;
        }

        .pc-list {
            width: 1200px;
            margin: 0 auto;
            padding-bottom: 10px;
        }

        .new-style-page .pc-list a,
        .new-style-page .pc-list a:link{
            display: inline;
        }

        .pc-c-item {
            width: 192px;
            height: 271px;
            border-radius: 10px;
            float: left;
            margin-right: 60px;
            margin-bottom: 40px;
        }
        .pc-list a:nth-child(5n+5) .pc-c-item {
            margin-right: 0;
        }

        .pc-c-item .pc-c-cn,
        .pc-c-item .pc-c-en {
            text-align: center;
            line-height: 20px;
            padding-top: 40px;
        }

        .pc-c-item .pc-c-en {
            font-size: 12px;
            color: #ffffff;
        }

        .pc-c-item .pc-c-cn {
            font-size: 16px;
            color: #ffffff;
            display: none;
        }
    </style>
</head>
<jsp:include page="v3/header.jsp"/>
<jsp:include page="v3/nav.jsp"/>
<body class="lang_en w_1200">
<jsp:include page="template/web-top.jsp"></jsp:include>
<%--<jsp:include page="template/new-header.jsp"></jsp:include>--%>

    <div class="cbox0">

        
        <div id="app">
            <index-top></index-top>
        </div>
            <img alt="Responsive image" class="img-responsive center-block " src="/home/static/images/centimg/banner.jpg" style="width: 100%;min-width:1200px;"/>
        <div class="container">
            <div class="pc-title">
                <div style="font-size: 24px;color: #425661;">Global Purchase Center Store</div>
                <div style="color: #949ea4;"><s:text name="purchaseshop"/></div>
            </div>
            <div class="pc-list clearfix">
    
            </div>
        </div>
        <!-- /.container -->
        <img alt="Responsive image" class="img-responsive center-block " src="/home/static/images/centimg/nav.jpg"  style="width: 100%;min-width:1200px;"/>
        <div class="container-fluid">
            <div class="title_all">
                <img alt="" height="74" src="/home/static/images/centimg/title07.png" width="338" />
            </div>
    
            <div class="pc-content_all">
                <p style="font-size: 17px;text-align:  center;line-height:180%">互联网手段B2B进行供需匹配，帮助鞋企进入全球供应链体系，带来利润与品质的双重提升。结合国际站点辐射到各个国家门店，促进供需精准匹配和产业转型升级，全面提高产品和服务质量.通过整合产业链上下游实现让海外的2C与中国的2B采购互通起来,形成聚集的零担凑整的采购模式。</p>
    
                <p style="font-size: 14px;margin-bottom:  50px;line-height:180%;text-align:  center;">The Internet means B2B matching supply and demand to help shoe companies enter the global supply chain system,
                    bringing double improvements in profits and quality. Combining international sites into stores in various
                    countries to promote accurate matching of supply and demand and industrial transformation and upgrading,
                    and comprehensively improve the quality of products and services. By integrating upstream and downstream
                    of the industrial chain, overseas 2C and China&rsquo;s 2B procurement will be interoperable, forming a concentrated
                    LTL roundup. Purchasing model.
                </p>
            </div>
    
            <div id="pc-footer">
                <div class="title_all">
                    <img alt="" height="74" src="/home/static/images/centimg/title04.png" width="338" />
                </div>
    
                <div class="button_contact">
                    <button class="button_all" onclick="parent.LC_API.open_chat_window({source:'minimized'}); return false">立即联系</button>
                </div>
            </div>
            <index-bottom></index-bottom>
        </div>
        <script>
            var countriesList = {
                center: "Purchase center",
                countries: [{
                        bgImg: "/home/static/images/centimg/c1.png",
                        enName: "GuangZhou",
                        cnName: lang_obj.Purchase_Center_Store.GuangZhou,
                        url:"/home/usr_UsrSupplier_gtSupIndex?pkey=101"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c2.png",
                        enName: "Spain",
                        cnName:lang_obj.Purchase_Center_Store.Spain,
                        url:"/home/usr_UsrSupplier_gtSupIndex?pkey=264"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c3.png",
                        enName: "Chile",
                        cnName: lang_obj.Purchase_Center_Store.Chile,
                        url:"/home/usr_UsrSupplier_gtSupIndex?pkey=261"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c4.png",
                        enName: "Romania",
                        cnName: lang_obj.Purchase_Center_Store.Romania,
                        url:"/home/usr_UsrSupplier_gtSupIndex?pkey=262"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c5.png",
                        enName: "Hungary",
                        cnName: lang_obj.Purchase_Center_Store.Hungary,
                        url:"/home/usr_UsrSupplier_gtSupIndex?pkey=263"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c6.png",
                        enName: "America",
                        cnName: lang_obj.Purchase_Center_Store.America,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c7.png",
                        enName: "Canada",
                        cnName: lang_obj.Purchase_Center_Store.Canada,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c8.png",
                        enName: "Russia",
                        cnName: lang_obj.Purchase_Center_Store.Russia,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c9.png",
                        enName: "Belarus",
                        cnName: lang_obj.Purchase_Center_Store.Belarus,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c10.png",
                        enName: "Ukraine",
                        cnName: lang_obj.Purchase_Center_Store.Ukraine,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c11.png",
                        enName: "Poland",
                        cnName: lang_obj.Purchase_Center_Store.Poland,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c12.png",
                        enName: "Germany",
                        cnName:lang_obj.Purchase_Center_Store.Germany,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c13.png",
                        enName: "Czekh",
                        cnName: lang_obj.Purchase_Center_Store.Czekh,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c14.png",
                        enName: "England",
                        cnName: lang_obj.Purchase_Center_Store.England,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c15.png",
                        enName: "Ireland",
                        cnName: lang_obj.Purchase_Center_Store.Ireland,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c16.png",
                        enName: "Holland",
                        cnName: lang_obj.Purchase_Center_Store.Holland,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c17.png",
                        enName: "France",
                        cnName: lang_obj.Purchase_Center_Store.France,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c18.png",
                        enName: "Finland",
                        cnName: lang_obj.Purchase_Center_Store.Finland,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c19.png",
                        enName: "Sweden",
                        cnName: lang_obj.Purchase_Center_Store.Sweden,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c20.png",
                        enName: "Norway",
                        cnName: lang_obj.Purchase_Center_Store.Norway,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c21.png",
                        enName: "Denmark",
                        cnName: lang_obj.Purchase_Center_Store.Denmark,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c22.png",
                        enName: "Korea",
                        cnName: lang_obj.Purchase_Center_Store.Korea,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c23.png",
                        enName: "Japan",
                        cnName: lang_obj.Purchase_Center_Store.Japan,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c24.png",
                        enName: "Philippines",
                        cnName: lang_obj.Purchase_Center_Store.Philippines,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c25.png",
                        enName: "Singapore",
                        cnName: lang_obj.Purchase_Center_Store.Singapore,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c26.png",
                        enName: "Israel",
                        cnName: lang_obj.Purchase_Center_Store.Israel,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c27.png",
                        enName: "Turkey",
                        cnName: lang_obj.Purchase_Center_Store.Turkey,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c28.png",
                        enName: "Australia",
                        cnName: lang_obj.Purchase_Center_Store.Australia,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c29.png",
                        enName: "New Zealand",
                        cnName: lang_obj.Purchase_Center_Store.New_Zealand,
                         url:"#"
                    },
                    {
                        bgImg: "/home/static/images/centimg/c30.png",
                        enName: "South Africa",
                        cnName: lang_obj.Purchase_Center_Store.South_Africa,
                         url:"#"
                    },
                ]
            }
       
         var item = ''
        $.each(countriesList.countries, function (index, ele) {
            if(index < 5){
                item +=
                `
                <a href="${'${ele.url}'}" target="_blank">
                    <div class="pc-c-item" style="background:url(${'${ele.bgImg}'}) 100% 100% no-repeat;">
                        <div class="pc-c-en">
                            <span>${'${ele.enName}'}</span><br/>
                            <span>${'${countriesList.center}'}</span>
                        </div>
                        <div class="pc-c-cn">
                            <span>${'${ele.cnName}'}</span>
                        </div>
                    </div>
                </a>
         `
            }else{
                item +=
                `
                <a href="javascript:void(0);">
                    <div class="pc-c-item" style="background:url(${'${ele.bgImg}'}) 100% 100% no-repeat;">
                        <div class="pc-c-en">
                            <span>${'${ele.enName}'}</span><br/>
                            <span>${'${countriesList.center}'}</span>
                        </div>
                        <div class="pc-c-cn">
                            <span>${'${ele.cnName}'}</span>
                        </div>
                    </div>
                </a>
         `
            }
            
        })
        $(".pc-list").html(item)
        $('.pc-list').on('mouseover mouseout','a .pc-c-item',function(event){
            if(event.type == "mouseover"){
                $(this).find('.pc-c-cn').show()
                $(this).find('.pc-c-en').hide()
            }else if(event.type == 'mouseout'){
                $(this).find('.pc-c-cn').hide()
                $(this).find('.pc-c-en').show()
            }
})

        </script>
    </div>





	<%--<jsp:include page="template/new-foot.jsp"></jsp:include>--%>
<script src="/home/v3/static/js/index-top.js"></script>
<script src="/home/v3/static/js/index-bottom.js"></script>
<script>
    new Vue({
        el:"#app"
    })
</script>
</body>

</html>