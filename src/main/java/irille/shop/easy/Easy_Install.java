package irille.shop.easy;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Easy_Install extends InstallBase {

    public static final Easy_Install INST = new Easy_Install();

    @Override
    public void initMenu(SysMenuDAO m) {
        m.proc(EasyOdr.TB, 10, null);
    }

}
