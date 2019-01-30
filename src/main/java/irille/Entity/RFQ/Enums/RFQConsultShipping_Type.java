package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQConsultShipping_Type implements IEnumOpt {
    OCEAN_SHIPPING(1, "海运"),
    ;

    public static final String NAME = "配送方式";
    public static final RFQConsultShipping_Type DEFAULT = OCEAN_SHIPPING;
    private EnumLine _line;

    private RFQConsultShipping_Type(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
