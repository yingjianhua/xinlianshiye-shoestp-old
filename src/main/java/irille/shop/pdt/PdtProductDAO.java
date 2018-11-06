package irille.shop.pdt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.pdt.dto.PdtProductView;
import irille.homeAction.pdt.dto.ProductInfoView;
import irille.homeAction.pdt.dto.SpecView;
import irille.homeAction.usr.dto.PdtView;
import irille.pub.Exp;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduPage;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.util.AppConfig;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SEOUtils;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.TranslateFilter;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.sellerAction.SellerAction;
import irille.shop.pdt.Pdt.OState;
import irille.shop.pdt.PdtProduct.T;
import irille.shop.plt.PltConfigDAO;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;
import irille.shop.usr.*;
import irille.view.pdt.PdtCategoriesView;
import irille.view.pdt.PdtProductSaveView;
import irille.view.pdt.PdtProductSpecSaveView;
import irille.view.pdt.PdtYouMayLikeView;
import org.json.JSONException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static irille.core.sys.Sys.OYn.YES;

public class PdtProductDAO {
    public static final Log LOG = new Log(PdtProductDAO.class);

    public static class QueryDetail extends IduOther<Query, PdtProduct> {
        public PdtProduct load(Integer pkey) {
            return PdtProduct.load(PdtProduct.class, pkey);
        }
    }

    /**
     * 产品审核弃审
     * <p>
     * 弃审同时下架产品
     */
    public static PdtProduct verify(boolean verify, Integer pkey) {
        PdtProduct bean = BeanBase.load(PdtProduct.class, pkey);
        bean.stIsVerify(verify);

        if (!verify) {
            bean.stState(OState.OFF);
        }

        bean.upd();
        return bean;
    }

    private static final UsrCartDAO.Query cartQuery = new UsrCartDAO.Query();

    /**
     * @author yingjianhua
     */
    public static class Query extends IduOther<Query, PdtProduct> {
        public List<PdtProduct> listByPurchaseCart(Integer pkey) {
            List<UsrCart> carts = cartQuery.listByPurchase(pkey);
            Map<Integer, PdtProduct> productMap = new HashMap<Integer, PdtProduct>();
            for (UsrCart cart : carts) {
                Integer pid = cart.gtSpec().getProduct();
                if (!productMap.containsKey(pid))
                    productMap.put(pid, cart.gtSpec().gtProduct());
            }
            return languageShift(new ArrayList<PdtProduct>(productMap.values()));//转
        }

        public List<PdtProduct> listBySpec(List<PdtSpec> specs) {
            if (specs.size() == 0) return new ArrayList<>();
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < specs.size(); i++) {
                if (i != 0) b.append(",");
                b.append(specs.get(i).getProduct());
            }
            String where = PdtProduct.T.PKEY.getFld().getCodeSqlField() + " in (" + b.toString() + ")";
            return languageShift(BeanBase.list(PdtProduct.class, where, false));//转
        }
    }

    public static class Ins extends IduIns<Ins, PdtProduct> {


        @Override
        public void before() {
            super.before();
            getB().setUpdateTime(Env.getTranBeginTime());
            try {
                translateUtil.autoTranslate(getB());//转
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static class pageSelect extends IduOther<pageSelect, PdtProduct> {
        private static boolean Debug = true;
        /***
         * 最小库存限制，低于这个数据将不展示于首页
         */
        private static final int MinStockNumber = 0;


        /***
         * 首页新品
         *
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/23 14:38
         */
        public Map getNewProducts(IduPage page) {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    T.PKEY,
                    T.NAME,
                    T.PICTURE,
                    T.CUR_PRICE
            )
                    .gt(T.STOCK, this.MinStockNumber)
//                    .eqAutoAnd(T.IS_NEW, YES.getLine().getKey())
                    .eqAutoAnd(T.STATE, Pdt.OState.ON.getLine().getKey())
                    .eqAutoAnd(T.IS_VERIFY, YES.getLine().getKey())
                    .page(page.getStart(), page.getLimit())
                    .desc(T.UPDATE_TIME)
                    .desc(T.MY_ORDER);
            if (page.getWhere() != null)
                sql.eqAutoAnd(T.CATEGORY, Integer.valueOf(page.getWhere()), number -> {
                    return page.getWhere() != null && page.getWhere().length() > 0 && !page.getWhere().equalsIgnoreCase("-1");
                });
            Map result = new HashMap();
            List<Object[]> list = BeanBase.list(sql.buildSql(), sql.getParms());
            result.put("items", SetBeans.setList(list.stream().map(o -> {
                return sql.castMapAddFld(o);
            }).collect(Collectors.toList()), PdtYouMayLikeView.class));
            result.put("total", sql.castLong(BeanBase.queryOneRow(sql.buildCountSql(), sql.getParms())));
            return result;
        }


        /***
         * 首页新品   返回List方便jsp迭代
         *
         * @author lijie@shoestp.cn
         * @param
         * @return List
         * @date 2018/7/23 14:38
         */
        public List getNewProductsList(IduPage page) throws JSONException {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    T.PKEY,
                    T.NAME,
                    T.PICTURE,
                    T.CUR_PRICE
            )
                    .gt(T.STOCK, this.MinStockNumber)
//                    .eqAutoAnd(T.IS_NEW, YES.getLine().getKey())
                    .eqAutoAnd(T.STATE, Pdt.OState.ON.getLine().getKey())
                    .eqAutoAnd(T.IS_VERIFY, YES.getLine().getKey())
                    .page(page.getStart(), page.getLimit())
                    .desc(T.UPDATE_TIME)
                    .desc(T.MY_ORDER);
            if (page.getWhere() != null)
                sql.eqAutoAnd(T.CATEGORY, Integer.valueOf(page.getWhere()), number -> {
                    return page.getWhere() != null && page.getWhere().length() > 0;
                });
            List result = BeanBase.list(sql.buildSql(), sql.getParms()).stream().map(objects ->
            {
                return sql.castMapAddFld(objects);
            }).collect(Collectors.toList());
            return SetBeans.setList(result, PdtYouMayLikeView.class);
        }

        /***
         * 所有商品列表页
         * 首页，根据产品分类查询产品列表的数据
         *
         * @author lijie@shoestp.cn
         * @param page 分页参数 flds 排序参数 order true desc false asc category 产品分类
         * @return
         * @date 2018/7/23 11:31
         */
        public Map getProductsIndexByCategory(IduPage page, String[] flds, boolean order, int category, String spec, String onlyFld, String keyword) {
            PdtProductView pdtProductView = new PdtProductView();
            pdtProductView
                    .setPage(page)
                    .setFlds(flds)
                    .setOrder(order)
                    .setCategory(category)
                    .setSpec(spec)
                    .setKeyword(keyword)
            ;
            if (onlyFld != null) {
                try {
                    pdtProductView.setOnlyFld(PdtProductView.onlyFld.valueOf(onlyFld));
                } catch (Exception e) {

                }
            }
            return getProducts(pdtProductView);
        }

        public Map getProductsIndexByCategory(IduPage page, String[] flds, boolean order, int category, String spec, String onlyFld, String keyword, Integer type) {
            PdtProductView pdtProductView = new PdtProductView();
            pdtProductView
                    .setPage(page)
                    .setFlds(flds)
                    .setOrder(order)
                    .setCategory(category)
                    .setSpec(spec)
                    .setKeyword(keyword)
            ;
            if (type == null) type = 0;
            switch (type) {
                case 0:
                    pdtProductView.setProductType(Pdt.OProductType.GENERAL);
                    break;
                case 1:
                    pdtProductView.setProductType(Pdt.OProductType.GROUP);
                    break;
            }
            if (onlyFld != null) {
                try {
                    pdtProductView.setOnlyFld(PdtProductView.onlyFld.valueOf(onlyFld));
                } catch (Exception e) {

                }
            }
            return getProducts(pdtProductView);
        }

        /**
         * @Description: 获取单个商品简介, 返回字段与你可能喜欢一致, 暂用这个
         * @author lijie@shoestp.cn
         * @date 2018/8/16 10:55
         */
        public PdtYouMayLikeView getProduct(int id) {
            FormaterSql sql = FormaterSql.build(this);
            sql.select(
                    T.PKEY, T.NAME, T.CUR_PRICE, T.PICTURE
            ).from(T.NAME)
                    .eqAutoAnd(T.PKEY, id)
                    .eqAutoAnd(T.IS_VERIFY, Sys.OYn.YES.getLine().getKey())
                    .neq(T.PRODUCT_TYPE, Pdt.OProductType.GROUP);
            Object[] objects = BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms());
            if (objects != null) {
                Map map = sql.castMapAddFld(objects);
                PdtYouMayLikeView view = SetBeans.set(map, PdtYouMayLikeView.class);
                return view;
            }
            return new PdtYouMayLikeView();
        }

        /**
         * @Description: 返回商品平均分  AVG严重拖累性能,后期缓存
         * @author lijie@shoestp.cn
         * @date 2018/8/16 14:30
         */
        public long getProductAvgById(long id) {
            return FormaterSql.castLong(BeanBase.queryOneRowIsNull("SELECT avg(product_satisfaction) FROM `pdt_comment` where product=?", id));

        }

        /**
         * @Description: 获取商品节点下方所有子节点
         * @author lijie@shoestp.cn
         * @date 2018/8/21 18:05
         */
        public List getCatsNodeByCatId(int id) {
            if (id < 1) {
                return null;
            }
            FormaterSql sql = FormaterSql.build();
            sql.select(PdtCat.T.PKEY).eq(PdtCat.T.CATEGORY_UP);
            List<Object[]> l = new ArrayList();
            List<Object[]> tList = BeanBase.list(sql.buildSql(), id);
            List ttList = new ArrayList();
            l.addAll(tList);
            List result = new ArrayList();
            result.add(id);
            do {
                ttList.clear();
                for (Object[] objects : tList) {
                    ttList.addAll(BeanBase.list(sql.buildSql(), sql.castLong(objects)));
                }
                l.addAll(ttList);
                tList.clear();
                tList.addAll(ttList);
                if (ttList.size() < 1) break;
            } while (true);
            sql.clean();
            for (Object[] o : l) {
                Long ll = sql.castLong(o);
                if (ll > 0) {
                    result.add(ll);
                }
            }
            return result;
        }

        /**
         * 获取产品列表
         *
         * @author lijie@shoestp.cn
         * @Description:
         * @date 2018/8/3 9:22
         */
        public Map getProducts(PdtProductView pdtProductView) {
            FormaterSql sql = FormaterSql.build(this);
            List<Serializable> parmList = new ArrayList<>();
            if (pdtProductView.getCategory() > -1) {
                parmList.addAll(getCatsNodeByCatId(pdtProductView.getCategory()));
                sql.in(T.CATEGORY, parmList.size());
            }
            sql.select(
                    T.PKEY,
                    T.NAME,
                    T.PICTURE,
                    T.CUR_PRICE,
                    T.Favorite_Count
            ).from(T.PKEY)
                    .gtAutoAnd(T.STOCK)
                    .eqAutoAnd(T.STATE)
                    .page(pdtProductView.getPage())
                    .eqAutoAnd(T.IS_VERIFY);

            parmList.add(MinStockNumber);
            parmList.add(Pdt.OState.ON.getLine().getKey());
            parmList.add(Sys.OYn.YES.getLine().getKey());
            //TODO
            /**
             * @Description: 暂时折中方案, 性能差
             * @author lijie@shoestp.cn
             * @date 2018/8/23 14:42
             */
            if (pdtProductView.getSpec() != null) {
                for (String string : pdtProductView.getSpec().split(",")) {
                    if (string.length() > 1) {
                        sql.Andwhere("find_in_set( ?, norm_attr )");
                        parmList.add(string);
                    }
                }
            }
            /**
             * 根据供应商Id 查询
             * @author lijie@shoestp.cn
             * @Description:
             * @date 2018/8/3 9:26
             */
            if (pdtProductView.getSupplierId() > -1) {
                sql.and().eq(T.SUPPLIER);
                parmList.add(pdtProductView.getSupplierId());
            }
            /**
             * 根据张伟，添加商品标题关键词查询
             * @author lijie@shoestp.cn
             * @Description:
             * @date 2018/8/6 16:45
             */
            if (pdtProductView.getKeyword() != null && pdtProductView.getKeyword().length() > 0) {
                try {
                    parmList.add("%" + URLDecoder.decode(pdtProductView.getKeyword(), "utf-8") + "%");
                    sql.and().like(T.NAME);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            /**
             * @Description: 根据李逸超要求添加 PRODUCT_TYPE字段判断
             * @author lijie@shoestp.cn
             * @date 2018/8/20 11:08
             */
            if (pdtProductView.getProductType() != null) {
                sql.eqAutoAnd(T.PRODUCT_TYPE);
                parmList.add(pdtProductView.getProductType().getLine().getKey());
            }

            if (pdtProductView.getOnlyFld() != null) {
                switch (pdtProductView.getOnlyFld()) {
                    case Hot: {
                        sql.and().eq(T.IS_HOT);
                        parmList.add(Sys.OYn.YES.getLine().getKey());
                    }
                    break;
                    case New: {
                        sql.and().eq(PdtProduct.T.IS_NEW);
                        parmList.add(Sys.OYn.YES.getLine().getKey());
                        sql.desc(T.UPDATE_TIME);
                    }
                    break;
                }
            }
            //缺少权重计算，待优化  策略模式
            if (pdtProductView.getFlds() != null) {
                for (String fld : pdtProductView.getFlds()) {
                    try {
                        ProductsIndexOrderByType orderByType = ProductsIndexOrderByType.valueOf(URLDecoder.decode(fld, "utf-8").replace(" ", ""));
                        if (orderByType != null) {
                            if (pdtProductView.isOrder()) {
                                sql.desc(orderByType.getFld());
                            } else {
                                sql.asc(orderByType.getFld());
                            }
                            String pageWhere = pdtProductView.getPage().getWhere();
                            if (pageWhere != null) {
                                Pattern pattern = Pattern.compile("([0-9]{0,})-([0-9]{0,})");
                                Matcher matcher = pattern.matcher(pageWhere);
                                if (matcher.matches()) {
                                    if (matcher.group(1).length() > 0) {
                                        sql
                                                .gteqAutoAnd(T.CUR_PRICE);
                                        parmList.add(matcher.group(1));
                                    }
                                    if (matcher.group(2).length() > 0) {
                                        sql.lteqAutoAnd(T.CUR_PRICE);
                                        parmList.add(matcher.group(2));
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Map result = new HashMap();
            List list = BeanBase.list(sql.buildSql(), sql.getParms(parmList)).stream().map(objects -> {
                return sql.castMapAddFld(objects);
            }).collect(Collectors.toList());
            result.put("items", SetBeans.setList(list, PdtYouMayLikeView.class));
            result.put("total", sql.castLong(BeanBase.queryOneRow(sql.buildCountSql(), sql.getParms(parmList))));
            if (pdtProductView.getCategory() > -1) {
                result.put("breadcrumbnav", getBreadcrumbNav(pdtProductView.getCategory()));
            }
            return result;
        }

        /***
         * 所有商品列表页 热榜
         * div #what_hot
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/23 14:47
         */
        public Map getProductsIndexHot(IduPage page) throws JSONException {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    T.PKEY,
                    T.NAME,
                    T.PICTURE,
                    T.CUR_PRICE
            )
                    .gtAutoAnd(T.STOCK, this.MinStockNumber)
                    .eqAutoAnd(T.IS_HOT, YES.getLine().getKey())
                    .eqAutoAnd(T.IS_VERIFY, Sys.OYn.YES.getLine().getKey())
                    .page(page.getStart(), page.getLimit())
                    .desc(T.MY_ORDER);
            Map map = new HashMap();
            List list = BeanBase.list(sql.buildSql(), sql.getParms()).stream().map(objects -> {
                return sql.castMapAddFld(objects);
            }).collect(Collectors.toList());
            map.put("items", SetBeans.setList(list, PdtYouMayLikeView.class));
            return map;
        }


        /**
         * 获取板块名称
         *
         * @author lijie@shoestp.cn
         * @Description:
         * @date 2018/8/6 19:25
         */
        public String getCategorieNameById(long id) {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.select(
                    PdtCat.T.NAME
            ).eq(PdtCat.T.PKEY);
            return translateUtil.getAutoTranslate(sql.castString(BeanBase.queryOneRowIsNull(sql.buildSql(), id)), HomeAction.curLanguage());
        }

        /**
         * @Description: 你可能喜欢功能 ,根据商品Id返回
         * @author lijie@shoestp.cn
         * @date 2018/8/14 10:57
         */
        public List getYouMayLikeByPdtId(int id) {
            String sql = "SELECT\n" +
                    "\tt1.pkey,t1.`name`,t1.cur_price,t1.picture \n" +
                    "FROM\n" +
                    "\tpdt_product AS t1\n" +
                    "\tJOIN (\n" +
                    "SELECT\n" +
                    "\tROUND(\n" +
                    "\tRAND( ) * ( ( SELECT MAX( pkey ) FROM `pdt_product` ) - ( SELECT MIN( pkey ) FROM `pdt_product` ) ) + ( SELECT MIN( pkey ) FROM `pdt_product` ) \n" +
                    "\t) AS pkey \n" +
                    "\t) AS t2 \n" +
                    "WHERE\n" +
                    "\tt1.pkey >= t2.pkey and t1.category=(select category FROM pdt_product as t3  where t3.pkey=?)\n" +
                    "         And t1.is_verify=1\n" +
                    "ORDER BY\n" +
                    "\tt1.pkey \n" +
                    "\tLIMIT 5;";
            FormaterSql formaterSql = FormaterSql.build(this);
            formaterSql.select(T.PKEY, T.NAME, T.CUR_PRICE, T.PICTURE);
            List result = new ArrayList();
            List<Map> list = BeanBase.list(sql, id).stream().map(objects -> {
                return formaterSql.castMapAddFld(objects);
            }).collect(Collectors.toList());
            for (Map map : list) {
                result.add(SetBeans.set(map, PdtYouMayLikeView.class));
            }
            return result;
        }

        /***
         SELECT
         t1.pkey,t1.`name`,t1.cur_price,t1.picture
         FROM
         pdt_product AS t1
         JOIN (
         SELECT
         ROUND(
         RAND( ) * ( ( SELECT MAX( pkey ) FROM `pdt_product` ) - ( SELECT MIN( pkey ) FROM `pdt_product` ) ) + ( SELECT MIN( pkey ) FROM `pdt_product` )
         ) AS pkey
         ) AS t2
         WHERE
         t1.pkey >= t2.pkey AND t1.category = ( SELECT category FROM pdt_product AS t3 WHERE t3.pkey =? )
         And t1.is_verify=1
         ORDER BY
         t1.pkey
         LIMIT 5;
         *
         */
        public List getYouMayLike(IduPage iduPage, int cat) {
            List result = new ArrayList();
            String pkeys = getYouMayLikeProd(cat);
            SQL sql = new SQL() {{
                SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CUR_PRICE, PdtProduct.T.PICTURE);
                FROM(PdtProduct.class);
                WHERE(PdtProduct.T.PKEY, " in(" + pkeys + ") ");
            }};
            List<PdtProduct> prodList = irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class);
            return prodList.stream().map(bean -> new PdtYouMayLikeView() {{
                setRewrite(SEOUtils.getPdtProductTitle(bean.getPkey(), bean.getName()));
                translateUtil.getAutoTranslate(bean, HomeAction.curLanguage());
                setId(bean.getPkey());
                setPrice(bean.getCurPrice());
                setImage(bean.getPicture());
                setName(bean.getName());
            }}).collect(Collectors.toList());

//            List parmList = new ArrayList();
//            String sql = "SELECT\n" +
//                    "\tt1.pkey,t1.`name`,t1.cur_price,t1.picture \n" +
//                    "FROM\n" +
//                    "\tpdt_product AS t1\n" +
//                    "\tJOIN (\n" +
//                    "SELECT\n" +
//                    "\tROUND(\n" +
//                    "\tRAND( ) * ( ( SELECT MAX( pkey ) FROM `pdt_product` ) - ( SELECT MIN( pkey ) FROM `pdt_product` ) ) + ( SELECT MIN( pkey ) FROM `pdt_product` ) \n" +
//                    "\t) AS pkey \n" +
//                    "\t) AS t2 \n" +
//                    "WHERE\n" +
//                    "\tt1.pkey >= t2.pkey and t1.category=?\n" +
//                    "         And t1.is_verify=1 And t1.product_type = 0 \n" +
//                    "ORDER BY\n" +
//                    "\tt1.pkey \n" +
//                    "\tLIMIT 5;";
//            parmList.add(cat);
//            FormaterSql formaterSql = FormaterSql.build(this);
//            formaterSql.select(T.PKEY, T.NAME, T.CUR_PRICE, T.PICTURE);
//            List<Map> list = BeanBase.list(sql, cat).stream().map(objects -> {
//                return formaterSql.castMapAddFld(objects);
//            }).collect(Collectors.toList());
//            for (Map map : list) {
//                result.add(SetBeans.set(map, PdtYouMayLikeView.class));
//            }
            // return result;
        }

        /**
         * 随机获取猜你喜欢的产品Pkey
         *
         * @param cat
         * @return
         */
        public static String getYouMayLikeProd(int cat) {
            SQL sql = new SQL() {{
                SELECT(PdtProduct.T.PKEY);
                FROM(PdtProduct.class);
                WHERE(PdtProduct.T.CATEGORY, "=?", cat);
                WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON.getLine().getKey());
                WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES);
                WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL);
                ORDER_BY(PdtProduct.T.PKEY, " DESC ");
            }};
            String pkeys = "";
            Set<Integer> prodPkeys = new HashSet<Integer>();
            List<PdtProduct> prodPkeyList = irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class);
            if (prodPkeyList.size() > 0) {
                Random rand = new Random();
                for (int i = 0; ; i++) {
                    if (prodPkeys.size() > 4||i>100) {
                        break;
                    }
                    prodPkeys.add(prodPkeyList.get(rand.nextInt(prodPkeyList.size())).getPkey());

                }
                for (Integer pkey : prodPkeys) {
                    if (pkeys.equals("")) {
                        pkeys += pkey;
                    } else {
                        pkeys += "," + pkey;
                    }
                }
            }
            return pkeys;
        }

        /**
         * @Description:获取所有产品分类
         * @author lijie@shoestp.cn
         * @date 2018/7/23 14:10
         */
        public Map getProductsIndexCategories(IduPage iduPage) throws JSONException, JsonProcessingException {
            FormaterSql sql = FormaterSql.build(Debug);
            //查询大类
            sql.select(
                    PdtCat.T.PKEY,
                    PdtCat.T.NAME
            )
                    .Andwhere(PdtCat.T.DELETED + "=0 AND ")
                    .isNull(PdtCat.T.CATEGORY_UP)
                    .page(iduPage.getStart(), iduPage.getLimit());
            List<Object[]> list = BeanBase.list(sql.buildSql());
            List list1 = new ArrayList();
            for (Object[] o : list) {
                PdtCategoriesView pdtCategoriesView = new PdtCategoriesView();
                pdtCategoriesView.setPkey(sql.castLong(o));
                pdtCategoriesView.setName(translateUtil.getLanguage(o[1], HomeAction.curLanguage()));
                sql.cleanWhere()
                        .eq(
                                PdtCat.T.CATEGORY_UP
                        )
                        .Andwhere(PdtCat.T.DELETED + "=0");
                pdtCategoriesView.setItems(sql.castListMap(BeanBase.list(sql.buildSql(), pdtCategoriesView.getPkey())));
                list1.add(pdtCategoriesView);
            }
            Map map = new HashMap();
            map.put("items", list1);

            return map;
        }

        /**
         * @Description: 获取指定分类id下面的子类
         * @author lijie@shoestp.cn
         * @date 2018/7/23 14:10
         */
        public List getProductsCategorieNodesByCategory(IduPage iduPage, long category) throws JSONException {
            FormaterSql sql = FormaterSql.build(Debug);
            //查询大类
            sql.select(
                    PdtCat.T.PKEY,
                    PdtCat.T.NAME
            )
                    .eq(PdtCat.T.CATEGORY_UP)
                    .page(iduPage);
            return sql.castListMap(BeanBase.list(sql.buildSql(), category));
        }

        /**
         * 获取具体规格库存及版本号
         *
         * @author lijie@shoestp.cn
         * @Description:获取具体规格库存及版本号
         * @date 2018/8/8 13:50
         */
        public Map getProductsStocksBySpecId(long pkey) {
            FormaterSql sql = FormaterSql.build(true);
            sql.select(PdtSpec.T.STORE_COUNT, PdtSpec.T.ROW_VERSION).eq(PdtSpec.T.PKEY);
            return sql.castMap(BeanBase.queryOneRow(sql.buildSql(), pkey));
        }

        /***
         * 获取指定分类的产品总数
         * @return
         */
        public Long getCatPdtCount(int category) {
            FormaterSql sql = FormaterSql.build(Debug);
            List parmList = new ArrayList();
            sql.from(T.PKEY);
            if (category > -1) {
                sql.eq(T.CATEGORY);
                parmList.add(category);
            }
            return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms(parmList)));
        }

        /**
         * @Description: 面包屑导航
         * @author lijie@shoestp.cn
         * @date 2018/7/27 14:01
         */
        public List getBreadcrumbNav(long id) {
            FormaterSql sql = FormaterSql.build();
            List nav = new ArrayList();
            Long i = id;
            do {
                sql.clean().select(PdtCat.T.PKEY, PdtCat.T.NAME, PdtCat.T.CATEGORY_UP)
                        .eq(PdtCat.T.PKEY);
                Map map = sql.castMap(BeanBase.queryOneRow(sql.buildSql(), i));
                if (map != null) {
                    Object t = map.get(PdtCat.T.CATEGORY_UP.getFld().getCodeSqlField());
                    nav.add(0, map);
                    if (t == null) {
                        break;
                    }
                    i = Long.valueOf(String.valueOf(t));
                } else {
                    break;
                }
            } while (true);
            return nav;
        }

        /**
         * @Description: 判断用户是否拥有该商品
         * @author lijie@shoestp.cn
         * @date 2018/8/18 11:59
         */
        private boolean isFavorite(int purid, int pdtid) {
            if (purid < 1) {
                return false;
            }
            FormaterSql sql = FormaterSql.build();
            sql.from(UsrFavorites.T.PKEY)
                    .eqAutoAnd(UsrFavorites.T.PURCHASE, purid, s -> {
                        return s.intValue() > 0 ? true : false;
                    })
                    .eqAutoAnd(UsrFavorites.T.PRODUCT, pdtid)
                    .eqAutoAnd(UsrFavorites.T.SHOW_STATE, 1);
            return sql.castLong(BeanBase.queryOneRowIsNull(sql.buildCountSql(), sql.getParms())) > 0 ? true : false;
        }

        /***
         * JSp 产品详情页
         * 根据产品Id获取产品信息
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @throws JSONException
         * @date 2018/7/26 10:00
         */
        public ProductInfoView getProductsById(int id, Sys.OYn is_verify, Pdt.OState state, int purId, PltErate curCurrency) throws JSONException {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.eq(T.IS_VERIFY).and().eq(T.STATE).and().eq(T.PKEY);
            List parmList = new ArrayList();
            parmList.add(is_verify.getLine().getKey());
            parmList.add(state.getLine().getKey());
            parmList.add(id);
            List<PdtProduct> list =
                    BeanBase.list(PdtProduct.class, sql.toWhereString(), false, sql.getParms(parmList));
            if (!list.isEmpty()) {
                PdtProduct pdtProduct = list.get(0);
                ProductInfoView productInfoView = new ProductInfoView();
                UsrSupplier supplier = pdtProduct.gtSupplier();
                productInfoView.setSupId(supplier.getPkey());
                productInfoView.setSupName(SEOUtils.firstUpperCase(translateUtil.getLanguage(supplier.getShowName(), HomeAction.curLanguage())));
                productInfoView.setRewrite(SEOUtils.getPdtProductTitle(pdtProduct.getPkey(), pdtProduct.getName()));
                productInfoView.setLogo(supplier.getLogo());
                productInfoView.setStock(pdtProduct.getStock().intValue());
//                productInfoView.setQty_num(pdtProduct.getStock().intValue());
                productInfoView.setAd(supplier.getAdPhoto());
                if (supplier.getAuthTime() != null) {
                    productInfoView.setAuthTime(getAuthTime(supplier));
                }
                productInfoView.setPdtId(pdtProduct.getPkey());
                productInfoView.setMin_oq(pdtProduct.getMinOq());
                productInfoView.setMax_oq(pdtProduct.getMaxOq());
                productInfoView.setItemCode(pdtProduct.getCode());
                productInfoView.setPdtName(translateUtil.getLanguage(pdtProduct.getName(), HomeAction.curLanguage()));
                productInfoView.setCurrency_symbol(curCurrency.getSymbol());
                productInfoView.setCurrency_unit(curCurrency.getCurName());
                productInfoView.setPrice(PltErateDAO.Query.getTargetPrice(pdtProduct.getCurPrice(), curCurrency.getNowrate()).setScale(2, RoundingMode.HALF_UP));
                productInfoView.setHeight(pdtProduct.getHeight());
                productInfoView.setWidth(pdtProduct.getWidth());
                productInfoView.setLength(pdtProduct.getLength());
                productInfoView.setWeight(pdtProduct.getWeight());
                productInfoView.setDescription(translateUtil.getLanguage(pdtProduct.getDescription(), HomeAction.curLanguage()));//转
                productInfoView.setPdtImg(pdtProduct.getPicture());
                productInfoView.setFavorite_count(pdtProduct.getFavoriteCount());
                productInfoView.setFavorite(isFavorite(purId, id));
                IduPage page = new IduPage();
                page.setStart(1);
                page.setLimit(5);
                productInfoView.setYouMayLike(getYouMayLike(page, pdtProduct.getCategory()));
                //面包屑导航
                productInfoView.setBreadcrumbNav(getBreadcrumbNav(pdtProduct.getCategory()));
                String wheresql = sql.clean().eqAutoAnd(PdtSpec.T.PRODUCT, id).toWhereString();
                List<PdtSpec> pdtSpecList = BeanBase.list(PdtSpec.class, wheresql, false, sql.getParms());
                Map<String, List<SpecView>> colorspecMap = new HashMap();
                Map<String, String[]> colorImageMap = new HashMap();
                //规格总库存
                for (PdtSpec pdtSpec : pdtSpecList) {
                    try {
                        List o = colorspecMap.get(translateUtil.getLanguage(pdtSpec.gtColor().getName(), HomeAction.curLanguage()));
                        if (pdtSpec.getPrice().intValue() < 0) {
                            pdtSpec.setPrice(pdtProduct.getCurPrice());
                        }
                        if (o == null) {
                            List l = new ArrayList();
                            l.add(SpecView.build_GoodsInfoView(pdtSpec, curCurrency));
                            colorspecMap.put(translateUtil.getLanguage(pdtSpec.gtColor().getName(), HomeAction.curLanguage()), l);
                            colorImageMap.put(
                                    translateUtil.getLanguage(pdtSpec.gtColor().getName(), HomeAction.curLanguage()),
                                    pdtSpec.getPics() != null ? Arrays.asList(pdtSpec.getPics().split(",")).stream().filter(s -> {
                                        if (s.length() > 0)
                                            return true;
                                        return false;
                                    }).toArray(String[]::new) : new String[0]
                            );
                        } else {
                            SpecView specView = SpecView.build_GoodsInfoView(pdtSpec, curCurrency);
                            if (specView.getImg() != null && specView.getImg().length() > 1) {
                                o.add(0, specView);
                            } else {
                                o.add(specView);
                            }
                        }
                    } catch (Exp exp) {
                        exp.printLastMessage();
                    }
                }
                productInfoView.setStock(pdtProduct.getStock().intValue());
                productInfoView.setSpec(colorspecMap);
                try {
                    productInfoView.setSpecJson(new ObjectMapper().writeValueAsString(colorspecMap));
                    productInfoView.setColorImageJson(AppConfig.objectMapper.writeValueAsString(colorImageMap));
                    productInfoView.setProductImageJson(AppConfig.objectMapper.writeValueAsString(pdtProduct.getPicture()));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                Map<String, List> map = new HashMap();
                if (pdtProduct.getNormAttr() != null) {
                    for (String s : pdtProduct.getNormAttr().split(",")) {
                        if (s.length() > 0) {
                            if (!s.equalsIgnoreCase("null")) {
                                if (Integer.parseInt(s) > -1) {
                                    try {
                                        PdtAttrLine pdtAttrLine = BeanBase.chk(PdtAttrLine.class, s);
                                        if (pdtAttrLine == null) {
                                            continue;
                                        }
                                        PdtAttr attr = pdtAttrLine.gtMain();
                                        List o = map.get(attr.getName(HomeAction.curLanguage()));
                                        if (o != null) {
                                            o.add(pdtAttrLine.getName(HomeAction.curLanguage()));
                                        } else {
                                            List attrlineList = new ArrayList();

                                            attrlineList.add(translateUtil.getLanguage(pdtAttrLine.getName(), HomeAction.curLanguage()));
                                            map.put(translateUtil.getLanguage(attr.getName(), HomeAction.curLanguage()), attrlineList);
                                        }
                                    } catch (Exp e) {
                                        e.printLastMessage();
                                    }
                                }
                            }
                        }
                    }

                }
                productInfoView.setSpecifications(map);
                //获取评价
                PdtCommentDAO.pageSelect pageSelect = new PdtCommentDAO.pageSelect();
                PdtProductDAO.pageSelect pdtpage = new pageSelect();
                productInfoView.setCommentTotal(pageSelect.getCommentCountByProId(id));
                productInfoView.setSatisfaction(pdtpage.getProductAvgById(id));
                page = new IduPage();
                page.setStart(1);
                page.setLimit(10);
                productInfoView.setComment(pageSelect.getCommentListByProId(page, pdtProduct.getPkey()));
                productInfoView.setSeoKeywords(SEOUtils.firstUpperCase(productInfoView.getPdtName()));
                List list1 = (List) productInfoView.getBreadcrumbNav().stream().map(o -> {
                    return ((HashMap) o).get(PdtCat.T.NAME.getFld().getCode());
                }).collect(Collectors.toList());
                productInfoView.setSeoDescription(SEOUtils.firstUpperCase(productInfoView.getPdtName()) + "," + String.join(",", list1));
                return productInfoView;
            }
            return null;
        }

        public static int getAuthTime(UsrSupplier supplier) {
            int time1 = 0;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            Date authTime2 = supplier.getAuthTime();
            String time = formatter.format(authTime2);
            Calendar c = Calendar.getInstance();
            int y1 = Integer.parseInt(time);
            c.setTime(new Date());
            int y2 = c.get(Calendar.YEAR);
            time1 = y1 - y2 == 0 ? 1 : y1 - y2;
            return time1;
        }

        /***
         * 根据产品Id获取所有所属规格
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/26 10:19
         */
        public List<PdtSpec> getProductsSpecByPdtId(long id) {
            FormaterSql sql = FormaterSql.build(Debug);
            sql.eq(PdtSpec.T.PRODUCT);
            return BeanBase.list(PdtSpec.class, sql.toWhereString(), false, id);
        }

        /**
         * @Description: 面包屑导航 根据商品id
         * @author lijie@shoestp.cn
         * @date 2018/8/20 9:56
         */
        public List getBreadcrumbNavByPdtId(Integer id) {
            FormaterSql sql = FormaterSql.build();
            sql.select(T.CATEGORY).eqAutoAnd(T.PKEY, id);
            System.out.println(BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms()));
            return getBreadcrumbNav(sql.castLong(BeanBase.queryOneRowIsNull(sql.buildSql(), sql.getParms())));
        }


        /***
         * 所有商品列表页
         * 根据什么排列，枚举类
         * @author lijie@shoestp.cn
         * @param
         * @return
         * @date 2018/7/23 15:28
         */
        public enum ProductsIndexOrderByType {
            MostPopular(T.MY_ORDER),
            Sales(T.SALES),
            Favorites(T.Favorite_Count),
            Price(T.CUR_PRICE),
            New(T.IS_NEW),
            Hot(T.IS_HOT);

            private IEnumFld fld;

            ProductsIndexOrderByType(IEnumFld fld) {
                this.fld = fld;
            }

            public IEnumFld getFld() {
                return fld;
            }
        }

        /**
         * @Description: 编辑商品, 获取商品详情
         * @author lijie@shoestp.cn
         * @date 2018/9/25 9:59
         */
        public PdtProductSaveView sellerGetProductById(int id, int supId) throws JSONException {
            return sellerGetProductById(id, supId, pdtSpec -> {
                PdtProductSpecSaveView saveView = new PdtProductSpecSaveView();
                saveView.setId(pdtSpec.getPkey());
                translateUtil.getAutoTranslate(pdtSpec, PltConfigDAO.supplierLanguage(supId));
                saveView.setName(pdtSpec.getKeyName());
                saveView.setSku(pdtSpec.getSku());
                Map map1 = Maps.newLinkedHashMap();
                if (pdtSpec.getPics() != null)
                    for (String s : pdtSpec.getPics().split(",")) {
                        map1.put(s, s);
                    }
                saveView.setPic(map1);
                saveView.setPrice(pdtSpec.getPrice().doubleValue() == -1 ? null : pdtSpec.getPrice().doubleValue());
                saveView.setStock(pdtSpec.getStoreCount() > 0 ? pdtSpec.getStoreCount() : null);
                saveView.setWeight(pdtSpec.getWeight().intValue() == -1 ? null : pdtSpec.getWeight().intValue());
                saveView.setPrice(pdtSpec.getPrice().doubleValue() == -1 ? null : pdtSpec.getPrice().doubleValue());
//                saveView.setStock(pdtSpec.getStoreCount() > 0 ? pdtSpec.getStoreCount() : 0);
//                saveView.setWeight(pdtSpec.getWeight().intValue());
                saveView.setColor(pdtSpec.getColor());
                saveView.setSize(pdtSpec.getSize());
                return saveView;
            });
        }

        /**
         * @Description: 编辑商品, 获取商品详情 基础方法
         * @author lijie@shoestp.cn
         * @date 2018/9/25 9:59
         */
        public PdtProductSaveView sellerGetProductById(int id, int supId, Function<PdtSpec, PdtProductSpecSaveView> function) throws JSONException {
            PdtProductSaveView view = new PdtProductSaveView();
            PdtProduct pdtProduct = PdtProduct.load(PdtProduct.class, id);
            //ToDo 采集功能权限检查
            if (supId != pdtProduct.getSupplier()) {
                view.setFrom(pdtProduct.getPkey());
                return null;
            }
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(pdtProduct.getName());
            JsonObject description = (JsonObject) new JsonParser().parse(pdtProduct.getDescription());
            translateUtil.getAutoTranslate(pdtProduct, PltConfigDAO.supplierLanguage(supId));
            Map map = new HashMap();
            for (Map.Entry<String, JsonElement> elementEntry : jsonObject.entrySet()) {
                map.put(elementEntry.getKey(), elementEntry.getValue().getAsString());
            }
            view.setPdtName(map);
            map = new HashMap();
            for (Map.Entry<String, JsonElement> elementEntry : description.entrySet()) {
                map.put(elementEntry.getKey(), elementEntry.getValue().getAsString());
            }
            view.setDescription(map);
            view.setId(pdtProduct.getPkey());
            view.setAttr(pdtProduct.getNormAttr() == null ? new ArrayList<>() : Stream.of(pdtProduct.getNormAttr().split(",")).map(s -> {
                if (s != null) {
                    if (!s.equalsIgnoreCase("null") && s.length() > 0)
                        return Integer.parseInt(s);
                }
                return -1;
            }).collect(Collectors.toList()));
            view.setProductCat(pdtProduct.getCategory());
            view.setSupplierCat(pdtProduct.getCategoryDiy());
            String[] strings = pdtProduct.getCode().split("-");
            if (strings.length > 1) {
                view.setNumber_left(strings[0]);
                view.setNumber_right(strings[1]);
            } else {
                view.setNumber_left("TOP");
                view.setNumber_right(pdtProduct.getCode());
            }
            view.setSku(pdtProduct.getSku());
            Map pic = Maps.newLinkedHashMap();
            for (String s : pdtProduct.getPicture().split(",")) {
                pic.put(s, s);
            }
            view.setPdtPics(pic);
            view.setPrice(pdtProduct.getCurPrice().doubleValue());
            view.setMktPrice(pdtProduct.getMktPrice() == null ? 0 : pdtProduct.getMktPrice().doubleValue());
            view.setPurPrice(pdtProduct.getPurPrice() == null ? 0 : pdtProduct.getPurPrice().doubleValue());
            view.setMax_oq(pdtProduct.getMaxOq().intValue());
            view.setMin_oq(pdtProduct.getMinOq().intValue());
            view.setSpecColor(Arrays.asList(pdtProduct.getColorAttr().split(",")).stream().map(s -> {
                return Integer.valueOf(s);
            }).collect(Collectors.toSet()));
            view.setSpecSize(Arrays.asList(pdtProduct.getSizeAttr().split(",")).stream().map(s -> {
                if (s == null) {
                    return null;
                }
                if (s.length() < 1) return -1;
                if (s.equalsIgnoreCase("null")) {
                    return -1;
                }
                return Integer.valueOf(s);
            }).collect(Collectors.toSet()));
            view.setWarnStock(pdtProduct.getWarnStock().intValue());
            view.setState(pdtProduct.getState() == Sys.OYn.YES.getLine().getKey() ? true : false);
            view.setSoldInStatus(pdtProduct.gtSoldInTime());
            view.setSoldInTime(Arrays.asList(pdtProduct.getSoldTimeE(), pdtProduct.getSoldTimeB()));
            view.setFreeShipping(pdtProduct.gtIsFreeShipping());
            view.setWeight(pdtProduct.getWeight().intValue());
            view.setHeight(pdtProduct.getHeight().intValue());
            view.setWidth(pdtProduct.getWidth().intValue());
            view.setLength(pdtProduct.getLength().intValue());
            view.setBriefDescription(pdtProduct.getBriefDescription());
            view.setSpec(getSpec(pdtProduct.getPkey(), function));
            return view;
        }

        private List<PdtProductSpecSaveView> getSpec(int pdtId, Function<PdtSpec, PdtProductSpecSaveView> function) {
            FormaterSql sql = FormaterSql.build();
            sql.eq(PdtSpec.T.PRODUCT);
            return PdtSpec.list(PdtSpec.class, sql.toWhereString(), false, pdtId).stream().map(function).collect(Collectors.toList());
        }

        /**
         * @Description: 复制商品, 获取商品详情
         * @author lijie@shoestp.cn
         * @date 2018/9/25 10:00
         */
        public PdtProductSaveView sellerCopyProductById(int id, int supId) throws JSONException {
            PdtProductSaveView result = sellerGetProductById(id, supId, pdtSpec -> {
                PdtProductSpecSaveView saveView = new PdtProductSpecSaveView();
                translateUtil.getAutoTranslate(pdtSpec, PltConfigDAO.supplierLanguage(supId));
                saveView.setName(pdtSpec.getKeyName());
                saveView.setSku(pdtSpec.getSku());
                Map map1 = Maps.newLinkedHashMap();
                if (pdtSpec.getPics() != null)
                    for (String s : pdtSpec.getPics().split(",")) {
                        map1.put(s, s);
                    }
                saveView.setId(-1);
                saveView.setPic(map1);
                saveView.setPrice(pdtSpec.getPrice().doubleValue() == -1 ? null : pdtSpec.getPrice().doubleValue());
                saveView.setStock(pdtSpec.getStoreCount() > 0 ? pdtSpec.getStoreCount() : null);
                saveView.setWeight(pdtSpec.getWeight().intValue() == -1 ? null : pdtSpec.getWeight().intValue());
                saveView.setColor(pdtSpec.getColor());
                saveView.setSize(pdtSpec.getSize());
                return saveView;
            });
            result.setNumber_right("");
            result.setId(-1);
            result.setNumber_left("Top");

            return result;
        }

    }

    /**
     * 商家发布产品
     *
     * @author yingjianhua
     */
    public static class Publish extends IduIns<Publish, PdtProduct> {
        private List<PdtSpec> _lines;

        public List<PdtSpec> getLines() {
            return _lines;
        }

        public void setLines(List<PdtSpec> _lines) {
            this._lines = _lines;
        }

        @Override
        public void before() {
//			super.before();
            PdtProduct dbBean = new PdtProduct().init();
            dbBean.setSupplier(SellerAction.getSupplier().getPkey());
//            dbBean.setSupplier(262);
            dbBean.setMemberLevel(null);
            TranslateFilter translateFilter = new TranslateFilter();
            translateFilter.setLanguageList(new ArrayList<>());
            JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
            translateFilter.setMode(0);
            jsonObject.entrySet().forEach(stringJsonElementEntry -> {
                //如果不相等  添加到清单
                if (
                        stringJsonElementEntry.getValue().getAsString().length() > 0
                ) {
                    translateFilter.getLanguageList().add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                }
            });
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.NAME, //名字
                    T.CATEGORY, //产品类目
                    T.CATEGORY_DIY, //店铺-产品类目
                    T.CODE, //编号
                    T.SKU, //sku
                    T.PICTURE,//产品图片
                    T.NORM_ATTR, //属性
                    T.CUR_PRICE, //价格
                    T.MIN_OQ,//起订量
                    T.MAX_OQ, //最大购买量
                    T.STOCK, //库存
                    T.WARN_STOCK, //警告库存
                    T.STATE,//销售状态上架下架
                    T.SOLD_IN_TIME,//定时上架
                    T.SOLD_TIME_B,//上架时间开始
                    T.SOLD_TIME_E,//上架时间结束
                    T.SIZE_ATTR, //规格属性    PdtSize的pkeys
                    T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
                    T.IS_FREE_SHIPPING, //免运费
                    T.WEIGHT, //重量
                    T.LENGTH, //长
                    T.WIDTH, //宽
                    T.HEIGHT, //高
                    T.BRIEF_DESCRIPTION, //简短描述
                    T.DESCRIPTION //详细介绍
            );
            translateUtil.newAutoTranslate(dbBean, translateFilter);

            getB().setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);

        }

        @Override
        public void valid() {
        }

        @Override
        public void after() {
            super.after();
            for (PdtSpec line : getLines()) {
                translateUtil.autoTranslate(line);
            }
            insLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
        }
    }

    /**
     * 商家发布产品
     *
     * @author yingjianhua
     */
    public static class ExcelLoad extends IduIns<ExcelLoad, PdtProduct> {
        private List<PdtSpec> _lines;

        public List<PdtSpec> getLines() {
            return _lines;
        }

        public void setLines(List<PdtSpec> _lines) {
            this._lines = _lines;
        }

        @Override
        public void before() {
//			super.before();
            PdtProduct dbBean = new PdtProduct().init();
//            dbBean.setSupplier(SellerAction.getSupplier().getPkey());
            dbBean.setSupplier(262);
            dbBean.setMemberLevel(null);
            TranslateFilter translateFilter = new TranslateFilter();
            translateFilter.setLanguageList(new ArrayList<>());
            JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
            translateFilter.setMode(0);
            translateUtil.addFilterToGlobalFilter(PdtSpec.class, PdtSpec.T.KEY_NAME);
            jsonObject.entrySet().forEach(stringJsonElementEntry -> {
                //如果不相等  添加到清单
                if (
                        stringJsonElementEntry.getValue().getAsString().length() > 0
                ) {
                    translateFilter.getLanguageList().add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                }
            });
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.NAME, //名字
                    T.CATEGORY, //产品类目
                    T.CATEGORY_DIY, //店铺-产品类目
                    T.CODE, //编号
                    T.SKU, //sku
                    T.PICTURE,//产品图片
                    T.NORM_ATTR, //属性
                    T.CUR_PRICE, //价格
                    T.MIN_OQ,//起订量
                    T.MAX_OQ, //最大购买量
                    T.STOCK, //库存
                    T.WARN_STOCK, //警告库存
                    T.STATE,//销售状态上架下架
                    T.SOLD_IN_TIME,//定时上架
                    T.SOLD_TIME_B,//上架时间开始
                    T.SOLD_TIME_E,//上架时间结束
                    T.SIZE_ATTR, //规格属性    PdtSize的pkeys
                    T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
                    T.IS_FREE_SHIPPING, //免运费
                    T.WEIGHT, //重量
                    T.LENGTH, //长
                    T.WIDTH, //宽
                    T.HEIGHT, //高
                    T.BRIEF_DESCRIPTION, //简短描述
                    T.DESCRIPTION //详细介绍
            );
            translateUtil.newAutoTranslate(dbBean, translateFilter);

            getB().setUpdateTime(Env.getTranBeginTime());
            setB(dbBean);

        }

        @Override
        public void valid() {
        }

        @Override
        public void after() {
            super.after();
            for (PdtSpec line : getLines()) {
                translateUtil.autoTranslate(line);
            }
            insLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
        }
    }


    public static class Upd extends IduUpd<Upd, PdtProduct> {

        private List<PdtSpec> _lines;

        public List<PdtSpec> getLines() {
            return _lines;
        }

        public void setLines(List<PdtSpec> _lines) {
            this._lines = _lines;
        }

        @Override
        public void before() {

//			super.before();
            PdtProduct dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.NAME, //名字
                    T.CATEGORY, //产品类目
                    T.CATEGORY_DIY, //店铺-产品类目
                    T.CODE, //编号
                    T.SKU, //sku
                    T.PICTURE,//产品图片
                    T.NORM_ATTR, //属性
                    T.CUR_PRICE, //价格
                    T.MIN_OQ,//起订量
                    T.MAX_OQ, //最大购买量
                    T.STOCK, //库存
                    T.WARN_STOCK, //警告库存
                    T.STATE,//销售状态上架下架
                    T.SOLD_IN_TIME,//定时上架
                    T.SOLD_TIME_B,//上架时间开始
                    T.SOLD_TIME_E,//上架时间结束
                    T.SIZE_ATTR, //规格属性    PdtSize的pkeys
                    T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
                    T.IS_FREE_SHIPPING, //免运费
                    T.WEIGHT, //重量
                    T.LENGTH, //长
                    T.WIDTH, //宽
                    T.HEIGHT, //高
                    T.BRIEF_DESCRIPTION, //简短描述
                    T.DESCRIPTION //详细介绍
            );

//			dbBean.setSupplier(SellerAction.getSupplier().getPkey());
//			dbBean.setMemberLevel(null);
            try {
                dbBean.setName(dbBean.getName(HomeAction.curLanguage()));//转
                dbBean.setDescription(dbBean.getDescription(HomeAction.curLanguage()));//转
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            getB().setUpdateTime(Env.getTranBeginTime());
//            translateUtil.autoTranslateSaveOrUpdate(dbBean, true, false, null);
//			updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
//			getB().upd();
        }

        public void updateFavorite(int id) {
            String sql = "update " + FormaterSql.getTableName(T.PKEY) + " set " + T.Favorite_Count.getFld().getCodeSqlField() + "=" + T.Favorite_Count.getFld().getCodeSqlField() + "+1 where " + T.PKEY + " = ?";
            BeanBase.executeUpdate(sql, id);
        }

        public void removeFavorite(int id) {
            String sql = "update " + FormaterSql.getTableName(T.PKEY) + " set " + T.Favorite_Count.getFld().getCodeSqlField() + "=" + T.Favorite_Count.getFld().getCodeSqlField() + "-1 where " + T.PKEY + " = ?";
            BeanBase.executeUpdate(sql, id);
        }

        @Override
        public void after() {
            super.after();
            for (PdtSpec line : getLines()) {
                line.setProduct(getB().getPkey());

                line.setKeyName(line.gtColor().getName() + "+" + line.gtSize().getName());
                line.setMarkup(null);

                line.setMarkup(BigDecimal.ZERO);
            }
            updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
        }
    }


    /**
     * xiayan
     *
     * @author xinlian
     */
    public static class Upd1 extends IduUpd<Upd1, PdtProduct> {

        private List<PdtSpec> _lines;

        public List<PdtSpec> getLines() {
            return _lines;
        }

        public void setLines(List<PdtSpec> _lines) {
            this._lines = _lines;
        }

        @Override
        public void before() {

//			super.before();
            PdtProduct dbBean = loadThisBeanAndLock();
            PropertyUtils.copyProperties(dbBean, getB(),
                    T.NAME, //名字
                    T.CATEGORY, //产品类目
                    T.CATEGORY_DIY, //店铺-产品类目
                    T.CODE, //编号
                    T.SKU, //sku
                    T.PICTURE,//产品图片
                    T.NORM_ATTR, //属性
                    T.CUR_PRICE, //价格
                    T.MIN_OQ,//起订量
                    T.MAX_OQ, //最大购买量
                    T.STOCK, //库存
                    T.WARN_STOCK, //警告库存
                    T.STATE,//销售状态上架下架
                    T.SOLD_IN_TIME,//定时上架
                    T.SOLD_TIME_B,//上架时间开始
                    T.SOLD_TIME_E,//上架时间结束
                    T.SIZE_ATTR, //规格属性    PdtSize的pkeys
                    T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
                    T.IS_FREE_SHIPPING, //免运费
                    T.WEIGHT, //重量
                    T.LENGTH, //长
                    T.WIDTH, //宽
                    T.HEIGHT, //高
                    T.BRIEF_DESCRIPTION, //简短描述
                    T.DESCRIPTION //详细介绍
            );

//			dbBean.setSupplier(SellerAction.getSupplier().getPkey());
//			dbBean.setMemberLevel(null);
            setB(dbBean);
            getB().setUpdateTime(Env.getTranBeginTime());
//			updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());
//			getB().upd();
        }

    }

    /**
     * xiayan
     *
     * @author xinlian
     */
    public static class Upd2 extends IduUpd<Upd2, PdtProduct> {

        private List<PdtSpec> _lines;

        public List<PdtSpec> getLines() {
            return _lines;
        }

        public void setLines(List<PdtSpec> _lines) {
            this._lines = _lines;
        }

        @Override
        public void before() {
            PdtProduct dbBean = load(getB().getPkey());
            TranslateFilter translateFilter = new TranslateFilter();
            translateFilter.setLanguageList(new ArrayList<>());
            JsonObject dbJson = new JsonParser().parse(dbBean.getName()).getAsJsonObject();
            JsonObject jsonObject = new JsonParser().parse(getB().getName()).getAsJsonObject();
            jsonObject.addProperty(FldLanguage.Language.en.name(), SEOUtils.firstUpperCase(jsonObject.get(FldLanguage.Language.en.name()).getAsString()));
            if (
                    dbJson.get(FldLanguage.Language.en.name()).getAsString().hashCode()
                            !=
                            jsonObject.get(FldLanguage.Language.en.name()).getAsString().hashCode()) {
                translateFilter.setMode(0);
                //基准发生修改 用黑名单模式,修改的字段不翻译
                jsonObject.entrySet().forEach(stringJsonElementEntry -> {
                    //如果不相等  添加到清单
                    if (
                            dbJson.get(stringJsonElementEntry.getKey()).getAsString().hashCode()
                                    !=
                                    (stringJsonElementEntry.getValue().getAsString()).hashCode()
                    ) {
                        translateFilter.getLanguageList().add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                    }
                });
            } else {
                translateFilter.setMode(2);
                jsonObject.entrySet().forEach(stringJsonElementEntry -> {
                    if (stringJsonElementEntry.getValue().getAsString().length() < 1) {
                        translateFilter.getLanguageList().add(FldLanguage.Language.valueOf(stringJsonElementEntry.getKey()));
                    }
                });
            }

            PropertyUtils.copyProperties(dbBean, getB(),
                    T.NAME, //名字
                    T.CATEGORY, //产品类目
                    T.CATEGORY_DIY, //店铺-产品类目
                    T.CODE, //编号
                    T.SKU, //sku
                    T.PICTURE,//产品图片
                    T.NORM_ATTR, //属性
                    T.CUR_PRICE, //价格
                    T.MIN_OQ,//起订量
                    T.MAX_OQ, //最大购买量
                    T.STOCK, //库存
                    T.WARN_STOCK, //警告库存
                    T.STATE,//销售状态上架下架
                    T.SOLD_IN_TIME,//定时上架
                    T.SOLD_TIME_B,//上架时间开始
                    T.SOLD_TIME_E,//上架时间结束
                    T.SIZE_ATTR, //规格属性    PdtSize的pkeys
                    T.COLOR_ATTR, //颜色属性   PdtColor的pkeys
                    T.IS_FREE_SHIPPING, //免运费
                    T.WEIGHT, //重量
                    T.LENGTH, //长
                    T.WIDTH, //宽
                    T.HEIGHT, //高
                    T.BRIEF_DESCRIPTION, //简短描述,
                    T.DESCRIPTION //简短描述
            );
            setB(translateUtil.newAutoTranslate(dbBean, translateFilter));
            getLines().forEach(pdtSpec -> {
                pdtSpec.setRowVersion((short) 0);
                translateUtil.autoTranslate(pdtSpec);
            });
            updLine(getB(), getLines(), PdtSpec.T.PRODUCT.getFld());

        }

        @Override
        public void valid() {
        }
    }


    public static class DelDetails extends IduOther<DelDetails, PdtProduct> {

        public void before() {
            getB().setState(Pdt.OState.DELETE.getLine().getKey());
        }

        public void run() {
            getB().upd();
        }

    }

    /**
     * xiayan
     * List<PdtProduct> 查询时多国语言转换
     *
     * @param _productList
     * @return
     */
    public static List<PdtProduct> languageShift(List<PdtProduct> _productList) {//转
        for (int i = 0; i < _productList.size(); i++) {
            try {
                _productList.get(i).setName(_productList.get(i).getName(HomeAction.curLanguage()));
                _productList.get(i).setDescription(_productList.get(i).getDescription(HomeAction.curLanguage()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return _productList;
    }

    public static class Select extends IduOther<Select, PdtProduct> {
        /**
         * 获取店铺产品页产品(条件:/对应商家/上架/通过审核/普通及采集产品
         * (搜索条件:/页码/店铺分类)
         * (排序条件)
         *
         * @param lang      语言
         * @param supplier  供应商pkey
         * @param start     查询起始位置
         * @param limit     查询记录数
         * @param cat       商家产品分类
         * @param sort      排序依据		1->热卖	2->收藏数 3->新品 4->产品价格 默认审核时间
         * @param orderType 排序方式 	1->ASC 默认->DESC
         * @return
         */
        public static Map getProductBySup(FldLanguage.Language lang, Serializable supplier, Integer start, Integer limit, Integer cat, Integer sort, Integer orderType) {
            Map map = new HashMap();
            SQL sql = new SQL() {{
                SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE, T.IS_HOT)
                        .FROM(PdtProduct.class)
                        .WHERE(T.SUPPLIER, "=?", supplier)
                        .WHERE(T.STATE, "=?", Pdt.OState.ON)
                        .WHERE(T.IS_VERIFY, "=?", Sys.OYn.YES)
                        .WHERE(T.PRODUCT_TYPE, "<>?", Pdt.OProductType.GROUP);
            }};
            if (cat != -1) {
                sql.WHERE(T.CATEGORY_DIY, "in(" + UsrProductCategoryDAO.Sellect.getAllChild(lang, supplier, cat) + ")");
            }
            map.put("pageAll", Math.ceil((double) irille.pub.bean.Query.sql(sql).queryCount() / limit));
            String rankingBasis = " DESC ";
            if (orderType == 1) {
                rankingBasis = " ASC ";
            }
            switch (sort) {
                case 1:
                    sql.ORDER_BY(T.SALES, rankingBasis);
                    break;
                case 2:
                    sql.ORDER_BY(T.Favorite_Count, rankingBasis);
                    break;
                case 3:
                    sql.ORDER_BY(T.IS_NEW, rankingBasis);
                    break;
                case 4:
                    sql.ORDER_BY(T.CUR_PRICE, rankingBasis);
                    break;
                default:
                    sql.ORDER_BY(T.IS_HOT, rankingBasis.trim().toLowerCase().equals("desc") ? " ASC " : " DESC ");
                    break;
            }
            sql.ORDER_BY(T.PKEY, rankingBasis);
            sql.LIMIT(start, limit);
            map.put("items", irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class)
                    .stream()
                    .map(bean -> new PdtView() {{
                        String name = bean.getName();
                        translateUtil.getAutoTranslate(bean, lang);
                        setPdt(bean);
                        setRewrite(bean.getPkey(), name);
                    }}).collect(Collectors.toList()));
            return map;
        }

        /**
         * 获取店铺首页产品(条件:/对应商家/新品/上架/通过审核/首页显示/普通产品
         */
        public static List<PdtView> getIndexProduct(Integer supplier, FldLanguage.Language lang) {
            SQL sql = new SQL() {{
                SELECT(T.PKEY, T.PICTURE, T.NAME, T.CUR_PRICE)
                        .FROM(PdtProduct.class)
                        .WHERE(T.SUPPLIER, "=?", supplier)
                        .WHERE(T.IS_NEW, "=?", Sys.OYn.YES)
                        .WHERE(T.STATE, "=?", Pdt.OState.ON)
                        .WHERE(T.IS_INDEX, "=?", Sys.OYn.YES)
                        .WHERE(T.IS_VERIFY, "=?", Sys.OYn.YES)
                        .WHERE(T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
                        .ORDER_BY(T.VERIFY_TIME, "DESC")
                        .LIMIT(0, 8);
            }};

            return irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class)
                    .stream()
                    .map(bean -> new PdtView() {{
                        String name = bean.getName();
                        translateUtil.getAutoTranslate(bean, lang);
                        setPdt(bean);
                        setRewrite(bean.getPkey(), name);
                    }}).collect(Collectors.toList());

        }
    }

}
