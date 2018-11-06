package irille.sellerAction.plt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/17
 * Time: 18:36
 */
@Controller(module = "用户管理", name = "支付")
public interface IPltPayAction extends ISellerAction {
    @RequestMapping(alias = "保存支付设置", sort = 1)
    void savePaySetting() throws IOException, JSONException;

    @RequestMapping(alias = "获取支付设置", sort = 2)
    void getPaySettings() throws Exception;
}