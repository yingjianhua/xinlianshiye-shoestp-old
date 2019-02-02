package irille.Entity.RFQ.JSON;

import irille.view.BaseView;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/29
 * Time: 20:41
 */
@Data
public class RFQConsultImageMessage implements BaseView, ConsultMessage {
	private String alt;//图片描述信息
    private String imageUrl;//图片地址
}
