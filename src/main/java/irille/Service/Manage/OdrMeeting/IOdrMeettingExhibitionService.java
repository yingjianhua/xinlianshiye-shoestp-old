package irille.Service.Manage.OdrMeeting;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingExhibitionServiceImp;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingManageServiceImp;
import irille.Service.OdrMeeting.Imp.OdrMeetingServiceImp;
import irille.view.Manage.OdrMeeting.OrderMeetingExhibitionView;

import java.util.List;
/**
 * 商家端 展廳接口
 */
@ImplementedBy(OdrMeetingExhibitionServiceImp.class)
public interface IOdrMeettingExhibitionService {
    List<OrderMeetingExhibitionView> listExhibition();
}
