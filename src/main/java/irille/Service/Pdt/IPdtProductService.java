package irille.Service.Pdt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;

import irille.Service.Pdt.Imp.PdtProductServiceImp;
import irille.homeAction.pdt.dto.PdtExhibitionView;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.shop.usr.MainCateInfoView;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;
import irille.view.RFQ.RFQPdtInfo;
import irille.view.pdt.PdtProductBaseInfoView;
import irille.view.pdt.PdtProductCatView;
import irille.view.pub.PageSearch;
import irille.view.v2.Pdt.PdtNewPdtInfo;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/7 Time: 14:50 */
@ImplementedBy(PdtProductServiceImp.class)
public interface IPdtProductService {
  /**
   * @Description: 首页 获取新品
   *
   * @date 2018/11/7 15:05
   * @author lijie@shoestp.cn
   */
  List<PdtProductBaseInfoView> getNewProductsListByIndex(IduPage iduPage);

  /**
   * @Description: 商品列表页 热榜
   *
   * @date 2018/11/7 15:45
   * @author lijie@shoestp.cn
   */
  List<PdtProductBaseInfoView> getProductsIndexHot(IduPage iduPage);

  /**
   * @Description: 商品列表页 商品列表
   *
   * @date 2018/11/7 15:45
   * @author lijie@shoestp.cn
   */
  Map getProductListByCategory(
      IduPage page,
      String[] flds,
      boolean order,
      int category,
      String spec,
      String onlyFld,
      String keyword,
      Integer type);

  List<PdtProductBaseInfoView> getYouMayLike(IduPage iduPage, int cat);

  List<PdtNewPdtInfo> getRandomPdt(Integer limit, int cat, UsrPurchase purchase);

  /**
   * @Description: 商品页 侧边栏 商品分类
   *
   * @date 2018/11/9 9:40
   * @author lijie@shoestp.cn
   */
  List<PdtProductCatView> getProductsIndexCategories(
      int start, int limit, FldLanguage.Language language);

  /**
   * @Description:
   *
   * @date 2018/12/17 9:53
   * @author lijie@shoestp.cn
   */
  List<PdtNewPdtInfo> getNewProducts(IduPage page, UsrPurchase pkey, FldLanguage.Language language);

  Map getProductListByCategoryV2(
      IduPage iduPage,
      String[] orderfld,
      boolean order,
      int cated,
      String spec,
      String onlyFld,
      String keyword,
      Integer type);

  public List<PdtExhibitionView> findExhibitionGoods(IduPage page);

  /**
   * xy -pc商城端新搜索商品功能
   *
   * @return
   */
  PageSearch searchPdt(
      String[] orderfld,
      UsrPurchase purchase,
      Integer supplier,
      FldLanguage.Language curLanguage,
      Integer lose,
      String pName,
      Integer cate,
      Integer level,
      String export,
      Integer mOrder,
      BigDecimal min,
      BigDecimal max,
      Integer IsO2o,
      String o2oAddress,
      Integer start,
      Integer limit);

  RFQPdtInfo getInquiryPdtInfo(Integer id);
  
  Page findPdtList(Integer pkey,Integer start,Integer limit,Integer checkType);
  
  public List<MainCateInfoView> getMainCateName(Integer supplier);
}
