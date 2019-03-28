package irille.Service.Usr;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.view.usr.FavoritesView;
import com.xinlianshiye.shoestp.shop.view.usr.SuplierDetailView;

import irille.Service.Usr.Imp.UsrSupplierServiceImp;
import irille.core.sys.Sys;
import irille.homeAction.usr.dto.SupplierListView;
import irille.pub.idu.IduPage;
import irille.pub.tb.FldLanguage;
import irille.view.Page;
import irille.view.usr.SupplierInfoView;
import irille.view.usr.UsrSupplierInfView;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/5 Time: 16:23 */
@ImplementedBy(UsrSupplierServiceImp.class)
public interface IUsrSupplierService {

  /**
   * @Description: 根据分类获取收藏夹内容
   *
   * @author lijie@shoestp.cn
   * @date 2018/8/22 18:23
   */
  List<FavoritesView> getFavoritesListByCat(IduPage page, int cat, int purId, Sys.OYn showState);

  /**
   * @Description: 根据分类获取收藏数
   *
   * @date 2018/11/12 16:22
   */
  Long getFavoritesCountByCat(IduPage page, int cat, int purId, Sys.OYn showState);

  /**
   * @Description: 供应商页
   *
   * @date 2018/11/9 14:37
   * @author lijie@shoestp.cn
   */
  List<SupplierListView> getSupplierListAndPdtList(IduPage iduPage, FldLanguage.Language language);

  /**
   * @Description: 首页供应商列表
   *
   * @date 2018/11/9 14:08
   * @author lijie@shoestp.cn
   */
  List getSupplierInfo(IduPage page);

  /*
   *   供应商中心列表
   * @Author HuangHaoBin
   **/
  Page listSupplier(Integer start, Integer limit);
  /**
   * 3.1.1供应商列表
   *
   * @author GS
   * @param start
   * @param limit
   * @param storeName
   * @param targetMarket
   * @param processType
   * @param grade
   * @param pdtCategory
   * @return
   */
  Page listSupplier(
      Integer start,
      Integer limit,
      String storeName,
      String targetMarket,
      Integer processType,
      String grade,
      Integer pdtCategory,
      Integer checkType);

  /*
   *   判断用户类型 0:普通用户 1:商家
   * @Author HuangHaoBin
   **/
  Integer isSupplier(String loginName);

  SuplierDetailView getSuplierDetail(Integer supplierPkey);
  /**
   * 供应商详情(暂时针对手机端提供接口)
   *
   * @date 2019/03/08
   * @author GS
   * @return
   */
  SupplierInfoView getSupplierInfo(Integer pkey);
}
