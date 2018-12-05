package irille.sellerAction.activitys.Inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/11/20
 * Time: 14:28
 */
@Controller(module = "其他", name = "活动报名")
public interface IActivityAction extends ISellerAction {
    @RequestMapping(alias = "获取Pk大赛数据", sort = 1)
    void getPkData() throws IOException;
}
