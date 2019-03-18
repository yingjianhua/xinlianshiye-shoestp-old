package irille.action.odr;

import irille.action.MgtAction;
import irille.shop.odr.OdrOrderLine;

public class OdrOrderLineAction extends MgtAction<OdrOrderLine> {

  @Override
  public Class beanClazz() {
    return OdrOrderLine.class;
  }

  public OdrOrderLine getBean() {
    return _bean;
  }

  public void setBean(OdrOrderLine bean) {
    this._bean = bean;
  }
}
