package irille.view.prm;

import java.util.List;
import java.util.Map;

import irille.homeAction.plt.dto.PltPayView;
import irille.homeAction.prm.dto.PdtView;
import irille.homeAction.prm.dto.SpecStaView;
import irille.homeAction.usr.dto.AddressView;
import irille.homeAction.usr.dto.ColorView;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltProvince;
import irille.shop.prm.PrmGroupPurchaseLine;
import irille.shop.usr.UsrSupplier;
import irille.view.BaseView;

public class ConfirmOrderView implements BaseView {
  private List<AddressView> addressList;
  private AddressView billAddress;
  private PrmGroupPurchaseLine groupLine;
  private UsrSupplier supplier;
  private PdtProduct product;
  private Map<String, List<SpecStaView>> staView;
  private List<PltPayView> payList;
  private List<PltCountry> countryList;
  private List<ColorView> color;
  private List<PltFreightSeller> delivery;
  private Map<Integer, List<PltProvince>> provinceMap;
  private PdtView sta;

  public AddressView getBillAddress() {
    return billAddress;
  }

  public void setBillAddress(AddressView billAddress) {
    this.billAddress = billAddress;
  }

  public List<PltFreightSeller> getDelivery() {
    return delivery;
  }

  public void setDelivery(List<PltFreightSeller> delivery) {
    this.delivery = delivery;
  }

  public Map<Integer, List<PltProvince>> getProvinceMap() {
    return provinceMap;
  }

  public void setProvinceMap(Map<Integer, List<PltProvince>> provinceMap) {
    this.provinceMap = provinceMap;
  }

  public PdtView getSta() {
    return sta;
  }

  public void setSta(PdtView sta) {
    this.sta = sta;
  }

  public List<AddressView> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<AddressView> addressList) {
    this.addressList = addressList;
  }

  public PrmGroupPurchaseLine getGroupLine() {
    return groupLine;
  }

  public void setGroupLine(PrmGroupPurchaseLine groupLine) {
    this.groupLine = groupLine;
  }

  public UsrSupplier getSupplier() {
    return supplier;
  }

  public void setSupplier(UsrSupplier supplier) {
    this.supplier = supplier;
  }

  public PdtProduct getProduct() {
    return product;
  }

  public void setProduct(PdtProduct product) {
    this.product = product;
  }

  public Map<String, List<SpecStaView>> getStaView() {
    return staView;
  }

  public void setStaView(Map<String, List<SpecStaView>> staView) {
    this.staView = staView;
  }

  public List<PltPayView> getPayList() {
    return payList;
  }

  public void setPayList(List<PltPayView> payList) {
    this.payList = payList;
  }

  public List<PltCountry> getCountryList() {
    return countryList;
  }

  public void setCountryList(List<PltCountry> countryList) {
    this.countryList = countryList;
  }

  public List<ColorView> getColor() {
    return color;
  }

  public void setColor(List<ColorView> color) {
    this.color = color;
  }
}
