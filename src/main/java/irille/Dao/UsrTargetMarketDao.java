package irille.Dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.shop.usr.UsrTargetMarker;

public class UsrTargetMarketDao {

  /**
   * 插入目标市场
   *
   * @author GS
   * @param countrty
   * @param supplier
   */
  public void save(List<Integer> countrties, Integer supplier) {
    SQL sql = new SQL();
    sql.DELETE_FROM(UsrTargetMarker.class).WHERE(UsrTargetMarker.T.SUPPLIER, "=?", supplier);
    for (Integer integer : countrties) {
      UsrTargetMarker market = new UsrTargetMarker();
      market.setCountry(integer);
      market.setSupplier(supplier);
      market.ins();
    }
  }
}
