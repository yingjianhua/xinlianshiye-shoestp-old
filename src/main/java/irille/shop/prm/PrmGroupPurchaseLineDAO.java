package irille.shop.prm;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import irille.pub.Log;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduOther;
import irille.pub.tb.FldLanguage;
import irille.pub.util.CacheUtils;
import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.view.prm.GproductListView;
import irille.view.prm.GroupProductView;
import irille.view.prm.shoesView;

import java.util.*;
import java.util.stream.Collectors;

public class PrmGroupPurchaseLineDAO {
    public static final Log LOG = new Log(PrmGroupPurchaseLineDAO.class);


    public static class Select extends IduOther<Select, PrmGroupPurchaseLine> {

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
        public static Map getActInfo(FldLanguage.Language lang, Integer start, Integer limit, Integer category, Integer sort, Integer type, Integer id) {
            Map map = new HashMap();
            SQL sql = new SQL() {{
                SELECT(PrmGroupPurchaseLine.T.PKEY, PrmGroupPurchaseLine.T.PRODUCT, PrmGroupPurchaseLine.T.COUNT)
                        .FROM(PrmGroupPurchaseLine.class);
                LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PrmGroupPurchaseLine.T.PRODUCT)
                .WHERE(PrmGroupPurchaseLine.T.MAIN, "=?", id)
                .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON);
            }};
            if (category != -1) {
                sql.WHERE(PdtProduct.T.CATEGORY, " in( " + PdtCatDAO.Sellect.getAllChild(lang,category) + " ) ");
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
            sql.ORDER_BY(PrmGroupPurchaseLine.T.PKEY,rankingBasis);
            sql.LIMIT(start, limit);
            map.put("items", irille.pub.bean.Query.sql(sql).queryList(PrmGroupPurchaseLine.class)
                    .stream()
                    .map(bean -> new GroupProductView() {{
                        PdtProduct product = bean.gtProduct();
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
    }
    public static GproductListView getgroupshoplist(Integer purchaseid ){
        GproductListView glv=new GproductListView();
        String manpkeys=  PdtCatDAO.Sellect.getAllChild(FldLanguage.Language.en,373);
        String womanpkeys=  PdtCatDAO.Sellect.getAllChild(FldLanguage.Language.en,380);
        String children=  PdtCatDAO.Sellect.getAllChild(FldLanguage.Language.en,387);
        glv.setManshoes( loadshoesView(purchaseid,manpkeys,"mancache"));
        glv.setWomanshoes( loadshoesView(purchaseid,womanpkeys,"wumancache"));
        glv.setChildrenshoes( loadshoesView(purchaseid,children,"childrencache"));
        return  glv;
    }

    public static   List<shoesView> loadshoesView(Integer purchaseid,String pkeys ,String keycache){
        List<shoesView> manglv= (List<shoesView>)CacheUtils.groupshopcache.get(keycache,o -> {
            SQL mansql=new SQL(){{
                SELECT(PrmGroupPurchaseLine.T.PKEY).SELECT(PdtProduct.T.PKEY,"PPKEY")
                        .SELECT(PdtProduct.T.NAME,PdtProduct.T.PICTURE)
                        .FROM(PrmGroupPurchaseLine.class);
                LEFT_JOIN(PdtProduct.class,PdtProduct.T.PKEY,PrmGroupPurchaseLine.T.PRODUCT);
                WHERE(PdtProduct.T.CATEGORY," in ("+pkeys+") ");
            }};
            List<shoesView> listman= Query.sql(mansql).queryMaps().stream().map(Y ->new shoesView(){{
                setId((Integer) Y.get(PrmGroupPurchaseLine.T.PKEY.getFld().getCodeSqlField()));
                setImg((String)Y.get(PdtProduct.T.PICTURE.getFld().getCodeSqlField()));
                setName((String)Y.get(PdtProduct.T.NAME.getFld().getCodeSqlField()));
                if(purchaseid!=null){
                    SQL sql =new SQL(){{
                        SELECT(UsrFavorites.class).FROM(UsrFavorites.class)
                                .WHERE(UsrFavorites.T.PRODUCT,"=?",(Integer) Y.get("PPKEY"));
                        WHERE(UsrFavorites.T.PURCHASE," =? ",purchaseid);
                    }};
                    if(Query.sql(sql).queryCount()>0){
                        setIslove(true);
                    }else{
                        setIslove(false);
                    }
                }else{
                    setIslove(false);
                }
            }}).collect(Collectors.toList());
            return  listman;
        });
        Set<Integer> intSet = new HashSet<>();
        Integer i = 10;
        if(manglv.size()!=0){
            while (intSet.size() < 6 && i>0) {
                i--;
                intSet.add(new Random().nextInt(manglv.size()));
            }
        }

        Integer[] ints = intSet.toArray(new Integer[intSet.size()]);
        List<shoesView> manglvshow=new ArrayList<>();
        for(int  m=0;m<ints.length;m++) {
            manglvshow.add(manglv.get(ints[m]));
        }
        return  manglvshow;
    }
}
