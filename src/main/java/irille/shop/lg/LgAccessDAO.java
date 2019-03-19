package irille.shop.lg;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import irille.platform.lg.View.LgAccessView;
import irille.platform.lg.View.statisticsView;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.bean.statistics.Table;
import irille.pub.svr.DbPool;
import irille.view.Page;
import irille.view.usr.UserView;
import org.json.JSONArray;
import org.json.JSONObject;

public class LgAccessDAO {

  public static void add(
      UserView user, HttpServletRequest request, Long processTime, boolean isSuccess, Exception e) {
    String remark = null;
    if (e != null) remark = e.toString();
    add(
        getUser(user),
        getLoginName(user),
        request.getRemoteAddr(),
        request.getRequestURL().toString(),
        getParameters(request),
        processTime,
        isSuccess,
        remark);
  }

  public static void add(
      String user,
      String loginName,
      String remoteAddr,
      String requestUrl,
      String params,
      Long processTime,
      boolean isSuccess,
      String remark) {
    LgAccess bean = new LgAccess();
    bean.setUser(user);
    bean.setLoginname(loginName);
    bean.setRemoteaddr(remoteAddr);
    bean.setRequesturl(requestUrl);
    bean.setParams(params);
    bean.setProcesstime(processTime);
    bean.setRequesttime(new Date());
    bean.stSuccess(isSuccess);
    bean.setRemark(remark);
    bean.ins();
  }

  private static String getUser(UserView user) {
    String s = "";
    if (user != null && user.isPurchase()) {
      s += "purchase";
      if (user.isSupplier()) s += "|supplier";
    } else if (user != null && user.isSupplier()) {
      s += "supplier";
    } else {
      s += "anonymous";
    }
    return s;
  }

  private static String getLoginName(UserView user) {
    if (user !=null && user.haveUser()) return user.getLoginName();
    return null;
  }

  private static String getParameters(HttpServletRequest request) {
    StringBuilder params = new StringBuilder();
    Enumeration<?> names = request.getParameterNames();
    boolean first = true;
    while (names.hasMoreElements()) {
      Object element = names.nextElement();
      if (!first) params.append("&");
      params.append(element);
      params.append("=");
      params.append(request.getParameter(element.toString()));
      if (first) first = false;
    }
    return params.toString();
  }

  public static void main(String[] args) {
    avgProcessTime();
    //		countRequest();
    DbPool.getInstance().releaseAll();
  }

  enum Querys {
    avgProcessTime(
        "select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY processtime desc"),
    countRequest(
        "select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY count desc");
    private String sql;

    private Querys(String sql) {
      this.sql = sql;
    }

    public String query() {
      return new Table(sql).toString();
    }
  }

  public static JSONArray listQuerys() {
    JSONArray list = new JSONArray();
    Stream.of(Querys.values())
        .forEach(
            query -> {
              try {
                list.put(new JSONObject().put("name", query.name()));
              } catch (Exception e) {
              }
            });
    return list;
  }

  public static String query(String query) {
    String result = "查询出错";
    try {
      result = Querys.valueOf(query).query();
    } catch (Exception e) {
    }
    return result;
  }

  /**
   * 统计请求的平均用时
   *
   * @author yingjianhua
   */
  public static void avgProcessTime() {
    String sql =
        "select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY processtime desc";
    new Table(sql).print();
  }

  /**
   * 统计请求次数
   *
   * @author yingjianhua
   */
  public static void countRequest() {
    String sql =
        "select requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY count desc";
    new Table(sql).print();
  }

  /** ——————————————————————分割线(新平台)———————————————————————————— */
  public static Page getAccessList(
      Integer start, Integer limit, String user, String loginName, String requestUrl) {
    if (start == null) {
      start = 0;
    }
    if (limit == null) {
      limit = 10;
    }
    SQL sql =
        new SQL() {
          {
            SELECT(LgAccess.class).FROM(LgAccess.class);
            if (user != null) {
              WHERE(LgAccess.T.USER, "like ?","%" + user + "%");
            }
            if (loginName != null) {
              WHERE(LgAccess.T.LOGINNAME, "like ?","%" + loginName + "%");
            }
            if (requestUrl != null) {
              WHERE(LgAccess.T.REQUESTURL, "like ?","%" + requestUrl + "%");
            }
          }
        };
    Integer count = Query.sql(sql).queryCount();
    List<LgAccessView> list =
        Query.sql(sql.LIMIT(start, limit)).queryMaps().stream()
            .map(
                o ->
                    new LgAccessView() {
                      {
                        setId((Integer) o.get(LgAccess.T.PKEY.getFld().getCodeSqlField()));
                        setUser((String) o.get(LgAccess.T.USER.getFld().getCodeSqlField()));
                        setLoginName(
                            (String) o.get(LgAccess.T.LOGINNAME.getFld().getCodeSqlField()));
                        setRemoteAddr(
                            (String) o.get(LgAccess.T.REMOTEADDR.getFld().getCodeSqlField()));
                        setRequestUrl(
                            (String) o.get(LgAccess.T.REQUESTURL.getFld().getCodeSqlField()));
                        setParams((String) o.get(LgAccess.T.PARAMS.getFld().getCodeSqlField()));
                        setSuccess(
                            Byte.valueOf(
                                String.valueOf(
                                    o.get(LgAccess.T.SUCCESS.getFld().getCodeSqlField()))));
                        setRemark((String) o.get(LgAccess.T.REMARK.getFld().getCodeSqlField()));
                        setProcessTime(
                            (Long) o.get(LgAccess.T.PROCESSTIME.getFld().getCodeSqlField()));
                        setRequestTime(
                            (Date) o.get(LgAccess.T.REQUESTTIME.getFld().getCodeSqlField()));
                      }
                    })
            .collect(Collectors.toList());
    return new Page(list, start, limit, count);
  }

  public static List<statisticsView> getStatisticsList(Integer id) {
    SQL sql = new SQL();
    if (id == 1) {
      sql.SELECT(
          "requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY processtime desc");
    } else {
      sql.SELECT(
          "requesturl, AVG(processtime) as processtime, count(1) as count from lg_access GROUP BY requesturl ORDER BY count desc");
    }
    List<statisticsView> list =
        Query.sql(sql).queryMaps().stream()
            .map(
                o ->
                    new statisticsView() {
                      {
                        setRequestUrl((String) o.get("requesturl"));
                        setCount((Long) o.get("count"));
                        setProcessTime((BigDecimal) o.get("processtime"));
                      }
                    })
            .collect(Collectors.toList());
    return list;
  }

  /** ——————————————————————分割线(新平台)END———————————————————————————— */
}
