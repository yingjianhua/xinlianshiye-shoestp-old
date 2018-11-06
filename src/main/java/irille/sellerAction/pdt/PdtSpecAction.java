package irille.sellerAction.pdt;

import irille.action.ActionBase;
import irille.shop.pdt.PdtSpec;

public class PdtSpecAction extends ActionBase<PdtSpec>{
	public PdtSpec getBean() {
		return _bean;
	}

	public void setBean(PdtSpec bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PdtSpec.class;
	}

}
