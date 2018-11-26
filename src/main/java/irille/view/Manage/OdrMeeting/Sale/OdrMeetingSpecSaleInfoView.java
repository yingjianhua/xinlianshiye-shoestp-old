package irille.view.Manage.OdrMeeting.Sale;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import lombok.Data;

/**
 * 具体规格 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/15 Time: 15:12
 */
@Data
public class OdrMeetingSpecSaleInfoView {

    @SetBean(OriginalField = "specName")
    private String name;
    private double price;
    private int qty;
    private double subtotal;
}
