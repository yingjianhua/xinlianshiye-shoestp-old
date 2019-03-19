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
	service_Invalid_Code(212), // 无效的code
	service_Wrong_CodeOrEmail(213), // 错误的code
	service_Wrong_PwdOrEmail(214), // 用户名和密码不匹配
	service_Wrong_RepeatPwd(215), // 两次密码输入不一致

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
	valid_phone_notnull(514),// 手机不能为空
	;
	int code;

	private ReturnCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
