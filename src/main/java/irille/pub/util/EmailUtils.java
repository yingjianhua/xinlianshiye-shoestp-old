package irille.pub.util;

import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.util.MailSSLSocketFactory;
import com.sun.mail.util.PropUtil;
import irille.view.se.sendEmail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Slf4j
public class EmailUtils {
    public static boolean sendMail(sendEmail mailVo) throws IOException {
        boolean flag = false;
        InputStream inStream= EmailUtils.class.getClassLoader().getResourceAsStream("email.properties");
        Properties prop = new Properties();
        prop.load(inStream);
        String smtpHost = "";
        String smtpport = "";
        String from = "";
        String password = "";
        String to = "";
        String subject = "";
        String messageText = "";
        try {
            smtpHost = prop.getProperty("mail.smtp.host");
            smtpport = prop.getProperty("mail.smtp.port");
            from = prop.getProperty("mail.sender");
            password = prop.getProperty("mail.smtp.password");
            if(null == mailVo.getReceiver()){
                to = prop.getProperty("to");
            }else{
                to = mailVo.getReceiver();
            }
            subject = mailVo.getSubject();
            messageText = mailVo.getContent();
            int sendFlag = sendMessage(smtpHost, smtpport, from, password, to, subject, messageText);
            flag = true;
            if(sendFlag == 200){
                log.debug("邮件发送成功：发件人 = " + from + "; 收件人 = " + to + "; 主题  = " + subject + "; 内容  = " + messageText);
            }else{
                log.debug("邮件发送失败：发件人 = " + from + "; 收件人 = " + to + "; 主题  = " + subject + "; 内容  = " + messageText);
            }
        } catch (Exception e) {
            log.debug("发送邮件失败，失败内容：smtpHost = " + smtpHost);
            log.debug("发送邮件失败，失败内容：smtpport = " + smtpport);
            log.debug("发送邮件失败，失败内容：from = " + from);
            log.debug("发送邮件失败，失败内容：password = " + password);
            log.debug("发送邮件失败，失败内容：to = " + to);
            log.debug("发送邮件失败，失败内容：smtpHost = " + smtpHost);
            log.debug("发送邮件失败，失败内容：subject = " + subject);
            log.debug("发送邮件失败，失败内容：messageText = " + messageText);
            log.error("发送邮件失败", e);
        }
        return flag;
    }

    public static int sendMessage(String smtpHost,String smtpport,
                                  String from, String password, String to,
                                  String subject, String messageText){
        try {
            //1、配置邮件基本属性
            java.util.Properties props = new java.util.Properties();

            //2、判断是否需要开启SSL
            if(!StringUtils.isEmpty(smtpport) && smtpport.equals("465")){
                MailSSLSocketFactory sf;
                try {
                    sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true);
                    props.put("mail.smtp.ssl.enable", "true");
                    //props.put("mail.smtp.ssl.checkserveridentity", "true");
                    props.put("mail.smtp.ssl.socketFactory", sf);
                } catch (GeneralSecurityException e1) {
                    log.error("开始SSL设置出错", e1);
                }
            }

            props.setProperty("mail.smtp.auth", "true");//指定是否需要SMTP验证
            props.setProperty("mail.smtp.host", smtpHost);//指定SMTP服务器
            props.setProperty("mail.smtp.port", smtpport);//指定SMTP服务器端口
            props.put("mail.transport.protocol", "smtp");
            Session mailSession = Session.getInstance(props);
            mailSession.setDebug(true);//是否在控制台显示debug信息

            //3、构建发送邮件的具体消息体
            log.debug("Constructing message -  from=" + from + "  to=" + to);
            String nick = javax.mail.internet.MimeUtility.encodeText("新联PK大赛");
            //设置发送人
            InternetAddress fromAddress = new InternetAddress(nick+" <"+from+">");
            //设置接收人
            InternetAddress toAddress;
            toAddress = new InternetAddress(to);

            MimeMessage testMessage = new MimeMessage(mailSession);

            try {
                testMessage.setFrom(fromAddress);
            } catch (MessagingException e) {
                log.error("MessagingException");
                return 535;
            }
            try {
                testMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
            } catch (MessagingException e) {
                log.error("MessagingException");
                return 534;
            }
            testMessage.setSentDate(new java.util.Date());
            testMessage.setSubject(MimeUtility.encodeText(subject,"gb2312","B"));
            //设置消息文本
            testMessage.setContent(messageText, "text/html;charset=gb2312");
            System.out.println("Message constructed");
            Transport transport;
            transport = mailSession.getTransport("smtp");

            //设置邮箱服务器     用户名    密码
            transport.connect(smtpHost, from, password);
            transport.sendMessage(testMessage, testMessage.getAllRecipients());

            transport.close();

            return 200;
        }catch(SMTPSendFailedException e){
            log.error("SMTPSendFailedException");
            return 550;
        }catch (MessagingException e) {
            log.error("MessagingException");
            return 500;
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException");
            return 501;//文本消息有错
        }
    }
}
