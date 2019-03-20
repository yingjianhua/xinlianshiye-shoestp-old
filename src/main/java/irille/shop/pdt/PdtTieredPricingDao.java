package irille.shop.pdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import irille.core.sys.Sys.OYn;
import irille.pub.LogMessage;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.tb.IEnumFld;
import irille.pub.util.BatchUtils;
import irille.view.pdt.PdtTieredPricingView;

public class PdtTieredPricingDao {

  public static final LogMessage LOG = new LogMessage(PdtTieredPricingDao.class);

  public static void ins(List<PdtTieredPricing> objs) {
    List<IEnumFld> flds =
        Arrays.asList(
            PdtTieredPricing.T.PRODUCT,
            PdtTieredPricing.T.MIN_OQ,
            PdtTieredPricing.T.CUR_PRICE,
            PdtTieredPricing.T.DELETED,
            PdtTieredPricing.T.ROW_VERSION);
    BatchUtils.batchIns(PdtTieredPricing.class, objs);
  }

  public static List<PdtTieredPricingView> getList(Integer pdtPkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtTieredPricing.class);
    sql.FROM(PdtTieredPricing.class);
    sql.WHERE(PdtTieredPricing.T.PRODUCT, " =? ", pdtPkey);
    sql.WHERE(PdtTieredPricing.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    List<PdtTieredPricing> list = Query.sql(sql).queryList(PdtTieredPricing.class);
    return list.stream()
        .map(
            bean ->
                new PdtTieredPricingView() {
                  {
                    setId(bean.getPkey());
                    setCount(bean.getMinOq());
                    setPrice(bean.getCurPrice());
                    setType((int) bean.getMain());
                  }
                })
        .collect(Collectors.toList());
  }

  public static void updByPdt(List<PdtTieredPricingView> tpView, Integer pdtPkey) {
    SQL sql = new SQL();
    sql.SELECT(PdtTieredPricing.class);
    sql.FROM(PdtTieredPricing.class);
    sql.WHERE(PdtTieredPricing.T.PRODUCT, " =? ", pdtPkey);
    sql.WHERE(PdtTieredPricing.T.DELETED, " =? ", OYn.NO.getLine().getKey());
    List<PdtTieredPricing> list = Query.sql(sql).queryList(PdtTieredPricing.class);
    if (list == null) {
      return;
    }
    Set<Integer> tpSet = new HashSet<>();
    List<PdtTieredPricing> tpList = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      tpSet.add(list.get(i).getPkey());
      boolean b = false;
      for (int j = 0; j < tpView.size(); j++) {
        if (list.get(i).getPkey().equals(tpView.get(j).getId())) {
          b = true;
          list.get(i).setCurPrice(tpView.get(j).getPrice());
          list.get(i).setMinOq(tpView.get(j).getCount());
          list.get(i).upd();
        }
        if (tpView.get(j).getId() < 0) {
          tpSet.add(tpView.get(j).getId());
        }
      }
      if (!b) {
        list.get(i).setDeleted(OYn.YES.getLine().getKey());
        list.get(i).upd();
      }
    }
    if (tpSet != null && !tpSet.isEmpty()) {
      for (PdtTieredPricingView itemView : tpView) {
        if (tpSet.contains(itemView.getId()) && itemView.getId() <= 0) {
          PdtTieredPricing pdtTP = new PdtTieredPricing();
          pdtTP.setMinOq(itemView.getCount());
          pdtTP.setCurPrice(itemView.getPrice());
          pdtTP.setMain(
              itemView.getType() == 1 ? OYn.YES.getLine().getKey() : OYn.NO.getLine().getKey());
          pdtTP.setProduct(pdtPkey);
          pdtTP.setDeleted(OYn.NO.getLine().getKey());
          pdtTP.setRowVersion((short) 0);
          tpList.add(pdtTP);
        }
      }
    }
    if (tpList != null && !tpList.isEmpty()) {
      ins(tpList);
    }
  }
}
