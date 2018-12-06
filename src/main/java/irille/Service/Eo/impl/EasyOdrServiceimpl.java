package irille.Service.Eo.impl;

import irille.Dao.EO.EasyOdrDao;
import irille.Service.Eo.EasyOdrService;
import irille.view.EO.easyodrView;

import javax.inject.Inject;
import java.util.List;

public class EasyOdrServiceimpl implements EasyOdrService {
    @Inject
    private EasyOdrDao easyOdrDao;
    @Override
    public void generateOrder(Integer getPurchaseLineid, Integer Purchaseid, List<easyodrView> list) throws Exception {
        easyOdrDao.generate0rder(getPurchaseLineid,Purchaseid,list);
    }
}
