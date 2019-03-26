package com.xinlianshiye.shoestp.plat.dao.pm.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.xinlianshiye.shoestp.plat.dao.pm.IPMMessageDao;
import com.xinlianshiye.shoestp.plat.view.pm.MessageView;

import irille.Entity.pm.PM.ORCVRType;
import irille.Entity.pm.PMMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.GetValue;
import irille.view.Page;

public class PMMessageDaoImp implements IPMMessageDao {

  @Override
  public Page<MessageView> list(MessageView view, Integer start, Integer limit) {
    SQL sql = new SQL();
    view = view == null ? new MessageView() : view;
    sql.SELECT(PMMessage.class)
        .FROM(PMMessage.class)
        .WHERE(null != view.getStartDate(), PMMessage.T.SEND_TIME, " >= ? ", view.getStartDate())
        .WHERE(null != view.getEndDate(), PMMessage.T.SEND_TIME, " <=? ", view.getEndDate())
        .WHERE(null != view.getMsgTypeId(), PMMessage.T.MSG_TYPE, " =? ", view.getMsgTypeId());
    sql.WHERE(null != view.getRcvrTypeId(), PMMessage.T.RCVR_TYPE, " =? ", view.getRcvrTypeId());
    Integer count = Query.sql(sql).queryCount();
    sql.ORDER_BY(PMMessage.T.SEND_TIME, " DESC ").LIMIT(start, limit);

    List<MessageView> items =
        Query.sql(sql).queryList(PMMessage.class).stream()
            .map(
                item -> {
                  MessageView model = new MessageView();
                  model.setPkey(item.getPkey());
                  model.setMsgType(item.gtType().getLine().getName());
                  model.setRcvrType(item.gtRcvrType().getLine().getName());
                  model.setTitle(item.getTitle());
                  model.setCount(item.getCount());
                  model.setSendTime(item.getSendTime());
                  return model;
                })
            .collect(Collectors.toList());
    return new Page<MessageView>(items, start / limit + 1, limit, count);
  }

  @Override
  public Page<MessageView> list(Integer rec, ORCVRType type, Integer start, Integer limit) {
    limit = limit == 0 ? 15 : limit;

    String sql =
        " SELECT "
            + " message.rcvr, "
            + " message.rcvr_type, "
            + " message.pkey, "
            + " message.content AS content,"
            + " message.send_time AS sendTime,"
            + " IF ( r.c > 0, 1, 0 ) as c"
            + " FROM "
            + " p_m_message message "
            + " LEFT JOIN ( "
            + " SELECT COUNT( * ) AS c,rea.read_message AS m,rea.read_time AS t "
            + " FROM p_m_read rea "
            + " WHERE rea.reader = "
            + rec
            + " GROUP BY rea.read_message "
            + " ) AS r "
            + " ON r.m = message.pkey "
            + " WHERE "
            + " ( message.rcvr = "
            + rec
            + " OR ( message.rcvr_type = "
            + type.getLine().getKey()
            + " AND message.rcvr = - 1 ) ) "
            + " AND ((r.c = 0 OR r.c is null) OR (r.c > 0 AND DATEDIFF(now(),r.t) < 15)) "
            + " ORDER BY "
            + "  r.c ASC,sendTime DESC";

    List<Map<String, Object>> map = Query.sql(sql).queryMaps();
    Integer count = map.size(); // 总数

    Integer countNoRead = 0;

    for (Map<String, Object> k : map) {
      Integer r = GetValue.get(k, "c", Integer.class, 0);
      if (r.equals(0)) {
        countNoRead++;
      }
    }
    sql += " limit " + start + "," + limit;

    List<MessageView> items =
        Query.sql(sql).queryMaps().stream()
            .map(
                item -> {
                  MessageView model = new MessageView();
                  model.setPkey(GetValue.get(item, "pkey", Integer.class, null));
                  model.setContent(GetValue.get(item, "content", String.class, null));
                  Date time = GetValue.get(item, "sendTime", Date.class, null);
                  model.setTime(time == null ? null : time.getTime());
                  Integer r = GetValue.get(item, "c", Integer.class, 0);
                  model.setRead(r.intValue());
                  return model;
                })
            .collect(Collectors.toList());

    return new Page<MessageView>(items, start / limit + 1, limit, count, countNoRead);
  }
}
