package irille.homeAction.usr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

import javax.inject.Inject;

import org.json.JSONException;
import org.json.JSONObject;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import com.github.benmanes.caffeine.cache.Cache;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.Str;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.AppConfig;
import irille.pub.util.CacheUtils;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidRegex;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrMainDao;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户Action
 *
 * @author chen
 */
public class UsrMainAction extends HomeAction<UsrMain> {

	private static LogMessage LOG = new LogMessage(UsrMainAction.class);

	@Inject
	private UsrMainDao.Ins ins;
	@Inject
	private UsrMainDao.updPwd updPwd;
	@Inject
	private UsrMainDao usrMainDao;

	@Getter
	@Setter
	private String checkCode;
	@Getter
	@Setter
	private String pwd;
	@Getter
	@Setter
	private String pwdA;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private Integer type;
	@Getter
	@Setter
	private String firstName;
	@Getter
	@Setter
	private String lastName;
	@Getter
	@Setter
	private String telPre;
	@Getter
	@Setter
	private String telMid;
	@Getter
	@Setter
	private String telAft;
	@Getter
	@Setter
	private String loginName;
	@Setter
	@Getter
	private String code;

	@Getter
	@Setter
	private String thirdName;
	@Getter
	@Setter
	private String thirdId;
	@Setter
	@Getter
	private String uid;
	@Inject
	private Mailer mailer;
	private static Map<String, String> mailTemplate;

	static {
		mailTemplate = new HashMap<>();
		List<String> list = Arrays.asList("checkEmail", "forgetPassWord");
		for (String fileName : list) {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(UsrMainAction.class.getResourceAsStream("/mailPage/" + fileName + ".html")));
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
					MessageBuild.buildMessage(ReturnCode.service_verification_code, HomeAction.curLanguage()));
		}
		if (Str.isEmpty(getPwd()) || Str.isEmpty(getPwdA()))
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_pwd_notnull, HomeAction.curLanguage()));
		if (uid == null || CacheUtils.mailValid.getIfPresent(uid) == null
				|| !CacheUtils.mailValid.getIfPresent(uid).equals(getBean().getEmail())) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.service_Invalid_UID, HomeAction.curLanguage()));
		}
		insBefore();

		if (getBean().getEmail() == null) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_mail_notnull, HomeAction.curLanguage()));
		}
		if (!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getBean().getEmail())) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_mailRegex, HomeAction.curLanguage()));
		}
		if (!ValidRegex.regMarch(Regular.REGULAR_PWD, pwd)) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.password_format, HomeAction.curLanguage()));
		}
		if (!pwd.equals(pwdA)) {
			throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.dif_password, HomeAction.curLanguage()));
		}
		if (getBean().getCompany() != null) {
			if (!ValidRegex.regMarch(Regular.REGULAR_COMPANY, getBean().getCompany())) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.valid_companyNameRegex, HomeAction.curLanguage()));
			}
		}
		if (getBean().getIdentity() == 1) {
			if (!Str.isEmpty(getFirstName()) && !Str.isEmpty(getLastName())) {
				String name = getFirstName() + "," + getLastName();
				if (!ValidRegex.regMarch(Regular.REGULAR_NAME, getFirstName().trim() + getLastName().trim())) {
					throw new WebMessageException(
							MessageBuild.buildMessage(ReturnCode.valid_nameRegex, HomeAction.curLanguage()));
				}
				getBean().setContacts(name);
			} else {
				getBean().setContacts(null);
			}
		} else {
			if (!ValidRegex.regMarch(Regular.REGULAR_NAME, getBean().getNickname())) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.valid_nameRegex, HomeAction.curLanguage()));
			}
		}
		if (getBean().getIdentity() == 0) {
			if (getTelPre() == null || getTelMid() == null || getTelAft() == null) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.valid_phone_notnull, HomeAction.curLanguage()));
			}
			String phone = getTelPre() + "-" + getTelMid() + getTelAft();
			if (!ValidRegex.regMarch(Regular.REGULAR_TEL, phone)) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.valid_phoneRegex, HomeAction.curLanguage()));
			}
			String tel = getTelPre() + "-" + getTelMid() + "-" + getTelAft();
			getBean().setTelphone(tel);
		} else {
			if (getBean().getTelphone() == null) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.valid_phone_notnull, HomeAction.curLanguage()));
			}
			if (!ValidRegex.regMarch(Regular.REGULAR_CHINATEL, getBean().getTelphone())) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.valid_phoneRegex, HomeAction.curLanguage()));
			}
		}
		UsrMain main = UsrMain.chkUniqueEmail(false, getBean().getEmail());
		if (main != null) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.service_user_exists, HomeAction.curLanguage()));
		}
		getBean().setEmail(getBean().getEmail().toLowerCase());
		getBean().setPassword(getPwd());
		ins.setB(getBean());
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
		if (getEmail() == null) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_mail_notnull, HomeAction.curLanguage()));
		}
		if (!ValidRegex.regMarch(Regular.REGULAR_EMAIL, getEmail())) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_mailRegex, HomeAction.curLanguage()));
		}
		UsrMain main = UsrMain.chkUniqueEmail(false, getEmail());
		String title = "";
		if (type == 0) {
			String valide = verifyCode();
			if (Str.isEmpty(valide) || Str.isEmpty(getCheckCode()) || valide.equals(getCheckCode()) == false) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.service_verification_code, HomeAction.curLanguage()));
			}

			title = "User registration";
			if (main != null) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.service_user_exists, HomeAction.curLanguage()));
			}
		}
		if (type == 1) {
			String valide = verifyCode();
			if (Str.isEmpty(valide) || Str.isEmpty(getCheckCode()) || valide.equals(getCheckCode()) == false) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.service_verification_code, HomeAction.curLanguage()));
			}
			title = "Forgot password";
			if (main == null) {
				throw new WebMessageException(
						MessageBuild.buildMessage(ReturnCode.service_user_notfound, HomeAction.curLanguage()));
			}
			write();
		}

		String uid = UUID.randomUUID().toString();

		// Integer code = (int) ((Math.random() * 9 + 1) * 100000);

		switch (type) {
		case 0:
			Cache cache = CacheUtils.sendEm;
			String value = String.valueOf(cache.getIfPresent(getEmail()));
			if (value != null && !value.equals("null")) {
				Long time = Long.parseLong(value);
				long now = new Date().getTime();
				int interval = (int) ((now - time) / 1000);
				if (interval < 60) {
					throw new WebMessageException(
							MessageBuild.buildMessage(ReturnCode.send_ofen, HomeAction.curLanguage()));
				}
			}

			CacheUtils.mailValid.put(uid, getEmail());
			CacheUtils.sendEm.put(getEmail(), new Date().getTime());
			String mesg = AppConfig.domain + "home/usr_UsrMain_completeReg?uid=" + uid + "&email=" + email;
			mailer.sendMail(EmailBuilder.startingBlank().withSubject("Shoestp User Registration")
					.prependTextHTML(mailTemplate.get("checkEmail").replaceAll("\\{\\{url}}", mesg))
					.from("Shoestp", "notice@service.shoestp.com").to(getEmail()).buildEmail(), true);
			write();
			return;
		case 2:
			Cache cachec = CacheUtils.sendEm;
			String valuec = String.valueOf(cachec.getIfPresent(getEmail()));
			if (valuec != null && !valuec.equals("null")) {
				Long time = Long.parseLong(valuec);
				long now = new Date().getTime();
				int interval = (int) ((now - time) / 1000);
				if (interval < 60) {
					throw new WebMessageException(
							MessageBuild.buildMessage(ReturnCode.send_ofen, HomeAction.curLanguage()));
				}
			}
			SecureRandom secureRandom = new SecureRandom();
			Integer code = secureRandom.nextInt(999999);
			CacheUtils.pwdValid.put(code, getEmail());
			CacheUtils.sendEm.put(getEmail(), new Date().getTime());
			mailer.sendMail(EmailBuilder.startingBlank().withSubject("Shoestp Rest Your Password")
					.prependTextHTML(
							mailTemplate.get("forgetPassWord").replaceAll("\\{\\{CODE}}", String.valueOf(code)))
					.from("Shoestp", "notice@service.shoestp.com").to(getEmail()).buildEmail(), true);
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
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.service_Invalid_UID, HomeAction.curLanguage()));
		}
		if (CacheUtils.pwdValid.getIfPresent(Integer.valueOf(code)) == null
				|| !CacheUtils.pwdValid.getIfPresent(Integer.valueOf(code)).equals(getEmail())) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.service_Invalid_email, HomeAction.curLanguage()));
		}
		if (getEmail() == null) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_mail_notnull, HomeAction.curLanguage()));
		}
		if (pwd == null) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_pwd_notnull, HomeAction.curLanguage()));
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
		if (Str.isEmpty(loginName)) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_mail_notnull, HomeAction.curLanguage()));
		}
		if (Str.isEmail(pwd)) {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.valid_pwd_notnull, HomeAction.curLanguage()));
		}
		UserView userView = usrMainDao.loginValid(loginName, pwd, thirdName, thirdId, HomeAction.curLanguage());
		if (userView != null) {
			setUser(userView);
			JSONObject json = new JSONObject();
			json.put("ret", 1);
			json.put("sign", true);
			json.put("jumpUrl", getParams("jumpUrl"));
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
			if (cache.getIfPresent(getUid()) != null && value.equals(getEmail())) {
				setResult("/home/v3/jsp/reg/register-step2.jsp");
			} else {
				setResult("/home/v3/jsp/reg/register-error.jsp");
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
		if (cache.getIfPresent(Integer.parseInt(getCode())) != null && value.equals(getEmail())) {
			write();
		} else {
			throw new WebMessageException(
					MessageBuild.buildMessage(ReturnCode.service_Invalid_UID, HomeAction.curLanguage()));
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

	@Getter
	@Setter
	private String id;

	/**
	 * 根据id获取供应商注册信息
	 *
	 * @throws IOException
	 */
	public void getRegistById() throws IOException {
		write(UsrMainDao.getRegistById(id));
	}
}
