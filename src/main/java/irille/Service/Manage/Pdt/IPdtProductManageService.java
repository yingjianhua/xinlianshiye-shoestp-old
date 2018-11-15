package irille.Service.Manage.Pdt;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.Pdt.Imp.PdtProductManageServiceImp;
import irille.pub.tb.FldLanguage;
import irille.view.Page;
import irille.view.pdt.PdtProductCatView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 15:53
 */
@ImplementedBy(PdtProductManageServiceImp.class)
public interface IPdtProductManageService {
    Page getProductList(String name,String number,Integer supplierId,int cat,int start,int limit);

    List<PdtProductCatView> getCatChildNodesByCatId(int i, FldLanguage.Language supplierLanguage);
}