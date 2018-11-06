package irille.shop.plt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import irille.homeAction.HomeAction;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.sellerAction.SellerAction;
import irille.shop.plt.Plt.ErrMsgs;


public class PltFreightDAO {
	private static final Log LOG = new Log(PltFreightDAO.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		priceErr("【{0}】不能低于或等于【{1}】"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	
	public static class Ins extends IduInsLines<Ins, PltFreight, PltFreightLine>{
		
		@Override
		public void before() {
			super.before();
			getB().setUserSys(getUser().getPkey());
			getB().setCreatedTime(Env.getSystemTime());
            getB().setCreatedBy(Idu.getUser().getPkey());
			getB().stEnabled(true);
		}
		@Override
		public void after() {
			insLine(getB(), getLines(), PltFreightLine.T.MAIN.getFld());
			super.after();
		}
		
		@Override
		public void valid() {
			super.valid();
			
			List<PltFreightLine> s=getLines();
			if(getB().getWeightMin().compareTo(getB().getWeightMax())>=0)
				throw LOG.err(Msgs.priceErr, PltFreight.T.WEIGHT_MAX.getFld().getName(), PltFreight.T.WEIGHT_MIN.getFld().getName());
	        if(PltFreight.chkUniqueCompany(true, getB().getCompany()) != null) 
	        	throw LOG.err(ErrMsgs.uniqueErr, getB().getCompany());		
	        if(getB().getSort()!= null) {
	        	if(getB().getSort()<0)
	        		throw LOG.err(ErrMsgs.lowPriceErr,PltFreight.T.SORT.getFld().getName(),0);
	        	}	
	        if(getLines()!= null)
	        	for (int i = 0; i < s.size(); i++) {
					
					if(s.get(i).getWeightPrice().compareTo(BigDecimal.ZERO)<0)
					{
						throw LOG.err(ErrMsgs.lowPriceErr,PltFreightLine.T.WEIGHT_PRICE .getFld().getName(),0);
					}
					
					if(s.get(i).getAggravatePrice().compareTo(BigDecimal.ZERO)<0)
					{
						throw LOG.err(ErrMsgs.lowPriceErr,PltFreightLine.T.AGGRAVATE_PRICE.getFld().getName(),0);
					}
				}
	        			
	        	
		}
		
		
	}
	public  static class Upd extends IduUpdLines<Upd,PltFreight, PltFreightLine>
	{
		@Override
		public void before() {
			PltFreight dbBean = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(dbBean, getB(),PltFreight.T.PKEY,PltFreight.T.ENABLED,PltFreight.T.CREATED_BY,PltFreight.T.CREATED_TIME,PltFreight.T.USER_SYS);
			setB(dbBean);
			updLine(getB(), getLines(), PltFreightLine.T.MAIN.getFld());
		}
		
		
		@Override
		public void valid() {
			super.valid();
			
			List<PltFreightLine> s=getLines();
			if(getB().getWeightMin().compareTo(getB().getWeightMax())>=0)
				throw LOG.err(Msgs.priceErr, PltFreight.T.WEIGHT_MAX.getFld().getName(), PltFreight.T.WEIGHT_MIN.getFld().getName());	
	        if(getB().getSort()!= null) {
	        	if(getB().getSort()<0)
	        		throw LOG.err(ErrMsgs.lowPriceErr,PltFreight.T.SORT.getFld().getName(),0);
	        	}	
	        if(getLines()!= null)
	        	for (int i = 0; i < s.size(); i++) {
	        		//System.out.println(s.get(i).getWeightfirst());
					
					if(s.get(i).getWeightPrice().compareTo(BigDecimal.ZERO)<0)
					{
						throw LOG.err(ErrMsgs.lowPriceErr,PltFreightLine.T.WEIGHT_PRICE .getFld().getName(),0);
					}
					
					if(s.get(i).getWeightPrice().compareTo(BigDecimal.ZERO)<0)
					{
						throw LOG.err(ErrMsgs.lowPriceErr,PltFreightLine.T.WEIGHT_PRICE.getFld().getName(),0);
					}
				}
	        			
	        	
		}
		
	}
	public static class Del extends IduDel<Del,PltFreight>{
		@Override
		public void before() {
			super.before();
			delLine(getLines(PltFreightLine.T.MAIN,getB().getPkey()));
			//delLineTid(getB(), PltFreightLine.class);
		}
	}
}
