package irille.pub.util.FormaterSql;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import irille.homeAction.HomeAction;
import irille.pub.Str;
import irille.pub.idu.IduPage;
import irille.pub.tb.Fld;
import irille.pub.tb.FldLanguage;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.IEnumOpt;
import irille.pub.util.SEOUtils;
import irille.pub.util.TranslateLanguage.translateUtil;

import static irille.pub.util.FormaterSql.FormaterSql_TypeBean.*;

/**
 * 根据IEnumFld字段生成sql语句 Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/7/12 Time: 14:48
 */
public class FormaterSql {
  private static final Logger logger = LoggerFactory.getLogger(FormaterSql.class);

  private FormaterSqlBean formaterSqlBean;
  private ConcurrentHashMap<String, Integer> map;
  private FormaterSql_TypeBean type;
  private List<Serializable> parmList;

  public FormaterSql setDebug(boolean debug) {
    return this;
  }

  private FormaterSql() {
    formaterSqlBean = new FormaterSqlBean();
    map = new ConcurrentHashMap<>();
    this.type = SELECT;
    this.parmList = new ArrayList();
  }

  private String getName(IEnumFld fld) {
    return fld.getFld().getName();
  }

  private String getSqlfld(IEnumFld fld) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(getTableName(fld)).append(".");
    buffer.append(fld.getFld().getCodeSqlField());
    return buffer.toString();
  }

  private String getSqlfldNotTableName(IEnumFld fld) {
    return fld.getFld().getCodeSqlField();
  }

  public static String getTableName(IEnumFld fld) {
    return Str.tranFieldToLineLower(fld.getFld().getTb().getCode());
  }

  /**
   * * 设置查询的字段
   *
   * @param iEnumFlds
   * @return
   */
  public FormaterSql select(IEnumFld... iEnumFlds) {
    for (IEnumFld iEnumFld : iEnumFlds) {
      String tableName = getTableName(iEnumFld);
      Integer i = map.get(tableName);
      FormaterSqlFldBean fldBean = new FormaterSqlFldBean();
      fldBean.setFullName(getSqlfld(iEnumFld));
      fldBean.setFldName(getSqlfldNotTableName(iEnumFld));
      fldBean.setMultiLanguageFld(iEnumFld.getFld() instanceof FldLanguage);
      formaterSqlBean.getFldlist().add(fldBean);
      if (i != null) {
        i++;
        map.put(tableName, i);
      } else {
        if (formaterSqlBean.getTableName() == null)
          formaterSqlBean.setTableName(getTableName(iEnumFld));
        i = 1;
        map.put(tableName, i);
      }
    }
    return this;
  }

  public FormaterSql selectAs(IEnumFld iEnumFlds, String name) {
    String tableName = getTableName(iEnumFlds);
    Integer i = map.get(tableName);
    List<FormaterSqlFldBean> l = formaterSqlBean.getFldlist();
    FormaterSqlFldBean fldBean = null;
    int index = -1;
    if ((index = l.indexOf(getSqlfld(iEnumFlds))) != -1) {
      fldBean = l.get(index);
    } else {
      fldBean = new FormaterSqlFldBean();
      fldBean.setFullName(getSqlfld(iEnumFlds));
      fldBean.setFldName(getSqlfldNotTableName(iEnumFlds));
      fldBean.setMultiLanguageFld(iEnumFlds instanceof FldLanguage);
    }
    fldBean.setAlias(name);
    l.add(fldBean);
    return this;
  }

  public FormaterSql update(IEnumFld... iEnumFlds) {
    type = FormaterSql_TypeBean.UPDATE;
    return select(iEnumFlds);
  }

  /**
   * * 指定表明
   *
   * @param tableName
   * @return
   */
  public FormaterSql from(IEnumFld tableName) {
    formaterSqlBean.setTableName(getTableName(tableName));
    return this;
  }

  public FormaterSql limit(int i) {
    formaterSqlBean.setShowItem(i);
    return this;
  }

  public FormaterSql offset(int i) {
    formaterSqlBean.setOffset(i);
    return this;
  }

  /**
   * * 不制定表名的情况下，根据出现最多表的为主表
   *
   * @return
   */
  public FormaterSql from() {
    String tableName = null;
    int i = -1;
    for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
      if (i < stringIntegerEntry.getValue()) {
        i = stringIntegerEntry.getValue();
        tableName = stringIntegerEntry.getKey();
      }
    }
    formaterSqlBean.setTableName(tableName);
    return this;
  }

  /**
   * * 第一个为查询的字段，第二个为本表中的关联的字段 即目标表 本表
   *
   * @param outkey
   * @param fld
   * @return
   */
  public FormaterSql leftjoin(IEnumFld outkey, IEnumFld fld) {
    FormaterSql_OutKeyBean sqlOutKeyBean = new FormaterSql_OutKeyBean();
    sqlOutKeyBean.setTable(getTableName(outkey));
    sqlOutKeyBean.setFld(getSqlfld(fld));
    sqlOutKeyBean.setOutKey(outkey.getFld().getTb().get(0).getCodeSqlField());
    sqlOutKeyBean.setType(FormaterSql_TypeBean.LeftJoin);
    formaterSqlBean.getOn().add(sqlOutKeyBean);
    return this;
  }

  /**
   * 参数 目标表，目标表中字段 本表关联字段 @Description: 参数 目标表，目标表中字段 本表关联字段
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/1 10:27
   */
  public FormaterSql leftjoin(IEnumFld table, IEnumFld outkey, IEnumFld fld) {
    FormaterSql_OutKeyBean sqlOutKeyBean = new FormaterSql_OutKeyBean();
    sqlOutKeyBean.setTable(getTableName(table));
    sqlOutKeyBean.setFld(getSqlfld(fld));
    sqlOutKeyBean.setOutKey(getSqlfldNotTableName(outkey));
    sqlOutKeyBean.setType(FormaterSql_TypeBean.LeftJoin);
    formaterSqlBean.getOn().add(sqlOutKeyBean);
    return this;
  }

  public static FormaterSql build() {
    return new FormaterSql();
  }

  public static FormaterSql build(boolean debug) {
    return new FormaterSql().setDebug(debug);
  }

  public static FormaterSql build(Object debug) {
    return new FormaterSql();
  }

  /**
   * 基类中已有count()和countWhere() 方法不需要另外查询
   *
   * @author lijie@shoestp.cn @Description: *基类中已有count()和countWhere() 方法不需要另外查询
   * @date 2018/8/1 15:01
   */
  public String buildCountSql() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SELECT COUNT(1) ").append(" FROM ").append(formaterSqlBean.getTableName());
    if (formaterSqlBean.getWhere().length() > 0) {
      if (formaterSqlBean.getOn().size() > 0) {
        for (FormaterSql_OutKeyBean s : formaterSqlBean.getOn()) {
          stringBuffer
              .append(s.getType().toString())
              .append(s.getTable())
              .append(" on ")
              .append(s.getFld())
              .append("=")
              .append(s.getTable())
              .append(".")
              .append(s.getOutKey())
              .append(" ");
        }
      }
      stringBuffer.append(" WHERE ").append(formaterSqlBean.getWhere().toString());
    }
    outSqlToConsole(stringBuffer);
    return stringBuffer.toString();
  }

  /**
   * 设置类型
   *
   * @author lijie@shoestp.cn @Description:
   * @date 2018/8/8 11:39
   */
  public FormaterSql setType(FormaterSql_TypeBean type) {
    this.type = type;
    return this;
  }

  /**
   * * 输出Sql字符串
   *
   * @return
   */
  public String buildSql() {
    StringBuffer sqlStringBuffer = new StringBuffer();
    do {
      if (type == SELECT) {
        sqlStringBuffer.append("SELECT ");
        int length = sqlStringBuffer.length();
        for (FormaterSqlFldBean s : formaterSqlBean.getFldlist()) {
          if (sqlStringBuffer.length() > length) {
            sqlStringBuffer.append(",");
          }
          sqlStringBuffer.append(s.getFullName());
          if (s.getAlias() != null) {
            sqlStringBuffer.append(" AS ").append(s.getAlias());
          }
        }
        if (sqlStringBuffer.length() <= length) {
          sqlStringBuffer.append("* ");
        }
        sqlStringBuffer.append(" FROM ").append(formaterSqlBean.getTableName());
        if (formaterSqlBean.getOn().size() > 0) {
          for (FormaterSql_OutKeyBean s : formaterSqlBean.getOn()) {
            sqlStringBuffer
                .append(s.getType().toString())
                .append(s.getTable())
                .append(" on ")
                .append(s.getFld())
                .append("=")
                .append(s.getTable())
                .append(".")
                .append(s.getOutKey())
                .append(" ");
          }
        }
        break;
      }
      if (type == UPDATE) {
        sqlStringBuffer
            .append(UPDATE.toString())
            .append(formaterSqlBean.getTableName())
            .append(" Set ");
        int length = sqlStringBuffer.length();
        for (FormaterSqlFldBean s : formaterSqlBean.getFldlist()) {
          if (sqlStringBuffer.length() > length) {
            sqlStringBuffer.append(",");
          }
          sqlStringBuffer.append(s.getFullName()).append("=?");
        }

        ;
      }
    } while (false);

    if (formaterSqlBean.getWhere().length() > 0) {
      sqlStringBuffer.append(" WHERE ").append(formaterSqlBean.getWhere().toString());
    }
    if (formaterSqlBean.getOrderFld().size() > 0) {
      sqlStringBuffer.append(" ORDER BY ");
      int i = sqlStringBuffer.length();
      for (String s : formaterSqlBean.getOrderFld()) {
        if (sqlStringBuffer.length() > i) {
          sqlStringBuffer.append(",");
        }
        sqlStringBuffer.append(s);
      }
      sqlStringBuffer.append(" ").append(formaterSqlBean.getOrder());
    }
    if (formaterSqlBean.getShowItem() != 0) {
      sqlStringBuffer
          .append(" limit ")
          .append(formaterSqlBean.getOffset())
          .append(",")
          .append(formaterSqlBean.getShowItem());
    }
    outSqlToConsole(sqlStringBuffer);
    return sqlStringBuffer.toString();
  }

  private void outSqlToConsole(StringBuffer stringBuffer) {
    Throwable t = new Throwable();
    StackTraceElement stack[] = t.getStackTrace();
    if (stack.length > 3) {
      logger.debug(String.format("[ %s ] %s:\t %s \r\n ", stack[2], new Date(), stringBuffer));
    } else {
      logger.debug(String.format(" %s:\t %s \r\n ", new Date(), stringBuffer));
    }
  }

  public Serializable[] getParms(List list) {

    for (int i = 0; i < list.size(); i++) {
      logger.debug(String.format("Params:    Index:%d   Value:%s \r\n", i, list.get(i)));
    }
    return (Serializable[]) list.toArray(new Serializable[] {});
  }

  public Serializable[] getParms() {
    for (int i = 0; i < parmList.size(); i++) {
      logger.debug(String.format("Params:    Index:%d   Value:%s \r\n", i, parmList.get(i)));
    }
    return parmList.toArray(new Serializable[] {});
  }

  public Serializable[] getParms(Serializable... serializables) {
    for (int i = 0; i < serializables.length; i++) {
      logger.debug(String.format("Index:%d   Value:%s \r\n", i, serializables[i]));
    }
    return serializables;
  }

  public FormaterSql and() {
    formaterSqlBean.getWhere().append(" AND ");
    return this;
  }

  public FormaterSql eq(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("=? ");
    return this;
  }

  public FormaterSql eqAutoAnd(String sql) {
    if (sql == null) {
      return this;
    }
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(sql);
    return this;
  }

  /**
   * @Description: 实验性质
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/10 11:29
   */
  public FormaterSql eqAutoAnd(
      IEnumFld fld, Number serializable, Function<Number, Boolean> function) {
    if (function.apply(serializable)) {
      return eqAutoAnd(fld, serializable);
    }
    return this;
  }

  public FormaterSql eqAutoAnd(IEnumFld fld, Serializable serializable) {
    if (serializable == null) {
      return this;
    }
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("=? ");
    if (serializable instanceof IEnumOpt) {
      parmList.add(((IEnumOpt) serializable).getLine().getKey());

    } else {
      parmList.add(serializable);
    }
    return this;
  }

  public FormaterSql eqAutoAnds(IEnumFld fld, IEnumOpt serializable) {
    if (serializable == null) {
      return this;
    }
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("=? ");
    parmList.add(serializable.getLine().getKey());
    return this;
  }

  public FormaterSql eqAutoAnd(IEnumFld fld) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("=? ");
    return this;
  }

  public FormaterSql neq(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("<>? ");
    return this;
  }

  public FormaterSql eq(Fld fld) {
    formaterSqlBean.getWhere().append(fld.getCodeSqlField()).append("=? ");
    return this;
  }

  public FormaterSql in(IEnumFld fld, int number) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" in ( ");
    int ii = formaterSqlBean.getWhere().length();
    for (int i = 0; i < number; i++) {
      if (ii < formaterSqlBean.getWhere().length()) {
        formaterSqlBean.getWhere().append(",");
      }
      formaterSqlBean.getWhere().append("?");
    }

    formaterSqlBean.getWhere().append(") ");
    return this;
  }

  public FormaterSql in(IEnumFld fld, List list, Function<List, Boolean> function) {
    if (function.apply(list)) {
      if (formaterSqlBean.getWhere().length() > 0) {
        formaterSqlBean.getWhere().append(" AND ");
      }
      formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" in ( ");
      int ii = formaterSqlBean.getWhere().length();
      for (int i = 0; i < list.size(); i++) {
        if (ii < formaterSqlBean.getWhere().length()) {
          formaterSqlBean.getWhere().append(",");
        }
        formaterSqlBean.getWhere().append("?");
      }

      formaterSqlBean.getWhere().append(") ");
      parmList.addAll(list);
    }
    return this;
  }

  public FormaterSql is(Fld fld) {
    formaterSqlBean.getWhere().append(fld.getCodeSqlField()).append("is ? ");
    return this;
  }

  public FormaterSql isNull(Fld fld) {
    formaterSqlBean.getWhere().append(fld.getCodeSqlField()).append("is null ");
    return this;
  }

  public FormaterSql like(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" like ? ");
    return this;
  }

  public FormaterSql like(IEnumFld fld, String s, Function<String, Boolean> function) {
    if (function.apply(s)) {
      formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" like ? ");
      parmList.add(s);
    }
    return this;
  }

  public FormaterSql like(IEnumFld fld, Number number, Function<Number, Boolean> function) {
    if (function.apply(number)) {
      formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" like ? ");
      parmList.add(number);
    }
    return this;
  }

  public FormaterSql or() {
    formaterSqlBean.getWhere().append(" OR ");
    return this;
  }

  /**
   * * 小于
   *
   * @param fld
   * @return
   */
  public FormaterSql lt(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("< ? ");
    return this;
  }

  /**
   * * 小于
   *
   * @param fld
   * @return
   */
  public FormaterSql ltAutoAnd(IEnumFld fld) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("< ? ");
    return this;
  }

  /**
   * * 大于
   *
   * @param fld
   * @return
   */
  public FormaterSql gt(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" > ? ");
    return this;
  }

  public FormaterSql gtAutoAnd(IEnumFld fld) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" > ? ");
    return this;
  }

  public FormaterSql gt(IEnumFld fld, Serializable serializable) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" > ? ");
    parmList.add(serializable);
    return this;
  }

  public FormaterSql desc(IEnumFld fld) {
    formaterSqlBean.setOrder(DESC);
    formaterSqlBean.getOrderFld().add(getSqlfld(fld));
    return this;
  }

  public FormaterSql asc(IEnumFld fld) {
    formaterSqlBean.setOrder(ASC);
    formaterSqlBean.getOrderFld().add(getSqlfld(fld));
    return this;
  }

  public FormaterSql clean() {
    formaterSqlBean = new FormaterSqlBean();
    map.clear();
    parmList.clear();
    return this;
  }

  public FormaterSql cleanWhere() {
    formaterSqlBean.getWhere().setLength(0);
    return this;
  }

  /**
   * * 临时所用，确保类型安全。 转换第一个对象为Long 但是不应写在这里，不符合单一原则。
   *
   * @param o
   * @return
   */
  public static Long castLong(Object[] o) {
    if (o != null) {
      for (Object o1 : o) {
        if (o1 != null) {
          if (o1 instanceof Long || o1 instanceof Integer) {
            return Long.valueOf(String.valueOf(o1));
          }
          if (o1 instanceof BigDecimal) {
            return ((BigDecimal) o1).longValue();
          }
          if (o1 instanceof Number) {
            System.out.println("数字类型");
            System.out.println(o1.getClass().getName());
            return (Long) o1;
          }
        }
      }
    }
    return 0L;
  }

  public static int castInt(Object[] o) {
    if (o != null) {
      for (Object o1 : o) {
        if (o1 != null) {
          if (o1 instanceof Long || o1 instanceof Integer) {
            return Integer.valueOf(String.valueOf(o1));
          }
          if (o1 instanceof BigDecimal) {
            return ((BigDecimal) o1).intValue();
          }
          if (o1 instanceof Number) {
            System.out.println("数字类型");
            System.out.println(o1.getClass().getName());
            return (int) o1;
          }
        }
      }
    }
    return 0;
  }

  /**
   * * 临时所用，确保类型安全。 转换第一个对象为Long 但是不应写在这里，不符合单一原则。
   *
   * @param o
   * @return
   */
  public static String castString(Object[] o) {
    if (o != null) {
      for (Object o1 : o) {
        if (o1 != null) {
          if (o1 instanceof String) {
            return String.valueOf(o1);
          }
        }
      }
    }
    return null;
  }

  /**
   * * 临时所用，转换为List Map 方便jsp迭代 放入Dto更优,但Dto赋值模块 待测试 但是不应写在这里，不符合单一原则。
   *
   * @return
   */
  public List<Map> castListMap(List<Object[]> objects) {
    return castListMap(objects, HomeAction.curLanguage());
  }

  public List<Map> castListMap(List<Object[]> objects, FldLanguage.Language language) {
    List<Map> list = new ArrayList<>();
    if (objects == null) {
      return list;
    }
    List<FormaterSqlFldBean> fldlist = formaterSqlBean.getFldlist();
    for (Object[] obj : objects) {
      if (obj.length == fldlist.size()) {
        Map map = new HashMap();
        for (int i = 0; i < obj.length; i++) {
          FormaterSqlFldBean fldBean = fldlist.get(i);
          Object setvalue = null;
          setvalue =
              fldBean.isMultiLanguageFld() ? translateUtil.getLanguage(obj[i], language) : obj[i];
          map.put(fldBean.getAlias() == null ? fldBean.getFldName() : fldBean.getAlias(), setvalue);
        }
        list.add(map);
      }
    }
    return list;
  }

  public FormaterSql isNull(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" is null ");
    return this;
  }

  public FormaterSql notNull(IEnumFld fld) {
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(" is not null ");
    return this;
  }

  /**
   * * 分页， start为页数 从1开始，ShowItem为显示条数
   *
   * @author lijie@shoestp.cn
   * @param
   * @return
   * @date 2018/7/23 18:57
   */
  public FormaterSql page(int start, int ShowItem) {
    if (ShowItem > 0) {
      formaterSqlBean.setShowItem(ShowItem);
    } else {
      formaterSqlBean.setShowItem(20);
    }

    if (start > 0) {
      formaterSqlBean.setOffset((start - 1) * ShowItem);
    } else {
      // 防止个别代码是从0开始的。从而导致负数
      formaterSqlBean.setOffset(start * ShowItem);
    }
    return this;
  }

  public FormaterSql page(IduPage page) {
    if (page == null) {
      return this;
    }
    return page(page.getStart(), page.getLimit());
  }

  public String toWhereString() {
    String result = formaterSqlBean.getWhere().toString();
    return result;
  }

  public Map castMap(Object[] obj) {
    List<FormaterSqlFldBean> fldlist = formaterSqlBean.getFldlist();
    if (obj == null) {
      return null;
    }
    if (obj.length != fldlist.size()) {
      return null;
    }
    Map map = new HashMap();
    for (int i = 0; i < obj.length; i++) {
      if (obj[i] != null) {
        FormaterSqlFldBean fldBean = fldlist.get(i);
        Object setvalue = null;
        setvalue =
            fldBean.isMultiLanguageFld()
                ? translateUtil.getLanguage(obj[i], HomeAction.curLanguage())
                : obj[i];
        map.put(fldBean.getAlias() == null ? fldBean.getFldName() : fldBean.getAlias(), setvalue);
      }
    }
    return map;
  }

  /**
   * @param 对传入对象顺序有严格限制 第二个必须是商品名称
   * @return @Description:
   * @date 2018/10/4 13:39
   * @author lijie@shoestp.cn
   */
  public Map castMapAddFld(Object[] obj) {
    Map map = castMap(obj);
    map.put(
        "rewrite",
        SEOUtils.getPdtProductTitle(
            Integer.parseInt(String.valueOf(map.get("pkey"))), String.valueOf(obj[1])));
    return map;
  }

  public FormaterSql gteqAutoAnd(IEnumFld fld) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(">= ? ");
    return this;
  }

  public FormaterSql lteqAutoAnd(IEnumFld fld) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("<= ? ");
    return this;
  }

  public FormaterSql Andwhere(String s) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(s).append(" ");
    return this;
  }

  public FormaterSql gtAutoAnd(IEnumFld fld, int parm) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append(">= ? ");
    parmList.add(parm);
    return this;
  }

  public FormaterSql neq(IEnumFld fld, IEnumOpt gather) {
    if (formaterSqlBean.getWhere().length() > 0) {
      formaterSqlBean.getWhere().append(" AND ");
    }
    formaterSqlBean.getWhere().append(getSqlfld(fld)).append("!= ? ");
    parmList.add(gather.getLine().getKey());
    return this;
  }
}
