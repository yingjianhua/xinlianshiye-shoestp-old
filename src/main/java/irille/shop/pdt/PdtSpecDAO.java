package irille.shop.pdt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import irille.core.sys.Sys.OYn;
import irille.pub.LogMessage;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.bean.sql.SQL;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.shop.pdt.PdtSpec.T;
import irille.shop.usr.UsrCart;
import irille.view.pdt.NewSpceView;

public class PdtSpecDAO {
  public static final LogMessage LOG = new LogMessage(PdtSpecDAO.class);

  /** @author yingjianhua */
  public static class Query extends IduOther<Query, PdtSpec> {
    // 根据产品主键查询所有规格
    public List<PdtSpec> listByProduct(Integer pkey) {
      String where =
          PdtSpec.T.PRODUCT.getFld().getCodeSqlField()
              + "="
              + pkey
              + " AND "
              + PdtSpec.T.DELETED.getFld().getCodeSqlField()
              + " = "
              + OYn.NO.getLine().getKey();
      return BeanBase.list(PdtSpec.class, where, false);
    }

    // 查询购物车里所拥有的规格的属性
    public List<PdtSpec> listByCart(List<UsrCart> carts) {
      if (carts.size() == 0) return new ArrayList<>();
      StringBuilder b = new StringBuilder();
      for (int i = 0; i < carts.size(); i++) {
        if (i != 0) b.append(",");
        b.append(carts.get(i).getSpec());
      }
      String where = PdtSpec.T.PKEY.getFld().getCodeSqlField() + " in (" + b.toString() + ")";
      return BeanBase.list(PdtSpec.class, where, false);
    }

    // 查询产品下所有的规格
    public List<PdtSpec> listByProduct(List<PdtProduct> products) {
      List<PdtSpec> l = new ArrayList<>();
      for (PdtProduct p : products) {
        l.addAll(listByProduct(p.getPkey()));
      }
      return l;
    }
  }

  public static class Ins extends IduIns<Ins, PdtSpec> {
    @Override
    public void before() {
      super.before();
      getB().setDeleted(OYn.NO.getLine().getKey());
    }
  }

  public static class upd extends IduUpd<upd, PdtSpec> {
    @Override
    public void before() {
      PdtSpec spec = loadThisBeanAndLock();
      System.out.println(getB());
      PropertyUtils.copyProperties(
          spec,
          getB(),
          T.PRODUCT,
          T.COLOR,
          T.SIZE,
          T.PRICE,
          T.MARKUP,
          T.STORE_COUNT,
          T.WEIGHT,
          T.PICS);
      setB(spec);
    }
  }

  /** 比较库存 */
  public static PdtSpec judgeCount(PdtSpec spec, PdtProduct product, Integer qty) {
    if (spec.getStoreCount() < 0) {
      // 和产品中的库存比较
      if (product.getStock().equals(0)) {
        throw LOG.errTran("goods_info%product_out", "该商品已售罄");
      }
      if (product.getStock() < qty) {
        throw LOG.errTran("cart%prod_stock_error", "商品库存不足", product.getName());
      }
    } else {
      // 和规格中的库存比较
      if (spec.getStoreCount().equals(0)) {
        throw LOG.errTran("goods_info%product_out", "该商品已售罄");
      }
      if (spec.getStoreCount() < qty) {
        throw LOG.errTran("cart%prod_stock_error", "商品库存不足", product.getName());
      }
    }

    return spec;
  }

  /** 减少对应库存 */
  public static void reduceStock(PdtSpec spec, Integer qty) {
    if (spec.getStoreCount() < 0) {
      // 减少产品中的库存
      PdtProduct product = spec.gtProduct();
      product.setStock(product.getStock() - qty);
      product.upd();
    } else {
      // 减少规格中的库存
      spec.setStoreCount(spec.getStoreCount() - qty);
      spec.upd();
    }
  }

  public static List<NewSpceView> getList(Integer pdtPkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtSpec.class);
    sql.FROM(PdtSpec.class);
    sql.WHERE(PdtSpec.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    sql.WHERE(PdtSpec.T.PRODUCT, " =? ", pdtPkey);
    List<PdtSpec> list = irille.pub.bean.Query.sql(sql).queryList(PdtSpec.class);
    return list.stream()
        .filter(
            new Predicate<PdtSpec>() {

              @Override
              public boolean test(PdtSpec t) {
                if (t.gtColor().getType() == Pdt.OVer.ELSE.getLine().getKey()
                    || t.gtSize().getTypever() == Pdt.OVer.ELSE.getLine().getKey()) {
                  return false;
                }
                return true;
              }
            })
        .map(
            bean ->
                new NewSpceView() {
                  {
                    setSku(bean.getSku());
                    setCount(bean.getStoreCount());
                    setId(bean.getPkey());
                    setSize(bean.getSize());
                    setSizeName(bean.gtSize().getName());
                    setSizeType(
                        bean.gtSize().getType() == null ? null : (int) bean.gtSize().getType());
                    setColor(bean.getColor());
                    setColorImg(bean.getPics());
                    setColorName(bean.gtColor().getName());
                    setColorType((int) bean.gtColor().getDefaultColor());
                  }
                })
        .collect(Collectors.toList());
  }
}
