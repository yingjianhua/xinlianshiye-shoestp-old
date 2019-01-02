package irille.view.newInq;

import irille.view.BaseView;
import lombok.Data;

@Data
public class newInqView implements BaseView {
    private Integer inqPkey;//pk大赛询盘pkey
    private String supplierName;//供应商名字
    private String name;//询盘名字
    private String email;//邮箱
    private String detail;//详情
}
