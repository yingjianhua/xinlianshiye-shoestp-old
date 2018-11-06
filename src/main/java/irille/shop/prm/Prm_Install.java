package irille.shop.prm;

import irille.core.sys.SysMenuDAO;
import irille.core.sys.SysUser;
import irille.pub.bean.InstallBase;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;

public class Prm_Install extends InstallBase{
	
	public static final Prm_Install INST = new Prm_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		m.proc(PrmGroupPurchase.TB, 20, null);
		//m.proc(PrmGroupOrder.TB, 30, null);
	}
	
}
