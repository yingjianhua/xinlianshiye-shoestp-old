package irille.Service.Manage.Pdt;

import com.google.inject.ImplementedBy;
import irille.Service.Manage.Pdt.Imp.PdtCatManageServiceImp;
import irille.pub.tb.FldLanguage;
import irille.view.Page;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/8 Time: 11:18
 */
@ImplementedBy(PdtCatManageServiceImp.class)
public interface IPdtCatManageService {

    Page getProductCatList(int start, int limit, int supplierId);

    /**
     * 获取产品页信息
     * Date 2018/12/20 16:00
     * zjl
     */
    Page getProductSEOs(Integer start,Integer limit,Integer supplier, FldLanguage.Language language);

}
