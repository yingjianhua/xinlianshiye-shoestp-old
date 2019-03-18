package irille.homeAction.usr;

import java.io.IOException;
import java.util.List;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.dto.AddressView;
import irille.pub.Exp;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.i18n.I18NUtil;
import irille.pub.idu.Idu;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltProvince;
import irille.shop.usr.Usr;
import irille.shop.usr.UsrPurchaseLine;
import irille.shop.usr.UsrPurchaseLine.T;
import irille.shop.usr.UsrPurchaseLineDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsrPurchaseLineAction extends HomeAction<UsrPurchaseLine> {

  public static UsrPurchaseLineDAO.InsLine line = new UsrPurchaseLineDAO.InsLine();

  private List<PltCountry> _countries;
  private List<PltProvince> _provinces;
  private List<AddressView> shippingAddr;
  private List<AddressView> billAddr;
  private Integer _countryId;
  private Integer date;
  private Integer beanaddress;
  private Integer data;

  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    this.data = data;
  }

  public Integer getBeanaddress() {
    return beanaddress;
  }

  public void setBeanaddress(Integer beanaddress) {
    this.beanaddress = beanaddress;
  }

  public Integer getDate() {
    return date;
  }

  public void setDate(Integer date) {
    this.date = date;
  }

  public List<AddressView> getShippingAddr() {
    return shippingAddr;
  }

  public void setShippingAddr(List<AddressView> shippingAddr) {
    this.shippingAddr = shippingAddr;
  }

  public List<AddressView> getBillAddr() {
    return billAddr;
  }

  public void setBillAddr(List<AddressView> billAddr) {
    this.billAddr = billAddr;
  }

  public Integer getCountryId() {
    return _countryId;
  }

  public void setCountryId(Integer countryId) {
    this._countryId = countryId;
  }

  public List<PltProvince> getProvinces() {
    return _provinces;
  }

  public void setProvinces(List<PltProvince> provinces) {
    this._provinces = provinces;
  }

  public List<PltCountry> getCountries() {
    return _countries;
  }

  public void setCountries(List<PltCountry> countries) {
    this._countries = countries;
  }

  @Override
  public void ins() throws Exception {
    UsrPurchaseLineDAO.Ins Dao = new UsrPurchaseLineDAO.Ins();
    Dao.setB(getBean());
    Dao.commit();

    writeSuccess(getBean());
  }

  public void inss() throws Exception {
    UsrPurchaseLineDAO.Inss Dao = new UsrPurchaseLineDAO.Inss();

    System.out.println(getBean().getRegion());
    Dao.setB(getBean());
    Dao.commit();
    writeSuccess(Dao.getB());
  }

  @Override
  public void upd() throws Exception {
    UsrPurchaseLineDAO.upd Dao = new UsrPurchaseLineDAO.upd();
    Dao.setB(getBean());
    System.out.println(crtJsonByBean(getBean()));
    Dao.commit();
    writeSuccess(getBean());
  }

  public void inquire() throws JSONException, Exception {
    List<UsrPurchaseLine> list =
        UsrPurchaseLine.list(
            UsrPurchaseLine.class, UsrPurchaseLine.T.PKEY + " = " + getPkey(), false);
    JSONObject json = new JSONObject();
    for (UsrPurchaseLine usr : list) {
      json.put("us", crtJsonByBean(usr));
      json.put("co", crtJsonByBean(usr.gtCountry()));
      json.put("pv", crtJsonByBean(usr.gtRegion()));
    }

    List<PltProvince> list2 = Idu.getLines(PltProvince.T.MAIN, list.get(0).getCountry());
    json.put("lis", new JSONArray(list2, false));
    write(json.toString());
    return;
  }

  /**
   * 设置默认地址
   *
   * @return
   * @throws Exception
   * @author zw
   */
  public void setdefault() throws Exception {
    UsrPurchaseLineDAO.updisdefault Dao = new UsrPurchaseLineDAO.updisdefault();
    Dao.setB(getBean());
    try {
      Dao.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 删除地址
   *
   * @author zw
   */
  public void del() throws Exception {
    UsrPurchaseLineDAO.del del = new UsrPurchaseLineDAO.del();
    del.setB(getBean());
    del.commit();
    writeSuccess(getBean());
  }

  /**
   * 返回到地址管理页面
   *
   * @return
   */
  @NeedLogin
  public String addmanagement() {
    UsrPurchaseLine.TB.getCode();
    setShippingAddr(
        AddressView.buildByPurchaseLine(
            BeanBase.list(
                UsrPurchaseLine.class,
                T.PURCHASE.getFld().getCodeSqlField()
                    + " =? AND "
                    + T.ADDRSSTYPE.getFld().getCodeSqlField()
                    + "=? ORDER BY "
                    + T.ISDEFAULT
                    + " DESC ",
                false,
                getPurchase().getPkey(),
                Usr.OAddress.COMMON.getLine().getKey())));
    setBillAddr(
        AddressView.buildByPurchaseLine(
            BeanBase.list(
                UsrPurchaseLine.class,
                T.PURCHASE.getFld().getCodeSqlField()
                    + " =? AND "
                    + T.ADDRSSTYPE.getFld().getCodeSqlField()
                    + "=?",
                false,
                getPurchase().getPkey(),
                Usr.OAddress.BILLED.getLine().getKey())));
    setCountries(
        translateUtil.getAutoTranslateList(
            PltCountry.list(PltCountry.class, "1=1", false), HomeAction.curLanguage()));
    setProvinces(
        translateUtil.getAutoTranslateList(
            Idu.getLines(PltProvince.T.MAIN, getCountries().get(0).getPkey()),
            HomeAction.curLanguage()));
    if (shippingAddr == null || shippingAddr.size() <= 0) {
      data = 1;
    } else {
      data = 0;
    }

    if (billAddr == null || billAddr.size() <= 0) {
      date = 1;
    } else {
      date = 0;
    }
    setResult("/home/address.jsp");
    return TRENDS;
  }

  List<PltProvince> list2;

  @NeedLogin
  public String addressform() {
    UsrPurchaseLine.TB.getCode();
    setShippingAddr(
        AddressView.buildByPurchaseLine(
            BeanBase.list(
                UsrPurchaseLine.class,
                T.PURCHASE.getFld().getCodeSqlField()
                    + " =? AND "
                    + T.ADDRSSTYPE.getFld().getCodeSqlField()
                    + "=?",
                false,
                getPurchase().getPkey(),
                Usr.OAddress.COMMON.getLine().getKey())));
    setBillAddr(
        AddressView.buildByPurchaseLine(
            BeanBase.list(
                UsrPurchaseLine.class,
                T.PURCHASE.getFld().getCodeSqlField()
                    + " =? AND "
                    + T.ADDRSSTYPE.getFld().getCodeSqlField()
                    + "=?",
                false,
                getPurchase().getPkey(),
                Usr.OAddress.BILLED.getLine().getKey())));
    setCountries(
        translateUtil.getAutoTranslateList(
            PltCountry.list(PltCountry.class, "1=1", false), HomeAction.curLanguage()));
    List<UsrPurchaseLine> list1 = null;
    setProvinces(
        translateUtil.getAutoTranslateList(
            Idu.getLines(PltProvince.T.MAIN, getCountries().get(0).getPkey()),
            HomeAction.curLanguage()));
    if (shippingAddr == null || shippingAddr.size() <= 0) {
      data = 1;
    } else {
      data = 0;
    }

    if (billAddr == null || billAddr.size() <= 0) {
      date = 1;
    } else {
      date = 0;
    }
    setResult("/home/addressform.jsp");
    return TRENDS;
  }

  /*
   * 赋予初始值
   */
  public void initializing() throws JSONException, IOException {
    List<PltCountry> pc =
        translateUtil.getAutoTranslateList(
            PltCountry.list(PltCountry.class, "1=1", false), HomeAction.curLanguage());
    List<PltProvince> pr =
        translateUtil.getAutoTranslateList(
            Idu.getLines(PltProvince.T.MAIN, pc.get(0).getPkey()), HomeAction.curLanguage());
    JSONObject json = new JSONObject();
    json.put("pc", new JSONArray(pc, false));
    json.put("pr", new JSONArray(pr, false));
    write(json.toString());
  }

  public void listProvinces() throws Exception {
    List<PltProvince> list =
        translateUtil.getAutoTranslateList(
            Idu.getLines(PltProvince.T.MAIN, getCountryId()), HomeAction.curLanguage());
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    // 目前过滤器的搜索，是肯定会带初始条件的
    JSONObject lineJson = null;
    for (PltProvince line : list) {
      lineJson = crtJsonByBean(line);
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    //		writerOrExport(json);
    writeSuccess(json);
  }

  /** 增加收货地址 chenlili */
  public void addAddress() throws IOException, JSONException {
    if (getBean().getPkey() == null || getBean().getPkey() == 0) {
      UsrPurchaseLineDAO.Ins ins = new UsrPurchaseLineDAO.Ins();
      getBean().setAddrsstype(Usr.OAddress.COMMON.getLine().getKey());
      ins.setB(getBean());
      try {
        ins.commit();
        write();
      } catch (Exp e) {
        writeErr(e.getLastMessage());
      }
    } else {
      UsrPurchaseLineDAO.updAdd upd = new UsrPurchaseLineDAO.updAdd();
      getBean().setAddrsstype(Usr.OAddress.COMMON.getLine().getKey());
      upd.setB(getBean());
      try {
        upd.commit();
        write();
      } catch (Exp e) {
        writeErr(e.getLastMessage());
      }
    }
  }

  public void getAddInfo() throws Exception {
    UsrPurchaseLine usr = Bean.load(UsrPurchaseLine.class, getBean().getPkey());
    AddressView address = AddressView.buildByPurchaseLine(usr);
    JSONObject json = new JSONObject(address);
    writerOrExport(json);
  }

  /**
   * 获取地址信息地址chenlili
   *
   * @throws Exception
   * @throws JSONException
   */
  // public void getAddInfo() throws JSONException, Exception {
  //
  //	 UsrPurchaseLine usr= Bean.load(UsrPurchaseLine.class,getBean().getPkey());
  //	 String country=usr.gtCountry().getName();
  //	 String add=usr.getAddress();
  //	 System.out.println(usr.gtCountry().getPkey());
  //	 JSONObject json=new JSONObject();
  //	 json.put("editAddress", crtJsonByBean(usr));
  //	 json.put("country", country);
  //	 json.put("countrypkey", usr.gtCountry().getPkey());
  //	 if(add.indexOf(" ")!=-1) {
  //		 String addA=add.split(" ")[0];
  //		 String addB=add.split(" ")[1];
  //		 json.put("addressa", addA);
  //		 json.put("addressb", addB);
  //	 }else {
  //	 json.put("addressa", add);
  //	 }
  //	 writerOrExport(json);
  // }
  public static void aaaa() {
    String a = "{\"en\": \"hello\"}";
    String b = translateUtil.getMultiLanguageTrans(a, true);
    System.out.println(b);
  }

  /**
   * 更新收货地址
   *
   * @author GS
   * @throws Exception
   */
  private String province;

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  private String addressType;

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  /**
   * 更新地址
   *
   * @throws Exception
   * @author GS
   */
  public void UpdAddress() throws Exception {
    UsrPurchaseLineDAO.updAdress usrPurchaseLineDAO = new UsrPurchaseLineDAO.updAdress();
    JSONObject json = new JSONObject();
    usrPurchaseLineDAO.setProvince(province);
    usrPurchaseLineDAO.setB(getBean());
    usrPurchaseLineDAO.commit();
    if (usrPurchaseLineDAO.getQueryProResult().equals("success")) {
      json.put("ret", 1);
      System.out.println(usrPurchaseLineDAO.getQueryProResult());
      json.put("msg", usrPurchaseLineDAO.getQueryProResult());
    } else {
      json.put("ret", 0);
      json.put("msg", I18NUtil.getBundle("Failure"));
    }
    writeSuccess(json);
  }

  /**
   * 添加地址
   *
   * @throws Exception
   * @author GS
   */
  public void AddToAddress() throws Exception {
    UsrPurchaseLineDAO.InsAddress usrPurchaseLineDAO = new UsrPurchaseLineDAO.InsAddress();
    usrPurchaseLineDAO.setProvince(province);
    usrPurchaseLineDAO.setAddressType(addressType);
    usrPurchaseLineDAO.setB(getBean());
    try {
      usrPurchaseLineDAO.commit();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  private String jsonCarts;

  public String getJsonCarts() {
    return jsonCarts;
  }

  public void setJsonCarts(String jsonCarts) {
    this.jsonCarts = jsonCarts;
  }
}
