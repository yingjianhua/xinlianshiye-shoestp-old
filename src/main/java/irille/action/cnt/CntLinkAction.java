package irille.action.cnt;

import irille.action.MgtAction;
import irille.shop.cnt.CntLink;
import irille.shop.cnt.CntLinkDAO;

public class CntLinkAction extends MgtAction<CntLink> {

  @Override
  public Class beanClazz() {
    return CntLink.class;
  }

  public CntLink getBean() {
    return _bean;
  }

  public void setBean(CntLink bean) {
    this._bean = bean;
  }

  @Override
  public CntLink insRun() throws Exception {
    insBefore();
    CntLinkDAO.Ins insDao = new CntLinkDAO.Ins();
    insDao.setB(getBean());
    insDao.commit();
    insAfter();
    return insDao.getB();
  }

  @Override
  public CntLink updRun() throws Exception {
    updBefore();
    CntLinkDAO.Upd upd = new CntLinkDAO.Upd();
    upd.setB(getBean());
    upd.commit();
    updAfter();
    return upd.getB();
  }
}
