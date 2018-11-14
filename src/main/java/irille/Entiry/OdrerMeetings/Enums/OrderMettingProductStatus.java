package irille.Entiry.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21
 */
public enum OrderMettingProductStatus implements IEnumOpt {
  OFF(0, "下架"), ON(1, "上架"), DELETE(2, "违规下架");
  public static final String NAME = "供应商认证";
  public static final OrderMettingProductStatus DEFAULT = OFF;
  private EnumLine _line;

  private OrderMettingProductStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
