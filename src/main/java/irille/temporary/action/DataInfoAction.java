package irille.temporary.action;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.temporary.entity.DataInfo;
import irille.temporary.util.getNameUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataInfoAction extends HomeAction<DataInfo> {
  private static final LogMessage LOG = new LogMessage(DataInfoAction.class);
  private String type;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void getDataInfo() throws JSONException, Exception {
    String[] countryArray = {"西班牙", "意大利", "罗马尼亚", "匈牙利", "俄罗斯"};
    // String []
    // nameArray={"Lisa","Sandra","Shirley","Scott","Maria","Barbara","Jason","Angela","Patricia","Maria","Kenneth","Brian","Jessica","Edward","Jason","Mark","Helen","George","Charles","Daniel","Jeffrey","Matthew","Christopher","Patricia","Donald","Michael","Linda","Matthew","Elizabeth"};
    // String []
    // dateArray={"2018-10-17","2018-10-18","2018-10-19","2018-10-20","2018-10-21","2018-10-16","2018-10-15","2018-10-14","2018-10-13"};
    String[] status = {"待付款", "已付款"};
    String[] amount = {
      "32,154.00",
      "62,124.80",
      "12,204.00",
      "2,354.00",
      "7,154.00",
      "11,380.00",
      "9,274.00",
      "10,306.00",
      "42,184.00",
      "2,156.00",
      "8,871.00",
      "22,764.00",
      "12,334.00",
      "6,158.00",
      "1,154.00",
      "9,159.00",
      "5,158.00",
      "6,808.00",
      "62,750.00",
      "12,880.00",
      "6,159.00",
      "32,154.00",
      "7,764.00"
    };
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
    if (type != null && type.equals("1")) {
      // 鞋帽港列表信息
      for (int i = 0; i < 20; i++) {
        DataInfo df = new DataInfo();
        df.setFromName(countryArray[getNameUtil.getNum(0, countryArray.length - 1)]);
        df.setName(getNameUtil.getEnName());
        df.setAmount(amount[ranDom(amount.length, 0)]);
        df.setDate(sdf.format(new Date()));
        df.setStatus(status[getNameUtil.getNum(0, status.length - 1)]);
        df.setType("1");
        df.ins();
      }
      // List<DataInfo> odrInfList=BeanBase.list(DataInfo.class," ORDER BY " +
      // DataInfo.T.DATE.getFld().getCodeSqlField() + " DESC LIMIT(0,20)",false);
      SQL sql =
          new SQL() {
            {
              SELECT(DataInfo.class);
              FROM(DataInfo.class);
              WHERE(DataInfo.T.TYPE, " = ?", "1");
              ORDER_BY(DataInfo.T.DATE, "desc");
              LIMIT(0, 20);
            }
          };

      List<DataInfo> odrInfList = Query.sql(sql).queryList(DataInfo.class);
      Collections.shuffle(odrInfList);
      JSONObject json = new JSONObject();
      JSONArray ja = new JSONArray();
      JSONObject lineJson = null;
      for (DataInfo dataInfo : odrInfList.subList(0, odrInfList.size())) {
        lineJson = crtJsonByBean(dataInfo);
        ja.put(lineJson);
      }
      json.put(STORE_ROOT, ja);
      writerOrExport(json);

    } else {
      // 鞋乐购列表信息
      for (int i = 0; i < 20; i++) {
        DataInfo df = new DataInfo();
        String amountLogo = Integer.toString(getNameUtil.getNum(99, 289));
        df.setFromName(getNameUtil.getCityName());
        df.setName(getNameUtil.getChineseName());
        df.setAmount(amountLogo);
        df.setDate(sdf.format(new Date()));
        df.setStatus(status[getNameUtil.getNum(0, status.length - 1)]);
        df.setType("0");
        df.ins();
      }
      // List<DataInfo> odrInfList=BeanBase.list(DataInfo.class," ORDER BY " +
      // DataInfo.T.DATE.getFld().getCodeSqlField() + " DESC LIMIT(0,20)",false);
      SQL sql =
          new SQL() {
            {
              SELECT(DataInfo.class);
              FROM(DataInfo.class);
              WHERE(DataInfo.T.TYPE, " = ? ", "0");
              ORDER_BY(DataInfo.T.DATE, "desc");
              LIMIT(0, 20);
            }
          };
      List<DataInfo> odrInfList = Query.sql(sql).lock(true).queryList(DataInfo.class);
      Collections.shuffle(odrInfList);
      JSONObject json = new JSONObject();
      JSONArray ja = new JSONArray();
      JSONObject lineJson = null;
      for (DataInfo dataInfo : odrInfList.subList(0, odrInfList.size())) {
        lineJson = crtJsonByBean(dataInfo);
        ja.put(lineJson);
      }
      json.put(STORE_ROOT, ja);
      writerOrExport(json);
    }
  }

  public void getRegAndConsult() throws Exception {
    String[] countryArray = {"西班牙", "意大利", "罗马尼亚", "匈牙利", "俄罗斯"};
    // String []
    // nameArray={"Lisa","Sandra","Shirley","Scott","Maria","Barbara","Jason","Angela","Patricia","Maria","Kenneth","Brian","Jessica","Edward","Jason","Mark","Helen","George","Charles","Daniel","Jeffrey","Matthew","Christopher","Patricia","Donald","Michael","Linda","Matthew","Elizabeth"};
    // String [] title={"Romania Wholesale Shoes...","Juna ladies dress shoes...","FOLLOW ME
    // girls...","Women's Chic Pointed...","Aokang official men's...","British Style Simple Ankle
    // Shoes...","Men's casual velcro soft sandals...","Chunky heel single shoes..."};
    if (type.equals("1")) {
      for (int i = 0; i < 20; i++) {
        DataInfo di = new DataInfo();
        di.setFromName(countryArray[getNameUtil.getNum(0, countryArray.length - 1)]);
        di.setName(getNameUtil.getEnName());
        di.setStatus("inquiry|" + getNameUtil.getTitle());
        di.ins();
      }
      SQL sql =
          new SQL() {
            {
              SELECT(DataInfo.class);
              FROM(DataInfo.class);
              WHERE(DataInfo.T.STATUS, " like 'inquiry%'");
              LIMIT(0, 20);
            }
          };
      List<DataInfo> bigList = Query.sql(sql).queryList(DataInfo.class);
      JSONObject json = new JSONObject();
      JSONArray ja = new JSONArray();
      JSONObject lineJson = null;
      for (DataInfo dataInfo : bigList) {
        lineJson = crtJsonByBean(dataInfo);
        ja.put(lineJson);
      }
      json.put(STORE_ROOT, ja);
      writerOrExport(json);
    } else {
      for (int i = 0; i < 5; i++) {
        DataInfo di = new DataInfo();
        di.setFromName(countryArray[ranDom(5, 0)]);
        di.setName(getNameUtil.getEnName());
        di.setStatus("成功注册了");
        di.ins();
      }
      SQL sql =
          new SQL() {
            {
              SELECT(DataInfo.class);
              FROM(DataInfo.class);
              WHERE(DataInfo.T.STATUS, " like '%注册%'");
              LIMIT(0, 20);
            }
          };
      List<DataInfo> bigList = Query.sql(sql).queryList(DataInfo.class);
      JSONObject json = new JSONObject();
      JSONArray ja = new JSONArray();
      JSONObject lineJson = null;
      for (DataInfo dataInfo : bigList) {
        lineJson = crtJsonByBean(dataInfo);
        ja.put(lineJson);
      }
      json.put(STORE_ROOT, ja);
      writerOrExport(json);
    }
  }

  public static int ranDom(int valueA, int valueB) {
    Random rand = new Random();
    return rand.nextInt(valueA) + valueB;
  }

  private static Date randomDate(String beginDate, String endDate) {
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Date start = format.parse(beginDate); // 构造开始日期
      Date end = format.parse(endDate); // 构造结束日期
      // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
      if (start.getTime() >= end.getTime()) {
        return null;
      }
      long date = random(start.getTime(), end.getTime());
      return new Date(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static long random(long begin, long end) {
    long rtn = begin + (long) (Math.random() * (end - begin));
    // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
    if (rtn == begin || rtn == end) {
      return random(begin, end);
    }
    return rtn;
  }
}
