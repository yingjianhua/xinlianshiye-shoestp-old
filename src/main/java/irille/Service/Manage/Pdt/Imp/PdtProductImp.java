package irille.Service.Manage.Pdt.Imp;

import irille.Dao.PdtProductDao;
import irille.Service.Manage.Pdt.IPdtProduct;
import irille.pub.tb.FldLanguage;
import irille.view.Page;
import irille.view.pdt.PdtProductCatView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 15:55
 */
public class PdtProductImp implements IPdtProduct {

    @Inject
    PdtProductDao pdtProductDao;

    @Override
    public Page getProductList(String name, String number, Integer supplierId, int cat, int start, int limit) {
        return pdtProductDao.getProductListManage(name, number, supplierId, cat, start, limit);
    }

    @Override
    public List<PdtProductCatView> getCatChildNodesByCatId(int i, FldLanguage.Language supplierLanguage) {
        return pdtProductDao.getCatChildNodesByCatId(i, supplierLanguage);
    }
}
