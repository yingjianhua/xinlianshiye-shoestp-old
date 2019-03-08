package irille.platform.usr;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.action.MgtAction;
import irille.platform.usr.View.UsrSupplierNewView;
import irille.pub.util.upload.ImageUpload;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierDAO;
import irille.view.plt.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class UsrSupplierAction extends MgtAction<UsrSupplier> {
	@Override
	public Class beanClazz() {
		return UsrSupplier.class;
	}

	public UsrSupplier getBean() {
		return _bean;
	}

	public void setBean(UsrSupplier bean) {
		this._bean = bean;
	}
	/**
	 *@Description:  供应商选中器列表平台
	 *@date 2019/1/23 19:20
	 *@anthor wilson zhang
	 */
	@Getter
	@Setter
	private  String fldvalue;
	@Getter
	@Setter
	private  String condition;
	public void ListUsrSup()throws Exception {
		write(	UsrSupplierDAO.listsupselect(fldvalue,condition,getStart(),getLimit()));
	}
	@Getter
	@Setter
	private Integer supplier;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private Integer category;
	@Getter
	@Setter
	private Integer status;
	@Getter
	@Setter
	private String fileFileName = "";
	@Getter
	@Setter
	private File file;

	/**
	 * 获取开店申请列表
	 * @author: lingjian
	 * @Date: 2019/3/4 15:59
	 * @throws IOException
	 */
	public void getShopApplication() throws IOException {
		write(UsrSupplierDAO.getShopApplication(getStart(), getLimit(), name, status));
	}


	@Getter
	@Setter
	private String id;

	/**
	 * 根据id获取供应商信息
	 * @author: lingjian
	 * @Date: 2019/3/8 10:41
	 * @throws IOException
	 */
	public void getSupplierById() throws IOException {
		write(UsrSupplierDAO.getSupplierById(id));
	}

	@Getter
	@Setter
	private String reason;

	/**
	 * 审核
	 * @throws IOException
	 */
	public void reviewStatus() throws IOException {
		UsrSupplier supplier = UsrSupplierDAO.reviewStatus(id,status,reason);
		UsrSupplierNewView usrSupplierNewView = new UsrSupplierNewView();
		usrSupplierNewView.setStatus(supplier.getStatus());
		write(usrSupplierNewView);
	}


	/**
	 * @Description: 获取供应商列表
	 * *@date 2019/1/21 09:05
	 * *@anthor zjl
	 */
	public void getSuppliers() throws IOException {
		write(UsrSupplierDAO.getSuppliers(getStart(), getLimit(), name, category, status));
	}

	/**
	 * @Description: 获取所有状态
	 * *@date 2019/1/21 10:33
	 * *@anthor zjl
	 */
	public void getStatus() throws IOException {
		write(UsrSupplierDAO.getStatus());
	}

	/**
	 * @Description: 审核/弃审
	 * *@date 2019/1/21 10:44
	 * *@anthor zjl
	 */
	public void updStatus() throws IOException {
		UsrSupplierDAO.UpdStatus upd = new UsrSupplierDAO.UpdStatus();
		upd.setB(getBean());
		upd.commit();
		write();
	}

	/**
	 * @Description: 获取供应商基本信息
	 * *@date 2019/1/21 14:20
	 * *@anthor zjl
	 */
	public void getBasicInformation() throws IOException {
		write(UsrSupplierDAO.getBasicInformation(supplier));
	}

	/**
	 * @Description: 更新供应商基本信息
	 * *@date 2019/1/21 14:20
	 * *@anthor zjl
	 */
	public void updBasicInformation() throws IOException {
		UsrSupplierDAO.UpdBasicInformation upd = new UsrSupplierDAO.UpdBasicInformation();
		upd.setB(getBean());
		upd.commit();
		write();
	}

	/**
	 * @Description: 获取供应商页面资料
	 * *@date 2019/1/21 14:20
	 * *@anthor zjl
	 */
	public void getPageInformation() throws IOException {
		write(UsrSupplierDAO.getPageInformation(supplier));
	}

	/**
	 * @Description: 更新供应商页面资料
	 * *@date 2019/1/21 14:58
	 * *@anthor zjl
	 */
	public void updPageInformation() throws IOException {
		UsrSupplierDAO.UpdPageInformation upd = new UsrSupplierDAO.UpdPageInformation();
		upd.setB(getBean());
		upd.commit();
		write();
	}

	/**
	 * @Description: 获取供应商个性装修
	 * *@date 2019/1/21 15:05
	 * *@anthor zjl
	 */
	public void getPersonalityDecoration() throws IOException {
		write(UsrSupplierDAO.getPersonalityDecoration(supplier));
	}

	/**
	 * @Description: 更新供应商个性装修
	 * *@date 2019/1/21 15:05
	 * *@anthor zjl
	 */
	public void updPersonalityDecoration() throws IOException {
		UsrSupplierDAO.UpdPersonalityDecoration upd = new UsrSupplierDAO.UpdPersonalityDecoration();
		upd.setB(getBean());
		upd.commit();
		write();
	}

	/**
	 * @Description: 获取供应商营销设置
	 * *@date 2019/1/21 15:05
	 * *@anthor zjl
	 */
	public void getmarketingSettings() throws IOException {
		write(UsrSupplierDAO.getmarketingSettings(supplier));
	}

	/**
	 * @Description: 更新供应商营销设置
	 * *@date 2019/1/21 15:05
	 * *@anthor zjl
	 */
	public void updmarketingSettings() throws IOException {
		UsrSupplierDAO.UpdMarketingSettings upd = new UsrSupplierDAO.UpdMarketingSettings();
		upd.setB(getBean());
		upd.commit();
		write();
	}

	/**
	 * 上传图片
	 *
	 * @Date 219/1/14 15:13
	 * @author zjl
	 */
	public void upload() throws Exception {
		if (getLoginSys() == null) {
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("msg", "登录超时,请重新登录");
			writerOrExport(json);
		} else {
			ImageView view = ImageUpload.upload(beanClazz(), fileFileName, file);
			JSONObject json = new JSONObject();
			json.put("ret", 1);
			json.put("result", new JSONObject(new ObjectMapper().writeValueAsString(view)));
			json.put("success", true);
			writerOrExport(json);
		}
	}
}
