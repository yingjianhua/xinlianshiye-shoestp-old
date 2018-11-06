package irille.sellerAction.usr;

import irille.action.ActionBase;
import irille.pub.Str;
import irille.pub.svr.ProvCtrl;
import irille.sellerAction.usr.inf.IUsrSupplierRoleAction;
import irille.shop.usr.UsrSupplierRole;
import irille.shop.usr.UsrSupplierRoleDAO;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class UsrSupplierRoleAction extends ActionBase<UsrSupplierRole> implements IUsrSupplierRoleAction {
	public String _type;
	
	@Override
	public Class beanClazz() {
		return UsrSupplierRole.class;
	}

	public UsrSupplierRole getBean() {
		return _bean;
	}

	public void setBean(UsrSupplierRole bean) {
		this._bean = bean;
	}
	
	public String getType() {
		return _type;
	}

	public void setType(String _type) {
		this._type = _type;
	}

	@Override
	public String orderBy() {
		return " ORDER BY " + UsrSupplierRole.T.CODE;
	}
	
	/**
	 * 用户功能缓存初始化 前台EXT加载时AJAX同步初台化后，再开始加载菜单
	 * @throws Exception
	 */
	@Override
	public void initAct() throws Exception {
		String types = ProvCtrl.getInstance().initActSet();
		if (Str.isEmpty(types))
			return;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().print(types);
	}

	/**
	 * 加载商家的功能菜单
	 * @throws Exception
	 */
	@Override
	public void loadMenu() throws Exception {
		if (Str.isEmpty(getType()))
			return;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().print(UsrSupplierRoleDAO.ProvCtrl.getInstance().getMenuByType(getType()));
	}
}
