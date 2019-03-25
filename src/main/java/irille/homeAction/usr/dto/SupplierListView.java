package irille.homeAction.usr.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.util.DataFilters;
import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/19 Time: 16:34 */
public class SupplierListView {

  private int pkey;

  @JsonSerialize(using = irille.pub.i18n.I18NFieldSerializer.class)
  private String name;

  private String logo;

  @SetBean(OriginalField = "prod_pattern")
  @JsonSerialize(using = irille.pub.i18n.I18NFieldSerializer.class)
  private String prodpattern;

  @SetBean(NotSet = true)
  private List<Page_supplierProductView> proDuctDtos;

  @SetBean(NotSet = true)
  private long proDuctCount;

  public long getProDuctCount() {
    return proDuctCount;
  }

  public void setProDuctCount(long proDuctCount) {
    this.proDuctCount = proDuctCount;
  }

  public long getPkey() {
    return pkey;
  }

  public void setPkey(int pkey) {
    this.pkey = pkey;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    System.out.println(logo);
    this.logo = DataFilters.ImageUrl_NoHost_String(logo);
  }

  public String getProdpattern() {
    return prodpattern;
  }

  public void setProdpattern(String prodpattern) {
    this.prodpattern = prodpattern;
  }

  public List<Page_supplierProductView> getProDuctDtos() {
    return proDuctDtos;
  }

  public void setProDuctDtos(List<Page_supplierProductView> proDuctDtos) {
    this.proDuctDtos = proDuctDtos;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  //    public String getCompanyphotolink() {
  //        return companyphotolink;
  //    }
  //
  //    public Page_supplierInfoView setCompanyphotolink(String companyphotolink) {
  //        this.companyphotolink = companyphotolink;
  //        return this;
  //    }

  @Override
  public String toString() {
    return "{"
        + "\"pkey\":"
        + pkey
        + ", \"name\":\""
        + name
        + '\"'
        +
        //                ", \"companyphoto\":\"" + companyphoto + '\"' +
        ", \"prodpattern\":\""
        + prodpattern
        + '\"'
        + ", \"proDuctDtos\":"
        + proDuctDtos
        + ", \"proDuctCount\":"
        + proDuctCount
        + '}';
  }
}
