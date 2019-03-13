package irille.homeAction.cnt.dto;

import java.util.List;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/25 Time: 18:51 */
public class CntAd_IndexCategoryView {
  private long pkey;
  private String name;
  // 板块下面的子类
  private List categorys;
  // 板块广告
  private CntAd_CategoryAdView ads;
  // 暂时类型
  private int displayType;

  public int getDisplayType() {
    return displayType;
  }

  public void setDisplayType(int displayType) {
    this.displayType = displayType;
  }

  public List getCategorys() {
    return categorys;
  }

  public void setCategorys(List categorys) {
    this.categorys = categorys;
  }

  public CntAd_CategoryAdView getAds() {
    return ads;
  }

  public void setAds(CntAd_CategoryAdView ads) {
    this.ads = ads;
  }

  public long getPkey() {
    return pkey;
  }

  public void setPkey(long pkey) {
    this.pkey = pkey;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{"
        + "\"pkey\":"
        + pkey
        + ", \"name\":\""
        + name
        + '\"'
        + ", \"categorys\":"
        + categorys
        + ", \"ads\":"
        + ads
        + ", \"displayType\":"
        + displayType
        + '}';
  }
}
