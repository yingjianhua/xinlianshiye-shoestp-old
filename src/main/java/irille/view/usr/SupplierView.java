package irille.view.usr;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import irille.homeAction.usr.dto.PdtView;
import irille.pub.bean.BeanBase;
import irille.pub.i18n.I18NFieldSerializer;
import irille.shop.usr.UsrSupIm;
import irille.shop.usr.UsrSupplier;
import irille.view.BaseView;
import lombok.Getter;
import lombok.Setter;

public class SupplierView implements BaseView {

  public UsrSupplier toBean() {
    UsrSupplier bean = new UsrSupplier();
    bean.init();
    bean.setName(name);
    bean.setCompanyNature(companyNature);
    bean.setCompanyType(companyType);
    bean.setCategory(category);
    bean.setWebsite(webSite);
    bean.setCountry(country);
    bean.setProvince(province);
    bean.setCity(city);
    bean.setCompanyAddr(companyAddr);
    bean.setTelephone(telephone);
    bean.setEmail(email);
    bean.setFax(fax);
    bean.setRegisteredCapital(registeredCapital);
    bean.setCreditCode(creditCode);
    bean.setEntity(entity);
    bean.setBusinessLicenseBeginTime(businessLicenseBeginTime);
    bean.setBusinessLicenseEndTime(businessLicenseEndTime);
    bean.setBusinessLicenseIsSecular(BeanBase.booleanToByte(businessLicenseIsSecular));
    bean.setTaxpayerType(taxpayerType);
    bean.setContacts(contacts);
    bean.setPhone(phone);
    bean.setSettlementBank(settlementBank);
    bean.setBankAccount(bankAccount);
    bean.setBankBranch(bankBranch);
    bean.setBankCountry(bankCountry);
    bean.setBankProvince(bankProvince);
    bean.setCertPhoto(certPhoto);
    bean.setIdCard(idCard);
    bean.setIdCardFrontPhoto(idCardFrontPhoto);
    bean.setIdCardBackPhoto(idCardBackPhoto);
    bean.setOperateIdCard(operateIdCard);
    bean.setContactsIdCardFrontPhoto(contactsIdCardFrontPhoto);
    bean.setContactsIdCardBackPhoto(contactsIdCardBackPhoto);
    bean.stHomePageOn(false);
    bean.stContactPageOn(false);
    bean.stProductPageOn(false);
    bean.setRowVersion((short) 0);
    return bean;
  }

  private Integer pkey; // 编号 INT
  private Integer role; // 供应商角色 <表主键:UsrSupplierRole> INT
  private String loginName; // 登录账号 STR(20)
  private String password; // 密码 STR(40)<null>
  private Byte status; // 状态 <OStatus> BYTE
  // INIT:0,未审核
  // APPR:1,已审核
  private Integer apprBy; // 审核员 <表主键:SysUser> INT<null>
  private Date apprTime; // 审核时间 TIME<null>
  private String name; // 名称 STR(100)
  private String registeredCapital; // 注册资金 STR(100)
  private Integer category; // 供应商分类 <表主键:UsrSupplierCategory> INT
  private Byte isAuth; // 供应商认证 <OIsAuth> BYTE
  // NO:0,未认证
  // YES:1,已认证
  private Integer sort; // 排序号 INT
  private String seoTitleEn; // 店铺关键字 STR(100)<null>
  private String seoContentEn; // 搜索引擎说明 STR(100)<null>
  private Date authTime; // 认证时间 TIME<null>
  private String showName; // 网站显示名称 STR(100)
  private String entity; // 企业法人 STR(100)

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String companyType; // 企业类型 JSONOBJECT<null>

  private String companyNature; // 企业性质 JSONOBJECT<null>
  private String creditCode; // 信用代码 STR(100)<null>
  private Date companyEstablishTime; // 成立时间 DATE<null>
  private String operationTerm; // 业务期限 STR(100)<null>
  private String mainSalesArea; // 主销售区 JSONOBJECT<null>
  private String mainProd; // 主要产品 JSONOBJECT<null>
  private String prodPattern; // 生产模式 JSONOBJECT<null>

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String companyAddr; // 地址 JSONOBJECT<null>

  private String des; // 描述 STR(200)<null>
  private String email; // Email STR(100)
  private String businessLicenseBeginTime; // 营业执照开始时间 STR(100)<null>
  private String businessLicenseEndTime; // 营业执照到期时间 STR(100)<null>
  private Boolean businessLicenseIsSecular; // 是否长期 <OYn> BYTE
  // YES:1,是
  // NO:0,否
  private String telephone; // 电话 STR(20)<null>
  private String fax; // 传真 STR(20)<null>
  private String qq; // QQ STR(100)<null>
  private String certPhoto; // 资质证书 STR(100)<null>
  private String idCardFrontPhoto; // 身份证正面 STR(100)<null>
  private String idCardBackPhoto; // 身份证反面 STR(100)<null>
  private String coopCertPhoto; // 合作凭证 STR(100)<null>
  private String taxpayerType; // 纳税人类型 STR(100)<null>
  private String idCard; // 法人身份证号码 STR(100)<null>
  private String operateIdCard; // 运营身份证号码 STR(100)<null>
  private String contacts; // 联系人 STR(40)<null>
  private String phone; // 手机 STR(20)<null>
  private String settlementBank; // 结算开户行 STR(100)<null>
  private String bankAccount; // 银行账号 STR(100)<null>
  private String bankBranch; // 银行开户行 STR(100)<null>
  private Integer bankCountry; // 国家管理 <表主键:PltCountry> INT
  private Integer bankProvince; // 省份 <表主键:PltProvince> INT
  private String contactsIdCardFrontPhoto; // 运营负责人身份证正面 STR(100)<null>
  private String contactsIdCardBackPhoto; // 运营负责人身份证反面 STR(100)<null>
  private String businessTyp; // Businesstyp JSONOBJECT<null>
  private String location; // Location JSONOBJECT<null>
  private String production; // Production JSONOBJECT<null>
  private String developer; // Developer JSONOBJECT<null>
  private String totalEmployees; // Totalemployees JSONOBJECT<null>
  private String annualSales; // Annualsales JSONOBJECT<null>
  private String top3Markets; // Top 3 Markets JSONOBJECT<null>
  private String materials; // Materials JSONOBJECT<null>
  private String headPic; // 头像 STR(100)<null>
  private String department; // Department JSONOBJECT<null>
  private String jobTitle; // JobTitle JSONOBJECT<null>
  private String website; // Website STR(100)<null>
  private Integer country; // 国家管理 <表主键:PltCountry> INT
  private Integer province; // 省份 <表主键:PltProvince> INT
  private String city; // City JSONOBJECT<null>
  private Byte isPro; // 供应商首页产品展示 <OYn> BYTE
  // YES:1,是
  // NO:0,否
  private String logo; // logo STR(200)<null>
  private String signBackgd; // 店招背景 STR(200)<null>

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String adPhoto; // 广告图 STR(200)<null>

  private String adPhotoMobile; // 移动端广告图 STR(200)<null>

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String adPhotoLink; // 广告连接 STR(200)<null>

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String companyPhoto; // 企业图片 STR(200)<null>

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String companyPhotoLink; // 企业图片连接 STR(200)<null>

  @JsonSerialize(using = I18NFieldSerializer.class)
  private String homePageDiy; // 首页个性装修 JSONOBJECT<null>

  private String productPageDiy; // 产品页个性装修 JSONOBJECT<null>
  private String contactPageDiy; // 联系页个性装修 JSONOBJECT<null>
  private String homePageDiyMobile; // 首页个性装修（移动） STR(1000)<null>
  private String productPageDiyMobile; // 产品页个性装修（移动） STR(1000)<null>
  private String contactPageDiyMobile; // 联系页个性装修（移动） STR(1000)<null>
  private String traceCode; // 跟踪代码 STR(100)<null>
  private String webSizeTitle; // 自定义链接名称 STR(100)<null>
  private String webSite; // 网址 STR(100)<null>
  private String tongjiUrl; // 第三方统计地址 STR(200)<null>
  private String tongjiPwd; // 第三方统计密码 STR(40)<null>
  private Date updateTime; // 更新时间 TIME
  private Short rowVersion; // 版本 SHORT

  // ================<2018-9-29 && new>==================
  private boolean prmAuthrity; // 联合采购权限
  private int authAge; // 认证年限
  private String countryName; // 公司所在地国家名称
  private String provinceName;
  private Byte homePageOn; // 首页个性装修开关 <OYn>  BYTE
  private Byte productPageOn; // 产品页个性装修开关 <OYn>  BYTE
  private Byte contactPageOn; // 联系页个性装修开关 <OYn>  BYTE
  private List<PdtView> productList; // 新品展示
  private List<UsrSupIm> imList; // 线上聊天工具

  @Getter @Setter private String targetedMarkets; // 目标市场TARGETED_MARKET
  @Getter @Setter private String AnnualOutput;
  @Getter @Setter private String Name; // 联系人CONTACTS
  // SVS  research  isTeam   numOfShoes
  @Getter @Setter private String rDdepartment; // 独立研发团队RESEARCH
  @Getter @Setter private String AnnualNumberOfNewShoes; // 年开发鞋款数量
  // productionCapacity   productionLineQuantity  needleCartNum  exportVolume
  @Getter @Setter private String NumberOfProductionLines; // 生产线数量
  @Getter @Setter private String NumberOfSewingMachines; // 针车数量
  @Getter @Setter private String AnnualExportValue; // 年出口额
  //  realFactory   employeesNum   licence
  @Getter @Setter private String NumberOfEmployees; // 员工人数TOTAL_EMPLOYEES
  @Getter @Setter private String ExportLicense; // 出口许可证:有/无
  //  productQuality  testEquipment
  @Getter @Setter private String TestEquipmentAndFacilities; // 测试设备:有/无
  // tradeTeam   teamSize  experience
  @Getter @Setter private String NumberOfForeignTradeTeams; // 外贸团队人数
  @Getter @Setter private String YearsOfForeignTradeExperience; // 外贸经验
  @Getter @Setter private Integer statusAuth; // SVS认证状态;未认证:0,认证成功:1

  public List<UsrSupIm> getImList() {
    return imList;
  }

  public void setImList(List<UsrSupIm> imList) {
    this.imList = imList;
  }

  public List<PdtView> getProductList() {
    return productList;
  }

  public void setProductList(List<PdtView> productList) {
    this.productList = productList;
  }

  public Byte getHomePageOn() {
    return homePageOn;
  }

  public void setHomePageOn(Byte homePageOn) {
    this.homePageOn = homePageOn;
  }

  public Byte getProductPageOn() {
    return productPageOn;
  }

  public void setProductPageOn(Byte productPageOn) {
    this.productPageOn = productPageOn;
  }

  public Byte getContactPageOn() {
    return contactPageOn;
  }

  public void setContactPageOn(Byte contactPageOn) {
    this.contactPageOn = contactPageOn;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public int getAuthAge() {
    return authAge;
  }

  public void setAuthAge(int authAge) {
    this.authAge = authAge;
  }

  public boolean isPrmAuthrity() {
    return prmAuthrity;
  }

  public void setPrmAuthrity(boolean prmAuthrity) {
    this.prmAuthrity = prmAuthrity;
  }
  // ================<2018-9-29 && end>==================

  public Integer getPkey() {
    return pkey;
  }

  public void setPkey(Integer pkey) {
    this.pkey = pkey;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Integer getApprBy() {
    return apprBy;
  }

  public void setApprBy(Integer apprBy) {
    this.apprBy = apprBy;
  }

  public Date getApprTime() {
    return apprTime;
  }

  public void setApprTime(Date apprTime) {
    this.apprTime = apprTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegisteredCapital() {
    return registeredCapital;
  }

  public void setRegisteredCapital(String registeredCapital) {
    this.registeredCapital = registeredCapital;
  }

  public Integer getCategory() {
    return category;
  }

  public void setCategory(Integer category) {
    this.category = category;
  }

  public Byte getIsAuth() {
    return isAuth;
  }

  public void setIsAuth(Byte isAuth) {
    this.isAuth = isAuth;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public String getSeoTitleEn() {
    return seoTitleEn;
  }

  public void setSeoTitleEn(String seoTitleEn) {
    this.seoTitleEn = seoTitleEn;
  }

  public String getSeoContentEn() {
    return seoContentEn;
  }

  public void setSeoContentEn(String seoContentEn) {
    this.seoContentEn = seoContentEn;
  }

  public Date getAuthTime() {
    return authTime;
  }

  public void setAuthTime(Date authTime) {
    this.authTime = authTime;
  }

  public String getShowName() {
    return showName;
  }

  public void setShowName(String showName) {
    this.showName = showName;
  }

  public String getEntity() {
    return entity;
  }

  public void setEntity(String entity) {
    this.entity = entity;
  }

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String getCompanyNature() {
    return companyNature;
  }

  public void setCompanyNature(String companyNature) {
    this.companyNature = companyNature;
  }

  public String getCreditCode() {
    return creditCode;
  }

  public void setCreditCode(String creditCode) {
    this.creditCode = creditCode;
  }

  public Date getCompanyEstablishTime() {
    return companyEstablishTime;
  }

  public void setCompanyEstablishTime(Date companyEstablishTime) {
    this.companyEstablishTime = companyEstablishTime;
  }

  public String getOperationTerm() {
    return operationTerm;
  }

  public void setOperationTerm(String operationTerm) {
    this.operationTerm = operationTerm;
  }

  public String getMainSalesArea() {
    return mainSalesArea;
  }

  public void setMainSalesArea(String mainSalesArea) {
    this.mainSalesArea = mainSalesArea;
  }

  public String getMainProd() {
    return mainProd;
  }

  public void setMainProd(String mainProd) {
    this.mainProd = mainProd;
  }

  public String getProdPattern() {
    return prodPattern;
  }

  public void setProdPattern(String prodPattern) {
    this.prodPattern = prodPattern;
  }

  public String getCompanyAddr() {
    return companyAddr;
  }

  public void setCompanyAddr(String companyAddr) {
    this.companyAddr = companyAddr;
  }

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBusinessLicenseBeginTime() {
    return businessLicenseBeginTime;
  }

  public void setBusinessLicenseBeginTime(String businessLicenseBeginTime) {
    this.businessLicenseBeginTime = businessLicenseBeginTime;
  }

  public String getBusinessLicenseEndTime() {
    return businessLicenseEndTime;
  }

  public void setBusinessLicenseEndTime(String businessLicenseEndTime) {
    this.businessLicenseEndTime = businessLicenseEndTime;
  }

  public Boolean getBusinessLicenseIsSecular() {
    return businessLicenseIsSecular;
  }

  public void setBusinessLicenseIsSecular(Boolean businessLicenseIsSecular) {
    this.businessLicenseIsSecular = businessLicenseIsSecular;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getCertPhoto() {
    return certPhoto;
  }

  public void setCertPhoto(String certPhoto) {
    this.certPhoto = certPhoto;
  }

  public String getIdCardFrontPhoto() {
    return idCardFrontPhoto;
  }

  public void setIdCardFrontPhoto(String idCardFrontPhoto) {
    this.idCardFrontPhoto = idCardFrontPhoto;
  }

  public String getIdCardBackPhoto() {
    return idCardBackPhoto;
  }

  public void setIdCardBackPhoto(String idCardBackPhoto) {
    this.idCardBackPhoto = idCardBackPhoto;
  }

  public String getCoopCertPhoto() {
    return coopCertPhoto;
  }

  public void setCoopCertPhoto(String coopCertPhoto) {
    this.coopCertPhoto = coopCertPhoto;
  }

  public String getTaxpayerType() {
    return taxpayerType;
  }

  public void setTaxpayerType(String taxpayerType) {
    this.taxpayerType = taxpayerType;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getOperateIdCard() {
    return operateIdCard;
  }

  public void setOperateIdCard(String operateIdCard) {
    this.operateIdCard = operateIdCard;
  }

  public String getContacts() {
    return contacts;
  }

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSettlementBank() {
    return settlementBank;
  }

  public void setSettlementBank(String settlementBank) {
    this.settlementBank = settlementBank;
  }

  public String getBankAccount() {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount) {
    this.bankAccount = bankAccount;
  }

  public String getBankBranch() {
    return bankBranch;
  }

  public void setBankBranch(String bankBranch) {
    this.bankBranch = bankBranch;
  }

  public Integer getBankCountry() {
    return bankCountry;
  }

  public void setBankCountry(Integer bankCountry) {
    this.bankCountry = bankCountry;
  }

  public Integer getBankProvince() {
    return bankProvince;
  }

  public void setBankProvince(Integer bankProvince) {
    this.bankProvince = bankProvince;
  }

  public String getContactsIdCardFrontPhoto() {
    return contactsIdCardFrontPhoto;
  }

  public void setContactsIdCardFrontPhoto(String contactsIdCardFrontPhoto) {
    this.contactsIdCardFrontPhoto = contactsIdCardFrontPhoto;
  }

  public String getContactsIdCardBackPhoto() {
    return contactsIdCardBackPhoto;
  }

  public void setContactsIdCardBackPhoto(String contactsIdCardBackPhoto) {
    this.contactsIdCardBackPhoto = contactsIdCardBackPhoto;
  }

  public String getBusinessTyp() {
    return businessTyp;
  }

  public void setBusinessTyp(String businessTyp) {
    this.businessTyp = businessTyp;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getProduction() {
    return production;
  }

  public void setProduction(String production) {
    this.production = production;
  }

  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public String getTotalEmployees() {
    return totalEmployees;
  }

  public void setTotalEmployees(String totalEmployees) {
    this.totalEmployees = totalEmployees;
  }

  public String getAnnualSales() {
    return annualSales;
  }

  public void setAnnualSales(String annualSales) {
    this.annualSales = annualSales;
  }

  public String getTop3Markets() {
    return top3Markets;
  }

  public void setTop3Markets(String top3Markets) {
    this.top3Markets = top3Markets;
  }

  public String getMaterials() {
    return materials;
  }

  public void setMaterials(String materials) {
    this.materials = materials;
  }

  public String getHeadPic() {
    return headPic;
  }

  public void setHeadPic(String headPic) {
    this.headPic = headPic;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public Integer getCountry() {
    return country;
  }

  public void setCountry(Integer country) {
    this.country = country;
  }

  public Integer getProvince() {
    return province;
  }

  public void setProvince(Integer province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Byte getIsPro() {
    return isPro;
  }

  public void setIsPro(Byte isPro) {
    this.isPro = isPro;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getSignBackgd() {
    return signBackgd;
  }

  public void setSignBackgd(String signBackgd) {
    this.signBackgd = signBackgd;
  }

  public String getAdPhoto() {
    return adPhoto;
  }

  public void setAdPhoto(String adPhoto) {
    this.adPhoto = adPhoto;
  }

  public String getAdPhotoMobile() {
    return adPhotoMobile;
  }

  public void setAdPhotoMobile(String adPhotoMobile) {
    this.adPhotoMobile = adPhotoMobile;
  }

  public String getAdPhotoLink() {
    return adPhotoLink;
  }

  public void setAdPhotoLink(String adPhotoLink) {
    this.adPhotoLink = adPhotoLink;
  }

  public String getCompanyPhoto() {
    return companyPhoto;
  }

  public void setCompanyPhoto(String companyPhoto) {
    this.companyPhoto = companyPhoto;
  }

  public String getCompanyPhotoLink() {
    return companyPhotoLink;
  }

  public void setCompanyPhotoLink(String companyPhotoLink) {
    this.companyPhotoLink = companyPhotoLink;
  }

  public String getHomePageDiy() {
    return homePageDiy;
  }

  public void setHomePageDiy(String homePageDiy) {
    this.homePageDiy = homePageDiy;
  }

  public String getProductPageDiy() {
    return productPageDiy;
  }

  public void setProductPageDiy(String productPageDiy) {
    this.productPageDiy = productPageDiy;
  }

  public String getContactPageDiy() {
    return contactPageDiy;
  }

  public void setContactPageDiy(String contactPageDiy) {
    this.contactPageDiy = contactPageDiy;
  }

  public String getHomePageDiyMobile() {
    return homePageDiyMobile;
  }

  public void setHomePageDiyMobile(String homePageDiyMobile) {
    this.homePageDiyMobile = homePageDiyMobile;
  }

  public String getProductPageDiyMobile() {
    return productPageDiyMobile;
  }

  public void setProductPageDiyMobile(String productPageDiyMobile) {
    this.productPageDiyMobile = productPageDiyMobile;
  }

  public String getContactPageDiyMobile() {
    return contactPageDiyMobile;
  }

  public void setContactPageDiyMobile(String contactPageDiyMobile) {
    this.contactPageDiyMobile = contactPageDiyMobile;
  }

  public String getTraceCode() {
    return traceCode;
  }

  public void setTraceCode(String traceCode) {
    this.traceCode = traceCode;
  }

  public String getWebSizeTitle() {
    return webSizeTitle;
  }

  public void setWebSizeTitle(String webSizeTitle) {
    this.webSizeTitle = webSizeTitle;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getTongjiUrl() {
    return tongjiUrl;
  }

  public void setTongjiUrl(String tongjiUrl) {
    this.tongjiUrl = tongjiUrl;
  }

  public String getTongjiPwd() {
    return tongjiPwd;
  }

  public void setTongjiPwd(String tongjiPwd) {
    this.tongjiPwd = tongjiPwd;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public Short getRowVersion() {
    return rowVersion;
  }

  public void setRowVersion(Short rowVersion) {
    this.rowVersion = rowVersion;
  }

  @Override
  public String toString() {
    return "SupplierView{"
        + "pkey="
        + pkey
        + ", role="
        + role
        + ", loginName='"
        + loginName
        + '\''
        + ", password='"
        + password
        + '\''
        + ", status="
        + status
        + ", apprBy="
        + apprBy
        + ", apprTime="
        + apprTime
        + ", name='"
        + name
        + '\''
        + ", registeredCapital='"
        + registeredCapital
        + '\''
        + ", category="
        + category
        + ", isAuth="
        + isAuth
        + ", sort="
        + sort
        + ", seoTitleEn='"
        + seoTitleEn
        + '\''
        + ", seoContentEn='"
        + seoContentEn
        + '\''
        + ", authTime="
        + authTime
        + ", showName='"
        + showName
        + '\''
        + ", entity='"
        + entity
        + '\''
        + ", companyType='"
        + companyType
        + '\''
        + ", companyNature='"
        + companyNature
        + '\''
        + ", creditCode='"
        + creditCode
        + '\''
        + ", companyEstablishTime="
        + companyEstablishTime
        + ", operationTerm='"
        + operationTerm
        + '\''
        + ", mainSalesArea='"
        + mainSalesArea
        + '\''
        + ", mainProd='"
        + mainProd
        + '\''
        + ", prodPattern='"
        + prodPattern
        + '\''
        + ", companyAddr='"
        + companyAddr
        + '\''
        + ", des='"
        + des
        + '\''
        + ", email='"
        + email
        + '\''
        + ", businessLicenseBeginTime='"
        + businessLicenseBeginTime
        + '\''
        + ", businessLicenseEndTime='"
        + businessLicenseEndTime
        + '\''
        + ", businessLicenseIsSecular="
        + businessLicenseIsSecular
        + ", telephone='"
        + telephone
        + '\''
        + ", fax='"
        + fax
        + '\''
        + ", qq='"
        + qq
        + '\''
        + ", certPhoto='"
        + certPhoto
        + '\''
        + ", idCardFrontPhoto='"
        + idCardFrontPhoto
        + '\''
        + ", idCardBackPhoto='"
        + idCardBackPhoto
        + '\''
        + ", coopCertPhoto='"
        + coopCertPhoto
        + '\''
        + ", taxpayerType='"
        + taxpayerType
        + '\''
        + ", idCard='"
        + idCard
        + '\''
        + ", operateIdCard='"
        + operateIdCard
        + '\''
        + ", contacts='"
        + contacts
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", settlementBank='"
        + settlementBank
        + '\''
        + ", bankAccount='"
        + bankAccount
        + '\''
        + ", bankBranch='"
        + bankBranch
        + '\''
        + ", bankCountry="
        + bankCountry
        + ", bankProvince="
        + bankProvince
        + ", contactsIdCardFrontPhoto='"
        + contactsIdCardFrontPhoto
        + '\''
        + ", contactsIdCardBackPhoto='"
        + contactsIdCardBackPhoto
        + '\''
        + ", businessTyp='"
        + businessTyp
        + '\''
        + ", location='"
        + location
        + '\''
        + ", production='"
        + production
        + '\''
        + ", developer='"
        + developer
        + '\''
        + ", totalEmployees='"
        + totalEmployees
        + '\''
        + ", annualSales='"
        + annualSales
        + '\''
        + ", top3Markets='"
        + top3Markets
        + '\''
        + ", materials='"
        + materials
        + '\''
        + ", headPic='"
        + headPic
        + '\''
        + ", department='"
        + department
        + '\''
        + ", jobTitle='"
        + jobTitle
        + '\''
        + ", website='"
        + website
        + '\''
        + ", country="
        + country
        + ", province="
        + province
        + ", city='"
        + city
        + '\''
        + ", isPro="
        + isPro
        + ", logo='"
        + logo
        + '\''
        + ", signBackgd='"
        + signBackgd
        + '\''
        + ", adPhoto='"
        + adPhoto
        + '\''
        + ", adPhotoMobile='"
        + adPhotoMobile
        + '\''
        + ", adPhotoLink='"
        + adPhotoLink
        + '\''
        + ", companyPhoto='"
        + companyPhoto
        + '\''
        + ", companyPhotoLink='"
        + companyPhotoLink
        + '\''
        + ", homePageDiy='"
        + homePageDiy
        + '\''
        + ", productPageDiy='"
        + productPageDiy
        + '\''
        + ", contactPageDiy='"
        + contactPageDiy
        + '\''
        + ", homePageDiyMobile='"
        + homePageDiyMobile
        + '\''
        + ", productPageDiyMobile='"
        + productPageDiyMobile
        + '\''
        + ", contactPageDiyMobile='"
        + contactPageDiyMobile
        + '\''
        + ", traceCode='"
        + traceCode
        + '\''
        + ", webSizeTitle='"
        + webSizeTitle
        + '\''
        + ", webSite='"
        + webSite
        + '\''
        + ", tongjiUrl='"
        + tongjiUrl
        + '\''
        + ", tongjiPwd='"
        + tongjiPwd
        + '\''
        + ", updateTime="
        + updateTime
        + ", rowVersion="
        + rowVersion
        + ", prmAuthrity="
        + prmAuthrity
        + ", authAge="
        + authAge
        + ", countryName='"
        + countryName
        + '\''
        + ", provinceName='"
        + provinceName
        + '\''
        + ", homePageOn="
        + homePageOn
        + ", productPageOn="
        + productPageOn
        + ", contactPageOn="
        + contactPageOn
        + ", productList="
        + productList
        + ", imList="
        + imList
        + '}';
  }
}
