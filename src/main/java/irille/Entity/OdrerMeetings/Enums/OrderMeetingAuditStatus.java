package irille.Entity.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * 订购会状态
 * <p>
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21
 */
public enum OrderMeetingAuditStatus implements IEnumOpt {
    PENDING(0, "待审核"), VERIFYING(1, "审核中"), ACTIVITY(2, "审核通过"), SUSPEND(3, "暂停"), DELETE(4, "已删除");
    public static final String NAME = "参加订购会状态";
    public static final OrderMeetingAuditStatus DEFAULT = PENDING;
    private EnumLine _line;

    private OrderMeetingAuditStatus(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
