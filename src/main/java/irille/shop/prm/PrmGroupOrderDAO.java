package irille.shop.prm;

import java.math.BigDecimal;

import irille.pub.idu.IduInsLines;
import irille.pub.svr.Env;

public class PrmGroupOrderDAO {

  /**
   * 采购商订购商品后生成总订单
   *
   * @author liyichao
   */
  public static class Ins extends IduInsLines<Ins, PrmGroupOrder, PrmGroupOrderLine> {
    @Override
    public void before() {
      Integer activityPkey = getB().getActivity();
      // 设置订单创建时间
      getB().setCreatedTime(Env.getSystemTime());
      // 设置订购总价及订购总数量
      Integer allNum = 0;
      BigDecimal allPrice = BigDecimal.ZERO;
      for (int i = 0; i < getLines().size(); i++) {
        // 获取各个明细的数量 进行累加
        Integer num = getLines().get(i).getCount();
        allNum += num;
        // 获取此产品的批发价  -> 此处批发价指中间商给采购商的价格 -> 即在UnionLine中设置的价格
        // 如何获得UnionLine中的价格?先传入Union的pkey和产品规格pkey在UnionLine对象中查找对应UnionLine对象,再获得价格
        // PrmGroupPurchaseLine unionLine =
        // PrmGroupPurchaseLine.loadUniqueMain_product(false,activityPkey,
        // getLines().get(i).getSpec());
        // 和数量相乘得到总价
        // 将对应规格的产品总价设置到对应规格产品中
        //				allPrice = allPrice.add(getLines().get(i).getAmt());
        //				unionLine.setBoughtCount(unionLine.getBoughtCount() + getLines().get(i).getCount());
      }

      getB().setCount(allNum);
      getB().setAmtTotal(allPrice);
    }

    @Override
    public void after() {
      insLine(getB(), getLines(), PrmGroupOrderLine.T.GROUP_ORDER.getFld());
      // super.after();
      // 设置订单号
      String timeStamp = Env.getSystemTime().getTime() + "";
      String pkey = getB().getPkey() + "";
      String orderid = timeStamp.substring(0, timeStamp.length() - pkey.length()) + pkey;
      getB().setOrderNum(orderid);
      getB().upd();
    }
  }
}
