package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class FreightManagementView implements BaseView {
    private Integer pkey;//运费管理pkey
    private String courierCompany;//快递公司
    private String logo;//logo
    private String expressOrderNumberInquiryAddress;//快递单号查询地址
    private Integer sort;//排序
    private Byte usingInterface;//使用接口
    private Integer minimumWeight;//最小体重
    private Integer maximumWeight;//最大体重
    private Byte weightCalculation;//重量计算方式
}
