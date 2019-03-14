package irille.homeAction.usr;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONException;
import org.json.JSONObject;

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

/**
 * 用户Action
 *
 * @author chen
 */
public class UsrMainAction extends HomeAction<UsrMain> {

  private static LogMessage LOG = new LogMessage(UsrMainAction.class);

  public String getCheckCode() {
    return checkCode;
  }

  public void setCheckCode(String checkCode) {
    this.checkCode = checkCode;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getPwdA() {
    return pwdA;
  }

  public void setPwdA(String pwdA) {
    this.pwdA = pwdA;
  }

  private String checkCode;
  private String pwd;
  private String pwdA;
  private String email;
  private Integer type;
  private String firstName;
  private String lastName;
  private String telPre;
  private String telMid;
  private String telAft;

  public String getTelPre() {
    return telPre;
  }

  public void setTelPre(String telPre) {
    this.telPre = telPre;
  }

  public String getTelMid() {
    return telMid;
  }

  public void setTelMid(String telMid) {
    this.telMid = telMid;
  }

  public String getTelAft() {
    return telAft;
  }

  public void setTelAft(String telAft) {
    this.telAft = telAft;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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
    insBefore();
    if (getBean().getIdentity() == 1) {
      String name = getFirstName() + "," + getLastName();
      getBean().setContacts(name);
    }
    if (getBean().getIdentity() == 0) {
      String tel = getTelPre() + "-" + getTelMid() + "-" + getTelAft();
      getBean().setTelphone(tel);
    }
    System.out.println(getBean().getNickname());
    UsrMainDao.Ins ins = new UsrMainDao.Ins();
    ins.setB(getBean());
    ins.setPwd(getPwd());
    ins.setPwdA(getPwdA());
    try {
      ins.commit();
      insAfter();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
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
    Integer code = (int) ((Math.random() * 9 + 1) * 100000);
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
        mesg = "/home/usr_UsrMain_completeReg?uid=" + uid;
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
    UsrMainDao.updPwd upd = new UsrMainDao.updPwd();
    upd.setNewPwd(pwdA);
    upd.setOldPwd(pwd);
    upd.setEmail(getEmail());
    try {
      upd.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  private String loginName;

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  private String thirdName;
  private String thirdId;

  public String getThirdName() {
    return thirdName;
  }

  public void setThirdName(String thirdName) {
    this.thirdName = thirdName;
  }

  public String getThirdId() {
    return thirdId;
  }

  public void setThirdId(String thirdId) {
    this.thirdId = thirdId;
  }

  /**
   * 登陆(3.0)
   *
   * @author chen
   */
  public void login() throws Exception {
    UsrMainDao ud = new UsrMainDao();
    if (ud.loginValid(loginName, pwd, thirdName, thirdId)) {
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

  private String uid;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  /**
   * 完成注册
   *
   * @author chen
   */
  public String completeReg() {
    Cache cache = CacheUtils.mailValid;
    String value = String.valueOf(cache.getIfPresent(Integer.parseInt(getUid())));
    if (value != null && value.equals(getEmail())) {
      setResult("/home/sendEmail.jsp");
      return HomeAction.TRENDS;
    } else {
      throw LOG.errTran("该链接已超时", "该链接已超时");
    }
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

  private String code;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
