package irille.Entity.O2O;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class O2O_Install extends InstallBase {
  public static final O2O_Install INST = new O2O_Install();

  @Override
  public void initMenu(SysMenuDAO m) {
    m.proc(O2O_Activity.TB, 10, null);
    m.proc(O2O_JoinInfo.TB, 20, null);
    m.proc(O2O_PrivateExpoPdt.TB, 30, null);
    m.proc(O2O_Product.TB, 40, null);
  }
}
