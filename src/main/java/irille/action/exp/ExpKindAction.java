package irille.action.exp;

import irille.action.MgtAction;
import irille.exp.exp.ExpKind;

public class ExpKindAction extends MgtAction<ExpKind> {
	public ExpKind getBean() {
		return _bean;
	}

	public void setBean(ExpKind bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return ExpKind.class;
	}

}