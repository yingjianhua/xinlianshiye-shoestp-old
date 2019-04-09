/** */
package irille.temporary.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import irille.homeAction.HomeAction;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.ipUtils.City;
import irille.temporary.dao.PageHitsDao;
import irille.temporary.entity.PageHits;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/** @author liyichao */
@Slf4j
public class PageHitsAction extends HomeAction<PageHits> {

  @Getter @Setter private String pageName;
  @Getter @Setter private String element;

  private static final String LOCAL = "0:0:0:0:0:0:0:1";

  private static final String LAN = "局域网";

  private static final String CHINA = "中国";

  private PageHitsDao hitsDao = new PageHitsDao();

  public void click() throws IOException {
    if (isBlank(getPageName())) {
      throw new WebMessageException(ReturnCode.failure, "非法参数");
    }
    if (isBlank(getElement())) {
      throw new WebMessageException(ReturnCode.failure, "非法参数");
    }
    HttpServletRequest request = ServletActionContext.getRequest();
    String ip = request.getRemoteAddr();
    if (LOCAL.equals(ip)) {
      write();
    } else {
      String[] areas = City.find(ip);
      if (null != areas && areas.length > 0) {
        String country = areas[0];
        log.info(country + "来访,ip地址为" + ip);
        if (!LAN.equals(country) && !CHINA.equals(country)) {
          hitsDao.click(getPageName(), getElement());
          write();
        } else {
          write();
        }
      } else {
        write();
      }
    }
  }

  private boolean isBlank(String str) {
    return null == str || (null != str && "".equals(str.trim()));
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

  public static void main(String[] args) {
    System.out.println(City.find("192.168.100.46")[0]);
  }
}
