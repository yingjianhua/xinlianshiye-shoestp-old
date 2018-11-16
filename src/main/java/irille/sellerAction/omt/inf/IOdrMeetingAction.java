package irille.sellerAction.omt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import irille.view.Page;

@Controller(module="用户管理", name="联合订单")
public interface IOdrMeetingAction extends ISellerAction {
    @RequestMapping(alias="状态列表", sort=1)
    public void loadstate() throws Exception;
    @RequestMapping(alias="发起订购会列表", sort=2)
    public void getMyOdrMeetingList() throws Exception;
    @RequestMapping(alias="加入订购会列表", sort=3)
    public void getMyJoinOdrMeetingList() throws Exception;
    @RequestMapping(alias="其他订购会列表", sort=4)
    public void getMyOtherOdrMeetingList() throws Exception;
    @RequestMapping(alias="批量删除", sort=5)
    public void batchdelete() throws Exception;
    @RequestMapping(alias="订购会列表  修改状态", sort=6)
    public void Meettingupdstate() throws Exception;

}
