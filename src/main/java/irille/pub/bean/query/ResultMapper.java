package irille.pub.bean.query;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.WeakHashMap;

import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.Fld;
import irille.pub.tb.Tb;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultMapper {
	
  private static final void propertySet(Bean<?, ?> bean, Fld<?> fld, Object val) {
    try {
      fld.getSetMethod().invoke(bean, val);
    } catch (Exception e) {
    	log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
    	throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }

  @SuppressWarnings("rawtypes")
  private static final <T extends Bean> T fromResultSet(ResultSet rs, Class<T> beanClass) {
    T bean = BeanBase.newInstance(beanClass);
    Fld[] flds = Tb.getTBByBean(beanClass).getFlds();
    try {
      for (Fld fld : flds) {
        try {
          if (rs.getObject(fld.getCodeSqlField()) != null)
            propertySet(bean, fld, fld.getResult(rs, fld.getCodeSqlField()));
        } catch (SQLException e) {
        }
      }
      return bean;
    } catch (Exception e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }

  public static <T extends Bean<?, ?>> T asBean(ResultSet rs, Class<T> beanClass) {
    try {
      if (rs.next()) return fromResultSet(rs, beanClass);
      return null;
    } catch (Exception e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }

  public static <T extends Bean<?, ?>> List<T> asBeanList(ResultSet rs, Class<T> beanClass) {
    try {
      Vector<T> list = new Vector<T>();
      while (rs.next()) {
        list.add(fromResultSet(rs, beanClass));
      }
      return list;
    } catch (SQLException e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }

  public static <T extends Object> List<T> asObjects(ResultSet rs, Class<T> resultClass) {
    try {
      Vector<T> list = new Vector<T>();
      while (rs.next()) {
        list.add((T) rs.getObject(1));
      }
      return list;
    } catch (Exception e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }

  public static <T extends Object> T asObject(ResultSet rs, Class<T> resultClass) {
    try {
      if (rs.next()) {
        switch (resultClass.getName()) {
          case "java.lang.Integer":
            return (T) (Integer) (rs.getInt(1));
          default:
            return (T) rs.getObject(1);
        }
      }
    } catch (Exception e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
    return null;
  }

  public static Map<String, Object> asMap(ResultSet rs) {
    try {
      Map<String, Object> map = new WeakHashMap<>();
      //		System.out.println("column length:"+l);
      //		System.out.println("
      // |catalogName|tableName|schemaName|columnClassName|columnLabel|scale|columnDisplaySize|precision|columnType|columnTypeName|columnName|columnValue|"
      //				+ "javaType|");
      //		java.sql.Types
      ResultSetMetaData md = rs.getMetaData();
      int l = md.getColumnCount();
      if (rs.next()) {
        for (int i = 0; i < l; i++) {
          String key = md.getColumnLabel(i + 1);
          Object value = null;
          /** 没有根据数据库里的实际类型来取值,会导致某些类型的出现偏差,如表里为short类型,数据库为tinyint,但取出来的值为integer 还有一些类型没有做转换 */
          switch (md.getColumnType(i + 1)) {
            case Types.TINYINT:
              value = rs.getByte(i + 1);
              break;
            case Types.SMALLINT:
              value = rs.getShort(i + 1);
              break;
            default:
              value = rs.getObject(i + 1);
          }
          //				System.out.println((i+1)+""
          //						+" | "+md.getCatalogName(i+1)
          //						+" | "+md.getTableName(i+1)
          //						+" | "+md.getSchemaName(i+1)
          //						+" | "+md.getColumnClassName(i+1)
          //						+" | "+md.getColumnLabel(i+1)
          //						+" | "+md.getScale(i+1)
          //						+" | "+md.getColumnDisplaySize(i+1)
          //						+" | "+md.getPrecision(i+1)
          //						+" | "+md.getColumnType(i+1)
          //						+" | "+md.getColumnTypeName(i+1)
          //						+" | "+md.getColumnName(i+1)
          //						+" | "+rs.getObject(i+1)
          //						+" | "+rs.getObject(i+1).getClass().getName()
          //						+" |");
          map.put(key, value);
        }
      }
      return map;
    } catch (Exception e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }

  public static List<Map<String, Object>> asMaps(ResultSet rs) {
    try {
      List<Map<String, Object>> list = new ArrayList<>();
      ResultSetMetaData md = rs.getMetaData();
      int l = md.getColumnCount();
      while (rs.next()) {
        Map<String, Object> map = new WeakHashMap<>();
        for (int i = 0; i < l; i++) {
          String key = md.getColumnLabel(i + 1);
          Object value = null;
          switch (md.getColumnType(i + 1)) {
            case Types.TINYINT:
              value = rs.getByte(i + 1);
              break;
            case Types.SMALLINT:
              value = rs.getShort(i + 1);
              break;
            default:
              value = rs.getObject(i + 1);
          }
          map.put(key, rs.wasNull() ? null : value);
        }
        list.add(map);
      }
      return list;
    } catch (SQLException e) {
      log.warn("数据库记录转换到对象 赋值出错 : {}", e.getMessage());
      throw new WebMessageException(ReturnCode.db_unknow, "service error");
    }
  }
}
