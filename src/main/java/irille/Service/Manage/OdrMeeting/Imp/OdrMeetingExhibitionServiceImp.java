package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.OrderMeetingExhibitionDao;
import irille.Service.Manage.OdrMeeting.IOdrMeettingExhibitionService;
import irille.view.Manage.OdrMeeting.OrderMeetingExhibitionView;

import java.util.List;

public class OdrMeetingExhibitionServiceImp implements IOdrMeettingExhibitionService {
    @Override
    public List<OrderMeetingExhibitionView> listExhibition() {
        return OrderMeetingExhibitionDao.listomev();
    }
}
