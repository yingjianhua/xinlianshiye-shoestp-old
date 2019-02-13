package irille.platform.plt.View;

import irille.view.BaseView;
import lombok.Data;

@Data
public class FreightManagementView implements BaseView {
    private Integer pkey;//运费管理pkey
    private String company;//快递公司
    private String logo;//logo
    private String url;//快递单号查询地址
    private Integer sort;//排序
    private byte enabled;//启用
    private Byte usingInterface;//使用接口
    private Integer minWeight;//最小体重
    private Integer maxWeight;//最大体重
    private Byte type;//重量计算方式
    private Integer createdby;//当前操作人
}
