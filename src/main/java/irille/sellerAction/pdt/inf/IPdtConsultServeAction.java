package irille.sellerAction.pdt.inf;

import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

// @Controller(module="专属询盘", name="专属询盘")
public interface IPdtConsultServeAction extends ISellerAction {

  @RequestMapping(alias = "条件查询", sort = 1)
  public void termSelect() throws Exception;
}
