package irille.Dao.RFQ.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import irille.Dao.RFQ.RFQConsultDao;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.RFQConsultRelation.T;
import irille.Entity.RFQ.Enums.RFQConsultRecommend;
import irille.Entity.RFQ.Enums.RFQConsultType;
import irille.Entity.RFQ.Enums.RFQConsultVerifyStatus;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.core.sys.Sys;
import irille.platform.rfq.view.CountryView;
import irille.platform.rfq.view.ProductView;
import irille.platform.rfq.view.PurchaseView;
import irille.platform.rfq.view.RFQConsultView;
import irille.platform.rfq.view.RFQMessageView;
import irille.platform.rfq.view.SupplierView;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.sellerAction.rfq.view.RFQConsultMessageView;
import irille.shop.pdt.Pdt;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtProduct;
import irille.shop.plt.PltCountry;
import irille.shop.plt.PltErate;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierRole;
import irille.view.Page;
import irille.view.RFQ.InquirysView;

public class RFQConsultDaoImpl implements RFQConsultDao {

  /** @author Jianhua Ying */
  @Override
  public Page<RFQConsultView> findAllView(Integer start, Integer limit, RFQConsultView condition) {
    BeanQuery<RFQConsult> query = createQuery();
    // 询盘是否被标记为已删除
    if (condition.getIsDeleted() != null) {
      query.WHERE(RFQConsult.T.IS_DELETED, "=?", BeanBase.booleanToByte(condition.getIsDeleted()));
    }
    // 询盘名称
    query.WHERE(
        condition.getTitle() != null,
        RFQConsult.T.TITLE,
        "like ?",
        "%" + condition.getTitle() + "%");
    // 采购商名称
    if (condition.getPurchase() != null && condition.getPurchase().getName() != null)
      query.WHERE(UsrPurchase.T.NAME, "like ?", "%" + condition.getPurchase().getName() + "%");
    // 供应商名称
    if (condition.getSupplier() != null && condition.getSupplier().getName() != null)
      query.WHERE(UsrSupplier.T.NAME, "like ?", "%" + condition.getSupplier().getName() + "%");
    // 产品名称
    if (condition.getProduct() != null && condition.getProduct().getName() != null)
      query.WHERE(PdtProduct.T.NAME, "like ?", "%" + condition.getProduct().getName() + "%");
    // 国家
    if (condition.getCountry() != null && condition.getCountry().getName() != null)
      query.WHERE(PltCountry.T.NAME, "like ?", "%" + condition.getCountry().getName() + "%");
    // 询盘类型
    if (condition.getType() != null) {
      query.WHERE(
          condition.getType() != null,
          RFQConsult.T.TYPE,
          "in ("
              + Stream.of(condition.getType().split(","))
                  .map(i -> "?")
                  .collect(Collectors.joining(","))
              + ")",
          Stream.of(condition.getType().split(",")).map(Byte::new).toArray(Serializable[]::new));
    }
    // 询盘的审核状态
    query.WHERE(
        condition.getVerifyStatus() != null,
        RFQConsult.T.VERIFY_STATUS,
        "= ?",
        condition.getVerifyStatus());
    query.ORDER_BY(RFQConsult.T.CREATE_TIME, "DESC");
    Integer count = query.queryCount();
    query.limit(start, limit);
    return new Page<>(toView(query.queryMaps()), start, limit, count);
  }

  /** @author Jianhua Ying */
  @Override
  public RFQConsultView findViewById(Integer id) {
    BeanQuery<RFQConsult> query = createQuery();
    // 查询额外的字段
    query
        .SELECT(
            RFQConsult.T.CREATE_TIME,
            RFQConsult.T.PRICE,
            RFQConsult.T.SHIPPING_TYPE,
            RFQConsult.T.PAY_TYPE,
            RFQConsult.T.EXTRA_DESCRIPTION,
            PltErate.T.CUR_NAME,
            RFQConsult.T.IMAGE)
        .LEFT_JOIN(PltErate.class, RFQConsult.T.CURRENCY, PltErate.T.PKEY)
        .WHERE(RFQConsult.T.PKEY, "=?", id);
    Map<String, Object> map = query.queryMap();
    RFQConsultView view = toView(map);
    // 添加额外的字段
    view.setCreateTime((Date) map.get(RFQConsult.T.CREATE_TIME.getFld().getCodeSqlField()));
    view.setPrice((String) map.get(RFQConsult.T.PRICE.getFld().getCodeSqlField()));
    view.setShippingType((Byte) map.get(RFQConsult.T.SHIPPING_TYPE.getFld().getCodeSqlField()));
    view.setPayType((Byte) map.get(RFQConsult.T.PAY_TYPE.getFld().getCodeSqlField()));
    view.setExtraDescription(
        (String) map.get(RFQConsult.T.EXTRA_DESCRIPTION.getFld().getCodeSqlField()));
    view.setImage((String) map.get(RFQConsult.T.IMAGE.getFld().getCodeSqlField()));
    view.setCurName((String) map.get(PltErate.T.CUR_NAME.getFld().getCodeSqlField()));
    return view;
  }

  private BeanQuery<RFQConsult> createQuery() {
    return Query.SELECT(
            RFQConsult.T.PKEY, // 主键
            RFQConsult.T.TITLE, // 询盘标题
            RFQConsult.T.TYPE, // 询盘类型
            RFQConsult.T.QUANTITY, // 采购数量
            RFQConsult.T.UNIT, // 询盘单位 如 双
            RFQConsult.T.CONTENT,
            RFQConsult.T.TOTAL, // 询盘抢单总次数
            RFQConsult.T.LEFT_COUNT, // 询盘剩余抢单次数
            RFQConsult.T.VALID_DATE, // 询盘过期时间
            RFQConsult.T.STATUS, // 询盘状态
            RFQConsult.T.VERIFY_STATUS, // 询盘审核状态
            RFQConsult.T.CREATE_TIME, // 发布时间
            RFQConsult.T.RECOMMEND // 是否推荐
            )
        .SELECT(UsrSupplier.T.PKEY, "supplierPkey") // 询盘供应商主键
        .SELECT(UsrSupplier.T.NAME, "supplierName") // 询盘供应商名称
        .SELECT(UsrSupplierRole.T.PKEY, "supplierRolePkey") // 询盘供应商等级主键
        .SELECT(UsrSupplierRole.T.NAME, "supplierRoleName") // 询盘供应商等级名称
        .SELECT(PdtCat.T.PKEY, "productCatPkey") // 询盘商品分类主键
        .SELECT(PdtCat.T.NAME, "productCatName") // 询盘商品分类名称
        .SELECT(PdtProduct.T.PKEY, "productPkey") // 询盘商品主键
        .SELECT(PdtProduct.T.NAME, "productName") // 询盘商品名称
        .SELECT(UsrPurchase.T.PKEY, "purchasePkey") // 询盘采购商主键
        .SELECT(UsrPurchase.T.NAME, "purchaseName") // 询盘采购商名称
        .SELECT(PltCountry.T.PKEY, "countryPkey") // 采购商国家主键
        .SELECT(PltCountry.T.NAME, "countryName") // 采购商国家名称
        .SELECT(PltCountry.T.NATIONAL_FLAG, "countryLogo") // 采购商国家国旗
        .FROM(RFQConsult.class)
        .LEFT_JOIN(UsrPurchase.class, RFQConsult.T.PURCHASE_ID, UsrPurchase.T.PKEY)
        .LEFT_JOIN(PltCountry.class, UsrPurchase.T.COUNTRY, PltCountry.T.PKEY)
        .LEFT_JOIN(UsrSupplier.class, RFQConsult.T.SUPPLIER_ID, UsrSupplier.T.PKEY)
        .LEFT_JOIN(UsrSupplierRole.class, UsrSupplier.T.ROLE, UsrSupplierRole.T.PKEY)
        //                .LEFT_JOIN(PltCountry.class, RFQConsult.T.COUNTRY, PltCountry.T.PKEY)
        .LEFT_JOIN(PdtProduct.class, RFQConsult.T.PRODUCT, PdtProduct.T.PKEY)
        .LEFT_JOIN(PdtCat.class, PdtProduct.T.CATEGORY, PdtCat.T.PKEY);
  }

  private RFQConsultView toView(Map<String, Object> map) {
    RFQConsultView view = new RFQConsultView();
    view.setPkey((Integer) map.get(RFQConsult.T.PKEY.getFld().getCodeSqlField()));
    view.setTitle((String) map.get(RFQConsult.T.TITLE.getFld().getCodeSqlField()));
    view.setType("" + map.get(RFQConsult.T.TYPE.getFld().getCodeSqlField()));
    view.setContent((String) map.get(RFQConsult.T.CONTENT.getFld().getCodeSqlField()));
    if (map.containsKey("supplierPkey")) {
      view.setSupplier(
          new SupplierView() {
            {
              setPkey((Integer) map.get("supplierPkey"));
              setName((String) map.get("supplierName"));
              if (map.containsKey("supplierRoleName")) {
                setRoleName((String) map.get("supplierRoleName"));
              }
            }
          });
    }
    if (map.containsKey("productPkey")) {
      view.setProduct(
          new ProductView() {
            {
              setPkey((Integer) map.get("productPkey"));
              setName((String) map.get("productName"));
              if (map.containsKey("productCatName")) {
                setCatName((String) map.get("productCatName"));
              }
            }
          });
    }
    if (map.containsKey("purchasePkey")) {
      view.setPurchase(
          new PurchaseView() {
            {
              setPkey((Integer) map.get("purchasePkey"));
              setName((String) map.get("purchaseName"));
            }
          });
    }
    if (map.containsKey("countryPkey")) {
      view.setCountry(
          new CountryView() {
            {
              setPkey((Integer) map.get("countryPkey"));
              setName((String) map.get("countryName"));
              setNationalFlag((String) map.get("countryLogo"));
            }
          });
    }
    view.setQuantity((Integer) map.get(RFQConsult.T.QUANTITY.getFld().getCodeSqlField()));
    view.setUnit((Byte) map.get(RFQConsult.T.UNIT.getFld().getCodeSqlField()));
    view.setTotal((Integer) map.get(RFQConsult.T.TOTAL.getFld().getCodeSqlField()));
    view.setLeftCount((Integer) map.get(RFQConsult.T.LEFT_COUNT.getFld().getCodeSqlField()));
    view.setValidDate((Date) map.get(RFQConsult.T.VALID_DATE.getFld().getCodeSqlField()));
    view.setStatus((Byte) map.get(RFQConsult.T.STATUS.getFld().getCodeSqlField()));
    view.setVerifyStatus((Byte) map.get(RFQConsult.T.VERIFY_STATUS.getFld().getCodeSqlField()));
    view.setCreateTime((Date) map.get(RFQConsult.T.CREATE_TIME.getFld().getCodeSqlField()));
    view.setRecommend(
        map.get(RFQConsult.T.RECOMMEND.getFld().getCodeSqlField()) != null
            ? (Byte) map.get(RFQConsult.T.RECOMMEND.getFld().getCodeSqlField())
            : RFQConsultRecommend.NOT_RECOMMENDED.getLine().getKey());
    return view;
  }

  @Override
  public RFQConsult findById(Integer pkey) {
    return Query.SELECT(RFQConsult.class, pkey);
  }

  @Override
  public void save(RFQConsult bean) {
    if (bean.getPkey() == null) {
      bean.ins();
    } else {
      bean.upd();
    }
  }

  private List<RFQConsultView> toView(List<Map<String, Object>> result) {
    return result.stream()
        .map(
            map -> {
              return toView(map);
            })
        .collect(Collectors.toList());
  }

  /** @author Jianhua Ying */
  public static void main(String[] args) {
    RFQConsultDaoImpl dao = new RFQConsultDaoImpl();
    dao.testDirector();
  }

  /** @author Jianhua Ying */
  @Test
  public void testDirector() {
    RFQConsult.TB.getCode();
    UsrPurchase.TB.getCode();
    UsrSupplier.TB.getCode();
    UsrSupplierRole.TB.getCode();
    PltCountry.TB.getCode();
    PdtProduct.TB.getCode();
    PdtCat.TB.getCode();

    System.out.println("==========================================================");
    testFindAll("n", "有限公司", "chen", "童鞋", "中国", "1", (byte) 2);
    System.out.println("==========================================================");
    testFindById(1);
    System.out.println("==========================================================");
  }

  private void testFindById(Integer id) {
    System.out.println(findViewById(id));
    ;
  }

  /** @author Jianhua Ying */
  private void testFindAll(
      String title,
      String supplierName,
      String purchaseName,
      String productName,
      String countryName,
      String type,
      Byte verifyStatus) {
    RFQConsultView condition = new RFQConsultView();
    condition.setTitle(title);
    condition.setPurchase(
        new PurchaseView() {
          {
            setName(purchaseName);
          }
        });
    condition.setSupplier(
        new SupplierView() {
          {
            setName(supplierName);
          }
        });
    condition.setProduct(
        new ProductView() {
          {
            setName(productName);
          }
        });
    condition.setCountry(
        new CountryView() {
          {
            setName(countryName);
          }
        });

    condition.setType(type);
    condition.setVerifyStatus(verifyStatus);

    Page<RFQConsultView> page = findAllView(0, 10, condition);
    System.out.println("total: " + page.getTotalCount());
    page.getItems()
        .forEach(
            view -> {
              System.out.println(view);
            });
  }

  /**
   * @Description: 获取询盘列表
   *
   * @date 2019/1/30 15:44
   * @author lijie@shoestp.cn
   *     <p>询盘状态必须为RFQ询盘 有效期必须大于当前时间 审核状态必须为已审核 询盘关联表的供应商id为当前供应商(不加会出现查询重复)
   *     <p>查询inquiry时必须满足： 询盘关联表的consult等于询盘表的pkey 并且是当前登录的供应商
   */
  @Override
  public List<Map<String, Object>> getRFQList(int start, int limit, String keyword, int supId) {
    return Query.sql(getRFQListSql(start, limit, keyword, supId)).queryMaps();
  }

  @Override
  public int getRFQListCount(int start, int limit, String keyword, Integer supId) {
    return Query.sql(getRFQListSql(start, limit, keyword, supId)).queryCount();
  }

  private SQL getRFQListSql(int start, int limit, String keyword, int supId) {
    SQL sql = new SQL();
    SQL sql1 = new SQL();
    //        RFQ询盘 报价的时候,会在关联表添加一条数据
    //        私有,询盘读取的时候也会添加一条关联数据
    sql1.SELECT(RFQConsultRelation.T.FAVORITE, RFQConsultRelation.T.CONSULT)
        .SELECT(T.PKEY, "inquiry")
        .FROM(RFQConsultRelation.class)
        .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "= ?");
    sql.SELECT(
            RFQConsult.T.PKEY,
            RFQConsult.T.TITLE,
            RFQConsult.T.COUNTRY,
            RFQConsult.T.CREATE_TIME,
            RFQConsult.T.QUANTITY,
            RFQConsult.T.CONTENT,
            RFQConsult.T.UNIT,
            RFQConsult.T.LEFT_COUNT,
            RFQConsult.T.IMAGE,
            RFQConsult.T.TOTAL)
        .SELECT("r." + RFQConsultRelation.T.CONSULT.getFld().getCodeSqlField())
        .SELECT("r.inquiry")
        .FROM(RFQConsult.class)
        .PARAM(supId)
        .WHERE(RFQConsult.T.TYPE, "=?", RFQConsultType.RFQ)
        .LEFT_JOIN(
            "("
                + sql1.toString()
                + ") as r on r."
                + RFQConsultRelation.T.CONSULT.getFld().getCodeSqlField()
                + "="
                + RFQConsult.T.PKEY.getFld().getCodeSqlField())
        .LIMIT(start, limit)
        .WHERE(keyword != null && keyword.length() > 0, RFQConsult.T.TITLE, "like ?", keyword)
        .WHERE(RFQConsult.T.VALID_DATE, ">?", LocalDateTime.now())
        .WHERE(RFQConsult.T.VERIFY_STATUS, "=?", RFQConsultVerifyStatus.PASS);
    sql.ORDER_BY(RFQConsult.T.CREATE_TIME, "desc");
    return sql;
  }

  public RFQConsult getRFQInfo(int id) {
    SQL sql = new SQL();
    sql.SELECT(RFQConsult.class).FROM(RFQConsult.class).WHERE(RFQConsult.T.PKEY, "=?", id);
    return Query.sql(sql).query(RFQConsult.class);
  }

  public List<Map<String, Object>> getRFQofferList(int id) {
    SQL sql = new SQL();
    sql.SELECT(UsrSupplier.T.CITY, RFQConsultRelation.T.TITLE, RFQConsultRelation.T.CREATE_DATE)
        .FROM(RFQConsultRelation.class)
        .WHERE(RFQConsultRelation.T.CONSULT, "=?", id)
        .WHERE(RFQConsultRelation.T.IN_RECYCLE_BIN, "=?", Sys.OYn.NO)
        .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, RFQConsultRelation.T.SUPPLIER_ID);
    return Query.sql(sql).queryMaps();
  }

  public RFQConsultRelation getRFQRelation(int rfqId, Integer pkey) {
    RFQConsultRelation result = null;
    if (Query.SELECT(RFQConsultRelation.class)
            .WHERE(RFQConsultRelation.T.CONSULT, "=?", rfqId)
            .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", pkey)
            .query()
        != null) return null;
    result = new RFQConsultRelation();
    return result;
  }

  @Override
  public Map<String, Object> getMyPdtInfo(Integer id, Integer pkey) {
    return Query.SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON) // TODO 商品的其他逻辑
        .queryMap();
  }

  @Override
  public List getPdtList(Integer start, Integer limit, String keyword, Integer pkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.SUPPLIER, "=?", pkey)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON) // TODO 商品的其他逻辑
        .WHERE(keyword != null && keyword.length() > 0, PdtProduct.T.NAME, "like ?", keyword)
        .LIMIT(start, limit);
    return Query.sql(sql).queryMaps();
  }

  @Override
  public int getPdtListCount(Integer start, Integer limit, String keyword, Integer pkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtProduct.T.PKEY, PdtProduct.T.NAME, PdtProduct.T.PICTURE)
        .FROM(PdtProduct.class)
        .WHERE(PdtProduct.T.STATE, "=?", Pdt.OState.ON) // TODO 商品的其他逻辑
        .WHERE(PdtProduct.T.SUPPLIER, "=?", pkey)
        .WHERE(keyword != null && keyword.length() > 0, PdtProduct.T.NAME, "like ?", keyword)
        .LIMIT(start, limit);
    return Query.sql(sql).queryCount();
  }

  @Override
  public List<Map<String, Object>> getMyRFQQuoteList(
      Integer start,
      Integer limit,
      Date date,
      String keyword,
      boolean flag,
      Byte readStatus,
      Integer country,
      int supId,
      Integer usrCountry) {
    return Query.sql(
            getMyRFQQuoteListSql(date, keyword, flag, readStatus, country, supId, usrCountry)
                .LIMIT(start, limit))
        .queryMaps();
  }

  @Override
  public Integer countMyRFQQuoteList(
      Date date,
      String keyword,
      boolean flag,
      Byte readStatus,
      Integer country,
      int supId,
      Integer usrCountry) {
    return Query.sql(
            getMyRFQQuoteListSql(date, keyword, flag, readStatus, country, supId, usrCountry))
        .queryCount();
  }

  private SQL getMyRFQQuoteListSql(
      Date date,
      String keyword,
      boolean flag,
      Byte readStatus,
      Integer country,
      int supId,
      Integer usrCountry) {
    SQL sql = new SQL();
    sql.SELECT(
            RFQConsult.T.PKEY,
            RFQConsult.T.TITLE,
            RFQConsult.T.QUANTITY,
            RFQConsult.T.CONTENT,
            RFQConsult.T.CREATE_TIME,
            RFQConsultRelation.T.READ_STATUS,
            RFQConsultRelation.T.QUANTITY,
            RFQConsultRelation.T.DESCRIPTION,
            RFQConsultRelation.T.PURCHASE_ID,
            RFQConsultMessage.T.HAD_READ,
            RFQConsultMessage.T.P2S)
        .SELECT(RFQConsultRelation.T.TITLE, "myTitle")
        .SELECT(RFQConsultRelation.T.CREATE_DATE, "myCreate_time")
        .FROM(RFQConsult.class)
        .LEFT_JOIN(RFQConsultRelation.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY)
        .LEFT_JOIN(UsrPurchase.class, UsrPurchase.T.PKEY, T.PURCHASE_ID)
        .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supId)
        .WHERE(RFQConsultRelation.T.IN_RECYCLE_BIN, "=?", Sys.OYn.NO);
    sql.WHERE(readStatus != null, RFQConsultRelation.T.READ_STATUS, "=?", readStatus);
    sql.WHERE(country != null, RFQConsult.T.COUNTRY, "=?", country);
    if (null != date) {
      sql.WHERE(
          " DATEDIFF("
              + RFQConsultRelation.class.getSimpleName()
              + "."
              + RFQConsultRelation.T.CREATE_DATE.getFld().getCodeSqlField()
              + ",?) = 0",
          date);
    }
    sql.WHERE(keyword != null, RFQConsult.T.TITLE, "like ?", "%" + keyword + "%");
    sql.WHERE(T.FAVORITE, "=?", flag);
    sql.WHERE(usrCountry != null, UsrPurchase.T.COUNTRY, "=?", usrCountry);
    return sql;
  }

  @Override
  public Map<String, Object> getMyRFQQuoteInfo(Integer id, Integer pkey) {
    SQL sql = new SQL();
    sql.SELECT(
            RFQConsultRelation.T.PKEY,
            RFQConsultRelation.T.TITLE,
            RFQConsultRelation.T.DESCRIPTION,
            RFQConsultRelation.T.IMAGE,
            RFQConsultRelation.T.CURRENCY,
            RFQConsultRelation.T.QUANTITY,
            RFQConsultRelation.T.PAYTYPE,
            RFQConsultRelation.T.IMAGE,
            RFQConsultRelation.T.TRANSITTYPE,
            RFQConsultRelation.T.VALID_DATE,
            RFQConsultRelation.T.SAMPLE,
            RFQConsultRelation.T.COMPANYDESCRIBE,
            RFQConsultRelation.T.THROWAWAY,
            RFQConsultRelation.T.MINPRICE,
            RFQConsultRelation.T.MAXPRICE)
        .FROM(RFQConsult.class)
        .LEFT_JOIN(RFQConsultRelation.class, RFQConsultRelation.T.CONSULT, RFQConsult.T.PKEY)
        .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", pkey)
        .WHERE(RFQConsultRelation.T.CONSULT, "=?", id);
    return Query.sql(sql).queryMap();
  }

  @Override
  public Page getRFQMsgList(Integer id, Integer start, Integer limit) {
    SQL sql =
        new SQL() {
          {
            SELECT(RFQConsultRelation.class)
                .SELECT(UsrSupplier.T.NAME, "supplierName")
                .SELECT(UsrSupplier.T.PKEY, "supplierId")
                .SELECT(UsrPurchase.T.NAME, "userName")
                .FROM(RFQConsultRelation.class)
                .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, RFQConsultRelation.T.SUPPLIER_ID)
                .LEFT_JOIN(UsrPurchase.class, UsrPurchase.T.PKEY, RFQConsultRelation.T.PURCHASE_ID)
                .WHERE(RFQConsultRelation.T.CONSULT, "=?", id)
                .LIMIT(start, limit);
          }
        };
    int num = 0;
    String str = "";
    Map<Integer, RFQMessageView> beans = new HashMap<>();
    Integer count = Query.sql(sql).queryCount();
    List<Map<String, Object>> lists = Query.sql(sql).queryMaps();
    for (Map<String, Object> map : lists) {
      RFQMessageView view = new RFQMessageView();
      view.setPkey((Integer) map.get(RFQConsultRelation.T.PKEY.getFld().getCodeSqlField()));
      view.setSupplierName((String) map.get("supplierName"));
      view.setUser((String) map.get("userName"));
      str +=
          "( SELECT"
              + " m.msgPkey AS msgPkey,"
              + " m.content AS msgContent,"
              + " m.type AS msgType,"
              + " m.time AS msgTime,"
              + " m.ps AS msgPs,"
              + " relation.pkey as relPkey "
              + " FROM"
              + " r_f_q_consult_relation relation"
              + " LEFT JOIN ("
              + " SELECT"
              + " msg.pkey AS msgPkey,"
              + " msg.relation AS rel,"
              + " msg.content AS content,"
              + " msg.type AS type,"
              + " msg.send_time AS time,"
              + " msg.p2s AS ps "
              + " FROM"
              + " r_f_q_consult_message msg "
              + " ) m ON m.rel = relation.pkey "
              + " WHERE"
              + " relation.supplier_id = "
              + ((Integer) map.get("supplierId"))
              + " AND relation.consult = "
              + id
              + " ORDER BY"
              + " m.time DESC "
              + "LIMIT 0,4) ";
      if (lists.size() - 1 != num) {
        str += " UNION ALL ";
      }
      if (!beans.containsKey(view.getPkey())) {
        beans.put(view.getPkey(), view);
      }
      num++;
    }
    if (!("".equals(str))) {
      List<Map<String, Object>> entitys = Query.sql(str).queryMaps();
      for (Map<String, Object> m : entitys) {
        if (m.get("msgContent") != null) {
          RFQMessageView rfqMessageView = beans.get(m.get("relPkey"));
          if (rfqMessageView != null) {
            RFQConsultMessageView view = new RFQConsultMessageView();
            view.setPkey((Integer) m.get("msgPkey"));
            view.setContent((String) m.get("msgContent"));
            view.setType((Byte) m.get("msgType"));
            view.setSendTime((Date) m.get("msgTime"));
            view.setRelation((Integer) m.get("relPkey"));
            view.setP2S((((Byte) m.get("msgPs")) == 1) ? true : false);
            if (rfqMessageView.getMessages() != null) {
              rfqMessageView.getMessages().add(view);
            } else {
              List<RFQConsultMessageView> views = new ArrayList<>();
              views.add(view);
              rfqMessageView.setMessages(views);
            }
          }
        }
      }
    }
    return new Page<RFQMessageView>(
        new ArrayList<RFQMessageView>(beans.values()), start, limit, count);
  }

  @Override
  public Page getMessage(Integer id, Integer start, Integer limit) {
    SQL sql =
        new SQL() {
          {
            SELECT(RFQConsultMessage.class)
                .FROM(RFQConsultMessage.class)
                .WHERE(RFQConsultMessage.T.RELATION, "=?", id)
                .ORDER_BY(RFQConsultMessage.T.SEND_TIME, "desc");
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<RFQConsultMessageView> view =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new RFQConsultMessageView() {
                      {
                        setPkey(
                            (Integer) o.get(RFQConsultMessage.T.PKEY.getFld().getCodeSqlField()));
                        setContent(
                            (String) o.get(RFQConsultMessage.T.CONTENT.getFld().getCodeSqlField()));
                        setType((Byte) o.get(RFQConsultMessage.T.TYPE.getFld().getCodeSqlField()));
                        setSendTime(
                            (Date) o.get(RFQConsultMessage.T.SEND_TIME.getFld().getCodeSqlField()));
                        setP2S(
                            (((Byte) o.get(RFQConsultMessage.T.P2S.getFld().getCodeSqlField()))
                                    == 1)
                                ? true
                                : false);
                      }
                    })
            .collect(Collectors.toList());
    return new Page(view, start, limit, count);
  }

  @Override
  public Page getInqList(
      Integer start,
      Integer limit,
      Byte type,
      String supplierName,
      String purchaseName,
      String productName) {
    SQL sql =
        new SQL() {
          {
            SELECT(RFQConsult.T.PKEY, "rfqPkey")
                .SELECT(RFQConsult.T.TYPE, "rfqStatus")
                .SELECT(PdtProduct.T.NAME, "productName")
                .SELECT(PdtCat.T.NAME, "productCategories")
                .SELECT(RFQConsult.T.QUANTITY, "quantity")
                .SELECT(UsrPurchase.T.NAME, "purchaseName")
                .SELECT(PltCountry.T.NAME, "purchaseCountry")
                .SELECT(RFQConsult.T.CREATE_TIME, "releaseTime")
                .SELECT(UsrSupplier.T.NAME, "supplierName")
                .SELECT(SVSInfo.T.GRADE, "grade")
                .FROM(RFQConsult.class)
                .LEFT_JOIN(UsrPurchase.class, UsrPurchase.T.PKEY, RFQConsult.T.PURCHASE_ID)
                .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, RFQConsult.T.PRODUCT)
                .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, PdtProduct.T.SUPPLIER)
                .LEFT_JOIN(PdtCat.class, PdtCat.T.PKEY, PdtProduct.T.CATEGORY)
                .LEFT_JOIN(SVSInfo.class, SVSInfo.T.SUPPLIER, UsrSupplier.T.PKEY)
                .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, UsrPurchase.T.COUNTRY)
                .WHERE(RFQConsult.T.TYPE, "=?", RFQConsultType.Private_INQUIRY)
                .or()
                .WHERE(RFQConsult.T.TYPE, "=?", RFQConsultType.INQUIRY)
                .AND()
                .WHERE("1 = 1")
                .WHERE(type != null, RFQConsult.T.TYPE, "=?", type)
                .WHERE(supplierName != null, UsrSupplier.T.NAME, "like ?", "%" + supplierName + "%")
                .WHERE(purchaseName != null, UsrPurchase.T.NAME, "like ?", "%" + purchaseName + "%")
                .WHERE(productName != null, PdtProduct.T.NAME, "like ?", "%" + productName + "%");
          }
        };
    Integer num = Query.sql(sql).queryCount();
    List<InquirysView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new InquirysView() {
                      {
                        setRfqPkey((Integer) o.get("rfqPkey"));
                        setRfqStatus((Byte) o.get("rfqStatus"));
                        setProductName((String) o.get("productName"));
                        setProductCategories((String) o.get("productCategories"));
                        setQuantity((Integer) o.get("quantity"));
                        setPurchaseName((String) o.get("purchaseName"));
                        setPurchaseCountry((String) o.get("purchaseCountry"));
                        setReleaseTime((Date) o.get("releaseTime"));
                        setSupplierName((String) o.get("supplierName"));
                        setGrade((Byte) o.get("grade"));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, num);
  }

  @Override
  public JSONObject getInqStatus() throws JSONException {
    JSONObject jsonObject = new JSONObject();
    JSONArray inqTypeArray = new JSONArray();
    JSONArray svsGradeArray = new JSONArray();
    for (RFQConsultType value : RFQConsultType.values()) {
      if (value.getLine().getKey() == RFQConsultType.INQUIRY.getLine().getKey()
          || value.getLine().getKey() == RFQConsultType.Private_INQUIRY.getLine().getKey()) {
        JSONObject object = new JSONObject();
        object.put("key", value.getLine().getKey());
        object.put("value", value.getLine().getName());
        inqTypeArray.put(object);
      }
    }
    for (SVSGradeType value : SVSGradeType.values()) {
      JSONObject object = new JSONObject();
      object.put("key", value.getLine().getKey());
      object.put("value", value.getLine().getName());
      svsGradeArray.put(object);
    }
    jsonObject.put("inqType", inqTypeArray);
    jsonObject.put("svsGrade", svsGradeArray);
    return jsonObject;
  }

  @Override
  public InquirysView getInqDetail(Integer rfqPkey) {
    SQL sql =
        new SQL() {
          {
            SELECT(RFQConsult.T.PKEY, "rfqPkey")
                .SELECT(RFQConsult.T.TYPE, "rfqStatus")
                .SELECT(PdtProduct.T.NAME, "productName")
                .SELECT(PdtCat.T.NAME, "productCategories")
                .SELECT(RFQConsult.T.QUANTITY, "quantity")
                .SELECT(UsrPurchase.T.NAME, "purchaseName")
                .SELECT(PltCountry.T.NAME, "purchaseCountry")
                .SELECT(UsrSupplier.T.NAME, "supplierName")
                .SELECT(SVSInfo.T.GRADE, "grade")
                .FROM(RFQConsult.class)
                .LEFT_JOIN(UsrPurchase.class, UsrPurchase.T.PKEY, RFQConsult.T.PURCHASE_ID)
                .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, RFQConsult.T.PRODUCT)
                .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, PdtProduct.T.SUPPLIER)
                .LEFT_JOIN(PdtCat.class, PdtCat.T.PKEY, PdtProduct.T.CATEGORY)
                .LEFT_JOIN(SVSInfo.class, SVSInfo.T.SUPPLIER, UsrSupplier.T.PKEY)
                .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, UsrPurchase.T.COUNTRY)
                .WHERE(RFQConsult.T.TYPE, "=?", RFQConsultType.Private_INQUIRY)
                .or()
                .WHERE(RFQConsult.T.TYPE, "=?", RFQConsultType.INQUIRY)
                .AND()
                .WHERE("1 = 1")
                .WHERE(rfqPkey != null, RFQConsult.T.PKEY, "=?", rfqPkey);
          }
        };
    Map<String, Object> map = Query.sql(sql).queryMap();
    InquirysView view = new InquirysView();
    view.setRfqPkey((Integer) map.get("rfqPkey"));
    view.setRfqStatus((Byte) map.get("rfqStatus"));
    view.setProductName((String) map.get("productName"));
    view.setProductCategories((String) map.get("productCategories"));
    view.setQuantity((Integer) map.get("quantity"));
    view.setPurchaseName((String) map.get("purchaseName"));
    view.setPurchaseCountry((String) map.get("purchaseCountry"));
    view.setReleaseTime((Date) map.get("releaseTime"));
    view.setSupplierName((String) map.get("supplierName"));
    view.setGrade((Byte) map.get("grade"));
    return view;
  }
}
