package irille.homeAction.usr;

import java.io.IOException;

import irille.homeAction.HomeAction;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierCategoryDAO;

public class UsrSupplierCategoryAction extends HomeAction<UsrSupplierCategory> {

  private static final long serialVersionUID = -6947974871253844187L;

  /**
   * 列表供应商一级分类
   *
   * @author yingjianhua
   */
  // @NeedLogin
  public void listTop() throws IOException {
    write(UsrSupplierCategoryDAO.listViewIsTop(curLanguage()));
  }
}
