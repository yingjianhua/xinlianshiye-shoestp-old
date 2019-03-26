package irille.platform.omt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import irille.Dao.OdrMeetingDao;
import irille.Dao.OrderMeetingAuditReleaseDao;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.action.MgtAction;
import irille.platform.omt.View.StatusView;
import lombok.Getter;
import lombok.Setter;

public class OrderMeetingAction extends MgtAction<OrderMeeting> {
  @Getter @Setter private String omtName;
  @Getter @Setter private Integer status;
  @Getter @Setter private Integer id;
  @Getter @Setter private String remarks;

  @Override
  public Class beanClazz() {
    return null;
  }

  @Inject private OdrMeetingDao odrMeetingDao;
  @Inject private OrderMeetingAuditReleaseDao orderMeetingAuditReleaseDao;

  /** @Description: (平台)获取审核状态 *@date 2019/1/8 15:44 *@anthor zjl */
  public void getStatus() throws IOException {
    List<StatusView> list = new ArrayList<>();
    for (OrderMeetingAuditStatus value : OrderMeetingAuditStatus.values()) {
      StatusView view = new StatusView();
      view.setId(value.getLine().getKey());
      view.setName(value.getLine().getName());
      list.add(view);
    }
    write(list);
  }

  /** @Description: (平台)获取发布订购会审核列表 *@date 2019/1/3 10:13 *@anthor zjl */
  public void getApplications() throws IOException {
    write(orderMeetingAuditReleaseDao.getApplications(getStart(), getLimit(), omtName, status));
  }

  /** @Description: (平台)获取单个订购会详情 *@date 2019/1/3 10:58 *@anthor zjl */
  public void getDetails() throws IOException {
    write(odrMeetingDao.getDetails(id));
  }

  /** @Description: (平台)修改状态+备注 *@date 2019/1/9 14:33 *@anthor zjl */
  public void updStatus() throws IOException {
    odrMeetingDao.updStatus(id, status, remarks);
    write();
  }
}
