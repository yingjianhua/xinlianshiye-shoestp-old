package irille.action.plt;

import irille.action.MgtAction;
import irille.shop.plt.PltErate;
import irille.shop.plt.PltErateDAO;

public class PltErateAction extends MgtAction<PltErate> {

  public PltErate getBean() {
    return _bean;
  }

  public void setBean(PltErate bean) {
    this._bean = bean;
  }

  @Override
  public String orderBy() {
    return super.orderByAsc();
  }

  @Override
  public Class beanClazz() {
    // TODO Auto-generated method stub
    return PltErate.class;
  }

  /**
   * 设置网站默认货币
   *
   * @throws Exception
   * @author guosong
   */
  public void defcur() throws Exception {
    PltErateDAO.Defcur act = new PltErateDAO.Defcur();
    act.setBKey(getPkey());
    act.commit();
    writeSuccess();
  }

  /**
   * 设置商家默认货币
   *
   * @throws Exception
   * @author guosong
   */
  public void bcdefcur() throws Exception {
    PltErateDAO.Bcdefcur act = new PltErateDAO.Bcdefcur();
    act.setBKey(getPkey());
    act.commit();
    writeSuccess(act.getB());
  }
}
