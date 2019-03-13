package irille.sellerAction.prm;

import java.util.ArrayList;
import java.util.List;

import irille.sellerAction.SellerAction;
import irille.shop.prm.PrmGroupOrder;
import irille.shop.prm.PrmGroupOrderDAO;
import irille.shop.prm.PrmGroupOrderLine;

public class PrmGroupOrderAction extends SellerAction<PrmGroupOrder> {

  private List<PrmGroupOrderLine> _listLine = new ArrayList<PrmGroupOrderLine>();

  public List<PrmGroupOrderLine> get_listLine() {
    return _listLine;
  }

  public void set_listLine(List<PrmGroupOrderLine> _listLine) {
    this._listLine = _listLine;
  }

  public PrmGroupOrder insRun() throws Exception {
    insBefore();
    PrmGroupOrderDAO.Ins ins = new PrmGroupOrderDAO.Ins();
    ins.setB(getBean());
    ins.commit();
    insAfter();
    return ins.getB();
  }
}
