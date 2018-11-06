package irille.action.prm;

import irille.action.MgtAction;
import irille.shop.prm.PrmGroupOrder;
import irille.shop.prm.PrmGroupOrderDAO;
import irille.shop.prm.PrmGroupOrderLine;

import java.util.ArrayList;
import java.util.List;

public class PrmGroupOrderAction extends MgtAction<PrmGroupOrder>{
	
	private List<PrmGroupOrderLine> _listLine = new ArrayList<PrmGroupOrderLine>();
	
	public void setBean(PrmGroupOrder bean){
		this._bean = bean;
	}

	public PrmGroupOrder getBean(){
		return _bean;
		
	}
	
	public List<PrmGroupOrderLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<PrmGroupOrderLine> _list) {
		this._listLine = _list;
	}

	@Override
	public Class beanClazz() {
		return PrmGroupOrder.class;
	}
	
	/**
	 * PrmOrder插入操作
	 */
	public PrmGroupOrder insRun(){
		PrmGroupOrderDAO.Ins ins = new PrmGroupOrderDAO.Ins();
		insBefore();
		ins.setB(getBean());
		ins.setLines(getListLine());
		ins.commit();
		insAfter();
		return ins.getB();
	}
	
	
	
}
