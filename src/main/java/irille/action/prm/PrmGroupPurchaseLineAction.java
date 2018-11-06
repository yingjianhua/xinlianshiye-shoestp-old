package irille.action.prm;

import irille.action.MgtAction;
import irille.shop.prm.PrmGroupPurchaseLine;

public class PrmGroupPurchaseLineAction extends MgtAction<PrmGroupPurchaseLine>{
	
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
