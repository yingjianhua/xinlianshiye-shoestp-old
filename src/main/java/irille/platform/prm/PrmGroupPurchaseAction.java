package irille.platform.prm;

import java.io.IOException;

import irille.action.MgtAction;
import irille.shop.prm.PrmGroupPurchase;
import irille.shop.prm.PrmGroupPurchaseDAO;
import lombok.Getter;
import lombok.Setter;

public class PrmGroupPurchaseAction extends MgtAction<PrmGroupPurchase> {
  @Override
  public Class beanClazz() {
    return PrmGroupPurchase.class;
  }

  public PrmGroupPurchase getBean() {
    return _bean;
  }

  public void setBean(PrmGroupPurchase bean) {
    this._bean = bean;
  }

  @Getter @Setter private String title;
  @Getter @Setter private Integer status;
  @Getter @Setter private Integer supplier;

  /** @Description: 获取联合采购列表 *@date 2019/1/17 16:28 *@anthor zjl */
  public void getPrmGroupPurchases() throws IOException {
    write(
        PrmGroupPurchaseDAO.getPrmGroupPurchases(getStart(), getLimit(), title, status, supplier));
  }

  /** @Description: 获取联合采购活动状态AND提前预告AND供应商列表 *@date 2019/1/18 09:30 *@anthor zjl */
  public void getStatusAndType() throws IOException {
    write(PrmGroupPurchaseDAO.getStatusAndType());
  }
}
