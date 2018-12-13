package irille.shop.pdt;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.ValidForm;
import irille.shop.pdt.PdtCat.T;
import irille.view.pdt.CategoryView;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PdtCatDAO {
    public static final Log LOG = new Log(PdtCatDAO.class);


    /**
     * 获取一级产品分类
     *
     * @param lang 语言
     * @author yingjianhua
     */
    public static List<CategoryView> listTopCatView(Language lang) throws JSONException {
        List<PdtCat> list = irille.pub.bean.Query.SELECT(PdtCat.class).WHERE(T.CATEGORY_UP, "is NULL").WHERE(T.DELETED, " = ?", OYn.NO).queryList();
        List<CategoryView> views = new ArrayList<>();
        for (PdtCat line : list) {
            CategoryView view = new CategoryView();
            view.setId(line.getPkey());
            view.setName(line.getName(lang));
            views.add(view);
        }
        return views;
    }

    public static class Query extends IduOther<Query, PdtCat> {
        public static List<PdtCat> listSub(Integer pkey) {
            return BeanBase.list(PdtCat.class, PdtCat.T.CATEGORY_UP.getFld().getCodeSqlField() + " = ? AND " + PdtCat.T.DELETED.getFld().getCodeSqlField() + " = ?", false, pkey, OYn.NO.getLine().getKey());
        }

        /**
         * 获取所有一级分类
         *
         * @return
         */
        public static List<PdtCat> listTopCat() {
            return BeanBase.list(PdtCat.class, PdtCat.T.CATEGORY_UP.getFld().getCodeSqlField() + " is NULL  AND " + PdtCat.T.DELETED.getFld().getCodeSqlField() + " = " + OYn.NO.getLine().getKey(), false);
        }


    }

    public static class InsMap extends IduOther<InsMap, PdtCat> {
        private Map<String, Map<String, List<String>>> categoryMap;

        public Map<String, Map<String, List<String>>> getCategoryMap() {
            return categoryMap;
        }

        public void setCategoryMap(Map<String, Map<String, List<String>>> categoryMap) {
            this.categoryMap = categoryMap;
        }

        @Override
        public void run() {
            super.run();
            for (String categoryName : categoryMap.keySet()) {
                try {
                    PdtCat category1 = new PdtCat().init();
                    category1.setName(categoryName);
                    category1.setDeleted(OYn.NO.getLine().getKey());
                    category1 = translateUtil.autoTranslate(category1);
                    category1.ins();
                    for (String attrName : categoryMap.get(categoryName).keySet()) {
                        PdtCat category2 = new PdtCat().init();
                        category2.stCategoryUp(category1);
                        category2.setName(attrName);
                        category2.setDeleted(OYn.NO.getLine().getKey());
                        category2 = translateUtil.autoTranslate(category2);
                        category2.ins();
                        for (String lineName : categoryMap.get(categoryName).get(attrName)) {
                            PdtCat category3 = new PdtCat().init();
                            category3.stCategoryUp(category2);
                            category3.setName(lineName);
                            category3.setDeleted(OYn.NO.getLine().getKey());
                            category3 = translateUtil.autoTranslate(category3);
                            category3.ins();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Upd extends IduUpd<Upd, PdtCat> {
        @Override
        public void before() {
            PdtCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyPropertiesWithout(dbBean, getB(), PdtCat.T.PKEY, PdtCat.T.CREATE_BY, PdtCat.T.CREATE_TIME, PdtCat.T.DELETED);
            setB(translateUtil.autoTranslate(dbBean));
            super.before();

        }
    }

    public static class Ins extends IduIns<Ins, PdtCat> {
        @Override
        public void before() {
            ValidForm.validEmpty(getB().getName(), T.NAME);
            super.before();
            getB().setDeleted(OYn.NO.getLine().getKey());
            getB().setCreateBy(getUser().getPkey());
            getB().setCreateTime(Env.getSystemTime());
            setB(translateUtil.autoTranslate(getB()));
        }
    }

    public static class Del extends IduUpd<Upd, PdtCat> {

        @Override
        public void before() {
            super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
        }

        public void valid() {
            super.valid();
            SQL sql = new SQL() {{
                SELECT(PdtCat.class);
                FROM(PdtCat.class);
                WHERE(T.CATEGORY_UP, "=?").PARAM(getB().getPkey());
            }};
            SqlQuery query = irille.pub.bean.Query.sql(sql);
            Integer count = query.queryCount();
            if (count > 0) {
                throw LOG.err("hasChild", "存在下级分类,不可删除");
            }
        }
    }
}
