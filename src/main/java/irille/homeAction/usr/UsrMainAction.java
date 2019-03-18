package irille.homeAction.usr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.*;

import javax.inject.Inject;

import com.github.benmanes.caffeine.cache.Cache;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.Str;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.AppConfig;
import irille.pub.util.CacheUtils;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrMainDao;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

/**
 * 用户Action
 *
 * @author chen
 */
public class UsrMainAction extends HomeAction<UsrMain> {

  private static LogMessage LOG = new LogMessage(UsrMainAction.class);

  @Inject private UsrMainDao.Ins ins;
  @Inject private UsrMainDao.updPwd updPwd;
  @Inject private UsrMainDao usrMainDao;

  @Getter @Setter private String checkCode;
  @Getter @Setter private String pwd;
  @Getter @Setter private String pwdA;
  @Getter @Setter private String email;
  @Getter @Setter private Integer type;
  @Getter @Setter private String firstName;
  @Getter @Setter private String lastName;
  @Getter @Setter private String telPre;
  @Getter @Setter private String telMid;
  @Getter @Setter private String telAft;
  @Getter @Setter private String loginName;
  @Setter @Getter private String code;

  @Getter @Setter private String thirdName;
  @Getter @Setter private String thirdId;
  @Setter @Getter private String uid;
  @Inject private Mailer mailer;
  private static Map<String, String> mailTemplate;

  static {
    mailTemplate = new HashMap<>();
    List<String> list = Arrays.asList("checkEmail", "forgetPassWord");
    for (String fileName : list) {
      BufferedReader reader =
          new BufferedReader(
              new InputStreamReader(
                  UsrMainAction.class.getResourceAsStream("/mailPage/" + fileName + ".html")));
      StringJoiner stringJoiner = new StringJoiner("\r\n");
      try {
        while (reader.ready()) {
          stringJoiner.add(reader.readLine());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      mailTemplate.put(fileName, stringJoiner.toString());
    }
  }

  /**
   * pc端注册(3.0)
   *
   * @author chen
   */
  public void regist() throws IOException {
    String code = verifyCode();
    if (Str.isEmpty(code) || Str.isEmpty(getCheckCode()) || code.equals(getCheckCode()) == false) {
      throw new WebMessageException(
          MessageBuild.buildMessage(
              ReturnCode.service_verification_code, HomeAction.curLanguage()));
    }
    if (uid == null
        || CacheUtils.mailValid.getIfPresent(uid) == null
        || !CacheUtils.mailValid.getIfPresent(uid).equals(getBean().getEmail())) {
      throw LOG.errTran("signIn%Invalid Uid", "无效的UID");
    }
    insBefore();
    if (getBean().getIdentity() == 1) {
      String name = getFirstName() + "," + getLastName();
      getBean().setContacts(name);
    }
    if (getBean().getIdentity() == 0) {
      String tel = getTelPre() + "-" + getTelMid() + "-" + getTelAft();
      getBean().setTelphone(tel);
    }
    ins.setB(getBean());
    ins.setPwd(getPwd());
    ins.setPwdA(getPwdA());
    ins.commit();
    insAfter();
    write();
    CacheUtils.mailValid.invalidate(uid);
  }

  /**
   * 发送邮件
   *
   * @author chen
   */
  public void sendEm() throws GeneralSecurityException, IOException, JSONException {
    UsrMain main = UsrMain.chkUniqueEmail(false, getEmail());
    String title = "";
    if (type == 0) {
      String valide = verifyCode();
      if (Str.isEmpty(valide)
          || Str.isEmpty(getCheckCode())
          || valide.equals(getCheckCode()) == false) {
        throw LOG.errTran("signIn%verification", "验证码错误");
      }

      title = "User registration";
      if (main != null) {
        throw LOG.errTran("signIn%Username_Exists", "该用户已注册");
      }
    }
    if (type == 1) {
      String valide = verifyCode();
      if (Str.isEmpty(valide)
          || Str.isEmpty(getCheckCode())
          || valide.equals(getCheckCode()) == false) {
        throw LOG.errTran("signIn%verification", "验证码错误");
      }
      title = "Forgot password";
      if (main == null) {
        throw LOG.errTran("signIn%Username_noExists", "该用户未注册");
      }
      write();
    }

    String uid = UUID.randomUUID().toString();

    // Integer code = (int) ((Math.random() * 9 + 1) * 100000);

    switch (type) {
      case 0:
        CacheUtils.mailValid.put(uid, getEmail());
        String mesg =
            AppConfig.domain + "home/usr_UsrMain_completeReg?uid=" + uid + "&email=" + email;
        mailer.sendMail(
            EmailBuilder.startingBlank()
                .withSubject("Shoestp User Registration")
                .prependTextHTML(mailTemplate.get("checkEmail").replaceAll("\\{\\{url}}", mesg))
                .from("Shoestp", "notice@service.shoestp.com")
                .to(getEmail())
                .buildEmail(),
            true);
        write();
        return;
      case 2:
        SecureRandom secureRandom = new SecureRandom();
        Integer code = secureRandom.nextInt(999999);
        CacheUtils.pwdValid.put(code, getEmail());
        mailer.sendMail(
            EmailBuilder.startingBlank()
                .withSubject("Shoestp Rest Your Password")
                .prependTextHTML(
                    mailTemplate
                        .get("forgetPassWord")
                        .replaceAll("\\{\\{CODE}}", String.valueOf(code)))
                .from("Shoestp", "notice@service.shoestp.com")
                .to(getEmail())
                .buildEmail(),
            true);
        write();
        return;
    }
  }

  /**
   * 修改密码
   *
   * @author chen
   */
  public void updPwd() throws Exception {
    if (code == null || code.length() < 1) {
      writeErr(-1, "Invalid Mail Verification Code");
      return;
    }
    if (CacheUtils.pwdValid.getIfPresent(Integer.valueOf(code)) == null
        || !CacheUtils.pwdValid.getIfPresent(Integer.valueOf(code)).equals(getEmail())) {
      writeErr(-1, "Invalid Mail Code or Email");
      return;
    }
    updPwd.setNewPwd(pwdA);
    updPwd.setOldPwd(pwd);
    updPwd.setEmail(getEmail());
    updPwd.commit();
    CacheUtils.pwdValid.invalidate(code);
    write();
  }

  /**
   * 登陆(3.0)
   *
   * @author chen
   */
  public void login() throws Exception {
    UserView userView = usrMainDao.loginValid(loginName, pwd, thirdName, thirdId);
    if (userView != null) {
      setUser(userView);
      JSONObject json = new JSONObject();
      json.put("ret", 1);
      json.put("sign", true);
      writerOrExport(json);
    } else {
      JSONObject json = new JSONObject();
      json.put("ret", 1);
      json.put("sign", false);
      writerOrExport(json);
    }
  }

  /**
   * 完成注册
   *
   * @author chen
   */
  public String completeReg() {
    if (code != null && code.equalsIgnoreCase("status")) {
      setResult("/home/v3/jsp/reg/register-step3.jsp");
    } else {
      Cache cache = CacheUtils.mailValid;
      String value = String.valueOf(cache.getIfPresent(getUid()));
      if (value != null && value.equals(getEmail())) {
        setResult("/home/v3/jsp/reg/register-step2.jsp");
      } else {
        setResult("/home/v3/jsp/reg/register-step3.jsp");
      }
    }
    return HomeAction.TRENDS;
  }

  /**
   * 提交验证码(重置密码)
   *
   * @author chen
   * @throws IOException
   */
  public void subValid() throws IOException {
    Cache cache = CacheUtils.pwdValid;
    String value = String.valueOf(cache.getIfPresent(Integer.parseInt(getCode())));
    if (value != null && value.equals(getEmail())) {
      write();
    } else {
      writeErr(-1, "Invalid Mail Verification Code");
    }
  }

  public String register() {
    setResult("/home/v3/jsp/reg/register-step1.jsp");
    return HomeAction.TRENDS;
  }

  public String forget() {
    setResult("/home/v3/jsp/forgetPassword/index.jsp");
    return HomeAction.TRENDS;
  }
}
