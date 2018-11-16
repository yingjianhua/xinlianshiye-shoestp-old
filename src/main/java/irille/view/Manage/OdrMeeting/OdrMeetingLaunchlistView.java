package irille.view.Manage.OdrMeeting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 订购会列表（发起）
 */
@Data
public class OdrMeetingLaunchlistView {
    private int id; //订购会PKYS
    private String images;//缩略图
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String name;//订购会名称
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String country;//国家
    private int state;//状态
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date starttime;//开始时间
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date endtime;//结束时间
}
