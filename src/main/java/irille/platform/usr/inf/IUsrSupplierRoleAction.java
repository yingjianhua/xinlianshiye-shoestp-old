package irille.platform.usr.inf;


public interface IUsrSupplierRoleAction {

	/**
	 * 将某一个角色设置为默认角色，新增商家的时候 成为默认角色
	 */
	public void setDefault() throws Exception;
	
}
