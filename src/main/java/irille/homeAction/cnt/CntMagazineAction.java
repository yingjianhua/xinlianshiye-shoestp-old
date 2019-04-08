package irille.homeAction.cnt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import irille.homeAction.HomeAction;
import irille.pub.bean.BeanBase;
import irille.shop.cnt.CntMagazine;

public class CntMagazineAction extends HomeAction<CntMagazine> {
  private Map<String, Object> map = new HashMap<>();

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }

  /**
   * 杂志列表
   *
   * @author chen
   */
  public String listShow() {
    String sql = "1=1 order by " + CntMagazine.T.CYCLE.getFld().getCode() + " desc";
    List<CntMagazine> list = BeanBase.list(CntMagazine.class, sql, false);
    if (list == null) {
      return ERR404;
    }
    map.put("list", list);
    setResult("/home/magazine.jsp");
    return TRENDS;
  }

  /**
   * 杂志自适应PC wap
   *
   * @return
   */
  private String contenturl;

  public String getContenturl() {
    return contenturl;
  }

  public void setContenturl(String contenturl) {
    this.contenturl = contenturl;
  }

  public String content() {
    System.out.println(contenturl);
    setResult("/home/magazine/" + contenturl);
    return TRENDS;
  }
}
