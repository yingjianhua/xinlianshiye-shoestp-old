package irille.view.usr;

import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.UsrPurchaseLine;
import irille.view.BaseView;
import irille.view.plt.CountryView;
import irille.view.plt.ProvinceView;

// 账单地址或收货地址
public class AddressView implements BaseView {
  private String name; // 名
  private String surname; // 姓
  private String postalCode; // 邮政编码
  private String phone; // 电话号码
  private CountryView country; // 国家
  private ProvinceView region; // 省份
  private String city; // 城市
  private String address; // 详细地址

  public static final AddressView trans(UsrPurchaseLine bean) {
    AddressView view = new AddressView();
    view.setName(bean.getName());
    view.setSurname(bean.getSurname());
    view.setPostalCode(bean.getEmailcode());
    view.setPhone(bean.getPhonenumber());
    view.setCountry(
        new CountryView() {
          {
            PltCountry country = bean.gtCountry();
            setId(country.getPkey());
            setName(country.getName());
          }
        });
    view.setRegion(
        new ProvinceView() {
          {
            PltProvince region = bean.gtRegion();
            setId(region.getPkey());
            setName(region.getName());
          }
        });
    view.setCity(bean.getCity());
    view.setAddress(bean.getAddress());
    return view;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public CountryView getCountry() {
    return country;
  }

  public void setCountry(CountryView country) {
    this.country = country;
  }

  public ProvinceView getRegion() {
    return region;
  }

  public void setRegion(ProvinceView region) {
    this.region = region;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
