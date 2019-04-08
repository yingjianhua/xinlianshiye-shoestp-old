package irille.Dao.RFQ.impl;

import java.util.Date;
import java.util.List;

import irille.Dao.RFQ.RFQConsultMessageDao;
import irille.Entity.RFQ.RFQConsultMessage;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.Entity.RFQ.Enums.RFQConsultRelationReadStatus;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;

public class RFQConsultMessageDaoImpl implements RFQConsultMessageDao {

  @Override
  public List<RFQConsultMessage> findAll(
      Integer start, Integer limit, Integer consultPkey, Integer supplierPkey) {
    if (start == null) start = 0;
    if (limit == null || limit == 0) limit = 10;
    BeanQuery<RFQConsultMessage> query =
        Query.SELECT(RFQConsultMessage.class)
            .LEFT_JOIN(
                RFQConsultRelation.class, RFQConsultMessage.T.RELATION, RFQConsultRelation.T.PKEY)
            .WHERE(RFQConsultRelation.T.CONSULT, "=?", consultPkey)
            .WHERE(RFQConsultRelation.T.SUPPLIER_ID, "=?", supplierPkey)
            .ORDER_BY(RFQConsultMessage.T.SEND_TIME, "desc");
    return query.limit(start, limit).queryList();
  }

  @Override
  public void save(RFQConsultMessage bean) {
    if (bean.getPkey() == null) {
      bean.ins();
    } else {
      bean.upd();
    }
  }

  @Override
  public Integer countPurchaseUnreadByRelation_PurchaseGroupByRelation(Integer purchasePkey) {
    return Query.sql(
            new SQL() {
              {
                SELECT("*");
                FROM(
                    new SQL() {
                      {
                        SELECT("1").FROM(RFQConsultRelation.class);
                        WHERE(RFQConsultRelation.T.PURCHASE_ID, "=?", purchasePkey);
                        WHERE(
                            RFQConsultRelation.T.READ_STATUS,
                            "=?",
                            RFQConsultRelationReadStatus.PURCHASE_UNREAD);
                        WHERE(RFQConsultRelation.T.IS_DELETED_PURCHASE, "=?", false);
                      }
                    },
                    "a");
              }
            })
        .queryCount();
  }

  @Override
  public RFQConsultMessage findByUuid(String uuid) {
    return Query.selectFrom(RFQConsultMessage.class)
        .WHERE(RFQConsultMessage.T.PRIVATE_PRODUCT_URL_MESSAGE_UUID, "=?", uuid)
        .query();
  }

  @Override
  public void updateValidDateAndContentByUuid(String uuid, Date validDate, String content) {
    Query.UPDATE(RFQConsultMessage.class)
        .SET(RFQConsultMessage.T.PRIVATE_PRODUCT_URL_MESSAGE_VALID_DATE, validDate)
        .SET(RFQConsultMessage.T.CONTENT, content)
        .WHERE(RFQConsultMessage.T.PRIVATE_PRODUCT_URL_MESSAGE_UUID, "=?", uuid)
        .executeUpdate();
  }
}
