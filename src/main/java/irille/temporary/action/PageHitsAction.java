/** */
package irille.temporary.action;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import irille.homeAction.HomeAction;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.temporary.dao.PageHitsDao;
import irille.temporary.entity.PageHits;
import lombok.Getter;
import lombok.Setter;

/** @author liyichao */
public class PageHitsAction extends HomeAction<PageHits> {

  @Getter @Setter private String pageName;
  @Getter @Setter private String element;

  private PageHitsDao hitsDao = new PageHitsDao();

  public void click() throws IOException {
    if (null == getPageName() || (null != getPageName() && "".equals(getPageName().trim()))) {
      throw new WebMessageException(ReturnCode.failure, "非法参数");
    }
    if (null == getElement() || (null != getElement() && "".equals(getElement().trim()))) {
      throw new WebMessageException(ReturnCode.failure, "非法参数");
    }
    hitsDao.click(getPageName(), getElement());
    write();
  }

  public void statistics() throws Exception {
    JSONArray pageHits = hitsDao.findAllByGroupPage();
    JSONArray elementHits = hitsDao.findAllByGroupElement();
    JSONArray timeHits = hitsDao.findAllByGroupTime();
    JSONArray elementTimeHits = hitsDao.findAllByGroupElementAndTime();
    JSONArray pageTimeHits = hitsDao.findAllByGroupPageAndTime();
    JSONObject json = new JSONObject();
    json.put("页面点击量", pageHits);
    json.put("元素点击量", elementHits);
    json.put("时间点击量", timeHits);
    json.put("元素时间点击量", elementTimeHits);
    json.put("页面时间点击量", pageTimeHits);
    writerOrExport(json);
  }
}
