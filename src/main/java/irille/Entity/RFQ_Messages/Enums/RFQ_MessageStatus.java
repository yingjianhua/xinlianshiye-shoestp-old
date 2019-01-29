package irille.Entity.RFQ_Messages.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 9:53
 */
public enum RFQ_MessageStatus implements IEnumOpt {
    UNREAD(0, "未读"),
    READ(1, "已读"),
    ;

    public static final String NAME = "消息类型";
    public static final RFQ_MessageStatus DEFAULT = UNREAD;
    private EnumLine _line;

    private RFQ_MessageStatus(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }

}
