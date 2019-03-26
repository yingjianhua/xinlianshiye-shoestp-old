package irille.homeAction.usr.dto;

import java.util.ArrayList;
import java.util.List;

import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchaseLine;
import org.json.JSONException;

public class AddressView {
  private Integer id;
  private String name;
  private String surname;
  private String country;
  private String region;
  private String phoneNumber;
  private String address;
  private String emailCode;
  private String city;
  private Integer isdefault;
  private Integer rowVersion;
  private Integer countryId;
  private Integer regionId;

  public static AddressView build(List<UsrPurchaseLine> addressList, Language lang)
      throws JSONException {
    AddressView view = new AddressView();
    for (UsrPurchaseLine address : addressList) {
      if (Integer.valueOf(address.getIsdefault())
          .equals(Integer.valueOf(Sys.OYn.YES.getLine().getKey()))) {

        try {
          view.setId(address.getPkey());
          view.setName(address.getName());
          view.setSurname(address.getSurname());
          view.setCountry(address.gtCountry().getName(HomeAction.curLanguage()));
          view.setRegion(address.gtRegion().getName(HomeAction.curLanguage()));
          view.setPhoneNumber(address.getPhonenumber());
          view.setAddress(address.getAddress());
          view.setEmailCode(address.getEmailcode());
          view.setCity(address.getCity());
        } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return view;
  }

  public static AddressView buildByPurchaseLine(UsrPurchaseLine address) {
    AddressView view = new AddressView();
    try {
      view.setId(address.getPkey());
      view.setName(address.getName());
      view.setCountry(address.gtCountry().getName(HomeAction.curLanguage()));
      view.setSurname(address.getSurname());
      view.setRegion(address.gtRegion().getName(HomeAction.curLanguage()));
      view.setPhoneNumber(address.getPhonenumber());
      view.setAddress(address.getAddress());
      view.setEmailCode(address.getEmailcode());
      view.setCity(address.getCity());
      view.setIsdefault(Integer.valueOf(address.getIsdefault()));
      view.setRowVersion(Integer.valueOf(address.getRowVersion()));
      view.setCountryId(address.getCountry());
      view.setRegionId(address.getRegion());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }

  public static List<AddressView> buildByPurchaseLine(List<UsrPurchaseLine> addressList) {
    List<AddressView> views = new ArrayList<AddressView>();
    for (UsrPurchaseLine line : addressList) {
      views.add(buildByPurchaseLine(line));
    }
    return views;
  }

  public Integer getCountryId() {
    return countryId;
  }

  public void setCountryId(Integer countryId) {
    this.countryId = countryId;
  }

  public Integer getRegionId() {
    return regionId;
  }

  public void setRegionId(Integer regionId) {
    this.regionId = regionId;
  }

  public Integer getRowVersion() {
    return rowVersion;
  }

  public void setRowVersion(Integer rowVersion) {
    this.rowVersion = rowVersion;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIsdefault() {
    return isdefault;
  }

  public void setIsdefault(Integer isdefault) {
    this.isdefault = isdefault;
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmailCode() {
    return emailCode;
  }

  public void setEmailCode(String emailCode) {
    this.emailCode = emailCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
