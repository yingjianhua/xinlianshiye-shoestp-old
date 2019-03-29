package irille.homeAction.usr.dto;

import java.util.List;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/25 Time: 10:32
 *
 * <p>首页渲染内容
 */
public class PurchaseIndexView {
  // 新品
  private List newProducts;
  // 供应商
  private List supplier;
  // 板块广告
  private List pdtCategoryAds;
  // 板块中间广告
  private List middleAds;

  // 板块头部_左侧
  private List headedSwiperAds;
  // 板块头部_右侧
  private List headedRightTopAds;
  private List headedBottomTopAds;

  public List getSupplier() {
    return supplier;
  }

  public void setSupplier(List supplier) {
    this.supplier = supplier;
  }

  public List getPurchases() {
    return supplier;
  }

  public List getNewProducts() {
    return newProducts;
  }

  public void setNewProducts(List newProducts) {
    this.newProducts = newProducts;
  }

  public List getPdtCategoryAds() {
    return pdtCategoryAds;
  }

  public void setPdtCategoryAds(List pdtCategoryAds) {
    this.pdtCategoryAds = pdtCategoryAds;
  }

  public List getMiddleAds() {
    return middleAds;
  }

  public void setMiddleAds(List middleAds) {
    this.middleAds = middleAds;
  }

  public List getHeadedSwiperAds() {
    return headedSwiperAds;
  }

  public void setHeadedSwiperAds(List headedSwiperAds) {
    this.headedSwiperAds = headedSwiperAds;
  }

  public List getHeadedRightTopAds() {
    return headedRightTopAds;
  }

  public void setHeadedRightTopAds(List headedRightTopAds) {
    this.headedRightTopAds = headedRightTopAds;
  }

  public List getHeadedBottomTopAds() {
    return headedBottomTopAds;
  }

  public void setHeadedBottomTopAds(List headedBottomTopAds) {
    this.headedBottomTopAds = headedBottomTopAds;
  }

  @Override
  public String toString() {
    return "{"
        + "\"newProducts\":"
        + newProducts
        + ", \"supplier\":"
        + supplier
        + ", \"pdtCategoryAds\":"
        + pdtCategoryAds
        + ", \"middleAds\":"
        + middleAds
        + ", \"headedSwiperAds\":"
        + headedSwiperAds
        + ", \"headedRightTopAds\":"
        + headedRightTopAds
        + ", \"headedBottomTopAds\":"
        + headedBottomTopAds
        + '}';
  }
}
