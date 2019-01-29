package irille.Entity.RFQ_Messages.Enums;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/13 Time: 16:18
 */
public enum RFQ_MessageType implements IEnumOpt {
    INFORMATION(0, "普通消息"),
    RFQ_ENQUIRY(1, "RFQ询盘"),
    RFQ_QUOTATION(2, "RFQ报价"),
    RFQ_REPLY(3, "RFQ普通回复"),
    PRIVATE_ENQUIRY(4, "私人询盘"),
    PRIVATE_QUOTATION(5, "私人报价"),
    PRIVATE_REPLY(6, "私人询盘普通回复"),
    ;

    public static final String NAME = "消息类型";
    public static final RFQ_MessageType DEFAULT = INFORMATION;
    private EnumLine _line;

    private RFQ_MessageType(int key, String name) {
        _line = new EnumLine(this, key, name);
    }

    public EnumLine getLine() {
        return _line;
    }

}
