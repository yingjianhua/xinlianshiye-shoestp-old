package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQConsultUnit implements IEnumOpt {
    PAIR(1, "双"),
    ;

    public static final String NAME = "货物单位";
    public static final RFQConsultUnit DEFAULT = PAIR;
    private EnumLine _line;

    private RFQConsultUnit(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
