package irille.action.plt;

import irille.action.MgtAction;
import irille.shop.plt.PltTrantslate;

public class PltTrantslateAction extends MgtAction<PltTrantslate> {

  @Override
  public Class<PltTrantslate> beanClazz() {
    return PltTrantslate.class;
  }

  public PltTrantslate getBean() {
    return _bean;
  }

  public void setBean(PltTrantslate bean) {
    this._bean = bean;
  }
}
