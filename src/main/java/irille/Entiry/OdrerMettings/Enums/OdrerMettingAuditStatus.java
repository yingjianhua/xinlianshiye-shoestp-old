package irille.Entiry.OdrerMettings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21
 */
public enum OdrerMettingAuditStatus implements IEnumOpt {
  NO(0, "未认证"), YES(1, "已认证");
  public static final String NAME = "供应商认证";
  public static final OdrerMettingAuditStatus DEFAULT = NO;
  private EnumLine _line;

  private OdrerMettingAuditStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
