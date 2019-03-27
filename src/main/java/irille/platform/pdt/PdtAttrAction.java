package irille.platform.pdt;

import java.io.IOException;

import javax.inject.Inject;

import irille.action.ActionBase;
import irille.action.dataimport.util.StringUtil;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtAttr;
import irille.shop.pdt.PdtAttrDAO;
import irille.shop.pdt.PdtSize;
import irille.shop.plt.PltConfigDAO;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 产品属性
 *
 * @author lingjian
 * @date 2019/1/22 15:59
 */
@Data
public class PdtAttrAction extends ActionBase<PdtAttr> {

  @Override
  public Class beanClazz() {
    return PdtSize.class;
  }

  public PdtAttr getBean() {
    return _bean;
  }

  public void setBean(PdtAttr bean) {
    this._bean = bean;
  }

  @Inject private PdtAttrDAO.UpdAttr upd;
  @Inject private PdtAttrDAO.InsAttr dl;

  private String name; // 搜索的产品属性名称
  private String category; // 搜索的产品属性类目

  /**
   * 查询产品属性列表+搜索
   *
   * @author lingjian
   * @date 2019/1/22 16:04
   */
  public void list() throws Exception {
    write(PdtAttrDAO.listAttr(name, category, getStart(), getLimit()));
  }

  /**
   * 新增产品属性
   *
   * @throws IOException
   * @author lingjian
   * @date 2019/1/22 13:36
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
   * @date 2019/1/22 13:36
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
   * @date 2019/1/22 13:38
   */
  public void delete() throws IOException {
    PdtAttrDAO.DelAttr remove = new PdtAttrDAO.DelAttr();
    remove.setBKey(getBean().getPkey());
    remove.commit();
    write();
  }

  public void verify(PdtAttr attr) throws IOException {
    if (!StringUtil.hasValue(attr.getName()))
      throw new WebMessageException(ReturnCode.service_wrong_data, "名称不能为空");
    if (attr.getCategory() == null || attr.getCategory() <= 0) {
      throw new WebMessageException(ReturnCode.service_wrong_data, "分类不能为空");
    }
    JSONObject json;
    try {
      json = new JSONObject(attr.getName());
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
  }
}
