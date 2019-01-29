package irille.sellerAction.o2o.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import org.json.JSONException;

import java.io.IOException;

@Controller(module = "用户管理", name = "O2O相关")
public interface IO2oActivityAction extends ISellerAction {
    @RequestMapping(alias = "获取活动列表", sort = 10)
    void getActivityList() throws Exception;

    @RequestMapping(alias = "获取活动详细信息", sort = 10)
    void getActivityInfo() throws IOException;

    @RequestMapping(alias = "参与活动", sort = 20)
    void enroll() throws IOException, JSONException;

    @RequestMapping(alias = "获取商家所有通过审核、上架的普通商品")
    void listAllGeneral() throws IOException;

    @RequestMapping(alias = "申请下架", sort = 30)
    void lower() throws IOException, JSONException;
}
