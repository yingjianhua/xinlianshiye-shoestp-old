package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/** by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18 */
public enum RFQConsultStatus implements IEnumOpt {
  ready(1, "待发布"),
  runing(2, "进行中"),
  complete(3, "已完成"),
  close(4, "已关闭"),
  ;

  public static final String NAME = "RFQ状态";
  public static final RFQConsultStatus DEFAULT = ready;
  private EnumLine _line;

  private RFQConsultStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
