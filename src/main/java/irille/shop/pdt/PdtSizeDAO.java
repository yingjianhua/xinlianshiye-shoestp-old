package irille.shop.pdt;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
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

public class PdtSizeDAO {
    public static final Log LOG = new Log(PdtSizeDAO.class);
    
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
