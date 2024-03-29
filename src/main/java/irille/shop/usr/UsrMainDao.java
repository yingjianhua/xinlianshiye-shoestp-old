package irille.shop.usr;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.platform.usr.View.UsrMainView;
import irille.pub.DateTools;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidRegex;
import irille.pub.validate.ValidRegex2;
import irille.shop.plt.PltArea;
import irille.shop.plt.PltCity;
import irille.shop.plt.PltProvinces;
import irille.view.Page;
import irille.view.usr.UserView;
import lombok.Getter;
import lombok.Setter;

public class UsrMainDao {
	public static final LogMessage LOG = new LogMessage(UsrMain.class);

	/**
	 * 用户注册
	 *
	 * @author chen
	 */
	public static class Ins extends IduIns<Ins, UsrMain> {

		@Override
		public void before() {
			getB().setRegTime(new Date());
			super.before();
		}

		@Override
		public void valid() {
			// super.valid();
		}

		@Override
		public void after() {
			String newPwd = getB().getPkey() + getB().getPassword();
			getB().setPassword(DateTools.getDigest(newPwd));
			getB().upd();
			// 暂时复制用户信息到老表
			if (getB().getIdentity() == 0) {
				UsrPurchase up = new UsrPurchase();
				up.setUserid(getB().getPkey());
				up.setEmail(getB().getEmail());
				up.setPassword(getB().getPassword());
				up.setName(getB().getNickname());
				up.setCountry(getB().getCountry());
				up.setTelphone(getB().getTelphone());
				up.setSex((byte) 0);
				up.setLoginName(getB().getEmail());
				up.setCurrency(1);
				up.setRegTime(getB().getRegTime());
				up.setFacebookUserId(getB().getFacebookUserId());
				up.setGoogleUserId(getB().getGoogleUserId());
				up.setRowVersion((short) 0);
				up.ins();
			}
			super.after();
		}
	}

	/**
	 * 邮箱验证方法
	 *
	 * @param bean
	 */
	private static void toValid(UsrMain bean) {
		ValidRegex2 vr = new ValidRegex2(bean);
		vr.validEmail(UsrMain.T.EMAIL);
	}

	/**
	 * 修改密码
	 *
	 * @author chen
	 */
	public static class updPwd extends IduUpd<updPwd, UsrMain> {
		public String getOldPwd() {
			return oldPwd;
		}

		public void setOldPwd(String oldPwd) {
			this.oldPwd = oldPwd;
		}

		public String getNewPwd() {
			return newPwd;
		}

		public void setNewPwd(String newPwd) {
			this.newPwd = newPwd;
		}

		private String newPwd;
		private String oldPwd;
		private Language language;

		public Language getLanguage() {
			return language;
		}

		public void setLanguage(Language language) {
			this.language = language;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		private String email;

		@Override
		public void before() {
			UsrMain usr = UsrMain.chkUniqueEmail(false, email);
			String mdPwd = DateTools.getDigest((usr.getPkey() + oldPwd));
			usr.setPassword(mdPwd);
			setB(usr);
		}

		@Override
		public void valid() {
			// super.valid();
			if (!ValidRegex.regMarch(Regular.REGULAR_PWD, oldPwd)) {
				throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.password_format, language));
				// throw LOG.errTran("密码为6到20为的数字和字母组成\"", "密码为6到20为的数字和字母组成");
			}
			if (!oldPwd.equals(newPwd)) {
				throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.dif_password, language));
				// throw LOG.errTran("两次密码输入不一致", "两次密码输入不一致");
			}
		}

		@Override
		public void run() {
			UsrMain user = UsrMain.chkUniqueEmail(false, email);
			PropertyUtils.copyProperties(user, getB(), UsrMain.T.PASSWORD);
			user.upd();
		}
	}

	public UserView loginValid(String loginName, String pwd, String thirdName, String thirdId, Language language) {
		if (thirdName != null) {
			if (thirdName == "facebook") {
				// TODO 原来登陆Session逻辑所以第三方登陆暂时搁置
				if (UsrMain.chkUniqueFacebook_user_id(false, thirdId) != null) {
					return null;
				} else {
					return null;
				}
			}
			if (thirdName == "google") {
				if (UsrMain.chkUniqueGoogle_user_id(false, thirdId) != null) {
					return null;
				} else {
					return null;
				}
			}
			if (thirdName == "linkedin") {
				if (UsrMain.chkUniqueLinkedin_user_id(false, thirdId) != null) {
					return null;
				} else {
					return null;
				}
			}
			if (thirdName == "linkedin") {
				if (UsrMain.chkUniqueTwitter_user_id(false, thirdId) != null) {
					return null;
				} else {
					return null;
				}
			}
		}
		if (loginName != null) {
			UsrMain um = UsrMain.chkUniqueEmail(false, loginName);
			if (um == null) {
				throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.service_user_notfound, language));
			}
			String mdPwd = DateTools.getDigest(um.getPkey() + pwd);
			if (!mdPwd.equals(um.getPassword())) {
				throw new WebMessageException(MessageBuild.buildMessage(ReturnCode.wrong_password, language));
			}
			UserView userView = new UserView();
			userView.setLoginName(um.getEmail());
			userView.setPkey(um.getPkey());
			userView.setUser_type(um.getIdentity());
			BeanQuery query = new BeanQuery();
			switch (um.gtIdentity()) {
			case BUYNER:
				userView.setPurchase((UsrPurchase) query.SELECT(UsrPurchase.class).FROM(UsrPurchase.class)
						.WHERE(UsrPurchase.T.UserId, "=?", um.getPkey()).query(UsrPurchase.class));
				return userView;
			case SELLER:
				userView.setSupplier((UsrSupplier) query.SELECT(UsrSupplier.class).FROM(UsrSupplier.class)
						.WHERE(UsrSupplier.T.UserId, "=?", um.getPkey()).query(UsrSupplier.class));
				return userView;
			}
		} else {

		}
		return null;
	}

	/**
	 * @Description: 获取供应商注册信息-卖家
	 *
	 * @author: lingjian @Date: 2019/3/7 10:15
	 */
	public static Page getRegistList(Integer start, Integer limit, String company, String email) {
		SQL sql = new SQL() {
			{
				SELECT(UsrMain.class).FROM(UsrMain.class).WHERE(UsrMain.T.IDENTITY, "=1").ORDER_BY(UsrMain.T.PKEY,"DESC");;
				if (company != null) {
					WHERE(UsrMain.T.COMPANY, "like '%" + company + "%'");
				}
				if (email != null) {
					WHERE(UsrMain.T.EMAIL, "like '%" + email + "%'");
				}
			}
		};
		Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<UsrMainView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new UsrMainView() {
        {
            setPkey((Integer) bean.get(UsrMain.T.PKEY.getFld().getCodeSqlField()));
            setEmail((String) bean.get(UsrMain.T.EMAIL.getFld().getCodeSqlField()));
            setNickName((String) bean.get(UsrMain.T.NICKNAME.getFld().getCodeSqlField()));
            setCompany((String) bean.get(UsrMain.T.COMPANY.getFld().getCodeSqlField()));
            if (bean.get(UsrMain.T.PROVINCE.getFld().getCodeSqlField()) != null) {
              setProvince(
                  Bean.load(
                          PltProvinces.class,
                          (Integer)
                              bean.get(UsrMain.T.PROVINCE.getFld().getCodeSqlField()))
                      .getName());
            }
            if (bean.get(UsrMain.T.CITY.getFld().getCodeSqlField()) != null) {
              setCity(
                  Bean.load(
                          PltCity.class,
                          (Integer) bean.get(UsrMain.T.CITY.getFld().getCodeSqlField()))
                      .getName());
            }
            if (bean.get(UsrMain.T.ZONE.getFld().getCodeSqlField()) != null) {
              setZone(
                  Bean.load(
                          PltArea.class,
                          (Integer) bean.get(UsrMain.T.ZONE.getFld().getCodeSqlField()))
                      .getName());
            }
            setAddress((String) bean.get(UsrMain.T.ADDRESS.getFld().getCodeSqlField()));
            setContacts(
                (String) bean.get(UsrMain.T.CONTACTS.getFld().getCodeSqlField()));
            setTelphone(
                (String) bean.get(UsrMain.T.TELPHONE.getFld().getCodeSqlField()));
            setRegTime((Date) bean.get(UsrMain.T.REG_TIME.getFld().getCodeSqlField()));
          }
        })
            .collect(Collectors.toList());
		return new Page(list, start, limit, count);
	}

	/**
	 * 根据id获取供应商注册信息
	 *
	 * @param pkey
	 * @return
	 * @author: lingjian @Date: 2019/3/7 13:41
	 */
	public static List<UsrMainView> getRegistById(String pkey) {
		SQL sql = new SQL() {
			{
				SELECT(UsrMain.class).FROM(UsrMain.class).WHERE(UsrMain.T.PKEY, "=?", pkey);
			}
		};
		List<UsrMainView> list = Query.sql(sql).queryMaps().stream().map(bean -> new UsrMainView() {
			{
				setPkey((Integer) bean.get(UsrMain.T.PKEY.getFld().getCodeSqlField()));
				setEmail((String) bean.get(UsrMain.T.EMAIL.getFld().getCodeSqlField()));
				setNickName((String) bean.get(UsrMain.T.NICKNAME.getFld().getCodeSqlField()));
				setCompany((String) bean.get(UsrMain.T.COMPANY.getFld().getCodeSqlField()));
				if (bean.get(UsrMain.T.PROVINCE.getFld().getCodeSqlField()) != null) {
					setProvince(Bean
							.load(PltProvinces.class, (Integer) bean.get(UsrMain.T.PROVINCE.getFld().getCodeSqlField()))
							.getName());
				}
				if (bean.get(UsrMain.T.CITY.getFld().getCodeSqlField()) != null) {
					setCity(Bean.load(PltCity.class, (Integer) bean.get(UsrMain.T.CITY.getFld().getCodeSqlField()))
							.getName());
				}
				if (bean.get(UsrMain.T.ZONE.getFld().getCodeSqlField()) != null) {
					setZone(Bean.load(PltArea.class, (Integer) bean.get(UsrMain.T.ZONE.getFld().getCodeSqlField()))
							.getName());
				}
				setAddress((String) bean.get(UsrMain.T.ADDRESS.getFld().getCodeSqlField()));
				String name= (String) bean.get(UsrMain.T.CONTACTS.getFld().getCodeSqlField());
				if(name != null){
					String [] strArray = name.split(",");
					String contact="";
					for(String s : strArray){
						contact+=s;
					}
					setContacts(contact);
				}
				setTelphone((String) bean.get(UsrMain.T.TELPHONE.getFld().getCodeSqlField()));
				setRegTime((Date) bean.get(UsrMain.T.REG_TIME.getFld().getCodeSqlField()));
			}
		}).collect(Collectors.toList());
		return list;
	}
	/**
	 * 验证手机唯一性
	 * @author chen
	 */
	public boolean validOnePhone(String phone,byte identity) {
		System.out.println(phone);
		String sql = "SELECT * FROM usr_main WHERE telphone=\""+phone+"\" AND IDENTITY="+identity+"";
		Integer count=Query.sql(sql).queryCount();
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 商家修改邮箱验证密码是否正确
	 * @author chen
	 */
  public boolean validPwd(String email,String pwd){
  	UsrMain main=UsrMain.chkUniqueEmail(false,email);
  	if(main==null){
		throw LOG.err("Invalid email", "用户不存在");
	}
	String mdPwd=DateTools.getDigest(main.getPkey() + pwd);
  	if (mdPwd.equals(main.getPassword())){
		System.out.println("密码正确");
  		return true;
	}else{
  		return false;
	}
  }
	/**
	 * 商家修改邮箱
	 * @author chen
	 */
	public static class updEmail  extends IduUpd<updEmail, UsrMain>{
		@Getter
		@Setter
		String email;
		@Getter
		@Setter
		String emailA;
		@Override
		public void before() {
			UsrMain main=UsrMain.chkUniqueEmail(false,getEmail());
			main.setEmail(emailA);
			setB(main);
		}

		@Override
		public void valid() {

		}

		@Override
		public void run() {
			super.run();
		}

		@Override
		public void after() {
			UsrSupplier sp=UsrSupplier.chkUniqueLogin_name(false,getB().getEmail());
			if(sp == null){
				String sql = "UPDATE usr_supplier SET login_name =\""+getB().getEmail()+"\" WHERE userid ="+getB().getPkey()+"";
				Query.sql(sql).executeUpdate();
			}else{
				throw LOG.err("Registed  Email", "邮箱已注册");
			}

		}
	}

}
