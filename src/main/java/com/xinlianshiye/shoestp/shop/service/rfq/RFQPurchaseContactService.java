package com.xinlianshiye.shoestp.shop.service.rfq;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQPurchaseContactServiceImpl;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQPurchaseContactGroupView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQPurchaseContactView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

@ImplementedBy(RFQPurchaseContactServiceImpl.class)
public interface RFQPurchaseContactService {

  /**
   * 添加联系人
   *
   * @param purchase 采购商
   * @param supplierPkey 供应商主键
   * @author Jianhua Ying
   */
  void add(UsrPurchase purchase, Integer supplierPkey);

  /**
   * 分页查询联系人列表 包含联系人的询盘报价信息
   *
   * @param purchase 采购商
   * @param keyword 关键字 关联供应商的名字和询盘的标题
   * @param groupPkey 分组主键
   * @param start 开始记录数
   * @param limit 每页记录数
   * @return
   * @author Jianhua Ying
   */
  Page<RFQPurchaseContactView> page(
      UsrPurchase purchase, String keyword, Integer groupPkey, Integer start, Integer limit);

  /**
   * 删除联系人
   *
   * @param purchase 采购商
   * @param supplierPkey 供应商主键
   * @author Jianhua Ying
   */
  void delete(UsrPurchase purchase, Integer supplierPkey);

  /**
   * 添加分组
   *
   * @param purchase 采购商
   * @param groupName 分组名字
   * @author Jianhua Ying
   */
  void addGroup(UsrPurchase purchase, String groupName, Language language);

  /**
   * 删除分组
   *
   * @param purchase 采购商
   * @param groupPkey 分组主键
   * @author Jianhua Ying
   */
  void deleteGroup(UsrPurchase purchase, Integer groupPkey);

  /**
   * 编辑分组
   *
   * @param purchase 采购商
   * @param groupPkey 分组主键
   * @param groupName 分组名字
   * @author Jianhua Ying
   */
  void editGroup(UsrPurchase purchase, Integer groupPkey, String groupName, Language language);

  /**
   * 移动分组
   *
   * @param purchase 采购商
   * @param contactPkey 联系人主键
   * @param groupPkey 分组主键
   * @author Jianhua Ying
   */
  void moveToGroup(UsrPurchase purchase, Integer contactPkey, Integer groupPkey, Language language);

  /**
   * 列表分组
   *
   * @param purchase 采购商
   * @author Jianhua Ying
   */
  List<RFQPurchaseContactGroupView> listGroup(UsrPurchase purchase);
}
