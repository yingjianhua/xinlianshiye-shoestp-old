package irille.action.pdt;

import irille.action.MgtAction;
import irille.shop.pdt.PdtComment;
import irille.shop.pdt.PdtCommentDAO;

public class PdtCommentAction extends MgtAction<PdtComment>{
	
	public PdtComment getBean(){
		return _bean;
	}
	
	public void setBean(PdtComment bean){
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return PdtComment.class;
	}
	
	@Override
	public void ins() throws Exception {
		PdtCommentDAO.Ins ins = new PdtCommentDAO.Ins();
		ins.setB(getBean());
		ins.commit();
		writeSuccess();
	}
	
	@Override
	public void upd() throws Exception {
		PdtCommentDAO.Upd upd = new PdtCommentDAO.Upd();
		upd.setB(getBean());
		upd.commit();
		writeSuccess();
	}
	
	
	

}
