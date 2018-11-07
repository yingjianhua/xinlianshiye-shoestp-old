package irille.Service.Pdt;

import com.google.inject.ImplementedBy;
import irille.Service.Pdt.Imp.PdtProductImp;
import irille.pub.idu.IduPage;
import irille.view.pdt.PdtProductBaseInfoView;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/7
 * Time: 14:50
 */
@ImplementedBy(PdtProductImp.class)
public interface IPdtProduct {
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

}
