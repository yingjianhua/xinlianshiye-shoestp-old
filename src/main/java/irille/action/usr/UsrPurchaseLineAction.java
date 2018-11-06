package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrPurchaseLine;

public class UsrPurchaseLineAction extends MgtAction<UsrPurchaseLine>{
	
	public UsrPurchaseLine getBean() {
		return _bean;
	}

	public void setBean(UsrPurchaseLine bean) {
		this._bean = bean;
	}
	
	@Override
	public Class beanClazz() {
		return UsrPurchaseLine.class;
	}
	
	
	
	

}
