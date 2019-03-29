package irille.action.cnt;

import java.util.ArrayList;
import java.util.List;

import irille.action.MgtAction;
import irille.shop.cnt.CntAd;
import irille.shop.cnt.CntAdDAO;
import irille.shop.cnt.CntAdLine;

public class CntAdAction extends MgtAction<CntAd> {

  private static final long serialVersionUID = 1L;

  private List<CntAdLine> _listLine = new ArrayList<CntAdLine>();

  @Override
  public Class<CntAd> beanClazz() {
    return CntAd.class;
  }

  public CntAd getBean() {
    return _bean;
  }

  public void setBean(CntAd bean) {
    this._bean = bean;
  }

  public List<CntAdLine> getListLine() {
    return _listLine;
  }

  public void setListLine(List<CntAdLine> listLine) {
    _listLine = listLine;
  }

  @Override
  public CntAd insRun() throws Exception {
    insBefore();
    CntAdDAO.Ins insDao = new CntAdDAO.Ins();
    insDao.setB(getBean());
    insDao.setLines(getListLine());
    insDao.commit();
    insAfter();
    return insDao.getB();
  }

  @Override
  public CntAd updRun() throws Exception {
    updBefore();
    CntAdDAO.Upd upd = new CntAdDAO.Upd();
    upd.setB(getBean());
    upd.setLines(getListLine());
    upd.commit();
    updAfter();
    return upd.getB();
  }

  public void delDetails() throws Exception {
    CntAdDAO.Del del = new CntAdDAO.Del();
    del.setB(getBean());
    del.commit();
    writeSuccess(getBean());
  }

  @Override
  public String orderBy() {
    return " ORDER BY SIGNAGE,AD_POSITION DESC";
  }
}
