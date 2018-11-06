package irille.action.cnt;

import irille.action.MgtAction;
import irille.shop.cnt.CntAdLine;

public class CntAdLineAction extends MgtAction<CntAdLine>{

	private static final long serialVersionUID = 1L;

	public CntAdLine getBean() {
		return _bean;
	}

	public void setBean(CntAdLine bean) {
		this._bean = bean;
	}
	@Override
	public Class<CntAdLine> beanClazz() {
		return CntAdLine.class;
	}

	@Override
	public String orderBy() {
		return " ORDER BY MAIN_IMG DESC,SORT ASC";
	}
}
