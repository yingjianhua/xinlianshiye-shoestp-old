package irille.Dao.Activity.Romania;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import irille.Entity.Activity.SupGoogleView;
import irille.Entity.NewInquiry.NewInquiry;
import irille.Entity.Pk.PkCompetitionData;
import irille.pub.bean.Query;
import irille.pub.bean.query.BeanQuery;
import irille.pub.bean.sql.SQL;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrConsult;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/3 Time: 11:24 */
public class PkCompetitionDataDao {

  /**
   * @Description: 获取所有Pk大赛数据
   *
   * @date 2018/12/5 13:58
   * @author lijie@shoestp.cn
   */
  public List<PkCompetitionData> getPkCompetitionData(Date startTime, Date endTime) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(PkCompetitionData.class)
        .FROM(PkCompetitionData.class)
        .WHERE(PkCompetitionData.T.CREATEDTIME, ">=?", startTime)
        .WHERE(PkCompetitionData.T.CREATEDTIME, "<?", endTime);
    return query.queryList(PkCompetitionData.class);
  }

  /**
   * @Description: 统计询盘统计量
   *
   * @date 2018/12/5 13:58
   * @author lijie@shoestp.cn
   */
  public Integer getInquiry(Date startDate, Date endDate, int supid) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(UsrConsult.T.PKEY)
        .FROM(UsrConsult.class)
        .LEFT_JOIN(PdtProduct.class, PdtProduct.T.PKEY, UsrConsult.T.PKEY)
        .WHERE(PdtProduct.T.SUPPLIER, "=?", supid);
    if (startDate != null) query.WHERE(UsrConsult.T.CREATE_TIME, ">=?", startDate);
    if (endDate != null) query.WHERE(UsrConsult.T.CREATE_TIME, "<?", endDate);
    int oldInq = query.queryCount();
    query = new BeanQuery();
    query
        .SELECT(NewInquiry.T.PKEY)
        .FROM(NewInquiry.class)
        .WHERE(NewInquiry.T.SUPPLIERID, "=?", supid);
    if (startDate != null) query.WHERE(NewInquiry.T.CREATE_TIME, ">=?", startDate);
    if (endDate != null) query.WHERE(NewInquiry.T.CREATE_TIME, "<?", endDate);
    return query.queryCount() + oldInq;
  }

  /**
   * @Description: 获取上一次获取数据的时间 一天一次
   *
   * @date 2018/12/5 13:58
   * @author lijie@shoestp.cn
   */
  public String getLastDate() {
    SQL query = new SQL();
    query.SELECT("max(createdtime) as lastDate").FROM(PkCompetitionData.class);
    Object resultObject = Query.sql(query).queryMap().get("lastDate");
    if (resultObject == null) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(2018, 11, 1, 00, 00, 00);
      resultObject = calendar.getTime();
    }
    return new SimpleDateFormat("yyyy-MM-dd").format(resultObject);
  }

  /**
   * @Description: 获取供应商的统计数据
   *
   * @date 2018/12/5 13:58
   * @author lijie@shoestp.cn
   */
  public Map<String, Object> getSupPk(Date startDate, Date endDate, Integer supId) {
    SQL query = new SQL();
    query
        .SELECT("sum(pe) as pe,sum(trafficvolume) as tr,sum(inquiry) as inq")
        .FROM(PkCompetitionData.class)
        .WHERE(PkCompetitionData.T.SUPID, "=?", supId);
    if (startDate != null) query.WHERE(PkCompetitionData.T.CREATEDTIME, ">=?", startDate);
    if (endDate != null) query.WHERE(PkCompetitionData.T.CREATEDTIME, "<?", endDate);

    return Query.sql(query).queryMap();
  }

  /**
   * @Description: 获取前5曝光量的数据统计
   *
   * @date 2018/12/5 13:59
   * @author lijie@shoestp.cn
   */
  public Integer getPeTop5Count(Date startDate, Date endDate) {
    SQL sql = new SQL();
    sql.SELECT("pe")
        .FROM(PkCompetitionData.class)
        .ORDER_BY(PkCompetitionData.T.PE, "desc")
        .LIMIT(0, 5);
    if (startDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, ">?", startDate);
    }
    if (endDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, "<=?", endDate);
    }
    int result = 0;
    for (Map<String, Object> queryMap : Query.sql(sql).queryMaps()) {
      result += Integer.valueOf(String.valueOf(queryMap.get("pe")));
    }
    return result;
  }

  /**
   * @Description: 获取前5的数据总和
   *
   * @date 2018/12/5 13:59
   * @author lijie@shoestp.cn
   */
  public Integer getInqTop5Count(Date startDate, Date endDate) {
    SQL sql = new SQL();
    sql.SELECT("sum(inquiry) as count")
        .FROM(PkCompetitionData.class)
        .ORDER_BY(PkCompetitionData.T.PE, "desc")
        .LIMIT(0, 5);
    if (startDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, ">?", startDate);
    }
    if (endDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, "<=?", endDate);
    }
    return Integer.valueOf(String.valueOf(Query.sql(sql).queryMap().get("count")));
  }

  /**
   * @Description: 获取所有的曝光量
   *
   * @date 2018/12/5 13:59
   * @author lijie@shoestp.cn
   */
  public Map getAllPe(Date startDate, Date endDate) {
    SQL sql = new SQL();
    sql.SELECT(
            "sum(pe) as pe,(select count(1) as `count` from (select 1 from pk_competition_data group by supid) temp) as count")
        .FROM(PkCompetitionData.class)
        .ORDER_BY(PkCompetitionData.T.PE, "desc");
    if (startDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, ">?", startDate);
    }
    if (endDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, "<=?", endDate);
    }
    Object result = Query.sql(sql).queryMap().get("pe");
    if (result == null) {
      return null;
    }

    return Query.sql(sql).queryMap();
  }

  /**
   * @Description: 获取所有的询盘
   *
   * @date 2018/12/5 13:59
   * @author lijie@shoestp.cn
   */
  public Map getInqCount(Date startDate, Date endDate) {
    SQL sql = new SQL();
    sql.SELECT(
            "sum(inquiry) as inquiry,(select count(1) as `count` from (select 1 from pk_competition_data group by supid) temp) as count")
        .FROM(PkCompetitionData.class)
        .ORDER_BY(PkCompetitionData.T.PE, "desc");
    if (startDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, ">?", startDate);
    }
    if (endDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, "<=?", endDate);
    }
    return Query.sql(sql).queryMap();
  }

  public List<Map<String, Object>> getSupTraceCode() {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(SupGoogleView.T.VIEWID, "viewId")
        .SELECT(SupGoogleView.T.SUPID, "supId")
        .FROM(SupGoogleView.class);

    return query.queryMaps();
  }

  public String getGoogleViewId(Integer supId) {
    BeanQuery query = new BeanQuery();
    query
        .SELECT(SupGoogleView.T.VIEWID)
        .FROM(SupGoogleView.class)
        .WHERE(SupGoogleView.T.SUPID, "=?", supId);
    Object googleView = query.query(SupGoogleView.class);
    if (googleView != null) {
      return ((SupGoogleView) googleView).getViewid();
    }
    return "";
  }

  public int getTrafficvolumeTop5Count(Date startDate, Date endDate) {
    SQL sql = new SQL();
    sql.SELECT("sum(trafficvolume) as count")
        .FROM(PkCompetitionData.class)
        .ORDER_BY(PkCompetitionData.T.PE, "desc")
        .LIMIT(0, 5);
    if (startDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, ">?", startDate);
    }
    if (endDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, "<=?", endDate);
    }
    return Integer.valueOf(String.valueOf(Query.sql(sql).queryMap().get("count")));
  }

  public Map getTrafficvolumeCount(Date startDate, Date endDate) {
    SQL sql = new SQL();
    sql.SELECT(
            "sum(trafficvolume) as trafficvolume,(select count(1) as `count` from (select 1 from pk_competition_data group by supid) temp) as count")
        .FROM(PkCompetitionData.class)
        .ORDER_BY(PkCompetitionData.T.PE, "desc");
    if (startDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, ">?", startDate);
    }
    if (endDate != null) {
      sql.WHERE(PkCompetitionData.T.CREATEDTIME, "<=?", endDate);
    }
    return Query.sql(sql).queryMap();
  }
}
