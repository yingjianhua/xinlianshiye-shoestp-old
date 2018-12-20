package irille.Service.Pdt;

import com.google.inject.ImplementedBy;
import irille.Service.Pdt.Imp.PdtProductServiceImp;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.shop.usr.UsrPurchase;
import irille.view.pdt.PdtProductBaseInfoView;
import irille.view.pdt.PdtProductCatView;
import irille.view.v2.Pdt.PdtNewPdtInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 14:50
 */
@ImplementedBy(PdtProductServiceImp.class)
public interface IPdtProductService {
    /**
     * @Description: 首页 获取新品
     * @date 2018/11/7 15:05
     * @author lijie@shoestp.cn
     */
    List<PdtProductBaseInfoView> getNewProductsListByIndex(IduPage iduPage);

    /**
     * @Description: 商品列表页 热榜
     * @date 2018/11/7 15:45
     * @author lijie@shoestp.cn
     */
    List<PdtProductBaseInfoView> getProductsIndexHot(IduPage iduPage);


    /**
     * @Description: 商品列表页 商品列表
     * @date 2018/11/7 15:45
     * @author lijie@shoestp.cn
     */
    Map getProductListByCategory(IduPage page, String[] flds, boolean order, int category, String spec, String onlyFld, String keyword, Integer type);


    List<PdtProductBaseInfoView> getYouMayLike(IduPage iduPage, int cat);

    List<PdtNewPdtInfo> getRandomPdt(Integer limit, int cat, UsrPurchase purchase);


    /**
     * @Description: 商品页  侧边栏 商品分类
     * @date 2018/11/9 9:40
     * @author lijie@shoestp.cn
     */
    List<PdtProductCatView> getProductsIndexCategories(int start, int limit, FldLanguage.Language language);

    /**
     * @Description:
     * @date 2018/12/17 9:53
     * @author lijie@shoestp.cn
     */
    List<PdtNewPdtInfo> getNewProducts(IduPage page, UsrPurchase pkey, FldLanguage.Language language);

}
