package irille.view.Manage.OdrMeeting.Sale;

import java.util.List;
import java.util.Map;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.homeAction.usr.dto.PdtView;
import irille.homeAction.usr.dto.SpecView;
import irille.shop.pdt.*;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrSupplier;
import irille.view.BaseView;
import lombok.Data;

/**
 * @Description: 订购会商品详情页
 *
 * @date 2018/11/23 9:53
 * @anthor wilson zhang
 */
@Data
public class OdrMeetingdetailView implements BaseView {
  private Integer id; // 联合采购明细的pkey
  private UsrFavorites favorite; // 收藏该商品
  private OrderMeeting groupPurchaseLine; // 用户点击的订购会明细对象
  private PdtProduct product; // 此联合采购明细对象所对应的产品
  private UsrSupplier supplier; // 此产品的供应商
  private List<PdtCat> pdtCatList; // 此产品的分类
  private Map<PdtColor, List<SpecView>> colorToSpec; // 此产品包含的颜色及该颜色对应的规格集合
  private Map<PdtColor, List<String>> colorToImg; // 此产品包含的颜色及该颜色对应的产品图片
  private Map<PdtColor, String> colorShow; // 该颜色及其首张对应产品图
  private Map<PdtAttr, PdtAttrLine> proAttribute; // 此产品的属性
  private List<PdtView> recommendationPdt; // 供应商的推荐商品
}
