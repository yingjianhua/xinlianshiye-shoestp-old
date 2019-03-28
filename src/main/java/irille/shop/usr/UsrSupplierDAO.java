package irille.shop.usr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.xinlianshiye.shoestp.plat.service.pm.IPMMessageService;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMMessageServiceImp;

import irille.Dao.SVS.SVSInfoDao;
import irille.Dao.SVS.SVSInfoService;
import irille.Dao.SVS.impl.SVSInfoDaoImpl;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.pm.PM.OTempType;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.Page_supplierProductView;
import irille.homeAction.usr.dto.SupplierListView;
import irille.platform.usr.View.UsrSUPSelectView;
import irille.platform.usr.View.UsrSupplierNewView;
import irille.platform.usr.View.UsrSupplierView.*;
import irille.pub.DateTools;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.query.SqlQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.*;
import irille.pub.svr.DbPool;
import irille.pub.svr.Env;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.FldLanguage.Language;
import irille.pub.util.FormaterSql.FormaterSql;
import irille.pub.util.SEOUtils;
import irille.pub.util.TranslateLanguage.translateUtil;
import irille.pub.validate.Valid;
import irille.pub.validate.ValidForm;
import irille.pub.validate.ValidRegex2;
import irille.sellerAction.view.AuthenticationView;
import irille.sellerAction.view.SupinfoView;
import irille.sellerAction.view.operateinfoView;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtProductDAO;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.*;
import irille.shop.plt.PltPay.OPay_Mode;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.UsrSupplier.T;
import irille.view.Page;
import irille.view.SVS.SVSInfoView;
import irille.view.usr.AccountSettingsView;
import irille.view.usr.SupplierDetailsDTO;
import irille.view.usr.SupplierView;
import irille.view.usr.shopSettingView;
import org.json.JSONException;
import org.json.JSONObject;

public class UsrSupplierDAO {

  public static final LogMessage LOG = new LogMessage(UsrSupplierDAO.class);

  /**
   * @Description: 供应商选中器列表平台
   *
   * @date 2019/1/23 19:27
   * @anthor wilson zhang
   */
  public static Page listsupselect(
      String fldvalue, String condition, Integer start, Integer limit) {
    SQL sql =
        new SQL() {
          {
            SELECT(
                T.PKEY,
                T.NAME,
                T.ROLE,
                T.LOGIN_NAME,
                T.CATEGORY,
                T.ENTITY,
                T.COMPANY_TYPE,
                T.COMPANY_NATURE);
            FROM(UsrSupplier.class);
            if (fldvalue != null) {
              if (fldvalue.equalsIgnoreCase("name")) {
                WHERE(T.NAME, "like '%" + condition + "%'");
              }
              if (fldvalue.equalsIgnoreCase("loginName")) {
                WHERE(T.LOGIN_NAME, "like '%" + condition + "%'");
              }
              if (fldvalue.equalsIgnoreCase("entity")) {
                WHERE(T.ENTITY, "like '%" + condition + "%'");
              }
            }
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<UsrSUPSelectView> list =
        irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                bean ->
                    new UsrSUPSelectView() {
                      {
                        setPkey((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
                        setRole(
                            BeanBase.load(
                                    UsrSupplierRole.class,
                                    (Integer) bean.get(T.ROLE.getFld().getCodeSqlField()))
                                .getName());
                        setLoginname((String) bean.get(T.LOGIN_NAME.getFld().getCodeSqlField()));
                        setCategory(
                            BeanBase.load(
                                    UsrSupplierCategory.class,
                                    (Integer) bean.get(T.CATEGORY.getFld().getCodeSqlField()))
                                .getName());
                        setEntity((String) bean.get(T.ENTITY.getFld().getCodeSqlField()));
                        setCompanytype(
                            (String) bean.get(T.COMPANY_TYPE.getFld().getCodeSqlField()));
                        setCompanyNature(
                            (String) bean.get(T.COMPANY_NATURE.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static SupplierView getSupplierInfo(Integer pkey) {
    UsrSupplier bean = UsrSupplier.load(UsrSupplier.class, pkey);
    SupplierView view = new SupplierView();
    view.setName(bean.getName());
    return view;
  }

  /**
   * 商家端,商家账户设置页面需要的信息
   *
   * @author yingjianhua
   */
  public static SupplierView findById4AccountSet(Integer id) {
    UsrSupplier bean = irille.pub.bean.Query.SELECT(UsrSupplier.class, id);
    SupplierView view = new SupplierView();
    view.setPkey(bean.getPkey());
    view.setLoginName(bean.gtUserid().getEmail());
    view.setStatus(bean.getStatus());
    view.setName(bean.getName());
    view.setCompanyType(bean.getCompanyType());
    view.setLocation(bean.getLocation());
    view.setWebsite(bean.getWebSite());

    view.setLogo(bean.getLogo());
    view.setProduction(bean.getProduction());
    view.setDeveloper(bean.getDeveloper());
    view.setTotalEmployees(bean.getTotalEmployees());
    view.setAnnualSales(bean.getAnnualSales());
    view.setTop3Markets(bean.getTop3Markets());
    view.setMaterials(bean.getMaterials());
    view.setHeadPic(bean.getHeadPic());
    view.setDepartment(bean.getDepartment());
    view.setJobTitle(bean.getJobTitle());
    System.out.println(view);
    return view;
  }

  /** @author yingjianhua */
  public static class Query extends IduOther<Query, PdtSpec> {

    public List<UsrSupplier> listByProduct(List<PdtProduct> products) {
      if (products.size() == 0) {
        return new ArrayList<>();
      }
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < products.size(); i++) {
        if (i != 0) {
          b.append(",");
        }
        b.append(products.get(i).getSupplier());
      }
      String where = UsrSupplier.T.PKEY.getFld().getCodeSqlField() + " in (" + b.toString() + ")";
      return BeanBase.list(UsrSupplier.class, where, false);
    }
  }

  public static class Ins extends IduIns<Ins, UsrSupplier> {

    public String _mm;
    public String _mmcheck;

    @Override
    public void before() {
      super.before();
      validatePw(_mm, _mmcheck);
      getB().stIsPro(true);
      getB().setUpdateTime(Env.getTranBeginTime());
    }

    @Override
    public void after() {
      super.after();
      getB().setPassword(inCode(getB().getPkey() + _mm));
      getB().upd();
    }
  }

  /**
   * * 前端供应商列表页面查询类
   *
   * @author lijie@shoestp.cn
   * @date 2018/7/19 15:51
   */
  public static class pageSelect extends IduOther<pageSelect, UsrSupplier> {

    private final boolean Debug = false;

    public Map SupplierList(IduPage page, int cat, FldLanguage.Language lang) {
      // 获取商品总数及条数
      SQL productSQL =
          new SQL() {
            {
              SELECT("count(*)");
              FROM(PdtProduct.class);
              WHERE(PdtProduct.T.IS_VERIFY, " = " + Sys.OYn.YES.getLine().getKey());
              WHERE(PdtProduct.T.PRODUCT_TYPE, " = " + Pdt.OProductType.GENERAL.getLine().getKey());
              GROUP_BY(PdtProduct.T.SUPPLIER);
              HAVING(
                  PdtProduct.T.SUPPLIER.getFld().getCodeSqlField()
                      + " = "
                      + UsrSupplier.class.getSimpleName()
                      + "."
                      + UsrSupplier.T.PKEY.getFld().getCodeSqlField());
            }
          };
      // 获取供应商
      SQL sql =
          new SQL() {
            {
              SELECT(
                  UsrSupplier.T.PKEY,
                  UsrSupplier.T.LOGO,
                  UsrSupplier.T.NAME,
                  UsrSupplier.T.PROD_PATTERN);
              SELECT("(" + productSQL.toString() + ") as prodCount ");
              FROM(UsrSupplier.class);
              if (cat > 0) {
                WHERE(UsrSupplier.T.CATEGORY, " = " + cat);
              }
              WHERE(T.STATUS, " = ?", OStatus.APPR);
              ORDER_BY(T.SORT, "asc");
              ORDER_BY(T.UPDATE_TIME, "desc");
            }
          };
      Map map = new HashMap();
      map.put("total", irille.pub.bean.Query.sql(sql).queryMaps().size());
      sql.LIMIT(page.getStart(), page.getLimit());

      List<SupplierListView> views =
          irille.pub.bean.Query.sql(sql).queryMaps().stream()
              .map(
                  bean ->
                      new SupplierListView() {
                        {
                          setPkey((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                          setLogo((String) bean.get(T.LOGO.getFld().getCodeSqlField()));
                          setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
                          System.out.println(bean);
                          setProdpattern(
                              (String) bean.get(T.PROD_PATTERN.getFld().getCodeSqlField()));
                          setProDuctCount(
                              Long.valueOf(
                                  (bean.get("prodCount") == null ? 0 : bean.get("prodCount"))
                                      .toString()));
                          SQL prodSQL =
                              new SQL() {
                                {
                                  SELECT(
                                      PdtProduct.T.PKEY, PdtProduct.T.PICTURE, PdtProduct.T.NAME);
                                  FROM(PdtProduct.class);
                                  WHERE(PdtProduct.T.IS_VERIFY, "=?", Sys.OYn.YES);
                                  WHERE(PdtProduct.T.SUPPLIER, "=?", getPkey());
                                  WHERE(PdtProduct.T.PRODUCT_TYPE, "=?", Pdt.OProductType.GENERAL);
                                  ORDER_BY(PdtProduct.T.IS_NEW, " DESC ");
                                  LIMIT(0, 3);
                                }
                              };
                          setProDuctDtos(
                              irille.pub.bean.Query.sql(prodSQL).queryList(PdtProduct.class)
                                  .stream()
                                  .map(
                                      prod ->
                                          new Page_supplierProductView() {
                                            {
                                              translateUtil.getAutoTranslate(prod, lang);
                                              setPkey(prod.getPkey());
                                              String pic =
                                                  prod.getPicture() == null
                                                      ? ""
                                                      : (prod.getPicture().split(",").length > 0
                                                          ? prod.getPicture().split(",")[0]
                                                          : prod.getPicture());
                                              setPicture(pic);
                                              setRewrite(
                                                  SEOUtils.getPdtProductTitle(
                                                      (int) getPkey(), getName()));
                                            }
                                          })
                                  .collect(Collectors.toList()));
                        }
                      })
              .collect(Collectors.toList());

      map.put("items", views);
      return map;
    }

    /**
     * * 获取分类
     *
     * @param
     * @return
     * @author lijie@shoestp.cn
     * @date 2018/7/23 16:58
     */
    public List getCategory() {
      FormaterSql sql = FormaterSql.build(Debug);
      sql.select(UsrSupplierCategory.T.PKEY, UsrSupplierCategory.T.SHOW_NAME);
      return BeanBase.list(sql.buildSql());
    }

    /**
     * * 获取供应商列表
     *
     * @param
     * @return
     * @author lijie@shoestp.cn
     * @date 2018/7/24 16:15
     */
    public String getSupplierList(IduPage page, int cated) throws JsonProcessingException {
      FormaterSql sql = FormaterSql.build();
      sql.select(
              T.PKEY,
              T.COMPANY_PHOTO, // 公司图片
              T.COMPANY_PHOTO_LINK, // 公司外链图片
              T.NAME // 公司名称
              )
          .asc(UsrSupplier.T.SORT)
          .desc(T.UPDATE_TIME)
          .page(page.getStart(), page.getLimit());
      sql.eqAutoAnd(
          T.CATEGORY,
          cated,
          number -> {
            return number.intValue() > 0;
          });
      Map result = new HashMap();
      result.put("total", sql.castLong(BeanBase.queryOneRow(sql.buildCountSql(), sql.getParms())));
      result.put("items", sql.castListMap(BeanBase.list(sql.buildSql(), sql.getParms())));
      return new ObjectMapper().writeValueAsString(result);
    }
  }

  public static void validatePw(String mm, String mmcheck) {
    // TODO 密码暂时不做复杂的限制
    // String ptn = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,}$";
    if (mm == null || mm.length() < 6) {
      throw LOG.err("err", "密码不能小于6位!");
    }
    //		if (mm.matches(ptn) == false)
    //			throw LOG.err("err", "密码必须含有一个字母和一个数字!");
    if (mmcheck == null || mmcheck.equals(mm) == false) {
      throw LOG.err("err", "密码确认不一致");
    }
  }

  public static class UpdBase extends IduUpd<UpdBase, UsrSupplier> {

    @Override
    public void before() {
      super.before();
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME,
          T.CATEGORY,
          T.IS_AUTH,
          T.SORT,
          T.SEO_TITLE,
          T.SEO_CONTENT,
          T.AUTH_TIME,
          T.SHOW_NAME,
          T.ENTITY,
          T.COMPANY_TYPE,
          T.COMPANY_NATURE,
          T.CREDIT_CODE,
          T.COMPANY_ESTABLISH_TIME,
          T.OPERATION_TERM,
          T.MAIN_SALES_AREA,
          T.MAIN_PROD,
          T.PROD_PATTERN,
          T.COMPANY_ADDR,
          T.DES,
          T.CONTACTS,
          T.EMAIL,
          T.PHONE,
          T.TELEPHONE,
          T.FAX,
          T.QQ,
          T.CERT_PHOTO,
          T.ID_CARD_FRONT_PHOTO,
          T.ID_CARD_BACK_PHOTO,
          T.COOP_CERT_PHOTO);
      dbBean.setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);
    }
  }

  public static class UpdPage extends IduUpd<UpdPage, UsrSupplier> {

    @Override
    public void before() {
      super.before();
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.BUSINESS_TYP,
          T.LOCATION,
          T.PRODUCTION,
          T.DEVELOPER,
          T.TOTAL_EMPLOYEES,
          T.ANNUAL_SALES,
          T.TOP_3_MARKETS,
          T.MATERIALS,
          T.HEAD_PIC,
          T.DEPARTMENT,
          T.JOB_TITLE,
          T.WEBSITE,
          T.COUNTRY,
          T.PROVINCE,
          T.CITY,
          T.Is_Pro,
          T.LOGO,
          T.SIGN_BACKGD,
          T.AD_PHOTO,
          T.AD_PHOTO_MOBILE,
          T.AD_PHOTO_LINK,
          T.COMPANY_PHOTO,
          T.COMPANY_PHOTO_LINK);
      dbBean.setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);
    }
  }

  public static class UpdDiy extends IduUpd<UpdDiy, UsrSupplier> {

    @Override
    public void before() {
      super.before();
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.HOME_PAGE_DIY,
          T.PRODUCT_PAGE_DIY,
          T.CONTACT_PAGE_DIY,
          T.HOME_PAGE_DIY_MOBILE,
          T.PRODUCT_PAGE_DIY_MOBILE,
          T.CONTACT_PAGE_DIY_MOBILE);
      dbBean.setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);
    }
  }

  public static class UpdMarketing extends IduUpd<UpdMarketing, UsrSupplier> {

    @Override
    public void before() {
      super.before();
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.TRACE_CODE,
          T.WEB_SIZE_TITLE,
          T.WEB_SITE,
          T.TONGJI_URL,
          T.TONGJI_PWD,
          T.UPDATE_TIME);
      dbBean.setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);
    }
  }

  private static String inCode(String str) {
    return DateTools.getDigest(str.toLowerCase());
  }

  /**
   * 更新店铺设置信息
   *
   * @author GS
   */
  public static class UpdBizDiy extends IduUpd<UpdBase, UsrSupplier> {

    @Override
    public void before() {
      super.before();
      try {
        new JSONObject(getB().getHomePageDiy());
      } catch (JSONException e) {
        try {
          getB().setHomePageDiy(new JSONObject().put("en", getB().getHomePageDiy()).toString());
          getB()
              .setContactPageDiy(new JSONObject().put("en", getB().getContactPageDiy()).toString());
          getB()
              .setProductPageDiy(new JSONObject().put("en", getB().getProductPageDiy()).toString());
        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
      setB(translateUtil.autoTranslate(getB(), true));
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.BUSINESS_TYP,
          T.LOCATION,
          T.PRODUCTION,
          T.DEVELOPER,
          T.TOTAL_EMPLOYEES,
          T.ANNUAL_SALES,
          T.TOP_3_MARKETS,
          T.MATERIALS,
          T.HEAD_PIC,
          T.DEPARTMENT,
          T.JOB_TITLE,
          T.WEB_SITE,
          T.COUNTRY,
          T.PROVINCE,
          T.CITY,
          T.Is_Pro,
          T.LOGO,
          T.SIGN_BACKGD,
          T.AD_PHOTO,
          T.AD_PHOTO_LINK,
          T.AD_PHOTO_MOBILE,
          T.COMPANY_PHOTO,
          T.COMPANY_PHOTO_LINK,
          T.HOME_PAGE_DIY,
          T.PRODUCT_PAGE_DIY,
          T.CONTACT_PAGE_DIY,
          T.HOME_PAGE_DIY_MOBILE,
          T.PRODUCT_PAGE_DIY_MOBILE,
          T.CONTACT_PAGE_DIY_MOBILE,
          T.ROLE,
          T.HOME_PAGE_ON,
          T.PRODUCT_PAGE_ON,
          T.CONTACT_PAGE_ON);
      dbBean.setUpdateTime(Env.getTranBeginTime());
      setB(dbBean);
    }
  }

  /**
   * 更新账户设置
   *
   * @author zjl
   */
  public static void updAccount(Integer pkey, AccountSettingsView asv) {
    UsrSupplier bean = irille.pub.bean.Query.SELECT(UsrSupplier.class, pkey);
    bean.setProduction(asv.getProductionCapacity());
    bean.setDeveloper(asv.getTotalProduction());
    bean.setTotalEmployees(asv.getNumberEmployees());
    bean.setAnnualSales(asv.getAnnualSalesFigure());
    bean.setTop3Markets(asv.getThirdMarket());
    bean.setMaterials(asv.getMaterialSelection());
    bean.setHeadPic(asv.getAvatar());
    bean.setLogo(asv.getLogo());
    bean.setDepartment(asv.getDepartment());
    bean.setJobTitle(asv.getJobTitle());
    bean.setWebsite(asv.getWebsite());
    bean.upd();
  }

  /**
   * 更新商家logo
   *
   * @author yingjianhua
   */
  public static void updLogo(String logo, Integer id) {
    UsrSupplier bean = irille.pub.bean.Query.SELECT(UsrSupplier.class, id);
    bean.setLogo(logo);
    bean.upd();
  }

  /**
   * 获取指定供应商
   *
   * @author liyichao
   */
  public static class GtSup extends IduOther<GtSup, UsrSupplier> {

    private List<PdtProduct> proList;

    public List<PdtProduct> getProList() {
      return proList;
    }

    public void setProList(List<PdtProduct> proList) {
      this.proList = proList;
    }

    @Override
    public void before() {
      super.before();
      proList =
          BeanBase.list(
              PdtProduct.class, PdtProduct.T.SUPPLIER + " in (" + getB().getPkey() + ")", false);
    }
  }

  /*public static void main(String[] args) {
        int pkey = 1;
        String password = "123456";
        System.out.println(inCode(pkey + password));
    	//https://graph.facebook.com/debug_token?access_token=311019632992273%7C191eaf6470de890483ad21946911574c&input_token=EAAEa3uaZBIBEBAPcP3V5yTw3yb4Y6wBIxZCSQIpPaX0dd5ZCf0olpDxGFPE68EVzvXBsgoC910bkMXi6fQ8x95qDHnbB84XMovoWyZC9l5LtvEsU9obn74QjbNsdk6LwkIIpyRTC39Nmi351BuAXMwZCqYGaPa1xUp02TapLbvzin2QUgTx1KWM8lWtYp5KzlYSK1tFKKwRis64w36aBp
        try {
  String result=HttpRequestUtil.sendGet("https://graph.facebook.com/debug_token?access_token=311019632992273%7C191eaf6470de890483ad21946911574c&input_token=EAAEa3uaZBIBEBAPcP3V5yTw3yb4Y6wBIxZCSQIpPaX0dd5ZCf0olpDxGFPE68EVzvXBsgoC910bkMXi6fQ8x95qDHnbB84XMovoWyZC9l5LtvEsU9obn74QjbNsdk6LwkIIpyRTC39Nmi351BuAXMwZCqYGaPa1xUp02TapLbvzin2QUgTx1KWM8lWtYp5KzlYSK1tFKKwRis64w36aBp" ,"access_token=311019632992273%7C191eaf6470de890483ad21946911574c&input_token=EAAEa3uaZBIBEBAPcP3V5yTw3yb4Y6wBIxZCSQIpPaX0dd5ZCf0olpDxGFPE68EVzvXBsgoC910bkMXi6fQ8x95qDHnbB84XMovoWyZC9l5LtvEsU9obn74QjbNsdk6LwkIIpyRTC39Nmi351BuAXMwZCqYGaPa1xUp02TapLbvzin2QUgTx1KWM8lWtYp5KzlYSK1tFKKwRis64w36aBp", true);
  System.out.println(result);
  } catch (Exception e) {
  	// TODO Auto-generated catch block
  	e.printStackTrace();
  }
    }*/

  // 将所有的供应商的密码初始化为123456
  private static void initAllSupplierPassword() throws SQLException {
    List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
    for (UsrSupplier bean : list) {
      bean.setPassword(DateTools.getDigest(bean.getPkey() + "123456"));
      bean.upd();
    }
    DbPool.getInstance().getConn().commit();
  }

  // 给所有未设置运费模板的供应商初始化一条运费模板
  private static void initAllSupplierFreight() throws SQLException {
    PltFreight f = irille.pub.bean.Query.SELECT(PltFreight.class).limit(0, 1).query();
    PltFreightSeller s = new PltFreightSeller();
    s.setCompany(f.getCompany());
    s.setEnabled(f.getEnabled());
    s.setExpressUrl(f.getExpressUrl());
    s.setLogo(f.getLogo());
    s.setRowVersion((short) 1);
    s.setSort(f.getSort());
    s.setType(f.getType());
    s.setUseInterface(f.getUseInterface());
    s.setWeightMax(f.getWeightMax());
    s.setWeightMin(f.getWeightMin());
    PltFreightLine l =
        irille.pub.bean.Query.SELECT(PltFreightLine.class)
            .WHERE(PltFreightLine.T.MAIN, "=?", f.getPkey())
            .limit(0, 1)
            .query();
    PltFreightSellerLine sl = new PltFreightSellerLine();
    sl.setAggravatePrice(l.getAggravatePrice());
    sl.setAggravateSection(l.getAggravateSection());
    sl.setBrief(l.getBrief());
    sl.setExtraPrice(l.getExtraPrice());
    sl.setFree(l.getFree());
    sl.setFreePrice(l.getFreePrice());
    sl.setRowVersion((short) 1);
    sl.setSection(l.getSection());
    sl.setWeightPrice(l.getWeightPrice());
    sl.setWeightSection(l.getWeightSection());
    List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
    for (UsrSupplier bean : list) {
      int c =
          irille.pub.bean.Query.SELECT(PltFreightSeller.class)
              .WHERE(PltFreightSeller.T.SUPPLIER, "=?", bean.getPkey())
              .queryCount();
      if (c == 0) {
        s.setPkey(null);
        s.setSupplier(bean.getPkey());
        s.ins();
        sl.setPkey(null);
        sl.setMain(s.getPkey());
        sl.ins();
      }
    }
    DbPool.getInstance().getConn().commit();
  }

  // 给所有的没有支付设置的供应商添加一个默认的支付设置
  public static void initAllSupplierPayType() throws JSONException, SQLException {
    PltPay p = new PltPay();
    JSONObject json = new JSONObject();
    json.put("account_name", "1");
    json.put("bank_account", "1");
    json.put("depos_it_bank_subbranch", "1");
    json.put("depos_it_bank_subbranch_ascription", "1");
    p.stMode(OPay_Mode.OFFINE_PAY);
    p.setPoundage(BigDecimal.ZERO);
    p.setExtraCosts(BigDecimal.ZERO);
    p.stEnabled(true);
    p.setPaysetting(json.toString());
    List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
    for (UsrSupplier bean : list) {
      int c =
          irille.pub.bean.Query.SELECT(PltPay.class)
              .WHERE(PltPay.T.SUPPLIER, "=?", bean.getPkey())
              .queryCount();
      if (c == 0) {
        p.setPkey(null);
        p.setSupplier(bean.getPkey());
        p.setRowVersion((short) 0);
        p.ins();
      }
    }
    DbPool.getInstance().getConn().commit();
  }

  // 将admin@163.com的采购商的密码初始化为123456
  public static void initAdminPurchasePassword() throws SQLException {
    UsrPurchase purchase =
        irille.pub.bean.Query.SELECT(UsrPurchase.class)
            .WHERE(UsrPurchase.T.LOGIN_NAME, "=?", "admin@163.com")
            .query();
    purchase.setPassword(DateTools.getDigest(purchase.getPkey() + "123456"));
    purchase.upd();
    DbPool.getInstance().getConn().commit();
  }

  public static void initAllSupplierShowName() throws SQLException {
    List<UsrSupplier> list = irille.pub.bean.Query.SELECT(UsrSupplier.class).queryList();
    for (UsrSupplier bean : list) {
      String name = bean.getShowName();
      JSONObject showname = new JSONObject();
      Stream.of(Language.values())
          .forEach(
              lang -> {
                try {
                  showname.put(lang.name(), name);
                } catch (Exception e) {
                  e.printStackTrace();
                }
              });
      ;
      bean.stShowName(showname);
      bean.upd();
    }
    DbPool.getInstance().getConn().commit();
  }

  public static boolean judgeAuthority(Integer pkey) {
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
    UsrSupplierRole role = supplier.gtRole();
    String roleDetail = role.getActs();
    if (roleDetail.contains(PrmGroupPurchase.TB.getCode())) {
      return true;
    }
    return false;
  }

  /** 供应商入驻后的默认排序 */
  private static final Integer default_sort = 999;

  /**
   * 商家申请入驻
   *
   * @author yingjianhua
   */
  public static UsrSupplier apply(SupplierView view, Integer purchasePkey, Language lang)
      throws JSONException {
    UsrPurchase purchase = irille.pub.bean.Query.SELECT(UsrPurchase.class, purchasePkey);
    UsrSupplier bean = valid4Apply(view.toBean(), purchase);
    bean.setLoginName(purchase.getLoginName());
    bean.setPassword(purchase.getPassword());
    bean.stCompanyType(new JSONObject().put(lang.name(), bean.getCompanyType()));
    bean.stCompanyNature(new JSONObject().put(lang.name(), bean.getCompanyNature()));
    bean.stCompanyAddr(new JSONObject().put(lang.name(), bean.getCompanyAddr()));
    bean.stCity(new JSONObject().put(lang.name(), bean.getCity()));
    bean.stStatus(Usr.OStatus.INIT);
    bean.stRole(UsrSupplierRoleDAO.getDefault());
    bean.stIsAuth(Usr.OIsAuth.NO);
    bean.stIsPro(false);
    bean.setUpdateTime(Env.getTranBeginTime());
    bean.setSort(default_sort);
    bean.ins();
    // 审核通过才将运费模板复制给供应商
    // PltFreightSellerAction pltFreightSellerAction = new PltFreightSellerAction();
    // pltFreightSellerAction.insfreight();
    return bean;
  }

  /** 商家申请入驻信息校验 */
  private static UsrSupplier valid4Apply(UsrSupplier bean, UsrPurchase purchase) {

    if (UsrSupplier.chkUniqueLogin_name(false, purchase.getLoginName()) != null) {
      throw LOG.errTran("addressfrom%Has_Residence", "您已申请入驻,不能重复申请!");
    }

    ValidForm vf = new ValidForm(bean);
    ValidRegex2 vr = new ValidRegex2(bean);
    //
    vf.validNotEmpty(
        T.CATEGORY,
        T.NAME,
        T.COUNTRY,
        T.PROVINCE,
        T.CITY,
        T.COMPANY_ADDR,
        T.EMAIL,
        T.CREDIT_CODE,
        T.ENTITY);
    // 不是长期的营业执照需要填写营业执照有效期
    if (!bean.gtBusinessLicenseIsSecular()) {
      vf.validNotEmpty(T.BUSINESS_LICENSE_BEGIN_TIME, T.BUSINESS_LICENSE_END_TIME);
    }
    vf.validExists(UsrSupplierCategory.TB, UsrSupplierCategory.T.PKEY, bean.getCategory());
    vf.validExists(PltCountry.TB, PltCountry.T.PKEY, bean.getCountry());
    vf.validExists(PltProvince.TB, PltProvince.T.PKEY, bean.getProvince());
    vr.validEmail(T.EMAIL);

    /* vf.validNotEmpty(T.CONTACTS, T.PHONE, T.SETTLEMENT_BANK, T.BANK_ACCOUNT, T.BANK_BRANCH, T.BANK_COUNTRY, T.BANK_PROVINCE);*/
    if (bean.getBankCountry() != -1) {
      vf.validExists(PltCountry.TB, PltCountry.T.PKEY, bean.getBankCountry());
    }
    if (bean.getBankProvince() != -1) {
      vf.validExists(PltProvince.TB, PltProvince.T.PKEY, bean.getBankProvince());
    }

    vf.validNotEmpty(T.ID_CARD, T.CERT_PHOTO, T.ID_CARD_FRONT_PHOTO, T.ID_CARD_BACK_PHOTO);

    return bean;
  }

  /**
   * 供应商审核
   *
   * @author yingjianhua
   */
  public static UsrSupplier approve(Integer pkey) {
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
    supplier.stApprBy(Idu.getUser());
    supplier.setApprTime(Env.getTranBeginTime());
    supplier.stStatus(OStatus.APPR);
    supplier.upd();

    // 初始化商家的运费设置,根据平台端的运费模板
    PltFreightSellerDAO.init(pkey, false);

    // 初始化商家的支付设置,没有明确支付设置的信息 没办法初始化,TODO
    // PltPayDAO.init(supplier);

    return supplier;
  }

  /**
   * 供应商弃审
   *
   * @author yingjianhua
   */
  public static UsrSupplier unapprove(Integer pkey) {
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
    supplier.stApprBy(Idu.getUser());
    supplier.setApprTime(Env.getTranBeginTime());
    supplier.stStatus(OStatus.INIT);
    supplier.upd();
    return supplier;
  }

  /**
   * @param pkey
   * @return
   */
  public static UsrSupplier getStatus(Integer pkey) {
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
    SQL sql =
        new SQL() {
          {
            SELECT(T.PKEY, T.STATUS);
            FROM(UsrSupplier.class);
          }
        };
    SqlQuery query = irille.pub.bean.Query.sql(sql);
    supplier.stStatus(supplier.gtStatus());
    return supplier;
  }

  // <<<================<2019-3-8 && 3.10-new-start>==================>>>

  /**
   * 审核
   *
   * @param pkey
   * @param status
   * @param reason
   * @return
   * @author: lingjian @Date: 2019/3/11 10:49
   */
  public static UsrSupplier reviewStatus(
      String pkey, Integer status, String reason, Date storeopenTime) {
    UsrSupplier supplier = BeanBase.load(UsrSupplier.class, pkey);
    IPMMessageService messageService = new PMMessageServiceImp();
    String signBackgd = "/public/upload/usr/supplier/105df0d09f73c7dccd0baf4d67c38a0d.jpg";

    if (status == 0) {
      supplier.stStatus(OStatus.INIT);
    } else if (status == 1) {
      supplier.stStatus(OStatus.APPR);
      supplier.stStoreStatus(Usr.SStatus.OPEN);
      supplier.setStoreopenTime(storeopenTime);
      supplier.setSignBackgd(signBackgd);
      try {
        messageService.send(OTempType.SHOP_APPR, supplier, null, supplier);
      } catch (Exception e) {
        throw new WebMessageException("店铺审核通知站内信出现错误，请关闭站内信");
      }

    } else if (status == 2) {
      supplier.stStatus(OStatus.FAIL);
      supplier.setReason(reason);
      try {
        messageService.send(OTempType.SHOP_APPR, supplier, null, supplier);
      } catch (Exception e) {
        throw new WebMessageException("店铺审核通知站内信出现错误，请关闭站内信");
      }
    }
    translateUtil.autoTranslate(supplier, false).upd();
    return supplier;
  }

  /**
   * 根据id获取供应商信息
   *
   * @param pkey
   * @return
   * @author: lingjian @Date: 2019/3/8 9:36
   */
  public static List<UsrSupplierNewView> getSupplierById(String pkey) {
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(UsrSupplier.T.PKEY, "=?", pkey);
          }
        };
    List<UsrSupplierNewView> list =
        irille.pub.bean.Query.sql(sql).queryMaps().stream()
            .map(
                bean ->
                    new UsrSupplierNewView() {
                      {
                        setPkey((Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) bean.get(T.NAME.getFld().getCodeSqlField()));
                        setWebsite((String) bean.get(T.WEBSITE.getFld().getCodeSqlField()));
                        setEnglishName(
                            (String) bean.get(T.ENGLISH_NAME.getFld().getCodeSqlField()));
                        setCompanyAddr(
                            (String) bean.get(T.COMPANY_ADDR.getFld().getCodeSqlField()));
                        setTelephone((String) bean.get(T.TELEPHONE.getFld().getCodeSqlField()));
                        setPostcode((String) bean.get(T.POSTCODE.getFld().getCodeSqlField()));
                        setFax((String) bean.get(T.FAX.getFld().getCodeSqlField()));
                        if (bean.get(T.UserId.getFld().getCodeSqlField()) != null) {
                          setUserId((Integer) bean.get(T.UserId.getFld().getCodeSqlField()));
                          UsrMain main =
                              Bean.load(
                                  UsrMain.class,
                                  (Integer) bean.get(T.UserId.getFld().getCodeSqlField()));
                          if (main != null) {
                            setMainEmail(main.getEmail());
                            setMainProvince(main.getProvince());
                            setMainCity(main.getCity());
                            setMainZone(main.getZone());
                          }
                        }
                        setCompanyType(
                            (String) bean.get(T.COMPANY_TYPE.getFld().getCodeSqlField()));
                        setCompanyNature(
                            (String) bean.get(T.COMPANY_NATURE.getFld().getCodeSqlField()));
                        setCompanyEstablishTime(
                            (Date) bean.get(T.COMPANY_ESTABLISH_TIME.getFld().getCodeSqlField()));
                        setTargetedMarket(
                            (String) bean.get(T.TARGETED_MARKET.getFld().getCodeSqlField()));
                        setProdPattern(
                            (String) bean.get(T.PROD_PATTERN.getFld().getCodeSqlField()));
                        setAnnualProduction(
                            (String) bean.get(T.ANNUAL_PRODUCTION.getFld().getCodeSqlField()));
                        setRegisteredCapital(
                            (String) bean.get(T.REGISTERED_CAPITAL.getFld().getCodeSqlField()));
                        setEntity((String) bean.get(T.ENTITY.getFld().getCodeSqlField()));
                        setTaxpayerType(
                            (String) bean.get(T.TAXPAYER_TYPE.getFld().getCodeSqlField()));
                        setCreditCode((String) bean.get(T.CREDIT_CODE.getFld().getCodeSqlField()));
                        setBusinessLicenseIsSecular(
                            (Byte)
                                bean.get(T.BUSINESS_LICENSE_IS_SECULAR.getFld().getCodeSqlField()));
                        setBusinessLicenseBeginTime(
                            (String)
                                bean.get(T.BUSINESS_LICENSE_BEGIN_TIME.getFld().getCodeSqlField()));
                        if ((Byte)
                                bean.get(T.BUSINESS_LICENSE_IS_SECULAR.getFld().getCodeSqlField())
                            == 0) {
                          setBusinessLicenseEndTime(
                              (String)
                                  bean.get(T.BUSINESS_LICENSE_END_TIME.getFld().getCodeSqlField()));
                        }
                        setContacts((String) bean.get(T.CONTACTS.getFld().getCodeSqlField()));
                        setDepartment((String) bean.get(T.DEPARTMENT.getFld().getCodeSqlField()));
                        setJobTitle((String) bean.get(T.JOB_TITLE.getFld().getCodeSqlField()));
                        setPhone((String) bean.get(T.PHONE.getFld().getCodeSqlField()));
                        setContactEmail(
                            (String) bean.get(T.CONTACT_EMAIL.getFld().getCodeSqlField()));
                        setCertPhoto((String) bean.get(T.CERT_PHOTO.getFld().getCodeSqlField()));
                        setIdCardFrontPhoto(
                            (String) bean.get(T.ID_CARD_FRONT_PHOTO.getFld().getCodeSqlField()));
                        setContactsIdCardFrontPhoto(
                            (String)
                                bean.get(
                                    T.CONTACTS_ID_CARD_FRONT_PHOTO.getFld().getCodeSqlField()));
                        UsrAnnex annex =
                            UsrAnnex.chkUniqueSupplier(
                                false, (Integer) bean.get(T.PKEY.getFld().getCodeSqlField()));
                        if (annex != null) {
                          setCertPhotoName(annex.getCertPhotoName());
                          setIdCardFrontPhotoName(annex.getIdCardFrontPhotoName());
                          setContactsIdCardFrontPhotoName(annex.getContactsIdCardFrontPhotoName());
                        }
                        setStatus((Byte) bean.get(T.STATUS.getFld().getCodeSqlField()));
                        if (bean.get(T.REASON.getFld().getCodeSqlField()) != null) {
                          setReason((String) bean.get(T.REASON.getFld().getCodeSqlField()));
                        }
                        setStoreStatus((Byte) bean.get(T.STORE_STATUS.getFld().getCodeSqlField()));
                        if (bean.get(T.CLOSE_REASON.getFld().getCodeSqlField()) != null) {
                          setCloseReason(
                              (String) bean.get(T.CLOSE_REASON.getFld().getCodeSqlField()));
                        }
                        setApplicationTime(
                            (Date) bean.get(T.APPLICATION_TIME.getFld().getCodeSqlField()));
                        setStoreopenTime(
                            (Date) bean.get(T.STOREOPEN_TIME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return list;
  }

  /**
   * 创建供应商信息-没有多国语言翻译
   *
   * @author: lingjian @Date: 2019/3/20 19:29
   * @param view
   * @param lang
   * @return
   * @throws JSONException
   */
  public static UsrSupplier insSupplierNo(UsrSupplier view, Language lang) throws JSONException {
      UsrMain main = irille.pub.bean.Query.SELECT(UsrMain.class, view.getUserid());
      main.setContacts(view.getContacts());
      if (view.getPhone() != null) {
        main.setTelphone(view.getPhone());
      }
      main.setCompany(view.getName());
      main.setAddress(view.getCompanyAddr());
      main.upd();

      UsrSupplier bean = new UsrSupplier();
      // 必填项
      bean.stRole(UsrSupplierRoleDAO.getDefault());
      if (main.getNickname() == null) {
        bean.setLoginName(main.getEmail()); // UsrMain表的昵称
      } else {
        bean.setLoginName(main.getNickname()); // UsrMain表的昵称
      }
      bean.setEmail(main.getEmail()); // UsrMain表的邮箱
      bean.stStatus(Usr.OStatus.INIT); // 审核状态
      bean.stStoreStatus(Usr.SStatus.DOWN); // 店铺状态
      bean.stIsAuth(Usr.OIsAuth.NO); // 认证状态
      bean.setSort(default_sort); // 排序
      bean.setCountry(7); // 国家
      bean.setProvince(257); // 省份
      bean.setBankCountry(7); // 开户行国家
      bean.setBankProvince(257); // 开户行省份
      bean.stHomePageOn(false); // 首页个性装修开关
      bean.stProductPageOn(false); // 产品页个性装修开关
      bean.stContactPageOn(false); // 联系页个性装修开关
      bean.stBottomHomeProductsOn(false); // 首页底部产品展示开关
      bean.stHomePosterOn(false);
      bean.stHomeBusinessBigPosterOn(false);
      bean.stCompanyIntroductionPageCustomDecorationOn(false);
      bean.stIsPro(false); // 供应商首页产品展示
      bean.setUpdateTime(Env.getTranBeginTime()); // 更新时间
      bean.setCategory(40); // 供应商分类

      bean.setUserid(view.getUserid()); // UsrMain表的pkey
      bean.setPassword(DateTools.getDigest(main.getPkey() + main.getPassword())); // UsrMain表的密码
      bean.setName(view.getName()); // 公司名称-必填
      bean.setEnglishName(view.getEnglishName()); // 公司英文名称

      if(view.getName() != null)
          bean.setShowName(translateUtil.toSaveJson(view.getName(), lang)); // 前端公司展示名称
      if(view.getCompanyType() != null)
          bean.setCompanyType(translateUtil.toSaveJson(view.getCompanyType(), lang)); // 企业类型
      if(view.getCompanyNature() != null)
          bean.setCompanyNature(translateUtil.toSaveJson(view.getCompanyNature(), lang)); // 企业性质
      if(view.getCompanyAddr() != null)
          bean.setCompanyAddr(translateUtil.toSaveJson(view.getCompanyAddr(), lang)); // 详细地址
      if(view.getProdPattern() != null)
          bean.setProdPattern(translateUtil.toSaveJson(view.getProdPattern(), lang)); // 生产模式
      if(view.getContacts() != null)
          bean.setContacts(translateUtil.toSaveJson(view.getContacts(), lang)); // 联系人
      if(view.getDepartment() != null)
          bean.setDepartment(translateUtil.toSaveJson(view.getDepartment(), lang)); // 联系人部门
      if(view.getJobTitle() != null)
          bean.setJobTitle(translateUtil.toSaveJson(view.getJobTitle(), lang)); // 联系人职称
      bean.setCompanyEstablishTime(view.getCompanyEstablishTime()); // 成立时间
      bean.setWebsite(view.getWebsite()); // 官网地址
      bean.setAnnualProduction(view.getAnnualProduction()); // 年产量
      bean.setTelephone(view.getTelephone()); // 公司电话
      bean.setFax(view.getFax()); // 传真
      bean.setPostcode(view.getPostcode()); // 邮编
      bean.setTargetedMarket(view.getTargetedMarket()); // 目标市场
      bean.setCreditCode(view.getCreditCode()); // 信用代码
      bean.setRegisteredCapital(view.getRegisteredCapital()); // 注册资金-必填
      bean.setEntity(view.getEntity()); // 企业法人-必填
      bean.setEntity(view.getEntity()); // 企业法人-必填
      bean.setBusinessLicenseIsSecular(view.getBusinessLicenseIsSecular()); // 营业执照是否在有效期-必填
      bean.setBusinessLicenseBeginTime(view.getBusinessLicenseBeginTime()); // 营业执照开始时间
      if (view.getBusinessLicenseIsSecular() == 0) {
          bean.setBusinessLicenseEndTime(view.getBusinessLicenseEndTime()); // 营业执照结束时间
      }
      bean.setTaxpayerType(view.getTaxpayerType()); // 纳税人类型
      bean.setPhone(view.getPhone()); // 联系人手机
    if (view.getContactEmail() == null) {
      bean.setContactEmail(main.getEmail()); // 联系人邮箱
    } else {
      bean.setContactEmail(view.getContactEmail()); // 联系人邮箱
    }
    bean.setCertPhoto(view.getCertPhoto()); // 资质证书复印件
    bean.setIdCardFrontPhoto(view.getIdCardFrontPhoto()); // 法人身份证复印件
    bean.setIdCard(view.getIdCard()); // 法人身份证号码
    bean.setContactsIdCardFrontPhoto(view.getContactsIdCardFrontPhoto()); // 运营负责人身份证复印件
    bean.setOperateIdCard(view.getOperateIdCard()); // 运营负责人身份证号码
    bean.setApplicationTime(view.getApplicationTime()); // 申请时间
    bean.ins();
    return bean;
  }

  /**
   * 更新供应商信息-没多国语言翻译
   *
   * @author: lingjian @Date: 2019/3/1 16:21
   */
  public static UsrSupplier updInfoNo(UsrSupplier supplier, Language lang) throws JSONException {
    UsrSupplier model = BeanBase.load(UsrSupplier.class, supplier.getPkey());
    PropertyUtils.copyProperties(
        model,
        supplier,
        T.COMPANY_TYPE, // 公司类型 多国语言
        T.COMPANY_NATURE, // 企业性质 多国语言
        T.COMPANY_ADDR, // 公司详细地址 多国语言
        T.PROD_PATTERN, // 生产模式 多国语言
        T.DEPARTMENT, // 联系人部门 多国语言
        T.JOB_TITLE, // 联系人职称 多国语言
        T.CONTACTS, // 联系人 多国语言
        T.SHOW_NAME, // 公司名称 多国语言
        T.NAME, // 公司名称
        T.ENGLISH_NAME, // 英文名称
        T.COMPANY_ESTABLISH_TIME, // 企业成立时间
        T.WEBSITE, // 公司官网网站地址
        T.ANNUAL_PRODUCTION, // 年产量
        T.TELEPHONE, // 公司电话
        T.FAX, // 传真
        T.POSTCODE, // 邮编
        T.TARGETED_MARKET, // 目标市场
        T.CREDIT_CODE, // 统一社会信用代码
        T.REGISTERED_CAPITAL, // 注册资本
        T.ENTITY, // 法定代表人
        T.BUSINESS_LICENSE_IS_SECULAR, // 营业执照是否长期
        T.BUSINESS_LICENSE_BEGIN_TIME, // 营业执照开始时间
        T.BUSINESS_LICENSE_END_TIME, // 营业执照结束时间
        T.TAXPAYER_TYPE, // 纳税人类型
        T.PHONE, // 联系人手机
        T.CONTACT_EMAIL, // 联系人邮箱
        T.CERT_PHOTO, // 营业执照副本复印件
        T.ID_CARD_FRONT_PHOTO, // 法人代表身份证复印件
        T.ID_CARD, // 法人代表身份证号码
        T.CONTACTS_ID_CARD_FRONT_PHOTO, // 运营人员身份证复印件
        T.OPERATE_ID_CARD, // 运营人员身份证号码
        T.STORE_STATUS,
        T.CLOSE_REASON);
      if(supplier.getName() != null)
          model.setShowName(translateUtil.toSaveJson(supplier.getName(), lang));
      if(supplier.getCompanyType() != null)
          model.setCompanyType(translateUtil.toSaveJson(supplier.getCompanyType(), lang));
      if(supplier.getCompanyNature() != null)
          model.setCompanyNature(translateUtil.toSaveJson(supplier.getCompanyNature(), lang));
      if(supplier.getCompanyAddr() != null)
          model.setCompanyAddr(translateUtil.toSaveJson(supplier.getCompanyAddr(), lang));
      if(supplier.getProdPattern() != null)
          model.setProdPattern(translateUtil.toSaveJson(supplier.getProdPattern(), lang));

      if(supplier.getContacts() != null)
          model.setContacts(translateUtil.toSaveJson(supplier.getContacts(), lang));
      if(supplier.getDepartment() != null)
          model.setDepartment(translateUtil.toSaveJson(supplier.getDepartment(), lang));
      if(supplier.getJobTitle() != null)
          model.setJobTitle(translateUtil.toSaveJson(supplier.getJobTitle(), lang));
    model.upd();
    return model;
  }

  /**
   * 创建供应商信息-有多国语言翻译
   *
   * @param view
   * @return
   * @throws JSONException
   * @author: lingjian @Date: 2019/3/4 10:01
   */
  public static UsrSupplier insSupplier(UsrSupplier view) throws JSONException {

    UsrSupplier beanjson = new UsrSupplier();
    beanjson.setShowName(view.getName()); // 前端公司展示名称
    beanjson.setCompanyType(view.getCompanyType()); // 企业类型
    beanjson.setCompanyNature(view.getCompanyNature()); // 企业性质
    beanjson.setCompanyAddr(view.getCompanyAddr()); // 详细地址
    beanjson.setProdPattern(view.getProdPattern()); // 生产模式
    beanjson.setContacts(view.getContacts()); // 联系人
    beanjson.setDepartment(view.getDepartment()); // 联系人部门
    beanjson.setJobTitle(view.getJobTitle()); // 联系人职称
    translateUtil.autoTranslate(beanjson, true);
    UsrMain main = irille.pub.bean.Query.SELECT(UsrMain.class, view.getUserid());
    main.setContacts(view.getContacts());
    if (view.getPhone() != null) {
      main.setTelphone(view.getPhone());
    }
    main.setCompany(view.getName());
    main.setAddress(view.getCompanyAddr());
    main.upd();

    UsrSupplier bean = new UsrSupplier();
    // 必填项
    bean.stRole(UsrSupplierRoleDAO.getDefault());
    if (main.getNickname() == null) {
      bean.setLoginName(main.getEmail()); // UsrMain表的昵称
    } else {
      bean.setLoginName(main.getNickname()); // UsrMain表的昵称
    }
    bean.setEmail(main.getEmail()); // UsrMain表的邮箱
    bean.stStatus(Usr.OStatus.INIT); // 审核状态
    bean.stStoreStatus(Usr.SStatus.DOWN); // 店铺状态
    bean.stIsAuth(Usr.OIsAuth.NO); // 认证状态
    bean.setSort(default_sort); // 排序
    bean.setCountry(7); // 国家
    bean.setProvince(257); // 省份
    bean.setBankCountry(7); // 开户行国家
    bean.setBankProvince(257); // 开户行省份
    bean.stHomePageOn(false); // 首页个性装修开关
    bean.stProductPageOn(false); // 产品页个性装修开关
    bean.stContactPageOn(false); // 联系页个性装修开关
    bean.stBottomHomeProductsOn(false); // 首页底部产品展示开关
    bean.stHomePosterOn(false);
    bean.stHomeBusinessBigPosterOn(false);
    bean.stCompanyIntroductionPageCustomDecorationOn(false);
    bean.stIsPro(false); // 供应商首页产品展示
    bean.setUpdateTime(Env.getTranBeginTime()); // 更新时间
    bean.setCategory(40); // 供应商分类

    bean.setUserid(view.getUserid()); // UsrMain表的pkey
    bean.setPassword(DateTools.getDigest(main.getPkey() + main.getPassword())); // UsrMain表的密码
    bean.setName(view.getName()); // 公司名称-必填
    bean.setEnglishName(view.getEnglishName()); // 公司英文名称
    bean.setShowName(beanjson.getShowName()); // 前端公司展示名称

    bean.setCompanyType(beanjson.getCompanyType()); // 企业类型
    bean.setCompanyNature(beanjson.getCompanyNature()); // 企业性质
    bean.setCompanyEstablishTime(view.getCompanyEstablishTime()); // 成立时间
    bean.setWebsite(view.getWebsite()); // 官网地址
    bean.setCompanyAddr(beanjson.getCompanyAddr()); // 详细地址
    bean.setAnnualProduction(view.getAnnualProduction()); // 年产量
    bean.setTelephone(view.getTelephone()); // 公司电话
    bean.setFax(view.getFax()); // 传真
    bean.setPostcode(view.getPostcode()); // 邮编
    bean.setTargetedMarket(view.getTargetedMarket()); // 目标市场
    bean.setProdPattern(beanjson.getProdPattern()); // 生产模式
    bean.setCreditCode(view.getCreditCode()); // 信用代码
    bean.setRegisteredCapital(view.getRegisteredCapital()); // 注册资金-必填
    bean.setEntity(view.getEntity()); // 企业法人-必填
    bean.setEntity(view.getEntity()); // 企业法人-必填
    bean.setBusinessLicenseIsSecular(view.getBusinessLicenseIsSecular()); // 营业执照是否在有效期-必填
    bean.setBusinessLicenseBeginTime(view.getBusinessLicenseBeginTime()); // 营业执照开始时间
    if (view.getBusinessLicenseIsSecular() == 0) {
      bean.setBusinessLicenseEndTime(view.getBusinessLicenseEndTime()); // 营业执照结束时间
    }
    bean.setTaxpayerType(view.getTaxpayerType()); // 纳税人类型

    bean.setContacts(beanjson.getContacts()); // 联系人
    bean.setDepartment(beanjson.getDepartment()); // 联系人部门
    bean.setJobTitle(beanjson.getJobTitle()); // 联系人职称
    bean.setPhone(view.getPhone()); // 联系人手机
    if (view.getContactEmail() == null) {
      bean.setContactEmail(main.getEmail()); // 联系人邮箱
    } else {
      bean.setContactEmail(view.getContactEmail()); // 联系人邮箱
    }
    bean.setCertPhoto(view.getCertPhoto()); // 资质证书复印件
    bean.setIdCardFrontPhoto(view.getIdCardFrontPhoto()); // 法人身份证复印件
    bean.setIdCard(view.getIdCard()); // 法人身份证号码
    bean.setContactsIdCardFrontPhoto(view.getContactsIdCardFrontPhoto()); // 运营负责人身份证复印件
    bean.setOperateIdCard(view.getOperateIdCard()); // 运营负责人身份证号码
    bean.setApplicationTime(view.getApplicationTime()); // 申请时间
    bean.ins();
    return bean;
  }

  /**
   * 更新供应商信息-有多国语言翻译
   *
   * @author: lingjian @Date: 2019/3/1 16:21
   */
  public static UsrSupplier updInfo(UsrSupplier supplier) {
    UsrSupplier model = BeanBase.load(UsrSupplier.class, supplier.getPkey());
    PropertyUtils.copyProperties(
        model,
        supplier,
        T.COMPANY_TYPE, // 公司类型 多国语言
        T.COMPANY_NATURE, // 企业性质 多国语言
        T.COMPANY_ADDR, // 公司详细地址 多国语言
        T.PROD_PATTERN, // 生产模式 多国语言
        T.DEPARTMENT, // 联系人部门 多国语言
        T.JOB_TITLE, // 联系人职称 多国语言
        T.CONTACTS, // 联系人 多国语言
        T.SHOW_NAME, // 公司名称 多国语言
        T.NAME, // 公司名称
        T.ENGLISH_NAME, // 英文名称
        T.COMPANY_ESTABLISH_TIME, // 企业成立时间
        T.WEBSITE, // 公司官网网站地址
        T.ANNUAL_PRODUCTION, // 年产量
        T.TELEPHONE, // 公司电话
        T.FAX, // 传真
        T.POSTCODE, // 邮编
        T.TARGETED_MARKET, // 目标市场
        T.CREDIT_CODE, // 统一社会信用代码
        T.REGISTERED_CAPITAL, // 注册资本
        T.ENTITY, // 法定代表人
        T.BUSINESS_LICENSE_IS_SECULAR, // 营业执照是否长期
        T.BUSINESS_LICENSE_BEGIN_TIME, // 营业执照开始时间
        T.BUSINESS_LICENSE_END_TIME, // 营业执照结束时间
        T.TAXPAYER_TYPE, // 纳税人类型
        T.PHONE, // 联系人手机
        T.CONTACT_EMAIL, // 联系人邮箱
        T.CERT_PHOTO, // 营业执照副本复印件
        T.ID_CARD_FRONT_PHOTO, // 法人代表身份证复印件
        T.ID_CARD, // 法人代表身份证号码
        T.CONTACTS_ID_CARD_FRONT_PHOTO, // 运营人员身份证复印件
        T.OPERATE_ID_CARD, // 运营人员身份证号码
        T.STORE_STATUS,
        T.CLOSE_REASON);
    UsrSupplier s = new UsrSupplier();
    s.setCompanyType(supplier.getCompanyType());
    s.setCompanyNature(supplier.getCompanyNature());
    s.setCompanyAddr(supplier.getCompanyAddr());
    s.setProdPattern(supplier.getProdPattern());
    s.setContacts(supplier.getContacts());
    s.setDepartment(supplier.getDepartment());
    s.setJobTitle(supplier.getJobTitle());
    s.setShowName(supplier.getName());
    translateUtil.autoTranslate(s, true);
    model.setCompanyType(s.getCompanyType());
    model.setCompanyNature(s.getCompanyNature());
    model.setCompanyAddr(s.getCompanyAddr());
    model.setProdPattern(s.getProdPattern());
    model.setContacts(s.getContacts());
    model.setDepartment(s.getDepartment());
    model.setJobTitle(s.getJobTitle());
    model.setShowName(s.getShowName());
    model.upd();
    return model;
  }

  /**
   * 更新关闭店铺状态
   *
   * @param supplier
   * @return
   */
  public static UsrSupplier updStore(UsrSupplier supplier) {
    UsrSupplier model = BeanBase.load(UsrSupplier.class, supplier.getPkey());
    PropertyUtils.copyProperties(model, supplier, T.STORE_STATUS, T.CLOSE_REASON, T.STOREOPEN_TIME);
    model.upd();
    return model;
  }

  /**
   * 获取开店申请列表
   *
   * @param start
   * @param limit
   * @param name
   * @param status
   * @return
   * @author: lingjian @Date: 2019/3/11 10:48
   */
  public static Page getShopApplication(Integer start, Integer limit, String name, Integer status) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 15;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class)
                .FROM(UsrSupplier.class)
                .WHERE(T.STATUS, "!=1")
                .WHERE(T.STORE_STATUS, "!=1");
            if (name != null) {
              WHERE(T.NAME, "like '%" + name + "%'");
            }
            if (status != null) {
              WHERE(T.STATUS, "=?", status);
            }
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<SuppliersView> list =
        irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new SuppliersView() {
                      {
                        setId((Integer) o.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) o.get(T.NAME.getFld().getCodeSqlField()));
                        setContacts((String) o.get(T.CONTACTS.getFld().getCodeSqlField()));
                        setCompanyAddr((String) o.get(T.COMPANY_ADDR.getFld().getCodeSqlField()));
                        setApplicationTime(
                            (Date) o.get(T.APPLICATION_TIME.getFld().getCodeSqlField()));
                        setStoreStatus(
                            Byte.valueOf(
                                String.valueOf(o.get(T.STORE_STATUS.getFld().getCodeSqlField()))));
                        setStatus(
                            Byte.valueOf(
                                String.valueOf(o.get(T.STATUS.getFld().getCodeSqlField()))));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * 获取店铺列表
   *
   * @author: lingjian @Date: 2019/3/13 11:10
   * @param start
   * @param limit
   * @return
   */
  public static Page getShopList(
      Integer start, Integer limit, String name, Integer storeStatus, Integer svsGrade) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 15;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class)
                .SELECT(SVSInfo.T.PKEY, "svsId")
                .SELECT(SVSInfo.T.GRADE)
                .SELECT(SVSInfo.T.STATUS, "svsStatus")
                .FROM(UsrSupplier.class)
                .WHERE(T.STATUS, "=1");
            LEFT_JOIN(SVSInfo.class, T.PKEY, SVSInfo.T.SUPPLIER);
            if (name != null) {
              WHERE(T.NAME, "like '%" + name + "%'");
            }
            if (storeStatus != null) {
              WHERE(T.STORE_STATUS, "=?", storeStatus);
            }
            if (svsGrade != null) {
              WHERE(SVSInfo.T.GRADE, "=?", svsGrade);
            }
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<SuppliersView> list =
        irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new SuppliersView() {
                      {
                        setId((Integer) o.get(T.PKEY.getFld().getCodeSqlField())); // 供应商id
                        if (o.get("svsId") != null) {
                          setSvsId((Integer) o.get("svsId"));
                        }
                        setName((String) o.get(T.NAME.getFld().getCodeSqlField()));
                        if (o.get(T.UserId.getFld().getCodeSqlField()) != null) {
                          UsrMain main =
                              BeanBase.load(
                                  UsrMain.class,
                                  (Serializable) o.get(T.UserId.getFld().getCodeSqlField()));
                          if (main != null) {
                            setMainId(main.getPkey());
                            setEmail(main.getEmail());
                          }
                        }
                        if (o.get(SVSInfo.T.GRADE.getFld().getCodeSqlField()) != null) {
                          setSvsGrade((Byte) o.get(SVSInfo.T.GRADE.getFld().getCodeSqlField()));
                        }
                        if (o.get("svsStatus") != null) {
                          setSvsStatus((Byte) o.get("svsStatus"));
                        }
                        setContacts((String) o.get(T.CONTACTS.getFld().getCodeSqlField()));
                        setTelphone((String) o.get(T.PHONE.getFld().getCodeSqlField()));
                        setStoreopenTime((Date) o.get(T.STOREOPEN_TIME.getFld().getCodeSqlField()));
                        setStoreStatus(
                            Byte.valueOf(
                                String.valueOf(o.get(T.STORE_STATUS.getFld().getCodeSqlField()))));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  // <<<================<2019-3-8 && 3.10new-end>==================>>>

  // ================<2018-9-29 && new>==================

  /**
   * 客户端,获取商家页面信息
   *
   * @author liyichao
   */
  public static class Select extends IduOther<Select, UsrSupplier> {

    /** 根据pkey获取商家信息 id => 供应商id type = 1 获取商家首页信息 type = 2 获取商家产品页信息 type = 3&4 获取商家公司页或者联系页信息 */
    public static SupplierView getSupView(
        FldLanguage.Language language, Serializable id, Integer type) {

      UsrSupplier supplier =
          translateUtil.getAutoTranslate(BeanBase.load(UsrSupplier.class, id), language);
      SupplierView view = new SupplierView();
      PltCountry country =
          translateUtil.getAutoTranslate(
              BeanBase.load(PltCountry.class, supplier.getCountry()), language);
      PltProvince province =
          translateUtil.getAutoTranslate(
              BeanBase.load(PltProvince.class, supplier.getProvince()), language);
      // 公司信息
      view.setPkey(supplier.getPkey());
      view.setLogo(supplier.getLogo()); // 公司logo
      view.setShowName(supplier.getShowName()); // 公司网站显示名称
      view.setAuthAge(getAuthTime(supplier)); // 公司认证年限
      view.setCompanyType(supplier.getCompanyType()); // 企业类型
      view.setCompanyNature(supplier.getCompanyNature()); // 企业性质
      // 页面设置
      view.setIsAuth(supplier.getIsAuth()); // 公司是否通过认证
      view.setPrmAuthrity(judgeAuthorityByRole(supplier.gtRole())); // 公司是否存在联合采购权限
      view.setImList(UsrSupImDAO.getEnabledImSetting(supplier.getPkey())); // 店铺内聊天插件
      view.setTraceCode(supplier.getTraceCode()); // 跟踪代码 STR(100)<null>
      view.setWebSizeTitle(supplier.getWebSizeTitle()); // 跟踪代码 STR(100)<null>

      switch (type) {
        case 1: // 首页
          view.setHomePageOn(supplier.getHomePageOn()); // 公司是否启用首页个性化装修
          view.setHomePageDiy(supplier.getHomePageDiy()); // 公司首页个性装修
          view.setIsPro(supplier.getIsPro()); // 是否展示首页产品
          view.setMainSalesArea(supplier.getMainSalesArea()); // 主要销售区
          view.setAdPhoto(supplier.getAdPhoto()); // 首页大轮播图
          view.setAdPhotoLink(supplier.getAdPhotoLink()); // 首页大轮播图链接
          view.setCompanyPhoto(supplier.getCompanyPhoto()); // 企业图片
          view.setCompanyPhotoLink(supplier.getCompanyPhotoLink()); // 企业图片链接
          view.setAdPhotoMobile(supplier.getAdPhotoMobile());
          if (supplier.getIsPro().equals(Sys.OYn.YES.getLine().getKey())) {
            view.setProductList(PdtProductDAO.Select.getIndexProduct(view.getPkey(), language));
          }
          break;
        case 2: // 产品中心
          view.setProductPageOn(supplier.getProductPageOn()); // 公司是否启用产品页个性化装修
          view.setProductPageDiy(supplier.getProductPageDiy()); // 产品页个性化装修
          break;
        case 3: // 公司信息或者联系我们
          view.setName(supplier.getName()); // 公司名称
          view.setCountryName(country.getName()); // 公司所在地国家
          view.setCity(supplier.getCity()); // 公司所在地城市
          view.setProvinceName(province.getName()); // 公司所在地省份
          view.setCreditCode(supplier.getCreditCode()); // 企业信用代码
          view.setFax(supplier.getFax()); // 传真
          view.setCompanyEstablishTime(supplier.getCompanyEstablishTime()); // 公司成立时间
          view.setBusinessTyp(supplier.getBusinessTyp()); // 公司商业模式
          view.setTop3Markets(supplier.getTop3Markets()); // 前三市场
          view.setCompanyAddr(supplier.getCompanyAddr()); // 公司地址
          view.setMainProd(supplier.getMainProd()); // 公司主要产品
          view.setProdPattern(supplier.getProdPattern()); // 公司生产模式
          view.setTelephone(supplier.getTelephone()); // 公司电话
          view.setPhone(supplier.getPhone()); // 联系人电话
          view.setRegisteredCapital(supplier.getRegisteredCapital()); // 注册资金
          view.setEntity(supplier.getEntity()); // 企业法人
          view.setWebSizeTitle(supplier.getWebSizeTitle()); // 企业网站标题
          view.setWebsite(supplier.getWebsite()); // 企业网站
          view.setDeveloper(supplier.getDeveloper()); // 开发者编号
          view.setTotalEmployees(supplier.getTotalEmployees()); // 员工总数
          view.setAnnualSales(supplier.getAnnualSales()); // 年销售额
          view.setMaterials(supplier.getMaterials()); // 公司所选材料
          view.setAuthTime(supplier.getAuthTime()); // 公司认证时间
          view.setLocation(supplier.getLocation()); // 公司位置
          // 运营信息
          view.setHeadPic(supplier.getHeadPic()); // 公司联系人头像
          view.setContacts(supplier.getContacts()); // 联系人名称
          view.setDepartment(supplier.getDepartment()); // 联系人部门
          view.setJobTitle(supplier.getJobTitle()); // 联系人职位
          // 页面设置
          view.setContactPageOn(supplier.getContactPageOn()); // 是否启用联系页个性化装修
          view.setContactPageDiy(supplier.getContactPageDiy()); // 联系页个性化装修
          break;
      }

      return view;
    }
  }

  /**
   * 获取公司联合采购权限
   *
   * @author liyichao
   */
  private static boolean judgeAuthorityByRole(UsrSupplierRole role) {
    String roleDetail = role.getActs();
    if (roleDetail.contains(PrmGroupPurchase.TB.getCode())) {
      return true;
    }
    return false;
  }

  /**
   * 获取公司认证时间年数
   *
   * @author liyichao
   */
  private static int getAuthTime(UsrSupplier supplier) {
    if (supplier.getAuthTime() == null) {
      return 0;
    }
    int time1 = 0;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    Date authTime2 = supplier.getAuthTime();
    String time = formatter.format(authTime2);
    Calendar c = Calendar.getInstance();
    int y1 = Integer.parseInt(time);
    c.setTime(new Date());
    int y2 = c.get(Calendar.YEAR);
    time1 = y1 - y2 == 0 ? 1 : y1 - y2;
    return time1;
  }

  // ================<2018-9-29 && end>==================

  /**
   * 加载获取商家端店铺装修
   *
   * @author wilson zhang
   */
  public static shopSettingView loadshopsetting(Integer supkey, Language lang)
      throws JSONException {
    UsrSupplier us = BeanBase.load(UsrSupplier.class, supkey);
    shopSettingView ssv = new shopSettingView();
    ssv.setLocation(lang == null ? us.getLocation() : us.getLocation(lang));
    ssv.setProductionCapacity(lang == null ? us.getProduction() : us.getProduction(lang));
    ssv.setTotalProduction(lang == null ? us.getDeveloper() : us.getDeveloper(lang));
    ssv.setNumberEmployees(lang == null ? us.getTotalEmployees() : us.getTotalEmployees(lang));
    ssv.setAnnualSalesFigure(lang == null ? us.getAnnualSales() : us.getAnnualSales(lang));
    ssv.setThirdMarket(lang == null ? us.getTop3Markets() : us.getTop3Markets(lang));
    ssv.setMaterialSelection(lang == null ? us.getMaterials() : us.getMaterials(lang));
    ssv.setDepartment(lang == null ? us.getDepartment() : us.getDepartment(lang));
    ssv.setJobTitle(lang == null ? us.getJobTitle() : us.getJobTitle(lang));
    ssv.setWebsite(us.getWebsite());
    ssv.setCountryPkey(us.getCountry());
    ssv.setProvincPpkey(us.getProvince());
    ssv.setCity(lang == null ? us.getCity() : us.getCity(lang));
    ssv.setIsPro(us.getIsPro());
    ssv.setAdPhotoLink(us.getAdPhotoLink());
    ssv.setCompanyPhotoLink(us.getCompanyPhotoLink());
    ssv.setHeadimgurl(us.getHeadPic());
    ssv.setLogo(us.getLogo());
    ssv.setSignBackGD(us.getSignBackgd());
    ssv.setAdPhotoMobile(us.getAdPhotoMobile());
    ssv.setAdimg(us.getAdPhoto());
    ssv.setCompanyPhoto(us.getCompanyPhoto());
    ssv.setHomePageDIY(us.getHomePageDiy());
    ssv.setProductPageDIY(us.getProductPageDiy());
    ssv.setContactPageDIY(us.getContactPageDiy());
    ssv.setShopcategory(us.getCategory());
    return ssv;
  }

  /** 修改装修店铺 商家 @author wilson zhang */
  public static class setting extends IduUpd<setting, UsrSupplier> {

    @Override
    public void before() {
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.LOGO,
          T.SIGN_BACKGD,
          T.AD_PHOTO,
          T.AD_PHOTO_LINK,
          T.COMPANY_PHOTO,
          T.COMPANY_PHOTO_LINK,
          T.HOME_PAGE_DIY,
          T.HOME_PAGE_ON,
          T.COMPANY_INTRODUCTION_PAGE_CUSTOM_DECORATION,
          T.BOTTOM_HOME_PRODUCTS_ON,
          T.HOME_POSTER_ON,
          T.HOME_BUSINESS_BIG_POSTER_ON,
          T.Is_Pro,
          T.COMPANY_INTRODUCTION_PAGE_CUSTOM_DECORATION_ON);
      setB(dbBean);
      super.before();
    }
  }

  /**
   * @Description: 新商家2.1 商家 店铺信息-账户信息（公司信息）
   *
   * @date 2018/12/18 9:38
   * @anthor wilson zhang
   */
  public static SupinfoView getsupinfo(Integer supplierId, Language language) {
    SupinfoView sv = new SupinfoView();
    sv.setCurlang(language.toString());
    SQL sql =
        new SQL() {
          {
            SELECT(
                T.PKEY,
                T.QQ,
                T.FAX,
                T.TELEPHONE,
                T.EMAIL,
                T.REGISTERED_CAPITAL,
                T.COMPANY_ESTABLISH_TIME,
                T.OPERATION_TERM,
                T.DES,
                T.CREDIT_CODE,
                T.ENTITY,
                T.BUSINESS_LICENSE_BEGIN_TIME,
                T.BUSINESS_LICENSE_END_TIME,
                T.BUSINESS_LICENSE_IS_SECULAR,
                T.TAXPAYER_TYPE,
                T.ID_CARD,
                T.ID_CARD_FRONT_PHOTO,
                T.ID_CARD_BACK_PHOTO,
                T.COUNTRY,
                T.PROVINCE,
                T.COMPANY_ADDR,
                T.COMPANY_TYPE,
                T.COMPANY_NATURE,
                T.MAIN_SALES_AREA,
                T.PROD_PATTERN,
                T.PROD_PATTERN,
                T.COMPANY_TYPE,
                T.MAIN_PROD,
                T.CATEGORY,
                T.ID_CARD_FRONT_PHOTO,
                T.ID_CARD_BACK_PHOTO);
            SELECT(T.NAME, "COMPANYNAME");
            FROM(UsrSupplier.class);
            WHERE(T.PKEY, "=?", supplierId);
          }
        };
    Map<String, Object> map = irille.pub.bean.Query.sql(sql).queryMap();
    sv.setId((Integer) map.get(T.PKEY.getFld().getCodeSqlField()));
    sv.setCompany((String) map.get("COMPANYNAME"));
    sv.setQQ((String) map.get(T.QQ.getFld().getCodeSqlField()));
    sv.setFAX((String) map.get(T.FAX.getFld().getCodeSqlField()));
    sv.setTelephone((String) map.get(T.TELEPHONE.getFld().getCodeSqlField()));
    sv.setEmail((String) map.get(T.EMAIL.getFld().getCodeSqlField()));
    sv.setRegistered_Capital((String) map.get(T.REGISTERED_CAPITAL.getFld().getCodeSqlField()));
    sv.setCompany_establish_time(
        (Date) map.get(T.COMPANY_ESTABLISH_TIME.getFld().getCodeSqlField()));
    sv.setOperation_term((String) map.get(T.OPERATION_TERM.getFld().getCodeSqlField()));
    sv.setDes((String) map.get(T.DES.getFld().getCodeSqlField()));
    sv.setCredit_code((String) map.get(T.CREDIT_CODE.getFld().getCodeSqlField()));
    sv.setEntity((String) map.get(T.ENTITY.getFld().getCodeSqlField()));
    sv.setBegintime((String) map.get(T.BUSINESS_LICENSE_BEGIN_TIME.getFld().getCodeSqlField()));
    sv.setEndtime((String) map.get(T.BUSINESS_LICENSE_END_TIME.getFld().getCodeSqlField()));
    Byte b = (Byte) map.get(T.BUSINESS_LICENSE_IS_SECULAR.getFld().getCodeSqlField());
    if (b != 1 || b == null) {
      sv.setIssecular(false);
    } else {
      sv.setIssecular(true);
    }
    sv.setTaxpayer_Type((String) map.get(T.TAXPAYER_TYPE.getFld().getCodeSqlField()));
    sv.setIdcard((String) map.get(T.ID_CARD.getFld().getCodeSqlField()));
    sv.setIdcardFrontPhoto((String) map.get(T.ID_CARD_FRONT_PHOTO.getFld().getCodeSqlField()));
    sv.setIdcardBackPhoto((String) map.get(T.ID_CARD_BACK_PHOTO.getFld().getCodeSqlField()));
    sv.setCoutry(
        BeanBase.load(PltCountry.class, (Integer) map.get(T.COUNTRY.getFld().getCodeSqlField()))
            .getName());
    sv.setProvince(
        BeanBase.load(PltProvince.class, (Integer) map.get(T.PROVINCE.getFld().getCodeSqlField()))
            .getName());
    sv.setCompany_add((String) map.get(T.COMPANY_ADDR.getFld().getCodeSqlField()));
    sv.setCompany_Type((String) map.get(T.COMPANY_TYPE.getFld().getCodeSqlField()));
    sv.setCompany_nature((String) map.get(T.COMPANY_NATURE.getFld().getCodeSqlField()));
    sv.setProd_patiern((String) map.get(T.PROD_PATTERN.getFld().getCodeSqlField()));
    sv.setMain_sale_area((String) map.get(T.MAIN_SALES_AREA.getFld().getCodeSqlField()));
    sv.setMain_prod((String) map.get(T.MAIN_PROD.getFld().getCodeSqlField()));
    sv.setType((Integer) map.get(T.CATEGORY.getFld().getCodeSqlField()));
    return sv;
  }

  /**
   * @Description: 新商家2.1 商家 店铺信息-修改账户信息（公司信息）
   *
   * @date 2018/12/18 9:38
   * @anthor wilson zhang
   */
  public static class updShopbase extends IduUpd<UpdBase, UsrSupplier> {

    @Override
    public void before() {
      UsrSupplier model = BeanBase.load(UsrSupplier.class, getB().getPkey());
      PropertyUtils.copyProperties(
          model,
          getB(),
          T.COMPANY_NATURE,
          T.COMPANY_TYPE,
          T.CATEGORY,
          T.QQ,
          T.FAX,
          T.OPERATION_TERM,
          T.MAIN_SALES_AREA,
          T.PROD_PATTERN,
          T.DES,
          T.MAIN_PROD,
          T.CREDIT_CODE,
          T.TAXPAYER_TYPE);
      setB(model);
    }
  }

  /**
   * @Description: 新商家2.1 商家 店铺信息-修改运营信息
   *
   * @date 2018/12/18 9:38
   * @anthor wilson zhang
   */
  public static class updoperatebase extends IduUpd<UpdBase, UsrSupplier> {

    @Override
    public void before() {
      UsrSupplier model = BeanBase.load(UsrSupplier.class, getB().getPkey());
      PropertyUtils.copyProperties(
          model,
          getB(),
          T.WEBSITE,
          T.PRODUCTION,
          T.DEVELOPER,
          T.TOTAL_EMPLOYEES,
          T.ANNUAL_SALES,
          T.TOP_3_MARKETS,
          T.WEB_SIZE_TITLE,
          T.CITY,
          T.HEAD_PIC,
          T.CONTACTS,
          T.PHONE);
      setB(model);
    }
  }

  /**
   * @Description: 新商家2.1 商家 运营信息
   *
   * @date 2018/12/18 9:38
   * @anthor wilson zhang
   */
  public static operateinfoView getoperateinfo(Integer supperid, Language language)
      throws Exception {
    operateinfoView ov = new operateinfoView();
    try {
      SQL sql =
          new SQL() {
            {
              SELECT(
                  T.WEB_SIZE_TITLE,
                  T.PRODUCTION,
                  T.DEVELOPER,
                  T.TOTAL_EMPLOYEES,
                  T.ANNUAL_SALES,
                  T.WEBSITE,
                  T.COUNTRY,
                  T.PROVINCE,
                  T.CITY,
                  T.HEAD_PIC,
                  T.CONTACTS,
                  T.PHONE,
                  T.DEPARTMENT,
                  T.JOB_TITLE,
                  T.BANK_ACCOUNT,
                  T.SETTLEMENT_BANK,
                  T.BANK_BRANCH,
                  T.BANK_COUNTRY,
                  T.BANK_PROVINCE,
                  T.OPERATE_ID_CARD,
                  T.CONTACTS_ID_CARD_FRONT_PHOTO,
                  T.CONTACTS_ID_CARD_BACK_PHOTO);
              SELECT(
                  UsrSupplier.class.getSimpleName()
                      + "."
                      + T.TOP_3_MARKETS.getFld().getCodeSqlField());
              FROM(UsrSupplier.class);
              WHERE(T.PKEY, "=?", supperid);
            }
          };
      Map<String, Object> map = irille.pub.bean.Query.sql(sql).queryMap();
      ov.setWebsizetitle((String) map.get(T.WEB_SIZE_TITLE.getFld().getCodeSqlField()));
      ov.setProduction((String) map.get(T.PRODUCTION.getFld().getCodeSqlField()));
      ov.setTotalProduction((String) map.get(T.DEVELOPER.getFld().getCodeSqlField()));
      ov.setNumberEmployees((String) map.get(T.TOTAL_EMPLOYEES.getFld().getCodeSqlField()));
      ov.setAnnualSalesFigure((String) map.get(T.ANNUAL_SALES.getFld().getCodeSqlField()));
      ov.setTOP3MARKETS((String) map.get("top3_markets"));
      ov.setWebsite((String) map.get(T.WEBSITE.getFld().getCodeSqlField()));
      ov.setCountry(
          BeanBase.load(PltCountry.class, (Integer) map.get(T.COUNTRY.getFld().getCodeSqlField()))
              .getName());
      ov.setProvince(
          BeanBase.load(PltProvince.class, (Integer) map.get(T.PROVINCE.getFld().getCodeSqlField()))
              .getName());
      ov.setCity((String) map.get(T.CITY.getFld().getCodeSqlField()));
      ov.setHeadpic((String) map.get(T.HEAD_PIC.getFld().getCodeSqlField()));
      ov.setContacts((String) map.get(T.CONTACTS.getFld().getCodeSqlField()));
      ov.setPhone((String) map.get(T.PHONE.getFld().getCodeSqlField()));
      ov.setDepartment((String) map.get(T.DEPARTMENT.getFld().getCodeSqlField()));
      ov.setJobTitle((String) map.get(T.JOB_TITLE.getFld().getCodeSqlField()));
      ov.setSettlementbank((String) map.get(T.SETTLEMENT_BANK.getFld().getCodeSqlField()));
      ov.setBankaccount((String) map.get(T.BANK_ACCOUNT.getFld().getCodeSqlField()));
      ov.setBankbranch((String) map.get(T.BANK_BRANCH.getFld().getCodeSqlField()));
      ov.setBankcountry(
          BeanBase.load(
                  PltCountry.class, (Integer) map.get(T.BANK_COUNTRY.getFld().getCodeSqlField()))
              .getName());
      ov.setBankprovince(
          BeanBase.load(
                  PltProvince.class, (Integer) map.get(T.BANK_PROVINCE.getFld().getCodeSqlField()))
              .getName());
      ov.setOperateidcard((String) map.get(T.OPERATE_ID_CARD.getFld().getCodeSqlField()));
      ov.setContactsidcardfront(
          (String) map.get(T.CONTACTS_ID_CARD_FRONT_PHOTO.getFld().getCodeSqlField()));
      ov.setContactsidcardback(
          (String) map.get(T.CONTACTS_ID_CARD_BACK_PHOTO.getFld().getCodeSqlField()));
      ov.setCurlang(language.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ov;
  }

  // 2.1 商家端获取认证信息
  public static AuthenticationView auth(Integer supid) {
    SQL sql =
        new SQL() {
          {
            SELECT(T.IS_AUTH, T.AUTH_TIME);
            FROM(UsrSupplier.class);
            WHERE(T.PKEY, "=?", supid);
          }
        };
    Map<String, Object> map = irille.pub.bean.Query.sql(sql).queryMap();
    AuthenticationView av = new AuthenticationView();
    if ((byte) map.get(T.IS_AUTH.getFld().getCodeSqlField()) == 1) {
      av.setIsauth(true);
      Date date = (Date) map.get(T.AUTH_TIME.getFld().getCodeSqlField());
      LocalDate date1 = LocalDate.now();
      Instant instant = date.toInstant();
      ZoneId zoneId = ZoneId.systemDefault();
      LocalDate localDate = instant.atZone(zoneId).toLocalDate();
      av.setTime(localDate.toString());
      av.setAge(date1.getYear() - localDate.getYear());
    } else {
      av.setIsauth(false);
    }
    return av;
  }

  /** ———————————————————分割线(新平台)————————————————————————— */
  public static Page getSuppliers(
      Integer start, Integer limit, String name, Integer category, Integer status) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 15;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(T.PKEY, T.NAME, T.STATUS, T.CATEGORY, T.IS_AUTH, T.WEB_SITE, T.SORT)
                .FROM(UsrSupplier.class);
            if (name != null) {
              WHERE(T.NAME, "like '%" + name + "%'");
            }
            if (category != null) {
              WHERE(T.CATEGORY, "=?", category);
            }
            if (status != null) {
              WHERE(T.STATUS, "=?", status);
            }
          }
        };
    Integer count = irille.pub.bean.Query.sql(sql).queryCount();
    List<SuppliersView> list =
        irille.pub.bean.Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new SuppliersView() {
                      {
                        setId((Integer) o.get(T.PKEY.getFld().getCodeSqlField()));
                        setName((String) o.get(T.NAME.getFld().getCodeSqlField()));
                        setStatus(
                            Byte.valueOf(
                                String.valueOf(o.get(T.STATUS.getFld().getCodeSqlField()))));
                        setCategory(
                            BeanBase.load(
                                    UsrSupplierCategory.class,
                                    (Integer) o.get(T.CATEGORY.getFld().getCodeSqlField()))
                                .getName());
                        setAuth(
                            Byte.valueOf(
                                String.valueOf(o.get(T.IS_AUTH.getFld().getCodeSqlField()))));
                        setWebSite((String) o.get(T.WEB_SITE.getFld().getCodeSqlField()));
                        setSort((Integer) o.get(T.SORT.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static ListView getStatus() {
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplierCategory.T.PKEY, UsrSupplierCategory.T.NAME)
                .FROM(UsrSupplierCategory.class);
          }
        };
    List<CategorysView> categorys =
        irille.pub.bean.Query.sql(sql).queryMaps().stream()
            .map(
                o ->
                    new CategorysView() {
                      {
                        setId(
                            (Integer) o.get(UsrSupplierCategory.T.PKEY.getFld().getCodeSqlField()));
                        setName(
                            (String) o.get(UsrSupplierCategory.T.NAME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    List<SVSStatusView> svsStatus = new ArrayList<>();
    for (SVSAuthenticationStatus value : SVSAuthenticationStatus.values()) {
      SVSStatusView view = new SVSStatusView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      svsStatus.add(view);
    }
    List<SVSGradeView> svsGrade = new ArrayList<>();
    for (SVSGradeType value : SVSGradeType.values()) {
      SVSGradeView view = new SVSGradeView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      svsGrade.add(view);
    }
    List<StoreStatusView> storeStatus = new ArrayList<>();
    for (Usr.SStatus value : Usr.SStatus.values()) {
      StoreStatusView view = new StoreStatusView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      storeStatus.add(view);
    }
    List<StatusView> status = new ArrayList<>();
    for (OStatus value : OStatus.values()) {
      StatusView view = new StatusView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      status.add(view);
    }
    List<AuthView> auths = new ArrayList<>();
    for (Usr.OIsAuth value : Usr.OIsAuth.values()) {
      AuthView view = new AuthView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      auths.add(view);
    }
    List<IsProsView> isPros = new ArrayList<>();
    for (Sys.OYn value : Sys.OYn.values()) {
      IsProsView view = new IsProsView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      isPros.add(view);
    }
    SQL countrySql =
        new SQL() {
          {
            SELECT(PltCountry.T.PKEY, PltCountry.T.NAME).FROM(PltCountry.class);
          }
        };
    List<CountryView> countrys =
        irille.pub.bean.Query.sql(countrySql).queryMaps().stream()
            .map(
                o ->
                    new CountryView() {
                      {
                        setId((Integer) o.get(PltCountry.T.PKEY.getFld().getCodeSqlField()));
                        setName((String) o.get(PltCountry.T.NAME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    ListView view = new ListView();
    view.setCategorys(categorys);
    view.setStatus(status);
    view.setAuths(auths);
    view.setIsPros(isPros);
    view.setCountrys(countrys);
    view.setStoreStatus(storeStatus);
    view.setSvsStatus(svsStatus);
    view.setSvsGrade(svsGrade);
    return view;
  }

  public static class UpdStatus extends IduOther<UpdStatus, UsrSupplier> {

    @Override
    public void before() {}

    @Override
    public void valid() {}

    @Override
    public void run() {
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), T.STATUS);
      dbBean.upd();
      super.run();
      // TODO 店铺审核站内信
      IPMMessageService messageService = new PMMessageServiceImp();
      messageService.send(OTempType.SHOP_APPR, dbBean, null, dbBean);
    }
  }

  public static BasicInformationView getBasicInformation(Integer suppllierId) {
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(T.PKEY, "=?", suppllierId);
          }
        };
    UsrSupplier usrSupplier = irille.pub.bean.Query.sql(sql).query(UsrSupplier.class);
    BasicInformationView view = new BasicInformationView();
    view.setName(usrSupplier.getName());
    view.setCategory(usrSupplier.getCategory());
    view.setAuth(usrSupplier.getIsAuth());
    view.setAuthTime(usrSupplier.getAuthTime());
    view.setEntity(usrSupplier.getEntity());
    view.setCreditCode(usrSupplier.getCreditCode());
    view.setCompanyEstablishTime(usrSupplier.getCompanyEstablishTime());
    view.setOperationTerm(usrSupplier.getOperationTerm());
    view.setDes(usrSupplier.getDes());
    view.setContacts(usrSupplier.getContacts());
    view.setEmail(usrSupplier.getEmail());
    view.setPhone(usrSupplier.getPhone());
    view.setTelephone(usrSupplier.getTelephone());
    view.setFax(usrSupplier.getFax());
    view.setQq(usrSupplier.getQq());
    view.setSeoTitle(usrSupplier.getSeoTitle());
    view.setSeoContent(usrSupplier.getSeoContent());
    view.setShowName(usrSupplier.getShowName());
    view.setCompanyNature(usrSupplier.getCompanyNature());
    view.setCompanyType(usrSupplier.getCompanyType());
    view.setMainSalesArea(usrSupplier.getMainSalesArea());
    view.setMainProd(usrSupplier.getMainProd());
    view.setProdPattern(usrSupplier.getProdPattern());
    view.setCompanyAddr(usrSupplier.getCompanyAddr());
    if (usrSupplier.getCertPhoto() != null) {
      view.setCertPhoto(usrSupplier.getCertPhoto());
    } else {
      view.setCertPhoto("");
    }
    if (usrSupplier.getIdCardFrontPhoto() != null) {
      view.setIdCardFrontPhoto(usrSupplier.getIdCardFrontPhoto());
    } else {
      view.setIdCardFrontPhoto("");
    }
    if (usrSupplier.getIdCardBackPhoto() != null) {
      view.setIdCardBackPhoto(usrSupplier.getIdCardBackPhoto());
    } else {
      view.setIdCardBackPhoto("");
    }
    if (usrSupplier.getCoopCertPhoto() != null) {
      view.setCoopCertPhoto(usrSupplier.getCoopCertPhoto());
    } else {
      view.setCoopCertPhoto("");
    }
    view.setSort(usrSupplier.getSort());
    return view;
  }

  public static class UpdBasicInformation extends IduOther<UpdBasicInformation, UsrSupplier> {

    @Override
    public void before() {}

    @Override
    public void valid() {}

    @Override
    public void run() {
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.NAME,
          T.CATEGORY,
          T.IS_AUTH,
          T.ENTITY,
          T.CREDIT_CODE,
          T.OPERATION_TERM,
          T.DES,
          T.CONTACTS,
          T.EMAIL,
          T.PHONE,
          T.TELEPHONE,
          T.FAX,
          T.QQ,
          T.SEO_TITLE,
          T.SEO_CONTENT,
          T.SHOW_NAME,
          T.COMPANY_NATURE,
          T.COMPANY_TYPE,
          T.MAIN_SALES_AREA,
          T.MAIN_PROD,
          T.PROD_PATTERN,
          T.COMPANY_ADDR,
          T.CERT_PHOTO,
          T.ID_CARD_FRONT_PHOTO,
          T.ID_CARD_BACK_PHOTO,
          T.COOP_CERT_PHOTO,
          T.SORT);
      dbBean.upd();
      super.run();
    }
  }

  public static PageInformationView getPageInformation(Integer suppllierId) {
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(T.PKEY, "=?", suppllierId);
          }
        };
    UsrSupplier usrSupplier = irille.pub.bean.Query.sql(sql).query(UsrSupplier.class);
    PageInformationView view = new PageInformationView();
    view.setSignBackgd(usrSupplier.getSignBackgd());
    view.setAdPhotoLink(usrSupplier.getAdPhotoLink());
    view.setCountry(usrSupplier.getCountry());
    view.setProvince(usrSupplier.getProvince());
    view.setIsPro(usrSupplier.getIsPro());
    view.setWebSite(usrSupplier.getWebSite());
    view.setCompanyPhotoLink(usrSupplier.getCompanyPhotoLink());
    view.setBusinessTyp(usrSupplier.getBusinessTyp());
    view.setLocation(usrSupplier.getLocation());
    view.setDeveloper(usrSupplier.getDeveloper());
    view.setProduction(usrSupplier.getProduction());
    view.setTotalEmployees(usrSupplier.getTotalEmployees());
    view.setAnnualSales(usrSupplier.getAnnualSales());
    view.setTopMarkets(usrSupplier.getTop3Markets());
    view.setMaterials(usrSupplier.getMaterials());
    view.setDepartment(usrSupplier.getDepartment());
    view.setJobTitle(usrSupplier.getJobTitle());
    view.setCity(usrSupplier.getCity());
    if (usrSupplier.getAdPhoto() != null) {
      view.setAdPhoto(usrSupplier.getAdPhoto());
    } else {
      view.setAdPhoto("");
    }
    if (usrSupplier.getAdPhotoMobile() != null) {
      view.setAdPhotoMobile(usrSupplier.getAdPhotoMobile());
    } else {
      view.setAdPhotoMobile("");
    }
    if (usrSupplier.getCompanyPhoto() != null) {
      view.setCompanyPhoto(usrSupplier.getCompanyPhoto());
    } else {
      view.setCompanyPhoto("");
    }
    if (usrSupplier.getHeadPic() != null) {
      view.setHeadPic(usrSupplier.getHeadPic());
    } else {
      view.setHeadPic("");
    }
    if (usrSupplier.getLogo() != null) {
      view.setLogo(usrSupplier.getLogo());
    } else {
      view.setLogo("");
    }
    return view;
  }

  public static class UpdPageInformation extends IduOther<UpdPageInformation, UsrSupplier> {

    @Override
    public void before() {}

    @Override
    public void valid() {}

    @Override
    public void run() {
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.SIGN_BACKGD,
          T.AD_PHOTO_LINK,
          T.COUNTRY,
          T.PROVINCE,
          T.Is_Pro,
          T.WEB_SITE,
          T.COMPANY_PHOTO_LINK,
          T.BUSINESS_TYP,
          T.LOCATION,
          T.DEVELOPER,
          T.PRODUCTION,
          T.TOTAL_EMPLOYEES,
          T.ANNUAL_SALES,
          T.TOP_3_MARKETS,
          T.MATERIALS,
          T.DEPARTMENT,
          T.JOB_TITLE,
          T.CITY,
          T.AD_PHOTO,
          T.AD_PHOTO_MOBILE,
          T.COMPANY_PHOTO,
          T.HEAD_PIC,
          T.LOGO);
      dbBean.upd();
      super.run();
    }
  }

  public static PersonalityDecorationView getPersonalityDecoration(Integer suppllierId) {
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(T.PKEY, "=?", suppllierId);
          }
        };
    UsrSupplier usrSupplier = irille.pub.bean.Query.sql(sql).query(UsrSupplier.class);
    PersonalityDecorationView view = new PersonalityDecorationView();
    view.setHomePageDiy(usrSupplier.getHomePageDiy());
    view.setProductPageDiy(usrSupplier.getProductPageDiy());
    view.setContactPageDiy(usrSupplier.getContactPageDiy());
    view.setHomePageDiyMobile(usrSupplier.getHomePageDiyMobile());
    view.setProductPageDiyMobile(usrSupplier.getProductPageDiyMobile());
    view.setContactPageDiyMobile(usrSupplier.getContactPageDiyMobile());
    return view;
  }

  public static class UpdPersonalityDecoration
      extends IduOther<UpdPersonalityDecoration, UsrSupplier> {

    @Override
    public void before() {}

    @Override
    public void valid() {}

    @Override
    public void run() {
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.HOME_PAGE_DIY,
          T.PRODUCT_PAGE_DIY,
          T.CONTACT_PAGE_DIY,
          T.HOME_PAGE_DIY_MOBILE,
          T.PRODUCT_PAGE_DIY_MOBILE,
          T.CONTACT_PAGE_DIY_MOBILE);
      dbBean.upd();
      super.run();
    }
  }

  public static MarketingSettingsView getmarketingSettings(Integer suppllierId) {
    SQL sql =
        new SQL() {
          {
            SELECT(UsrSupplier.class).FROM(UsrSupplier.class).WHERE(T.PKEY, "=?", suppllierId);
          }
        };
    UsrSupplier usrSupplier = irille.pub.bean.Query.sql(sql).query(UsrSupplier.class);
    MarketingSettingsView view = new MarketingSettingsView();
    view.setTraceCode(usrSupplier.getTraceCode());
    view.setWebSizeTitle(usrSupplier.getWebSizeTitle());
    view.setWebSite(usrSupplier.getWebSite());
    view.setTongjiUrl(usrSupplier.getTongjiUrl());
    view.setTongjiPwd(usrSupplier.getTongjiPwd());
    view.setUpdateTime(usrSupplier.getUpdateTime());
    return view;
  }

  public static class UpdMarketingSettings extends IduOther<UpdMarketingSettings, UsrSupplier> {

    @Override
    public void before() {}

    @Override
    public void valid() {}

    @Override
    public void run() {
      getB().setUpdateTime(Env.getTranBeginTime());
      UsrSupplier dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          T.TRACE_CODE,
          T.WEB_SIZE_TITLE,
          T.WEB_SITE,
          T.TONGJI_URL,
          T.TONGJI_PWD,
          T.UPDATE_TIME);
      dbBean.upd();
      super.run();
    }
  }
  /** ———————————————————分割线(新平台)END————————————————————————— */

  /** ———————————————————分割线(3.1.1)——————————————————————————— */
  @Inject SVSInfoService svsInfoService;

  public SupplierDetailsDTO getSupplierDetails(Integer supplierId) {
    SupplierDetailsDTO view = new SupplierDetailsDTO();
    SQL sql =
        new SQL() {
          {
            SELECT(
                    T.PKEY,
                    T.LOGO,
                    T.NAME,
                    UsrMain.T.EMAIL,
                    T.TARGETED_MARKET,
                    SVSInfo.T.AUTHENTICATION_TIME)
                .FROM(UsrSupplier.class)
                .LEFT_JOIN(SVSInfo.class, SVSInfo.T.SUPPLIER, T.PKEY)
                .LEFT_JOIN(UsrMain.class, UsrMain.T.PKEY, T.UserId)
                .WHERE(T.PKEY, "=?", supplierId);
          }
        };
    Map<String, Object> map = irille.pub.bean.Query.sql(sql).queryMap();
    view.setPkey((Integer) map.get(T.PKEY.getFld().getCodeSqlField()));
    view.setLogo((String) map.get(T.LOGO.getFld().getCodeSqlField()));
    view.setName((String) map.get(T.NAME.getFld().getCodeSqlField()));
    view.setUserName((String) map.get(UsrMain.T.EMAIL.getFld().getCodeSqlField()));
    view.setTargetedMarket((String) map.get(T.TARGETED_MARKET.getFld().getCodeSqlField()));
    view.setAuthentication_time(
        (Date) map.get(SVSInfo.T.AUTHENTICATION_TIME.getFld().getCodeSqlField()));
    view.setSvsRatingAndRosDTO(svsInfoService.getSvsRatingAndRos(supplierId));
    return view;
  }
  /** ———————————————————分割线(3.1.1END)————————————————————————— */
}
