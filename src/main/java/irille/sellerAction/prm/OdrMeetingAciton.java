package irille.sellerAction.prm;


import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.Service.Manage.OdrMeeting.IOdrMeetingProductManageService;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 14:01
 */
public class OdrMeetingAciton {

  @Getter
  @Setter
  private String data;

  @Inject
  private IOdrMeetingManageService odrMeetingManageService;
  @Inject
  private IOdrMeetingProductManageService odrMeetingProductManageService;

}
