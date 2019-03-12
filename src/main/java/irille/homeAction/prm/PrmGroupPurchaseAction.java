package irille.homeAction.prm;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Service.Odr.IOrderService;
import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.homeAction.plt.dto.PltPayView;
import irille.homeAction.prm.dto.PdtView;
import irille.homeAction.prm.dto.SpecStaView;
import irille.homeAction.usr.dto.AddressView;
import irille.homeAction.usr.dto.ColorView;
import irille.homeAction.usr.dto.SpecView;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.tb.FldLanguage;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.*;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltFreightSeller;
import irille.shop.plt.PltPay;
import irille.shop.plt.PltProvince;
import irille.shop.prm.*;
import irille.shop.usr.*;
import irille.view.prm.ConfirmOrderView;
import irille.view.prm.GroupProductInfoView;
import irille.view.usr.SupplierView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrmGroupPurchaseAction extends HomeAction<PrmGroupPurchase> {

  private List<PrmGroupPurchase> unionList;
  private String unionPkey;
  private PrmGroupPurchase prm;
  private String prmPkey;

  public String getPrmPkey() {
    return prmPkey;
  }

  public void setPrmPkey(String prmPkey) {
    this.prmPkey = prmPkey;
  }

  public PrmGroupPurchase getPrm() {
    return prm;
  }

  public void setPrm(PrmGroupPurchase prm) {
    this.prm = prm;
  }

  public String getUnionPkey() {
    return unionPkey;
  }

  public void setUnionPkey(String unionPkey) {
    this.unionPkey = unionPkey;
  }

  public List<PrmGroupPurchase> getUnionList() {
    return unionList;
  }

  public void setUnionList(List<PrmGroupPurchase> unionList) {
    this.unionList = unionList;
  }

  private Integer type;

  /** 获取指定商户的所有团购活动 */
  /*public String getSupUnion(){
  	PrmGroupPurchaseDAO.GroupList groupList = new PrmGroupPurchaseDAO.GroupList();
  	groupList.setSellerPkey(Integer.valueOf(getPkey().toString()));
  	groupList.setState(PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " <> " + Prm.OStatus.NOTSTART.getLine().getKey());
  	groupList.commit();
  	unionList = groupList.getGroupList();
  	supplier = BeanBase.load(UsrSupplier.class, getPkey());
  	if(getType() == null || getType() == 1){
  		setType(0);
  	}else if(getType() == 0){
  		setType(1);
  	}
  	if(prmPkey == null || prmPkey.trim() == ""){
  		List<PrmGroupPurchase> unionHandler = BeanBase.list(PrmGroupPurchase.class,  PrmGroupPurchase.T.CREATED_BY.getFld().getCodeSqlField() + " = ? AND " + PrmGroupPurchase.T.STATUS.getFld().getCodeSqlField() + " = ? " ,false,getPkey(),Prm.OStatus.HAVEINHAND.getLine().getKey());
  		if(unionHandler == null || unionHandler.size() == 0){
  			prm = unionList.get(0);
  		}else{
  			prm = unionHandler.get(0);
  		}
  	}else{
  		prm = BeanBase.load(PrmGroupPurchase.class, prmPkey);
  	}
  	setResult("/home/groupPurchase.jsp");
  	return TRENDS;
  }*/

  private String category;

  private String sort;
  private Integer pageNumber;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * 获取团购活动产品
   *
   * @throws Exception
   */
  public void getLineData() throws Exception {
    Integer idx = null;
    if (pageNumber == null) {

    } else if (pageNumber.intValue() == 0) {
      pageNumber = 1;
    } else if (pageNumber.intValue() == 1) {
      idx = 1;
    } else {
      idx = (pageNumber - 1) * 8;
    }
    String groupLineWhere =
        PrmGroupPurchaseLine.T.MAIN.getFld().getCodeSqlField() + " = " + getUnionPkey();

    if (sort != null && sort.trim() != "") {
      groupLineWhere += " ORDER BY " + sort;
    }
    JSONObject json = new JSONObject();
    List<PrmGroupPurchaseLine> unionLineList = null;
    if (idx == null) {
      unionLineList = BeanBase.list(PrmGroupPurchaseLine.class, groupLineWhere, false);
    } else {
      unionLineList = BeanBase.list(PrmGroupPurchaseLine.class, false, groupLineWhere, idx, 8);
    }
    List<PdtProduct> catPdtList = null;
    String where =
        PdtProduct.T.STATE.getFld().getCodeSqlField() + " = " + Pdt.OState.ON.getLine().getKey();
    if (category != null && category.trim() != "") {
      where +=
          " AND "
              + PdtProduct.T.CATEGORY.getFld().getCodeSqlField()
              + " in("
              + getAllCat(category)
              + ") ";
    }
    if (sort != null && sort.trim() != "") {
      switch (Integer.valueOf(sort)) {
        case 1:
          sort = PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField();
          break;
        case 2:
          sort = PdtProduct.T.DEFAULT_REVIEW_COUNT.getFld().getCodeSqlField();
          break;
        case 3:
          sort = PdtProduct.T.Favorite_Count.getFld().getCodeSqlField();
          break;
        default:
          sort = PdtProduct.T.CUR_PRICE.getFld().getCodeSqlField();
          break;
      }
      if (type == null || type.intValue() == 1) {
        where += " ORDER BY " + sort + " DESC ";
      } else {
        where += " ORDER BY " + sort + " ASC ";
      }
    }
    catPdtList = BeanBase.list(PdtProduct.class, where, false);
    JSONArray jo = new JSONArray();

    for (PdtProduct pdt : catPdtList) {
      for (PrmGroupPurchaseLine unionLine : unionLineList) {
        if (catPdtList != null) {
          if (unionLine.getProduct().equals(pdt.getPkey())) {
            JSONObject jsonLine = crtJsonByBean(unionLine);
            if (unionLine
                .gtProduct()
                .getProductType()
                .equals(Pdt.OProductType.GROUP.getLine().getKey())) {
              jsonLine.put(
                  "source",
                  crtJsonByBean(
                      translateUtil.getAutoTranslate(
                          unionLine.gtProduct().gtSourceProduct(), HomeAction.curLanguage())));
              JSONObject jsonPro =
                  crtJsonByBean(
                      translateUtil.getAutoTranslate(
                          unionLine.gtProduct(), HomeAction.curLanguage()));
              jsonLine.put("pro", jsonPro);
              jo.put(jsonLine);
            }
          }
        }
      }
    }
    json.put(STORE_ROOT, jo);
    json.put(STORE_TOTAL, Idu.getLinesCount(PrmGroupPurchaseLine.T.MAIN.getFld(), getUnionPkey()));
    writerOrExport(json);
  }

  private Set<String> catSet = new HashSet<String>();

  public Set<String> getCatSet() {
    return catSet;
  }

  public void setCatSet(Set<String> catSet) {
    this.catSet = catSet;
  }

  public String getAllCat(String category) {
    catSet.add(category);
    getAllSubclassification(category);
    String pkeys = "";
    for (String s : getCatSet()) {
      if (pkeys == "") {
        pkeys += s;
      } else {
        pkeys += "," + s;
      }
    }
    return pkeys;
  }

  /** 获取所有子分类pkey */
  public void getAllSubclassification(String category) {
    List<PdtCat> catList =
        BeanBase.list(
            PdtCat.class,
            PdtCat.T.CATEGORY_UP.getFld().getCodeSqlField() + " in(" + category + ") ",
            false);
    String childCat = "";
    if (catList != null && catList.size() > 0) {
      for (int i = 0; i < catList.size(); i++) {
        if (childCat == "") {
          childCat += catList.get(i).getPkey();
        } else {
          childCat += "," + catList.get(i).getPkey();
        }
        catSet.add(catList.get(i).getPkey().toString());
      }
      getAllSubclassification(childCat);
    }
  }

  public static final String SYMBOL = ",";
  private PrmGroupPurchaseLine groupPurchaseLine;
  private PdtProduct product;
  private Map<PdtColor, List<SpecView>> colorToSpec = new HashMap<PdtColor, List<SpecView>>();
  private Map<PdtColor, List<String>> colorToImg = new HashMap<PdtColor, List<String>>();
  private Map<PdtColor, String> colorShow = new HashMap<PdtColor, String>();
  private List<PdtCat> pdtCatList = new ArrayList<PdtCat>();
  private UsrSupplier supplier;
  private PrmGroupPurchase groupPurchase;
  private Map<PdtAttr, PdtAttrLine> proAttribute = new HashMap<PdtAttr, PdtAttrLine>();
  private List<irille.homeAction.usr.dto.PdtView> recommendationPdt =
      new ArrayList<irille.homeAction.usr.dto.PdtView>();
  private UsrPurchase presentPurchase;
  private UsrFavorites favorite;
  @Getter @Setter private PdtProduct sourceProduct;

  @Inject private IOrderService orderService;

  /**
   * @Description: 商品销售信息(达成度
   *
   * @date 2018/12/14 16:13
   * @author lijie@shoestp.cn
   */
  @Getter private GroupProductInfoView saleInfo;

  private List<UsrSupIm> imList;
  @Getter @Setter private SupplierView supView;

  /**
   * @Description: 临时用 页面显示价格
   *
   * @date 2018/12/14 19:16
   * @author lijie@shoestp.cn
   */
  @Getter private double price;

  /**
   * 获取联合采购商品详情 getPkey() ---> 联合采购明细的pkey groupPurchaseLine ---> 用户点击的联合采购明细对象 product --->
   * 此联合采购明细对象所对应的产品 supplier ---> 此产品的供应商 pdtCatList ---> 此产品的分类 colorToSpec --->
   * 此产品包含的颜色及该颜色对应的规格集合 colorToImg ---> 此产品包含的颜色及该颜色对应的产品图片 colorShow ---> 该颜色及其首张对应产品图
   * proAttribute ---> 此产品的属性 groupPurchase ---> 联合采购对象 recommendationPdt ---> 供应商的推荐商品 purchase
   * ---> 登录的采购商
   *
   * @throws Exception
   */
  public String getGroupPdt() throws Exception {
    new PrmGroupPurchaseDAO.GroupList().commit();
    groupPurchaseLine = BeanBase.get(PrmGroupPurchaseLine.class, getPkey()); // 获取点击的联合采购明细
    translateUtil.getAutoTranslate(groupPurchaseLine, HomeAction.curLanguage());
    groupPurchase = groupPurchaseLine.gtMain(); // 获取点击的联合采购
    translateUtil.getAutoTranslate(groupPurchase, HomeAction.curLanguage());
    product =
        translateUtil.getAutoTranslate(groupPurchaseLine.gtProduct(), curLanguage()); // 获取点击的联合采购产品
    if (getPurchase() != null) {
      favorite =
          UsrFavorites.chkUniquePurchase_product(false, getPurchase().getPkey(), product.getPkey());
    }
    price = product.getCurPrice().doubleValue();
    sourceProduct = product.gtSourceProduct();
    translateUtil.getAutoTranslate(sourceProduct, HomeAction.curLanguage());
    product.setDefaultReviewRating(
        new BigDecimal(Math.ceil(product.getDefaultReviewRating().doubleValue())));
    translateUtil.getAutoTranslate(product, HomeAction.curLanguage());
    supplier = product.gtSupplier(); // 获取点击的联合采购的商家
    setSupView(UsrSupplierDAO.Select.getSupView(curLanguage(), supplier.getPkey(), 1));
    translateUtil.getAutoTranslate(supplier, HomeAction.curLanguage());

    //		List<PdtProduct> newProList =  BeanBase.list(PdtProduct.class,PdtProduct.T.SUPPLIER + " = "
    // + supplier.getPkey() + " AND " + PdtProduct.T.IS_NEW + " = 1 ",false);
    List<PdtProduct> newProList =
        BeanBase.list(
            PdtProduct.class,
            PdtProduct.T.STATE.getFld().getCodeSqlField()
                + " = "
                + Pdt.OState.ON.getLine().getKey()
                + " AND "
                + PdtProduct.T.IS_VERIFY.getFld().getCodeSqlField()
                + " = "
                + Sys.OYn.YES.getLine().getKey()
                + " AND "
                + PdtProduct.T.PRODUCT_TYPE.getFld().getCodeSqlField()
                + " = ? AND "
                + PdtProduct.T.SUPPLIER.getFld().getCodeSqlField()
                + "=? AND "
                + PdtProduct.T.IS_NEW.getFld().getCodeSqlField()
                + "=?",
            false,
            Pdt.OProductType.GENERAL.getLine().getKey(),
            supplier.getPkey(),
            Sys.OYn.YES.getLine().getKey());
    if (newProList.size() > 0) {
      Random r = new Random();
      for (int i = 0; i < 5; i++) {
        irille.homeAction.usr.dto.PdtView view = new irille.homeAction.usr.dto.PdtView();
        PdtProduct pdt =
            translateUtil.getAutoTranslate(
                newProList.get(r.nextInt(newProList.size())), curLanguage());
        view.setPdt(pdt);
        view.setRewrite(pdt.getPkey(), pdt.getName());
        recommendationPdt.add(view);
      }
    }
    String attrStr = product.getNormAttr();
    if (attrStr != null) {
      String[] attrArr = attrStr.split(SYMBOL);

      for (int i = 0; i < attrArr.length; i++) {
        if (Integer.parseInt(attrArr[i]) < 1) continue;
        PdtAttrLine attrLine = BeanBase.get(PdtAttrLine.class, attrArr[i]);
        translateUtil.getAutoTranslate(attrLine, HomeAction.curLanguage());
        PdtAttr attr = attrLine.gtMain();
        translateUtil.getAutoTranslate(attr, HomeAction.curLanguage());
        proAttribute.put(attr, attrLine);
      }
    }

    PdtCat cat = product.gtCategory();
    pdtCatList = getParentCat(pdtCatList, cat);
    for (PdtCat topCat : pdtCatList) {
      translateUtil.getAutoTranslate(topCat, curLanguage());
    }
    String[] colorArr = null;
    if (product.getColorAttr().contains(SYMBOL)) {
      colorArr = product.getColorAttr().split(SYMBOL);
    } else {
      colorArr = new String[1];
      colorArr[0] = product.getColorAttr();
    }

    for (int i = 0; i < colorArr.length; i++) {
      // colorSpecList中为该颜色的规格
      List<PdtSpec> colorSpecList =
          BeanBase.list(
              PdtSpec.class,
              PdtSpec.T.PRODUCT.getFld().getCodeSqlField()
                  + " = ? AND "
                  + PdtSpec.T.COLOR.getFld().getCodeSqlField()
                  + " = ? ",
              false,
              product.getPkey(),
              colorArr[i]);
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
    saleInfo =
        orderService.getPrmSaleInfo(product.getPkey(), Integer.valueOf(String.valueOf(getPkey())));
    setResult("/home/groupPurchaseGoodsInfo.jsp");
    return HomeAction.TRENDS;
  }

  /** 取分类PdtCat对象的上级分类 */
  private List<PdtCat> getParentCat(List<PdtCat> catList, PdtCat cat) {
    catList.add(cat);
    PdtCat pCat = cat.gtCategoryUp();
    if (pCat != null) {
      getParentCat(catList, pCat);
    }
    return catList;
  }

  @Getter @Setter private List<UsrCart> specList;
  @Getter @Setter private Integer groupLinePkey;
  @Getter @Setter private Integer id;
  @Getter @Setter private String supLoginName;
  private ConfirmOrderView confirmView = new ConfirmOrderView();

  /**
   * 团购活动提交订单
   *
   * @throws Exception
   */
  public String send() throws Exception {
    UsrPurchase purchase = UsrPurchase.chkUniqueLogin_name(false, getSupLoginName());
    if (purchase == null) {
      setJumpUrl("/home/prm_PrmGroupPurchase_send?id=" + getId());
      return LOGIN;
    }
    PrmGroupPurchaseLine groupLine = BeanBase.load(PrmGroupPurchaseLine.class, getId());
    if (!groupLine.gtMain().gtCreatedBy().getLoginName().equals(getSupLoginName())) {
      setJumpUrl("/home/prm_PrmGroupPurchase_send?id=" + getId());
      return LOGIN;
    }
    setPurchase(purchase);
    PdtProduct product = groupLine.gtProduct();
    List<PdtSpec> specList =
        BeanBase.list(
            PdtSpec.class,
            PdtSpec.T.PRODUCT.getFld().getCodeSqlField() + " = " + product.getPkey(),
            false);
    String specIds = "";
    for (PdtSpec spec : specList) {
      if (specIds == "") {
        specIds += spec.getPkey();
      } else {
        specIds += "," + spec.getPkey();
      }
    }
    // 获取该明细下所有订单
    List<OdrOrderLine> orderLineList =
        BeanBase.list(
            OdrOrderLine.class,
            OdrOrderLine.T.SPEC.getFld().getCodeSqlField() + " in (" + specIds + ") ",
            false);
    List<OdrOrder> orderList = null;
    for (OdrOrderLine orderLine : orderLineList) {
      if (orderList == null) {
        orderList = new ArrayList<OdrOrder>();
        orderList.add(orderLine.gtMain());
      } else {
        boolean flag = false;
        for (OdrOrder order : orderList) {
          if (order.getPkey().equals(orderLine.gtMain().getPkey())) {
            flag = true;
            break;
          }
        }
        if (flag == false) {
          orderList.add(orderLine.gtMain());
        }
      }
    }
    if (orderList == null || orderList.size() <= 0) {
      throw LOG.err(Prm.ErrMsgs.EMPTYERR);
    }
    // 获取该采购商的所有地址
    List<UsrPurchaseLine> purchaseLineList =
        BeanBase.list(
            UsrPurchaseLine.class,
            UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField()
                + " = "
                + purchase.getPkey()
                + " AND "
                + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField()
                + " = "
                + Usr.OAddress.COMMON.getLine().getKey(),
            false);
    List<AddressView> address = AddressView.buildByPurchaseLine(purchaseLineList);
    List<UsrPurchaseLine> billAddressList =
        BeanBase.list(
            UsrPurchaseLine.class,
            UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField()
                + " =? AND "
                + UsrPurchaseLine.T.ADDRSSTYPE.getFld().getCodeSqlField()
                + " = ? ",
            false,
            purchase.getPkey(),
            Usr.OAddress.BILLED.getLine().getKey());
    AddressView billAddress = null;
    if (billAddressList != null && billAddressList.size() > 0) {
      billAddress = AddressView.buildByPurchaseLine(billAddressList.get(0));
    }
    // 获取对应的联合采购明细对象
    PrmGroupPurchaseLine groupLine1 = BeanBase.load(PrmGroupPurchaseLine.class, getId());
    // 获取其产品的供应商
    UsrSupplier supplier = groupLine.gtProduct().gtSupplier();
    // 获取此明细下对应的产品
    PdtProduct pdt = groupLine.gtProduct();
    translateUtil.getAutoTranslate(pdt, HomeAction.curLanguage());
    PdtView sta = PdtView.build(orderLineList);
    // 获取省
    Map<Integer, List<PltProvince>> provinceMap = new HashMap<Integer, List<PltProvince>>();
    List<PltProvince> provinceList = BeanBase.list(PltProvince.class, null, false);
    for (PltProvince p : provinceList) {
      if (provinceMap.get(p.getMain()) == null) {
        List<PltProvince> proList = new ArrayList<PltProvince>();
        proList.add(p);
        provinceMap.put(p.getMain(), proList);
      } else {
        List<PltProvince> proList = provinceMap.get(p.getMain());
        proList.add(p);
      }
    }

    List<PltFreightSeller> expressList =
        BeanBase.list(
            PltFreightSeller.class,
            PltFreightSeller.T.SUPPLIER.getFld().getCodeSqlField()
                + " = ? AND "
                + PltFreightSeller.T.ENABLED.getFld().getCodeSqlField()
                + " =? ",
            false,
            supplier.getPkey(),
            Sys.OEnabled.TRUE.getLine().getKey());
    List<PltPay> payMethod =
        BeanBase.list(
            PltPay.class,
            PltPay.T.SUPPLIER.getFld().getCodeSqlField()
                + " = "
                + supplier.getPkey()
                + " AND "
                + PltPay.T.ENABLED.getFld().getCodeSqlField()
                + " <> "
                + Sys.OEnabled.FALSE.getLine().getKey(),
            false);

    List<PltPayView> payViews = PltPayView.build(payMethod);

    // 获取产品下所有对应规格的数量
    List<ColorView> color = ColorView.buildColor2(orderList);
    Map<String, List<SpecStaView>> staMap = SpecStaView.build(orderLineList);
    List<PltPay> payList =
        BeanBase.list(
            PltPay.class,
            PltPay.T.SUPPLIER.getFld().getCodeSqlField()
                + " = "
                + supplier.getPkey()
                + " AND "
                + PltPay.T.ENABLED.getFld().getCodeSqlField()
                + " = "
                + Sys.OEnabled.TRUE.getLine().getKey(),
            false);
    List<PltCountry> country = BeanBase.list(PltCountry.class, null, false);
    confirmView.setBillAddress(billAddress);
    confirmView.setDelivery(expressList);
    confirmView.setAddressList(address);
    confirmView.setSupplier(supplier);
    confirmView.setGroupLine(groupLine);
    confirmView.setProduct(pdt);
    confirmView.setStaView(staMap);
    confirmView.setColor(color);
    confirmView.setSta(sta);
    confirmView.setPayList(payViews);
    confirmView.setCountryList(country);
    confirmView.setProvinceMap(provinceMap);
    confirmView.setGroupLine(groupLine1);
    setResult("/home/confirmOrder.jsp");
    return TRENDS;
  }

  public ConfirmOrderView getConfirmView() {
    return confirmView;
  }

  public void setConfirmView(ConfirmOrderView confirmView) {
    this.confirmView = confirmView;
  }

  public Integer getGroupLinePkey() {
    return groupLinePkey;
  }

  public void setGroupLinePkey(Integer groupLinePkey) {
    this.groupLinePkey = groupLinePkey;
  }

  public UsrPurchase getPresentPurchase() {
    return presentPurchase;
  }

  public void setPresentPurchase(UsrPurchase presentPurchase) {
    this.presentPurchase = presentPurchase;
  }

  public List<irille.homeAction.usr.dto.PdtView> getRecommendationPdt() {
    return recommendationPdt;
  }

  public void setRecommendationPdt(List<irille.homeAction.usr.dto.PdtView> recommendationPdt) {
    this.recommendationPdt = recommendationPdt;
  }

  public PrmGroupPurchase getGroupPurchase() {
    return groupPurchase;
  }

  public void setGroupPurchase(PrmGroupPurchase groupPurchase) {
    this.groupPurchase = groupPurchase;
  }

  public Map<PdtAttr, PdtAttrLine> getProAttribute() {
    return proAttribute;
  }

  public void setProAttribute(Map<PdtAttr, PdtAttrLine> proAttribute) {
    this.proAttribute = proAttribute;
  }

  public PrmGroupPurchaseLine getGroupPurchaseLine() {
    return groupPurchaseLine;
  }

  public void setGroupPurchaseLine(PrmGroupPurchaseLine groupPurchaseLine) {
    this.groupPurchaseLine = groupPurchaseLine;
  }

  public PdtProduct getProduct() {
    return product;
  }

  public void setProduct(PdtProduct product) {
    this.product = product;
  }

  public Map<PdtColor, List<SpecView>> getColorToSpec() {
    return colorToSpec;
  }

  public void setColorToSpec(Map<PdtColor, List<SpecView>> colorToSpec) {
    this.colorToSpec = colorToSpec;
  }

  public Map<PdtColor, List<String>> getColorToImg() {
    return colorToImg;
  }

  public void setColorToImg(Map<PdtColor, List<String>> colorToImg) {
    this.colorToImg = colorToImg;
  }

  public Map<PdtColor, String> getColorShow() {
    return colorShow;
  }

  public void setColorShow(Map<PdtColor, String> colorShow) {
    this.colorShow = colorShow;
  }

  public List<PdtCat> getPdtCatList() {
    return pdtCatList;
  }

  public void setPdtCatList(List<PdtCat> pdtCatList) {
    this.pdtCatList = pdtCatList;
  }

  public UsrSupplier getSupplier() {
    return supplier;
  }

  public void setSupplier(UsrSupplier supplier) {
    this.supplier = supplier;
  }

  public UsrFavorites getFavorite() {
    return favorite;
  }

  public void setFavorite(UsrFavorites favorite) {
    this.favorite = favorite;
  }

  public List<UsrSupIm> getImList() {
    return imList;
  }

  public void setImList(List<UsrSupIm> imList) {
    this.imList = imList;
  }

  @Getter @Setter private String lang;
  @Inject private ObjectMapper objectMapper;

  @Inject private PrmGroupPurchaseLineDAO prmGroupPurchaseLineDAO;

  public void getActProduct() throws IOException {
    setStart(getPage() <= 1 ? 0 : (getPage() - 1) * getLimit());
    Integer id;
    if (getPurchase() == null) {
      id = -1;
    } else {
      id = getPurchase().getPkey();
    }
    Map views =
        prmGroupPurchaseLineDAO.getActInfo(
            curLanguage(),
            getStart(),
            getLimit(),
            Integer.valueOf(getCategory()),
            Integer.valueOf(getSort()),
            getType(),
            getId(),
            id);
    views.put("page", getPage());
    write(objectMapper.writeValueAsString(views));
  }

  @Getter @Setter private int v;

  public void groupshoplist() throws IOException {
    Integer id;
    if (getPurchase() == null) {
      id = -1;
    } else {
      id = getPurchase().getPkey();
    }
    if (lang != null) {
      FldLanguage.Language language = FldLanguage.Language.valueOf(lang);
      if (language != null) setCurLanguage(language);
    }
    if (v == 2) {
      write(prmGroupPurchaseLineDAO.getgroupshoplist2(id));
    } else {
      write(prmGroupPurchaseLineDAO.getgroupshoplist(id));
    }
  }
}
