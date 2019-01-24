package irille.shop.pdt;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.PdtSizeView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.PdtSize.T;
import irille.shop.plt.PltConfigDAO;
import irille.view.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PdtSizeDAO {
    public static final Log LOG = new Log(PdtSizeDAO.class);

	/**
	 * 查询产品尺寸
	 * @author lingjian
	 * @date 2019/1/22 13:37
 	 * @param start
	 * @param limit
	 * @return
	 */
    public static Page listSize(String name, String productCategory,Integer start, Integer limit) {
		if (null == start) {
			start = 0;
		}
		if (null == limit) {
			limit = 5;
		}
		System.out.println(name + "---"+ productCategory);
		SQL sql = new SQL() {{
			SELECT(PdtSize.class).FROM(PdtSize.class).WHERE(PdtSize.T.DELETED, "=0");
			if(name != null){
				WHERE(PdtSize.T.NAME, "like ?", "%" + name + "%");
			}
			if(productCategory != null){
				WHERE(T.PRODUCT_CATEGORY, "=?", productCategory);
			}
		}};
		Integer count= Query.sql(sql).queryCount();
		List<PdtSizeView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new PdtSizeView() {{
			setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
			setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));

			Integer s = (Integer) bean.get(T.PRODUCT_CATEGORY.getFld().getCodeSqlField());
			if(null != s){
				setProductCategory(BeanBase.load(PdtCat.class,s).getName());
			}
			setCreatedBy(BeanBase.load(SysUser.class, Integer.valueOf(String.valueOf(bean.get(T.CREATE_BY.getFld().getCodeSqlField())))).getLoginName());
			setCreatedTime((Date) bean.get(T.CREATE_TIME.getFld().getCodeSqlField()));
		}}).collect(Collectors.toList());
		return new Page(list, start, limit, count);
	}
	/**
	 * 新增产品尺寸
	 * @author lingjian
	 * @date 2019/1/22 13:37
	 */
	public static class InsSize extends IduIns<PdtSizeDAO.Ins, PdtSize> {
		@Override
		public void before() {
			getB().setDeleted(OYn.NO.getLine().getKey());
			getB().setCreateTime(Env.getTranBeginTime());
			setB(translateUtil.autoTranslate(getB()));
			super.before();
		}
	}

	/**
	 * 修改产品尺寸
	 * @author lingjian
	 * @date 2019/1/22 13:37
	 */
	public static class UpdSize extends IduUpd<PdtSizeDAO.Upd, PdtSize> {
		@Override
		public void before() {
			PdtSize dbBean = loadThisBeanAndLock();
			getB().setCreateTime(Env.getSystemTime());//自动生成修改时间
			PropertyUtils.copyPropertiesWithout(
					dbBean, translateUtil.autoTranslateByManageLanguage(getB(), true),
					PdtSize.T.PKEY, PdtSize.T.SUPPLIER, PdtSize.T.CREATE_BY, PdtSize.T.CREATE_TIME, PdtSize.T.DELETED, PdtSize.T.ROW_VERSION);
			setB(dbBean);
		}
	}

	/**
	 * 删除产品尺寸
	 * @author lingjian
	 * @date 2019/1/22 13:38
	 */
	public static class DelSize extends IduUpd<PdtSizeDAO.Del, PdtSize> {
		@Override
		public void before() {
			super.before();
			getB().setDeleted(OYn.YES.getLine().getKey());
			PdtSize dbBean = loadThisBeanAndLock();
			PropertyUtils.copyProperties(dbBean, getB(), PdtSize.T.DELETED);
			setB(dbBean);
		}
	}


    public static List<PdtSize> listSummary(Language lang,Integer type) {
    	
    	SQL sql = new I18NSQL(lang){{
    		SELECT(T.PKEY, T.NAME,T.CREATE_BY,T.ROW_VERSION);
    		FROM(PdtSize.class).WHERE(T.DELETED,"=?", OYn.NO).WHERE(T.SUPPLIER," is null ");
    	}};
    	List<PdtSize> sysSize = Query.sql(sql).queryList(PdtSize.class);
    	if(type == 1){
    		SQL supSql = new I18NSQL(lang){{
        		SELECT(T.PKEY, T.NAME,T.CREATE_BY,T.ROW_VERSION);
        		FROM(PdtSize.class).WHERE(T.DELETED,"=?", OYn.NO).WHERE(T.SUPPLIER," =? ",SellerAction.getSupplier().getPkey());
        	}};
        	List<PdtSize> supSize = Query.sql(supSql).queryList(PdtSize.class);
        	sysSize.addAll(supSize);
    	}
    	return sysSize;
    }
    
    public static class InsSizeBySup extends IduOther<InsSizeBySup,PdtSize>{
		public void before() {
    		getB().setSupplier(SellerAction.getSupplier().getPkey());
    		getB().setDeleted(Sys.OYn.NO.getLine().getKey());
    		getB().setCreateTime(Env.getSystemTime());
    		setB(translateUtil.autoTranslate(getB()));
    	}
    	public void valid() {
    		ValidForm.validEmpty(getB().getName(), T.NAME);
    	}
    	public void run() {
    		PdtSize size = getB().ins();
    	}
    	public void after() {
    		setB(translateUtil.getAutoTranslate(getB(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
    	}
    }
    
    public static Integer delSizeBySup(Serializable id){
    	PdtSize size = BeanBase.load(PdtSize.class, id);
    	if(size.getCreateBy() != null){
    		throw LOG.err("noAccess","您没有权限删除平台尺寸");
    	}
    	size.setDeleted(Sys.OYn.YES.getLine().getKey());
    	size = size.upd();
    	return size.getPkey();
    }

    public static class Ins extends IduIns<Ins, PdtSize> {

        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().stCreateBy(getUser());
            getB().setCreateTime(Env.getTranBeginTime());
            translateUtil.autoTranslate(getB());
        }
    }

    public static class Upd extends IduUpd<Upd, PdtSize> {

        @Override
        public void before() {
            super.before();
				PdtSize dbBean = loadThisBeanAndLock();
	            PropertyUtils.copyPropertiesWithout(dbBean, translateUtil.autoTranslateByManageLanguage(getB(),true),T.PKEY,T.SUPPLIER,T.CREATE_BY, T.CREATE_TIME,T.DELETED);
	            setB(dbBean);
        }
    }

    public static class InsInit extends IduOther<InsInit, PdtSize> {
        private Stream<String> stream = Stream.empty();

        public Stream<String> getStream() {
            return stream;
        }

        public void setStream(Stream<String> stream) {
            this.stream = stream;
        }

        @Override
        public void run() {
            super.run();
            stream.forEach((name) -> {
                PdtSize b = new PdtSize().init();
                b.setDeleted(OYn.NO.getLine().getKey());
                b.setName(name);
                translateUtil.autoTranslate(b).ins();
            });
        }
    }
    public static class Del extends IduUpd<Upd,PdtSize>{
    	
    	@Override
    	public void before() {
    		super.before();
    		getB().setDeleted(OYn.YES.getLine().getKey());
    		PdtSize dbBean=loadThisBeanAndLock();
    		PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
    		setB(dbBean);
    	}
    	}
    
}
