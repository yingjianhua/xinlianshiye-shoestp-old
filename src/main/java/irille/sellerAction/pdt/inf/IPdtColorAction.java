package irille.sellerAction.pdt.inf;

import java.io.IOException;

import irille.Filter.svr.Controller;
import irille.Filter.svr.RequestMapping;
import irille.sellerAction.ISellerAction;

@Controller(module = "产品", name = "颜色")
public interface IPdtColorAction extends ISellerAction {
  @RequestMapping(alias = "获取颜色列表", sort = 1)
  void getColorList() throws IOException;

  @RequestMapping(alias = "添加颜色", sort = 2)
  void insColorBySup() throws Exception;

  @RequestMapping(alias = "删除颜色", sort = 3)
  void delColorBySup() throws Exception;

  @RequestMapping(alias = "修改颜色", sort = 4)
  void updColorBySup() throws Exception;

  @RequestMapping(alias = "修改颜色")
  void updColor() throws Exception;

  @RequestMapping(alias = "新增颜色")
  void insColor() throws Exception;

  @RequestMapping(alias = "获取默认颜色")
  void getList() throws IOException;
}
