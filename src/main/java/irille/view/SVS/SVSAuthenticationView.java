package irille.view.SVS;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SVSAuthenticationView implements BaseView {
    private Integer pkey;
    private String research;//开发团队
    private String productiveCapacity;//生产能力
    private String realFactory;//真实工厂
    private String productQualuity;//产品质量
    private String foreingTradeTeam;//外贸团队
    private String exhibitionAttended;//曾经参加的展会
    private String pratner;//合作商
    private byte status;//认证状态
    private String supplier;//供应商
}
