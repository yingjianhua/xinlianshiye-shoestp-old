package irille.Dao.SVS;

import com.google.inject.ImplementedBy;
import irille.Dao.SVS.impl.SVSAuthenticationServiceImpl;
import irille.Entity.SVS.SVSInfo;
import irille.view.SVS.SVSAuthenticationView;

@ImplementedBy(SVSAuthenticationServiceImpl.class)
public interface SVSAuthenticationService {
    //查询
    SVSAuthenticationView getAutInfo(Integer pkey);
    //认证
    void Authentication(Integer status, Integer grade, String reasons, Integer pkey);
}
