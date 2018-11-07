package irille.Dao;

import irille.core.sys.Sys;
import irille.homeAction.pdt.dto.PdtProductView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduPage;
import irille.pub.tb.IEnumFld;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SEOUtils;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.Usr;
import irille.shop.usr.UsrProductCategory;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;
import irille.view.pdt.PdtProductBaseInfoView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static irille.core.sys.Sys.OYn.YES;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 14:47
 */
public class PdtProductDao {
    /***
     * 首页新品
     *
     * @author lijie@shoestp.cn
     * @param
     * @return List
     * @date 2018/7/23 14:38
     */
    public List getNewProductsList(IduPage page) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE
        )
                .FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(PdtProduct.T.IS_VERIFY, "=?", YES)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
                .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
                .LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY)
                .ORDER_BY(
                        PdtProduct.T.UPDATE_TIME, "desc").ORDER_BY(PdtProduct.T.MY_ORDER, "desc")
                .limit(page.getStart(), page.getLimit())

        ;

        if (page.getWhere() != null) {
            if (page.getWhere() != null && page.getWhere().length() > 0) {
                query.WHERE(PdtProduct.T.CATEGORY, "=?", page.getWhere());
            }
        }
        return query.queryMaps();
    }


    /***
     *  商品列表页 热榜
     * div #what_hot
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/23 14:47
     */
    public List<Map> getProductsIndexHot(IduPage page) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE
        ).FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.IS_HOT, "=?", YES)
                .WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
                .LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY)
                .limit(page.getStart(), page.getLimit())
                .ORDER_BY(PdtProduct.T.MY_ORDER, "desc")
        ;
        return query.queryMaps();
    }

    public Map getProductList(PdtProductView pdtProductView) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE,
                PdtProduct.T.Favorite_Count
        ).FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(PdtProduct.T.IS_VERIFY, "=?", YES)
                .limit(pdtProductView.getPage().getStart(), pdtProductView.getPage().getLimit());
        if (pdtProductView.getCategory() > -1) {
            query.WHERE(PdtProduct.T.CATEGORY, "in(" + String.join(",", getCatsNodeByCatId(pdtProductView.getCategory())) + ")");
        }
        //TODO
        /**
         * @Description: 暂时折中方案, 性能差
         * @author lijie@shoestp.cn
         * @date 2018/8/23 14:42
         */
        if (pdtProductView.getSpec() != null) {
            for (String string : pdtProductView.getSpec().split(",")) {
                if (string.length() > 1) {
                    query.WHERE("find_in_set( ?, norm_attr )", string);
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
            query.WHERE(PdtProduct.T.SUPPLIER, "=?", pdtProductView.getSupplierId());
        }
        /**
         * 根据张伟，添加商品标题关键词查询
         * @author lijie@shoestp.cn
         * @Description:
         * @date 2018/8/6 16:45
         */
        if (pdtProductView.getKeyword() != null && pdtProductView.getKeyword().length() > 0) {
            try {
                query.WHERE(PdtProduct.T.NAME, "%" + URLDecoder.decode(pdtProductView.getKeyword(), "utf-8") + "%");
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
            query.WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", pdtProductView.getProductType());
        }

        if (pdtProductView.getOnlyFld() != null) {
            switch (pdtProductView.getOnlyFld()) {
                case Hot: {
                    query.WHERE(PdtProduct.T.IS_HOT, "=?", Sys.OYn.YES);
                }
                break;
                case New: {
                    query.ORDER_BY(PdtProduct.T.UPDATE_TIME, "desc");
                    query.ORDER_BY(PdtProduct.T.MY_ORDER, "desc");
                }
                break;
            }
        }
        //缺少权重计算，待优化  策略模式
        if (pdtProductView.getFlds() != null) {
            for (String fld : pdtProductView.getFlds()) {
                try {
                    PdtProduct.ProductsIndexOrderByType orderByType = PdtProduct.ProductsIndexOrderByType.valueOf(URLDecoder.decode(fld, "utf-8").replace(" ", ""));
                    if (orderByType != null) {
                        if (pdtProductView.isOrder()) {
                            for (IEnumFld iEnumFld : orderByType.getFld()) {
                                query.ORDER_BY(iEnumFld, "desc");
                            }
                        } else {
                            for (IEnumFld iEnumFld : orderByType.getFld()) {
                                query.ORDER_BY(iEnumFld, "asc");
                            }
                        }
                        String pageWhere = pdtProductView.getPage().getWhere();
                        if (pageWhere != null) {
                            Pattern pattern = Pattern.compile("([0-9]{0,})-([0-9]{0,})");
                            Matcher matcher = pattern.matcher(pageWhere);
                            if (matcher.matches()) {
                                if (matcher.group(1).length() > 0) {
                                    query.WHERE(PdtProduct.T.CUR_PRICE, ">?", matcher.group(1));
                                }
                                if (matcher.group(2).length() > 0) {
                                    query.WHERE(PdtProduct.T.CUR_PRICE, "<?", matcher.group(2));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        query.WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR);
        query.LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, PdtProduct.T.SUPPLIER);
        Map result = new HashMap();
        List<Map> list = query.queryMaps();
        list = list.stream().map(o -> {
            o.put("rewrite", SEOUtils.getPdtProductTitle(Integer.parseInt(String.valueOf(o.get("pkey"))),String.valueOf(o.get("name"))));
            return o;
        }).collect(Collectors.toList());
        result.put("items", SetBeans.setList(list, PdtProductBaseInfoView.class));
        result.put("total", query.queryCount());
        if (pdtProductView.getCategory() > -1) {
            result.put("breadcrumbnav", getBreadcrumbNav(pdtProductView.getCategory()));
        }
        return result;

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

    public Page getProductListManage(String name, String number, Integer supplierId, int cat, int start, int limit) {
        BeanQuery q = Query
                .SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CODE, PdtProduct.T.CUR_PRICE, PdtProduct.T.PICTURE, PdtProduct.T.UPDATE_TIME)
                .SELECT(PdtCat.T.NAME, "category")
                .SELECT(UsrProductCategory.T.NAME, "categoryDiy")
                .FROM(PdtProduct.class)
                .LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY)
                .LEFT_JOIN(UsrProductCategory.class, PdtProduct.T.CATEGORY_DIY, UsrProductCategory.T.PKEY)
                .WHERE(name != null && name.length() > 0, PdtProduct.T.NAME, "like ?", "%" + name + "%")
                .WHERE(number != null && number.length() > 0, PdtProduct.T.CODE, "like ?", "%" + number + "%")
                .WHERE(PdtProduct.T.SUPPLIER, "=?", supplierId)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, " <> ?", Pdt.OProductType.GROUP.getLine().getKey())
                .WHERE(PdtProduct.T.STATE, "<>2").ORDER_BY(PdtProduct.T.UPDATE_TIME, "desc");
        if (cat > 0) {
            List list = (List) getCatsNodeByCatId(cat).stream().map(o -> {
                return String.valueOf(o);
            }).collect(Collectors.toList());
            q.WHERE(cat != 0, PdtProduct.T.CATEGORY, "in (" + String.join(",", list) + ")");
        }
        Integer totalCount = q.queryCount();
        start = (start - 1 > -1 ? start - 1 : 0);
        q.limit(start * limit, limit).queryMaps();
        List<Map> list = q.limit(start * limit, limit).queryMaps();
        Page page = new Page(list.stream().map(o -> {
            o.put("category", translateUtil.getLanguage(o.get("category"), PltConfigDAO.supplierLanguage(supplierId)));
            o.put("name", translateUtil.getLanguage(o.get("name"), PltConfigDAO.supplierLanguage(supplierId)));
            o.put("categoryDiy", translateUtil.getLanguage(o.get("categoryDiy"), PltConfigDAO.supplierLanguage(supplierId)));
            o.put("update_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.get("update_time")));
            return o;
        }).collect(Collectors.toList()), start, limit, totalCount);
        return page;
    }

    public List<PdtProduct> getYouMayLike(IduPage iduPage, int cat) {
        String pkeys = getYouMayLikeProd(cat);
        SQL sql = new SQL() {{
            SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.CUR_PRICE, PdtProduct.T.PICTURE);
            FROM(PdtProduct.class);
            WHERE(PdtProduct.T.PKEY, " in(" + pkeys + ") ");
        }};
        return irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class);
    }

    /**
     * 随机获取猜你喜欢的产品Pkey
     *
     * @param cat
     * @return
     */
    private String getYouMayLikeProd(int cat) {
        SQL sql = new SQL() {{
            SELECT(PdtProduct.T.PKEY);
            FROM(PdtProduct.class);
            WHERE(PdtProduct.T.CATEGORY, "=?", cat);
            WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON.getLine().getKey());
            WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES);
            WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL);
            WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR);
            LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);
            ORDER_BY(PdtProduct.T.PKEY, " DESC ");
        }};
        String pkeys = "";
        Set<Integer> prodPkeys = new HashSet<Integer>();
        List<PdtProduct> prodPkeyList = irille.pub.bean.Query.sql(sql).queryList(PdtProduct.class);
        if (prodPkeyList.size() > 0) {
            Random rand = new Random();
            for (int i = 0; ; i++) {
                if (prodPkeys.size() > 4 || i > 100) {
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
}
