package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQConsultPayType implements IEnumOpt {
    TOBEGIN(1,
            "即将开始"),
    ACTIVITY(2, "活动中"),
    END(3, "活动结束"),
    ;

    public static final String NAME = "活动状态";
    public static final RFQConsultPayType DEFAULT = TOBEGIN;
    private EnumLine _line;

    private RFQConsultPayType(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
