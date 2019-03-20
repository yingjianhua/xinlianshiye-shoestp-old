package irille.pub.validate;

public class Regular {

	public static String REGULAR_ENG = "[A-Za-z]{0,}"; // 英文字符校验
	public static String REGULAR_TEL = "^[+\\d]?\\d{1,3}-\\d{1,16}$"; // 国外手机校验
	public static String REGULAR_EMAIL = "^[\\w]{1,16}@+\\w{1,15}.\\w{2,5}$"; // 邮箱校验
	public static String REGULAR_PWD = "^[^\\s]{6,20}$"; // 密码校验(6-20位字母加数字)
	public static String REGULAR_CHINATEL = "^1\\d{10}$"; // 国内手机校验
	public static String REGULAR_NAME = "^([\\\\u4e00-\\\\u9fa5]{1,20}|[a-zA-Z\\\\.\\\\s]{1,20})$"; // 中英文名字校验
}
