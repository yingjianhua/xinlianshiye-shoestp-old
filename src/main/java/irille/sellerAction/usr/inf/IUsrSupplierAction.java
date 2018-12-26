package irille.sellerAction.usr.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

@Controller(module = "用户管理", name = "供应商")
public interface IUsrSupplierAction extends ISellerAction {

    /**
     * 供应商登录
     *
     * @throws IOException
     */
    @RequestMapping(alias = "登录")
    public void login() throws IOException;

    /**
     * 获取登录信息
     */
    @RequestMapping(alias = "获取登录信息")
    public void getLoginMsg() throws Exception;


    /**
     * 查询店铺设置信息
     *
     * @throws Exception
     */
    @RequestMapping(alias = "查询供应商对象", sort = 3)
    public void selSupplier() throws Exception;

    /**
     * 更新LOGO
     *
     * @throws Exception
     */
    @RequestMapping(alias = "更新LOGO", sort = 4)
    public void updLogo() throws Exception;

    /**
     * 更新密码
     *
     * @throws Exception
     */
    @RequestMapping(alias = "更新密码", sort = 5)
    public void UpdPwd() throws Exception;

    /**
     * 更新密码
     *
     * @throws Exception
     */
    @RequestMapping(alias = "更新店铺设置", sort = 7)
    public void UpdBizDiy() throws Exception;

    /**
     * 注销供应商登录信息
     *
     * @return
     */
    @RequestMapping(alias = "注销", sort = 8)
    public String logon();

    /**
     * 上传图片
     *
     * @throws Exception
     */
    @RequestMapping(alias = "上传图片", sort = 9)
    public void upload() throws Exception;

    /**
     * 返回省份,国家,采购商信息
     *
     * @throws Exception
     */
    @RequestMapping(alias = "获取商家详细信息", sort = 20)
    public void getCountryAndSupType() throws Exception;

    /**
     * 加载在线商家信息
     *
     * @throws Exception
     */
    @RequestMapping(alias = "获取商家所有信息", sort = 30)
    public void loadOnlineSup() throws Exception;

    /**
     * 更新供应商信息
     *
     * @throws IOException
     */
    @RequestMapping(alias = "更新供应商信息", sort = 40)
    public void updInfo() throws Exception;

    /**
     * 获取商家邮箱
     *
     * @author zjl
     */
    @RequestMapping(alias = "获取商家邮箱", sort = 41)
    public void returnEmail() throws Exception;

    /**
     * 更新账户设置
     *
     * @throws Exception
     * @author zjl
     */
    @RequestMapping(alias = "更新账户设置", sort = 42)
    public void upAccount() throws Exception;

    /**
     * 加载店铺装修
     *
     * @throws Exception
     * @author zw
     */
    @RequestMapping(alias = "加载店铺装修", sort = 43)
    public void loadshopsetting() throws Exception;


    @RequestMapping(alias = "获取商家账户信息(NEW)", sort = 44)
    void Info() throws Exception;

    @RequestMapping(alias = "商家退出(NEW)", sort = 45)
    void logout() throws Exception;

    @RequestMapping(alias = "获取店铺装修信息(NEW)", sort = 46)
    void getShopSetting() throws Exception;
    @RequestMapping(alias = "更新店铺装修信息(NEW)", sort = 47)
    void updShopSetting() throws Exception;
    @RequestMapping(alias = "修改店铺装修", sort = 48)
    void Updsuppliersetting() throws Exception;
    @RequestMapping(alias = "2.1商家账户信息(公司基本信息)", sort = 49)
    void  getsupinfo() throws Exception;
    @RequestMapping(alias = "2.1商家修改账户信息(公司基本信息)", sort = 50)
    void  updShopbase() throws Exception;
    @RequestMapping(alias = "2.1商家运营信息", sort = 51)
    void  getoperateinfo() throws Exception;
    @RequestMapping(alias = "2.1商家修改运营信息", sort = 52)
    void  updoperateinfo() throws Exception;
    @RequestMapping(alias = "2.1认证信息", sort = 53)
    void  authInfo() throws Exception;

}
