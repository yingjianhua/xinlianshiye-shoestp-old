package irille.Service.Manage.OdrMeeting;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingExhibitionServiceImp;
import irille.view.Manage.OdrMeeting.OrderMeetingExhibitionView;

/** 商家端 展廳接口 */
@ImplementedBy(OdrMeetingExhibitionServiceImp.class)
public interface IOdrMeettingExhibitionService {
  List<OrderMeetingExhibitionView> listExhibition();
}
