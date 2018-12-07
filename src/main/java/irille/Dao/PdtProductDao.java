package irille.Dao;

import irille.Aops.Caches;
import irille.core.sys.Sys;
import irille.homeAction.pdt.dto.PdtProductView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
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
import irille.view.pdt.PdtProductCatView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static irille.core.sys.Sys.OYn.YES;
import static java.util.stream.Collectors.toList;

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
    @Caches
    public List getNewProductsList(int start, int limit, String where) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE
        )
                .FROM(PdtProduct.class)
                .limit(start, limit)
        ;
        productRules(query);
        newProduct(query);
        if (where != null) {
            if (where != null && where.length() > 0) {
                query.WHERE(PdtProduct.T.CATEGORY, "=?", where);
            }
        }
        return query.queryMaps();
    }


    public Integer getProductSupId(int pdtId) {
        BeanQuery beanQuery = new BeanQuery();
        PdtProduct result = (PdtProduct) beanQuery.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.SUPPLIER
        ).FROM(PdtProduct.class).WHERE(PdtProduct.T.PKEY, "=?", pdtId).query(PdtProduct.class);
        if (result != null)
            return result.getSupplier();
        return -1;
    }

    /***
     *  商品列表页 热榜
     * div #what_hot
     * @author lijie@shoestp.cn
     * @param
     * @return
     * @date 2018/7/23 14:47
     */
    public List<Map> getProductsIndexHot(int start, int limit) {
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PdtProduct.T.PKEY,
                PdtProduct.T.NAME,
                PdtProduct.T.PICTURE,
                PdtProduct.T.CUR_PRICE
        ).FROM(PdtProduct.class)
                .WHERE(PdtProduct.T.IS_HOT, "=?", YES)
                .limit(start, limit)
        ;
        productRules(query);
        query.ORDER_BY(PdtProduct.T.MY_ORDER, "desc");
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
                .limit(pdtProductView.getPage().getStart(), pdtProductView.getPage().getLimit());
        query.ORDER_BY(UsrSupplier.T.IS_AUTH, "desc");
        productRules(query);
        if (pdtProductView.getCategory() > -1) {
            List list = getCatsNodeByCatId(pdtProductView.getCategory());
            if (list != null)
                query.WHERE(PdtProduct.T.CATEGORY, "in(" + String.join(",", list) + ")");
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
                query.WHERE(PdtProduct.T.NAME, "like ?", "%" + URLDecoder.decode(pdtProductView.getKeyword(), "utf-8") + "%");
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
                    newProduct(query);
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
                            if (orderByType == PdtProduct.ProductsIndexOrderByType.New) {
                                newProduct(query, true);
                            } else {
                                for (IEnumFld iEnumFld : orderByType.getFld()) {
                                    query.ORDER_BY(iEnumFld, "desc");
                                }
                            }
                        } else {
                            if (orderByType == PdtProduct.ProductsIndexOrderByType.New) {
                                newProduct(query, false);
                            } else {
                                for (IEnumFld iEnumFld : orderByType.getFld()) {
                                    query.ORDER_BY(iEnumFld, "asc");
                                }
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
        Map result = new HashMap();
        List<Map> list = query.queryMaps();
        list = list.stream().map(o -> {
            o.put("rewrite", SEOUtils.getPdtProductTitle(Integer.parseInt(String.valueOf(o.get("pkey"))), String.valueOf(o.get("name"))));
            return o;
        }).collect(toList());
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
        BeanQuery query = new BeanQuery();
        query.SELECT(
                PdtCat.T.PKEY
        ).FROM(PdtCat.class).WHERE(PdtCat.T.CATEGORY_UP, "=?", id);
        List<PdtCat> l = new ArrayList();
        List<PdtCat> tList = query.queryList();
        List ttList = new ArrayList();
        l.addAll(tList);
        List<String> result = new ArrayList();
        result.add(String.valueOf(id));
        do {
            ttList.clear();
            for (PdtCat objects : tList) {
                ttList.addAll(new BeanQuery().SELECT(
                        PdtCat.T.PKEY
                        ).FROM(PdtCat.class).WHERE(PdtCat.T.CATEGORY_UP, "=?", objects.getPkey()).queryList()
                );
            }
            l.addAll(ttList);
            tList.clear();
            tList.addAll(ttList);
            if (ttList.size() < 1) break;
        } while (true);
        for (PdtCat o : l) {
            if (o.getPkey() > 0) {
                result.add(String.valueOf(o.getPkey()));
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
            }).collect(toList());
            q.WHERE(cat != 0, PdtProduct.T.CATEGORY, "in (" + String.join(",", list) + ")");
        }
        Integer totalCount = q.queryCount();
        start = (start - 1 > -1 ? start - 1 : 0);
        q.limit(start * limit, limit).queryMaps();
        List<Map> list = q.limit(start * limit, limit).queryMaps();
        Page page = new Page(list.stream().map(o -> {
            o.put("category", translateUtil.getLanguage(o.get("category"), PltConfigDAO.supplierLanguage(supplierId)));
            o.put("rewrite", SEOUtils.getPdtProductTitle(Integer.valueOf(String.valueOf(o.get("pkey"))), String.valueOf(o.get("name"))));
            o.put("name", translateUtil.getLanguage(o.get("name"), PltConfigDAO.supplierLanguage(supplierId)));
            o.put("categoryDiy", translateUtil.getLanguage(o.get("categoryDiy"), PltConfigDAO.supplierLanguage(supplierId)));
            o.put("update_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.get("update_time")));
            return o;
        }).collect(toList()), start, limit, totalCount);
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

    /**
     * @Description: 商品显示统一逻辑
     * 修改的时候,PdtProduct.ProductsIndexOrderByType  一起修改
     * @date 2018/11/8 9:57
     * @author lijie@shoestp.cn
     */
    private BeanQuery productRules(BeanQuery query) {
//        PdtProduct.ProductsIndexOrderByType
        return query.WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(PdtProduct.T.IS_VERIFY, "=?", YES)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON)
                .WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL)
                .WHERE(UsrSupplier.T.STATUS, "=?", Usr.OStatus.APPR)
                .LEFT_JOIN(UsrSupplier.class, PdtProduct.T.SUPPLIER, UsrSupplier.T.PKEY);

    }

    /**
     * @Description: 新品排序规则..解决 排序规则碎片化问题 (未完成)
     * @date 2018/11/8 11:04
     * @author lijie@shoestp.cn
     */
    private BeanQuery newProduct(BeanQuery query) {
        return newProduct(query, true);
    }

    private BeanQuery newProduct(BeanQuery query, boolean type) {
        return query
                .ORDER_BY(PdtProduct.T.PKEY, type ? "desc" : "asc")
                .ORDER_BY(PdtProduct.T.MY_ORDER, type ? "asc" : "desc")
                .ORDER_BY(PdtProduct.T.UPDATE_TIME, type ? "desc" : "asc")
                ;
    }


    /**
     * @Description: 根据分类获取该节点子节点
     * @author lijie@shoestp.cn
     * @date 2018/8/22 21:59
     */
    public List<PdtProductCatView> getCatChildNodesByCatId(Integer integer, FldLanguage.Language language) {
        FormaterSql sql = FormaterSql.build();
        if (integer == null || integer == 0) {
            sql.isNull(PdtCat.T.CATEGORY_UP);
        } else {
            sql.eqAutoAnd(PdtCat.T.CATEGORY_UP, integer);
        }
        List<PdtProductCatView> result = PdtCat.list(PdtCat.class, sql.toWhereString(), false, sql.getParms()).stream().filter(s -> !s.gtDeleted()).map(s -> {
            PdtProductCatView pdtProductVueView = new PdtProductCatView();
            translateUtil.getAutoTranslate(s, language);
            pdtProductVueView
                    .setValue(s.getPkey());
            pdtProductVueView
                    .setLabel(s.getName())
            ;
            List l = getCatChildNodesByCatId(s.getPkey(), language);
            if (l.size() > 1) {
                pdtProductVueView.setChildren(l);
            }
            return pdtProductVueView;
        }).collect(Collectors.toList());
        return result;
    }
}
