package irille.platform.cnt;

import java.io.IOException;

import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.cnt.CntSglPageCategory;
import irille.shop.cnt.CntSglPageCategoryDAO;

public class CntSglPageCategoryAction extends ActionBase<CntSglPageCategory> {
  @Override
  public Class beanClazz() {
    return CntSglPageCategory.class;
  }

  public CntSglPageCategory getBean() {
    return _bean;
  }

  public void setBean(CntSglPageCategory bean) {
    this._bean = bean;
  }

  public void listcategory() throws IOException {
    write(CntSglPageCategoryDAO.listcategory());
  }

  // 平台单页分类设置 插入
  @Override
  public void ins() throws IOException {
    CntSglPageCategoryDAO.InsCategory ic = new CntSglPageCategoryDAO.InsCategory();
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    ic.setB(getBean());
    ic.commit();
    write();
  }

  // 平台单页分类设置 修改
  @Override
  public void upd() throws IOException {
    System.out.println(getBean().getPkey());
    CntSglPageCategoryDAO.UpdCategory ic = new CntSglPageCategoryDAO.UpdCategory();
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    ic.setB(getBean());
    ic.commit();
    write();
  }

  // 平台单页分类删除 级联删除对应单页
  @Override
  public void del() throws IOException {
    System.out.println(getBean().getPkey());
    CntSglPageCategoryDAO.Del ic = new CntSglPageCategoryDAO.Del();
    ic.setBKey(getBean().getPkey());
    ic.commit();
    write();
  }
}
