package irille.homeAction.pdt.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import irille.view.O2O.O2OMapView;
import irille.view.pdt.PdtTieredPricingView;
import irille.view.v3.svs.SvsRatingAndRosDTO;

public class ProductInfoView implements BaseView {
  /** ===============O2O INFO START=============== */
  private O2OMapView map;

  public O2OMapView getMap() {
    return map;
  }

  public void setMap(O2OMapView map) {
    this.map = map;
  }

  /** ===============O2O INFO END=============== */
  // 商家Id
  private long supId;
  //
  private String supName;
  //
  private String pdtName;
  //
  private String logo;
  //
  private String ad;
  private int authTime;
  //
  private long pdtId;
  // 产品类型
  private Byte type;
  //
  private String itemCode;
  // 剩余库存:产品表的stock字段
  private int stock;

  // 商品主图片
  private List<String> pdtImg;
  // 价格
  private BigDecimal price;
  private String currency_unit;
  private String currency_symbol;

  // 规格
  private Map spec;
  private String specJson;
  // 规格根据颜色来分隔的图片
  private String colorImageJson;
  // 产品图片的json格式
  private String productImageJson;
  // 详细描述
  private String description;
  // 起购数量
  private Integer min_oq;
  // 最大
  private Integer max_oq;
  // 规格表
  private Map specifications;
  // 商品详情页tab
  private List tab;
  // 评论
  private List comment;

  // 商家SVS信息
  private SvsRatingAndRosDTO svsInfo;

  /** * 根据现有的逻辑 商品的库存就是个个规格相加的库存了 */
  // 剩余库存:每个规格的库存累加所得
  private Integer qty_num;
  // 面包屑导航
  private List breadcrumbNav;
  private int favorite_count;
  private BigDecimal height;
  private BigDecimal length;
  private BigDecimal weight;
  private BigDecimal width;

  // 你可能喜欢
  private List youMayLike;

  // 评论总数及评分
  private long commentTotal;
  private long satisfaction;

  private boolean isFavorite;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String seoTitle;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String seoKeywords;

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String seoDescription;

  private String rewrite;
  ////////////////// 3.1
  // 阶梯价
  private List<PdtTieredPricingView> tpView;
  // 描述模板
  private List<String> desModule;
  /*  //国家代码
  private String countryCode;
  //国旗
  private String banner;
  //证书
  private boolean credential;
  //svs等级
  private Integer svs;
  //R&D研发能力
  private BigDecimal RD;
  //产量
  private BigDecimal outPut;
  //商家规模
  private BigDecimal scale;
  //国家
  private String country;
  //省
  private String province;*/

  public List<String> getDesModule() {
    return desModule;
  }

  public SvsRatingAndRosDTO getSvsInfo() {
    return svsInfo;
  }

  public void setSvsInfo(SvsRatingAndRosDTO svsInfo) {
    this.svsInfo = svsInfo;
  }

  public void setDesModule(List<String> desModule) {
    this.desModule = desModule;
  }

  public List<PdtTieredPricingView> getTpView() {
    return tpView;
  }

  public void setTpView(List<PdtTieredPricingView> tpView) {
    this.tpView = tpView;
  }

  public String getRewrite() {
    return rewrite;
  }

  public void setRewrite(String rewrite) {
    this.rewrite = rewrite;
  }

  public String getSeoDescription() {
    return seoDescription;
  }

  public void setSeoDescription(String seoDescription) {
    this.seoDescription = seoDescription;
  }

  public String getSeoTitle() {
    return seoTitle;
  }

  public void setSeoTitle(String seoTitle) {
    this.seoTitle = seoTitle;
  }

  public String getSeoKeywords() {
    return seoKeywords;
  }

  public void setSeoKeywords(String seoKeywords) {
    this.seoKeywords = seoKeywords;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public ProductInfoView setFavorite(boolean favorite) {
    isFavorite = favorite;
    return this;
  }

  public Integer getQty_num() {
    return stock;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public int getFavorite_count() {
    return favorite_count;
  }

  public void setFavorite_count(int favorite_count) {
    this.favorite_count = favorite_count;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getAuthTime() {
    return authTime;
  }

  public void setAuthTime(int authTime) {
    this.authTime = authTime;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public ProductInfoView setMinoq(Integer min_oq) {
    this.min_oq = min_oq;
    return this;
  }

  public ProductInfoView setMaxoq(Integer max_oq) {
    this.max_oq = max_oq;
    return this;
  }

  public ProductInfoView setQtynum(Integer qty_num) {
    this.qty_num = qty_num;
    return this;
  }

  public ProductInfoView setFavoritecount(int favorite_count) {
    this.favorite_count = favorite_count;
    return this;
  }

  public long getCommentTotal() {
    return commentTotal;
  }

  public ProductInfoView setCommentTotal(long commentTotal) {
    this.commentTotal = commentTotal;
    return this;
  }

  public long getSatisfaction() {
    return satisfaction;
  }

  public ProductInfoView setSatisfaction(long satisfaction) {
    this.satisfaction = satisfaction;
    return this;
  }

  public String getCurrency_unit() {
    return currency_unit;
  }

  public void setCurrency_unit(String currency_unit) {
    this.currency_unit = currency_unit;
  }

  public Map getSpecifications() {
    return specifications;
  }

  public void setSpecifications(Map specifications) {
    this.specifications = specifications;
  }

  public List getTab() {
    return tab;
  }

  public void setTab(List tab) {
    this.tab = tab;
  }

  public List getComment() {
    return comment;
  }

  public void setComment(List comment) {
    this.comment = comment;
  }

  public long getSupId() {
    return supId;
  }

  public void setSupId(long supId) {
    this.supId = supId;
  }

  public String getSupName() {
    return supName;
  }

  public void setSupName(String supName) {
    this.supName = supName;
  }

  public String getPdtName() {
    return pdtName;
  }

  public void setPdtName(String pdtName) {
    this.pdtName = pdtName;
  }

  public String getLogo() {
    return logo;
  }

  public String getCurrency_symbol() {
    return currency_symbol;
  }

  public void setCurrency_symbol(String currency_symbol) {
    this.currency_symbol = currency_symbol;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getAd() {
    return ad;
  }

  public void setAd(String ad) {
    this.ad = ad;
  }

  public long getPdtId() {
    return pdtId;
  }

  public void setPdtId(long pdtId) {
    this.pdtId = pdtId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getPdtImg() {
    return pdtImg;
  }

  public void setPdtImg(List<String> pdtImg) {
    this.pdtImg = pdtImg;
  }

  public void setPdtImg(String pdtImg) {
    List<String> list = Arrays.asList(pdtImg.split(","));
    this.pdtImg = list;
  }

  public BigDecimal getHeight() {
    return height;
  }

  public void setHeight(BigDecimal height) {
    this.height = height;
  }

  public BigDecimal getLength() {
    return length;
  }

  public void setLength(BigDecimal length) {
    this.length = length;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getWidth() {
    return width;
  }

  public void setWidth(BigDecimal width) {
    this.width = width;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public Integer getMin_oq() {
    return min_oq;
  }

  public void setMin_oq(Integer min_oq) {
    this.min_oq = min_oq;
  }

  public Integer getMax_oq() {
    return max_oq;
  }

  public void setMax_oq(Integer max_oq) {
    this.max_oq = max_oq;
  }

  public List getBreadcrumbNav() {
    return breadcrumbNav;
  }

  public void setBreadcrumbNav(List breadcrumbNav) {
    this.breadcrumbNav = breadcrumbNav;
  }

  public Map getSpec() {
    return spec;
  }

  public ProductInfoView setSpec(Map spec) {
    this.spec = spec;
    return this;
  }

  public String getSpecJson() {
    return specJson;
  }

  public ProductInfoView setSpecJson(String specJson) {
    this.specJson = specJson;
    return this;
  }

  public List getYouMayLike() {
    return youMayLike;
  }

  public ProductInfoView setYouMayLike(List youMayLike) {
    this.youMayLike = youMayLike;
    return this;
  }

  public String getColorImageJson() {
    return colorImageJson;
  }

  public void setColorImageJson(String colorImageJson) {
    this.colorImageJson = colorImageJson;
  }

  public String getProductImageJson() {
    return productImageJson;
  }

  public void setProductImageJson(String productImageJson) {
    this.productImageJson = productImageJson;
  }
}
