package irille.platform.pdt;

import java.io.IOException;

import javax.inject.Inject;

import irille.Service.Manage.Pdt.IPdtCatManageService;
import irille.action.ActionBase;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtCat;
import irille.shop.pdt.PdtCatDAO;
import lombok.Getter;
import lombok.Setter;

public class PdtCatAction extends ActionBase<PdtCat> {
  @Override
  public Class beanClazz() {
    return PdtCat.class;
  }

  public PdtCat getBean() {
    return _bean;
  }

  public void setBean(PdtCat bean) {
    this._bean = bean;
  }

  @Getter @Setter private Integer enabled; // 查询是否启用
  @Getter @Setter private String title; // 查询标题
  @Getter @Setter private String name;
  @Getter @Setter private Integer subjectionCat;
  @Getter @Setter private String seoDescription;
  @Getter @Setter private String seoKeyword;
  @Getter @Setter private String seoName;
  @Getter @Setter private Integer id;

  @Inject private PdtCatDAO pdtCatDAO;

  @Inject private IPdtCatManageService iPdtCatManageService;

  /** @Description: 查询所有分类 *@anthor kouhanyang */
  public void pdtCatlist() throws IOException {
    write(pdtCatDAO.listPdtCat(getStart(), getLimit(), enabled, title, subjectionCat));
  }

  /** @Description: 删除分类 *@anthor kouhanyang */
  public void del() throws IOException {
    PdtCatDAO.Del cp = new PdtCatDAO.Del();
    cp.setBKey(getBean().getPkey());
    cp.commit();
    write();
  }

  /** @Description: 修改是否启用 *@anthor kouhanyang */
  public void countryenable() throws Exception {
    PdtCatDAO.enable ph = new PdtCatDAO.enable();
    ph.setB(getBean());
    ph.commit();
    write();
  }

  /** @Description: 添加分类 *@anthor kouhanyang */
  public void ins() throws Exception {
    LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
    Integer createBy = loginUserMsg.get_user().getPkey();
    PdtCatDAO.addPdtCat(
        name, enabled, subjectionCat, seoDescription, seoKeyword, seoName, createBy);
    write();
  }

  /** @Description: 修改分类 *@anthor kouhanyang */
  public void upd() throws IOException {
    PdtCatDAO.updPdtCat(name, enabled, subjectionCat, seoDescription, seoKeyword, seoName, id);
    write();
  }

  /** 获取所有一级分类 */
  public void pList() throws IOException {
    write(iPdtCatManageService.pList());
  }
}
