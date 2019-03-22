package irille.pub.validate;

public class Regular {

	public static String REGULAR_ENG = "[A-Za-z]{0,}"; // 英文字符校验
	public static String REGULAR_TEL = "^[+\\d]?\\d{1,3}-\\d{1,16}$"; // 国外手机校验
	public static String REGULAR_EMAIL = "^[\\w]{1,32}@\\w{1,15}.\\w{2,5}$"; // 邮箱校验
	public static String REGULAR_PWD = "^[^\\s]{6,20}$"; // 密码校验(6-20位字母加数字)
	public static String REGULAR_ARRRESS = "^[^!@~`%^&*()+|\\\\}{\":?/].{1,180}$"; // 地址校验 限制!@%^&*()`~ 等符号输入 其他任意,
																					// 长度限制为180 数据库长度为200
	public static String REGULAR_CHINATEL = "^((1\\d{10})|(\\d{4}-?\\d{8}))$"; // 国内手机/电话校验 格式 138111111111
																				// 0511-11111111
	// 051111111111
	/** * 考虑到其他语种问题..首尾不能为符号 且 长度在1-32为之间 */
	public static String REGULAR_NAME = "^([^`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|]).{0,32}(?<![`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|])$"; // 校验名字

	/** * 考虑到其他语种问题..首尾不能为符号 且 长度在1-52为之间 */
	public static String REGULAR_COMPANY = "^([^`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|]).{0,52}(?<![`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|])$"; // 公司名字校验
	public static String REGULAR_WEBSITE = "^http[s]?:\\/\\/[\\w]{1,}.?[\\w]{1,}.?[\\w/.?&=-]{1,}$"; // 公司网址校验 请输入完整网址格式，如https://www.shoestp.com
	public static String REGULAR_NUMBER = "^([1-9]\\d*|0)(\\.\\d*[1-9])?$"; // 纯数字校验 请填写数字,不能以0开头
	public static String REGULAR_FAX = "^(\\d{3,4}-)?\\d{7,8}$"; // 传真 格式 0511-11111111(010-3333333)
	public static String REGULAR_POSTCODE = "^[0-9]{6}$"; // 邮编 格式 320000
  	public static String REGULAR_CHINESE = "^[\\u4e00-\\u9fa5]{2,6}$"; // 纯中文，长度为2-6个
  	public static String REGULAR_IDCARD = "^(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)$"; // 身份证号码校验 格式 330825199903236569
}
