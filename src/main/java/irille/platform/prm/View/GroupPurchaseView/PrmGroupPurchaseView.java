package irille.platform.prm.View.GroupPurchaseView;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class PrmGroupPurchaseView implements BaseView {
    private Integer id;//主键
    private String title;//活动标题
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date startTime;//开始时间
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date endTime;//结束时间
    private Integer status;//活动状态
    private Integer preTime;//提前预告时间
    private String supplier;//供应商名称
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date createdTime;//建档时间
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date updatedTime;//更新时间
}
