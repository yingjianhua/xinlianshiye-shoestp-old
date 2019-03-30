package irille.Dao.RFQ.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import irille.Dao.RFQ.RFQConsultGroupRelationDao;
import irille.Entity.RFQ.RFQConsultGroupRelation;
import irille.pub.bean.Query;
import irille.pub.util.GetValue;
import irille.view.Page;
import irille.view.RFQ.InquiryMessageView;

public class RFQConsultGroupRelationDaoImpl implements RFQConsultGroupRelationDao {

  @Override
  public void save(RFQConsultGroupRelation bean) {
    if (bean.getPkey() == null) {
      bean.ins();
    } else {
      bean.upd();
    }
  }

  @Override
  public RFQConsultGroupRelation findByConsult_PkeySupplier_Pkey(
      Integer consultPkey, Integer supplierPkey) {
    return Query.SELECT(RFQConsultGroupRelation.class)
        .WHERE(RFQConsultGroupRelation.T.CONSULT, "=?", consultPkey)
        .WHERE(RFQConsultGroupRelation.T.SUPPLIER, "=?", supplierPkey)
        .query();
  }

  @Override
  public Integer countByGroup_pkey(Integer groupId) {
    return Query.SELECT(RFQConsultGroupRelation.class)
        .WHERE(RFQConsultGroupRelation.T.CONSULT_GROUP, "=?", groupId)
        .queryCount();
  }

  @Override
  public void deleteByGroup_pkey(Integer groupPkey) {
    findAllByGroup_Pkey(groupPkey)
        .forEach(
            bean -> {
              bean.del();
            });
  }

  @Override
  public List<RFQConsultGroupRelation> findAllByGroup_Pkey(Integer groupPkey) {
    return Query.SELECT(RFQConsultGroupRelation.class)
        .WHERE(RFQConsultGroupRelation.T.CONSULT_GROUP, "=?", groupPkey)
        .queryList();
  }

  @Override
  public Page<InquiryMessageView> message(
      Integer rec, boolean supplier, Integer start, Integer limit) {
    limit = limit == 0 ? 15 : limit;
    String sql =
        " SELECT consult.pkey,consult.title,message.re,consult.create_time "
            + " FROM r_f_q_consult_relation relation "
            + " LEFT JOIN r_f_q_consult consult "
            + " ON consult.pkey = relation.consult "
            + " LEFT JOIN (SELECT message.relation as r,message.had_read as re "
            + "			FROM r_f_q_consult_message message "
            + "			WHERE message.p2s = 1 "
            + "			ORDER BY message.send_time DESC "
            + "			LIMIT 0,1) message "
            + " ON message.r = relation.pkey "
            + " WHERE relation.supplier_id = "
            + rec
            + " AND message.re = 0";

    List<Map<String, Object>> map = Query.sql(sql).queryMaps();
    Integer count = map.size(); // 总数

    Integer countNoRead = 0;
    for (Map<String, Object> k : map) {
      Byte r = GetValue.get(k, "re", Byte.class, (byte) 0);
      if (r.equals((byte) 0)) {
        countNoRead++;
      }
    }
    sql += " limit " + start + "," + limit;
    System.out.println(sql);
    List<InquiryMessageView> items =
        Query.sql(sql).queryMaps().stream()
            .map(
                item -> {
                  InquiryMessageView model = new InquiryMessageView();
                  model.setPkey(GetValue.get(item, "pkey", Integer.class, null));
                  model.setTitle(GetValue.get(item, "title", String.class, null));
                  Date time = GetValue.get(item, "create_time", Date.class, null);
                  model.setTime(time == null ? null : time);
                  Byte r = GetValue.get(item, "re", Byte.class, (byte) 0);
                  model.setRead(r.intValue());
                  return model;
                })
            .collect(Collectors.toList());

    return new Page<InquiryMessageView>(items, start / limit + 1, limit, count, countNoRead);
  }
}
