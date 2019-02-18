<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html><!-- saved from url=(0033)https://www.shoestp.com/supplier/ -->
<html lang="en">
<head>
    <%@include file="/mobile/template/style_import.jsp" %>
    <link href="/home/static/themes/default/mobile/css/supplier.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/swipe.js"></script>
    <script type="text/javascript" src="/home/static/themes/default/mobile/js/supplier.js"></script>
    <style type="text/css">
      .footer_top{background:#f4f7fb;}
      .spsearchm{background-color:#f9f9f9;}
    </style>
</head>

<body>
<%@include file="/mobile/template/header.jsp" %>
<div class="wrapper spmain">
	<div class="w94ps">
		<div class="spblue_t">
			<!-- Recommend -->
			<s:text name="recommend"/>
		</div>
		<div class="new_suppx" id="new_suppx_touch">
			<div class="list">
                    <c:forEach var="d" items="${supplierDto.recommendList}">
                        <div class="list_item fl" style="margin-bottom: 0px;">
							<div class="ht clean">
								<div class="fl pic">
									<a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" target="_blank"><img src="${envConfig.imageBaseUrl}${d.logo}"></a>
								</div>
								<div class="info fr">
									<div class="name">
										<a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}" target="_blank">${d.name}</a>
									</div>
									<div class="ti2 clean">
										<div class="ti2_left fl">
											<div class="ti2p">
												<s:text name='order_line.spec'/>
											</div>
											<div class="ti2n">
												${d.proDuctCount}
											</div>
										</div>
										<!-- .ti2_left -->
										<div class="ti2_right fr">
											<div class="ti2p">
												<s:text name='mobile.feat_pro'/>
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
								<c:forEach var="img" items="${d.proDuctDtos}">
									<div class="item fl">
										<a href="${img.rewrite}"><img src="${envConfig.imageBaseUrl}${img.picture}"></a>
									</div>
								</c:forEach>
							</div>
						</div>
                    </c:forEach>
		</div>

		<div class="spblue_t spgray_t">
			<div class="fr suppx_cate">
				<div class="cate fl" id="scren_cate">
					<div class="t">
						<!-- All Categories -->
						<s:text name="All_Categories"/>
					</div>
					<div class="ct">
						<div class="mnm">
							<c:forEach items="${supplierDto.category}"  var="cate">
                                <div class="i" cateid="${cate.id}">
                                        ${cate.name}
                                </div>
                            </c:forEach>
						</div>
					</div>
				</div>
			</div>
			<!-- Manufacturers -->
			<s:text name="Manufacturers"/>
		</div>
		<div class="new_suppx" id="new_suppx_list" style="min-height:200px;">
			<div class="list_item" id="supList">
				<div class="ht clean" id="supListMain">
<!-- 					<div class="fl pic"> -->
<!-- 						<a href="https://www.shoestp.com/m/dabowen/" target="_blank"><img src="/home/static/themes/default/mobile/images/1a4a1ebf69.jpg"></a> -->
<!-- 					</div> -->
					 <%-- <c:forEach items="${supplierDto.manufacturersList}" var="d">
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
                                            <!-- Products -->
                                            <s:text name="order_line.spec"/>
                                        </div>
                                        <div class="ti2n">
                                            <a href="/home/usr_UsrSupplier_gtSupIndex?pkey=${d.pkey}"
                                               target="_blank"></a>
                                        </div>
                                    </div>
                                    <!-- .ti2_left -->
                                    <div class="ti2_right fr">
                                        <div class="ti2p">
                                            <!-- Products Style -->
                                            <s:text name="mobile.feat_pro"/>
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
                            <c:forEach items="${d.proDuctDtos}" var="dd">
                                <div class="item fl">
                                    <a href="${dd.pkey}"
                                       target="_blank">
                                        <img src="${envConfig.imageBaseUrl}${dd.picture}">
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach> --%>
			<!-- .list_item -->
		</div>
		<!-- <a class="index_supplier_more" href="javascript:;" data-page="1" data-cateid="0">
		View More
		</a> -->
		<div class="index_supplier_more">
                    <span page="2">
                        <!-- View More -->
                        <s:text name="read_more" />
                    </span>
        </div>
	</div>
	<!-- .w94ps -->
</div>
<footer>
<div id="prolist_mask_footer">
</div>
<div class="footer_top clean">
</div>
<ul class="footer_list ui_border_b clean" style="display:none;">
	<li class="fl" style="border-right:1px solid #ddd;">
	<a href="/home/usr_UsrPurchase_userIndex" class="clean">
	<span class="list_left"><!-- Sign In --><s:text name="sign_in" />
	</span>
	<span class="list_right"><em><i></i></em></span>
	</a>
	</li>
	<li class="list_lang fl">
	<a href="javascript:void(0);" class="clean">
	<span class="list_left"><!-- Language --><s:text name="language"/></span>
	<span class="list_right" style="text-align:right;"><em style="margin-left:0.6rem;"><i></i></em>English</span>
	</a>
	</li>
</ul>
<nav></nav><section class="font_col border_col copyright">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved.
	<%--浙ICP备16034166号-1 浙公网安备 33030402000493号--%>
</section></footer>
<%@ include file="/mobile/template/foot_menu.jsp" %>

<script type="text/javascript">
	var page = 1;
	var limit = 6;
	var cated = -1;
	var self = this;

	window.onload = function(){
		  getData();
	}

	function getData(){
		var start = this.page == 1?0:(this.page - 1) * this.limit;
	  var param = {"start":start,"limit":limit,"cated":cated};
	  var self = this;
	  $.ajax({
		  url:'/home/usr_UsrSupplier_getSupplierList',
		  type:'get',
		  data:param,
		  dataType:'JSON',
		  success:function(data){
			  if(data.items.length == 0){
				  layer.open({
			            content: lang_obj.mobile.No_Data
			            ,skin: 'msg'
			            ,time: 2
			          });
				  return;
			  }
			  renderData(data.items)
		  }
	  })
	}


  $('#scren_cate .t').click(function(e) {
    e.stopPropagation();
    $('#scren_cate .ct').toggle(0);
        });
  $('#scren_cate .ct .i').click(function (e){
	  $("#supListMain").html('');
	self.cated = parseInt($(this).attr("cateid"));
	self.page = 1;
	getData();
    /* var cateid = parseInt($(this).attr('cateid'));
    self.catId = cateid;
    var name = $(this).text().trim();
    $('#scren_cate .t').html(name);
    $('#scren_cate .ct').css('display', 'none');
    $.get('/home/usr_UsrSupplier_gtSupplierAndPdtListAjax', {limit:6,page:1,"Cated":catId}, function (data){
      $('#new_suppx_list #supList .ht.clean').html('');
      var items = JSON.parse(data);
    	renderData(items.items);
      $('.index_supplier_more').attr('data-page',1);
      $('.index_supplier_more').attr('data-cateid',cateid);
      $('.index_supplier_more').show();
      if(!data) $('.index_supplier_more').hide();
    }); */
  });
  $('.index_supplier_more span').click(function () {
	  self.page = ++self.page;
	  getData();
     /*  var limit = 6
      var self = this;
      var page = parseInt($(self).attr('page'));
      $(self).fadeOut();
      $.ajax({
  		type:'post',
  		url:'home/usr_UsrSupplier_gtSupplierAndPdtListAjax',
  		data:{limit:limit,page:page,"Cated":catId},
  		dataType:'json',
  		success:function(data){
  			renderData(data.items);
        if ((limit * page) < data.total) {
                    $(self).fadeIn();
                    $(self).attr('page', ++page)
                }
  			}
  		}) */
  });

  function renderData(data){
	  $.each(data, function (i, d) {
		  var prbox = "";
          $.each(d.proDuctDtos, function (j, v) {
              prbox +="<div class='item fl'>"+
							"<a href='/home/pdt_PdtProduct_gtProductsInfo?id="+v.pkey+"'><img src='${envConfig.imageBaseUrl}"+v.picture+"'></a>"+
						"</div>";
          })

    	    $("#supListMain").append(" <div class='list_item fl' style='margin-bottom:0'><div class='ht clean'><div class='fl pic'>"
    	    +"<a href='/home/usr_UsrSupplier_gtSupIndex?pkey="+d.pkey+"' target='_blank'>"
    	    +"<img src='${envConfig.imageBaseUrl}"+d.logo+"'></a>"
    	    +"</div>"
    	    +"<div class='info fr'>"
    	    +"<div class='name'>"
    	    +"<a href='/home/usr_UsrSupplier_gtSupIndex?pkey="+d.pkey+"' target='_blank'>"+d.name+"</a>"
    	    +"</div>"
    	    +"<div class='ti2 clean'><div class='ti2_left fl'><div class='ti2p'><s:text name='order_line.spec'/></div>"
    	    +"<div class='ti2n'>"
    	    +"<a href='/home/usr_UsrSupplier_gtSupIndex?pkey="+d.pkey+"' target='_blank'></a>"
    	    +"</div></div> "
    	    +"<div class='ti2_right fr'><div class='ti2p'><s:text name='mobile.feat_pro'/></div><div class='ti2i'>"+getProdpatternd(d.prodpattern)+"</div></div></div></div></div>"
    	    +"<div class='probox clean'>"
    	    + prbox
    	    +"</div>"
    	    +"</div></div>");
      		})
  }

  function getProdpatternd(data){
	  if(data == "null" || data == null){
		  return "";
	  }else{
		  return data;
	  }
  }
</script>

</body>
</html>
