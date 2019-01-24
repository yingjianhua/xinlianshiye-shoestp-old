package irille.shop.usr;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.platform.pdt.View.PdtAttrCatView;
import irille.platform.pdt.view.UsrProductCategoryView.UsrProductCategoryView;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.I18NSQL;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduOther;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.Valid;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrProductCategory.T;
import irille.view.Page;

public class UsrProductCategoryDAO {
    public static final Log LOG = new Log(UsrProductCategoryDAO.class);

    /**
     * 商家新增供应商产品类目
     *
     * @author yingjianhua
     */
    public static void add(UsrProductCategory bean, Integer supplier) {
        //基础校验
        Valid.bean(bean).notBlank(T.NAME);
        SQL sql = new SQL() {{
            SELECT(UsrProductCategory.class);
            FROM(UsrProductCategory.class);
            WHERE(T.NAME, "=?").PARAM(bean.getName());
            WHERE(T.SUPPLIER, "=?").PARAM(SellerAction.getSupplier().getPkey());
        }};
        SqlQuery query = Query.sql(sql);
        Integer count = query.queryCount();
        if (count != 0) {
            throw LOG.err("copyName", "分类名称已存在");
        }
        //逻辑校验
        UsrProductCategory up = bean.gtCategoryUp();
        if (up != null && !up.getSupplier().equals(supplier))
            throw LOG.err("上级分类的供应商不是当前供应商", "隶属分类错误");

        bean.setSupplier(supplier);
        bean.stEnabled(false);
        translateUtil.autoTranslate(bean);
        bean.ins();
    }

    /**
     * 更新店铺产品分类的上下架状态
     *
     * @param pkey     分类pkey
     * @param enabeld  true 上架, false 下架
     * @param supplier 分类供应商
     */
    public static void updateEnabled(final Integer pkey, final boolean enabeld, final Integer supplier) {
        if (Query.SELECT(UsrProductCategory.class).WHERE(T.PKEY, "=?", pkey).WHERE(T.SUPPLIER, "=?", supplier).queryCount() == 0) {
            return;
        }
        Set<Integer> allCategory = new HashSet<>();
        allCategory.add(pkey);
        int a = 0;
        int b = 0;
        String pkeys = String.valueOf(pkey);
        do {
            List<Integer> list = Query
                    .SELECT(T.PKEY)
                    .FROM(UsrProductCategory.class)
                    .WHERE(T.CATEGORY_UP, "in (" + pkeys + ")")
                    .WHERE(T.SUPPLIER, "=?", supplier)
                    .queryList(Integer.class);
            a = allCategory.size();
            allCategory.addAll(list);
            b = allCategory.size();
            pkeys = list.stream().map(p -> String.valueOf(p)).collect(Collectors.joining(","));
        } while (b > a);
        Query
                .UPDATE(UsrProductCategory.class)
                .SET(T.ENABLED, enabeld ? OEnabled.TRUE : OEnabled.FALSE)
                .WHERE(T.PKEY, "in (" + allCategory.stream().map(p -> String.valueOf(p)).collect(Collectors.joining(",")) + ")")
                .WHERE(T.ENABLED, "=?", enabeld ? OEnabled.FALSE : OEnabled.TRUE)
                .executeUpdate();

        //同时更新这些分类下的商品的状态 TODO
    }

    public static void main(String[] args) throws SQLException {
        BeanQuery.config(true);
        UsrProductCategory.TB.getCode();
        updateEnabled(370, true, 12);
        DbPool.getInstance().getConn().rollback();
        DbPool.getInstance().releaseAll();
    }

    /**
     * 修改分类的上下架状态,同时需要修改此分类的上架下架状态
     *
     * @author liyichao
     */
    public static class UpperAndLowerFrame extends IduOther<UpperAndLowerFrame, UsrProductCategory> {
        public void valid() {
            if (getB().gtCategoryUp() != null && Integer.valueOf(getB().gtCategoryUp().getEnabled()).equals(Integer.valueOf(Sys.OEnabled.FALSE.getLine().getKey()))) {
                throw LOG.err(Usr.ErrMsgs.dataWrong);
            }
        }

        public void run() {
            //if(Integer.valueOf(getB().getEnabled()).equals(Integer.valueOf(Sys.OEnabled.FALSE.getLine().getKey()))){
            List<UsrProductCategory> catList = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " in(" + getAllCat(getB().getPkey()) + ")", false, SellerAction.getSupplier().getPkey());
            catSet.clear();
            for (UsrProductCategory cat : catList) {
                cat.setEnabled(getB().getEnabled());
                cat.upd();
            }
            updatePdtState(getAllCat(getB().getPkey()), Integer.valueOf(getB().getEnabled()));
            //}
            getB().upd();
        }

        public void log() {
        }


    }

    private static Set<String> catSet = new HashSet<String>();

    public static Set<String> getCatSet() {
        return catSet;
    }

    public void setCatSet(Set<String> catSet) {
        this.catSet = catSet;
    }

    public static String getAllCat(Integer category) {
        catSet.add(String.valueOf(category));
        getAllSubclassification(String.valueOf(category));
        String pkeys = "";
        for (String s : getCatSet()) {
            if (pkeys == "") {
                pkeys += s;
            } else {
                pkeys += "," + s;
            }
        }
        return pkeys;
    }

    /**
     * 获取所有子分类pkey
     */
    public static void getAllSubclassification(String category) {
        List<UsrProductCategory> catList = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " in(" + category + ") ", false, SellerAction.getSupplier().getPkey());
        String childCat = "";
        if (catList != null && catList.size() > 0) {
            for (int i = 0; i < catList.size(); i++) {
                if (childCat == "") {
                    childCat += catList.get(i).getPkey();
                } else {
                    childCat += "," + catList.get(i).getPkey();
                }
                catSet.add(catList.get(i).getPkey().toString());
            }
            getAllSubclassification(childCat);
        }
    }

    private static void updatePdtState(String cat, Integer state) {
        List<PdtProduct> pdtList = BeanBase.list(PdtProduct.class, PdtProduct.T.SUPPLIER.getFld().getCodeSqlField() + " = ? AND " + PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField() + " in(" + cat + ") AND " + PdtProduct.T.STATE.getFld().getCodeSqlField() + " <> " + Pdt.OState.DELETE.getLine().getKey(), false, SellerAction.getSupplier().getPkey());
        for (PdtProduct pdt : pdtList) {
            if (state.equals(Integer.valueOf(Sys.OEnabled.TRUE.getLine().getKey()))) {
                pdt.setState(Pdt.OState.ON.getLine().getKey());
                pdt.setSoldTimeB(Env.getSystemTime());
            } else {
                pdt.setState(Pdt.OState.OFF.getLine().getKey());
                pdt.setSoldTimeE(Env.getSystemTime());
            }
            pdt.upd();
        }
    }

    public static class Update extends IduOther<Update, UsrProductCategory> {
        public void before() {
            Valid.bean(getB()).notBlank(T.NAME);
            SQL sql = new SQL() {{
                SELECT(UsrProductCategory.class);
                FROM(UsrProductCategory.class);
                WHERE(T.NAME, "=?").PARAM(getB().getName());
                WHERE(T.SUPPLIER, "=?").PARAM(SellerAction.getSupplier().getPkey());
            }};
            String allCats = getAllCat(getB().getPkey());
            catSet.clear();
            if (allCats.indexOf(",") != -1) {
                throw LOG.err("noAccess", "存在子分类的分类不可作为子分类");
            }
            if (allCats.indexOf(String.valueOf(getB().getCategoryUp())) != -1) {
                throw LOG.err("noAccess", "不可将子分类作为自己的父分类");
            }
            SqlQuery query = Query.sql(sql);
            Integer count = query.queryCount();
            if (count != 0) {
                throw LOG.err("copyName", "分类名称已存在");
            }
            UsrProductCategory cat = loadThisBeanAndLock();
            PropertyUtils.copyProperties(cat, getB(), UsrProductCategory.T.NAME, UsrProductCategory.T.CATEGORY_UP, UsrProductCategory.T.SEO_DESCRIPTION_EN, UsrProductCategory.T.SEO_KEYWORD_EN, UsrProductCategory.T.SEO_TITLE_EN);
            setB(cat);
            translateUtil.autoTranslate(getB());
        }

        public void run() {
            getB().upd();
        }
    }

    public static class Del extends IduDel<Del, UsrProductCategory> {
        public void valid() {
            List<UsrProductCategory> catList = BeanBase.list(UsrProductCategory.class, UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " = ? ", false, getB().getPkey());
            if (catList != null && catList.size() > 0) {
                throw LOG.err(Usr.ErrMsgs.hasChild);
            }
        }

        @Override
        public void run() {
            List<PdtProduct> pdtList = BeanBase.list(PdtProduct.class, PdtProduct.T.CATEGORY_DIY.getFld().getCodeSqlField() + " = ? ", false, getB().getPkey());
            for (PdtProduct pdt : pdtList) {
                if (getB().getCategoryUp() != null) {
                    pdt.setCategoryDiy(getB().getCategoryUp());
                } else {
                    pdt.setCategoryDiy(null);
                }
                pdt.upd();
            }
            super.run();
        }
    }

    public static String getPkeys(List<UsrProductCategory> catList) {
        String catPkeys = "";
        for (UsrProductCategory cat : catList) {
            if (catPkeys == "") {
                catPkeys += cat.getPkey();
            } else {
                catPkeys += "," + cat.getPkey();
            }
        }
        return catPkeys;
    }

//	public static class BatchDelete extends IduOther<BatchDelete,UsrProductCategory>{
//		private String pkeys;
//		private List<UsrProductCategory> catList = new ArrayList<UsrProductCategory>();
//		public List<UsrProductCategory> getCatList() {
//			return catList;
//		}
//		public void setCatList(List<UsrProductCategory> catList) {
//			this.catList = catList;
//		}
//		public String getPkeys() {
//			return pkeys;
//		}
//		public void setPkeys(String pkeys) {
//			this.pkeys = pkeys;
//		}
//		public void before() {
//			catList = BeanBase.list(UsrProductCategory.class,UsrProductCategory.T.PKEY.getFld().getCodeSqlField() + " in(" + pkeys + ")",false);
//		}
//		
//		public void valid() {
//			for(UsrProductCategory cat : catList){
//				List<UsrProductCategory> childrenCat = BeanBase.list(UsrProductCategory.class,UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " = ? ",false,cat.getPkey()); 
//				if(childrenCat != null && childrenCat.size() > 0){
//					throw LOG.err(Usr.ErrMsgs.hasChild);
//				}
//			}
//		}
//		
//		public void run() {
//			Idu.delLine(catList);
//		}
//		
//	}

    public static class Sellect extends IduOther<Sellect, UsrProductCategory> {
        private static String pkeys = "";

        /**
         * 通过id查询所有子分类id
         */
        public static String getAllChild(FldLanguage.Language lang, Serializable supplier, Integer id) {
            List<UsrProductCategory> allCatBySup = getAllCatBySup(lang, supplier);
            pkeys = String.valueOf(id);
            getChildPkeys(allCatBySup, id);
            return pkeys;
        }

        /**
         * 递归查询子分类id
         */
        public static void getChildPkeys(List<UsrProductCategory> categories, Integer id) {
            for (UsrProductCategory cat : categories) {
                if (cat.getCategoryUp() == null) {
                    continue;
                }
                if (cat.getCategoryUp().equals(id)) {
                    pkeys += "," + String.valueOf(cat.getPkey());
                    getChildPkeys(categories, cat.getPkey());
                } else {
                    continue;
                }
            }
        }

        /**
         * 查询某商家下所有分类
         */
        public static List<UsrProductCategory> getAllCatBySup(FldLanguage.Language lang, Serializable supplier) {
            SQL sql = new I18NSQL(lang) {{
                SELECT(UsrProductCategory.class)
                        .FROM(UsrProductCategory.class)
                        .WHERE(T.SUPPLIER, "=?", supplier);
            }};
            return Query.sql(sql).queryList(UsrProductCategory.class);
        }

        /**
         * 查询某商家下所有启用的一级分类
         */
        public static List<UsrProductCategory> getTopCat(FldLanguage.Language lang, Serializable supplier) {
            SQL sql = new I18NSQL(lang) {{
                SELECT(T.PKEY, T.NAME)
                        .FROM(UsrProductCategory.class)
                        .WHERE(T.SUPPLIER, "=?", supplier)
                        .WHERE(T.CATEGORY_UP, " is null ")
                        .WHERE(T.ENABLED, "=?", Sys.OEnabled.TRUE);
            }};
            return Query.sql(sql).queryList(UsrProductCategory.class);
        }
    }

    /**
     * 查询所有分类
     */
    public static Page getUsrProductCategory(Integer start, Integer limit, String name, Integer category, Integer supplier, Integer enabled, String seoKeyword) {
        List<UsrProductCategory> listCat = irille.pub.bean.Query.SELECT(UsrProductCategory.class).queryList();
        HashMap map = new HashMap();
        for (UsrProductCategory line : listCat) {
            map.put(line.getPkey(), line.getName());
        }
        SQL sql = new SQL();
        sql.SELECT(UsrProductCategory.T.PKEY, "pkey");
        sql.SELECT(UsrProductCategory.T.NAME, "names");
        sql.SELECT(UsrProductCategory.T.SUPPLIER);
        sql.SELECT(UsrProductCategory.T.CATEGORY_UP);
        sql.SELECT(UsrProductCategory.T.SEO_KEYWORD_EN);
        sql.SELECT(UsrProductCategory.T.SEO_DESCRIPTION_EN);
        sql.SELECT(UsrProductCategory.T.SEO_TITLE_EN);
        sql.SELECT(UsrProductCategory.T.ENABLED);
        sql.SELECT(UsrSupplier.T.NAME);
        sql.FROM(UsrProductCategory.class);
        sql.LEFT_JOIN(UsrSupplier.class, UsrProductCategory.T.SUPPLIER, UsrSupplier.T.PKEY);
        if (category != null) {
            sql.WHERE(T.CATEGORY_UP, "=?", category);
        }
        if (name != null) {
            sql.WHERE(UsrProductCategory.T.NAME, " like '%" + name + "%' ");
        }
        if (supplier != null) {
            sql.WHERE(UsrProductCategory.T.SUPPLIER, "=?", supplier);
        }
        if (enabled != null) {
            sql.WHERE(UsrProductCategory.T.ENABLED, "=?", enabled);
        }

        if (seoKeyword != null) {
            sql.WHERE(T.SEO_KEYWORD_EN, " like '%" + seoKeyword + "%' ");
        }
        Integer count = Query.sql(sql).queryCount();
        List<UsrProductCategoryView> list = Query.sql(sql.LIMIT(start, limit)).queryMaps().stream().map(o -> {
            UsrProductCategoryView view = new UsrProductCategoryView();
            view.setId(Integer.valueOf(String.valueOf(o.get("pkey"))));
            view.setName(String.valueOf(o.get(UsrProductCategory.T.NAME.getFld().getCodeSqlField())));
            view.setName(String.valueOf(o.get("names")));
            view.setSupplierId((Integer) o.get(UsrProductCategory.T.SUPPLIER.getFld().getCodeSqlField()));
            view.setSupplier(String.valueOf(o.get(UsrSupplier.T.NAME.getFld().getCodeSqlField())));
            view.setCategoryId((Integer) o.get(UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField()));
            view.setEnabled(Integer.valueOf(String.valueOf(o.get(PdtCat.T.ENABLED.getFld().getCodeSqlField()))));
            view.setSeoKeyword(String.valueOf(o.get(UsrProductCategory.T.SEO_KEYWORD_EN.getFld().getCodeSqlField())));
            view.setSeoTitle(String.valueOf(o.get(UsrProductCategory.T.SEO_TITLE_EN.getFld().getCodeSqlField())));
            view.setSeoDescription(String.valueOf(o.get(UsrProductCategory.T.SEO_DESCRIPTION_EN.getFld().getCodeSqlField())));
            Integer id = (Integer) o.get(UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField());
            if (id != null) {
                view.setCategory((String) map.get(id));
            }
            return view;
        }).collect(Collectors.toList());
        return new Page(list, start, limit, count);
    }


}
