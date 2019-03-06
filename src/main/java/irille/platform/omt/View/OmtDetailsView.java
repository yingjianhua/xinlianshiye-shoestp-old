package irille.platform.omt.View;

import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class OmtDetailsView implements BaseView {
    private Integer pkey;//订购会id
    private String tiltle;//订购会标题
    private String exhibition;//展厅
    private String country;//所在国家
    private String logo;//封面图片
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date startTime;//开始时间
    @JsonFormat(pattern="yyyy/MM/dd HH:mm", timezone="GMT+8")
    private Date endTime;//结束时间
    /**------------------------------------*/
    private String address;//地址
    private String receiver;//收货人
    private String contactNumber;//联系电话
    private String zipCode;//邮编
    /**------------------------------------*/
    private Byte auditResults;//审核结果
    private String auditNote;//审核备注
}
