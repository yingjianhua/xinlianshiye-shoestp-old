package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrProductCategory;

public class UsrProductCategoryAction extends MgtAction<UsrProductCategory> {
	public UsrProductCategory getBean() {
		return _bean;
	}

	public void setBean(UsrProductCategory bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return UsrProductCategory.class;
	}
	
}