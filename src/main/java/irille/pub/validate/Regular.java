package irille.pub.validate;

public class Regular {

  public static String REGULAR_ENG = "[A-Za-z]{0,}"; // 英文字符校验
  public static String REGULAR_TEL = "^[+\\d]?\\d{1,3}-\\d{1,16}$"; // 国外手机校验
  public static String REGULAR_EMAIL = "^[\\w]{1,32}@\\w{1,15}.\\w{2,5}$"; // 邮箱校验
  public static String REGULAR_PWD = "^[^\\s]{6,20}$"; // 密码校验(6-20位字母加数字)
  public static String REGULAR_CHINATEL =
      "^((1\\d{10})|(\\d{4}-?\\d{8}))$"; // 国内手机/电话校验  格式 138111111111    0511-11111111
  // 051111111111
  /** * 考虑到其他语种问题..首尾不能为符号 且 长度在1-32为之间 */
  public static String REGULAR_NAME =
      "^([^`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|]).{0,32}(?<![`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|])$"; // 校验名字

  /** * 考虑到其他语种问题..首尾不能为符号 且 长度在1-52为之间 */
  public static String REGULAR_COMPANY =
      "^([^`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|]).{0,52}(?<![`~!@#$%^&*()+= -\\]\\[';/.,<>?:\"{}|])$"; // 公司名字校验
}
