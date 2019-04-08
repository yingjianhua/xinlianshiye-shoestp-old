/** */
package irille.temporary.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.util.DateTimeUtil;
import irille.pub.util.GetValue;
import irille.temporary.entity.PageHits;

/** @author liyichao */
public class PageHitsDao {

  /**
   * @auther liyichao
   * @param page
   */
  public void click(String page, String element) {
    Date now = DateTimeUtil.getTimesmorning(new Date());
    PageHits p = PageHits.chkUniquePage_element_created_time(false, page, element, now);
    if (null == p) {
      p = new PageHits();
    }
    p.setHits(p.getHits() == null ? 1L : (p.getHits() + 1L));
    p.setPage(page);
    p.setElement(element);
    p.setCreatedTime(DateTimeUtil.getTimesmorning(now));
    p.setRowVersion((short) 1);
    if (null == p.getPkey()) {
      p.ins();
    } else {
      p.upd();
    }
  }

  /**
   * 获得每个页面的点击量
   *
   * @auther liyichao
   * @return
   */
  public JSONArray findAllByGroupPage() {
    SQL sql = new SQL();
    sql.SELECT(PageHits.T.PAGE)
        .SELECT("(SUM(" + PageHits.class.getSimpleName() + "." + PageHits.T.HITS + ")) AS hits")
        .FROM(PageHits.class)
        .GROUP_BY(PageHits.T.PAGE);
    List<Map<String, Object>> result = Query.sql(sql).queryMaps();
    JSONArray array = new JSONArray();
    for (Map<String, Object> m : result) {
      JSONObject data = new JSONObject();
      try {
        data.put("page", GetValue.get(m, PageHits.T.PAGE, String.class, ""));
        data.put("count", GetValue.get(m, "hits", Long.class, 0L));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      array.put(data);
    }
    return array;
  }

  /**
   * 获得每个元素的点击量
   *
   * @auther liyichao
   * @return
   */
  public JSONArray findAllByGroupElement() {
    SQL sql = new SQL();
    sql.SELECT(PageHits.T.ELEMENT)
        .SELECT("(SUM(" + PageHits.class.getSimpleName() + "." + PageHits.T.HITS + ")) AS hits")
        .FROM(PageHits.class)
        .GROUP_BY(PageHits.T.ELEMENT);
    List<Map<String, Object>> result = Query.sql(sql).queryMaps();
    JSONArray array = new JSONArray();
    for (Map<String, Object> m : result) {
      JSONObject data = new JSONObject();
      try {
        data.put("element", GetValue.get(m, PageHits.T.ELEMENT, String.class, ""));
        data.put("count", GetValue.get(m, "hits", Long.class, 0L));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      array.put(data);
    }
    return array;
  }

  /**
   * 获得每日每个元素的点击量
   *
   * @auther liyichao
   * @return
   */
  public JSONArray findAllByGroupPageAndTime() {
    SQL sql = new SQL();
    sql.SELECT(PageHits.T.PAGE)
        .SELECT(PageHits.T.CREATED_TIME)
        .SELECT("(SUM(" + PageHits.class.getSimpleName() + "." + PageHits.T.HITS + ")) AS hits")
        .FROM(PageHits.class)
        .GROUP_BY(PageHits.T.CREATED_TIME)
        .GROUP_BY(PageHits.T.PAGE);
    List<Map<String, Object>> result = Query.sql(sql).queryMaps();
    JSONArray array = new JSONArray();
    for (Map<String, Object> m : result) {
      JSONObject data = new JSONObject();
      try {
        data.put("page", GetValue.get(m, PageHits.T.PAGE, String.class, ""));
        data.put("time", GetValue.get(m, PageHits.T.CREATED_TIME, Date.class, new Date()));
        data.put("count", GetValue.get(m, "hits", Long.class, 0L));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      array.put(data);
    }
    return array;
  }

  /**
   * 获得每日每个元素的点击量
   *
   * @auther liyichao
   * @return
   */
  public JSONArray findAllByGroupElementAndTime() {
    SQL sql = new SQL();
    sql.SELECT(PageHits.T.ELEMENT)
        .SELECT(PageHits.T.CREATED_TIME)
        .SELECT("(SUM(" + PageHits.class.getSimpleName() + "." + PageHits.T.HITS + ")) AS hits")
        .FROM(PageHits.class)
        .GROUP_BY(PageHits.T.CREATED_TIME)
        .GROUP_BY(PageHits.T.ELEMENT);
    List<Map<String, Object>> result = Query.sql(sql).queryMaps();
    JSONArray array = new JSONArray();
    for (Map<String, Object> m : result) {
      JSONObject data = new JSONObject();
      try {
        data.put("element", GetValue.get(m, PageHits.T.ELEMENT, String.class, ""));
        data.put("time", GetValue.get(m, PageHits.T.CREATED_TIME, Date.class, new Date()));
        data.put("count", GetValue.get(m, "hits", Long.class, 0L));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      array.put(data);
    }
    return array;
  }

  /**
   * 获得每日点击量
   *
   * @auther liyichao
   * @return
   */
  public JSONArray findAllByGroupTime() {
    SQL sql = new SQL();
    sql.SELECT(PageHits.T.CREATED_TIME)
        .SELECT("(SUM(" + PageHits.class.getSimpleName() + "." + PageHits.T.HITS + ")) AS hits")
        .FROM(PageHits.class)
        .GROUP_BY(PageHits.T.CREATED_TIME);
    List<Map<String, Object>> result = Query.sql(sql).queryMaps();
    JSONArray array = new JSONArray();
    for (Map<String, Object> m : result) {
      JSONObject data = new JSONObject();
      try {
        data.put("time", GetValue.get(m, PageHits.T.CREATED_TIME, Date.class, new Date()));
        data.put("count", GetValue.get(m, "hits", Long.class, 0L));
      } catch (JSONException e) {
        e.printStackTrace();
      }
      array.put(data);
    }
    return array;
  }
}
