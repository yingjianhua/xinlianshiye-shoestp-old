package irille.sellerAction.usr.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;
import org.json.JSONException;

@Controller(module = "用户管理", name = "产品类目")
public interface IUsrProductCategoryAction extends ISellerAction {

    @RequestMapping(alias = "获取供应商产品分类")
    public void getCategory() throws JSONException, Exception;

    @RequestMapping(alias = "分类产品上下架")
    public void upperAndLowerFrame() throws Exception;

    @RequestMapping(alias = "修改分类")
    public void update() throws Exception;

    @RequestMapping(alias = "获取店铺分类(Tree)")
    public void getCategoryTree() throws Exception;


    @RequestMapping(alias = "获取商品分类(NEW)")
    void getProductCats() throws Exception;

//	@RequestMapping(alias="批量删除")
//	public void batchDelete() throws Exception;
}
