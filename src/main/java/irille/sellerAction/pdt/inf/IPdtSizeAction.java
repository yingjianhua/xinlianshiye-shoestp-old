package irille.sellerAction.pdt.inf;

import java.io.IOException;

import irille.pub.svr.Controller;
import irille.pub.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module="产品",name="产品尺寸")
public interface IPdtSizeAction extends ISellerAction{
	
	/**
	 * 根据条件查询
	 * @throws Exception
	 */
	@RequestMapping(alias="条件查询")
	public void listAll() throws Exception;
	
	@RequestMapping(alias = "添加尺寸", sort = 2)
    void insSizeBySup() throws Exception;
    @RequestMapping(alias = "删除尺寸", sort = 3)
    void delSizeBySup() throws Exception;
    @RequestMapping(alias = "修改尺寸", sort = 4)
    void updSizeBySup() throws Exception;
	    
}
