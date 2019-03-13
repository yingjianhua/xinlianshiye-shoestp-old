package irille.platform.pdt;

import java.io.IOException;

import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtColor;
import irille.shop.pdt.PdtColorDAO;
import lombok.Data;

/** @Author: lingjian @Date: Created in 2019/1/17 15:01 @Version 1.0 */
@Data
public class PdtColorAction extends ActionBase<PdtColor> {

  @Override
  public Class beanClazz() {
    return PdtColor.class;
  }

  public PdtColor getBean() {
    return _bean;
  }

  public void setBean(PdtColor bean) {
    this._bean = bean;
  }

  private String name; // 搜索的产品颜色名称

  /**
   * 查询产品颜色列表+搜索
   *
   * @throws Exception
   * @author lingjian
   * @date 2019/1/22 13:36
   */
  public void list() throws Exception {
    write(PdtColorDAO.listview(name, getStart(), getLimit()));
  }

  /**
   * 新增产品颜色
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/22 13:36
   */
  public void ins() throws IOException {
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    PdtColorDAO.InsColor dl = new PdtColorDAO.InsColor();
    dl.setB(getBean());
    dl.commit();
    write();
  }

  /**
   * 修改产品颜色
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/22 13:36
   */
  public void upd() throws IOException {
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    PdtColorDAO.UpdColor upd = new PdtColorDAO.UpdColor();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /**
   * 删除产品颜色
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/22 13:38
   */
  public void delete() throws IOException {
    PdtColorDAO.DelColor remove = new PdtColorDAO.DelColor();
    remove.setBKey(getBean().getPkey());
    remove.commit();
    write();
  }
}
