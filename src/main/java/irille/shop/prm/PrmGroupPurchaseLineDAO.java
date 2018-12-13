package irille.shop.prm;

import irille.Dao.PdtCatDao;
import irille.pub.Log;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduOther;
import irille.pub.tb.FldLanguage;
import irille.pub.util.CacheUtils;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.view.prm.GproductListView;
import irille.view.prm.GroupProductView;
import irille.view.prm.shoesView;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class PrmGroupPurchaseLineDAO extends IduOther<PrmGroupPurchaseLineDAO, PrmGroupPurchaseLine> {
    public static final Log LOG = new Log(PrmGroupPurchaseLineDAO.class);

    @Inject
    private PdtCatDao pdtCatDao;

    /**
     * 根据活动pkey查询其明细产品
     * 需搜索字段:/PrmGroupPurchaseLine.T.COUNT,PrmGroupPurchaseLine.T.PKEY,PdtProduct.T.CUR_PRICE,PdtProduct.T.PICTURE/PdtProduct.T.NAME/原始产品curprice/PdtProduct.T.DEFAULT_REVIEW_RATING
     * 查询条件 :/产品上架/活动pkey/产品分类/分页
     *
     * @param lang     语言
     * @param start    分页查询起始位置
     * @param limit    每页记录数
     * @param category 产品分类
     * @param sort     排序依据
     * @param type     排序类型
     * @param id       活动id
     * @return
     */
    public Map getActInfo(FldLanguage.Language lang, Integer start, Integer limit, Integer category, Integer sort, Integer type, Integer id, Integer purchasepkey) {
        Map map = new HashMap();
        SQL sql = new SQL() {{
            SELECT(PrmGroupPurchaseLine.T.PKEY, PrmGroupPurchaseLine.T.PRODUCT, PrmGroupPurchaseLine.T.COUNT)
                    .FROM(PrmGroupPurchaseLine.class);
            LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PrmGroupPurchaseLine.T.PRODUCT)
                    .WHERE(PrmGroupPurchaseLine.T.MAIN, "=?", id)
                    .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON);
        }};
        if (category != -1) {
            /**
             * @Description: 联合采购过滤
             * @date 2018/12/13 9:57
             * @author lijie@shoestp.cn
             */
            switch (category) {
                case -2: {
                    List<Integer> list = Arrays.asList(375, 374, 377, 527);
                    List filter = new ArrayList();
                    for (Integer integer : list) {
                        filter.addAll(Arrays.asList(pdtCatDao.getAllChild(lang, integer).split(",")));
                    }
                    List result = new ArrayList();
                    for (String s : pdtCatDao.getAllChild(lang, 373).split(",")) {
                        System.out.println(s);
                        System.out.println(filter.contains(s));
                        if (!filter.contains(s)) {
                            result.add(s);
                        }
                    }
                    if (result.size() > 0) {
                        sql.WHERE(PdtProduct.T.CATEGORY, " in( " + String.join(",", result) + " ) ");
                    }

                }
                break;
                case -3: {
                    List<Integer> list = Arrays.asList(381, 383, 492);
                    List filter = new ArrayList();
                    for (Integer integer : list) {
                        filter.addAll(Arrays.asList(pdtCatDao.getAllChild(lang, integer).split(",")));
                    }
                    List result = new ArrayList();
                    for (String s : pdtCatDao.getAllChild(lang, 380).split(",")) {
                        if (!filter.contains(s)) {
                            result.add(s);
                        }
                    }
                    if (result.size() > 0) {
                        sql.WHERE(PdtProduct.T.CATEGORY, " in( " + String.join(",", result) + " ) ");
                    }
                }
                break;

                case -4: {
                    List<Integer> list = Arrays.asList(516, 391);
                    List filter = new ArrayList();
                    for (Integer integer : list) {
                        filter.addAll(Arrays.asList(pdtCatDao.getAllChild(lang, integer).split(",")));
                    }
                    List result = new ArrayList();
                    for (String s : pdtCatDao.getAllChild(lang, 387).split(",")) {
                        if (!filter.contains(s)) {
                            result.add(s);
                        }
                    }
                    if (result.size() > 0) {
                        sql.WHERE(PdtProduct.T.CATEGORY, " in( " + String.join(",", result) + " ) ");
                    }

                }
                break;
                default:
                    sql.WHERE(PdtProduct.T.CATEGORY, " in( " + pdtCatDao.getAllChild(lang, category) + " ) ");
            }
        }

        map.put("pageAll", Math.ceil((double) irille.pub.bean.Query.sql(sql).queryCount() / limit));
        String rankingBasis = " DESC ";
        if (type == 1) {
            rankingBasis = " ASC ";
        }
        switch (sort) {
            case 1:
                sql.ORDER_BY(PdtProduct.T.CUR_PRICE, rankingBasis);
                break;
            case 2:
                sql.ORDER_BY(PdtProduct.T.DEFAULT_REVIEW_COUNT, rankingBasis);
                break;
            case 3:
                sql.ORDER_BY(PdtProduct.T.SALES, rankingBasis);
                break;
            default:
                sql.ORDER_BY(PrmGroupPurchaseLine.T.COUNT, rankingBasis.trim().toLowerCase().equals("desc") ? " ASC " : " DESC ");
                break;
        }
        sql.ORDER_BY(PrmGroupPurchaseLine.T.PKEY, rankingBasis);
        sql.LIMIT(start, limit);
        map.put("items", irille.pub.bean.Query.sql(sql).queryList(PrmGroupPurchaseLine.class)
                .stream()
                .map(bean -> new GroupProductView() {{
                    PdtProduct product = bean.gtProduct();
                    setIsmyfavorite(Likebest(purchasepkey, product.getPkey()));
                    setRewrite(bean.getPkey(), product.getName());
                    product = translateUtil.getAutoTranslate(product, lang);
                    setId(bean.getPkey());
                    setCount(product.getMinOq());
                    setName(product.getName());
                    setImage(product.getPicture());
                    setSourcePrice(product.gtSourceProduct().getCurPrice());
                    setCurPrice(product.getCurPrice());
                    setProductId(product.getPkey());
                    setReviewRating(product.getDefaultReviewRating().intValue());
                    setReviewCount(product.getDefaultReviewCount());
                }}).collect(Collectors.toList()));
        return map;

    }


    public GproductListView getgroupshoplist(Integer purchaseid) {
        GproductListView glv = new GproductListView();
        String manpkeys = pdtCatDao.getAllChild(FldLanguage.Language.en, 373);
        String womanpkeys = pdtCatDao.getAllChild(FldLanguage.Language.en, 380);
        String children = pdtCatDao.getAllChild(FldLanguage.Language.en, 387);
        glv.setManshoes(loadshoesView(purchaseid, manpkeys, "mancache"));
        glv.setWomanshoes(loadshoesView(purchaseid, womanpkeys, "wumancache"));
        glv.setChildrenshoes(loadshoesView(purchaseid, children, "childrencache"));
        return glv;
    }

    public List<shoesView> loadshoesView(Integer purchaseid, String pkeys, String keycache) {
        List<shoesView> manglv = (List<shoesView>) CacheUtils.groupshopcache.get(keycache, o -> {
            SQL mansql = new SQL() {{
                SELECT(PrmGroupPurchaseLine.T.PKEY).SELECT(PdtProduct.T.PKEY, "PPKEY")
                        .SELECT(PdtProduct.T.NAME, PdtProduct.T.PICTURE)
                        .FROM(PrmGroupPurchaseLine.class);
                LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PrmGroupPurchaseLine.T.PRODUCT);
                WHERE(PdtProduct.T.CATEGORY, " in (" + pkeys + ") ");
            }};
            List<shoesView> listman = Query.sql(mansql).queryMaps().stream().map(Y -> new shoesView() {{
                setId((Integer) Y.get(PrmGroupPurchaseLine.T.PKEY.getFld().getCodeSqlField()));
                setImg((String) Y.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()));
                setName((String) Y.get(PdtProduct.T.NAME.getFld().getCodeSqlField()));
                setProductid((Integer) Y.get("PPKEY"));
            }}).collect(Collectors.toList());
            return listman;
        });
        Set<Integer> intSet = new HashSet<>();
        Integer i = 10;
        if (manglv.size() != 0) {
            while (intSet.size() < 6 && i > 0) {
                i--;
                intSet.add(new Random().nextInt(manglv.size()));
            }
        }

        Integer[] ints = intSet.toArray(new Integer[intSet.size()]);
        List<shoesView> manglvshow = new ArrayList<>();
        for (int m = 0; m < ints.length; m++) {
            manglv.get(ints[m]).setIsFavorite(Likebest(purchaseid, manglv.get(ints[m]).getProductid()));
            manglvshow.add(manglv.get(ints[m]));
        }
        return manglvshow;
    }

    /**
     * @Description: 返回是否是已收藏的
     * @date 2018/11/28 9:21
     * @anthor wilson zhang
     */
    public Boolean Likebest(Integer purchaseid, Integer pkey) {
        if (purchaseid != null && purchaseid != -1) {
            SQL sql = new SQL() {{
                SELECT(UsrFavorites.class).FROM(UsrFavorites.class)
                        .WHERE(UsrFavorites.T.PRODUCT, "=?", pkey);
                WHERE(UsrFavorites.T.PURCHASE, " =? ", purchaseid);
            }};
            if (Query.sql(sql).queryCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
