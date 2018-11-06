package irille.action.odr;

import irille.action.MgtAction;
import irille.shop.odr.OdrOrder;
import irille.shop.odr.OdrOrderDAO;
import irille.shop.odr.OdrOrderLine;

import java.util.ArrayList;
import java.util.List;

public class OdrOrderAction  extends MgtAction<OdrOrder>{
	private List<OdrOrderLine>  _listLine =new ArrayList<OdrOrderLine>();
	
 	public List<OdrOrderLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<OdrOrderLine> listLine) {
		this._listLine = listLine;
	}

	@Override
	public Class beanClazz() {
		return OdrOrder.class;
	}
	public  OdrOrder getBean(){
		return _bean;
	}
	public void setBean(OdrOrder bean){
		this._bean=bean;
	}
	@Override
	public OdrOrder insRun() throws Exception{
		OdrOrderDAO.Ins  insDao=new OdrOrderDAO.Ins();
		insDao.setB(getBean());
		insDao.setLines(getListLine());
		insDao.commit();
		return insDao.getB();
		
	}
	@Override
	public OdrOrder updRun() throws Exception {
		OdrOrderDAO.Upd upd = new OdrOrderDAO.Upd();
		upd.setB(getBean());
		upd.setLines(getListLine());
		upd.commit();
		return upd.getB();
	}
	public void del() throws Exception {
		OdrOrderDAO.Del del = new OdrOrderDAO.Del();
		del.setB(getBean());
		del.commit();
		writeSuccess(getBean());
	}
	 
	
}
