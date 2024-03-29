package irille.sellerAction.omt.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "用户管理", name = "联合订单")
public interface IOdrMeetingAction extends ISellerAction {
  @RequestMapping(alias = "审核状态列表", sort = 1)
  public void loadsupstate() throws Exception;

  @RequestMapping(alias = "发起订购会列表", sort = 2)
  public void getMyOdrMeetingList() throws Exception;

  @RequestMapping(alias = "加入订购会列表", sort = 3)
  public void getMyJoinOdrMeetingList() throws Exception;

  @RequestMapping(alias = "其他订购会列表", sort = 4)
  public void getMyOtherOdrMeetingList() throws Exception;

  @RequestMapping(alias = "发布 批量删除", sort = 5)
  public void batchdelete() throws Exception;

  @RequestMapping(alias = "参加 批量删除", sort = 6)
  public void joindelete() throws Exception;

  @RequestMapping(alias = "参加订购会插入", sort = 7)
  public void joininsert() throws Exception;

  @RequestMapping(alias = "订购会列表  修改状态", sort = 8)
  public void Meettingupdstate() throws Exception;

  @RequestMapping(alias = "状态列表", sort = 9)
  public void loadstate() throws Exception;

  @RequestMapping(alias = "發佈會 展會列表", sort = 10)
  public void exhibitionlist() throws Exception;

  @RequestMapping(alias = "上传图片", sort = 11)
  public void upload() throws Exception;

  @RequestMapping(alias = "获取订购会信息", sort = 12)
  public void getorderInformation() throws Exception;

  @RequestMapping(alias = "获取订购会商品列表", sort = 13)
  public void getOrderGoodsList() throws Exception;

  @RequestMapping(alias = "插入订购会", sort = 14)
  public void insOdrmeetting() throws Exception;

  @RequestMapping(alias = "订购会商品上下架状态更新", sort = 15)
  public void updateStatus() throws Exception;

  @RequestMapping(alias = "订购会商品删除状态改变", sort = 16)
  public void removeProduct() throws Exception;

  @RequestMapping(alias = "订购会修改地址", sort = 17)
  public void updaddress() throws Exception;

  @RequestMapping(alias = "获取订购会商品", sort = 18)
  public void getProducts() throws Exception;

  @RequestMapping(alias = "获取已添加订购会商品", sort = 19)
  public void getAddedProducts() throws Exception;

  @RequestMapping(alias = "添加订购会商品", sort = 20)
  public void addProducts() throws Exception;

  @RequestMapping(alias = "逻辑删除 参加订购会合作商", sort = 21)
  public void deletejoinOdr() throws Exception;

  @RequestMapping(alias = "获取订购会合作商列表", sort = 22)
  public void getPartnerList() throws Exception;

  @RequestMapping(alias = "获取订购会订单列表", sort = 23)
  public void getOmtOrderList() throws Exception;

  @RequestMapping(alias = "获取订单状态列表", sort = 24)
  public void getOrderStatus() throws Exception;

  @RequestMapping(alias = "获取销售明细", sort = 25)
  public void getSalesDetails() throws Exception;

  @RequestMapping(alias = "修改订单是否发送给合作商状态", sort = 26)
  public void updSendStatus() throws Exception;

  @RequestMapping(alias = "审核合作商(修改状态)", sort = 27)
  public void updLoadSupStatus() throws Exception;

  @RequestMapping(alias = "获取合作商是否认证状态", sort = 28)
  public void isAuthStatus() throws Exception;

  @RequestMapping(alias = "合作商发送样品(更新信息)", sort = 29)
  public void updAudit() throws Exception;

  @RequestMapping(alias = "获取合作商样品发送物流信息", sort = 30)
  public void getLogistics() throws Exception;
}
