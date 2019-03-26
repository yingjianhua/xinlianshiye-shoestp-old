package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/** by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 */
public enum RFQConsultVerifyStatus implements IEnumOpt {
  UNAUDITED(1, "未审核"),
  FAIL(2, "未通过"),
  PASS(3, "通过"),
  ;

  public static final String NAME = "审核状态";
  public static final RFQConsultVerifyStatus DEFAULT = UNAUDITED;
  private EnumLine _line;

  private RFQConsultVerifyStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
