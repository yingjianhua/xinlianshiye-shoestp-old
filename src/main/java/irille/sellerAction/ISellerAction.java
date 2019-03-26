package irille.sellerAction;

import irille.Filter.svr.RequestMapping;

public interface ISellerAction {

  @RequestMapping(alias = "列表", sort = -10)
  public void list() throws Exception;

  @RequestMapping(alias = "新增", sort = -9)
  public void ins() throws Exception;

  @RequestMapping(alias = "修改", sort = -8)
  public void upd() throws Exception;

  @RequestMapping(alias = "查询", sort = -7)
  public void load() throws Exception;

  @RequestMapping(alias = "删除", sort = -6)
  public void del() throws Exception;

  @RequestMapping(alias = "批量删除", sort = -5)
  public void delMulti() throws Exception;
}
