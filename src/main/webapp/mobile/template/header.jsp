<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--谷歌统计代码 运营加的--%>
<script async src="https://www.googletagmanager.com/gtag/js?id=AW-783435725"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'AW-783435725');
    gtag('config', 'UA-127715615-6')

</script>
<script type="text/javascript" src="/home/static/js/lang/${env.curLanguage }.js"></script>
<script type="text/javascript" src="/home/static/js/moment-with-locales.min.js"></script>
<%-- <script type="text/javascript" src="../home/static/themes/default/mobile/js/global(1).js"></script> --%>
<script type="text/javascript">
	var stpshop_config={
		"domain":"${envConfig.domain}",
		"lang":"${envConfig.lang}",
		"currency":"${envConfig.currency}",
		"currency_symbols":"${envConfig.currencySymbols}",
		"currency_rate":"${envConfig.currencyRate}",
		"userId":"${envConfig.userId}",
		"systemTime":${envConfig.systemTime},
		"timezoneOffset":${envConfig.timezoneOffset},//服务器时间和UTC时间的时间差(分钟)
		"imageBaseUrl":"+${envConfig.imageBaseUrl}+"
	};
	var timezoneOffset = new Date().getTimezoneOffset();
	Date.toLocale = function(systemTime) {
		if(typeof(systemTime) == "string")
			systemTime = parseInt(systemTime)
		return new Date(systemTime+(timezoneOffset*60*1000+stpshop_config.timezoneOffset));
	}
	var ueeshop_config = stpshop_config;
	
	
	

</script>
<c:if test="${empty supView.imList || fn:length(supView.imList) == 0}">
	<!--Start of Tawk.to Script-->
	<script type="text/javascript">
	var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
	(function(){
	var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
	s1.async=true;
	s1.src='/home/static/js/tawk.js';
	s1.charset='UTF-8';
	s1.setAttribute('crossorigin','*');
	s0.parentNode.insertBefore(s1,s0);
	})();
	</script>
	<!--End of Tawk.to Script-->
</c:if>
<c:if test="${not empty supView.imList && fn:length(supView.imList) > 0}">
	<c:set var="flag" value="true" />
	<c:forEach items="${supView.imList}" var="im" varStatus="state">
		<c:if test="${flag == true}">
			<c:if test="${im.type == 0 || im.type == 2 }">
				${im.demo}
				<c:set var="flag" value="false"/>
			</c:if>
			<c:if test="${im.type != 0 && im.type != 2}">
				<script type="text/javascript">
					var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
					(function(){
					var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
					s1.async=true;
					s1.src='/home/static/js/tawk.js';
					s1.charset='UTF-8';
					s1.setAttribute('crossorigin','*');
					s0.parentNode.insertBefore(s1,s0);
					})();
				</script>
			</c:if>
		</c:if>
	</c:forEach>
</c:if>
<script>

function getMessage(str){
	var sourceStr = str;
	if(str.indexOf("##") != -1){
		str = str.split("##")[0];
	}
	var key = str.split("%")[0];
	var value = str.split("%")[1];
	var message = lang_obj[key][value];
	
	if(sourceStr.indexOf("##") != -1){
		var arr = sourceStr.split("##");
		var arrs = new Array();
		for(var i = 1;i<arr.length;i++){
			arrs[i-1] = arr[i];
		}
		message = message.format(...arrs);
	}
	return message;
}

String.prototype.format = function() {
	if (arguments.length == 0)
		return this;
	for (var s = this, i = 0; i < arguments.length; i++)
		s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
	return s;
};
</script>

<style>
	#tawkchat-status-text-container{
		bottom:35px !important;
	}
</style>
<header>
        <div class="header_top clean FontBgColor">
            <div class="head_bg_col clean">
                <div class="fl icon menu pic_box">
                    <a href="javascript:;">
                        <img src="/home/static/themes/default/mobile/images/header_icon_0_2.png" alt="">
                    </a>
                    <span>
                    </span>
                </div>
                <div class="logo fl pic_box">
                    <a href="/">
                        <img src="/home/static/themes/default/mobile/images/de968c6924.png" alt="">
                    </a>
                    <span>
                    </span>
                </div>
								<%-- 原网站的user图标，没有跳转地址，按总监意思先隐藏 --%>
                <%-- <div class="fr dun icon pic_box">
                    <a href="#">
                        <img src="/home/static/themes/default/mobile/images/guanj.png" alt="">
                    </a>
                    <span>
                    </span>
                </div> --%>
                 <div class="inq" style="right: 5.3%;">
                    <a href="/home/usr_UsrConsult_listView">
                        <!-- Inquiry/RFQ -->
                       <s:text name="my-inquiry-publish.View_Inquiry"/>
                    </a>
                </div>
            </div>
        </div>
        <div class="pop_up nav_side">
            <div class="pop_up_container nav_container clean">
                <div class="fl mtitle">
                    <!-- Menu -->
                    <s:text name="Menu"></s:text>
                </div>
                <a class="fr close" href="javascript:;">
                    <em>
                    </em>
                </a>
                <div class="clear">
                </div>
                <nav class="menu_list">
                    <div class="ui_border_b item">
                        <a href="/">
                            <!-- Home -->
                            <s:text name="home"></s:text>
                        </a>
                    </div>
                    <div class="ui_border_b item son">
                        <a href="javascript:;" class="btn_all_category" rel="nofollow">
                            <!-- All Categories -->
                            <s:text name="allCategories"/>
                        </a>
                        <div class="icon">
                            <em>
                                <i>
                                </i>
                            </em>
                        </div>
                    </div>
                    <div class="ui_border_b item">
                        <a href="/home/pdt_PdtProduct">
                            <!-- Products -->
                            <s:text name="products_list"/>
                        </a>
                    </div>
             <%--       <div class="ui_border_b item">
                        <a href="/home/usr_UsrPurchase_center">
                            <!-- Purchasing center -->
                            <s:text name="group_order.activity"/>
                        </a>
                    </div>--%>
                    <div class="ui_border_b item">
                        <a href="/home/pdt_PdtProduct?orderfld=New">
                            <!-- New Arrival -->
                            <s:text name="newProd"/>
                        </a>
                    </div>
                    <div class="ui_border_b item">
                        <a href="https://www.shoestp.com/country/Romania-Pantofi-en-gros/romania-index-ro.html">
                            <!-- România -->
                            <s:text name="Romania"/>
                        </a>
                    </div>
                    <div class="lang_cr clean">
                        <div class="fl box lang">
                            <!-- Language -->
                            <s:text name="language"/>:
                            <s:iterator value="env.languages" var="language">
		                    	<s:if test="shortName==env.curLanguage">
									<s:if test='#language.shortName=="zh_TW"'>繁体中文</s:if>
		                    		<s:elseif test='#language.shortName=="de"'>Deutsch</s:elseif>
		                    		<s:elseif test='#language.shortName=="en"'>English</s:elseif>
		                    		<s:elseif test='#language.shortName=="es"'>Español</s:elseif>
		                    		<s:elseif test='#language.shortName=="fr"'>Français</s:elseif>
		                    		<s:elseif test='#language.shortName=="ja"'>日本語</s:elseif>
		                    		<s:elseif test='#language.shortName=="pt"'>Português</s:elseif>
		                    		<s:elseif test='#language.shortName=="ro"'>românesc</s:elseif>
		                    		<s:elseif test='#language.shortName=="ru"'>русский</s:elseif>
		                    		<s:elseif test='#language.shortName=="zh_CN"'>简体中文</s:elseif>
		                    		<s:elseif test='#language.shortName=="hu"'>magyar</s:elseif>
								</s:if>
							</s:iterator>
                        </div>
                        <div class="fr box currency">
                            <!-- Currency -->
                            <s:text name="currency"/>:${env.currency.shortName }
                        </div>
                    </div>
                </nav>
            </div>
        </div>
		<div class="pop_up category_side">
		    <div class="pop_up_container nav_container clean">
		        <div class="fl category_title">
		            <!--  All Categories -->
		            <s:text name="All_Categories" />
		        </div>
		
		        <a class="fr close close2" href="javascript:;">
		            <em>
		            </em>
		        </a>
		        <div class="clear">
		        </div>
		        <div class="menu_list">
		            <s:iterator value="env.productCat" var="cat1">
		                <div class="ui_border_b item son">
		                    <s:if test="#cat1.subs.size()>0">
		                        <a href="javascript:;" title="${cat1.name }">
		                            ${cat1.name }
		                        </a>
		                        <div class="icon">
		                            <em>
		                                <i></i>
		                            </em>
		                        </div>
		                        <ul class="ui_border_t menu_son">
		                            <s:iterator value="subs" var="cat2">
		                                <li class="item son">
		                                    <s:if test="#cat2.subs.size()>0">
		                                        <a href="javascript:;" title="${cat2.name }">
		                                            ${cat2.name }
		                                        </a>
		                                        <div class="icon">
		                                            <em>
		                                                <i></i>
		                                            </em>
		                                        </div>
		                                        <ul class="menu_son menu_grandson">
		                                            <s:iterator value="subs" var="cat3">
		                                                <li class="item">
		                                                    <a href="/home/pdt_PdtProduct?cated=${cat3.id }" title="${cat3.name }">
		                                                        ${cat3.name }
		                                                    </a>
		                                                </li>
		                                            </s:iterator>
		                                        </ul>
		                                    </s:if>
		                                    <s:else>
		                                        <a href="/home/pdt_PdtProduct?cated=${cat2.id }" title="${cat2.name }">
		                                            ${cat2.name }
		                                        </a>
		                                    </s:else>
		                                </li>
		                            </s:iterator>
		                        </ul>
		                    </s:if>
		                    <s:else>
		                        <a href="/home/pdt_PdtProduct?cated=${cat1.id }" title="${cat1.name }">
		                            ${cat1.name }
		                        </a>
		                    </s:else>
		                </div>
		            </s:iterator>
		        </div>
		    </div>
		</div>
        <div class="pop_up language_side">
			<div class="pop_up_container clean">
				<a class="fr close" href="javascript:;"><em></em></a>
				<div class="clear">
				</div>
				<div class="menu_list">
					<s:iterator value="env.languages" var="language">
						<s:if test="isEnabled">
							<div class="item">
								<a href="javascript:;" lang="${language.shortName}">
									<s:if test='#language.shortName=="zh_TW"'>繁体中文</s:if>
		                    		<s:elseif test='#language.shortName=="de"'>Deutsch</s:elseif>
		                    		<s:elseif test='#language.shortName=="en"'>English</s:elseif>
		                    		<s:elseif test='#language.shortName=="es"'>Español</s:elseif>
		                    		<s:elseif test='#language.shortName=="fr"'>Français</s:elseif>
		                    		<s:elseif test='#language.shortName=="ja"'>日本語</s:elseif>
		                    		<s:elseif test='#language.shortName=="pt"'>Português</s:elseif>
		                    		<s:elseif test='#language.shortName=="ro"'>românesc</s:elseif>
		                    		<s:elseif test='#language.shortName=="ru"'>русский</s:elseif>
		                    		<s:elseif test='#language.shortName=="zh_CN"'>简体中文</s:elseif>
		                    		<s:elseif test='#language.shortName=="hu"'>magyar</s:elseif>
								</a>
							</div>
						</s:if>
					</s:iterator>
				</div>
			</div>
		</div>
		<div class="pop_up currency_side">
			<div class="pop_up_container clean">
				<a class="fr close" href="javascript:;"><em></em></a>
				<div class="clear">
				</div>
				<div class="menu_list">
					<s:iterator value="env.currencys" var="currency">
					<div class="item">
						<a href="javascript:;" data="${currency.id }"><img src="${envConfig.imageBaseUrl}${currency.img }" alt="${currency.shortName }">${currency.shortName }</a>
					</div>
					</s:iterator>
				</div>
			</div>
		</div>
        <div class="header_fill"></div>
        <div id="float_chat">
            <div class="float_list">
                <a href="javascript:;" class="btn_global btn_top">
                    <!-- Top -->
                    <s:text name="top"/>
                </a>
            </div>
        </div>
    </header>
