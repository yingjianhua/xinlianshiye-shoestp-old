package irille.view.Manage.OdrMeeting.initiatedActivity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import irille.view.BaseView;
import lombok.Data;

@Data
public class OrderInformationView implements BaseView {
    private Integer Id;
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String orderingTitle;//订购会标题
    private String exhibition;//展厅
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String country;//所在国家
    private String coverImage;//封面图片
    private String orderStartTime;//订购会开始时间
    private String orderEndTime;//订购会结束时间
    private String address;//地址
    private String receiver;//收货人
    private String contactNumber;//联系电话
    private String zip;//邮编
    private String msg;//消息
    private Integer status;  //订购会状态
}
