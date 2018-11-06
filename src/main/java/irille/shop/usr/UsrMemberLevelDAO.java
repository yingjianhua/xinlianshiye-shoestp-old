package irille.shop.usr;

import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidNumber;

public class UsrMemberLevelDAO {
	public static final Log LOG = new Log(UsrMemberLevel.class);
	
	public static class Ins extends IduIns<Ins , UsrMemberLevel>{
		@Override
		public void before() {
			super.before();
			getB().setUpdatedBy(getUser().getPkey());
			getB().setUpdatedTime(Env.getSystemTime());
			getB().setCreatedBy(getUser().getPkey());
			getB().setCreatedTime(Env.getSystemTime());
		}
		
		@Override
		public void valid() {
			super.valid();
			toValid(getB());
			
		}
	}
	
	public static class Upd extends IduUpd<Upd,UsrMemberLevel>{
		@Override
		public void before() {
			super.before();
			getB().setUpdatedBy(getUser().getPkey());
			getB().setUpdatedTime(Env.getSystemTime());
			UsrMemberLevel model = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(model, getB(),UsrMemberLevel.T.CREATED_BY,UsrMemberLevel.T.CREATED_TIME);
			setB(model);
		}
		
		@Override
		public void valid() {
			super.valid();
			toValid(getB());
		}
	}
	
	private static void toValid(UsrMemberLevel bean){
		new ValidForm(bean).validNotEmpty(UsrMemberLevel.T.NAME);
		new ValidNumber(bean).validBigDecimalRange(BigDecimal.ZERO, BigDecimal.valueOf(100), UsrMemberLevel.T.DISCOUNT);
	}
	
}
