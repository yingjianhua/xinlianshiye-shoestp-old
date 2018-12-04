package irille.Entity.OdrerMeetings.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:21
 */
public enum OrderMeetingProductStatus implements IEnumOpt {
    OFF(0, "下架"), ON(1, "上架"), IRREGULARITIESDELETE(2, "违规下架"), DELETE(3, "删除"), OWN(4, "自有"), PARTNER(5, "合作商");
    public static final String NAME = "供应商认证";
    public static final OrderMeetingProductStatus DEFAULT = OFF;
    private EnumLine _line;

    private OrderMeetingProductStatus(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }
}
