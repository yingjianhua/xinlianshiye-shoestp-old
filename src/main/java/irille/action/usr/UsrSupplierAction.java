package irille.action.usr;

import irille.action.MgtAction;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierDAO;

public class UsrSupplierAction extends MgtAction<UsrSupplier> {
  public UsrSupplier getBean() {
    return _bean;
  }

  public void setBean(UsrSupplier bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return UsrSupplier.class;
  }

  public void updBase() throws Exception {
    UsrSupplierDAO.UpdBase upd = new UsrSupplierDAO.UpdBase();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  public void updPage() throws Exception {
    System.out.println("<<<" + getBean().getHeadPic());
    UsrSupplierDAO.UpdPage upd = new UsrSupplierDAO.UpdPage();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  public void updDiy() throws Exception {
    UsrSupplierDAO.UpdDiy upd = new UsrSupplierDAO.UpdDiy();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  public void updMarketing() throws Exception {
    UsrSupplierDAO.UpdMarketing upd = new UsrSupplierDAO.UpdMarketing();
    upd.setB(getBean());
    upd.commit();
    writeSuccess(upd.getB());
  }

  /**
   * 供应商审核
   *
   * @author yingjianhua
   */
  public void approve() throws Exception {
    UsrSupplier bean = UsrSupplierDAO.approve((Integer) getPkey());
    writeSuccess(bean);
  }

  /**
   * 供应商弃审
   *
   * @author yingjianhua
   */
  public void unapprove() throws Exception {
    UsrSupplier bean = UsrSupplierDAO.unapprove((Integer) getPkey());
    writeSuccess(bean);
  }

  private String _mmNew; // 新密码
  private String _mmCheck; // 新密码确认

  @Override
  public UsrSupplier insRun() throws Exception {
    UsrSupplierDAO.Ins ins = new UsrSupplierDAO.Ins();
    ins.setB(_bean);
    ins._mm = getMmNew();
    ins._mmcheck = getMmCheck();
    ins.commit();
    return ins.getB();
  }

  public String getMmNew() {
    return _mmNew;
  }

  public void setMmNew(String _mmNew) {
    this._mmNew = _mmNew;
  }

  public String getMmCheck() {
    return _mmCheck;
  }

  public void setMmCheck(String _mmCheck) {
    this._mmCheck = _mmCheck;
  }
}
