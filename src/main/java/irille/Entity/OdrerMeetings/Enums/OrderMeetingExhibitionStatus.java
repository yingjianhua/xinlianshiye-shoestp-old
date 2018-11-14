package irille.Entity.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:46
 */
public enum OrderMeetingExhibitionStatus implements IEnumOpt {
  FAILED(0, "未通过"), PASS(1, "通过"), DELETE(2, "删除");
  public static final String NAME = "供应商场地";
  public static final OrderMeetingExhibitionStatus DEFAULT = FAILED;
  private EnumLine _line;

  private OrderMeetingExhibitionStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
