<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    <link href="/home/static/css/style.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global.css" rel="stylesheet" type="text/css">
    <link href="/home/static/css/global(1).css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/home/static/js/en.js"></script>
<script type="text/javascript" src="/home/static/js/lang/${env.curLanguage }.js"></script>
<script type="text/javascript" src="/home/static/js/global.js"></script>
<script type="text/javascript" src="/home/static/js/global(1).js"></script>
<script type="text/javascript" src="/home/static/js/user.js"></script>
<script type="text/javascript" src="/home/static/js/moment-with-locales.min.js"></script>
<script type="text/javascript">
	$(window).resize(function () {
        $(window).webDisplay(2);
    });
    $(window).webDisplay(2);
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
<div id="web_top">
		<div class="wide clean">
			<div class="top_lang fl">
				<dl>
					<dt class="">
						<span> <!--                -->
							<s:text name="language" />: 
							<s:iterator value="env.languages" var="language">
		                    	<s:if test="shortName==env.curLanguage">${language.displayName }</s:if>
							</s:iterator>
						</span>
					</dt>
					<dd class="language lang">
						<s:iterator value="env.languages" var="language">
	                    	<s:if test="isEnabled"><a rel="nofollow" href="javascript:void(0);" lang="${language.shortName}">${language.displayName }</a></s:if>
						</s:iterator>
						<form action="" method="GET" hidden>
							<input name="request_locale">
						</form>
					</dd>
				</dl>
			</div>
            <!--        <div class="top_currency fl"></div>-->
		
            <div class="top_mem fr">
                <div class="mendiv">
                	<s:if test="env.login==null">
	                    <a rel="nofollow" href="javascript:;" class="SignInButton FontColor"><s:text name="sign_in" /></a> <s:text name="or" />
	                    <a rel="nofollow" href="/home/usr_UsrPurchase_sign" class="FontColor"><s:text name="join_free" /></a>
                	</s:if>
                	<s:else>
                		<s:text name="welcome" /><a href="/home/usr_UsrPurchase_userIndex">${env.login.loginName}</a>
   							<dl class="fl list">
   								<dd class="user">
   									<a rel="nofollow" href="/home/odr_OdrOrder_orders"><s:text name="my_orders" /></a>
   									<a rel="nofollow" href="/home/usr_UsrFavorites_myfavorite"><s:text name="my_fav" /></a>
   									<%-- <a rel="nofollow" href="https://www.shoestp.com/account/coupon/"><s:text name="my_coupon" /></a> --%>
   									<%-- <a rel="nofollow" href="https://www.shoestp.com/account/inbox/"><s:text name="my_inbox" /></a> --%>
   									<a rel="nofollow" href="/home/usr_UsrPurchase_signOut"><s:text name="sign_out" /></a>
   								</dd>
   							</dl>
                	</s:else>
                </div>
                <script type="text/javascript">
                    $(document).ready(function () {
                    	moment.locale('${env.curLanguage}');
   						user_obj.sign_in_init();
                    	$("#web_top .language.lang a").on("click", function() {
                    		$.ajax({
                    			url:"/home/plt_PltConfig_changeLanguage",
                    			type:"GET",
                    			dataType:"json",
                    			data:"request_locale="+$(this).attr("lang"),
                    			success:function(data) {
                    				if(data.ret==1)
                    					location.reload();
                    			}
                    		})
                    	})
                    });
                </script>
            </div>
            <div class="fr top_cart">
                <div class="fl img" >
                    <i class="inq_count">${env.login==null?0:env.login.inquiryCount }</i>
                </div>
                <a href="/home/usr_UsrConsult_listView" class="fl"><s:text name="my-inquiry-publish.View_Inquiry"/></a>
            </div>
            <div class="fr top_my_fav">
                <div class="fl img">
                	<i class="fav_count">${env.login==null?0:env.login.favoriteCount }</i>
                </div>
                <a href="/home/usr_UsrFavorites_myfavorite" class="fl"><s:text name="my_fav" /></a>
            </div>
            <div class="fr top_cart top_cart0">
                <div class="fl img">
                    <i class="cart_count">${env.login==null?0:env.login.cartCount}</i>
                </div>
                <a href="/home/usr_UsrCart_cartshopping" class="fl cart_inner"><s:text name="shoppingCart" /></a>
            </div>
        </div>
    </div>
