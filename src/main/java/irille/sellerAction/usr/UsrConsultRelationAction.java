package irille.sellerAction.usr;

import java.io.IOException;

import irille.sellerAction.SellerAction;
import irille.sellerAction.usr.inf.IUsrConsultRelationAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrConsultRelation;
import irille.shop.usr.UsrConsultRelationDAO;
import org.json.JSONException;

public class UsrConsultRelationAction extends SellerAction<UsrConsultRelation>
    implements IUsrConsultRelationAction {

  private static final long serialVersionUID = 4890609519448906930L;

  private String countryName;
  private String title;

  /**
   * 专属询盘列表(分页) 根据国家和标题查询
   *
   * @throws IOException
   * @throws JSONException
   * @author yingjianhua
   */
  @Override
  public void page() throws IOException, JSONException {
    if (getSupplier() != null) {
      write(
          UsrConsultRelationDAO.pagePrivate(
              getStart(),
              getLimit(),
              getSupplier().getPkey(),
              getCountryName(),
              getTitle(),
              PltConfigDAO.supplierLanguage(getSupplier())));
    } else {
      writeTimeout();
    }
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
