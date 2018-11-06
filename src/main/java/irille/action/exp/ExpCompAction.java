package irille.action.exp;

import irille.action.MgtAction;
import irille.exp.exp.ExpComp;
import irille.exp.exp.ExpCompDAO;
import irille.exp.exp.ExpCompLine;

import java.util.ArrayList;
import java.util.List;

public class ExpCompAction extends MgtAction<ExpComp> {

	private List<ExpCompLine> _listLine = new ArrayList<ExpCompLine>();
	
	public ExpComp getBean() {
		return _bean;
	}

	public void setBean(ExpComp bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return ExpComp.class;
	}
	

	public List<ExpCompLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<ExpCompLine> listLine) {
		_listLine = listLine;
	}
	
	@Override
	public ExpComp insRun() throws Exception {
		ExpCompDAO.Ins insDao = new ExpCompDAO.Ins();
		insDao.setB(getBean());
		insDao.setLines(getListLine());
		insDao.commit();
		return insDao.getB();
	}

	@Override
	public ExpComp updRun() throws Exception {
		ExpCompDAO.Upd upd = new ExpCompDAO.Upd();
		upd.setB(getBean());
		upd.setLines(getListLine());
		upd.commit();
		return upd.getB();
	}
	
	public void del() throws Exception {
		ExpCompDAO.Del del = new ExpCompDAO.Del();
		del.setB(getBean());
		del.commit();
		writeSuccess(getBean());
	}

}