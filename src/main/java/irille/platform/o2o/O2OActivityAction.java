package irille.platform.o2o;

import java.io.IOException;

import javax.inject.Inject;

import org.json.JSONException;

import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.Enums.O2O_PrivateExpoPdtStatus;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Service.Manage.O2O.O2OActivityService;
import irille.action.ActionBase;
import irille.view.O2O.O2OActivityView;
import irille.view.O2O.PdtSearchView;
import lombok.Getter;
import lombok.Setter;

@Setter
public class O2OActivityAction extends ActionBase<O2O_Activity> implements IO2OActivityAction {

  private static final long serialVersionUID = 1L;

  @Inject private O2OActivityService o2OActivityService;

  @Getter private O2OActivityView activity;

  @Getter private PdtSearchView search;
  private String reason;
  private Integer id;
  private String keyword;
  private Integer status;
  private Integer verify_status;
  private String catName;
  private String pdtName;

  @Override
  public Class<O2O_Activity> beanClazz() {
    return O2O_Activity.class;
  }

  @Override
  public void list() throws IOException {
    write(o2OActivityService.list(getStart(), getLimit(), activity));
  }

  @Override
  public void privetePdtlist() throws IOException {
    if (getLimit() == 0) setLimit(20);
    if (getStart() < 0) setStart(0);
    write(
        o2OActivityService.priveteList(
            getStart(), getLimit(), status, verify_status, catName, keyword, pdtName));
  }

  @Override
  public void cancel() throws IOException, JSONException {
    o2OActivityService.cancel(id);
    writeSuccess();
  }

  /** 发布或编辑活动 */
  @Override
  public void deploy() throws IOException, JSONException {
    o2OActivityService.deploy(activity);
    writeSuccess();
  }

  /** 获取报名列表 */
  @Override
  public void enrollList() throws IOException {
    write(o2OActivityService.enrollList(search, getStart(), getLimit(), 0));
  }

  /**
   * 获取申请上下架列表
   *
   * @throws IOException
   */
  public void upperLowerList() throws IOException {
    write(o2OActivityService.enrollList(search, getStart(), getLimit(), 1));
  }

  /** 审核通过 */
  public void pass() throws IOException, JSONException {
    o2OActivityService.appr(id, null, O2O_ProductStatus.PASS);
    writeSuccess();
  }

  /** 私人展厅审核产品通过 */
  public void privatefindAppr() throws IOException, JSONException {
    o2OActivityService.privatefindAppr(id, null, O2O_PrivateExpoPdtStatus.PASS);
    writeSuccess();
  }

  /** 审核拒绝 */
  public void refuse() throws IOException, JSONException {
    o2OActivityService.appr(id, reason, O2O_ProductStatus.Failed);
    writeSuccess();
  }

  /** 私人展厅审核产品审核拒绝 */
  public void privatefindRefuse() throws IOException, JSONException {
    o2OActivityService.privatefindAppr(id, reason, O2O_PrivateExpoPdtStatus.Failed);
    writeSuccess();
  }

  /** 确认下架 */
  public void lower() throws IOException, JSONException {
    o2OActivityService.lowerAndUpper(id, null, O2O_ProductStatus.PASS);
    writeSuccess();
  }

  /** 私人订购会商品确认下架 */
  public void privateLower() throws IOException, JSONException {
    o2OActivityService.privateLowerAndUpper(id, reason, O2O_PrivateExpoPdtStatus.OFF);
    writeSuccess();
  }

  /** 拒绝下架 */
  public void upper() throws IOException, JSONException {
    o2OActivityService.lowerAndUpper(id, reason, O2O_ProductStatus.Failed);
    writeSuccess();
  }

  public void load() throws IOException {
    write(o2OActivityService.load(id));
  }
}
