package irille.action.plt;

import irille.action.MgtAction;
import irille.shop.plt.PltFreightLine;

public class PltFreightLineAction extends MgtAction<PltFreightLine> {
  public PltFreightLine getBean() {
    return _bean;
  }

  public void setBean(PltFreightLine bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return PltFreightLine.class;
  }
}
