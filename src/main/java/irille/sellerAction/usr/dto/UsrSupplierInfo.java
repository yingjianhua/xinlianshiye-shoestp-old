package irille.sellerAction.usr.dto;

import java.util.List;

import irille.Dao.RFQ.view.SellerIndexConsultView;
import irille.view.BaseView;
import irille.view.usr.SupplierDetailsDTO;
import lombok.Data;

/** @Author wilson Zhang @Description 商家端首页总DTO @Date 21:30 2019/3/28 */
@Data
public class UsrSupplierInfo implements BaseView {
  private SupplierDetailsDTO supplierDetailsDTO; // 商家首页第一块信息
  private Integer inquiriesCount; //  商家首页第2块询盘总数;
  private Integer contactsCount; // 商家首页第2块联系人信息
  private Integer productCount; // 商家首页获取当前供应商的产品总数
  private Integer privateproductCount; // 商家首页获取当前供应商的私人展厅产品总数
  private Integer wareHouseProductCount; // 商家首页获取当前供应商的仓库产品总数
  private Integer verifyProductCount; // 商家首页获取当前供应商的产品待审核总数
  private Integer failedProductCount; // 商家首页获取当前供应商的审核失败产品总数
  private Integer warehouseProductCounts; // 商家首页获取当前供应商的产品回收站总数
  private Integer messageCount; // 商家首页商品消息总数
  private List<SellerIndexConsultView> sellerIndexConsultViewList; // 商家首页第3块商品询盘查询普通询盘
  private String svsLevel; // SVS等级
}
