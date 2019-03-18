package irille.platform.o2o;

import java.io.IOException;

/**
 * O2O活动商品控制层
 *
 * @author Jianhua Ying
 */
public interface IO2OProductAction {

  /**
   * 活动商品列表
   *
   * <ul>
   *   搜索条件
   *   <li>活动名称
   *   <li>商品类目
   *   <li>供应商名称
   *   <li>供应商等级
   *   <li>活动地区
   *   <li>活动商品状态
   */
  void list() throws IOException;

  /**
   * 审核
   *
   * <ul>
   *   <li>审核通过
   *   <li>审核失败
   */
  void approve() throws IOException;
}
