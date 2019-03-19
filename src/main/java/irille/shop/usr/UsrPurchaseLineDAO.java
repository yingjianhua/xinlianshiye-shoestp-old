package irille.shop.usr;

import java.util.List;

import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.core.sys.Sys;
import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.plt.PltProvince;
import irille.shop.usr.Usr.OAddress;
import irille.shop.usr.UsrPurchaseLine.T;

public class UsrPurchaseLineDAO {
  public static final LogMessage LOG = new LogMessage(UsrPurchaseLine.class);

  public static List<UsrPurchaseLine> listByPurchaseAddrsstype(Integer purchase, OAddress stype) {
    return Query.SELECT(UsrPurchaseLine.class)
        .WHERE(T.PURCHASE, "=?", purchase)
        .WHERE(T.ADDRSSTYPE, "=?", stype.getLine().getKey())
        .queryList();
  }

  public static UsrPurchaseLine findDefaultByPurchase(Integer purchase) {
    return Query.SELECT(UsrPurchaseLine.class)
        .WHERE(T.PURCHASE, "=?", purchase)
        .WHERE(T.ADDRSSTYPE, "=?", OAddress.COMMON.getLine().getKey())
        .WHERE(T.ISDEFAULT, "=?", BeanBase.booleanToByte(true))
        .query();
  }

  public static class Ins extends IduIns<Ins, UsrPurchaseLine> {
    public void before() {
      String sql =
          "UPDATE "
              + UsrPurchaseLine.TB.getCodeSqlTb()
              + " SET "
              + T.ISDEFAULT.getFld().getCodeSqlField()
              + "=? WHERE "
              + T.ISDEFAULT.getFld().getCodeSqlField()
              + "=? AND "
              + T.PURCHASE.getFld().getCodeSqlField()
              + "=?";
      UsrPurchaseLine.executeUpdate(
          sql,
          Sys.OYn.NO.getLine().getKey(),
          Sys.OYn.YES.getLine().getKey(),
          HomeAction.getPurchase().getPkey());
      getB().stIsdefault(true);
      getB().stAddrsstype(Usr.OAddress.COMMON);
      getB().setPurchase(HomeAction.getPurchase().getPkey());
      super.before();
    }
  }

  public static class Inss extends IduIns<Ins, UsrPurchaseLine> {
    public void before() {
      String sql =
          "UPDATE "
              + UsrPurchaseLine.TB.getCodeSqlTb()
              + " SET "
              + T.ISDEFAULT.getFld().getCodeSqlField()
              + "=? WHERE "
              + T.ISDEFAULT.getFld().getCodeSqlField()
              + "=? AND "
              + T.PURCHASE.getFld().getCodeSqlField()
              + "=?";
      UsrPurchaseLine.executeUpdate(
          sql,
          Sys.OYn.NO.getLine().getKey(),
          Sys.OYn.YES.getLine().getKey(),
          HomeAction.getPurchase().getPkey());
      getB().stIsdefault(true);
      getB().stAddrsstype(Usr.OAddress.BILLED);
      getB().setPurchase(HomeAction.getPurchase().getPkey());
      super.before();
    }
  }

  /**
   * 修改地址
   *
   * @author zw
   */
  public static class upd extends IduUpd<upd, UsrPurchaseLine> {
    public void before() {
      getB().stIsdefault(true);
      UsrPurchaseLine dbbean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbbean, getB(), T.ISDEFAULT, T.PKEY, T.ADDRSSTYPE, T.ROW_VERSION, T.PURCHASE);
      setB(dbbean);
      super.before();
    }
  }

  /**
   * 设置地址默认
   *
   * @author zw
   */
  public static class updisdefault extends IduUpd<upd, UsrPurchaseLine> {
    public void before() {
      String sql =
          "UPDATE "
              + UsrPurchaseLine.TB.getCodeSqlTb()
              + " SET "
              + T.ISDEFAULT.getFld().getCodeSqlField()
              + "=? WHERE "
              + T.ISDEFAULT.getFld().getCodeSqlField()
              + "=? AND "
              + T.PURCHASE.getFld().getCodeSqlField()
              + "=? AND "
              + T.ADDRSSTYPE.getFld().getCodeSqlField()
              + " = ? ";
      UsrPurchaseLine.executeUpdate(
          sql,
          Sys.OYn.NO.getLine().getKey(),
          Sys.OYn.YES.getLine().getKey(),
          HomeAction.getPurchase().getPkey(),
          Usr.OAddress.COMMON.getLine().getKey());
      getB().stIsdefault(true);
      UsrPurchaseLine dbbean = loadThisBeanAndLock();
      PropertyUtils.copyProperties(dbbean, getB(), UsrPurchaseLine.T.ISDEFAULT);
      setB(dbbean);
    }
  }

  public static class updAdd extends IduUpd<upd, UsrPurchaseLine> {
    public void before() {
      String sql =
          UsrPurchaseLine.T.PURCHASE.getFld().getCodeSqlField()
              + " in ("
              + HomeAction.getPurchase().getPkey()
              + ")";
      List<UsrPurchaseLine> line = BeanBase.list(UsrPurchaseLine.class, sql, false);
      for (UsrPurchaseLine address : line) {
        if (address.gtIsdefault() == true) {
          UsrPurchaseLineDAO.upd upd = new UsrPurchaseLineDAO.upd();
          UsrPurchaseLine upld = address;
          upld.stIsdefault(false);
          upd.setB(upld);
          upld.upd();
        }
      }
      getB().setPurchase(HomeAction.getPurchase().getPkey());
      getB().stIsdefault(true);
      UsrPurchaseLine dbbean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(dbbean, getB(), UsrPurchaseLine.T.ROW_VERSION);
      setB(dbbean);
    }
  }

  public static class InsLine extends IduIns<InsLine, UsrPurchaseLine> {
    @Override
    public void before() {
      setB(getB());
    }
  }

  public static class del extends IduDel<del, UsrPurchaseLine> {
    @Override
    public void before() {
      setB(getB());
    }
  }

  /**
   * 更新收货地址
   *
   * @author GS
   */
  public static class updAdress extends IduUpd<upd, UsrPurchaseLine> {
    private String province;
    private String queryProResult;
    private Language language;

    public Language getLanguage() {
      return language;
    }

    public void setLanguage(Language language) {
      this.language = language;
    }

    public String getQueryProResult() {
      return queryProResult;
    }

    public void setQueryProResult(String queryProResult) {
      this.queryProResult = queryProResult;
    }

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    @Override
    public void before() {
      try {
        Integer provinceValue = Integer.valueOf(province);
        PltProvince queryProvince = BeanBase.chk(PltProvince.class, provinceValue);
        if (null == queryProvince) {
          setQueryProResult("省记录不存在");
          throw new WebMessageException(
              MessageBuild.buildMessage(ReturnCode.no_province, language));
          //          throw LOG.err("provinceErr", "{0}记录不存在",
          // PltProvince.T.PKEY.getFld().getName());
        } else {
          getB().setRegion(provinceValue);
        }
      } catch (NumberFormatException e) {
        PltProvince pltProvince = new PltProvince();
        pltProvince.setName(province);
        pltProvince.setShortName(province);
        pltProvince.setMain(getB().getCountry());
        pltProvince.ins();
        if (pltProvince.getPkey() != null) {
          getB().setRegion(pltProvince.getPkey());
        } else {
          setQueryProResult("省记录添加失败");
          throw new WebMessageException(
              MessageBuild.buildMessage(ReturnCode.no_province, language));
          //          throw LOG.err("provinceAddErr", "{0}记录添加失败",
          // PltProvince.T.PKEY.getFld().getName());
        }
      }
      getB().stIsdefault(true);
      UsrPurchaseLine dbbean = loadThisBeanAndLock();
      PropertyUtils.copyPropertiesWithout(
          dbbean, getB(), T.ISDEFAULT, T.PKEY, T.ADDRSSTYPE, T.ROW_VERSION, T.PURCHASE);
      setB(dbbean);
      setQueryProResult("success");
    }
  }

  /**
   * 添加收货地址
   *
   * @author GS
   */
  public static class InsAddress extends IduIns<Ins, UsrPurchaseLine> {
    private String province;
    private String addressType;
    private String queryProResult;
    private Language language;

    public Language getLanguage() {
      return language;
    }

    public void setLanguage(Language language) {
      this.language = language;
    }

    public String getQueryProResult() {
      return queryProResult;
    }

    public void setQueryProResult(String queryProResult) {
      this.queryProResult = queryProResult;
    }

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getAddressType() {
      return addressType;
    }

    public void setAddressType(String addressType) {
      this.addressType = addressType;
    }

    @Override
    public void before() {
      try {
        Integer provinceValue = Integer.valueOf(province);
        PltProvince queryProvince = BeanBase.chk(PltProvince.class, provinceValue);
        if (null == queryProvince) {
          setQueryProResult("省记录不存在");
          throw new WebMessageException(
              MessageBuild.buildMessage(ReturnCode.no_province, language));
          //          throw LOG.errTran("addressfrom%No_Province", "省级记录不存在");
        } else {
          getB().setRegion(provinceValue);
        }
      } catch (NumberFormatException e) {
        PltProvince pltProvince = new PltProvince();
        pltProvince.setName(province);
        pltProvince.setShortName(province);
        pltProvince.setMain(getB().getCountry());
        pltProvince.ins();
        if (pltProvince.getPkey() != null) {
          getB().setRegion(pltProvince.getPkey());
        } else {
          setQueryProResult("省记录添加失败");
          throw new WebMessageException(
              MessageBuild.buildMessage(ReturnCode.no_province, language));
          //          throw LOG.errTran("addressfrom%No_Province", "省级记录不存在");
        }
      }
      if (getAddressType().equals(String.valueOf(Usr.OAddress.COMMON.getLine().getKey()))) {
        String sql =
            "UPDATE "
                + UsrPurchaseLine.TB.getCodeSqlTb()
                + " SET "
                + T.ISDEFAULT.getFld().getCodeSqlField()
                + "=? WHERE "
                + T.ISDEFAULT.getFld().getCodeSqlField()
                + "=? AND "
                + T.PURCHASE.getFld().getCodeSqlField()
                + "=? AND "
                + T.ADDRSSTYPE.getFld().getCodeSqlField()
                + " =? ";
        UsrPurchaseLine.executeUpdate(
            sql,
            Sys.OYn.NO.getLine().getKey(),
            Sys.OYn.YES.getLine().getKey(),
            HomeAction.getPurchase().getPkey(),
            Usr.OAddress.COMMON.getLine().getKey());
      }
      getB().stIsdefault(true);
      if (addressType.equals(Integer.toString(Usr.OAddress.COMMON.getLine().getKey()))) {
        getB().stAddrsstype(Usr.OAddress.COMMON);
      } else {
        getB().stAddrsstype(Usr.OAddress.BILLED);
      }
      getB().setPurchase(HomeAction.getPurchase().getPkey());
      setQueryProResult("success");
    }
  }
}
