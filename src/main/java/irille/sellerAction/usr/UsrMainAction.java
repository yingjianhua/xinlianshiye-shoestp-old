package irille.sellerAction.usr;
import com.github.benmanes.caffeine.cache.Cache;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;
import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.Str;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.AppConfig;
import irille.pub.util.CacheUtils;
import irille.pub.util.HttpUtils;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidRegex;
import irille.sellerAction.view.timeZone;
import irille.shop.usr.UsrMain;
import irille.sellerAction.SellerAction;
import irille.shop.usr.UsrMainDao;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UsrMainAction extends  SellerAction<UsrMain> {
    private static LogMessage LOG = new LogMessage(UsrMainAction.class);


    @Getter @Setter private String email;
    @Getter @Setter private String pwd;
    @Getter @Setter private String code;
    @Getter @Setter private String newEmail;
    @Getter @Setter private String sureEmail;
    @Getter @Setter private String fromCode;
    @Getter @Setter private String toCode;
    @Getter @Setter private String money;
    @Getter @Setter private String zoneId;
    @Inject private Mailer mailer;
    @Inject private UsrMainDao usrMainDao;
    @Inject private UsrMainDao.updEmail updEmail;
    private static Map<String, String> mailTemplate;
    private static final Logger logger = LoggerFactory.getLogger(irille.homeAction.usr.UsrMainAction.class);
    static {
        mailTemplate = new HashMap<>();
        List<String> list = Arrays.asList("checkEmail", "forgetPassWord");
        for (String fileName : list) {
            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    irille.homeAction.usr.UsrMainAction.class.getResourceAsStream("/mailPage/" + fileName + ".html")));
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
     * 发送邮件
     *
     * @author chen
     */
    public void sendEm() throws GeneralSecurityException, IOException, JSONException {
        if (getEmail() == null) {
            throw LOG.err("Invalid Email", "邮箱不存在");
        }
        //  忽略邮箱大小写
        setEmail(getEmail().toLowerCase());
        if (!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getEmail())) {
            throw LOG.err("Invalid Format Email", "邮箱格式不正确");
        }
        UsrMain main = UsrMain.chkUniqueEmail(false, getEmail());

        Cache cachec = CacheUtils.sendEm;
        String valuec = String.valueOf(cachec.getIfPresent(getEmail()));
        if (valuec != null && !valuec.equals("null")) {
            Long time = Long.parseLong(valuec);
            long now = new Date().getTime();
            int interval = (int) ((now - time) / 1000);
            if (interval < 60) {
                throw LOG.err("Send Ofen,wait minute", "邮箱发送频繁，请稍后再试");
            }
        }
        Integer intCode=(int)((Math.random()*9+1)*100000);
        String code=intCode.toString();
        CacheUtils.sixtyValidCode.put(code, getEmail());
        CacheUtils.sendEm.put(getEmail(), new Date().getTime());
        mailer.sendMail(
                EmailBuilder.startingBlank()
                        .withSubject("Shoestp Change Your EmailAddress")
                        .prependTextHTML(
                                mailTemplate
                                        .get("forgetPassWord")
                                        .replaceAll("\\{\\{CODE}}", String.valueOf(code)))
                        .from("Shoestp", "notice@service.shoestp.com")
                        .to(getEmail())
                        .buildEmail(),
                true);
        write();
    }
    /**
     * 提交验证码(重置密码)
     *
     * @author chen
     * @throws IOException
     */
    public void subValid() throws IOException {
        if (getEmail() == null) {
            throw LOG.err("Invalid Email", "邮箱不能为空");
        }
        //  忽略邮箱大小写
        setEmail(getEmail().toLowerCase());
        if (!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getEmail())) {
            throw LOG.err("Invalid Format Email", "邮箱格式不正确");
        }
        if (Str.isEmpty(getPwd())) {
            throw LOG.err("Invalid password", "密码不能为空");
        }
        if(!usrMainDao.validPwd(getEmail(),getPwd())){
            throw LOG.err("Wrong password", "密码不正确");
        }
        Cache cache = CacheUtils.sixtyValidCode;
        String value = String.valueOf(cache.getIfPresent(getCode()));
        System.out.println("...验证码"+value);
        if (cache.getIfPresent(getCode()) != null
                && value.equalsIgnoreCase(getEmail())) {
            write();
        } else {
            throw LOG.err("Invalid code", "验证码失效或未发送");
        }
    }
    /**修改邮箱
     * @author chen
     */
    public void updEmail() throws Exception {
        if (getEmail() == null) {
            throw LOG.err("Invalid Email", "原邮箱不能为空");
        }
        setEmail(getEmail().toLowerCase());
        if (!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getEmail())) {
            throw LOG.err("Invalid Format Email", "原邮箱格式不正确");
        }
        if (Str.isEmpty(getNewEmail()) ||Str.isEmpty(getSureEmail())) {
            throw LOG.err("Invalid Email", "新邮箱不能为空");
        }
        setNewEmail(getNewEmail().toLowerCase());
        if (!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getNewEmail())||!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getSureEmail())) {
            throw LOG.err("Invalid Format Email", "新邮箱格式不正确");
        }
        if(!getNewEmail().equals(getSureEmail())){
            throw LOG.err("Invalid  Email", "两次邮箱不一致");
        }
        UsrMain main=UsrMain.chkUniqueEmail(false,getNewEmail());
        if(main!=null){
            throw LOG.err("Registed  Email", "邮箱已注册");
        }
        updEmail.setEmail(getEmail());
        updEmail.setEmailA(getNewEmail());
        updEmail.commit();
        CacheUtils.sixtyValidCode.invalidate(code);
        write();
    }
    public  void queryRate() {
        String host = "https://ali-waihui.showapi.com";
        String path = "/waihui-transform";
        String method = "GET";
        String appcode = "f94da6a10b084e71ac4bf69405e21af5";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("fromCode", fromCode);
        querys.put("money", money);
        querys.put("toCode", toCode);


        try {

            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            JSONObject jsonA = new JSONObject(EntityUtils.toString(response.getEntity()));
            System.out.println(jsonA.getString("showapi_res_body"));
            JSONObject jsonB = new JSONObject(jsonA.getString("showapi_res_body"));
            JSONObject json = new JSONObject();
            json.put("ret", 1);
            json.put("money", jsonB.getString("money"));
            writerOrExport(json);
        } catch (Exception e) {
            throw LOG.err("NetWork error", "网络错误");
        }
    }
    public void getTimeZone() throws IOException{
        String [] zonnsId = TimeZone.getAvailableIDs();
        List<timeZone> zones=new ArrayList<timeZone>();
        for(String str : zonnsId){
            timeZone zt=new timeZone();
            zt.setId(str);
            zt.setName(str);
            zones.add(zt);
        }
       write(zones);

    }
    public void getTimeDifferent() throws Exception {
        if(Str.isEmpty(getZoneId())){
            throw LOG.err("Please select time zone", "请选择时区");
        }
        Date date= new Date();
        SimpleDateFormat londonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        londonSdf.setTimeZone(TimeZone.getTimeZone(getZoneId()));
        londonSdf.format(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateA = formatter.parse(londonSdf.format(date).toString());
        Calendar c1 = Calendar.getInstance();   //当前日期
        Calendar c2 = Calendar.getInstance();
        c2.setTime(dateA);
        int nowHour=c1.get(Calendar.HOUR);
        int selectHour=c2.get(Calendar.HOUR);
        int dif=selectHour-nowHour;
        JSONObject json = new JSONObject();
        json.put("ret", 1);
        json.put("different", dif);
        writerOrExport(json);
    }
}

