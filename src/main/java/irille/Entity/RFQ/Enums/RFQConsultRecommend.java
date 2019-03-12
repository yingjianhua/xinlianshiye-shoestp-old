package irille.Entity.RFQ.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public enum RFQConsultRecommend implements IEnumOpt {
    NOT_RECOMMENDED(0, "不推荐"),
    RECOMMENDED(1, "推荐");
    public static final String NAME = "是否推荐";
    public static final RFQConsultRecommend DEFAULT = NOT_RECOMMENDED;
    private EnumLine _line;

    private RFQConsultRecommend(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
