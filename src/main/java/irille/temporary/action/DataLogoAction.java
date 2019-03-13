package irille.temporary.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.temporary.entity.DataLogoData;
import irille.temporary.util.getNameUtil;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class DataLogoAction extends HomeAction<DataLogoData> {
  private static final LogMessage LOG = new LogMessage(DataLogoAction.class);
  private String type;
  private Integer power;

  public Integer getPower() {
    return power;
  }

  public void setPower(Integer power) {
    this.power = power;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void getLogoDataInfo() throws JSONException, Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
    SQL sql =
        new SQL() {
          {
            SELECT(DataLogoData.class);
            FROM(DataLogoData.class);
            ORDER_BY(DataLogoData.T.TIME, "desc");
          }
        };
    List<DataLogoData> dataList = Query.sql(sql).lock(true).queryList(DataLogoData.class);
    DataLogoData dl = dataList.get(0);
    if (type != null) {
      if (!dl.getTime().equals(sdf.format(new Date()))) {
        dl.setAbilityValue(dl.getAbilityValue());
        dl.setAllBrowse(dl.getAllBrowse());
        dl.setAllMoney(dl.getAllMoney());
        dl.setCaregiver(dl.getCaregiver());
        dl.setBeneficiaries(dl.getBeneficiaries());
        dl.setAbilityValue(dl.getAbilityValue());
        dl.setTodayBrowse(7461);
        dl.setTodayRegister(62);
        dl.setTodaySuccess(7461);
        dl.setYesterdayAllMoney(364);
        dl.setYesterdayBrowse(624);
        dl.setYesterdayRegister(3);
        dl.setCaregiverTimes(0);
        dl.setTime(sdf.format(new Date()));
        dl.ins();
        dl = DataLogoData.loadUniqueTime(true, sdf.format(new Date()));
      }
      if (type.equals("1")) {
        int rand = getNameUtil.getNum(1, 6);
        int randyestoday = getNameUtil.getNum(-3, 4);
        dl.setTodayBrowse(dl.getTodayBrowse() + rand);
        dl.setAllBrowse(dl.getAllBrowse() + rand);
        dl.setYesterdayBrowse(randyestoday);
      }
      if (type.equals("2")) {
        int rand = getNameUtil.getNum(99, 289);

        dl.setAllMoney(dl.getAllMoney() + rand);
      }
      if (type.equals("3")) {
        dl.setBeneficiaries(dl.getBeneficiaries() + 1);
      }
      if (type.equals("4")) {
        if (dl.getCaregiverTimes() < 3) {
          dl.setCaregiver(dl.getCaregiver() + 1);
          dl.setCaregiverTimes(dl.getCaregiverTimes() + 1);
        }
      }
      if (type.equals("5")) {
        dl.setAbilityValue(dl.getAbilityValue() + getPower());
      }
      if (type.equals("6")) {
        int rand = ranDom(3, 1);
        dl.setTodayRegister(dl.getTodayRegister() + rand);
      }
      if (type.equals("7")) {
        int rand = getNameUtil.getNum(99, 289);
        int randyestoday = getNameUtil.getNum(-100, 100);
        dl.setAllMoney(dl.getAllMoney() + rand);
        dl.setTodaySuccess(dl.getTodaySuccess() + rand);
        dl.setYesterdayAllMoney(randyestoday);
      }
      dl.upd();
      dl = DataLogoData.loadUniqueTime(true, sdf.format(new Date()));
    }

    JSONObject json = new JSONObject();
    json.put("data", crtJsonByBean(dl));
    ServletActionContext.getResponse().getWriter().print(json);
  }

  public static int ranDom(int valueA, int valueB) {
    Random rand = new Random();
    return rand.nextInt(valueA) + valueB;
  }
}
