package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrMemberLevel;

public class UsrMemberLevelAction extends MgtAction<UsrMemberLevel>{
	public UsrMemberLevel getBean() {
		return _bean;
	}

	public void setBean(UsrMemberLevel bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return UsrMemberLevel.class;
	}
	
	

}
