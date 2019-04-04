package irille.sellerAction.usr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irille.action.ActionBase;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrPurchaseDAO;
import irille.shop.usr.UsrPurchaseLine;
import org.json.JSONException;

public class UsrPurchaseAction extends ActionBase<UsrPurchase> {
  private List<UsrPurchaseLine> _listLine = new ArrayList<UsrPurchaseLine>();

  public UsrPurchase getBean() {
    return _bean;
  }

  public void setBean(UsrPurchase bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return UsrPurchase.class;
  }

  public List<UsrPurchaseLine> getListLine() {
    return _listLine;
  }

  public void setListLine(List<UsrPurchaseLine> listLine) {
    _listLine = listLine;
  }

  public UsrPurchase insRun() throws IOException, JSONException {
    UsrPurchaseDAO.Ins ins = new UsrPurchaseDAO.Ins();
    ins.setB(getBean());
    ins.commit();
    return ins.getB();
  }

  public UsrPurchase updRun() throws IOException, JSONException {
    UsrPurchaseDAO.Upd upd = new UsrPurchaseDAO.Upd();
    upd.setB(getBean());
    upd.setLines(getListLine());
    upd.commit();
    return upd.getB();
  }

  public void uda() throws Exception {
    UsrPurchaseDAO.Uda uda = new UsrPurchaseDAO.Uda();
    uda.setBKey(getPkey());
    uda.commit();
    writeSuccess();
  }
}
