package irille.sellerAction.pdt;

import java.io.IOException;

import irille.action.dataimport.util.StringUtil;
import irille.sellerAction.SellerAction;
import irille.sellerAction.pdt.inf.IPdtDescribeModuleAction;
import irille.shop.pdt.PdtDescribeModule;
import irille.shop.pdt.PdtDescribeModuleDAo;

public class PdtDescribeModuleAction extends SellerAction<PdtDescribeModule>
    implements IPdtDescribeModuleAction {

  public void getList() throws IOException {
    write(PdtDescribeModuleDAo.getList(getSupplier().getPkey(), getStart(), getLimit()));
  }

  public void getInfo() throws IOException {
    if (getBean().getPkey() == null) {
      writeErr("参数错误");
      return;
    }
    write(PdtDescribeModuleDAo.getInfo(getSupplier().getPkey(), getBean().getPkey()));
  }

  public void del() throws IOException {
    if (getBean().getPkey() == null) {
      writeErr("参数错误");
      return;
    }
    PdtDescribeModuleDAo.del(getSupplier().getPkey(), getBean().getPkey());
    write();
  }

  public void add() throws IOException {
    if (getBean() == null
        || !StringUtil.hasValue(getBean().getName())
        || !StringUtil.hasValue(getBean().getDescription())) {
      writeErr("参数错误");
      return;
    }
    PdtDescribeModuleDAo.add(getBean(), getSupplier().getPkey());
    write();
  }

  public void upd() throws IOException {
    if (getBean() == null
        || !StringUtil.hasValue(getBean().getName())
        || !StringUtil.hasValue(getBean().getDescription())) {
      writeErr("参数错误");
      return;
    }
    PdtDescribeModuleDAo.upd(getBean(), getSupplier().getPkey());
    write();
  }
}
