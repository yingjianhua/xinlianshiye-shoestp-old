package irille.homeAction.usr;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.github.benmanes.caffeine.cache.Cache;
import com.sun.mail.util.MailSSLSocketFactory;

import irille.homeAction.HomeAction;
import irille.pub.Exp;
import irille.pub.LogMessage;
import irille.pub.Str;
import irille.pub.util.AppConfig;
import irille.pub.util.CacheUtils;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrMainDao;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

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

  /**
   * pc端注册(3.0)
   *
   * @author chen
   */
  public void regist() throws IOException {
    String code = verifyCode();
    System.out.println("..." + getBean().getEmail());
    if (Str.isEmpty(code) || Str.isEmpty(getCheckCode()) || code.equals(getCheckCode()) == false) {
      throw LOG.errTran("signIn%verification", "验证码错误");
    }
    if (!CacheUtils.mailValid.getIfPresent(uid).equals(getBean().getEmail())) {
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
        throw LOG.errTran("验证码错误", "验证码错误");
      }

      title = "User registration";
      if (main != null) {
        throw LOG.errTran("该用户已注册", "该用户已注册");
      }
    }
    if (type == 1) {
      String valide = verifyCode();
      System.out.println("----" + valide);
      if (Str.isEmpty(valide)
          || Str.isEmpty(getCheckCode())
          || valide.equals(getCheckCode()) == false) {
        throw LOG.errTran("验证码错误", "验证码错误");
      }
      title = "Forgot password";
      if (main == null) {
        // throw LOG.errTran("该用户未注册", "该用户未注册");
      }
      write();
    }

    String uid = UUID.randomUUID().toString();

    //    Integer code = (int) ((Math.random() * 9 + 1) * 100000);
    SecureRandom secureRandom = new SecureRandom();
    Integer code = secureRandom.nextInt(999999);
    if (type == 0) {
      CacheUtils.mailValid.put(uid, getEmail());
    }
    if (type == 2) {
      CacheUtils.pwdValid.put(code, getEmail());
    }
    // 收件人电子邮箱
    String to = "";
    to = getEmail();
    // 发件人电子邮箱
    String from = "notice@service.shoestp.com";
    // 指定发送邮件的主机为 smtp.qq.com
    String host = "smtp.mxhichina.com"; // QQ 邮件服务器

    // 获取系统属性
    Properties properties = System.getProperties();

    // 设置邮件服务器
    properties.setProperty("mail.smtp.host", host);

    properties.put("mail.smtp.auth", "true");
    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    sf.setTrustAllHosts(true);
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.ssl.socketFactory", sf);
    // 获取默认session对象
    Session session =
        Session.getDefaultInstance(
            properties,
            new Authenticator() {
              public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                    "notice@service.shoestp.com", "7p7UaRqBb"); // 发件人邮件用户名、密码
              }
            });
    try {
      String mesg = "";
      if (type == 0) {
        mesg = "/home/usr_UsrMain_completeReg?uid=" + uid + "&email=" + email;
      }
      if (type == 2) {
        mesg = "你的验证码为:" + code;
      }
      // 创建默认的 MimeMessage 对象
      MimeMessage message = new MimeMessage(session);

      // Set From: 头部头字段
      message.setFrom(new InternetAddress(from));

      // Set To: 头部头字段
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: 头部头字段
      message.setSubject(AppConfig.mail_template_domain + title);

      // 设置消息体
      System.out.println(AppConfig.mail_template_logo + "logo++++++++++++++++++++++++++++++++");
      String text =
          AppConfig.mail_template
              .replaceAll("\\{ForgotUrl\\}", AppConfig.domain + mesg)
              .replaceAll(
                  "\\{Time\\}",
                  DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM, Locale.ENGLISH)
                      .format(new Date()))
              .replaceAll(
                  "\\{Logo\\}",
                  "<img style=\"max-width:350px;\" src=\""
                      + AppConfig.mail_template_logo
                      + "\" border=\"0\">")
              .replaceAll("\\{FullDomain\\}", AppConfig.mail_template_index)
              .replaceAll("\\{Domain\\}", AppConfig.mail_template_domain)
              .toString();
      //            message.setText(text);
      MimeMultipart mainpart = new MimeMultipart();
      MimeBodyPart bodyPart = new MimeBodyPart();
      bodyPart.setContent(text, "text/html; charset=utf-8");
      mainpart.addBodyPart(bodyPart);
      message.setContent(mainpart);
      // 发送消息
      Transport.send(message);
      if (type == 0) {
        write();
      } else if (type == 2) {
        write(code.toString());
      }
    } catch (MessagingException mex) {
      writeErr("邮件发送失败");
    }
  }

  /**
   * 修改密码
   *
   * @author chen
   */
  public void updPwd() throws Exception {
    updPwd.setNewPwd(pwdA);
    updPwd.setOldPwd(pwd);
    updPwd.setEmail(getEmail());
    try {
      updPwd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
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
        throw LOG.errTran("该链接已超时", "该链接已超时");
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
      throw LOG.errTran("该验证码已超时", "该验证码已超时");
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
