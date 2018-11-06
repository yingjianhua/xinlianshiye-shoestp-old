package irille.shop.usr;

import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuAct;
import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;

import java.util.HashSet;
import java.util.List;

public class UsrSupplierRoleActDAO {
	public static final Log LOG = new Log(UsrSupplierRoleActDAO.class);
	
	/**
	 * 取角色已有的功能集合
	 * @param pkey 角色主键值
	 * @return hashset避免重复值
	 */
	public static HashSet<Integer> listByRole(Integer pkey) {
		HashSet<Integer> setAct = new HashSet<Integer>();
		String where = Idu.sqlString("{0}=" + pkey, UsrSupplierRoleAct.T.ROLE);
		List<UsrSupplierRoleAct> list = BeanBase.list(UsrSupplierRoleAct.class, where, false);
		Integer actpkey = null;
		for (UsrSupplierRoleAct line : list) {
			for (int i = 1; i <= 12; i++) {
				actpkey = line.gtAct(i);
				if (actpkey != null)
					setAct.add(actpkey);
			}
		}
		return setAct;
	}
	
	
}