package irille.shop.odr;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Odr_Install  extends InstallBase {

	public static final Odr_Install INST = new Odr_Install();
	@Override
	public void initMenu(SysMenuDAO m) {
		m.proc(OdrOrder.TB,10,null);
	}

}
