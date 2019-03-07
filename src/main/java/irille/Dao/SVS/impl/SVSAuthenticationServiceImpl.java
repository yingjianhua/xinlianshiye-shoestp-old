package irille.Dao.SVS.impl;

import irille.Dao.SVS.SVSAuthenticationService;
import irille.Entity.SVS.SVSInfo;
import irille.view.SVS.SVSAuthenticationView;

public class SVSAuthenticationServiceImpl implements SVSAuthenticationService {
    @Override
    public SVSAuthenticationView getAutInfo(Integer pkey) {
        return SVSAuthenticationDao.querySVS(pkey);
    }
    @Override
    public void Authentication(Integer status, Integer grade, String reasons, Integer pkey) {
        SVSAuthenticationDao.updateSVS( status,  grade,  reasons,  pkey);
    }
}
