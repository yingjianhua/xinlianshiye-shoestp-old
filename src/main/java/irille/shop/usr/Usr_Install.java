package irille.shop.usr;

import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuDAO;
import irille.core.sys.SysUser;
import irille.pub.bean.BeanBase;
import irille.pub.bean.InstallBase;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Usr_Install extends InstallBase {
	public static final Usr_Install INST = new Usr_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		SysMenu up = m.procParent("供应商", "usr", 10, null);
		m.proc(UsrSupplier.TB, 20, up);
		m.proc(UsrSupplierCategory.TB, 30, up);
		SysMenu purchaser = m.procParent("采购商", "usr", 40, null);
		m.proc(UsrMemberLevel.TB, 50, purchaser);
		m.proc(UsrPurchase.TB, 60, purchaser);
		SysMenu role = m.procParent("权限管理", "usr", 70, null);
		m.proc(UsrSupplierRole.TB, 80, role);
		//m.proc(UsrConsult.TB, 90, null);
		m.proc(UsrMessages.TB, 100, null);
		m.proc(UsrSubscribe.TB, 110, null);
	}

	@Override
	public void installAfter() {
		super.installAfter();
		SysUser lu = new SysUser();
		lu.setPkey(1);
		LoginUserMsg msg = new LoginUserMsg(lu, (byte) 0, null, null, null);
		Env.INST.initTran(msg, null);
		initUsrSupplierCategory();
		initUsrSupplierRole();
		initUsrAccess();
	}
	private void initUsrSupplierCategory() {
		if (BeanBase.list(UsrSupplierCategory.class, null, false).size() > 0)
			return;
		UsrSupplierCategoryDAO.InsInit act = new UsrSupplierCategoryDAO.InsInit();
		act.setStream(Stream.of("冷粘鞋","模压鞋","硫化鞋","注塑鞋","缝纫鞋"));
		act.commit();
	}
	private void initUsrSupplierRole() {
		if (BeanBase.list(UsrSupplierRole.class, null, false).size() > 0)
			return;
		UsrSupplierRoleDAO.InsInit act = new UsrSupplierRoleDAO.InsInit();
		Map<String, String> map  = new LinkedHashMap<String, String>();
		map.put("01", "一级供应商");
		map.put("02", "二级供应商");
		map.put("03", "三级供应商");
		act.setMap(map);
		act.commit();
	}
	private void initUsrAccess() {
		UsrAccessDAO.InsInit act = new UsrAccessDAO.InsInit();
		act.commit();
	}
}
