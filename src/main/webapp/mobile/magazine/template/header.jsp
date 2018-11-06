<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="./static/js/lang/${env.curLanguage }.js"></script>
<script type="text/javascript" src="./static/js/moment-with-locales.min.js"></script>
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
		"timezoneOffset":${envConfig.timezoneOffset}//服务器时间和UTC时间的时间差(分钟)
	};
	var timezoneOffset = new Date().getTimezoneOffset();
	Date.toLocale = function(systemTime) {
		return new Date(systemTime+(timezoneOffset*60*1000+stpshop_config.timezoneOffset));
	}
	var ueeshop_config = stpshop_config;
</script>
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
                <div class="fr dun icon pic_box">
                    <a href="#">
                        <img src="/home/static/themes/default/mobile/images/guanj.png" alt="">
                    </a>
                    <span>
                    </span>
                </div>
                <div class="inq">
                    <a href="/home/usr_UsrConsult_listView">
                        Inquiry/RFQ
                    </a>
                </div>
            </div>
        </div>
        <div class="pop_up nav_side">
            <div class="pop_up_container nav_container clean">
                <div class="fl mtitle">
                   Menu
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
                            Home
                        </a>
                    </div>
                    <div class="ui_border_b item son">
                        <a href="javascript:;" class="btn_all_category" rel="nofollow">
                            All Categories
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
                            Products
                        </a>
                    </div>
                    <div class="ui_border_b item">
                        <a href="/home/usr_UsrPurchase_center">
                            Purchasing center
                        </a>
                    </div>
                    <div class="ui_border_b item">
                        <a href="/home/pdt_PdtProduct?orderfld=New">
                            New Arrival
                        </a>
                    </div>
                    <div class="ui_border_b item">
                        <a href="javascript:;">
                            România
                        </a>
                    </div>
                    <div class="lang_cr clean">
                        <div class="fl box lang">
                            Language:
                            <s:iterator value="env.languages" var="language">
		                    	<s:if test="shortName==env.curLanguage">${language.displayName }</s:if>
							</s:iterator>
                        </div>
                        <div class="fr box currency">
                            Currency:${env.currency.shortName }
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <div class="pop_up category_side">
            <div class="pop_up_container nav_container clean">
                <div class="fl category_title">
                    All Categories
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
                        <a href="javascript:;" title="${cat1.name }">
                            ${cat1.name }
                        </a>
                        <div class="icon">
                            <em>
                                <i>
                                </i>
                            </em>
                           </div>
                          	<ul class="ui_border_t menu_son">
							<s:iterator value="subs" var="cat2">
	                            <li class="item son">
	                                <a href="javascript:;" title="${cat2.name }">
	                                    ${cat2.name }
	                                </a>
	                                <div class="icon">
	                                    <em>
	                                        <i>
	                                        </i>
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
								</li>
							</s:iterator>
							</ul>
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
								<a href="javascript:;" lang="${language.shortName}">${language.displayName }</a>
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
						<a href="javascript:;" data="${currency.id }"><img src="${currency.img }" alt="${currency.shortName }">${currency.shortName }</a>
					</div>
					</s:iterator>
				</div>
			</div>
		</div>
        <div class="header_fill"></div>
        <div id="float_chat">
            <div class="float_list">
                <a href="javascript:;" class="btn_global btn_top">
                    Top
                </a>
            </div>
        </div>
    </header>
