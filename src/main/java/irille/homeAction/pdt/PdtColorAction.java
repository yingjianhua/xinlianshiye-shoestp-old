package irille.homeAction.pdt;

import irille.action.ActionBase;
import irille.shop.pdt.PdtColor;

public class PdtColorAction extends ActionBase<PdtColor> {
  public PdtColor getBean() {
    return _bean;
  }

  public void setBean(PdtColor bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return PdtColor.class;
  }
}
