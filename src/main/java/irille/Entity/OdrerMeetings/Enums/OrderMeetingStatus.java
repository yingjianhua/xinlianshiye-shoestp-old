package irille.Entity.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * 订购会状态
 *
 * <p>Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21
 */
public enum OrderMeetingStatus implements IEnumOpt {
  TOBEGIN(5, "即将开始"),
  ACTIVITY(6, "活动中"),
  SUSPEND(7, "暂停"),
  END(8, "活动结束"),
  DELETE(9, "活动已删除");
  public static final String NAME = "订购会状态";
  public static final OrderMeetingStatus DEFAULT = TOBEGIN;
  private EnumLine _line;

  private OrderMeetingStatus(int key, String name) {
    _line = new EnumLine(this, key, name);
  }

  public EnumLine getLine() {
    return _line;
  }
}
