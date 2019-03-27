package irille.platform.pdt;

import java.io.IOException;

import javax.inject.Inject;

import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtAttrCat;
import irille.shop.pdt.PdtAttrCatDAO;
import lombok.Getter;
import lombok.Setter;

/** Created by admin on 2019/1/17. */
public class PdtAttrCatAction extends ActionBase<PdtAttrCat> {
  @Override
  public Class beanClazz() {
    return PdtAttrCat.class;
  }

  public PdtAttrCat getBean() {
    return _bean;
  }

  public void setBean(PdtAttrCat bean) {
    this._bean = bean;
  }

  @Getter @Setter private String name; // 查询属性分类名称

  @Inject private PdtAttrCatDAO pdtAttrCatDAO;
  @Inject private PdtAttrCatDAO.InsAttrCat ic;
  @Inject private PdtAttrCatDAO.UpdAttrCat updAttrCat;
  /**
   * 查询所有分类
   *
   * @throws IOException
   */
  public void pdtAttrCatlist() throws IOException {
    write(pdtAttrCatDAO.getAttrCat(getStart(), getLimit(), name));
  }

  /** 删除分类 */
  public void del() throws IOException {
    PdtAttrCatDAO.Del cp = new PdtAttrCatDAO.Del();
    cp.setBKey(getBean().getPkey());
    cp.commit();
    write();
  }

  /** 添加分类 */
  @Override
  public void ins() throws IOException {
    LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(loginUserMsg.get_user().getPkey());
    ic.setB(getBean());
    ic.commit();
    write();
  }

  /** 修改分类 */
  @Override
  public void upd() throws IOException {
    LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(loginUserMsg.get_user().getPkey());
    updAttrCat.setB(getBean());
    updAttrCat.commit();
    write();
  }
}
