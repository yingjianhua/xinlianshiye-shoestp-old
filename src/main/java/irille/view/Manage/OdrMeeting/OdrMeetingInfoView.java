package irille.view.Manage.OdrMeeting;

import java.util.Date;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import lombok.Data;

/** 订购会信息 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:42 */
@Data
public class OdrMeetingInfoView {

  @SetBean(OriginalField = "pkey")
  private int id;

  private String name;
  private int exhibitionId;
  private String customExhibition;
  private int country;
  private String status;
  private Integer statusId;
  private String logo;
  private Date start_time;
  private Date end_time;
  private String mailAddress;
  private String mailFulladdress;
  private String postcode;
  private String mailname;
  private String mailtel;
}
