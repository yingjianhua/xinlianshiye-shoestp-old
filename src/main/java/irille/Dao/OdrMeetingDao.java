package irille.Dao;

import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeeting.T;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduUpd;
import irille.view.Manage.OdrMeeting.OdrMeetingLaunchlistView;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37
 */
public class OdrMeetingDao {

  /**
   * @Description: 检查是否有条件参加订购会
   * @date 2018/11/15 11:21
   * @author lijie@shoestp.cn
   */
  public boolean isJoinOdrMeeting(int odrMeetIngId, int supplier) {
    //参加的人不能是发起人,且不再审核列状态中  订购会已通过审核 状态为即将开始
    BeanQuery query = new BeanQuery();
    query
        .FROM(OrderMeetingAudit.class)
        .LEFT_JOIN(OrderMeeting.class, T.PKEY, OrderMeetingAudit.T.OrderMeeting)
        .WHERE(T.SUPPLIERID, "<> ?", supplier)
        .WHERE(OrderMeeting.T.STATUS, "=?", OrderMeetingStatus.TOBEGIN)
        .WHERE(OrderMeetingAudit.T.SUPPLIERID, "=?", supplier)
//        .WHERE(OrderMeetingAudit.T.STATUS, "=?", OrderMeetingAuditStatus.PASS)
        .WHERE(OrderMeetingAudit.T.OrderMeeting, "=?", odrMeetIngId)
    ;

    return query.queryCount() > 0;
  }


  /**
   * @Description: 我发起订购会列表
   * @date 2018/11/14 18:30
   * @anthor wilson zhang
   */
  public List<OdrMeetingLaunchlistView> Launchlist(Integer start, Integer limit, String name,
      Integer onstate, Integer getSupplier) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql = new SQL() {{
      SELECT(OrderMeeting.class);
      FROM(OrderMeeting.class);
      if (name.equals("")) {
        WHERE(OrderMeeting.T.NAME, " like %?%", name);
      }
      if (onstate != null) {
        WHERE(OrderMeeting.T.STATUS, " =?", onstate);
      }
      if (getSupplier != null) {
        WHERE(OrderMeeting.T.SUPPLIERID, " =?", getSupplier);
      }
    }};
    List<OdrMeetingLaunchlistView> list = Query.sql(sql).limit(start, limit)
        .queryList(OrderMeeting.class).stream().map(o -> {
          OdrMeetingLaunchlistView oml = new OdrMeetingLaunchlistView();
          oml.setId(o.getPkey());
          oml.setCountry(o.gtCountry().getName());
          oml.setEndtime(o.getEndTime());
          oml.setStarttime(o.getStartTime());
          oml.setImages(o.getLogo());
          oml.setState(o.getStatus());
          return oml;
        }).collect(Collectors.toList());
    return list;
  }

  /**
   * @Description: 参与订购会列表页面
   * @date 2018/11/14 20:32
   * @anthor wilson zhang
   */
  public List<OdrMeetingLaunchlistView> participatelist(Integer start, Integer limit, String name,
      Integer onstate, Integer getSupplier) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql = new SQL() {{
      SELECT(OrderMeeting.class);
      FROM(OrderMeeting.class);
      if (name.equals("")) {
        WHERE(OrderMeeting.T.NAME, " like %?%", name);
      }
      if (onstate != null) {
        WHERE(OrderMeeting.T.STATUS, " =?", onstate);
      }
      if (getSupplier != null) {
        WHERE(OrderMeeting.T.SUPPLIERID, " =?", getSupplier);
      }
    }};
    List<OdrMeetingLaunchlistView> list = Query.sql(sql).limit(start, limit)
        .queryList(OrderMeeting.class).stream().map(o -> {
          OdrMeetingLaunchlistView oml = new OdrMeetingLaunchlistView();
          oml.setId(o.getPkey());
          oml.setCountry(o.gtCountry().getName());
          oml.setEndtime(o.getEndTime());
          oml.setStarttime(o.getStartTime());
          oml.setImages(o.getLogo());
          oml.setState(o.getStatus());
          return oml;
        }).collect(Collectors.toList());
    return list;
  }


  /**
   * @Description: 订购会列表页面  批量删除功能  逻辑删除
   * @date 2018/11/14 19:48
   * @anthor wilson zhang
   */
  public void batchdelete(String pkeys) {
    Bean.executeUpdate("update set " + OrderMeeting.T.STATUS.getFld().getCodeSqlField()
        + "= " + OrderMeetingStatus.END.getLine().getKey()
        + "  from " + OrderMeeting.TB.getCodeSqlTb()
        + " where " + OrderMeeting.T.PKEY.getFld().getCodeSqlField()
        + " in(" + pkeys + ")");
  }

  /**
   * @Description: 订购会列表  修改状态
   * @date 2018/11/14 20:15
   * @anthor wilson zhang
   */
  public static class Meettingupdstate extends IduUpd<Meettingupdstate, OrderMeeting> {

    @Override
    public void run() {
      super.run();
      OrderMeeting dbBean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbBean, getB(), OrderMeeting.T.STATUS);
      setB(dbBean);
    }

  }

}
