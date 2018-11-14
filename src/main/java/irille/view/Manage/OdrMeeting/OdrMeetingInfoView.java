package irille.view.Manage.OdrMeeting;

import java.util.Date;
import java.util.Map;
import lombok.Data;

/**
 * 订购会信息
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:42
 */
@Data
public class OdrMeetingInfoView {

  private int id;
  private String name;
  private int exhibitionId;
  private String custonExhibition;
  private String status;
  private String logo;
  private Date start_time;
  private Date end_time;
  private Map mailAddress;
  private String mailFulladdress;
  private String mail_name;
  private String mail_tel;


}
