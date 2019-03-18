package irille.Dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.Entity.OdrerMeetings.*;
import irille.platform.omt.View.OmtDetailsView;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.odr.Odr;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderLine;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtSpec;
import irille.shop.plt.PltCountry;
import irille.shop.usr.UsrSupplier;
import irille.view.Manage.OdrMeeting.OdrMeetingLaunchlistView;
import irille.view.Manage.OdrMeeting.OdrMeetingOtherlistView;
import irille.view.Manage.OdrMeeting.OdrMeetingParticipatelistView;
import irille.view.Manage.OdrMeeting.initiatedActivity.OrderInformationView;
import irille.view.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37 */
public class OdrMeetingDao {
  private static final Logger logger = LoggerFactory.getLogger(OdrMeetingDao.class);

  /**
   * @Description: 检查是否有条件参加订购会
   *
   * @date 2018/11/15 11:21
   * @author lijie@shoestp.cn
   */
  public boolean isJoinOdrMeeting(int odrMeetIngId, int supplier) {
    // 参加的人不能是发起人,且不再审核列状态中  订购会已通过审核 状态为即将开始
    BeanQuery query = new BeanQuery();
    query
        .SELECT(OrderMeeting.T.SUPPLIERID)
        .FROM(OrderMeetingAudit.class)
        .LEFT_JOIN(
            OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingAudit.T.ODRMEETING) // 订购会与审核表进行关联
        .WHERE(OrderMeeting.T.SUPPLIERID, "<> ?", supplier) // 不能是订购会发起人
        .WHERE(OrderMeeting.T.STATUS, "=?", OrderMeetingStatus.TOBEGIN) // 订购会状态要未开始,否则不能参加
        //                .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplier)//并且该订购会该用户应该是未参加的
        //                .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.ACTIVITY)
        .WHERE(OrderMeetingAudit.T.ODRMEETING, "=?", odrMeetIngId) // 查看该订订购会的
    ;
    return query.queryCount() == 0;
  }

  /**
   * @Description: 我发起订购会列表
   *
   * @date 2018/11/14 18:30
   * @anthor wilson zhang
   */
  public Page Launchlist(
      Integer start,
      Integer limit,
      String name,
      Integer supstate,
      Integer onstate,
      Integer getSupplier) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeeting.class);
            SELECT(OrderMeetingAuditRelease.T.STATUS, "AuditStatus");
            FROM(OrderMeeting.class);
            LEFT_JOIN(
                OrderMeetingAuditRelease.class,
                OrderMeetingAuditRelease.T.ODRMEETING,
                OrderMeeting.T.PKEY);

            if (name != null && !("".equals(name))) {
              WHERE(OrderMeeting.T.NAME, " like '%" + name + "%'");
            }
            if (onstate != null) {
              WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }
            WHERE(OrderMeeting.T.STATUS, " <>?", OrderMeetingStatus.DELETE.getLine().getKey());

            if (getSupplier != null) {
              WHERE(OrderMeeting.T.SUPPLIERID, " =?", getSupplier);
            }
            if (supstate != null) {
              WHERE(OrderMeetingAuditRelease.T.STATUS, " =?", supstate);
            }
            WHERE(
                OrderMeetingAuditRelease.T.STATUS,
                " <>?",
                OrderMeetingAuditStatus.DELETE.getLine().getKey());

            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
          }
        };
    Integer count = Query.sql(sql).queryCount();
    sql.LIMIT(start, limit);
    List<OdrMeetingLaunchlistView> list =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  OdrMeetingLaunchlistView oml = new OdrMeetingLaunchlistView();
                  oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
                  oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
                  PltCountry pc =
                      BeanBase.load(
                          PltCountry.class,
                          (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
                  oml.setCountry(pc.getName());
                  oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
                  oml.setStarttime(
                      (Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
                  oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
                  if (Integer.parseInt(String.valueOf(o.get("AuditStatus"))) == 1) {
                    oml.setState(
                        Integer.parseInt(
                            String.valueOf(
                                o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
                  } else {
                    oml.setState(Integer.parseInt(String.valueOf(o.get("AuditStatus"))));
                  }
                  return oml;
                })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * @Description: 参与订购会列表页面
   *
   * @date 2018/11/14 20:32
   * @anthor wilson zhang
   */
  public Page participatelist(
      Integer start, Integer limit, String name, Integer onstate, Integer getSupplier) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(
                    OrderMeeting.T.PKEY,
                    OrderMeeting.T.LOGO,
                    OrderMeeting.T.SUPPLIERID,
                    OrderMeeting.T.NAME,
                    OrderMeeting.T.EXHIBITION,
                    OrderMeeting.T.CUSTOM_EXHIBITION,
                    OrderMeeting.T.COUNTRY,
                    OrderMeeting.T.STATUS,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME)
                .SELECT(OrderMeetingExhibition.T.COUNTRY, "omecountry")
                .SELECT(OrderMeetingExhibition.T.NAME, "OMENAME");
            FROM(OrderMeeting.class);
            LEFT_JOIN(
                OrderMeetingExhibition.class,
                OrderMeetingExhibition.T.PKEY,
                OrderMeeting.T.EXHIBITION);
            LEFT_JOIN(OrderMeetingAudit.class, OrderMeetingAudit.T.ODRMEETING, OrderMeeting.T.PKEY);
            LEFT_JOIN(
                OrderMeetingAuditRelease.class,
                OrderMeetingAuditRelease.T.ODRMEETING,
                OrderMeeting.T.PKEY);
            WHERE(
                OrderMeetingAuditRelease.T.STATUS,
                " =?",
                OrderMeetingAuditStatus.ACTIVITY.getLine().getKey());
            if (name != null) {
              WHERE(OrderMeeting.T.NAME, " like '%" + name + "%'");
            }
            if (onstate != null) {
              WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }
            if (getSupplier != null) {
              WHERE(OrderMeetingAudit.T.SUPPLIERID, " =?", getSupplier);
            }
            WHERE(OrderMeeting.T.SUPPLIERID, " <> ?", getSupplier);
            WHERE(OrderMeeting.T.STATUS, " <> ?", OrderMeetingStatus.DELETE.getLine().getKey());
            WHERE(
                OrderMeetingAudit.T.STATUS,
                " =?",
                OrderMeetingAuditStatus.ACTIVITY.getLine().getKey());
            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
          }
        };
    Integer count = Query.sql(sql).queryCount();
    sql.LIMIT(start, limit);
    Query.sql(sql).limit(start, limit).queryMaps();
    List<OdrMeetingParticipatelistView> list =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  OdrMeetingParticipatelistView oml = new OdrMeetingParticipatelistView();
                  oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
                  oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
                  oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
                  if (o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()) != null
                      && !o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField())
                          .equals("")) {
                    oml.setExhibition(
                        (String)
                            o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
                  } else {
                    oml.setExhibition((String) o.get("OMENAME"));
                  }
                  UsrSupplier usr =
                      BeanBase.load(
                          UsrSupplier.class,
                          (Integer) o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()));
                  PltCountry pc =
                      BeanBase.load(
                          PltCountry.class,
                          (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
                  oml.setSuppliername(usr.getName());
                  oml.setCountry(pc.getName());
                  oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
                  oml.setStarttime(
                      (Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
                  oml.setState(
                      Integer.parseInt(
                          String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
                  return oml;
                })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * @Description: 其他订购会列表页面
   *
   * @date 2018/11/14 20:32
   * @anthor wilson zhang
   */
  public Page Otherlist(
      Integer start, Integer limit, String name, Integer onstate, Integer getSupplier) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(
                    OrderMeeting.T.PKEY,
                    OrderMeeting.T.LOGO,
                    OrderMeeting.T.NAME,
                    OrderMeeting.T.EXHIBITION,
                    OrderMeeting.T.CUSTOM_EXHIBITION,
                    OrderMeeting.T.COUNTRY,
                    OrderMeeting.T.STATUS,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME,
                    OrderMeeting.T.SUPPLIERID)
                .SELECT(OrderMeetingExhibition.T.COUNTRY, "omecountry")
                .SELECT(OrderMeetingExhibition.T.NAME, "OMENAME")
                .SELECT(OrderMeetingAudit.T.STATUS, "OMASTATUS");
            FROM(OrderMeeting.class);
            LEFT_JOIN(
                OrderMeetingExhibition.class,
                OrderMeetingExhibition.T.PKEY,
                OrderMeeting.T.EXHIBITION);
            LEFT_JOIN(OrderMeetingAudit.class, OrderMeetingAudit.T.ODRMEETING, OrderMeeting.T.PKEY);
            LEFT_JOIN(
                OrderMeetingAuditRelease.class,
                OrderMeetingAuditRelease.T.ODRMEETING,
                OrderMeeting.T.PKEY);
            WHERE(
                OrderMeetingAuditRelease.T.STATUS,
                " =?",
                OrderMeetingAuditStatus.ACTIVITY.getLine().getKey());
            if (name != null) {
              WHERE(OrderMeeting.T.NAME, " like  '%" + name + "%'");
            }
            if (onstate != null) {
              WHERE(OrderMeeting.T.STATUS, " =?", onstate);
            }

            WHERE(OrderMeeting.T.SUPPLIERID, " <> ?", getSupplier);
            AND()
                .WHERE(OrderMeeting.T.STATUS, " <>?", OrderMeetingStatus.DELETE.getLine().getKey());
            AND();
            WHERE(
                OrderMeetingAudit.T.STATUS,
                " not in("
                    + OrderMeetingAuditStatus.ACTIVITY.getLine().getKey()
                    + ","
                    + OrderMeetingAuditStatus.DELETE.getLine().getKey()
                    + ")");
            or().WHERE(
                    OrderMeetingAudit.T.PKEY.getFld().getTb().getCode()
                        + "."
                        + OrderMeetingAudit.T.STATUS
                        + " is null ");
            AND().WHERE(OrderMeetingAudit.T.SUPPLIERID, " = ?", getSupplier);
            or().WHERE(OrderMeetingAudit.T.SUPPLIERID, " is null");
            ORDER_BY(OrderMeeting.T.UPDATED_TIME, "desc");
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<OdrMeetingOtherlistView> list =
        Query.sql(sql).limit(start, limit).queryMaps().stream()
            .map(
                o -> {
                  OdrMeetingOtherlistView oml = new OdrMeetingOtherlistView();
                  oml.setId((Integer) o.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()));
                  oml.setImages((String) o.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
                  oml.setName((String) o.get(OrderMeeting.T.NAME.getFld().getCodeSqlField()));
                  if (o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()) != null
                      && !o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField())
                          .equals("")) {
                    oml.setExhibition(
                        (String)
                            o.get(OrderMeeting.T.CUSTOM_EXHIBITION.getFld().getCodeSqlField()));
                  } else {
                    oml.setExhibition((String) o.get("OMENAME"));
                  }
                  // 判断当前供应商没有审核过的订购会为负数
                  if (o.get("OMASTATUS") != null) {
                    oml.setApplyodr(Integer.parseInt(String.valueOf(o.get("OMASTATUS"))));
                  } else {
                    oml.setApplyodr(-1);
                  }
                  UsrSupplier usr =
                      BeanBase.load(
                          UsrSupplier.class,
                          (Integer) o.get(OrderMeeting.T.SUPPLIERID.getFld().getCodeSqlField()));
                  oml.setSuppliername(usr.getName());
                  PltCountry pc =
                      BeanBase.load(
                          PltCountry.class,
                          (Integer) o.get(OrderMeeting.T.COUNTRY.getFld().getCodeSqlField()));
                  oml.setCountry(pc.getName());
                  oml.setEndtime((Date) o.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField()));
                  oml.setStarttime(
                      (Date) o.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField()));
                  oml.setState(
                      Integer.parseInt(
                          String.valueOf(o.get(OrderMeeting.T.STATUS.getFld().getCodeSqlField()))));
                  return oml;
                })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  /**
   * @Description: 发起订购会列表页面 批量删除功能 逻辑删除
   *
   * @date 2018/11/14 19:48
   * @anthor wilson zhang
   */
  public void batchdelete(String pkeys) {
    Bean.executeUpdate(
        "update "
            + OrderMeeting.TB.getCodeSqlTb()
            + " set "
            + OrderMeeting.T.STATUS.getFld().getCodeSqlField()
            + "= "
            + OrderMeetingStatus.DELETE.getLine().getKey()
            + " where "
            + OrderMeeting.T.PKEY.getFld().getCodeSqlField()
            + " in("
            + pkeys
            + ")");
  }

  /**
   * @Description: 参加购会列表页面 批量删除功能 逻辑删除
   *
   * @date 2018/11/16 10:06
   * @anthor wilson zhang
   */
  public void joindelete(String pkeys) {
    Bean.executeUpdate(
        "update "
            + OrderMeetingAudit.TB.getCodeSqlTb()
            + " set "
            + OrderMeetingAudit.T.STATUS.getFld().getCodeSqlField()
            + "= "
            + OrderMeetingAuditStatus.DELETE.getLine().getKey()
            + " where "
            + OrderMeetingAudit.T.PKEY.getFld().getCodeSqlField()
            + " in("
            + pkeys
            + ")");
  }

  /**
   * @Description: 获取该订购会所有销售信息
   *
   * @date 2018/11/16 9:46
   * @author lijie@shoestp.cn
   */
  public Page getMeetingAllSaleInfo(int start, int limit, int odrMeeting) {
    SQL sql = new SQL();
    sql.SELECT(
            OrderMeetingOrder.T.PKEY,
            OdrOrder.T.ORDER_NUM,
            OdrOrder.T.PRICE_TOTAL,
            OrderMeetingProduct.T.STATUS)
        .SELECT(PdtProduct.T.PKEY, "pdtId")
        .SELECT(PdtProduct.T.PICTURE, "pdtImage")
        .SELECT(PdtProduct.T.NAME, "pdtName")
        .SELECT(
            "sum("
                + OdrOrderLine.T.QTY.getFld().getTb().getCode()
                + "."
                + OdrOrderLine.T.QTY.getFld().getCodeSqlField()
                + ") as qty")
        .SELECT(OrderMeetingProduct.T.NEWPRICE, "newPrice")
        .SELECT(PdtProduct.T.SUPPLIER, "pdtSup")
        .SELECT(OrderMeeting.T.SUPPLIERID, "OmtSup")
        .FROM(OrderMeetingOrder.class)
        .LEFT_JOIN(OdrOrder.class, OdrOrder.T.PKEY, OrderMeetingOrder.T.ORDERID) // 订单表
        .LEFT_JOIN(OdrOrderLine.class, OdrOrder.T.PKEY, OdrOrderLine.T.MAIN) // 订单详细表
        .LEFT_JOIN(PdtSpec.class, OdrOrderLine.T.SPEC, PdtSpec.T.PKEY) // 规格表
        .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PdtSpec.T.PRODUCT) // 产品表
        .LEFT_JOIN(
            OrderMeetingProduct.class, OrderMeetingProduct.T.PRODUCTID, PdtProduct.T.PKEY) // 活动产品表
        .LEFT_JOIN(
            OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingOrder.T.ORDERMEETINGID) // 活动产品表
        .LEFT_JOIN(PdtColor.class, PdtColor.T.PKEY, PdtSpec.T.COLOR) // 颜色
        .WHERE(OdrOrder.T.TYPE, "=?", Odr.OdrType.STATEONE) // 订单类型是  联合采购订单
        .WHERE(OrderMeetingOrder.T.ORDERMEETINGID, "=?", odrMeeting)
        .LIMIT(start, limit)
        .GROUP_BY(PdtProduct.T.PKEY);
    List<Map<String, Object>> result = Query.sql(sql).queryMaps();
    return new Page(result, start, limit, Query.sql(sql).queryMaps().size());
  }

  public List<Map<String, Object>> getMeetingSpecSaleInfo(int odrMeeting, int pdtId) {
    SQL sql = new SQL();
    SQL chlid = new SQL();
    chlid
        .SELECT("count(1)")
        .FROM(OrderMeetingOrder.class)
        .LEFT_JOIN(OdrOrder.class, OdrOrder.T.PKEY, OrderMeetingOrder.T.ORDERID) // 订单表
        .LEFT_JOIN(OdrOrderLine.class, OdrOrder.T.PKEY, OdrOrderLine.T.MAIN) // 订单详细表
        .LEFT_JOIN(PdtSpec.class, OdrOrderLine.T.SPEC, PdtSpec.T.PKEY) // 规格表
        .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PdtSpec.T.PRODUCT) // 产品表
        .LEFT_JOIN(
            OrderMeetingProduct.class, OrderMeetingProduct.T.PRODUCTID, PdtProduct.T.PKEY) // 活动产品表
        .LEFT_JOIN(
            OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingOrder.T.ORDERMEETINGID) // 活动产品表
        .WHERE(OdrOrder.T.TYPE, "=?", Odr.OdrType.STATEONE) // 订单类型是  联合采购订单
        .WHERE(OrderMeetingOrder.T.ORDERMEETINGID, "=?", odrMeeting)
        .WHERE(" PdtSpec.pkey = id")
        .GROUP_BY(PdtSpec.T.PKEY);
    sql.SELECT(PdtSpec.T.PKEY, "id")
        .SELECT(PdtSpec.T.PICS, "pic")
        .SELECT(PdtSpec.T.KEY_NAME, "specName")
        .SELECT(OrderMeetingProduct.T.NEWPRICE)
        .SELECT("sum(OdrOrderLine.qty) as qty")
        .SELECT(OdrOrderLine.T.SUBTOTAL)
        .SELECT(chlid, "overall")
        .FROM(OrderMeetingOrder.class)
        .LEFT_JOIN(OdrOrder.class, OdrOrder.T.PKEY, OrderMeetingOrder.T.ORDERID) // 订单表
        .LEFT_JOIN(OdrOrderLine.class, OdrOrder.T.PKEY, OdrOrderLine.T.MAIN) // 订单详细表
        .LEFT_JOIN(PdtSpec.class, OdrOrderLine.T.SPEC, PdtSpec.T.PKEY) // 规格表
        .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, PdtSpec.T.PRODUCT) // 产品表
        .LEFT_JOIN(
            OrderMeetingProduct.class, OrderMeetingProduct.T.PRODUCTID, PdtProduct.T.PKEY) // 活动产品表
        .LEFT_JOIN(
            OrderMeeting.class, OrderMeeting.T.PKEY, OrderMeetingOrder.T.ORDERMEETINGID) // 活动产品表
        .WHERE(OdrOrder.T.TYPE, "=?", Odr.OdrType.STATEONE) // 订单类型是  联合采购订单
        .WHERE(OrderMeetingOrder.T.ORDERMEETINGID, "=?", odrMeeting)
        .WHERE(PdtProduct.T.PKEY, "=?", pdtId)
        .GROUP_BY(PdtSpec.T.PKEY);

    return Query.sql(sql).queryMaps();
  }

  /**
   * @Description: 订购会列表 修改状态
   *
   * @date 2018/11/14 20:15
   * @anthor wilson zhang
   */
  public static class Meettingupdstate extends IduUpd<Meettingupdstate, OrderMeeting> {
    @Override
    public void before() {

      OrderMeeting dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), OrderMeeting.T.STATUS);
      setB(dbBean);
      super.before();
    }
  }

  /**
   * @Description: 订购会 修改地址
   *
   * @date 2018/11/14 20:15
   * @anthor wilson zhang
   */
  public static class updaddress extends IduUpd<updaddress, OrderMeeting> {
    @Override
    public void before() {
      OrderMeeting dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(
          dbBean,
          getB(),
          OrderMeeting.T.MAILAFULLDDRESS,
          OrderMeeting.T.MAILTEL,
          OrderMeeting.T.POSTCODE,
          OrderMeeting.T.MAILNAME);
      setB(dbBean);
      super.before();
    }
  }

  /**
   * @Description: 商家发布订购会
   *
   * @date 2018/11/19 20:09
   * @anthor wilson zhang
   */
  public static class insertomt extends IduIns<insertomt, OrderMeeting> {
    @Override
    public void before() {
      getB().setUpdatedTime(Env.getTranBeginTime());
      getB().setStatus(OrderMeetingStatus.TOBEGIN.getLine().getKey());
      super.before();
    }
  }

  /**
   * @Description: 获取订购会信息
   *
   * @anthor zjl
   */
  public OrderInformationView getorderInformation(Integer id) {
    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeeting.T.NAME, "orderingTitle")
                .SELECT(OrderMeetingExhibition.T.NAME, "exhibition")
                .SELECT(PltCountry.T.NAME, "country")
                .SELECT(
                    OrderMeeting.T.PKEY,
                    OrderMeeting.T.LOGO,
                    OrderMeeting.T.START_TIME,
                    OrderMeeting.T.END_TIME,
                    OrderMeeting.T.MAILADDRESS,
                    OrderMeeting.T.MAILAFULLDDRESS,
                    OrderMeeting.T.MAILNAME,
                    OrderMeeting.T.MAILTEL,
                    OrderMeeting.T.POSTCODE)
                .FROM(OrderMeeting.class)
                .LEFT_JOIN(
                    OrderMeetingExhibition.class,
                    OrderMeetingExhibition.T.PKEY,
                    OrderMeeting.T.EXHIBITION)
                .LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, OrderMeeting.T.COUNTRY)
                .WHERE(OrderMeeting.T.PKEY, "=?", id);
            //                        LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY,
            // OrderMeeting.T.SUPPLIERID).

          }
        };
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Map<String, Object> map = Query.sql(sql).queryMap();
    OrderInformationView view = new OrderInformationView();
    view.setId(
        Integer.valueOf(String.valueOf(map.get(OrderMeeting.T.PKEY.getFld().getCodeSqlField()))));
    view.setOrderingTitle((String) map.get("orderingTitle"));
    view.setExhibition((String) map.get("exhibition"));
    view.setCountry((String) map.get("country"));
    view.setCoverImage((String) map.get(OrderMeeting.T.LOGO.getFld().getCodeSqlField()));
    view.setOrderStartTime(
        formatter.format(map.get(OrderMeeting.T.START_TIME.getFld().getCodeSqlField())));
    view.setOrderEndTime(
        formatter.format(map.get(OrderMeeting.T.END_TIME.getFld().getCodeSqlField())));
    view.setAddress((String) map.get(OrderMeeting.T.MAILAFULLDDRESS.getFld().getCodeSqlField()));
    view.setReceiver((String) map.get(OrderMeeting.T.MAILNAME.getFld().getCodeSqlField()));
    view.setContactNumber((String) map.get(OrderMeeting.T.MAILTEL.getFld().getCodeSqlField()));
    view.setZip((String) map.get(OrderMeeting.T.POSTCODE.getFld().getCodeSqlField()));
    return view;
  }

  public OmtDetailsView getDetails(Integer id) {
    SQL sql = new SQL();
    sql.SELECT(OrderMeeting.T.PKEY, "pkey"); // 订购会id
    sql.SELECT(OrderMeeting.T.NAME, "title"); // 订购会标题
    sql.SELECT(OrderMeetingExhibition.T.NAME, "exhibition"); // 展厅
    sql.SELECT(PltCountry.T.NAME, "country"); // 所在国家
    sql.SELECT(OrderMeeting.T.LOGO, "logo"); // 封面图片
    sql.SELECT(OrderMeeting.T.START_TIME, "startTime"); // 开始时间
    sql.SELECT(OrderMeeting.T.END_TIME, "endTime"); // 结束时间
    sql.SELECT(OrderMeeting.T.MAILADDRESS, "address"); // 地址
    sql.SELECT(OrderMeeting.T.MAILNAME, "receiver"); // 收货人
    sql.SELECT(OrderMeeting.T.MAILTEL, "contactNumber"); // 联系电话
    sql.SELECT(OrderMeeting.T.POSTCODE, "zipCode"); // 邮编
    sql.SELECT(OrderMeetingAuditRelease.T.STATUS, "auditResults"); // 审核状态
    sql.SELECT(OrderMeetingAuditRelease.T.MESSAGE, "auditNote"); // 审核备注
    sql.FROM(OrderMeeting.class);
    sql.LEFT_JOIN(
        OrderMeetingExhibition.class, OrderMeetingExhibition.T.PKEY, OrderMeeting.T.EXHIBITION);
    sql.LEFT_JOIN(PltCountry.class, PltCountry.T.PKEY, OrderMeeting.T.COUNTRY);
    sql.LEFT_JOIN(
        OrderMeetingAuditRelease.class, OrderMeetingAuditRelease.T.ODRMEETING, OrderMeeting.T.PKEY);
    sql.WHERE(OrderMeeting.T.PKEY, "=?", id);
    Map<String, Object> map = Query.sql(sql).queryMap();
    OmtDetailsView view = new OmtDetailsView();
    view.setPkey(Integer.valueOf(String.valueOf(map.get("pkey")))); // 订购会id
    view.setTiltle(String.valueOf(map.get("title"))); // 订购会标题
    view.setExhibition(String.valueOf(map.get("exhibition"))); // 展厅
    view.setCountry(String.valueOf(map.get("country"))); // 所在国家
    view.setLogo(String.valueOf(map.get("logo"))); // 封面图片
    view.setStartTime((Date) map.get("startTime"));
    view.setEndTime((Date) map.get("endTime"));
    view.setAddress(String.valueOf(map.get("address"))); // 地址
    view.setReceiver(String.valueOf(map.get("receiver"))); // 收货人
    view.setContactNumber(String.valueOf(map.get("contactNumber"))); // 联系电话
    view.setZipCode(String.valueOf(map.get("zipCode"))); // 邮编
    view.setAuditResults(Byte.valueOf(String.valueOf(map.get("auditResults")))); // 审核状态
    view.setAuditNote(String.valueOf(map.get("auditNote"))); // 审核备注
    return view;
  }

  public void updStatus(Integer id, Integer status, String remarks) {
    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeetingAuditRelease.class)
                .FROM(OrderMeetingAuditRelease.class)
                .WHERE(OrderMeetingAuditRelease.T.ODRMEETING, "=?", id);
          }
        };
    OrderMeetingAuditRelease orderMeetingAuditRelease =
        Query.sql(sql).query(OrderMeetingAuditRelease.class);
    orderMeetingAuditRelease.setStatus(status.byteValue());
    orderMeetingAuditRelease.setMessage(remarks);
    orderMeetingAuditRelease.upd();
  }
}
