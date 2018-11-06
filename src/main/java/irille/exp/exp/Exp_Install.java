package irille.exp.exp;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Exp_Install extends InstallBase {
	public static final Exp_Install INST = new Exp_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
//		m.proc(ExpBase.TB, 10, null);
//		m.proc(ExpKind.TB, 20, null);
//		m.proc(ExpComp.TB, 30, null);
	}
}
