package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.OdrMeetingDao;
import irille.Entity.OdrerMeetings.Enums.OrdrerMettingAuditStatus;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.pub.idu.IduPage;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import javax.inject.Inject;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37
 */
public class OdrMeetingManageServiceImp implements IOdrMeetingManageService {

  @Inject
  private OdrMeetingDao odrMeetingDao;


  @Override
  public void insOdrMeeting(OdrMeetingInfoView getOdrMeetingInfoView) {

  }

  @Override
  public void removeOdrMeeting(int... id) {

  }

  @Override
  public OdrMeetingInfoView getMyOdrMeetingInfo(IduPage iduPage, OrdrerMettingAuditStatus status) {
    return null;
  }

  @Override
  public OdrMeetingInfoView getMyOdrMeetingList(IduPage iduPage, OrdrerMettingAuditStatus status) {
    return null;
  }

  @Override
  public OdrMeetingInfoView getMyJoinOdrMeetingList(IduPage iduPage,
      OrdrerMettingAuditStatus status) {
    return null;
  }

  @Override
  public OdrMeetingInfoView getMyOtherOdrMeetingList(IduPage iduPage,
      OrdrerMettingAuditStatus status) {
    return null;
  }




}
