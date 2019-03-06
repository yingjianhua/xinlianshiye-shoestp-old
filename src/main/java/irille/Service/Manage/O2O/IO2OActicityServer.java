package irille.Service.Manage.O2O;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.O2O.Imp.O2OActicityServerImp;
import irille.shop.usr.UsrSupplier;
@ImplementedBy(O2OActicityServerImp.class)
public interface IO2OActicityServer{
    public void enroll(UsrSupplier supplier, Integer activity, String name, String tel, String pdts);
}
