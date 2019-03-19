package irille.pub.validate;

public class Regular {

	public static String REGULAR_ENG = "[A-Za-z]{0,}"; // 英文字符校验
	public static String REGULAR_TEL = "^[+\\d]?\\d{1,3}-\\d{1,16}$"; // 手机校验
	public static String REGULAR_EMAIL = "^[\\w]{1,16}@+\\w{1,15}.\\w{2,5}$"; // 邮箱校验
	public static String REGULAR_PWD = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,20})$"; // 密码校验(6-20位字母加数字)
}
