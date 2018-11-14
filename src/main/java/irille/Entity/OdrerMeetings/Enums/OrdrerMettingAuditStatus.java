package irille.Entity.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * 订购会状态
 *
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21
 */
public enum OrdrerMettingAuditStatus implements IEnumOpt {
  PENDING(0, "待审核"), VERIFYING(1, "审核中"), TOBEGIN(2,
      "即将开始"),ACTIVITY(3,"活动中"), SUSPEND(4, "暂停"),END(5,"活动结束"),DELETE(6,"活动已删除");
  public static final String NAME = "订购会状态";
  public static final OrdrerMettingAuditStatus DEFAULT = PENDING;
  private EnumLine _line;

  private OrdrerMettingAuditStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
