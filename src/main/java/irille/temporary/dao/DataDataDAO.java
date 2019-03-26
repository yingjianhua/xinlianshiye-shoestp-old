package irille.temporary.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import irille.pub.LogMessage;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.temporary.entity.DataData;

public class DataDataDAO {

  private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
  private static final LogMessage LOG = new LogMessage(DataDataDAO.class);

  public static class Ins extends IduIns<Ins, DataData> {
    @Override
    public void before() {
      super.before();
      //
    }
  }

  public static class Upd extends IduUpd<Upd, DataData> {
    @Override
    public void before() {
      super.before();
      DataData model = loadThisBeanAndLock();

      setB(model);
    }
  }

  public static class Del extends IduDel<Del, DataData> {}
}
