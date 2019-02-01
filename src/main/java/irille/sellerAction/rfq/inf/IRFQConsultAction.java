package irille.sellerAction.rfq.inf;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.IEnableable;
import irille.sellerAction.ISellerAction;

import java.io.IOException;

@Controller(module = "询盘", name = "RFQ询盘")
public interface IRFQConsultAction extends ISellerAction, IEnableable {
    @RequestMapping(alias = "获取RFQ询盘列表", sort = 1)
    void getRFQList() throws Exception;

    @RequestMapping(alias = "获取RFQ询盘详情", sort = 2)
    void getRFQInfo() throws Exception;

    @RequestMapping(alias = "RFQ询盘报价信息", sort = 3)
    void putRFQQuoteInfo() throws IOException;


    @RequestMapping(alias = "RFQ报价,载入商品信息", sort = 4)
    void getPdtInfo() throws IOException;

    @RequestMapping(alias = "RFQ报价,载入商品列表", sort = 5)
    void getPdtList() throws IOException;


    @RequestMapping(alias = "RFQ询盘报价列表", sort = 6)
    void getMyRFQQuoteList() throws IOException;

    @RequestMapping(alias = "RFQ询盘报价信息", sort = 7)
    void getMyRFQQuoteInfo() throws IOException;

    /**
     * 分页查询询盘信息
     * 查询条件
     * 询盘类型
     * 询盘标题
     * 询盘采购商名称
     *
     * @author Jianhua Ying
     */
    void list() throws IOException;

    /**
     * 移动到回收站
     *
     * @throws IOException
     * @author Jianhua Ying
     */
    @RequestMapping(alias = "RFQ移动到回收站", sort = 8)
    void moveToRecycled() throws IOException;

    /**
     * 从回收站恢复
     *
     * @throws IOException
     */
    @RequestMapping(alias = "RFQ从回收站恢复", sort = 9)
    void recovery() throws IOException;

    /**
     * 移动询盘到指定分组
     *
     * @throws IOException
     */
    @RequestMapping(alias = "移动询盘到指定分组", sort = 10)
    void moveToGroup() throws IOException;

}
