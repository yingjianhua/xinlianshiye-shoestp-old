package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * 询盘关联的消息读取状态
 *
 * @author Jianhua Ying
 * @date 2019年3月26日
 */
public enum RFQConsultRelationReadStatus implements IEnumOpt {
  PURCHASE_UNREAD(1, "采购商未读"),
  PURCHASE_HADREAD(2, "采购商已读"),
  SUPPLIER_UNREAD(3, "供应商未读"),
  SUPPLIER_HADREAD(4, "供应商已读"),
  ;

  public static final String NAME = "询盘关联的消息读取状态";
  private EnumLine _line;
  public static final RFQConsultRelationReadStatus DEFAULT = PURCHASE_HADREAD;

  private RFQConsultRelationReadStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
