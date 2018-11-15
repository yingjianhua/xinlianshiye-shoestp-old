package irille.view.Manage.OdrMeeting.Sale;

import irille.pub.util.SetBeans.SetBean.Annotations.SetBean;
import java.util.Map;
import lombok.Data;

/**
 * 订购会销售信息 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:42
 */
@Data
public class OdrMeetingSaleInfoView {

  @SetBean(OriginalField = "pkey")
  private int id;
  private int pdtId; //商品名称
  private String pdtname; //商品名称
  private double price;//订购会价格
  private int count;
  private String status;
  private Map<String, OdrMeetingSpecSaleInfoView> specList;// 规格销售
  private double countPrice;
  private boolean pdtType;//商品类型


}
