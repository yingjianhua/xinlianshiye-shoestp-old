package irille.Entity.RFQ.JSON;

import irille.Entity.RFQ.Enums.RFQConsultMessageType;
import irille.view.BaseView;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 20:41
 */
@Data
public class RFQConsultTextMessage implements BaseView, ConsultMessage  {
	
    private String content;

	@Override
	public Byte getType() {
		return RFQConsultMessageType.TEXT.getLine().getKey();
	}
}
