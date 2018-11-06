package irille.action.plt;

import irille.action.MgtAction;
import irille.shop.plt.PltFreight;
import irille.shop.plt.PltFreightDAO;
import irille.shop.plt.PltFreightLine;

import java.util.ArrayList;
import java.util.List;

public class PltFreightAction  extends MgtAction<PltFreight>{
	private List<PltFreightLine> _listLine=new ArrayList<PltFreightLine>();
	public List<PltFreightLine> getListLine() {
		return _listLine;
	}
	public void setListLine(List<PltFreightLine> _listLine) {
		this._listLine = _listLine;
	}
	@Override
	public Class beanClazz() {
		return PltFreight.class;
	}
	public PltFreight getBean()
	{
		return _bean;
	}
	public void setBean(PltFreight bean)
	{
		this._bean=bean;
	}
	@Override
	public PltFreight insRun() throws Exception {
		PltFreightDAO.Ins insDao = new PltFreightDAO.Ins();
		insDao.setB(getBean());
		insDao.setLines(getListLine());
		insDao.commit();
		return insDao.getB();
	}
	@Override
	public PltFreight updRun() throws Exception {
		PltFreightDAO.Upd upd = new PltFreightDAO.Upd();
		upd.setB(getBean());
		upd.setLines(getListLine());
		upd.commit();
		return upd.getB();
	}
	
}
