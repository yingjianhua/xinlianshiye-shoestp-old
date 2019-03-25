package irille.platform.pdt;

import java.io.IOException;

import irille.action.ActionBase;
import irille.action.dataimport.util.StringUtil;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtAttrLineDAO;
import lombok.Data;

/**
 * 属性明细
 *
 * @author lingjian
 * @date 2019/1/23 9:05
 */
@Data
public class PdtAttrLineAction extends ActionBase<PdtAttrLine> {

  @Override
  public Class beanClazz() {
    return PdtAttrLine.class;
  }

  public PdtAttrLine getBean() {
    return _bean;
  }

  public void setBean(PdtAttrLine bean) {
    this._bean = bean;
  }

  private String main; // 搜索的属性明细类目

  /**
   * 查询产品属性列表+搜索
   *
   * @author lingjian
   * @date 2019/1/23 10:30
   */
  public void list() throws Exception {
    write(PdtAttrLineDAO.listAttrLine(main, getStart(), getLimit()));
  }

  /**
   * 新增产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/23 9:17
   */
  public void ins() throws IOException {
    if (!StringUtil.hasValue(getBean().getName()) || getBean().getName().length() > 20) {
      writeErr(-1, "属性名称不能为空,并且不能过长");
      return;
    }
    if (getBean().getMain() == null || getBean().getMain() >= 0) {
      writeErr(-2, "参数错误");
      return;
    }
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    PdtAttrLineDAO.InsAttrLine dl = new PdtAttrLineDAO.InsAttrLine();
    dl.setB(getBean());
    dl.commit();
    write();
  }

  /**
   * 修改产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/23 9:17
   */
  public void upd() throws IOException {
    if (!StringUtil.hasValue(getBean().getName()) || getBean().getName().length() > 20) {
      writeErr(-1, "属性名称不能为空,并且不能过长");
      return;
    }
    if (getBean().getMain() == null || getBean().getMain() >= 0) {
      writeErr(-2, "参数错误");
      return;
    }
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    PdtAttrLineDAO.UpdAttrLine upd = new PdtAttrLineDAO.UpdAttrLine();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /**
   * 删除产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/23 9:17
   */
  public void delete() throws IOException {
    PdtAttrLineDAO.DelAttrLine remove = new PdtAttrLineDAO.DelAttrLine();
    remove.setBKey(getBean().getPkey());
    remove.commit();
    write();
  }
}
