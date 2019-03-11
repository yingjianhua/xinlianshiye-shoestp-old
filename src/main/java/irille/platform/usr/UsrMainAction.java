package irille.platform.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrMainDao;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class UsrMainAction extends MgtAction<UsrMain> {
	@Override
	public Class beanClazz() {
		return UsrMain.class;
	}

	public UsrMain getBean() {
		return _bean;
	}

	public void setBean(UsrMain bean) {
		this._bean = bean;
	}

	@Getter
	@Setter
	private String company;
	@Getter
	@Setter
	private String email;

	/**
	 * 获取供应商注册列表
	 * @throws IOException
	 */
	public void getRegistList() throws IOException {
		write(UsrMainDao.getRegistList(getStart(), getLimit(),company,email));
	}

	@Getter
	@Setter
	private String id;

	/**
	 * 根据id获取供应商注册信息
	 * @throws IOException
	 */
	public void getRegistById() throws IOException {
		write(UsrMainDao.getRegistById(id));
	}
}
