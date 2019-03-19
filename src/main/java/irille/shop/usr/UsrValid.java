package irille.shop.usr;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.validate.ValidForm;
import org.apache.commons.lang3.StringUtils;

public class UsrValid extends ValidForm {

  private static final LogMessage LOG = new LogMessage(UsrValid.class);

  public UsrValid(Bean bean) {
    super(bean);
  }

  public enum errMsgs implements IMsg { // 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
    wrongErr("【{0}】输入有误,请重新输入"),
    tooBig("【{0}】输入的值过大，不能超过100"),
    tooSmall("【{0}】输入的值过小，不能小于0"),
    userCopy("用户名已存在"),
    wrongPassword("密码输入有误,请检查后重新输入!"),
    ;
    private String _msg;

    private errMsgs(String msg) {
      _msg = msg;
    }

    public String getMsg() {
      return _msg;
    }
  } // @formatter:on

  public void validDiscount(BigDecimal discount, Object... param) {
    if (discount.compareTo(BigDecimal.valueOf(100)) > 0) {
      throw LOG.err(errMsgs.tooBig, param);
    }

    if (discount.compareTo(BigDecimal.ZERO) < 0) {
      throw LOG.err(errMsgs.tooSmall, param);
    }
  }

  public void validMail(String mail, Object... param) {
    String reg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    boolean flag = reg(reg, mail);
    if (flag == false)
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.valid_mailRegex, HomeAction.curLanguage()));
  }

  public void validPhone(String phone, Object... param) {
    String telephone = "^[0-9]*$";
    boolean flag = reg(telephone, phone);
    if (flag == false)
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.valid_mailRegex, HomeAction.curLanguage()));
  }

  public void validCopy(Bean param) {
    if (param != null) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.service_user_exists, HomeAction.curLanguage()));
    }
  }

  private static boolean reg(String reg, String str) {
    Pattern pattern = Pattern.compile(reg);
    Matcher m = pattern.matcher(str);
    return m.matches();
  }

  public void validWrongPassword(String password, String copyPassword) {
    if (StringUtils.isEmpty(copyPassword)
        || StringUtils.isEmpty(password)
        || !password.equals(copyPassword)) {
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.wrong_password, HomeAction.curLanguage()));
    }
  }

  public void validPassword(String password, Object... param) {
    String pwd = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,20})$";
    boolean flag = reg(pwd, password);
    if (flag == false)
      throw new WebMessageException(
          MessageBuild.buildMessage(ReturnCode.valid_pwdRegex, HomeAction.curLanguage()));
  }
}
