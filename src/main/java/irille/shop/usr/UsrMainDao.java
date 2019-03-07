package irille.shop.usr;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import irille.pub.DateTools;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.validate.ValidRegex;

public class UsrMainDao {
    public static final LogMessage LOG = new LogMessage(UsrMain.class);
    
    
    /**
     * 用户注册
     * @author chen
     */
    public static class Ins extends IduIns<Ins, UsrMain>{
    	
    	private String pwd;
    	private String pwdA;
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

    	
    	@Override
    	public void before() {
    		getB().setRegTime(new Date());
    		super.before();
    	}
    	
    	@Override
    	public void valid() {
    		super.valid();
    	   UsrValid valid = new UsrValid(getB());
    	   UsrMain main = UsrMain.chkUniqueEmail(false, getB().getEmail());
           valid.validCopy(main);
           valid.validWrongPassword(pwd, pwdA);
           toValid(getB());
    	}
    	
    	@Override
    	public void after() {
    		String newPwd = getB().getPkey() + pwd;
            getB().setPassword(DateTools.getDigest(newPwd));
            getB().upd();
            
            //暂时复制用户信息到老表
            if(getB().getIdentity()==0) {
            	 UsrPurchase up=new UsrPurchase();
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
            	 up.ins();
            }
    		super.after();
    	}
    }
    /**
     * 邮箱验证方法
     * @param bean
     */
    private static void toValid(UsrMain bean) {
        ValidRegex vr = new ValidRegex(bean);
        vr.validEmail(UsrMain.T.EMAIL);
    }
    /**
     * 修改密码
     * @author chen
     */
    public static class updPwd extends IduUpd<updPwd, UsrMain>{
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
		private String  newPwd;
		private String  oldPwd;
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		private String  email;
		
		@Override
        public void before() {
			UsrMain usr=UsrMain.chkUniqueEmail(false, email);
			String mdPwd=DateTools.getDigest((usr.getPkey() + oldPwd));
			usr.setPassword(mdPwd);
			setB(usr);
       }
		@Override
		public void valid() {
			super.valid();
			 Pattern Password_Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,20})$");
		        Matcher matcher = Password_Pattern.matcher(oldPwd);
		        if (!matcher.matches()) {
		        	throw LOG.errTran("密码为6到20为的数字和字母组成\"","密码为6到20为的数字和字母组成");
		        }
		        System.out.println("..."+oldPwd);
		        System.out.println("..."+newPwd);
		        System.out.println("..."+oldPwd.equals(newPwd));
			if(!oldPwd.equals(newPwd)) {
				
                throw LOG.errTran("两次密码输入不一致","两次密码输入不一致");

			}
		}
		@Override
		public void run() {
			UsrMain user=UsrMain.chkUniqueEmail(false, email);
			PropertyUtils.copyProperties(user, getB(), UsrMain.T.PASSWORD);
			user.upd();
		}
	
    }
    public Boolean loginValid(String loginName,String pwd,String thirdName,String thirdId) {
    	if(thirdName!=null) {
    		if(thirdName=="facebook") {
    			if(UsrMain.chkUniqueFacebook_user_id(false, thirdId)!=null) {
    				return true;
    			}else {
    				return false;
    			}
    		}
    		if(thirdName=="google") {
    			if(UsrMain.chkUniqueGoogle_user_id(false, thirdId)!=null) {
    				return true;
    			}else {
    				return false;
    			}
    		}
    		if(thirdName=="linkedin") {
    			if(UsrMain.chkUniqueLinkedin_user_id(false, thirdId)!=null) {
    				return true;
    			}else {
    				return false;
    			}
    		}
    		if(thirdName=="linkedin") {
    			if(UsrMain.chkUniqueTwitter_user_id(false, thirdId)!=null) {
    				return true;
    			}else {
    				return false;
    			}
    		}
    	}
    	if(loginName!=null) {
    		UsrMain um=UsrMain.chkUniqueEmail(false, loginName);
    		if(um==null)
    	   	throw LOG.err("loginName not exists", "用户不存在");
    		String mdPwd=DateTools.getDigest(um.getPkey()+pwd);
        	if(!mdPwd.equals(um.getPassword()))
        	throw LOG.err("wrong password", "用户名和密码不匹配");	
    	}
    	
    	return true;
    }
    public static void main(String[] args) {
		String a="55zz";
		String b="55zz";
		System.out.println(a==b);
		
	}

}
