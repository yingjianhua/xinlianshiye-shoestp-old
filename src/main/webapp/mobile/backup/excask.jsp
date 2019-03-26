<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <style>
        /* 询盘列表 begin */
        .clearfloat:after {
            display: block;
            clear: both;
            content: "";
            visibility: hidden;
            height: 0
        }
        .clearfloat {
            zoom: 1
        }
        .user-inquiry-list .inquiry-list{
            margin: 0 0.625rem;
        }
        .user-inquiry-list .inquiry-list ul{
            margin: 5% 0;
        }
        .user-inquiry-list .inquiry-list ul li{
            margin-top: 5%;
        }
        .user-inquiry-list .inquiry-list ul li input[type=checkbox]{
            zoom: 150%;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-img {
            width: 26%;
            font-size: 0;
            border: 1px solid #dedede;
            margin-left: 2%;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-info{
            width: 60%;
            margin-left: 2%;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-name p{
            line-height: 0.9rem;
            font-size: 12px;
            width: 88%;
            overflow: hidden;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 4;
            -ms-text-overflow: ellipsis;
            text-overflow: ellipsis;
            white-space: normal;
            word-wrap:break-word;
            word-break:break-all;
            word-wrap:break-word;
            position: relative;
            max-height: 3.5rem;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time{
            line-height: 0.9rem;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time p{
            line-height: 180%;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time p,.user-inquiry-list .inquiry-list ul li .inquiry-list-info .inquiry-list-time span{
            color: #919191;
        }
        .user-inquiry-list .inquiry-list ul li .inquiry-list-info select{
            border-radius:5px;
            -webkit-border-radius:5px;
            -moz-border-radius:5px;
            padding: 1% 0;
            width: 60%;
            margin: 1% 0;
        }
        .user-inquiry-list .inquiry-list ul li i{
            display: inline-block;
        }
        .user-inquiry-list .inquiry-list ul li .icon-close{
            width: 14px;
            height: 14px;
            overflow: hidden;
            background: url(../../mobile/static/images/nav_close.png) center center no-repeat;
            margin: 0 3px;
        }
        .user-inquiry-list .inquiry-list ul li .icon-messages{
            background: url(../../mobile/static/images/icon_review.gif) center center no-repeat;
            width: 1.3125rem;
            height: 1.125rem;
        }
        .user-inquiry-list .inquiry-list .more-button{
            width: 5rem;
            height: 1.875rem;
            background-color: #d7d7d7;
            color: #fff;
            line-height: 1.875rem;
            text-align: center;
            border-radius: 10px;
            margin: 5% auto;
        }
        /* 询盘列表 end */
    </style>
</head>

<body>
	<%@include file="/mobile/template/header.jsp" %>
    <div class="wrapper">
        <div id="user">
            <div class="crumb clean">
                <a href="/">
                    <span class="icon_crumb_home">
                    </span>
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/usr_UsrPurchase_userIndex">
                    <!-- My Account -->
                    <s:text name="my_account" />
                </a>
                <em>
                    <i>
                    </i>
                </em>
                <a href="/home/odr_OdrOrder_orders">
                    <!-- Inquiry list -->
                    
                    <s:text name="inq" />
                </a>
            </div>
            <div class="user-inquiry-list">

                <div class="inquiry-list">
                    <ul ID="lineShow">
                        <li class="clearfloat">
                            <input type="checkbox" class="fl">
                            <div class="inquiry-list-img fl">
                                <img src="../../mobile/static/images/pic_180_03.jpg" alt="">
                            </div>
                            <div class="fl inquiry-list-info">
                                <div class="inquiry-list-name clearfloat">
                                    <p class="fl">
                                            DABOWEN LADIES FASHION MID-HIGH RIVET SHOES LACE-UP YOUTH TREND CASUAL FLAT LOAFER LOAFER LOAFER
                                    </p>
                                    <i class="icon-close fr"></i>
                                </div>
                                <select name="" id="">
                                    <option value="">ASHOP</option>
                                    <option value="">BSHOP</option>
                                    <option value="">CSHOP</option>
                                    <option value="">NONE</option>
                                </select>
                                <div class="inquiry-list-time clearfloat">
                                    <p class="fl">2018-04-25 <span>14:31:35</span></p>
                                    <i class="icon-messages fr"></i>
                                </div>
                            </div>
                        </li>
                        <li class="clearfloat">
                            <input type="checkbox" class="fl">
                            <div class="inquiry-list-img fl">
                                <img src="../../mobile/static/images/pic_180_03.jpg" alt="">
                            </div>
                            <div class="fl inquiry-list-info">
                                <div class="inquiry-list-name clearfloat">
                                    <p class="fl">
                                            DABOWEN LADIES FASHION MID-HIGH RIVET SHOES LACE-UP YOUTH TREND CASUAL FLAT LOAFER LOAFER LOAFER
                                    </p>
                                    <i class="icon-close fr"></i>
                                </div>
                                <select name="" id="">
                                    <option value="">ASHOP</option>
                                    <option value="">BSHOP</option>
                                    <option value="">CSHOP</option>
                                    <option value="">NONE</option>
                                </select>
                                <div class="inquiry-list-time clearfloat">
                                    <p class="fl">2018-04-25 <span>14:31:35</span></p>
                                    <i class="icon-messages fr"></i>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <!-- 加载更多按钮 -->
                    <div class="more-button">
                        Load more
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div id="prolist_mask_footer">
        </div>
        <div class="footer_top clean">
        </div>
        <ul class="footer_list ui_border_b clean" style="display:none;">
            <li class="fl" style="border-right:1px solid #ddd;">
                <a href="/home/usr_UsrPurchase_userIndex" class="clean">
                    <span class="list_left">
                        Sign In
                    </span>
                    <span class="list_right">
                        <em>
                            <i>
                            </i>
                        </em>
                    </span>
                </a>
            </li>
        </ul>
        <nav>
        </nav>
        <section class="font_col border_col copyright">
            Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
            <%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
        </section>
    </footer>
    <%@ include file="/mobile/template/foot_menu.jsp" %>
</body>
<script type="text/javascript">
window.onload=function(){
	   $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/pdt_PdtConsult_selAskForPur',
			dataType:'json',
			success:function(data){
			  $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/pdt_PdtConsultServe_selectByPur',
			dataType:'json',
			success:function(askData){
			var selectSup='';
			$("#lineShow").html("");
			$.each(data.items,function(i,dataitem){
		    selectSup='';
			$.each(askData.items,function(i,askitem){
			if(askitem.purchasers==dataitem.purchasers&&askitem.consult==dataitem.pkey){
			selectSup="<option value ='"+askitem.supplier.pkey+"'>"+askitem.supplier.name+"</option>"+selectSup;
			}
			});
			var pic='';
			if(dataitem.product==null){
			var picArray=dataitem.image.split(',');
			pic=picArry[0];
			}else{
			pic=dataitem.product.picture;
			}
			$("#lineShow").append("<li class='clearfloat'>"
                +"<input type='checkbox' class='fl'>"
                +"<div class='inquiry-list-img fl'>"
                +"<img src='${envConfig.imageBaseUrl}"+pic+"' alt=''>"
                +"</div>"
                +"<div class='fl inquiry-list-info'>"
                +"<div class='inquiry-list-name clearfloat'>"
                +"<p class='fl'>"
                +"<a href='http://"+window.location.host+"/mobile/view-inquiry.html?askid="+dataitem.pkey+"'>"+dataitem.title+"</a>"
                +"</p>"
                +"<i class='icon-close fr' onclick='del("+dataitem.pkey+","+dataitem.rowVersion+")'></i>"
                +"</div>"
                +"<select id='sel"+dataitem.pkey+"'>"+selectSup+"</select>"
                +"<div class='inquiry-list-time clearfloat'>"
                +"<p class='fl'>2018-04-25 <span>"+dataitem.time+"</span></p>"
                +"<i class='icon-messages fr'></i>"
                +"</div>"
                +"</div>"
                +"</li>");
			});
					}
				})

			}
		})
}
function del(pkey,rowVersion){
	   if($('#sel'+pkey).children().length<=0){
		  if(confirm("确认删除？")){
		   $.ajax({
			type:'post',
			url:'http://'+window.location.host+'/home/pdt_PdtConsult_del?pkey='+pkey+'&rowVersion='+rowVersion+'',
			dataType:'json',
			success:function(askData){
			console.log(askData.success);
			if(askData.success==true){
      layer.open({
        content: '删除成功'
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
      });
			location.reload();
			}else{
      layer.open({
        content: '删除失败'
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
      });
			}
		  }
	   })
		  }
	   }else{
       layer.open({
         content: '供应商已接单无法删除!'
         ,skin: 'msg'
         ,time: 3 //2秒后自动关闭
       });
	   }

}
</script>
</html>
