package irille.shop.usr;

import irille.core.sys.Sys.OSex;
import irille.homeAction.HomeAction;
import irille.pub.DateTools;
import irille.pub.Log;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpdLines;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidRegex;
import irille.shop.plt.PltErateDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsrPurchaseDAO {
    public static final LogMessage LOG = new LogMessage(UsrPurchase.class);
    
    public static class Query extends IduOther<Query, UsrPurchase> {
        public UsrPurchase signIn(String email, String password) {
            UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, email);
            UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, email);
            if (purchase == null) return null;
            if (purchase.getPassword().equals(DateTools.getDigest(purchase.getPkey() + password))) return purchase;
            return null;
        }
    }
    public static void main(String[] args) {
		System.out.println(DateTools.getDigest("1123456"));
	}

    /**
     * 采购商注册
     *
     * @author liyichao
     */
    public static class Ins extends IduIns<Ins, UsrPurchase> {
        private String password;
        private String copyPassword;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCopyPassword() {
            return copyPassword;
        }

        public void setCopyPassword(String copyPassword) {
            this.copyPassword = copyPassword;
        }

        @Override
        public void before() {
            getB().setRegTime(new Date());
            getB().setLoginName(getB().getEmail());
            getB().stCurrency(PltErateDAO.Query.siteDefCurrency());
            getB().setSex(OSex.DEFAULT.getLine().getKey());
        }

        @Override
        public void valid() {
            super.valid();
            UsrValid valid = new UsrValid(getB());
            UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, getB().getLoginName());
            valid.validCopy(purchase);
            valid.validWrongPassword(password, copyPassword);
            toValid(getB());
        }

        @Override
        public void after() {
            String newPwd = getB().getPkey() + password;
            getB().setPassword(DateTools.getDigest(newPwd));
            getB().upd();
            super.after();
        }
    }

    public static class Upd extends IduUpdLines<Upd, UsrPurchase, UsrPurchaseLine> {
        @Override
        public void before() {
            UsrPurchase model = loadThisBeanAndLock();
            getB().setPassword(model.getPassword());
            PropertyUtils.copyPropertiesWithout(model, getB(), UsrPurchase.T.REG_TIME, UsrPurchase.T.REG_IP, UsrPurchase.T.PASSWORD);
            updLine(getB(), getLines(), UsrPurchaseLine.T.PURCHASE.getFld());
            super.after();
        }
    }

    public static class Del extends IduDel<Del, UsrPurchase> {
        @Override
        public void before() {
            super.before();
            delLine(getLines(UsrPurchaseLine.T.PURCHASE, getB().getPkey()));
        }
    }

    /**
     * 采购商忘记密码
     *
     * @author liyichao
     */
    public static class Uda extends IduOther<Uda, UsrPurchase> {
        private String password;
        private String copyPassword;

        public String getCopyPassword() {
            return copyPassword;
        }

        public void setCopyPassword(String copyPassword) {
            this.copyPassword = copyPassword;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        UsrValid valid = new UsrValid(getB());

        @Override
        public void valid() {
            super.valid();
            valid.validWrongPassword(password, copyPassword);
        }

        @Override
        public void run() {
            super.run();
            String newPwd = getB().getPkey() + password;
            getB().setPassword(DateTools.getDigest(newPwd));
            String loginName = getB().getLoginName();
            UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, loginName);
	   	     if(supplier == null) {
	   	        getB().upd();
	  	     }else {
		   		supplier.setPassword(DateTools.getDigest(newPwd));
		   		supplier.upd();
		   		getB().upd();
	   	     }
	        }
    }

    /**
     * 采购商成为供应商
     *
     * @author liyichao
     */
    public static class Bcme extends IduOther<Bcme, UsrPurchase> {
        private UsrSupplier supplier;
        private String copyPassword;

        public String getCopyPassword() {
            return copyPassword;
        }

        public void setCopyPassword(String copyPassword) {
            this.copyPassword = copyPassword;
        }

        public UsrSupplier getSupplier() {
            return supplier;
        }

        public void setSupplier(UsrSupplier supplier) {
            this.supplier = supplier;
        }

        @Override
        public void valid() {
            UsrSupplier supplier = UsrSupplier.chkUniqueLogin_name(false, getB().getLoginName());
            if (supplier != null) {
                throw LOG.err(Usr.ErrMsgs.copyErr, UsrSupplier.T.LOGIN_NAME.getFld().getName());
            }
            UsrValid valid = new UsrValid(getB());
            valid.validCopy(supplier);
        }

        @Override
        public void run() {
            supplier.setName(getB().getName());
            supplier.setLoginName(getB().getLoginName());
            supplier.setStatus((byte) 0);
            supplier.setEmail(getB().getEmail());
            supplier.setRole(3);//供应商角色默认为最低等级

            UsrSupplierDAO.Ins supplierIns = new UsrSupplierDAO.Ins();
            supplierIns.setB(supplier);
            supplierIns._mm = supplier.getPassword();
            supplierIns._mmcheck = copyPassword;
            supplierIns.commit();
        }
    }

    private static void toValid(UsrPurchase bean) {
        ValidRegex vr = new ValidRegex(bean);
        vr.validEmail(UsrPurchase.T.EMAIL);
    }


    /**
     * 更新采购商账户信息
     */
    public static class updPurchaseInf extends IduOther<updPurchaseInf, UsrPurchase> {
        

        @Override
        public void before() {
            UsrPurchase usrPurchase = loadThisBeanAndLock();
            PropertyUtils.copyProperties(usrPurchase, getB(), UsrPurchase.T.NAME, UsrPurchase.T.SURNAME, UsrPurchase.T.TELPHONE, UsrPurchase.T.COMPANY, UsrPurchase.T.ADDRESS,UsrPurchase.T.SEX);
            usrPurchase.upd();
        }
    }

    /**
     * 采购商修改密码
     */
    public static class updPwd extends IduOther<updPwd, UsrPurchase> {
        private String oldPwd;
        private String newPwd;
        private String updResult;

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
        
        public String getUpdResult() {
			return updResult;
		}

		public void setUpdResult(String updResult) {
			this.updResult = updResult;
		}

		@Override
        public void before() {
            String checkedPwd = DateTools.getDigest((HomeAction.getPurchase().getPkey() + oldPwd).toLowerCase());
            String newMd5Pwd = DateTools.getDigest((HomeAction.getPurchase().getPkey() + newPwd).toLowerCase());
            UsrPurchase purchase = loadThisBeanAndLock();
            if (checkedPwd.equals(HomeAction.getPurchase().getPassword())) {
                getB().setPkey(getB().getPkey());
                getB().setPassword(newMd5Pwd);
                PropertyUtils.copyProperties(purchase, getB(), UsrPurchase.T.PASSWORD);
                purchase.upd();
                setUpdResult("success");
            } else {
            	setUpdResult("原密码错误");
                throw LOG.errTran("signIn%original_password_wrong","原密码错误");
            }

        }

    }
    
    

    /**
     * @author lijie@shoestp.cn
     * @Description: 采购商 用户信息
     * @date 2018/7/31 16:28
     */
    public static class pageselect extends IduOther<pageselect, UsrPurchase> {

        public static final boolean Debug = false;

        public Map getAccountInfo(int id) {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    UsrPurchase.T.EMAIL,
                    UsrPurchaseLine.T.REGION,
                    UsrPurchaseLine.T.CITY,
                    UsrPurchaseLine.T.ADDRESS
            ).from(UsrPurchase.T.EMAIL)
                    .leftjoin(UsrPurchaseLine.T.PURCHASE, UsrPurchaseLine.T.PURCHASE, UsrPurchase.T.PKEY)
                    .eq(UsrPurchase.T.PKEY)
                    .desc(UsrPurchaseLine.T.ISDEFAULT).page(0, 1);
            List parmdList = new ArrayList();
            parmdList.add(id);
            Map result = new HashMap();
            try {
                result = sql.castMap(BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms(parmdList)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

    }
    /**
     * 
     * @author admin
     *@author zhengjianli
     *@date 2018/8/17
     *@Description 根据密码是否正确来判断是否修改邮箱
     */
    public static class upEmail extends IduOther<upEmail, UsrPurchase>{
    	private String Pwd;
    	private String newEmail;
    	public String getNewEmail() {
			return newEmail;
		}
		public void setNewEmail(String newEmail) {
			this.newEmail = newEmail;
		}
		public String getPwd() {
			return Pwd;
		}
		public void setPwd(String pwd) {
			Pwd = pwd;
		}
		public void before() {
    		String checkedPwd = DateTools.getDigest((HomeAction.getPurchase().getPkey() + Pwd).toLowerCase());
    		UsrPurchase purchase = loadThisBeanAndLock();
    		if (checkedPwd.equals(HomeAction.getPurchase().getPassword())) {
    			getB().setPkey(getB().getPkey());
    			getB().setEmail(newEmail);
    			PropertyUtils.copyProperties(purchase, getB(), UsrPurchase.T.EMAIL);
                purchase.upd();
    		}else {
                throw LOG.errTran("signIn%original_password_wrong","原密码错误");
            }
    	}
    }
    
    /**
     * 修改FaceBookId
     * @author GS
     *
     */
    public static class upFacebook extends IduOther<upFacebook, UsrPurchase>{
		public void before() {
    		UsrPurchase purchase = loadThisBeanAndLock();
    			getB().setPkey(getB().getPkey());
    			PropertyUtils.copyProperties(purchase, getB(), UsrPurchase.T.FACEBOOK_USER_ID);
                purchase.upd();
    	}
    }
    /**
     * 修改GoogleId
     * @author GS
     *
     */
    public static class upGoogleBook extends IduOther<upFacebook, UsrPurchase>{
		public void before() {
    		UsrPurchase purchase = loadThisBeanAndLock();
    			getB().setPkey(getB().getPkey());
    			PropertyUtils.copyProperties(purchase, getB(), UsrPurchase.T.GOOGLE_USER_ID);
                purchase.upd();
    	}
    }


}
