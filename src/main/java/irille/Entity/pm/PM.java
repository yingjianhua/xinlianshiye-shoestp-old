package irille.Entity.pm;

import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;

public class PM{
    public enum OMessageType implements IEnumOpt {//@formatter:off
        PM(1, "站内信"), EMAIL(0, "邮件");
        public static final String NAME = "消息类别";
        public static final OMessageType DEFAULT = PM; // 定义缺省值
        private EnumLine _line;

        private OMessageType(int key, String name) {
            _line = new EnumLine(this, key, name);
        }

        @Override
        public EnumLine getLine() {
            return _line;
        }

    }
    public enum ORCVRType implements IEnumOpt {//@formatter:off
    	ALL(-1,"所有"),SUPPLIER(1, "供应商"), PURCHASE(0, "采购商");
    	public static final String NAME = "收件人类型";
    	public static final ORCVRType DEFAULT = ALL; // 定义缺省值
    	private EnumLine _line;
    	
    	private ORCVRType(int key, String name) {
    		_line = new EnumLine(this, key, name);
    	}
    	
    	@Override
    	public EnumLine getLine() {
    		return _line;
    	}
    }
    public enum OTempType implements IEnumOpt {//@formatter:off
    	UNSET(-1,"",ORCVRType.SUPPLIER,""),
    	REGISTER_SUPPLIER(0, "邮件验证",ORCVRType.SUPPLIER,""), 
    	SHOP_APPR(1, "店铺审核通知",ORCVRType.SUPPLIER,"/platform/usr_UsrSupplier_updStatus"),
    	ADVERTISEMENT_SUPPLIER(2,"广告推送",ORCVRType.SUPPLIER,""),
    	SVS_WRITE_NOTICE(3,"SVS填写通知",ORCVRType.SUPPLIER,""),
    	SVS_APPR_NOTICE(4,"SVS审核通知",ORCVRType.SUPPLIER,"/platform/svs_SVSInfo_updAutInfo"),
    	PROD_APPR_NOTICE(5,"产品审核通知",ORCVRType.SUPPLIER,"/platform/pdt_PdtProduct_review"),
    	O2O_PROD_APPR_NOTICE(22,"O2O产品审核通知",ORCVRType.SUPPLIER,"/platform/o2o_O2OActivity_pass"),
    	ACTIVITY_NOTICE(6,"活动提醒",ORCVRType.SUPPLIER,""),
    	O2O_STOCK(7,"O2O批发库存状态提醒",ORCVRType.SUPPLIER,""),
    	O2O_ORDER(8,"O2O批发订单状态提醒",ORCVRType.SUPPLIER,""),
    	RFQ_REPLY(9,"RFQ报价回复提醒",ORCVRType.SUPPLIER,""),
    	INQUIRY_NOTICE_SUPPLIER(10,"询盘提醒",ORCVRType.SUPPLIER,""),
    	CONTACT_INFO_NOTICE_SUPPLIER(11,"联系人信息提醒",ORCVRType.SUPPLIER,""),
    	ORDER_STATUS_NOTICE_SUPPLIER(12,"订单状态提醒",ORCVRType.SUPPLIER,""),
    	O2O_ACTIVITY_NOTICE(24,"O2O活动提醒",ORCVRType.SUPPLIER,"irille.Service.Manage.O2O.Imp.O2OActicityServerImp.execute"),
    	
    	REGISTER_PURCHASE(13, "邮件验证",ORCVRType.PURCHASE,""),
    	SYSTEM_NOTICE(14,"系统提醒",ORCVRType.PURCHASE,""),
    	ADVERTISEMENT_PURCHASE(15,"广告推送",ORCVRType.PURCHASE,""),
    	RFQ_INFO_NOTICE(16,"RFQ信息提醒",ORCVRType.PURCHASE,"/home/rfq_RFQConsult_putRFQInquiry"),
    	INQUIRY_NOTICE_PURCHASE(17,"询盘提醒",ORCVRType.PURCHASE,"/home/rfq_RFQConsult_putInquiry"),
    	CONTACT_INFO_NOTICE_PURCHASE(18,"联系人信息提醒",ORCVRType.PURCHASE,""),
    	ACTIVITY_INFO_NOTICE(19,"活动信息推送",ORCVRType.PURCHASE,""),
    	ORDER_STATUS_NOTICE_PURCHASE(20,"订单状态提醒",ORCVRType.PURCHASE,""),
    	
    	RFQ_MESSAGE_NOTICE(23,"RFQ消息提醒",ORCVRType.PURCHASE,"/seller/rfq_RFQConsultMessage_send"),
    	PURCHASE_FORGET_PASSWORD(21,"用户忘记密码",ORCVRType.PURCHASE,"/home/usr_UsrPurchase_uda")
    	;
    	public static final String NAME = "收件人类型";
    	public static final OTempType DEFAULT = UNSET; // 定义缺省值
    	private EnumLine _line;
    	private String _method;
    	private ORCVRType _rcvrType;
    	
    	private OTempType(int key, String name,ORCVRType rcvrType,String method) {
    		_line = new EnumLine(this, key, name);
    		this._method = method;
    		this._rcvrType = rcvrType;
    	}
    	public ORCVRType getRcvrType() {
    		return _rcvrType;
    	}
    	public String getMethod() {
    		return _method;
    	}
    	
    	@Override
    	public EnumLine getLine() {
    		return _line;
    	}
    }
	
}
