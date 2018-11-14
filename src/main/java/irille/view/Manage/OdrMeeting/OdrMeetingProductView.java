package irille.view.Manage.OdrMeeting;

import lombok.Data;

/**
 * 订购会商品表 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 14:06
 */
@Data
public class OdrMeetingProductView {

  private int id;
  private int pdtId;
  private String pdtName;
  //商品类型
  private boolean pdtType;
  private int supplierId;
  private int moq;
  private int price;
  private int newMoq;
  private int newPrice;
  private String status;
  //是否有订单
  private boolean hasOrder;
}
