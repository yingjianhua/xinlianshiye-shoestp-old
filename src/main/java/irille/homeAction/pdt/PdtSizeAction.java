package irille.homeAction.pdt;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.action.ActionBase;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.shop.pdt.PdtSize;

public class PdtSizeAction extends ActionBase<PdtSize> {
	public PdtSize getBean() {
		return _bean;
	}

	public void setBean(PdtSize bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PdtSize.class;
	}

	
	
	
	
}