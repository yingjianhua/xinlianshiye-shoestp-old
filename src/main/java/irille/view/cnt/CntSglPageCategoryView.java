package irille.view.cnt;

import java.util.List;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.cnt.CntSglPageCategory;
import irille.view.BaseView;
import org.json.JSONException;

public class CntSglPageCategoryView implements BaseView {

  private Integer id;
  private String name;
  private List<CntSglPageView> pages;

  public static CntSglPageCategoryView build(
      CntSglPageCategory bean, List<CntSglPageView> pages, Language lang) throws JSONException {
    CntSglPageCategoryView view = new CntSglPageCategoryView();
    view.setId(bean.getPkey());
    view.setName(bean.getPageName(lang));
    view.setPages(pages);
    return view;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CntSglPageView> getPages() {
    return pages;
  }

  public void setPages(List<CntSglPageView> pages) {
    this.pages = pages;
  }
}
