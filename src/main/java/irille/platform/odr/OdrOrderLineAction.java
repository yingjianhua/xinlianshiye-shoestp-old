package irille.platform.odr;

import java.io.IOException;

import irille.action.ActionBase;
import irille.shop.odr.OdrOrderLine;
import irille.shop.odr.OdrOrderLineDAO;
import lombok.Getter;
import lombok.Setter;

public class OdrOrderLineAction extends ActionBase<OdrOrderLine> {
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

  @Getter @Setter private Integer id;

  /** @Description: 获取订单详情列表 *@date 2019/1/11 18:40 *@anthor zjl */
  public void getOrders() throws IOException {
    write(OdrOrderLineDAO.getOrderLines(id));
  }
}
