package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQConsultPayType implements IEnumOpt {
    TT(1, "TT支付"),
    LC(2, "L/C"),
    DP(3, "D/P"),
    WesternUnion(4, "Western Union"),
    MoneyGram(5, "Money Gram")
    ;

    public static final String NAME = "支付方式";
    public static final RFQConsultPayType DEFAULT = TT;
    private EnumLine _line;

    private RFQConsultPayType(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
