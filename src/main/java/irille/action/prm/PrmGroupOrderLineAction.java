package irille.action.prm;

import irille.action.MgtAction;
import irille.shop.prm.PrmGroupOrderLine;

public class PrmGroupOrderLineAction extends MgtAction<PrmGroupOrderLine> {

  public void setBean(PrmGroupOrderLine bean) {
    this._bean = bean;
  }

  public PrmGroupOrderLine getBean() {
    return _bean;
  }

  @Override
  public Class beanClazz() {
    // TODO Auto-generated method stub
    return PrmGroupOrderLine.class;
  }
}
