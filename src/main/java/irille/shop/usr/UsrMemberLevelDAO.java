package irille.shop.usr;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import irille.core.sys.SysUser;
import irille.platform.usr.View.UsrmemberlistView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
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
			getB().setUpdatedTime(Env.getSystemTime());
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
			getB().setUpdatedTime(Env.getSystemTime());
			UsrMemberLevel model = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(model, getB(),UsrMemberLevel.T.CREATED_BY,UsrMemberLevel.T.CREATED_TIME);
			setB(model);
		}
		
		@Override
		public void valid() {
			toValid(getB());
		}
	}
	public static  class  delnumber extends IduDel<delnumber,UsrMemberLevel>{
		@Override
		public void before() {
		}

		@Override
		public void valid() {
		}
	}
	
	private static void toValid(UsrMemberLevel bean){
		new ValidForm(bean).validNotEmpty(UsrMemberLevel.T.NAME);
		new ValidNumber(bean).validBigDecimalRange(BigDecimal.ZERO, BigDecimal.valueOf(100), UsrMemberLevel.T.DISCOUNT);
	}
	public static List<UsrmemberlistView> listnumer(){
		SQL sql=new SQL(){{
			SELECT(UsrMemberLevel.class).FROM(UsrMemberLevel.class);
		}};
		List<UsrmemberlistView> list= Query.sql(sql).queryMaps().stream().map(bean->new UsrmemberlistView(){{
			setId((Integer) bean.get(UsrMemberLevel.T.PKEY.getFld().getCodeSqlField()));
			setName((String) bean.get(UsrMemberLevel.T.NAME.getFld().getCodeSqlField()));
			setIcon((String) bean.get(UsrMemberLevel.T.ICON.getFld().getCodeSqlField()));
			if((byte)bean.get(UsrMemberLevel.T.ENABLED.getFld().getCodeSqlField())==1){
				setEnabled(true);
			}else{
				setEnabled(false);
			}
			setDiscount((BigDecimal) bean.get(UsrMemberLevel.T.DISCOUNT.getFld().getCodeSqlField()));
			setLevelUp((BigDecimal) bean.get(UsrMemberLevel.T.LEVEL_UP.getFld().getCodeSqlField()));

			setCreatedby(BeanBase.load(SysUser.class,(Integer) bean.get(UsrMemberLevel.T.CREATED_BY.getFld().getCodeSqlField())).getName());
			setCreatetime((Date) bean.get(UsrMemberLevel.T.CREATED_TIME.getFld().getCodeSqlField()));
			setUpdby(BeanBase.load(SysUser.class,(Integer) bean.get(UsrMemberLevel.T.UPDATED_BY.getFld().getCodeSqlField())).getName());
			setUpdtime((Date) bean.get(UsrMemberLevel.T.UPDATED_TIME.getFld().getCodeSqlField()));
		}}).collect(Collectors.toList());
		return  list;

	}
}
