package irille.temporary.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.temporary.entity.DataData;
import irille.temporary.util.getNameUtil;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class DataDataAction extends HomeAction<DataData> {
  private static final LogMessage LOG = new LogMessage(DataDataAction.class);
  private String type;
  private Integer number;

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void getDataInfo() throws JSONException, Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
    SQL sql =
        new SQL() {
          {
            SELECT(DataData.class);
            FROM(DataData.class);
            ORDER_BY(DataData.T.TIME, "desc");
          }
        };
    List<DataData> dataList = Query.sql(sql).lock(true).queryList(DataData.class);
    DataData odr = dataList.get(0);
    if (type != null) {
      if (!odr.getTime().equals(sdf.format(new Date()))) {
        // 清空有关不是今日的数据
        odr.setAllInquiry(odr.getAllInquiry());
        odr.setAllMoney(odr.getAllMoney());
        odr.setAllProducts(odr.getAllProducts());
        odr.setAllRegister(odr.getAllRegister());
        odr.setTodayBrowse(4783);
        odr.setTodayMoneyCount(0);
        odr.setTodayRegister(15);
        odr.setTodaySuccess(1086504);
        odr.setSupplier(1);
        odr.setPurchase(14);
        odr.setYesterdayAllMoney(1138);
        odr.setYesterdayAllRegister(3);
        odr.setYesterdayBrowse(524);
        odr.setAllTrueMoney(odr.getAllTrueMoney());
        odr.setTime(sdf.format(new Date()));
        odr.ins();
        odr = DataData.loadUniqueTime(true, sdf.format(new Date()));
      }
      if (type.equals("1")) {
        // 更新采购商注册量
        int rand = getNameUtil.getNum(1, 4);
        int randYestoday = getNameUtil.getNum(-2, 2);
        odr.setAllRegister(odr.getAllRegister() + rand);
        odr.setTodayRegister(odr.getTodayRegister() + rand);
        odr.setYesterdayAllRegister(randYestoday);
        odr.setPurchase(odr.getPurchase() + rand);
      }
      if (type.equals("2")) {
        // 更新供应商注册量
        int randYestoday = getNameUtil.getNum(-2, 2);
        odr.setAllRegister(odr.getAllRegister() + 1);
        odr.setTodayRegister(odr.getTodayRegister() + 1);
        odr.setYesterdayAllRegister(randYestoday);
        odr.setSupplier(odr.getSupplier() + 1);
      }
      if (type.equals("3")) {
        // 更新浏览量
        int rand = getNameUtil.getNum(3, 27);
        int randYestoday = getNameUtil.getNum(-6, 10);
        odr.setTodayBrowse(odr.getTodayBrowse() + rand);
        odr.setYesterdayBrowse(randYestoday);
      }
      if (type.equals("4")) {
        // 更新鞋贸港交易总金额
        int rand = getNameUtil.getNum(186475, 386745);
        if (odr.getAllMoneyCount() < 10) {
          odr.setAllMoney(odr.getAllMoney() + rand);
          odr.setAllMoneyCount(odr.getAllMoneyCount() + 1);
        } else {
          odr.setAllMoney(odr.getAllMoney());
          odr.setAllMoneyCount(odr.getAllMoneyCount() + 1);
        }
      }
      if (type.equals("5")) {
        // 更新交易金额
        int rand = getNameUtil.getNum(226415, 382382);
        int randYesterDay = getNameUtil.getNum(1138, 2451);
        if (odr.getTodayMoneyCount() < 3) {
          odr.setAllMoney(odr.getAllMoney() + rand);
          odr.setTodaySuccess(odr.getTodaySuccess() + rand);
          odr.setAllTrueMoney(odr.getAllTrueMoney() + rand);
          odr.setYesterdayAllMoney(randYesterDay);
          odr.setTodayMoneyCount(odr.getTodayMoneyCount() + 1);
        } else {
          odr.setAllMoney(odr.getAllMoney());
          odr.setTodaySuccess(odr.getTodaySuccess());
          odr.setAllTrueMoney(odr.getAllTrueMoney());
          odr.setYesterdayAllMoney(odr.getYesterdayAllMoney());
          odr.setTodayMoneyCount(odr.getTodayMoneyCount() + 1);
        }
      }
      if (type.equals("6")) {
        // 更新总商品量
        int rand = getNameUtil.getNum(1, 2);
        odr.setAllProducts(odr.getAllProducts() + rand);
      }
      if (type.equals("7")) {
        // 更新总询盘量
        int rand = getNameUtil.getNum(1, 2);
        odr.setAllInquiry(odr.getAllInquiry() + rand);
      }
      odr.upd();
      odr = DataData.loadUniqueTime(true, sdf.format(new Date()));
    }

    JSONObject json = new JSONObject();
    json.put("data", crtJsonByBean(odr));
    ServletActionContext.getResponse().getWriter().print(json);
  }

  public static int ranDom(int valueA, int valueB) {
    Random rand = new Random();
    return rand.nextInt(valueA) + valueB;
  }

  public static void main(String[] args) {
    /*for (int i = 0; i < 20; i++) {
    	System.out.println(ranDom(5,0));
    }*/
  }
}
