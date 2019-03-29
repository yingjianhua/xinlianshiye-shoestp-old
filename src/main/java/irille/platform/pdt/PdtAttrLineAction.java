package irille.platform.pdt;

import java.io.IOException;

import javax.inject.Inject;

import irille.action.ActionBase;
import irille.action.dataimport.util.StringUtil;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtAttrLine;
import irille.shop.pdt.PdtAttrLineDAO;
import irille.shop.plt.PltConfigDAO;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 属性明细
 *
 * @author lingjian
 * @date 2019/1/23 9:05
 */
@Data
public class PdtAttrLineAction extends ActionBase<PdtAttrLine> {

  @Override
  public Class beanClazz() {
    return PdtAttrLine.class;
  }

  public PdtAttrLine getBean() {
    return _bean;
  }

  public void setBean(PdtAttrLine bean) {
    this._bean = bean;
  }

  private String main; // 搜索的属性明细类目

  @Inject private PdtAttrLineDAO.UpdAttrLine upd;
  @Inject private PdtAttrLineDAO.InsAttrLine dl;
  /**
   * 查询产品属性列表+搜索
   *
   * @author lingjian
   * @date 2019/1/23 10:30
   */
  public void list() throws Exception {
    write(PdtAttrLineDAO.listAttrLine(main, getStart(), getLimit()));
  }

  /**
   * 新增产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/23 9:17
   */
  public void ins() throws IOException {
    verify(getBean());
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    dl.setB(getBean());
    dl.commit();
    write();
  }

  /**
   * 修改产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/23 9:17
   */
  public void upd() throws IOException {
    verify(getBean());
    LoginUserMsg lu = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(lu.get_user().getPkey());
    upd.setB(getBean());
    upd.commit();
    write();
  }

  /**
   * 删除产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/23 9:17
   */
  public void delete() throws IOException {
    PdtAttrLineDAO.DelAttrLine remove = new PdtAttrLineDAO.DelAttrLine();
    remove.setBKey(getBean().getPkey());
    remove.commit();
    write();
  }

  public void verify(PdtAttrLine attrLine) throws IOException {
    if (!StringUtil.hasValue(attrLine.getName())) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "名称不能为空");
    }
    JSONObject json;
    try {
      json = new JSONObject(attrLine.getName());
      Object object = json.get(PltConfigDAO.manageLanguage().toString());
      if (!StringUtil.hasValue(object)) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "默认语言的的分类名称不可为空");
      }
      if (object.toString().length() > 20) {
        throw new WebMessageException(ReturnCode.service_wrong_data, "名称不能过长");
      }
    } catch (JSONException e) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
    if (attrLine == null || attrLine.getMain() <= 0) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "参数错误");
    }
  }
}
