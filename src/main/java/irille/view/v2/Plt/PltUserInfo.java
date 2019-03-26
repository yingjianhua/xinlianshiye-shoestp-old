package irille.view.v2.Plt;

import lombok.Data;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/12/19 Time: 14:16 */
@Data
public class PltUserInfo {
  private int id;
  private String name;
  // 收藏夹总数
  private int favorite_count;
  // 询盘总数
  private int inquiry_count;
  // 购物车总数
  private int shopping_cart_count;
  // 用户类型 0:普通用户 1:商家
  private int user_type;
}
