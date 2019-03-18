package irille.homeAction.o2o;

import java.io.IOException;

import javax.inject.Inject;

import irille.Entity.O2O.O2oRegistration;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.homeAction.HomeAction;
import irille.platform.o2o.View.O2o_RegistrationView;
import irille.pub.LogMessage;
import irille.shop.o2o.O2O_RegistrationDao;
import lombok.Data;

@Data
public class O2oRegistrationAction extends HomeAction<O2oRegistration> {

  public static final LogMessage LOG = new LogMessage(O2oRegistrationAction.class);

  private O2o_RegistrationView view;

  @Inject private IO2OPdtServer io2OPdtServer;
  @Inject private O2O_RegistrationDao o2O_registrationDao;

  public void o2oList() throws IOException {
    write(io2OPdtServer.O2OList(getPurchase(), getStart(), getLimit()));
  }

  public void o2oPrivateList() throws IOException {
    write(io2OPdtServer.O2OPrivateList(getPurchase(), getStart(), getLimit()));
  }

  public void apply() throws IOException {
    boolean b = true;
    if (view == null) {
      b = false;
      writeErr("报名失败,请填写完整!");
      return;
    }
    if (view.getFullName() == null || view.getFullName().trim().equals("")) b = false;
    if (view.getGender() == null) b = false;
    if (view.getCountry() == null) b = false;
    if (view.getEmail() == null || view.getEmail().trim().equals("")) b = false;
    if (view.getTelphone() == null || view.getTelphone().trim().equals("")) b = false;
    if (view.getFootwear() == null || view.getFootwear().trim().equals("")) b = false;
    if (view.getMarketing() == null || view.getMarketing().trim().equals("")) b = false;
    if (view.getBuyertype() == null) b = false;
    if (view.getExhibitionCountry() == null || view.getExhibitionCountry().trim().equals(""))
      b = false;
    if (view.getActivityId() == null) b = false;
    if (!b) {
      writeErr("报名失败,请填写完整!");
      return;
    }
    o2O_registrationDao.ins(view);
    write();
  }
}
