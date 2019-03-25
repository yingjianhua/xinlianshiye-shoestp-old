package irille.shop.usr;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import irille.core.sys.Sys;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduOther;
import irille.sellerAction.SellerAction;
import irille.shop.usr.Usr.OIMType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsrSupImDAO {
  public static final Log LOG = new Log(UsrAccessDAO.class);

  public static JSONArray getDeviceType() throws JSONException {
    OIMType[] types = Usr.OIMType.values();
    JSONArray typeArr = new JSONArray();
    for (OIMType type : types) {
      JSONObject typeJson = new JSONObject();
      typeJson.put("key", type.getLine().getKey());
      typeJson.put("value", type.getLine().getName());
      typeArr.put(typeJson);
    }
    return typeArr;
  }

  /**
   * <script type="text/javascript"> var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
   * (function(){ var
   * s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
   * s1.async=true; s1.src='https://embed.tawk.to/5b95d3eaf31d0f771d8496a4/default';
   * s1.charset='UTF-8'; s1.setAttribute('crossorigin','*'); s0.parentNode.insertBefore(s1,s0);
   * })(); </script>
   *
   * @author 管理员
   */
  public static class AddImSetting extends IduOther<AddImSetting, UsrSupIm> {
    private String account;

    public void before() {
      if (getB().getDemo() == null) {
        throw LOG.err("notBlank", "请输入tawk代码");
      }
      if (getB().getDemo().indexOf("Tawk_API") != -1) {
        setAccount(getTawkAccount(getB().getDemo()));
      } else {
        throw LOG.err("nosupport", "目前只支持tawk.to");
      }
    }

    public void valid() {
      if (getB().getDemo().trim().length() > 500) {
        throw LOG.err("tooLong", "demo过长,请去除不必要的空格");
      }
      if (SellerAction.getSupplier() == null) {
        throw LOG.err("noLogin", "商家未登录");
      }
      if (getB().getDemo() == null || getB().getDemo().trim().equals("")) {
        throw LOG.err(Usr.ErrMsgs.emptyErr);
      }
      if (getB().getType() == null) {
        throw LOG.err("noType", "请设置类型");
      }
    }

    public void run() {
      getB().setAccount(getAccount());
      getB().setSupplier(SellerAction.getSupplier().getPkey());
      getB().setDemo(getB().getDemo().trim());
      getB().ins();
    }

    public String getAccount() {
      return account;
    }

    public void setAccount(String account) {
      this.account = account;
    }
  }

  public static class UpdImSetting extends IduOther<UpdImSetting, UsrSupIm> {
    private String account;

    public void before() {
      if (getB().getDemo() == null) {
        throw LOG.err("notBlank", "请输入tawk代码");
      }
      if (getB().getDemo().indexOf("Tawk_API") != -1) {
        setAccount(getTawkAccount(getB().getDemo()));
      } else {
        throw LOG.err("nosupport", "目前只支持tawk.to");
      }
    }

    public void valid() {
      if (SellerAction.getSupplier() == null) {
        throw LOG.err("noLogin", "商家未登录");
      }
      if (getB().getDemo().trim().length() > 500) {
        throw LOG.err("tooLong", "demo过长,请去除不必要的空格");
      }
      if (getB().getDemo() == null || getB().getDemo().trim().equals("")) {
        throw LOG.err(Usr.ErrMsgs.emptyErr);
      }
    }

    public void run() {
      String where =
          UsrSupIm.T.SUPPLIER.getFld().getCodeSqlField()
              + " = "
              + SellerAction.getSupplier().getPkey();
      switch (Integer.valueOf(getB().getType())) {
        case 0:
          where +=
              " AND "
                  + UsrSupIm.T.TYPE.getFld().getCodeSqlField()
                  + " = "
                  + Integer.valueOf(Usr.OIMType.PHONE.getLine().getKey());
          try {
            UsrSupIm computerIm = BeanBase.list(UsrSupIm.class, where, false).get(0);
            computerIm.setType(Usr.OIMType.COMPUTER.getLine().getKey());
            computerIm.upd();
          } catch (ArrayIndexOutOfBoundsException e) {

          } finally {
            updBean();
            break;
          }
        case 1:
          where +=
              " AND "
                  + UsrSupIm.T.TYPE.getFld().getCodeSqlField()
                  + " = "
                  + Integer.valueOf(Usr.OIMType.COMPUTER.getLine().getKey());
          try {
            UsrSupIm phoneIm = BeanBase.list(UsrSupIm.class, where, false).get(0);
            phoneIm.setType(Usr.OIMType.PHONE.getLine().getKey());
            phoneIm.upd();
          } catch (ArrayIndexOutOfBoundsException e) {

          } finally {
            updBean();
            break;
          }
        case 2:
          List<UsrSupIm> imList = BeanBase.list(UsrSupIm.class, where, false);
          Idu.delLine(imList);
          getB().setPkey(null);
          getB().setAccount(getAccount());
          getB().setSupplier(SellerAction.getSupplier().getPkey());
          getB().ins();
          break;
      }
    }

    private void updBean() {
      UsrSupIm model = BeanBase.load(UsrSupIm.class, getB().getPkey());
      getB().setAccount(getAccount());
      getB().setDemo(getB().getDemo().trim());
      PropertyUtils.copyPropertiesWithout(
          model, getB(), UsrSupIm.T.PKEY, UsrSupIm.T.SUPPLIER, UsrSupIm.T.ROW_VERSION);
      System.out.println(model.getAccount() + "<<<<");
      model.upd();
    }

    public String getAccount() {
      return account;
    }

    public void setAccount(String account) {
      this.account = account;
    }
  }

  public static List<UsrSupIm> getImSetting(Integer supId) {
    List<UsrSupIm> imList =
        BeanBase.list(
            UsrSupIm.class, UsrSupIm.T.SUPPLIER.getFld().getCodeSqlField() + " =? ", false, supId);
    return imList;
  }

  public static List<UsrSupIm> getEnabledImSetting(Integer supId) {
    List<UsrSupIm> imList =
        BeanBase.list(
            UsrSupIm.class,
            UsrSupIm.T.SUPPLIER.getFld().getCodeSqlField()
                + " =? AND "
                + UsrSupIm.T.ENABLED.getFld().getCodeSqlField()
                + " =? ",
            false,
            supId,
            Sys.OEnabled.TRUE.getLine().getKey());
    return imList;
  }

  private static String getTawkAccount(String str) {
    String prefix = "https://embed.tawk.to/";
    String suffix = "/default";
    String account = str.substring(str.indexOf(prefix) + prefix.length(), str.indexOf(suffix));
    try {
      URL url = new URL(prefix + account + suffix);
      InputStream in = url.openStream();
      in.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw LOG.err("wrongUrl", "数据异常");
    }
    return account;
  }
}
