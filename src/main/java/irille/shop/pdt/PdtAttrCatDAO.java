package irille.shop.pdt;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.shop.pdt.PdtAttr.T;
import irille.shop.pdt.PdtAttrDAO.Upd;

import java.util.List;
import java.util.Map;

public class PdtAttrCatDAO {
    public static final Log LOG = new Log(PdtAttrCatDAO.class);
    
    public static class Ins extends IduIns<Ins,PdtAttrCat>{
            @Override
            public void before() {
                super.before();
                getB().setDeleted(OYn.NO.getLine().getKey());
                getB().setCreateBy(getUser().getPkey());
                getB().setCreateTime(Env.getSystemTime());
            }

            @Override
            public void valid() {
                super.valid();
                ValidForm.validEmpty(getB().getName(),PdtAttrCat.T.NAME);
            }
    }
    
    public static class Upd extends IduUpd<Upd,PdtAttrCat>{
    	@Override
    	public void before() {
    		super.before();
    		getB().setDeleted(OYn.NO.getLine().getKey());
    		getB().setCreateBy(getUser().getPkey());
    		getB().setCreateTime(Env.getSystemTime());
    	}
    	
    	@Override
    	public void valid() {
    		super.valid();
    		ValidForm.validEmpty(getB().getName(),PdtAttrCat.T.NAME);
    	}
    }
    public static class Del extends IduUpd<Upd, PdtAttrCat> {
        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtAttrCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
        }
    }
    

    public static class InsMap extends IduOther<InsMap, PdtAttrCat> {
        Map<String, Map<String, List<String>>> categoryMap;

        public Map<String, Map<String, List<String>>> getCategoryMap() {
            return categoryMap;
        }

        public void setCategoryMap(Map<String, Map<String, List<String>>> categoryMap) {
            this.categoryMap = categoryMap;
        }

        @Override
        public void run() {
            super.run();
            try {
                for (String categoryName : categoryMap.keySet()) {
                    PdtAttrCat category = new PdtAttrCat().init();
                    category.setName(categoryName);
                    category.setDeleted(OYn.NO.getLine().getKey());
                    translateUtil.autoTranslate(category).ins();
                    for (String attrName : categoryMap.get(categoryName).keySet()) {
                        PdtAttr attr = new PdtAttr().init();
                        attr.stCategory(category);
                        attr.setName(attrName);
                        attr.setDeleted(OYn.NO.getLine().getKey());
                        translateUtil.autoTranslate(attr).ins();

                        for (String lineName : categoryMap.get(categoryName).get(attrName)) {
                            PdtAttrLine line = new PdtAttrLine().init();
                            line.setDeleted(OYn.NO.getLine().getKey());
                            line.stMain(attr);
                            line.setName(lineName);
                            translateUtil.autoTranslate(line).ins();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
