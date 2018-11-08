package irille.Service.Manage.Pdt;

import irille.view.Page;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/8
 * Time: 11:18
 */
public interface IPtdCatService {
    Page getProductCatList(int start, int limit,int supplierId);

}
