package irille.sellerAction.rfq.view;

import irille.Entity.RFQ.RFQConsultGroup;
import irille.view.BaseView;
import lombok.Data;

@Data
public class RFQConsultGroupView implements BaseView {
	
	private Integer pkey;
	private String name;
	private Integer supplierId;
	
	public static class Builder {
		
		public static RFQConsultGroupView toView(RFQConsultGroup bean) {
			RFQConsultGroupView view = new RFQConsultGroupView();
			view.setName(bean.getName());
			view.setPkey(bean.getPkey());
			view.setSupplierId(bean.getSupplier());
			return view;
		}
	}

}
