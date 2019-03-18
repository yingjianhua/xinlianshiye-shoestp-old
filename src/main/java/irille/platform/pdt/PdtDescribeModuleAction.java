package irille.platform.pdt;

import java.io.IOException;

import irille.action.ActionBase;
import irille.action.dataimport.util.StringUtil;
import irille.shop.pdt.PdtDescribeModule;
import irille.shop.pdt.PdtDescribeModuleDAo;
import lombok.Getter;
import lombok.Setter;

public class PdtDescribeModuleAction extends ActionBase<PdtDescribeModule> {

  @Setter @Getter private PdtDescribeModule bean;

  public void platGetList() throws IOException {
    write(PdtDescribeModuleDAo.platGetList(getStart(), getLimit()));
  }

  public void platGetInfo() throws IOException {
    if (getBean().getPkey() == null) {
      writeErr("参数错误");
      return;
    }
    write(PdtDescribeModuleDAo.platGetInfo(getBean().getPkey()));
  }

  public void platDel() throws IOException {
    if (getBean().getPkey() == null) {
      writeErr("参数错误");
      return;
    }
    PdtDescribeModuleDAo.platDel(getBean().getPkey());
    write();
  }

  public void platAdd() throws IOException {
    if (getBean() == null
        || !StringUtil.hasValue(getBean().getName())
        || !StringUtil.hasValue(getBean().getDescription())) {
      writeErr("参数错误");
      return;
    }
    PdtDescribeModuleDAo.platAdd(getBean());
    write();
  }

  public void platUpd() throws IOException {
    if (getBean() == null
        || !StringUtil.hasValue(getBean().getName())
        || !StringUtil.hasValue(getBean().getDescription())) {
      writeErr("参数错误");
      return;
    }
    PdtDescribeModuleDAo.platUpd(getBean());
    write();
  }

  @Override
  public Class beanClazz() { // TODO Auto-generated method stub
    return null;
  }
}
