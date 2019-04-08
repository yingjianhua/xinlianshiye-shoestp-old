package irille.homeAction.usr.dto;

import java.util.List;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/20 Time: 14:05 */
public class Page_supplierView {
  private List<SupplierListView> recommendList;
  private List<SupplierListView> manufacturersList;
  private List<Page_supplierProductView> category;

  public List getRecommendList() {
    return recommendList;
  }

  public void setRecommendList(List recommendList) {
    this.recommendList = recommendList;
  }

  public List getManufacturersList() {
    return manufacturersList;
  }

  public void setManufacturersList(List manufacturersList) {
    this.manufacturersList = manufacturersList;
  }

  public List getCategory() {
    return category;
  }

  public void setCategory(List category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "{"
        + "\"recommendList\":"
        + recommendList
        + ", \"manufacturersList\":"
        + manufacturersList
        + ", \"category\":"
        + category
        + '}';
  }
}
