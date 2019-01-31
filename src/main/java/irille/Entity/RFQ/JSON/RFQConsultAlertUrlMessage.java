package irille.Entity.RFQ.JSON;

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
	
	private String alertMsg;//提示框内容
	private String showMsg;//显示内容
    private String url;//链接地址
}
