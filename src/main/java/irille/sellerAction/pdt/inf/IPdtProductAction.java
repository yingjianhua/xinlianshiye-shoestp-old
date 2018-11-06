package irille.sellerAction.pdt.inf;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

@Controller(module = "产品", name = "产品")
public interface IPdtProductAction extends ISellerAction {

    /**
     * 自动生成编号
     */
    @RequestMapping(alias = "自动生成编号")
    public void getSeqnumInt() throws Exception;

    /**
     * layui查询
     *
     * @throws Exception
     */
    @RequestMapping(alias = "layui查询")
    public void layuiList() throws Exception;

    /**
     * 商家修改
     */
    @RequestMapping(alias = "商家修改")
    public void usrUpdRun() throws Exception;

    /**
     * 商家删除
     */
    @RequestMapping(alias = "商家删除")
    public void delDetails() throws Exception;

    /**
     * 根据pkey查询产品
     *
     * @return
     * @throws Exception
     */
    public String detail() throws Exception;

    /**
     * 上传图片
     *
     * @throws IOException
     * @author ***
     */
    @RequestMapping(alias = "上传图片", sort = 7)
    public void upload() throws IOException;


    /**
     * 获取所有商品
     */
    @RequestMapping(alias = "获取所有商品")
    public void getAllPdt() throws Exception;

    @RequestMapping(alias = "Vue发布商品")
    void saveProduct() throws Exception;

    @RequestMapping(alias = "Vue修改商品")
    void viewProduct() throws IOException;

    @RequestMapping(alias = "获取复制商品信息")
    void copyProduct();

}