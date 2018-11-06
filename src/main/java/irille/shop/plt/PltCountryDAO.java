package irille.shop.plt;



import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.svr.Env.SysConf;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSize;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.Plt.ErrMsgs;
import irille.shop.plt.PltCountry.T;
import irille.temporary.entity.DataData;
import irille.temporary.entity.DataInfo;
import irille.view.plt.CountryView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

public class PltCountryDAO {
	private static final Log LOG = new Log(PltCountryDAO.class);
	private static List<PltCountry> addCountry = null; 

	public static PltCountry loadByName(String name) {
		return PltCountry.chkUniqueShort_name(false, name);
	}
	
	public static List<PltCountry> list(Language lang) {
		SQL sql = new I18NSQL(lang){{
			SELECT(T.PKEY,T.NAME, T.SHORT_NAME);
			FROM(PltCountry.class);
		}};
		return Query.sql(sql).queryList(PltCountry.class);
	}
	
	/**
	 * @return
	 * @author yingjianhua
	 * @throws JSONException 
	 */
	public static List<CountryView> listView(Language lang) throws JSONException {
		List<CountryView> views = new ArrayList<>();
		for(PltCountry bean:Query.SELECT(PltCountry.class).queryList()) {
			CountryView view = new CountryView();
			view.setId(bean.getPkey());
			view.setName(lang==null?bean.getName():bean.getName(lang));
			view.setShortName(bean.getShortName());
			view.setFlag(bean.getNationalFlag());
			views.add(view);
		}
		return views;
	}
	/**
	 * 在运行plt的时候像数据库录入初始化数据
	 * @author yingjianhua
	 *
	 */
	public static class InsInit extends IduOther<InsInit, PltCountry> {
		private List<PltCountry> list;
		public static PltCountry build(String name, String shortName, String zone, Integer currency, String nationalFlag, boolean enabled, boolean hot, boolean isDefault) {
			PltCountry c = new PltCountry();
			c.setName(name);
			c.setShortName(shortName);
			c.setZone(zone);
			c.setCurrency(currency);
			c.setNationalFlag(nationalFlag);
			c.stEnabled(enabled);
			c.stHot(hot);
			c.stIsdefault(isDefault);
			c.setCreatedBy(getUser().getPkey());
			c.setCreatedTime(Env.getTranBeginTime());
			c.setRowVersion((short)0);
			return c;
		}
		@Override
		public void run() {
			super.run();
			if(list != null) {
				for(PltCountry line:list) {
					line.ins();
				}
			}
		}
		public List<PltCountry> getList() {
			return list;
		}
		public void setList(List<PltCountry> list) {
			this.list = list;
		}
	}
	
	public static class Ins extends IduInsLines<Ins, PltCountry, PltProvince> {
		
		@Override
		public void before() {
			super.before();
			getB().stEnabled(true);
			getB().stHot(false);
			getB().stIsdefault(false);
			getB().setCreatedTime(Env.getSystemTime());
            getB().setCreatedBy(Idu.getUser().getPkey());
		}
		
		@Override
		public void after() {
			insLine(getB(), getLines(), PltProvince.T.MAIN.getFld());
			addCountry = null;
			super.after();
		}
		
		@Override
		public void valid() {
			super.valid();
			 if(PltCountry.chkUniqueShort_name(true, getB().getShortName())!=null)
				    throw LOG.err(ErrMsgs.uniqueErr, PltCountry.T.NAME.getFld().getName());	
	        	
		}
	}
	
	public  static class Upd extends IduUpdLines<Upd,PltCountry, PltProvince> {
		@Override
		public void before() {
			PltCountry dbBean = loadThisBeanAndLock();
			PropertyUtils.copyPropertiesWithout(dbBean, getB(),PltCountry.T.PKEY, PltCountry.T.ENABLED,PltCountry.T.HOT,PltCountry.T.CREATED_BY,PltCountry.T.CREATED_TIME);
			translateUtil.autoTranslateByManageLanguage(dbBean, true);
			setB(dbBean);
			String wheresql = PltProvince.T.MAIN.getFld().getCodeSqlField() + "=?";
			List<PltProvince> listOld = BeanBase.list(PltProvince.T.MAIN.getFld().getTb().getClazz(), wheresql, false, getB().pkeyValues());
			boolean insFlag;
			for (PltProvince formBean : getLines()) {
					formBean.propertySet(PltProvince.T.MAIN.getFld(), getB().getPkey());
					insFlag = true; 
					for (PltProvince bean : listOld) {
						try {
							if (null!=formBean.getPkey()&&bean.getPkey().intValue()==formBean.getPkey().intValue()&&!bean.getName(PltConfigDAO.manageLanguage()).equals(formBean.getName())) {
								insFlag = false; 
								PropertyUtils.copyWithoutCollection(bean, formBean);
								validFromOut(bean);
								bean=translateUtil.autoTranslateByManageLanguage(bean, true);
								bean.upd();
								break;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (formBean.getPkey()==null) {
						formBean.setRowVersion((short) 0);
						validFromOut(formBean);
						formBean=translateUtil.autoTranslateByManageLanguage(formBean, true);
						formBean.ins();
					}
				}
				for (PltProvince bean : listOld) {
					if (getLines().contains(bean))
					continue;
					bean.del();
				}
		}
		@Override
		public void after() {
			addCountry = null;
			super.after();
		}
	}
	
	public static class Del extends IduDel<Del,PltCountry>{
		@Override
		public void before() {
			super.before();
			delLine(getLines(PltProvince.T.MAIN,getB().getPkey()));
		}
		@Override
		public void after() {
			addCountry = null;
			super.after();
		}
	}
	
	public static class Hot extends IduOther<Hot, PltCountry> {
		@Override
		public void run() {
			super.run();
			getB().stHot(!getB().gtHot()); //判断是否选中
			getB().upd();
		}
		@Override
		public void after() {
			addCountry = null;
			super.after();
		}
	}
	

}