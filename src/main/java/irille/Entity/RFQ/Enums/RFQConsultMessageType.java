package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/** by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 */
public enum RFQConsultMessageType implements IEnumOpt {
  TEXT(1, "文本信息"),
  IMAGE(2, "图片信息"),
  URL(3, "链接类型"),
  PRIVATE_PRODUCT_URL(4, "私人展厅产品链接消息"),
  ;

  public static final String NAME = "消息体类型";
  public static final RFQConsultMessageType DEFAULT = TEXT;
  private EnumLine _line;

  private RFQConsultMessageType(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
