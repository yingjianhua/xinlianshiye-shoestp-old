package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQConsultStatus implements IEnumOpt {
    OFF(1, "关闭"),
    ON(2, "开启"),
    ;

    public static final String NAME = "RFQ状态";
    public static final RFQConsultStatus DEFAULT = OFF;
    private EnumLine _line;

    private RFQConsultStatus(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
