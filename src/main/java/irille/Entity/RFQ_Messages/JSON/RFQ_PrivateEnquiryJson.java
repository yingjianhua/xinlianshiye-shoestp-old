package irille.Entity.RFQ_Messages.JSON;

import irille.view.BaseView;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 9:57
 */
@Data
public class RFQ_PrivateEnquiryJson implements BaseView {
    private int pdtId;   //导入商品的外键名称
    private String describe;  //询盘详细描述
    private int number;  //数量总和
    private String price;   //价格区间
    private String unit;
    private String images;

}
