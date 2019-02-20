package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQConsultType implements IEnumOpt {
    RFQ(1, "FRQ询盘"),
    INQUIRY(2, "询盘"),
    Private_INQUIRY(3, "私人展会询盘"),
    supplier_INQUIRY(4, "供应商询盘"),
    
    ;

    public static final String NAME = "询盘类型";
    public static final RFQConsultType DEFAULT = RFQ;
    private EnumLine _line;

    private RFQConsultType(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
