package irille.pub.bean.query;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.svr.DbPool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractQuery {

  protected static final Config config = new Config();

  public static final void config(boolean debug) {
    config.debug = debug;
  }

  static class Config {
    protected boolean debug = false;
  }

  protected abstract Serializable[] getParams();

  protected abstract String getSql();

  protected Boolean needDebug() {
    return config.debug;
  };

  /**
   * @return
   * @author yingjianhua
   */
  protected Map<String, Object> queryMap() {
    return query(rs -> ResultMapper.asMap(rs));
  }

  /**
   * @return
   * @author yingjianhua
   */
  protected List<Map<String, Object>> queryMaps() {
    return query(rs -> ResultMapper.asMaps(rs));
  }

  /** @author yingjianhua */
  protected <T extends Object> T queryObject(Class<T> resultClass) {
    return query(rs -> ResultMapper.asObject(rs, resultClass));
  }

  /** @author yingjianhua */
  protected <T extends Object> List<T> queryObjects(Class<T> resultClass) {
    return query(rs -> ResultMapper.asObjects(rs, resultClass));
  }

  /**
   * 统计记录数
   *
   * @return
   * @author yingjianhua
   */
  protected Integer countRecord() {
    String sql = getSql();
    Matcher matcher =
        Pattern.compile("^(.*)(LIMIT \\s*((\\d)+\\s*,)?\\s*(\\d)+\\s*)$").matcher(sql);
    if (matcher.find()) {
      sql = matcher.group(1);
    }
    //		int s = sql.indexOf(" (LIMIT (\\d|\\s)?,(\\d|\\s))$");
    //		if(s!=-1)
    //			sql = sql.substring(0, s);
    sql = sql.replaceFirst("(select|SELECT)\\s+.*?\\s+(FROM|from)", "SELECT COUNT(1) FROM");
    Integer count =
        query(rs -> ResultMapper.asObject(rs, Integer.class), needDebug(), sql, getParams());
    if (count == null) return 0;
    return count;
  }

  /**
   * 根据字段名将数据注入bean
   *
   * @author yingjianhua
   */
  protected <T extends Bean<?, ?>> List<T> queryBeans(Class<T> beanClass) {
    return query(rs -> ResultMapper.asBeanList(rs, beanClass));
  }

  /**
   * 根据字段名将数据注入bean
   *
   * @author yingjianhua
   */
  protected <T extends Bean<?, ?>> T queryBean(Class<T> beanClass) {
    return query(rs -> ResultMapper.asBean(rs, beanClass));
  }

  /**
   * 执行sql语句
   *
   * @author yingjianhua
   */
  protected int executeUpdate() {
    printSql(getSql(), getParams());
    PreparedStatement stmt = null;
    try {
      stmt = DbPool.getInstance().getConn().prepareStatement(getSql());
      BeanBase.toPreparedStatementData(stmt, 1, getParams());
      return stmt.executeUpdate();
    } catch (Exception e) {
      log.warn("执行【{}】出错!", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    } finally {
      DbPool.close(stmt);
    }
  }

  protected static <R> R query(
      Function<ResultSet, R> f, boolean debug, String sql, Serializable... params) {
    printSql(sql, params);
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DbPool.getInstance().getConn().prepareStatement(sql);
      stmt.setFetchSize(BeanBase.FETCH_SIZE);
      BeanBase.toPreparedStatementData(stmt, 1, params);
      rs = stmt.executeQuery();
      return f.apply(rs);
    } catch (Exception e) {
      log.warn("取数据库记录时出错【{}】!", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    } finally {
      DbPool.close(stmt, rs);
    }
  }

  protected <R> R query(Function<ResultSet, R> f) {
    printSql(getSql(), getParams());
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = DbPool.getInstance().getConn().prepareStatement(getSql());
      stmt.setFetchSize(BeanBase.FETCH_SIZE);
      BeanBase.toPreparedStatementData(stmt, 1, getParams());
      rs = stmt.executeQuery();
      return f.apply(rs);
    } catch (Exception e) {
      log.warn("取数据库记录时出错【{}】!", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    } finally {
      DbPool.close(stmt, rs);
    }
  }

  private static void printSql(String sql, Serializable... params) {
    Optional<StackTraceElement> o =
        Stream.of(new Throwable().getStackTrace())
            .limit(10)
            .filter(st -> st.getClassName().endsWith("DAO") || st.getClassName().contains("DAO$"))
            .findFirst();
    if (o.isPresent()) {
      log.debug("sql:" + sql + "|" + params(params) + "] [stackTrace: " + o.get().toString());
    } else {
      log.debug("sql:" + sql + "|" + params(params));
    }
  }

  private static String params(Serializable... a) {
    if (a == null) return "params:null";

    int iMax = a.length - 1;
    if (iMax == -1) return "params:[]";

    StringBuilder b = new StringBuilder();
    b.append("params:");
    for (int i = 0; ; i++) {
      if (a[i] instanceof String) b.append("\"").append(a[i]).append("\"");
      else b.append(String.valueOf(a[i]));
      if (i == iMax) return b.toString();
      b.append(", ");
    }
  }
}
