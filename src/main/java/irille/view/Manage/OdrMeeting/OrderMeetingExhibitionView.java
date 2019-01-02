package irille.view.Manage.OdrMeeting;

import irille.view.BaseView;
import lombok.Data;

@Data
public class OrderMeetingExhibitionView  implements BaseView {
    private Integer id;
    private String address;
    private String name;
    private Integer countryid;
}
