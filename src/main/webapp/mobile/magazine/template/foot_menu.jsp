<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="foot_blank"></div>
<div id="foot_menu" class="clean">
    <div class="fl item">
        <div class="pic">
            <a href="/">
                <img src="/home/static/themes/default/mobile/images/ficon0.png">
            </a>
        </div>
        <div class="name " >
            <a href="/">
                home
            </a>
        </div>
    </div>
    <div class="fl item" id="ficon1">
        <div class="pic" >
            <a href="/home/pdt_PdtCat_details">
                <img src="/home/static/themes/default/mobile/images/ficon1.png">
            </a>
        </div>
        <div class="name ">
            <a href="/home/pdt_PdtCat_details">
                category
            </a>
        </div>
    </div>
    <div class="fl item" id="ficon2">
        <div class="pic">
            <a href="/home/pdt_PdtProduct">
                <img src="/home/static/themes/default/mobile/images/ficon2.png">
            </a>
        </div>
        <div class="name">
            <a href="/home/pdt_PdtProduct">
                discover
            </a>
        </div>
    </div>
    <div class="fl item " id="ficon3">
        <div class="pic">
            <a href="/home/usr_UsrPurchase_userIndex">
                <img src="/home/static/themes/default/mobile/images/ficon3.png">
            </a>
        </div>
        <div class="name ">
            <a href="/home/usr_UsrPurchase_userIndex">
                account
            </a>
        </div>
    </div>
</div>
<script type="text/javascript">
	var html = location.href;
	//$("div.fl.item img")
	//$("div.fl.item div.pic")
		if(html.indexOf("usr_UsrPurchase")!=-1){
			$("#foot_menu div div a img").eq(0).attr("src","/home/static/themes/default/mobile/images/ficon0_on.png");
			$("div.fl.item div.name").eq(0).attr("class","name on");
		}
		if(html.indexOf("pdt_PdtCat_details")!=-1){
			$("#foot_menu div div a img").eq(1).attr("src","/home/static/themes/default/mobile/images/ficon1_on.png");
			$("div.fl.item div.name").eq(1).attr("class","name on");
		}
		if(html.indexOf("pdt_PdtProduct")!=-1){
			$("#foot_menu div div a img").eq(2).attr("src","/home/static/themes/default/mobile/images/ficon2_on.png");
			$("div.fl.item div.name").eq(2).attr("class","name on");
		}
		if(html.indexOf("usr_UsrPurchase_userIndex")!=-1){
			$("#foot_menu div div a img").eq(0).attr("src","/home/static/themes/default/mobile/images/ficon0.png");
			$("#foot_menu div div a img").eq(3).attr("src","/home/static/themes/default/mobile/images/ficon3_on.png");
			$("div.fl.item div.name").eq(3).attr("class","name on");
		}
</script>