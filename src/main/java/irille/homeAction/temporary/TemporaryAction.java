//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package irille.homeAction.temporary;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.json.JSONException;

import irille.Dao.PdtProductDao;
import irille.Entity.O2O.O2O_Activity;
import irille.Entity.O2O.O2O_Product;
import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.homeAction.HomeAction;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.tb.IEnumFld;
import irille.pub.util.GetValue;
import irille.pub.util.SEOUtils;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidRegex;
import irille.shop.pdt.PdtProduct;
import irille.shop.usr.UsrFavorites;
import irille.shop.usr.UsrFavorites.T;
import irille.view.v2.Pdt.PdtNewPdtInfo;
import lombok.Getter;
import lombok.Setter;

public class TemporaryAction extends HomeAction<O2O_Product> {

  @Inject private PdtProductDao productDao;
  @Setter @Getter private Integer cat;

  @Setter @Getter private String ids;

  public void productByIds() throws IOException {
    if (null == ids || (ids != null && !ValidRegex.regMarch(Regular.REGULAR_PKEY_ARRS, ids))) {
      throw new WebMessageException(ReturnCode.failure, "非法参数");
    }
    SQL sql = new SQL();
    sql.SELECT(
        new IEnumFld[] {
          irille.shop.pdt.PdtProduct.T.PKEY,
          irille.shop.pdt.PdtProduct.T.NAME,
          irille.shop.pdt.PdtProduct.T.PICTURE,
          irille.shop.pdt.PdtProduct.T.CUR_PRICE,
          irille.shop.pdt.PdtProduct.T.MIN_OQ
        });
    sql.FROM(irille.shop.pdt.PdtProduct.class);
    sql.WHERE(irille.shop.pdt.PdtProduct.T.PKEY, " IN(" + ids + ") ");
    List<irille.shop.pdt.PdtProduct> products =
        Query.sql(sql).queryList(irille.shop.pdt.PdtProduct.class);
    List<PdtNewPdtInfo> items =
        products.stream()
            .map(
                entity -> {
                  PdtNewPdtInfo item = new PdtNewPdtInfo();
                  item.setId(Long.valueOf(entity.getPkey()));
                  try {
                    item.setTitle(entity.getName(curLanguage()));
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                  item.setImage(entity.getPicture());
                  item.setMin_order(entity.getMinOq());
                  item.setRewrite(SEOUtils.getPdtProductTitle(entity.getPkey(), entity.getName()));
                  item.setPrice(entity.getCurPrice());
                  return item;
                })
            .collect(Collectors.toList());
    write(items);
  }

  public void randomO2oList() throws IOException {
    SQL sql = new SQL();
    SQL childrenQuery = new SQL();
    if (getPurchase() != null) {
      childrenQuery
          .SELECT(new IEnumFld[] {T.PKEY})
          .FROM(UsrFavorites.class)
          .WHERE(T.PURCHASE, "=?", new Serializable[] {getPurchase().getPkey()})
          .WHERE(T.PRODUCT, "=", irille.shop.pdt.PdtProduct.T.PKEY);
    }

    sql.SELECT(
        new IEnumFld[] {
          irille.shop.pdt.PdtProduct.T.PKEY,
          irille.shop.pdt.PdtProduct.T.NAME,
          irille.shop.pdt.PdtProduct.T.PICTURE,
          irille.Entity.O2O.O2O_Product.T.PRICE,
          irille.Entity.O2O.O2O_Product.T.MIN_OQ
        });
    if (getPurchase() != null) {
      sql.SELECT(childrenQuery, "ismyfavorite");
    }

    sql.FROM(O2O_Product.class)
        .LEFT_JOIN(
            PdtProduct.class,
            irille.shop.pdt.PdtProduct.T.PKEY,
            irille.Entity.O2O.O2O_Product.T.PRODUCT_ID)
        .LEFT_JOIN(
            O2O_Activity.class,
            irille.Entity.O2O.O2O_Activity.T.PKEY,
            irille.Entity.O2O.O2O_Product.T.ACTIVITY_ID)
        .WHERE(irille.Entity.O2O.O2O_Activity.T.START_DATE, "<?", new Serializable[] {new Date()})
        .WHERE(irille.Entity.O2O.O2O_Activity.T.END_DATE, ">?", new Serializable[] {new Date()})
        .WHERE(
            irille.Entity.O2O.O2O_Product.T.VERIFY_STATUS,
            "=?",
            new Serializable[] {O2O_ProductStatus.PASS.getLine().getKey()})
        .WHERE(
            "("
                + O2O_Product.class.getSimpleName()
                + "."
                + irille.Entity.O2O.O2O_Product.T.STATUS.getFld().getCodeSqlField()
                + "=? OR "
                + O2O_Product.class.getSimpleName()
                + "."
                + irille.Entity.O2O.O2O_Product.T.STATUS.getFld().getCodeSqlField()
                + " =? )",
            new Serializable[] {O2O_ProductStatus.ON, O2O_ProductStatus.Failed});
    List<Map<String, Object>> o2os = Query.sql(sql).queryMaps();
    Map<Integer, PdtNewPdtInfo> map = new HashMap(this.getLimit());

    for (int i = 0; o2os.size() != 0; ++i) {
      int rand = (new Random()).nextInt(o2os.size());
      Map<String, Object> pdt = (Map) o2os.get(rand);
      Integer pdtPkey =
          (Integer) GetValue.get(pdt, irille.shop.pdt.PdtProduct.T.PKEY, Integer.class, -1);
      if (!map.containsKey(pdtPkey)) {
        PdtNewPdtInfo item = new PdtNewPdtInfo();
        item.setFavorite(GetValue.get(pdt, "ismyfavorite", Integer.class, null) != null);
        item.setImage(
            GetValue.getFirstImage(
                (String)
                    GetValue.get(pdt, irille.shop.pdt.PdtProduct.T.PICTURE, String.class, null)));
        item.setId((long) pdtPkey);
        item.setMin_order(
            (Integer) GetValue.get(pdt, irille.Entity.O2O.O2O_Product.T.MIN_OQ, Integer.class, -1));
        item.setPrice(
            (BigDecimal)
                GetValue.get(
                    pdt, irille.Entity.O2O.O2O_Product.T.PRICE, BigDecimal.class, BigDecimal.ZERO));
        String name =
            (String) GetValue.get(pdt, irille.shop.pdt.PdtProduct.T.NAME, String.class, "");
        item.setTitle(name);
        item.setRewrite(
            SEOUtils.getPdtProductTitle(Integer.valueOf(String.valueOf(pdtPkey)), name));
        map.put(pdtPkey, item);
      }

      if (map.size() == this.getLimit() || i > 100) {
        break;
      }
    }

    List<PdtNewPdtInfo> result = new ArrayList();
    Iterator var13 = map.entrySet().iterator();

    while (var13.hasNext()) {
      Entry<Integer, PdtNewPdtInfo> entry = (Entry) var13.next();
      result.add((PdtNewPdtInfo) entry.getValue());
    }

    write(result);
  }
}
