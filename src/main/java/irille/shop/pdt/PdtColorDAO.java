package irille.shop.pdt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.statistics.Table;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.PdtCatDAO.Upd;
import irille.shop.pdt.PdtColor.T;
import irille.shop.plt.PltConfigDAO;

public class PdtColorDAO {
    public static final Log LOG = new Log(PdtColorDAO.class);
    
    public static void main(String[] args) {
    	PdtColor.TB.getCode();
    	new Table(Query.SELECT(T.PKEY, T.NAME).FROM(PdtColor.class).queryMaps()).print();
	}
    
    public static class InsColorBySup extends IduOther<InsColorBySup,PdtColor>{
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
    		PdtColor color = getB().ins();
    	}
    	public void after() {
    		setB(translateUtil.getAutoTranslate(getB(), PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
    	}
    }
    
    public static Integer delColorBySup(Serializable id){
    	PdtColor color = BeanBase.load(PdtColor.class, id);
    	if(color.getCreateBy() != null){
    		throw LOG.err("noAccess","您没有权限删除平台尺寸");
    	}
    	color.setDeleted(Sys.OYn.YES.getLine().getKey());
    	color = color.upd();
    	return color.getPkey();
    	
    }
    

    public static class PageSelect extends IduOther<PageSelect, PdtColor> {
    	private Integer type = -1;

        public List getAllColorList(FldLanguage.Language language) {
            FormaterSql sql = FormaterSql.build();
            sql.select(T.NAME).selectAs(T.PKEY, "id").select(T.CREATE_BY).eqAutoAnd(T.DELETED,OYn.NO.getLine().getKey()).Andwhere(T.SUPPLIER.getFld().getCodeSqlField()+" is null ");
            List<Map> sysColor = sql.castListMap(Bean.list(sql.buildSql(),sql.getParms()), language);
            List<Map> supColor = null;
            if(type != -1){
            	FormaterSql supSql = FormaterSql.build();
            	supSql.select(T.NAME).selectAs(T.PKEY, "id").select(T.CREATE_BY).select(T.ROW_VERSION).eqAutoAnd(T.DELETED,OYn.NO.getLine().getKey()).eqAutoAnd(T.SUPPLIER,SellerAction.getSupplier().getPkey());
            	supColor = supSql.castListMap(Bean.list(supSql.buildSql(),supSql.getParms()), language);
            }
            sysColor.addAll(supColor);
            return sysColor;
        }

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
        
    }

    public static class Ins extends IduIns<Ins, PdtColor> {

        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().stCreateBy(getUser());
            getB().setCreateTime(Env.getTranBeginTime());
            translateUtil.autoTranslate(getB());
        }
    }

    public static class Upd extends IduUpd<Upd, PdtColor> {

        @Override
        public void before() {
            super.before();
	            PdtColor dbBean = loadThisBeanAndLock();
	            PropertyUtils.copyPropertiesWithout(dbBean, translateUtil.autoTranslateByManageLanguage(getB(), true), T.PKEY,T.SUPPLIER,T.CREATE_BY, T.CREATE_TIME,T.DELETED);
	            setB(dbBean);
			
        }
    }

    public static class InsInit extends IduOther<InsInit, PdtColor> {
        private List<PdtColor> list = new ArrayList<>();

        public static PdtColor build(String name) {
            PdtColor c = new PdtColor().init();
            c.setName(name);
            return c;
        }

        @Override
        public void run() {
            super.run();
            for (PdtColor c : list) {
            	c.setDeleted(OYn.NO.getLine().getKey());
                translateUtil.autoTranslate(c).ins();
            }
        }

        public List<PdtColor> getList() {
            return list;
        }

        public void setList(List<PdtColor> list) {
            this.list = list;
        }

    }
    
    	public static class Del extends IduUpd<Upd,PdtColor>{
		    	@Override
		    	public void before() {
		    		super.before();
		    		getB().setDeleted(OYn.YES.getLine().getKey());
		    		PdtColor dbBean=loadThisBeanAndLock();
		    		PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
		    		setB(dbBean);
		    	}
    	}
    
}
