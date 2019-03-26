package irille.Dao.SVS.impl;

import java.util.List;
import java.util.Map;

import irille.Dao.SVS.SVSInfoDao;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.SVS.SVSInfo;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrSupplier;
import irille.view.Page;

public class SVSInfoDaoImpl implements SVSInfoDao {

  /** 更新或者插入SVS对象 */
  @Override
  public SVSInfo save(SVSInfo bean) {
    SVSInfo inf = new SVSInfo();
    if (null == bean.getPkey()) {
      inf = bean.ins();
    } else {
      inf = bean.upd();
    }
    return inf;
  }

  /**
   * 判断当前用户是否申请认证过
   *
   * @author GS
   */
  @Override
  public SVSInfo findSVSInfoBySupplier(Integer supplier) {
    SQL sql = new SQL();
    sql.SELECT(SVSInfo.class);
    sql.FROM(SVSInfo.class);
    sql.WHERE(SVSInfo.T.SUPPLIER, " =?", supplier);
    return Query.sql(sql).query(SVSInfo.class);
  }

  @Override
  public List<Map<String, Object>> findSVSinfo(
      Integer start, Integer limit, String shopName, Byte status, Byte shopStatus, Byte grade) {
    SQL sql =
        new SQL() {
          {
            SELECT(SVSInfo.T.PKEY, "id")
                .SELECT(UsrSupplier.T.NAME, "shopName")
                .SELECT(UsrSupplier.T.PKEY,"supplierId")
                .SELECT(UsrSupplier.T.CONTACTS, "name")
                .SELECT(SVSInfo.T.APPLICATION_TIME, "applicationTime")
                .SELECT(SVSInfo.T.STATUS, "status")
                .SELECT(SVSInfo.T.GRADE, "grade")
                .SELECT(UsrSupplier.T.STATUS, "shopStatus")
                .FROM(SVSInfo.class)
                .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, SVSInfo.T.SUPPLIER)
                .WHERE(shopName != null, UsrSupplier.T.NAME, "=?", shopName)
                .WHERE(status != null, SVSInfo.T.STATUS, "=?", status)
                .WHERE(shopStatus != null, UsrSupplier.T.STATUS, "=?", shopStatus)
                .WHERE(grade != null, SVSInfo.T.GRADE, "=?", grade)
                .ORDER_BY(SVSInfo.T.APPLICATION_TIME, "desc")
                .LIMIT(start, limit);
          }
        };
    return Query.sql(sql).queryMaps();
  }

  @Override
  public int count() {
    SQL sql =
        new SQL() {
          {
            SELECT(SVSInfo.T.PKEY, "id")
                .SELECT(UsrSupplier.T.NAME, "shopName")
                .SELECT(UsrSupplier.T.CONTACTS, "name")
                .SELECT(SVSInfo.T.APPLICATION_TIME, "applicationTime")
                .SELECT(SVSInfo.T.STATUS, "status")
                .SELECT(SVSInfo.T.GRADE, "grade")
                .SELECT(UsrSupplier.T.STATUS, "shopStatus")
                .FROM(SVSInfo.class)
                .LEFT_JOIN(UsrSupplier.class, UsrSupplier.T.PKEY, SVSInfo.T.SUPPLIER);
          }
        };
    return Query.sql(sql).queryCount();
  }
}
