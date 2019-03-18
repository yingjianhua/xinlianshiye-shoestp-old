package irille.Dao;

import java.util.List;
import java.util.stream.Collectors;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingProduct;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.shop.usr.UsrSupplier;
import irille.view.Manage.OdrMeeting.OdrAuditsupplierView;
import irille.view.Manage.OdrMeeting.OdrMeetingAuditLogisticsView;
import irille.view.Page;

public class OrderMeetingAuditDao {
  private static final Log LOG = new Log(OrderMeetingAuditDao.class);

  /**
   * @Description: 商家申请 参加订购会
   *
   * @date 2018/11/19 20:09
   * @anthor wilson zhang
   */
  public static class insertjoinOdr extends IduIns<insertjoinOdr, OrderMeetingAudit> {
    @Override
    public void before() {
      getB().setCreatedTime(Env.getTranBeginTime());
      getB().setStatus(OrderMeetingAuditStatus.PENDING.getLine().getKey());
      super.before();
    }
  }

  /**
   * @Description: 逻辑删除 参加订购会合作商
   *
   * @date 2018/11/22 11:14
   * @anthor wilson zhang
   */
  public static class deletejoinOdr extends IduUpd<deletejoinOdr, OrderMeetingAudit> {
    @Override
    public void before() {
      OrderMeetingAudit dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), OrderMeetingAudit.T.STATUS);
      setB(dbBean);
      OrderMeetingProduct omp = new OrderMeetingProduct();
      omp.setPkey(getB().getPkey());
      omp.setSupplierid(getB().getSupplierid());
      OdrMeetingProductDao.deletejoinOdr(omp);
      super.before();
    }
  }

  /**
   * @Description: 显示合作商列表
   *
   * @date 2018/11/22 13:55
   * @anthor wilson zhang
   */
  public Page cooperationsupplier(
      Integer start, Integer limit, Integer status, String name, Integer omtid) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sum = new SQL();
    StringBuilder sb = new StringBuilder();
    sb.append(
        "SUM(1)\n"
            + "\tFROM order_meeting_product orderMeetingProduct\n"
            + "\tWHERE orderMeetingProduct.STATUS <> 4\n"
            + "\tAND orderMeetingProduct.supplierid <> (SELECT orderMeeting.supplierid AS supplier\n"
            + "FROM order_meeting orderMeeting\n"
            + "WHERE orderMeeting.pkey = "
            + omtid
            + ")\n"
            + "\tGROUP BY orderMeetingProduct.supplierid");
    sum.SELECT(sb.toString());

    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeetingAudit.T.PKEY, "omatid")
                .SELECT(OrderMeetingAudit.T.STATUS, OrderMeetingAudit.T.SAMPLEADDRESS)
                .SELECT(UsrSupplier.T.PKEY, "supplierid")
                .SELECT(
                    UsrSupplier.T.NAME,
                    UsrSupplier.T.CONTACTS,
                    UsrSupplier.T.EMAIL,
                    UsrSupplier.T.IS_AUTH,
                    UsrSupplier.T.CERT_PHOTO);
            SELECT(sum, "sum");
            FROM(OrderMeetingAudit.class);
            LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, OrderMeetingAudit.T.SUPPLIERID);
            if (name != null) {
              WHERE(UsrSupplier.T.NAME, " like  '%" + name + "%'");
            }
            if (status != null) {
              WHERE(OrderMeetingAudit.T.STATUS, " =? ", status);
            }
            WHERE(OrderMeetingAudit.T.ODRMEETING, "= ? ", omtid);
            WHERE(
                OrderMeetingAudit.T.STATUS,
                " <>?",
                OrderMeetingAuditStatus.DELETE.getLine().getKey());
          }
        };

    Integer count = Query.sql(sql).queryMaps().size();
    Query.sql(sql).limit(start, limit);
    List<OdrAuditsupplierView> oal =
        Query.sql(sql).queryMaps().stream()
            .map(
                o -> {
                  OdrAuditsupplierView omv = new OdrAuditsupplierView();
                  omv.setAuditId(Integer.valueOf(String.valueOf(o.get("omatid"))));
                  omv.setId((Integer) o.get("supplierid"));
                  omv.setCompanyname((String) o.get(UsrSupplier.T.NAME.getFld().getCodeSqlField()));
                  omv.setName((String) o.get(UsrSupplier.T.CONTACTS.getFld().getCodeSqlField()));
                  omv.setEmail((String) o.get(UsrSupplier.T.EMAIL.getFld().getCodeSqlField()));
                  omv.setIsauth(
                      Integer.valueOf(
                          String.valueOf(o.get(UsrSupplier.T.IS_AUTH.getFld().getCodeSqlField()))));
                  if (o.get(UsrSupplier.T.CERT_PHOTO.getFld().getCodeSqlField()) != null) {
                    omv.setImage(
                        String.valueOf(
                            o.get(
                                UsrSupplier.T
                                    .CERT_PHOTO
                                    .getFld()
                                    .getCodeSqlField()
                                    .split(",")[0])));
                  }
                  omv.setStatus(
                      Integer.parseInt(
                          String.valueOf(
                              o.get(OrderMeetingAudit.T.STATUS.getFld().getCodeSqlField()))));
                  omv.setAddress(
                      (String) o.get(OrderMeetingAudit.T.SAMPLEADDRESS.getFld().getCodeSqlField()));
                  if (o.get("sum") != null) {
                    omv.setShopnum(Integer.valueOf(String.valueOf(o.get("sum"))));
                  } else {
                    omv.setShopnum(0);
                  }
                  return omv;
                })
            .collect(Collectors.toList());

    return new Page(oal, start, limit, count);
  }

  public static void updLoadSupStatus(Integer id, Integer status) {
    OrderMeetingAudit oma = BeanBase.load(OrderMeetingAudit.class, id);
    oma.setStatus(status.byteValue());
    oma.setCreatedTime(Env.getTranBeginTime());
    oma.upd();
  }

  public static void updAudit(
      Integer omtId, Integer supplierId, String orderNumber, String remarks) {
    SQL sql =
        new SQL() {
          {
            SELECT(OrderMeetingAudit.class)
                .FROM(OrderMeetingAudit.class)
                .WHERE(OrderMeetingAudit.T.ODRMEETING, "=?", omtId)
                .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplierId);
          }
        };
    OrderMeetingAudit oma = Query.sql(sql).query(OrderMeetingAudit.class);
    if (orderNumber != null) {
      oma.setSampleaddress(orderNumber);
    }
    if (remarks != null) {
      oma.setMessage(remarks);
    }
    oma.upd();
  }

  public static OdrMeetingAuditLogisticsView getLogistics(Integer omtId, Integer supplierId) {
    OdrMeetingAuditLogisticsView view = new OdrMeetingAuditLogisticsView();
    SQL mop =
        new SQL() {
          {
            SELECT(OrderMeeting.class)
                .FROM(OrderMeeting.class)
                .WHERE(OrderMeeting.T.SUPPLIERID, "=?", supplierId)
                .WHERE(OrderMeeting.T.PKEY, "=?", omtId);
          }
        };
    if (Query.sql(mop).query(OrderMeeting.class) == null) {
      SQL sql =
          new SQL() {
            {
              SELECT(OrderMeetingAudit.class)
                  .FROM(OrderMeetingAudit.class)
                  .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplierId)
                  .WHERE(OrderMeetingAudit.T.ODRMEETING, "=?", omtId);
            }
          };
      OrderMeetingAudit oma = Query.sql(sql).query(OrderMeetingAudit.class);
      view.setRemarks(oma.getMessage());
      view.setTrackingNumber(oma.getSampleaddress());
    }
    return view;
  }
}
