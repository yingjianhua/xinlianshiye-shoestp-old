<%@ page pageEncoding="UTF-8"%>
<div id="new_foot" style="width:100%">
		<div class="wide" style="width:1200px;">
			<div class="foot_letter fr">
				<div class="t"><s:text name="new-foot.Subscribe_To_Our_Newsletter"/></div>
				<div class="t0"><s:text name="new-foot.Get_Information_Of_Our_Latest_Products_And_Promotions"/></div>
				<div class="letter_form">
					<form id="newsletter_form">
						<input type="text" value="" id="Email" class="text" notnull="" format="Email">
						<input type="button" value=<s:text name="new-foot.Subscribe"/> onclick="subsribe()" class="btn button">
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="folink">
			<div class="wide clean">
		<div class="img fl pic_box">
			<a href="#" target="_blank"><img src="/home/static/images/854464db07.png" alt="shoeslogo.com"></a><span></span>
		</div>
		<div class="img fl pic_box">
			<a href="#" target="_blank"><img src="/home/static/images/0f12682298.png" alt="shoesmat.com"></a><span></span>
		</div>
		<div class="img fl pic_box">
			<a href="#" target="_blank"><img src="/home/static/images/3c27f32d85.png" alt="shoesrd.com"></a><span></span>
		</div>
		<div class="img fl pic_box">
			<a href="#" target="_blank"><img src="/home/static/images/d30276cc0d.png" alt="wzsomt.com"></a><span></span>
		</div>
			</div>
		</div>
		<div class="focontact">
			<div class="wide">
				<div class="fc fl">
					<div class="fl"><s:text name="new-foot.Share_It"/>:</div>
					<div class="fl fcfollow">
						<div class="follow_toolbox clearfix">
							<ul>
								<li>
									<a rel="nofollow" class="follow_facebook" href="#" target="_blank"
									    title="Facebook">Facebook</a>
								</li>
								<li>
									<a rel="nofollow" class="follow_twitter" href="#" target="_blank" title="Twitter">Twitter</a>
								</li>
								<li>
									<a rel="nofollow" class="follow_pinterest" href="#" target="_blank" title="Pinterest">Pinterest</a>
								</li>
								<li>
									<a rel="nofollow" class="follow_youtube" href="#" target="_blank"
									    title="YouTube">YouTube</a>
								</li>
								<li>
									<a rel="nofollow" class="follow_linkedin" href="#" target="_blank"
									    title="LinkedIn">LinkedIn</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="fd fr">Copyright © 2016-2017 温州新联实业股份有限公司. All Rights Reserved. 浙ICP备16034166号-1 浙公网安备 33030402000493号 &nbsp;&nbsp;&nbsp;&nbsp;
					</div>
			</div>
		</div>
	</div>
	<div id="showe" style="display:none;">
		<span id="showtext" style="background-color: #20b3a6;position: fixed;top: 38%;left: 47%;opacity: 0.8;padding: 20px;border-radius:  14px;color: #fff;font-size: 24px;"></span>
	</div>
	
<script type="text/javascript">
	var listpage={};
	$.ajax({
		type:'post',
		async:false,
		url:'/home/cnt_CntSglPageCategory_listAll',
		dataType:'json',
		success:function(data){
			if(data.ret == 1) {
				listpage=data.result;
				 $.each(data.result,function(i,val){
					var str="<div class='foot_list fl'>"
					+"<div class='title_"+val.id+" title'>"+val.name+"</div>";
					$.each(val.pages,function(j,jal){
						str=str+"<div class='item'>"
			 				+"<a href='/home/cnt_CntSglPageCategory_gosglpage?pkey="+jal.id+"' target='_blank'>"+jal.title+"</a>"
			 				+"</div>";
					})
					str=str+"</div>";
					$("#new_foot div.wide .foot_letter.fr").before(str);
				})
                $(".title_3").parent().append("<div class='item'><a href='/home/usr_UsrSupplier_supplierEntry' target='_blank'>商家入驻</a></div>");
			}
		}
	})
	
	
	function subsribe()
	{
					 if(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test($('#Email').val())==false){
						$("#showtext").text(lang_obj.format.email);
						$("#showe").fadeIn();
						setTimeout("emailshow()",2000);
					}else{	
						$("#showtext").text(lang_obj.newsletter.success);
						$.ajax({
							type:'post',
							url:'/home/usr_UsrSubscribe_ins',
							data:{"bean.email":$('#Email').val()},
							success:function(){
							}
						})
						$("#showe").fadeIn();
						setTimeout("emailshow()",2000);
						
					}
	}
	function emailshow(){
		$("#showe").fadeOut();
	}
	
	
	


	
</script>
