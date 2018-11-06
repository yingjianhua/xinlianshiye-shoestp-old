package irille.sellerAction.prm;

import irille.action.ActionBase;
import irille.shop.prm.PrmGroupPurchaseLine;

public class PrmGroupPurchaseLineAction extends ActionBase<PrmGroupPurchaseLine>{
	
	public PrmGroupPurchaseLine getBean(){
		return _bean;
	}
	
	public void setBean(PrmGroupPurchaseLine bean){
		this._bean = bean;
	}
	
	@Override
	public Class beanClazz() {
		return PrmGroupPurchaseLine.class;
	}
	

}
