package irille.action.exp;

import irille.action.MgtAction;
import irille.exp.exp.ExpCompLine;


public class ExpCompLineAction extends MgtAction<ExpCompLine> {
	public ExpCompLine getBean() {
		return _bean;
	}

	public void setBean(ExpCompLine bean) {
		this._bean = bean;
	}
	@Override
	public Class beanClazz() {
		return ExpCompLine.class;
	}

}