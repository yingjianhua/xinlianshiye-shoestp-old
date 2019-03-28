package irille.Service.Manage.Pdt;

import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.Pdt.Imp.PdtCatManageServiceImp;
import irille.platform.pdt.View.pdtCatView.PdtCatView;
import irille.pub.tb.FldLanguage;
import irille.sellerAction.view.ProductSEOsView;
import irille.view.Page;
import irille.view.pdt.CategoryView;
import irille.view.pdt.PdtProductCatView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/8 Time: 11:18 */
@ImplementedBy(PdtCatManageServiceImp.class)
public interface IPdtCatManageService {

  Page getProductCatList(int start, int limit, int supplierId);

  /** 获取产品页信息 Date 2018/12/20 16:00 zjl */
  Page getProductSEOs(
      Integer start, Integer limit, Integer supplier, FldLanguage.Language language);

  /** 获取单个产品页SEO(用于修改) Date 2018/12/24 14:05 zjl */
  ProductSEOsView getSEO(Integer product);

  /** 修改单个SEO Date 2018/12/24 15:38 zjl */
  void updSEO(ProductSEOsView view);

  /** 获取所有一级分类 */
  List<CategoryView> pList();

  /** 获取所有三级分类 */
  List<PdtCatView> findCategory(Integer id);

  /** 获取所有三级分类 */
  List<PdtProductCatView> findCategoryCascader(Integer id);
}
