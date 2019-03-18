package irille.action.seller.usr;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;

@Controller(module = "用户管理", name = "供应商")
public interface IUsrSupplierAction {

  /**
   * 供应商登录
   *
   * @throws IOException
   */
  @RequestMapping(alias = "登录", sort = 1)
  public void login() throws IOException;

  /**
   * 注销供应商登录信息
   *
   * @return
   */
  @RequestMapping(alias = "注销", sort = 3)
  public void signOut() throws IOException;

  /**
   * 查询店铺设置信息
   *
   * @throws Exception
   */
  @RequestMapping(alias = "查询供应商对象", sort = 5)
  public void selSupplier() throws Exception;

  /**
   * 更新LOGO
   *
   * @throws Exception
   */
  @RequestMapping(alias = "更新LOGO", sort = 6)
  public void updLogo() throws Exception;

  /**
   * 更新密码
   *
   * @throws Exception
   */
  @RequestMapping(alias = "更新密码", sort = 7)
  public void updPassword() throws Exception;

  /**
   * 更新密码
   *
   * @throws Exception
   */
  @RequestMapping(alias = "更新店铺设置", sort = 8)
  public void UpdBizDiy() throws Exception;

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
}
