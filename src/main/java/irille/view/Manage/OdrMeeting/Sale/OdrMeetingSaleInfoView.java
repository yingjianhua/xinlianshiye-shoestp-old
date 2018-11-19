package irille.view.Manage.OdrMeeting.Sale;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 订购会销售信息 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:42
 */
@Data
public class OdrMeetingSaleInfoView {

    @SetBean(OriginalField = "pkey")
    private int id;
    private int pdtId; //商品名称
    private String pdtName; //商品名称
    private double price;//订购会价格
    private int qty;  //数量
    private int count;  //采购人数
    private String status; //状态
    private Map<String, List<OdrMeetingSpecSaleInfoView>> specList;// 规格销售
    private double countPrice;
    private boolean pdtType;//商品类型


}
