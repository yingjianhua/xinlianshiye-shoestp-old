package irille.shop.pdt;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.platform.pdt.View.pdtCatView.PdtCatView;
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
import irille.view.Page;
import irille.view.pdt.CategoryView;
import org.json.JSONException;

import java.util.*;
import java.util.stream.Collectors;

public class PdtCatDAO {
    public static final Log LOG = new Log(PdtCatDAO.class);

    /**
     * 获取产品类目
     */
    public static Page listPdtCat(Integer start, Integer limit, Integer enabled, String name, Integer subjectionCat) {
        List<PdtCat> listCat = irille.pub.bean.Query.SELECT(PdtCat.class).queryList();
        HashMap map = new HashMap();
        for (PdtCat line : listCat) {
            map.put(line.getPkey(), line.getName());
        }
        SQL sql = new SQL();
        sql.SELECT(PdtCat.class).FROM(PdtCat.class);
        sql.WHERE(PdtCat.T.DELETED, "=?", 0);
        if (enabled != null) {
            sql.WHERE(T.ENABLED, "=?", enabled);
        }
        if (name != null) {
            sql.WHERE(T.NAME, "like ?", "%" + name + "%");
        }
        if (subjectionCat != null) {
            sql.WHERE(T.CATEGORY_UP, "=?", subjectionCat);
        }
        Integer count = irille.pub.bean.Query.sql(sql).queryCount();
        List<PdtCatView> list = irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(bean -> new PdtCatView() {{
            setId((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
            setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
            Integer id = (Integer) bean.get(T.CATEGORY_UP.getFld().getCodeSqlField());
            if (id != null) {
                setSubjectionCat((String) map.get(id));
            }
            setCategoryId((Integer) bean.get(T.CATEGORY_UP.getFld().getCodeSqlField()));
            setEnabled(Integer.valueOf(String.valueOf(bean.get(T.ENABLED.getFld().getCodeSqlField()))) == 1 ? true : false);
            setCreateTime((Date) bean.get(T.CREATE_TIME.getFld().getCodeSqlField()));
            setCreateBy(BeanBase.load(SysUser.class, Integer.valueOf(String.valueOf(bean.get(T.CREATE_BY.getFld().getCodeSqlField())))).getLoginName());
            setSeoDescription((String) bean.get(T.SEO_DESCRIPTION_EN.getFld().getCodeSqlField()));
            setSeoKeyword((String) bean.get(T.SEO_KEYWORD_EN.getFld().getCodeSqlField()));
            setSeoName((String) bean.get(T.SEO_TITLE_EN.getFld().getCodeSqlField()));

        }}).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }

    /**
     * 是否启用
     */
    public static class enable extends IduOther<enable, PdtCat> {
        @Override
        public void before() {
        }

        @Override
        public void valid() {
        }

        @Override
        public void run() {
            getB().stEnabled(!getB().gtEnabled()); //判断是否选中
            PdtCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.ENABLED);
            dbBean.upd();
            super.run();
        }
    }

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
            return BeanBase.list(PdtCat.class, T.CATEGORY_UP.getFld().getCodeSqlField() + " = ? AND " + T.DELETED.getFld().getCodeSqlField() + " = ?", false, pkey, OYn.NO.getLine().getKey());
        }

        /**
         * 获取所有一级分类
         *
         * @return
         */
        public static List<PdtCat> listTopCat() {
            return BeanBase.list(PdtCat.class, T.CATEGORY_UP.getFld().getCodeSqlField() + " is NULL  AND " + T.DELETED.getFld().getCodeSqlField() + " = " + OYn.NO.getLine().getKey(), false);
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
            PropertyUtils.copyPropertiesWithout(dbBean, getB(), T.PKEY, T.CREATE_BY, T.CREATE_TIME, T.DELETED);
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

    public static class De extends IduUpd<Upd, PdtCat> {
        @Override
        public void before() {
            //super.before();
            getB().setDeleted(OYn.YES.getLine().getKey());
            PdtCat dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(), T.DELETED);
            setB(dbBean);
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

    //修改
    public static void updPdtCat(String name, Integer enabled, Integer subjectionCat, String seoDescription, String seoKeyword, String seoName, Integer id) {
        PdtCat pdtCat = BeanBase.load(PdtCat.class, id);
        pdtCat.setName(name);
        pdtCat.setEnabled((byte) enabled.intValue());
        pdtCat.setCategoryUp(subjectionCat);
        pdtCat.setSeoDescriptionEn(seoDescription);
        pdtCat.setSeoKeywordEn(seoKeyword);
        pdtCat.setSeoTitleEn(seoName);
        pdtCat.upd();
    }

    //添加
    public static void addPdtCat(String name, Integer enabled, Integer subjectionCat, String seoDescription, String seoKeyword, String seoName, Integer createBy) throws Exception {
        PdtCat pdtCat = new PdtCat();
        pdtCat.setCategoryUp(subjectionCat);
        pdtCat.setSeoTitleEn(seoName);
        pdtCat.setSeoKeywordEn(seoKeyword);
        pdtCat.setSeoDescriptionEn(seoDescription);
        pdtCat.setEnabled((byte) enabled.intValue());
        pdtCat.setName(name);
        pdtCat.setCreateBy(createBy);
        pdtCat.setDeleted(OYn.NO.getLine().getKey());
        pdtCat.setCreateTime(Env.getSystemTime());
        pdtCat.ins();
    }

    /**
     * -获取所有分类
     */
    public static List<PdtCat> listAll(){
    	  List<PdtCat> listCat = irille.pub.bean.Query.SELECT(PdtCat.class).WHERE(T.DELETED, " = ?", OYn.NO).queryList();
    	  return listCat;
    }

    /**
     * xy
     * 查询男鞋或女鞋的子分类
     * @param gender
     * @return
     */
    public static List<Integer> getListByGender(Integer gender) {
    	SQL sql = new SQL();
    	sql.SELECT(PdtCat.T.PKEY).FROM(PdtCat.class).WHERE(PdtCat.T.CATEGORY_UP, " =? ",gender);
    	List<Integer> list = irille.pub.bean.Query.sql(sql).queryMaps().stream().map(bean->{
    		return GetValue.get(bean, PdtCat.T.PKEY, Integer.class, null);
    	}).collect(Collectors.toList());
    	return list;
    }
}
