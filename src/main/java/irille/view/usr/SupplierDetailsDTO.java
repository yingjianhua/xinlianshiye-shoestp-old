package irille.view.usr;

import java.util.Date;

import irille.view.BaseView;
import irille.view.v3.svs.SvsRatingAndRosDTO;
import lombok.Data;

@Data
public class SupplierDetailsDTO implements BaseView {
    private Integer pkey;//供应商pkey
    private String logo;//logo
    private String name;//店铺名称
    private String userName;//用户名
    private String targetedMarket;//目标市场
    private Date authentication_time;//SVS认证时间
    private SvsRatingAndRosDTO svsRatingAndRosDTO;//ROS分数以及星级
}
