import com.fasterxml.jackson.core.JsonProcessingException;
import irille.Dao.OdrMeetingDao;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingManageServiceImp;
import irille.core.prv.PrvRoleAct;
import irille.pub.svr.DbPool;
import irille.pub.svr.StartInitServlet;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import java.sql.SQLException;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/15 Time: 10:30
 */
public class OdrMeetingManageServiceImpTest {

  IOdrMeetingManageService iOdrMeetingManageService;

  @Before
  public void create() {
    StartInitServlet.initBeanLoad();
    PrvRoleAct.TB.getCode();
    iOdrMeetingManageService = new OdrMeetingManageServiceImp();
  }

  @Test
  public void insOdrMeeting() throws JsonProcessingException {
    OdrMeetingInfoView odrMeetingInfoView = new OdrMeetingInfoView();
    odrMeetingInfoView.setId(2);
    odrMeetingInfoView.setName("{\"en\":\"我喜欢你\"}");
    odrMeetingInfoView.setExhibitionId(1);
    odrMeetingInfoView.setCustomExhibition(null);
    odrMeetingInfoView.setCountry(1);
    odrMeetingInfoView.setStatus("Awdaw");
    odrMeetingInfoView.setLogo("Awdwa");
    odrMeetingInfoView.setStart_time(new Date());
    odrMeetingInfoView.setEnd_time(new Date());
    odrMeetingInfoView.setMailAddress("1212");
    odrMeetingInfoView.setMailFulladdress("awdwadw");
    odrMeetingInfoView.setMailname("12121");
    odrMeetingInfoView.setMailtel("!212 1212");
    iOdrMeetingManageService.insOdrMeeting(odrMeetingInfoView, 1);
    ;
//      iOdrMeetingManageService.insOdrMeeting(,1);
  }


  @Test
  public void isJoinOdrMeeting() {
    OdrMeetingDao dao = new OdrMeetingDao();
    dao.isJoinOdrMeeting(1, 1);
  }

  @After
  public void end() throws SQLException {
    DbPool.getInstance().getConn().commit();
  }
}
