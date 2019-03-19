package irille.platform.usr;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import irille.action.MgtAction;
import irille.platform.usr.View.UsrSupplierNewView;
import irille.pub.Exp;
import irille.pub.util.upload.ImageUpload;
import irille.shop.usr.UsrAnnex;
import irille.shop.usr.UsrMain;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierDAO;
import irille.view.plt.ImageView;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
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
   * @Description: 供应商选中器列表平台
   *
   * @date 2019/1/23 19:20
   * @anthor wilson zhang
   */
  private String fldvalue;

  private String condition;

  public void ListUsrSup() throws Exception {
    write(UsrSupplierDAO.listsupselect(fldvalue, condition, getStart(), getLimit()));
  }

  private Integer supplier;
  private String name;
  private Integer category;
  private Integer status;
  private Integer storeStatus;
  private Integer svsGrade;
  private String fileFileName = "";
  private File file;

  /**
   * 获取店铺列表
   *
   * @throws IOException
   * @author: lingjian @Date: 2019/3/11 10:48
   */
  public void getShopList() throws IOException {
    write(UsrSupplierDAO.getShopList(getStart(), getLimit(), name, storeStatus, svsGrade));
  }

  /**
   * 获取开店申请列表
   *
   * @throws IOException
   * @author: lingjian @Date: 2019/3/4 15:59
   */
  public void getShopApplication() throws IOException {
    write(UsrSupplierDAO.getShopApplication(getStart(), getLimit(), name, status));
  }

  private String certPhotoName;
  private String idCardFrontPhotoName;
  private String contactsIdCardFrontPhotoName;
  private String mainEmail;
  private String mainContacts;
  private String mainTelphone;
  private Integer mainProvince;
  private Integer mainCity;
  private Integer mainZone;

  /**
   * 更新店铺开启时的信息
   *
   * @throws IOException
   * @author: lingjian @Date: 2019/3/11 10:48
   */
  public void updShopList() throws IOException {
    try {
      UsrAnnex annex = UsrAnnex.chkUniqueSupplier(false, getBean().getPkey());
      if (annex != null) {
        annex.setCertPhotoName(certPhotoName);
        annex.setIdCardFrontPhotoName(idCardFrontPhotoName);
        annex.setContactsIdCardFrontPhotoName(contactsIdCardFrontPhotoName);
        annex.upd();
      } else {
        UsrAnnex annex1 = new UsrAnnex();
        annex1.setSupplier(getBean().getPkey());
        annex1.setCertPhotoName(certPhotoName);
        annex1.setIdCardFrontPhotoName(idCardFrontPhotoName);
        annex1.setContactsIdCardFrontPhotoName(contactsIdCardFrontPhotoName);
        annex1.ins();
      }
      UsrMain main = UsrMain.chkUniqueEmail(false, mainEmail);
      if (mainEmail != null) {
        main.setEmail(mainEmail);
        main.setContacts(mainContacts);
        main.setTelphone(mainTelphone);
        if (mainProvince != null) {
          main.setProvince(mainProvince);
        }
        if (mainCity != null) {
          main.setCity(mainCity);
        }
        if (mainZone != null) {
          main.setZone(mainZone);
        }
        main.upd();
      }
      UsrSupplier newSupplier = UsrSupplierDAO.updInfo(getBean());
      newSupplier.setStoreopenTime(getBean().getStoreopenTime());
      newSupplier.upd();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  /**
   * 更新店铺关闭后的信息
   *
   * @throws IOException
   */
  public void updStore() throws IOException {
    try {
      UsrSupplier newSupplier = UsrSupplierDAO.updStore(getBean());
      newSupplier.upd();
      write();
    } catch (Exp e) {
      writeErr(e.getLastMessage());
    }
  }

  private String id;

  /**
   * 根据id获取供应商信息
   *
   * @throws IOException
   * @author: lingjian @Date: 2019/3/8 10:41
   */
  public void getSupplierById() throws IOException {
    write(UsrSupplierDAO.getSupplierById(id));
  }

  private String reason;
  private Date storeopenTime;

  /**
   * 审核
   *
   * @throws IOException
   * @author: lingjian @Date: 2019/3/11 10:45
   */
  public void reviewStatus() throws IOException {
    UsrSupplier supplier = UsrSupplierDAO.reviewStatus(id, status, reason, storeopenTime);
    UsrSupplierNewView usrSupplierNewView = new UsrSupplierNewView();
    usrSupplierNewView.setStatus(supplier.getStatus());
    write(usrSupplierNewView);
  }

  /** @Description: 获取供应商列表 *@date 2019/1/21 09:05 *@anthor zjl */
  public void getSuppliers() throws IOException {
    write(UsrSupplierDAO.getSuppliers(getStart(), getLimit(), name, category, status));
  }

  /** @Description: 获取所有状态 *@date 2019/1/21 10:33 *@anthor zjl */
  public void getStatus() throws IOException {
    write(UsrSupplierDAO.getStatus());
  }

  /** @Description: 审核/弃审 *@date 2019/1/21 10:44 *@anthor zjl */
  public void updStatus() throws IOException {
    UsrSupplierDAO.UpdStatus upd = new UsrSupplierDAO.UpdStatus();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /** @Description: 获取供应商基本信息 *@date 2019/1/21 14:20 *@anthor zjl */
  public void getBasicInformation() throws IOException {
    write(UsrSupplierDAO.getBasicInformation(supplier));
  }

  /** @Description: 更新供应商基本信息 *@date 2019/1/21 14:20 *@anthor zjl */
  public void updBasicInformation() throws IOException {
    String regex = "^[\\w]{1,16}@+\\w{1,15}.\\w{2,5}$";
    Pattern pattern = Pattern.compile(regex);
    if (getBean().getName().isEmpty()) {
      writeErr("名称不可为空");
      return;
    }
    if (getBean().getCategory() == null) {
      writeErr("供应商分类不可为空");
      return;
    }
    if (getBean().getIsAuth() == null) {
      writeErr("供应商认证不可为空");
      return;
    }
    if (getBean().getEntity().isEmpty()) {
      writeErr("企业法人不可为空");
      return;
    }
    if (getBean().getEmail().isEmpty() && !(pattern.matcher(getBean().getEmail()).matches())) {
      writeErr("邮箱不可为空或者邮箱格式不正确");
      return;
    }
    if (getBean().getSort() == null) {
      writeErr("排序号不可为空");
      return;
    }

    UsrSupplierDAO.UpdBasicInformation upd = new UsrSupplierDAO.UpdBasicInformation();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /** @Description: 获取供应商页面资料 *@date 2019/1/21 14:20 *@anthor zjl */
  public void getPageInformation() throws IOException {
    write(UsrSupplierDAO.getPageInformation(supplier));
  }

  /** @Description: 更新供应商页面资料 *@date 2019/1/21 14:58 *@anthor zjl */
  public void updPageInformation() throws IOException {
    if (getBean().getCountry()==null){
      writeErr("国家不能为空");
      return;
    }
    if (getBean().getProvince()==null){
      writeErr("省份不能为空");
      return;
    }
    if (getBean().getIsPro()==null){
      writeErr("供应商首页产品展示不能为空");
      return;
    }
    UsrSupplierDAO.UpdPageInformation upd = new UsrSupplierDAO.UpdPageInformation();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /** @Description: 获取供应商个性装修 *@date 2019/1/21 15:05 *@anthor zjl */
  public void getPersonalityDecoration() throws IOException {
    write(UsrSupplierDAO.getPersonalityDecoration(supplier));
  }

  /** @Description: 更新供应商个性装修 *@date 2019/1/21 15:05 *@anthor zjl */
  public void updPersonalityDecoration() throws IOException {
    UsrSupplierDAO.UpdPersonalityDecoration upd = new UsrSupplierDAO.UpdPersonalityDecoration();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /** @Description: 获取供应商营销设置 *@date 2019/1/21 15:05 *@anthor zjl */
  public void getmarketingSettings() throws IOException {
    write(UsrSupplierDAO.getmarketingSettings(supplier));
  }

  /** @Description: 更新供应商营销设置 *@date 2019/1/21 15:05 *@anthor zjl */
  public void updmarketingSettings() throws IOException {
    UsrSupplierDAO.UpdMarketingSettings upd = new UsrSupplierDAO.UpdMarketingSettings();
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /**
   * 上传图片 @Date 219/1/14 15:13
   *
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
