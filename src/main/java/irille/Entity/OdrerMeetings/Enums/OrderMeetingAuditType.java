package irille.Entity.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21 */
public enum OrderMeetingAuditType implements IEnumOpt {
  PLATFORM(0, "平台"),
  SUPPLIER(1, "商家");
  public static final String NAME = "供应商认证类型";
  public static final OrderMeetingAuditType DEFAULT = PLATFORM;
  private EnumLine _line;

  private OrderMeetingAuditType(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
