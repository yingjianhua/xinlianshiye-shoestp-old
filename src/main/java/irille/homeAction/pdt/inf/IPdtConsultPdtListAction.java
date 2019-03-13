package irille.homeAction.pdt.inf;

public interface IPdtConsultPdtListAction {

  /**
   * 将产品添加到询盘产品列表中
   *
   * @throws Exception
   * @author yingjianhua
   */
  public void add() throws Exception;

  /**
   * 获取当前登录采购商的询盘产品列表的数据
   *
   * @throws Exception
   * @author yingjianhua
   */
  public void list() throws Exception;

  /**
   * 删除询盘产品列表中的产品
   *
   * @throws Exception
   * @author yingjianhua
   */
  public void delete() throws Exception;

  /**
   * 批量删除询盘产品列表中的产品
   *
   * @throws Exception
   * @author yingjianhua
   */
  public void deletes() throws Exception;
}
