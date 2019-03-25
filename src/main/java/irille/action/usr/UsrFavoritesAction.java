package irille.action.usr;

import java.util.List;

import irille.action.MgtAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.shop.usr.UsrFavorites;
import org.json.JSONArray;
import org.json.JSONObject;

public class UsrFavoritesAction extends MgtAction<UsrFavorites> {

  public UsrFavorites getBean() {
    return _bean;
  }

  public void setBean(UsrFavorites bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return UsrFavorites.class;
  }

  @Override
  public void list() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    // 目前过滤器的搜索，是肯定会带初始条件的
    String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    page.setWhere(where);
    page.commit();
    List<UsrFavorites> UsrFavoriteslist = page.getList();
    JSONObject lineJson = null;
    for (UsrFavorites UsrFavoritesline : UsrFavoriteslist) {
      /*lineJson.put(PdtProduct.T.NAME.getFld().getCode(), UsrFavoritesline.gtProduct().getName());
      lineJson.put(PdtProduct.T.CUR_PRICE.getFld().getCode(),UsrFavoritesline.gtProduct().getCurPrice());
      lineJson.put(PdtProduct.T.PICTURE.getFld().getCode(), UsrFavoritesline.gtProduct().getPicture());*/
      lineJson = crtJsonByBean(UsrFavoritesline);
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
  }
}
