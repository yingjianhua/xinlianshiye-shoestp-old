package irille.Entity.O2O.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/** by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 */
public enum O2O_PrivateExpoPdtStatus implements IEnumOpt {
  _DEFAULT(0, "未审核"),
  OFF(1, "下架"),
  ON(2, "上架"),
  PASS(3, "审核通过"),
  Failed(4, "审核失败"),
  ;
  public static final String NAME = "O2O商品状态";
  public static final O2O_PrivateExpoPdtStatus DEFAULT = _DEFAULT;
  private EnumLine _line;

  private O2O_PrivateExpoPdtStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
