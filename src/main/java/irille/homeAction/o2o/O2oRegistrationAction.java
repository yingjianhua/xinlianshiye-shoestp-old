package irille.homeAction.o2o;

import java.io.IOException;

import irille.Entity.O2O.O2oRegistration;
import irille.homeAction.HomeAction;
import irille.pub.LogMessage;
import irille.shop.o2o.O2O_RegistrationDao;
import irille.shop.usr.UsrSupplierDAO;

public class O2oRegistrationAction extends HomeAction<O2oRegistration>{
	
	public static final LogMessage LOG = new LogMessage(O2oRegistrationAction.class);
	
	public void apply() throws IOException {
		O2oRegistration bean = getBean();
		boolean b = true;
		if(bean == null) {
			b = false;
			writeErr("报名失败,请填写完整!");
			return;
		}
		if(bean.getFullName() == null || bean.getFullName().trim().equals(""))
			b = false;
		if(bean.getGender() == null)
			b = false;
		if(bean.getCountry() == null)
			b = false;
		if(bean.getEmail() == null || bean.getEmail().trim().equals(""))
			b = false;
		if(bean.getTelphone() == null || bean.getTelphone().trim().equals(""))
			b = false;
		if(bean.getFootwear() == null || bean.getFootwear().trim().equals(""))
			b = false;
		if(bean.getMarketing() == null)
			b = false;
		if(bean.getBuyerType() == null)
			b = false;
		if(bean.getExhibitionCountry() == null)
			b = false;
		if(bean.getActivityId() == null)
			b = false;
		if(!b) {
			writeErr("报名失败,请填写完整!");
			return;
		}
		O2O_RegistrationDao.ins(bean);
		write();
	}
}
