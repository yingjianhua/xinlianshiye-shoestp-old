package irille.sellerAction.usr.dto;

import java.util.ArrayList;
import java.util.List;

import irille.pub.bean.BeanBase;
import irille.sellerAction.SellerAction;
import irille.shop.plt.PltConfigDAO;
import irille.shop.usr.UsrProductCategory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsrProductCategoryView {
  private Integer pkey;
  private List<UsrProductCategoryView> children;
  private String name;
  private Integer enabled;
  private Integer parent;

  public static UsrProductCategoryView build(UsrProductCategory cat) {
    UsrProductCategoryView view = new UsrProductCategoryView();
    try {
      view.setEnabled(Integer.valueOf(cat.getEnabled()));
      view.setName(cat.getName(PltConfigDAO.supplierLanguage(SellerAction.getSupplier())));
      view.setPkey(cat.getPkey());

      List<UsrProductCategory> catList =
          BeanBase.list(
              UsrProductCategory.class,
              UsrProductCategory.T.CATEGORY_UP.getFld().getCodeSqlField() + " = ? ",
              false,
              cat.getPkey());
      if (catList == null) {
        view.setChildren(null);
      } else {
        List<UsrProductCategoryView> catViews = build(catList);
        view.setChildren(catViews);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }

  public static List<UsrProductCategoryView> build(List<UsrProductCategory> catList) {
    List<UsrProductCategoryView> views = new ArrayList<UsrProductCategoryView>();
    for (UsrProductCategory line : catList) {
      views.add(build(line));
    }
    return views;
  }

  public static JSONObject buildCat(UsrProductCategoryView line) throws JSONException {
    JSONObject json = new JSONObject(line);
    if (line.getChildren() instanceof List) {
      JSONArray childCat = buildCat(line.getChildren());
      json.put("children", childCat);
    }
    return json;
  }

  public static JSONArray buildCat(List<UsrProductCategoryView> catList) throws JSONException {
    JSONArray json = new JSONArray();
    for (UsrProductCategoryView line : catList) {
      json.put(buildCat(line));
    }
    return json;
  }

  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }

  public Integer getPkey() {
    return pkey;
  }

  public void setPkey(Integer pkey) {
    this.pkey = pkey;
  }

  public List<UsrProductCategoryView> getChildren() {
    return children;
  }

  public void setChildren(List<UsrProductCategoryView> children) {
    this.children = children;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getEnabled() {
    return enabled;
  }

  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
}
