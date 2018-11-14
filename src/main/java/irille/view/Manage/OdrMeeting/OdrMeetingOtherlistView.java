package irille.view.Manage.OdrMeeting;

import lombok.Data;

import java.util.Date;

/**
 * 订购会列表（其他）
 */
@Data
public class OdrMeetingOtherlistView {
    private int id;//pkeys
    private String images;//缩略图
    private String name;//名称
    private String exhibition;//展厅
    private String country;//国家
    private String suppliername;//供应商
    private int state;//状态
    private Date starttime;//开始时间
    private Date endtime;//结束时间
}
