package irille.homeAction.usr;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.homeAction.HomeAction;
import irille.homeAction.usr.inf.IUsrConsultAction;
import irille.pub.Str;
import irille.shop.usr.UsrConsult;
import irille.shop.usr.UsrConsultDAO;
import irille.view.usr.ConsultView;
import org.json.JSONException;

import java.io.IOException;
/**
 * 采购商action
 * @author yjh
 *
 */
public class UsrConsultAction extends HomeAction<UsrConsult> implements IUsrConsultAction {

	private static final long serialVersionUID = -7870388632292655187L;

	private ConsultView v;
	private String vCode;
	private static final String vcode_err = "Verification code error.";
	private static final String timeout_err = "timeout.";
	private static final String not_exists_err = "this inquiry is not exists";

	/**
	 * 公共询盘列表页面
	 * @return
	 * @author yingjianhua
	 */
	@Override
	public String publicListView() {
	    setResult("/home/usr_UsrMessages_center");
        return HomeAction.RTRENDS;
	}

	/**
	 * 发布询盘页面(需要登录)
	 * @return
	 * @author yingjianhua
	 */
	@Override
	public String publishView() {
//		setResult("/home/my-inquiry-publish.jsp");
//		return TRENDS;
        setResult("/home/usr_UsrMessages_center");
        return HomeAction.RTRENDS;
	}
	@NeedLogin
	public String productPublishView() {
		setResult("/home/pdt-inquiry-publish.jsp");
		return TRENDS;
	}

	/**
	 * 我的询盘列表页面(需要登录)
	 * @return
	 * @author yingjianhua
	 */
	@Override
	@NeedLogin
	public String listView() {
		setResult("/home/my-inquiry-list.jsp");
		return TRENDS;
	}

	/**
	 * 询盘详情页(需要登录)
	 * @return
	 * @author yingjianhua
	 */
	@Override
	@NeedLogin
	public String detailView() {
		setResult("/home/my-inquiry-view.jsp");
		return TRENDS;
	}

//------------------------------------------------------------------------以上是页面请求GET--------------------------------------------
//------------------------------------------------------------------------往下是数据请求POST-------------------------------------------

	/**
	 * 采购商发布询盘
	 * @throws Exception
	 * @author yingjianhua
	 */
	@Override
	@NeedLogin
	public void publish() throws Exception {
		String verifyCode = verifyCode();
		System.out.println("syscode:"+verifyCode());
		System.out.println("vCode:"+vCode);
		if ((Str.isEmpty(verifyCode) || Str.isEmpty(vCode) || verifyCode.equals(vCode) == false)) {
			writeErr(vcode_err);
        } else {
        	new UsrConsultDAO.Publish(v, getPurchase().getPkey()).commit();
        	write();
        }
	}

	/**
	 * 列表公共询盘数据
	 * @throws IOException
	 * @throws JSONException
	 * @author yingjianhua
	 */
	@Override
	public void pagePublic() throws IOException, JSONException {
		write(UsrConsultDAO.pagePublic(getStart(), getLimit(), null, null,null,null, curLanguage()));
	}
	/**
	 * 列表我的询盘(分页)
	 * @throws IOException
	 */
	@NeedLogin
	public void pagePrivate() throws IOException {
		write(UsrConsultDAO.pagePrivate(getStart(), getLimit(), getPurchase().getPkey()));
	}

	/**
	 * 查看询盘
	 * @throws Exception
	 * @author yingjianhua
	 */
	@Override
	public void detail() throws Exception {
		ConsultView view = UsrConsultDAO.load((Integer)getId(), curLanguage());
		if(view == null)
			writeErr(not_exists_err);
		else
			write(view);
	}

	/**
	 * 删除询盘
	 * @throws Exception
	 */
	@Override
	@NeedLogin
	public void delete() throws Exception {
		if(!UsrConsultDAO.isOwner((Integer)getId(), getPurchase().getPkey())){
			writeErr("wrong owner");
		} else {
			UsrConsultDAO.delete((Integer)getId());
			write();
		}
	}

	private String ids;

	/**
	 * 批量删除询盘
	 * @throws Exception
	 */
	@NeedLogin
	public void deletes() throws Exception {
		for(String id:ids.split(",")) {
			if(!UsrConsultDAO.isOwner(Integer.valueOf(id), getPurchase().getPkey())) {
				writeErr("wrong owner");
				return;
			}
		}
		for(String id:ids.split(",")) {
			UsrConsultDAO.delete(Integer.valueOf(id));
		}
		write();
	}

//------------------------------------------------------------------------以上是数据请求POST--------------------------------------------
//------------------------------------------------------------------------往下是get,set方法--------------------------------------------

	public ConsultView getV() {
		return v;
	}
	public void setV(ConsultView v) {
		this.v = v;
	}
	public String getVCode() {
		return vCode;
	}
	public void setVCode(String vCode) {
		this.vCode = vCode;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

}
