package irille.sellerAction.rfq.view;

import java.util.Date;

import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultMessageView implements BaseView {
	private Integer pkey;    // 编号  INT
    private String content;    // 内容  JSONOBJECT
    private Byte type;    // 消息体类型 <RFQConsultMessageType>  BYTE
    // TEXT:1,文本信息
    // IMAGE:2,图片信息
    // Quote:3,报价信息
    private Date sendTime;    // 留言时间  TIME
    private Integer relation;    // 询盘关联 <表主键:RFQConsultRelation>  INT
    private Boolean p2S;    // 是采购商留言 <OYn>  BYTE
    // YES:1,是
    // NO:0,否
    private Boolean hadRead;    // 是否 <OYn>  BYTE
    // YES:1,是
    // NO:0,否
}
