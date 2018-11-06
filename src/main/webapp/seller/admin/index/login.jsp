<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>An Online B2B market——-Shoestp.com,gathering 300 professional shoes manufacture companies Business backstage</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/seller/static/admin/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/seller/static/admin/css/login.css" />
	</head>

	<body>
		<div class="m-login-bg">
			<div class="m-login">
				<h3>后台系统登录</h3>
				<div class="m-login-warp">
					<div class="login_err" id="login_err">${sarg1}</div>
					<form class="layui-form" method="post" action="<%=request.getContextPath()%>/seller/usr_UsrSupplier_login">
						<div class="layui-form-item">
							<input type="text" name="bean.loginName" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input" value="${bean.loginName}">
						</div>
						<div class="layui-form-item">
							<input type="password" name="mmCheck" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input"  value="${mmCheck}">
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<input type="text" name="checkCode" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" src="<%=request.getContextPath()%>/servlet/verify.img" />
							</div>
						</div>
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">登录</button>
							</div>
							<div class="layui-inline">
								<button type="reset" class="layui-btn layui-btn-primary">取消</button>
							</div>
						</div>
					</form>
				</div>
				<p class="copyright">Copyright 2015-2016 by XIAODU</p>
			</div>
		</div>
		<script src="<%=request.getContextPath()%>/seller/static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer;

				//自定义验证规则
				form.verify({
					"bean.loginName": function(value) {
						if(value.length < 5) {
							return '标题至少得5个字符啊';
						}
					},
					mmCheck: [/(.+){6,12}$/, '密码必须6到12位'],
					checkCode: [/(.+){6}$/, '验证码必须是6位'],
					
				});

				
				//监听提交
				form.on('submit(login)', function(data) {
					
					/* layer.alert(JSON.stringify(data.field), {
						title: '最终的提交信息'
					}) */
					return true;
				});

			});
		</script>
	</body>

</html>