package irille.sellerAction.omt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import irille.view.Page;

@Controller(module="用户管理", name="联合订单")
public interface IOdrMeetingAction extends ISellerAction {
    @RequestMapping(alias="审核状态列表", sort=1)
    public void loadsupstate() throws Exception;
    @RequestMapping(alias="发起订购会列表", sort=2)
    public void getMyOdrMeetingList() throws Exception;
    @RequestMapping(alias="加入订购会列表", sort=3)
    public void getMyJoinOdrMeetingList() throws Exception;
    @RequestMapping(alias="其他订购会列表", sort=4)
    public void getMyOtherOdrMeetingList() throws Exception;
    @RequestMapping(alias="发布 批量删除", sort=5)
    public void batchdelete() throws Exception;
    @RequestMapping(alias="参加 批量删除", sort=8)
    public void joindelete() throws Exception;
    @RequestMapping(alias="参加订购会插入", sort=9)
    public void joininsert() throws Exception;
    @RequestMapping(alias="订购会列表  修改状态", sort=6)
    public void Meettingupdstate() throws Exception;
    @RequestMapping(alias="状态列表", sort=7)
    public void loadstate() throws Exception;
    @RequestMapping(alias="發佈會 展會列表", sort=7)
    public void exhibitionlist() throws Exception;
    @RequestMapping(alias="上传图片",sort=14)
    public void upload() throws Exception;
    @RequestMapping(alias = "获取订购会信息", sort = 9)
    public void getorderInformation() throws Exception;

    @RequestMapping(alias = "获取订购会商品列表", sort = 10)
    public void getOrderGoodsList() throws Exception;
    @RequestMapping(alias = "插入订购会", sort = 13)
    public void insOdrmeetting() throws Exception;

    @RequestMapping(alias = "订购会商品上下架状态更新", sort = 11)
    public void updateStatus() throws Exception;

    @RequestMapping(alias = "订购会商品删除状态改变", sort = 12)
    public void removeProduct() throws Exception;
}
