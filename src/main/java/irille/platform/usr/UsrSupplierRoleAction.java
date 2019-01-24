package irille.platform.usr;

import irille.action.MgtAction;
import irille.action.usr.inf.IUsrSupplierRoleAction;
import irille.pub.svr.LoginUserMsg;
import irille.shop.usr.UsrSupplierRole;
import irille.shop.usr.UsrSupplierRoleDAO;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsrSupplierRoleAction extends MgtAction<UsrSupplierRole> implements IUsrSupplierRoleAction {
	public String _lines;
	public String _type;
	private String _jyType;
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

	
	public String getLines() {
		return _lines;
	}

	public void setLines(String _lines) {
		this._lines = _lines;
	}

	public String getType() {
		return _type;
	}

	public void setType(String _type) {
		this._type = _type;
	}

	public String getJyType() {
		return _jyType;
	}

	public void setJyType(String jyType) {
		this._jyType = jyType;
	}

	@Override
	public String orderBy() {
		return " ORDER BY " + UsrSupplierRole.T.CODE;
	}

	@Override
	public void setDefault() throws IOException, JSONException {
		UsrSupplierRoleDAO.SetDefault act = new UsrSupplierRoleDAO.SetDefault();
		act.setB(getBean());
		act.commit();
		writeSuccess();
	}

	/**
	 * 获取供应商角色功能权限 用于前端显示
	 * @throws IOException
	 * @throws JSONException
	 */
	public void loadAuthorityByRole() throws IOException, JSONException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().print(UsrSupplierRoleDAO.ProvCtrl.crtRes((Integer) getPkey(), UsrAccessAction.MODULES.get(getJyType())).toString());
	}
	/**
	 * 修改供应商角色功能权限
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void updCtrl() throws IOException, JSONException {
		UsrSupplierRoleDAO.UpdCtrl act = new UsrSupplierRoleDAO.UpdCtrl();
		act.setBKey(getPkey());
		act.setLines(getLines()==null?"":getLines());
		act.setType(UsrAccessAction.MODULES.get(getType()));
		act.commit();
		writeSuccess();
	}
	//插入供应商权限表
	public void insctrl() throws IOException, JSONException {
		UsrSupplierRoleDAO.insctrl act = new UsrSupplierRoleDAO.insctrl();
		LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
		getBean().setUpdatedBy(lu.get_user().getPkey());
		act.setB(getBean());
		act.commit();
		writeSuccess();
	}
	//修改供应商权限表
	public void updctrl() throws IOException, JSONException {
		UsrSupplierRoleDAO.Updctrl1role act = new UsrSupplierRoleDAO.Updctrl1role();
		LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
		getBean().setUpdatedBy(lu.get_user().getPkey());
		act.setB(getBean());
		act.commit();
		writeSuccess();
	}

}
