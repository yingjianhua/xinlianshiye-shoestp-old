package irille.Service.Manage.Pdt.Imp;

import irille.Dao.PdtProductCatDao;
import irille.Service.Manage.Pdt.IPdtCatManageService;
import irille.view.Page;
import irille.view.usr.Manage.PdtCatsView;
import org.start2do.SetBean.SetBeans;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/8
 * Time: 11:32
 */
public class PdtCatManageServiceImp implements IPdtCatManageService {
    @Inject
    PdtProductCatDao pdtProductCatDao;

    @Override
    public Page getProductCatList(int start, int limit, int supplierId) {
        List<PdtCatsView> list = pdtProductCatDao.getPdtCatBySupplierId(supplierId).stream().map(map -> {
            PdtCatsView pdtCatsView=new PdtCatsView();
            return SetBeans.set(map, PdtCatsView.class);
        }).collect(Collectors.toList());
        return null;
    }
}
