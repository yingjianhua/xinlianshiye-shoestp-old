package irille.shop.prm;

import irille.pub.Log;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduOther;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCatDAO;
import irille.shop.pdt.PdtProduct;
import irille.view.prm.GroupProductView;

import java.util.HashMap;
import java.util.Map;
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
}
