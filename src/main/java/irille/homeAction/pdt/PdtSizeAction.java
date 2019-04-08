package irille.homeAction.pdt;

import irille.action.ActionBase;
import irille.shop.pdt.PdtSize;

public class PdtSizeAction extends ActionBase<PdtSize> {
  public PdtSize getBean() {
    return _bean;
  }

  public void setBean(PdtSize bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return PdtSize.class;
  }
}
