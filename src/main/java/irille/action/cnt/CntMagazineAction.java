package irille.action.cnt;

import irille.action.MgtAction;
import irille.shop.cnt.CntMagazine;

public class CntMagazineAction extends MgtAction<CntMagazine>{

	@Override
	public Class beanClazz() {
		return CntMagazine.class;
	}
    
	public CntMagazine getBean(){
		return _bean;
	}
	public void setBean(CntMagazine bean){
		this._bean = bean;
	}
	
	
}
