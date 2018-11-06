package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrSupplierCategory;

public class UsrSupplierCategoryAction extends MgtAction<UsrSupplierCategory> {
	public UsrSupplierCategory getBean() {
		return _bean;
	}

	public void setBean(UsrSupplierCategory bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return UsrSupplierCategory.class;
	}
	
}