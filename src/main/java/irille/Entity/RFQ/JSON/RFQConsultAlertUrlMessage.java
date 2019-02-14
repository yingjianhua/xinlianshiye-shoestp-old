package irille.Entity.RFQ.JSON;

import java.util.Date;

import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.view.BaseView;
import lombok.Data;

/**
 * 点击会弹出提示栏的url链接
 * 
 * @author Jianhua Ying
 *
 */
@Data
public class RFQConsultAlertUrlMessage implements BaseView, ConsultMessage {
	
	private Date validDate;//过期时间
	private Integer productId;//商品主键
	private String alertMsg;//提示框内容
	private String showMsg;//显示内容
    private String url;//链接地址
	@Override
	public Byte getType() {
		return RFQConsultMessageType.ALERT_URL.getLine().getKey();
	}
}
