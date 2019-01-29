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
public class RFQ_EnquiryJson implements BaseView {
    private String name;   //询盘名称
    private String describe;  //询盘详细描述
    private String images;  //询盘图 多图
    private int number;   //要求的数量
    private String price;  //价格区间   1-100
    private String currency;  //币种
}
