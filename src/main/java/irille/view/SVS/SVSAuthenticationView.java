package irille.view.SVS;
import com.fasterxml.jackson.annotation.JsonFormat;
import irille.view.BaseView;
import lombok.Data;

import java.util.Date;

@Data
public class SVSAuthenticationView implements BaseView {
    private Integer pkey;
    private SVSInfoView.research research; // 研发能力信息
    private SVSInfoView.productionCapacity capacity;// 生产能力信息
    private SVSInfoView.realFactory factory; // 真实工厂信息
    private SVSInfoView.productQuality quality; // 产品质量信息
    private SVSInfoView.tradeTeam team; // 外贸团队信息
    private SVSInfoView.exhibitionAttended exhibition; // 展会信息
    private SVSInfoView.partner part;// 合作商信息
    private Byte grade; // 会员等级
    private Integer baseScore; // 基础分
    private String failureReasons;//申请失败的原因
    private Byte status;// 认证状态
    private String supplier;// 申请认证用户
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date authenticationTime;//认证通过时间
    private Integer count;// 申请认证次数

}
