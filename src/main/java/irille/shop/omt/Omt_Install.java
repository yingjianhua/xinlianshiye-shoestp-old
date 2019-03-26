package irille.shop.omt;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Omt_Install extends InstallBase {

  public static final Omt_Install INST = new Omt_Install();

  @Override
  public void initMenu(SysMenuDAO m) {
    m.proc(OrderMeeting.TB, 20, null);
  }
}
