package irille.homeAction.o2o;

import java.io.IOException;

import irille.Entity.O2O.O2oRegistration;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.homeAction.HomeAction;
import irille.platform.o2o.View.O2o_RegistrationView;
import irille.pub.LogMessage;
import irille.shop.o2o.O2O_RegistrationDao;
import irille.shop.usr.UsrSupplierDAO;
import lombok.Data;

import javax.inject.Inject;

@Data
public class O2oRegistrationAction extends HomeAction<O2oRegistration>{
	
	public static final LogMessage LOG = new LogMessage(O2oRegistrationAction.class);
	
	private O2o_RegistrationView view;

	@Inject
	private IO2OPdtServer io2OPdtServer;

	public void o2oList() throws IOException {
		write(io2OPdtServer.O2OList(getPurchase(),getStart(),getLimit()));
	}
	
	
	public void apply() throws IOException {
		boolean b = true;
		if(view == null) {
			b = false;
			writeErr("报名失败,请填写完整!");
			return;
		}
		if(view.getFullName() == null || view.getFullName().trim().equals(""))
			b = false;
		if(view.getGender() == null)
			b = false;
		if(view.getCountry() == null)
			b = false;
		if(view.getEmail() == null || view.getEmail().trim().equals(""))
			b = false;
		if(view.getTelphone() == null || view.getTelphone().trim().equals(""))
			b = false;
		if(view.getFootwear() == null || view.getFootwear().trim().equals(""))
			b = false;
		if(view.getMarketing() == null || view.getMarketing().trim().equals(""))
			b = false;
		if(view.getBuyertype() == null)
			b = false;
		if(view.getExhibitionCountry() == null || view.getExhibitionCountry().trim().equals("") )
			b = false;
		if(view.getActivityId() == null)
			b = false;
		if(!b) {
			writeErr("报名失败,请填写完整!");
			return;
		}
		O2O_RegistrationDao.ins(view);
		write();
	}
}
