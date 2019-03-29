package irille.platform.pdt;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import irille.action.ActionBase;
import irille.core.sys.Sys.OYn;
import irille.pub.svr.LoginUserMsg;
import irille.shop.pdt.PdtAttrCat;
import irille.shop.pdt.PdtAttrCatDAO;
import lombok.Getter;
import lombok.Setter;

/** Created by admin on 2019/1/17. */
public class PdtAttrCatAction extends ActionBase<PdtAttrCat> {
  @Override
  public Class beanClazz() {
    return PdtAttrCat.class;
  }

  public PdtAttrCat getBean() {
    return _bean;
  }

  public void setBean(PdtAttrCat bean) {
    this._bean = bean;
  }

  @Getter @Setter private String name; // 查询属性分类名称

  @Getter @Setter private List<Integer> cat; // 分类ID

  @Inject private PdtAttrCatDAO pdtAttrCatDAO;

  /**
   * 查询所有分类
   *
   * @throws IOException
   */
  public void pdtAttrCatlist() throws IOException {
    write(pdtAttrCatDAO.getAttrCat(getStart(), getLimit(), name));
  }

  /** 删除分类 */
  public void del() throws IOException {
    if (!pdtAttrCatDAO.getCount(getBean().getPkey())) {
      writeErr("该属性已被关联到发布的产品中");
      return;
    }
    PdtAttrCatDAO.Del cp = new PdtAttrCatDAO.Del();
    cp.setBKey(getBean().getPkey());
    cp.commit();
    write();
  }

  /** 添加分类 */
  @Override
  public void ins() throws IOException {
    PdtAttrCatDAO.InsAttrCat ic = new PdtAttrCatDAO.InsAttrCat();
    LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(loginUserMsg.get_user().getPkey());
    ic.setPdtCat(cat);
    ic.setB(getBean());
    ic.commit();
    write();
  }

  /** 修改分类 */
  @Override
  public void upd() throws IOException {
    PdtAttrCatDAO.UpdAttrCat ic = new PdtAttrCatDAO.UpdAttrCat();
    LoginUserMsg loginUserMsg = (LoginUserMsg) this.session.get(LOGIN);
    getBean().setCreateBy(loginUserMsg.get_user().getPkey());
    ic.setB(getBean());
    ic.setCat(cat);
    ic.commit();
    write();
  }
  /**
   * 启用
   *
   * @auther liyichao
   * @throws IOException
   */
  public void enable() throws IOException {
    PdtAttrCatDAO.UpdState cp = new PdtAttrCatDAO.UpdState();
    cp.setBKey(getBean().getPkey());
    cp.setState(OYn.NO.getLine().getKey());
    cp.commit();
    write();
  }
  /**
   * 停用
   *
   * @auther liyichao
   * @throws IOException
   */
  public void unEnable() throws IOException {
    PdtAttrCatDAO.UpdState cp = new PdtAttrCatDAO.UpdState();
    cp.setBKey(getBean().getPkey());
    cp.setState(OYn.YES.getLine().getKey());
    cp.commit();
    write();
  }

  /** 关闭分类关联属性 */
  //  public void close() throws IOException {
  //    PdtAttrCatDAO.UpdState cp = new PdtAttrCatDAO.UpdState();
  //    cp.setBKey(getBean().getPkey());
  //    cp.setState(getBean().getState());
  //    cp.commit();
  //    write();
  //  }
}
