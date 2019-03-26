package irille.sellerAction.usr;

import java.io.IOException;

import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.inf.IUsrSupplierCategoryAction;
import irille.shop.usr.UsrSupplierCategory;
import irille.shop.usr.UsrSupplierCategoryDAO;

public class UsrSupplierCategoryAction extends SellerAction<UsrSupplierCategory>
    implements IUsrSupplierCategoryAction {
  public void listTop() throws IOException {
    write(UsrSupplierCategoryDAO.listView());
  }

  public void loadlist() throws IOException {
    write(UsrSupplierCategoryDAO.listViews());
  }
}
