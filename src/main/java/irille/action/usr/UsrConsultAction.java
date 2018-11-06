package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrConsult;
import irille.shop.usr.UsrConsultDAO;

public class UsrConsultAction extends MgtAction<UsrConsult> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Class<UsrConsult> beanClazz() {
		return UsrConsult.class;
	}

	public UsrConsult getBean() {
		return _bean;
	}

	public void setBean(UsrConsult bean) {
		this._bean = bean;
	}
	
	@Override
	public void del() throws Exception {
		UsrConsultDAO.delete((Integer)getPkey());
		writeSuccess();
	}

}
