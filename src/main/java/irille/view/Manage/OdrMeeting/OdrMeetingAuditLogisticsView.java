package irille.view.Manage.OdrMeeting;

import irille.view.BaseView;
import lombok.Data;

@Data
public class OdrMeetingAuditLogisticsView implements BaseView {
  private String logisticsCompany;
  private String trackingNumber;
  private String remarks;
}
