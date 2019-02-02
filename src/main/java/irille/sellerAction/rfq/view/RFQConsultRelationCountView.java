package irille.sellerAction.rfq.view;

import irille.view.BaseView;
import lombok.Data;

/**
 * 商家询价单数量统计视图
 * 
 * @author Jianhua Ying
 *
 */
@Data
public class RFQConsultRelationCountView implements BaseView {

	private Integer notDeleted;
	private Integer isDeleted;
}
