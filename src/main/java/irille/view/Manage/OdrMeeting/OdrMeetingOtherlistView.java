package irille.view.Manage.OdrMeeting;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import irille.pub.i18n.I18NFieldSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 订购会列表（其他）
 */
@Data
public class OdrMeetingOtherlistView  {
    private int id;//pkeys
    private String images;//缩略图
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String name;//名称
    private String exhibition;//展厅
    @JsonSerialize(using= I18NFieldSerializer.class)
    private String country;//国家
    private String suppliername;//供应商
    private int state;//状态
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date starttime;//开始时间
    @JsonFormat(pattern="yyyy.MM.dd", timezone="GMT+8")
    private Date endtime;//结束时间
    private Integer applyodr;
}
