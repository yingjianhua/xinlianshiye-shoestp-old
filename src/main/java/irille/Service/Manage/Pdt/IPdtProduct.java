package irille.Service.Manage.Pdt;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.Pdt.Imp.PdtProductImp;
import irille.view.Page;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 15:53
 */
@ImplementedBy(PdtProductImp.class)
public interface IPdtProduct {
    Page getProductList(String name,String number,Integer supplierId,int cat,int start,int limit);
}
