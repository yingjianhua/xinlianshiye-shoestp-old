package irille.platform.omt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class OmtDetailsView implements BaseView {
    private Integer pkey;//订购会id
    private String tiltle;//订购会标题
    private String exhibition;//展厅
    private String country;//所在国家
    private String logo;//封面图片
    private String startTime;//开始时间
    private String endTime;//结束时间
    /**------------------------------------*/
    private String address;//地址
    private String receiver;//收货人
    private String contactNumber;//联系电话
    private String zipCode;//邮编
    /**------------------------------------*/
    private Byte auditResults;//审核结果
    private String auditNote;//审核备注
}
