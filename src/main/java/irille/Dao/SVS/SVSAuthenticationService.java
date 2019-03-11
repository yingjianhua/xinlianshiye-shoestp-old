package irille.Dao.SVS;


import com.google.inject.ImplementedBy;
import irille.Dao.SVS.impl.SVSAuthenticationServiceImpl;
import irille.shop.usr.UsrSupplier;
import irille.view.SVS.SVSAuthenticationView;

@ImplementedBy(SVSAuthenticationServiceImpl.class)
public interface SVSAuthenticationService {
    //查询
    SVSAuthenticationView getAutInfo(Integer pkey) throws Exception;

    //认证
    void Authentication(UsrSupplier supplier, Integer status, Integer grade, String reasons, Integer pkey)throws Exception;
}
