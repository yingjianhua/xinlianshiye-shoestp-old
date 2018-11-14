package irille.Entiry.OdrerMettings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:46
 */
public enum OrderMeetingExhibitionStatus implements IEnumOpt {
  NO(0, "未认证"), YES(1, "已认证");
  public static final String NAME = "供应商认证";
  public static final OrderMeetingExhibitionStatus DEFAULT = NO;
  private EnumLine _line;

  private OrderMeetingExhibitionStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
