package irille.homeAction.omt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.PdtView;
import irille.homeAction.usr.dto.SpecView;
import irille.pub.bean.BeanBase;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.pdt.*;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrSupplier;
import irille.view.Manage.OdrMeeting.Sale.OdrMeetingdetailView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

public class OrderMeetingdetailAction extends HomeAction<OrderMeetingProduct> {

    /**
     * @Description: 联合采购商品详情页
     * @date 2018/11/23 9:30
     * @anthor wilson zhang
     */
    public static final String SYMBOL = ",";
    @Setter
    @Getter
    private Integer shoppingid;

    public void omtmeettingshop() throws Exception {
        OdrMeetingdetailView omdv = new OdrMeetingdetailView();
        omdv.setId(shoppingid);//订购会明细的pkey
        OrderMeetingProduct omp = BeanBase.load(OrderMeetingProduct.class, shoppingid); //订购会商品

        OrderMeeting omt = BeanBase.load(OrderMeeting.class, omp.getOrdermeetingid());//订购会时间
        OrderMeeting omt1 = new OrderMeeting();
        omt1.setStartTime(omt.getStartTime());
        omt1.setEndTime(omt.getEndTime());
        omdv.setGroupPurchaseLine(omt1);


        PdtProduct ppt = BeanBase.load(PdtProduct.class, omp.getProductid());//产品规格编号
        PdtProduct ppt1 = new PdtProduct();
        ppt1.setCode(ppt.getCode());
        ppt1.setDefaultReviewRating(new BigDecimal(Math.ceil(ppt.getDefaultReviewRating().doubleValue())));
        translateUtil.getAutoTranslate(ppt1, HomeAction.curLanguage());
        omdv.setProduct(ppt1);


        if (getPurchase() != null) {
            omdv.setFavorite(UsrFavorites.chkUniquePurchase_product(false, getPurchase().getPkey(), ppt.getPkey()));
        }
        //获取该商品的商家信息
        UsrSupplier usl = ppt.gtSupplier();

        List<PdtProduct> newProList = BeanBase.list(PdtProduct.class, PdtProduct.T.STATE.getFld().getCodeSqlField()
                        + " = " + Pdt.OState.ON.getLine().getKey() + " AND " + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()
                        + " = " + Sys.OYn.YES.getLine().getKey() + " AND " + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                        + " = ? AND " + PdtProduct.T.SUPPLIER.getFld().getCodeSqlField() + "=? AND "
                        + PdtProduct.T.IS_NEW.getFld().getCodeSqlField() + "=?",
                false, Pdt.OProductType.GENERAL.getLine().getKey(), usl.getPkey(),
                Sys.OYn.YES.getLine().getKey());
        if (newProList.size() > 0) {
            Random r = new Random();
            List<PdtView> recommendationPdt = new ArrayList<PdtView>();
            for (int i = 0; i < 5; i++) {
                irille.homeAction.usr.dto.PdtView view = new irille.homeAction.usr.dto.PdtView();
                PdtProduct pdt = translateUtil.getAutoTranslate(newProList.get(r.nextInt(newProList.size())), HomeAction.curLanguage());
                view.setPdt(pdt);
                view.setRewrite(pdt.getPkey(), pdt.getName());
                recommendationPdt.add(view);
            }
            omdv.setRecommendationPdt(recommendationPdt);
        }

        String attrStr = ppt.getNormAttr();
        if (attrStr != null) {
            String[] attrArr = attrStr.split(SYMBOL);
            Map<PdtAttr, PdtAttrLine> proAttribute = new HashMap<PdtAttr, PdtAttrLine>();
            for (int i = 0; i < attrArr.length; i++) {
                PdtAttrLine attrLine = BeanBase.get(PdtAttrLine.class, attrArr[i]);
                translateUtil.getAutoTranslate(attrLine, HomeAction.curLanguage());
                PdtAttr attr = attrLine.gtMain();
                translateUtil.getAutoTranslate(attr, HomeAction.curLanguage());
                proAttribute.put(attr, attrLine);
            }
            omdv.setProAttribute(proAttribute);
        }
        PdtCat cat = ppt.gtCategory();
        List<PdtCat> pdtCatList = new ArrayList<PdtCat>();
        pdtCatList = getParentCat(pdtCatList, cat);
        for (PdtCat topCat : pdtCatList) {
            translateUtil.getAutoTranslate(topCat, HomeAction.curLanguage());
        }

        String[] colorArr = null;
        if (ppt.getColorAttr().contains(SYMBOL)) {
            colorArr = ppt.getColorAttr().split(SYMBOL);
        } else {
            colorArr = new String[1];
            colorArr[0] = ppt.getColorAttr();
        }
        Map<PdtColor, List<SpecView>> colorToSpec = new HashMap<PdtColor, List<SpecView>>();
        Map<PdtColor, List<String>> colorToImg = new HashMap<PdtColor, List<String>>();
        Map<PdtColor, String> colorShow = new HashMap<PdtColor, String>();
        for (int i = 0; i < colorArr.length; i++) {
            //colorSpecList中为该颜色的规格
            List<PdtSpec> colorSpecList = BeanBase.list(PdtSpec.class, PdtSpec.T.PRODUCT.getFld()
                    .getCodeSqlField() + " = ? AND " + PdtSpec.T.COLOR.getFld().getCodeSqlField()
                    + " = ? ", false, ppt.getPkey(), colorArr[i]);
            List<SpecView> specView = SpecView.buildBySpec(colorSpecList);
            PdtColor color = BeanBase.get(PdtColor.class, colorArr[i]);
            translateUtil.getAutoTranslate(color, HomeAction.curLanguage());
            colorToSpec.put(color, specView);
            String pics = null;
            if (colorSpecList.size() > 0) {
                pics = colorSpecList.get(0).getPics();
                String[] picsArr = null;
                if (pics == null) {
                    writeFailure("No Pictures");
                } else {
                    if (pics.contains(SYMBOL)) {
                        picsArr = pics.split(SYMBOL);
                    } else {
                        picsArr = new String[1];
                        picsArr[0] = pics;
                    }
                }
                String pic = "";
                List<String> picsList = new ArrayList<String>();
                if (picsArr != null) {
                    pic = picsArr[0];
                    for (int j = 0; j < picsArr.length; j++) {
                        picsList.add(picsArr[j]);
                    }
                }
                colorToImg.put(color, picsList);
                colorShow.put(color, pic);
            } else {
                writeFailure("Product Data Error");
            }
        }
        omdv.setColorToSpec(colorToSpec);
        omdv.setColorToImg(colorToImg);
        omdv.setColorShow(colorShow);
        write(omdv);
    }

    /**
     * 取分类PdtCat对象的上级分类
     */
    private List<PdtCat> getParentCat(List<PdtCat> catList, PdtCat cat) {
        catList.add(cat);
        PdtCat pCat = cat.gtCategoryUp();
        if (pCat != null) {
            getParentCat(catList, pCat);
        }
        return catList;
    }
}
