package irille.homeAction.ActivitySignIn;

import com.google.inject.Inject;
import com.sun.mail.util.MailSSLSocketFactory;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsDao;
import irille.Dao.Old.ActivitySignIn.ActivitySignInsOneDao;
import irille.Entity.Activity.ActivityInfo;
import irille.homeAction.HomeAction;
import irille.pub.util.AppConfig;
import irille.pub.util.EmailManager;
import irille.pub.util.EmailUtils;
import irille.shop.as.PKContest;
import irille.view.se.sendEmail;
import lombok.Getter;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 16:00
 */
public class ActivitySignInAction extends HomeAction<PKContest> {
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String tel;
    @Setter
    @Getter
    private String companyname;

    @Setter
    @Getter
    private String inquiry;
    @Setter
    @Getter
    private int country;
    @Inject
    private ActivitySignInsDao activitySignInsDao;
    @Inject
    private ActivitySignInsOneDao activitySignInsOneDao;

    public void signIn() throws IOException {
        ActivityInfo entity = new ActivityInfo();
        entity.setName(getName());
        entity.setCountry(getCountry());
        entity.setTel(getTel());
        entity.setEmail(getEmail());
        entity.setInquiry(getInquiry());
        activitySignInsDao.setB(entity).commit();
        writeErr(1, null);
    }

    public void signInPk() throws IOException {
        PKContest pkContest = new PKContest();
        pkContest.setName(getName());
        pkContest.setTel(getTel());
        pkContest.setEmail(getEmail());
        pkContest.setCompanyname(getCompanyname());
        activitySignInsOneDao.setB(pkContest).commit();
/*        try {
            sendemail(pkContest);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }*/
        sendEmailLoad(pkContest);
        write();
    }
    public  void  sendEmailLoad(PKContest pk) throws IOException {
        String text = AppConfig.mail_template2
                .replaceAll("\\{name\\}", pk.getName())
                .replaceAll("\\{email\\}",pk.getEmail())
                .replaceAll("\\{tel\\}", pk.getTel())
                .replaceAll("\\{companyname\\}",pk.getCompanyname())
                .toString();
        sendEmail se=new sendEmail();
        se.setSubject("有客户报名了订购会活动");
        se.setContent(text);
        EmailManager.sendMail(se);
    }
/*    public  void sendemail(PKContest pk) throws GeneralSecurityException {

        // 收件人电子邮箱
        String to = "894295467@qq.com";
        // 发件人电子邮箱
        String from = "notice@service.shoestp.com";
        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.mxhichina.com";  //QQ 邮件服务器
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
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("notice@service.shoestp.com", "7p7UaRqBb"); //发件人邮件用户名、密码
            }
        });
        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: 头部头字段
            message.setSubject("有客户报名了订购会活动");
            // 设置消息体
            String text = AppConfig.mail_template2
                    .replaceAll("\\{name\\}", pk.getName())
                    .replaceAll("\\{email\\}",pk.getEmail())
                    .replaceAll("\\{tel\\}", pk.getTel())
                    .replaceAll("\\{companyname\\}",pk.getCompanyname())
                    .toString();
            MimeMultipart mainpart = new MimeMultipart();
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(text, "text/html; charset=utf-8");
            mainpart.addBodyPart(bodyPart);
            message.setContent(mainpart);
            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }*/

}
