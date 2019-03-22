package irille.pub.exception;

public enum ReturnCode {
	success(1), // 成功
	failure(-1), // 成功

	tools_unknow(100), // 功能异常

	service_unknow(200), // 业务逻辑异常
	service_timeout(201), // 登录超时
	service_verification_code(202), // 验证码校验失败
	service_wrong_data(203), // 错误数据
	service_gone(204), // 资源不可用
	service_unauthorized(205), // 没有权限
	service_state_error(206), // 状态异常
	service_user_exists(207), // 用户已存在
	service_user_notfound(208), // 用户不存在
	service_Invalid_verification_code(209), // 无效的验证码
	service_Invalid_UID(210), // 无效的UID
	service_Invalid_Title(211), // 无效的标题
	service_Invalid_email(212), // 无效的邮箱
	cart_checked_error(213), // 请选择产品
	goods_info_No_Spec(214), // 该商品尚未发布规格
	inquiry_wrong_data(215), // 询盘记录不存在
	valid_notnull_token(216), // token不能为空
	wrong_supplier(217), // 该供应商不是指定供应商
	imperfect_data(218), // 报名失败,请填写完整!
	choose_address(219), // 请选择收货地址
	Please_Select_The_Billing_Address(220), // 请先设置帐单邮寄地址
	order_wrong_data(221), // 订单记录不存在
	order_service_state_error(222), // 订单状态异常
	choose_express(223), // 请选择快递方式
	choose_paymode(224), // 请选择支付方式
	choose_currency(225), // 请选择币种
	product_wrong_data(226), // 产品记录不存在
	max_update_numbers(227), // 最多修改次数
	overdue(228), // 已过期
	already_quot(229), // 已经有报价
	time_wrong(230), // 时间异常
	close(231), // 已关闭,不可进行操作
	please_choose_image(232), // 请选择图片
	images_too_much(233), // 图片数量超出上限
	products_too_much(234), // 产品数量超出上限
	reselect_group(235), // 请重新选择分组
	choose_contact(236), // 请重新选择联系人
	group_contact(237), // 联系人已存在XX分组
	please_input_group_name(238), // 请输入分组名字
	please_reselect_input_group_name(239), // 请重写输入分组名称
	reselect_upload_headpic(240), // 请重新上传头像
	mail_exists(241), // 邮箱重复,请使用其它邮箱
	senderErr(242), // 发送人异常
	email_code_wrong(243), // 邮箱验证码错误
	password_format(244), // "密码为6到20为的数字和字母组成
	dif_password(245), // 两次密码输入不一致
	wrong_password(246), // 用户名和密码不匹配
	check_google_username(247), // 校验失败，请确认Google账号是否正确
	username_email_no_binding(248), // 该账户未与邮箱绑定，无法第三方登陆
	origin_password_wrong(249), // 原密码错误
	no_province(250), // 省份记录不存在
	send_ofen(251), // 发送邮件过于频繁
	third_unknow(300), // 第三方库异常

	db_unknow(400), // 数据库异常

	valid_unknow(500), // 表单校验异常
	valid_notnull(501), // 不能为空
	valid_notempty(502), // 不能为空字符串
	valid_notblank(503), // 不能没有内容
	valid_regex(504), // 正则校验不通过
	valid_illegal(505), // 非法参数
	valid_toolong(506), // 过长
	valid_tooShort(507), // 过短
	valid_mailRegex(508), // 邮箱格式不正确
	valid_phoneRegex(509), // 手机格式不正确
	valid_pwdRegex(510), // 密码格式不正确
	valid_mail_notnull(511), // 邮箱不能为空
	valid_pwd_notnull(512), // 密码不能为空
	valid_name_notnull(513), // 姓名不能为空
	valid_phone_notnull(514), // 手机不能为空
	valid_nameRegex(515), // 姓名格式不正确
	valid_companyNameRegex(516), // 公司名称格式不正确
	valid_price_range(517), // 价格必须在 {2}-{3}之间
	valid_group_name_illegal(518), // 价格必须在 {2}-{3}之间
	valid_adrRegex(519), // 地址格式不正确
	;
	int code;

	private ReturnCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
