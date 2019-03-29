/** */
package com.xinlianshiye.shoestp.seller.service.pdt.impl;

import java.util.ArrayList;
import java.util.List;

import com.xinlianshiye.shoestp.seller.service.pdt.IPdtTargetMarketService;

import irille.pub.bean.BeanBase;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.pub.util.BatchUtils;
import irille.pub.validate.Regular;
import irille.pub.validate.ValidRegex;
import irille.shop.pdt.PdtProduct;
import irille.shop.pdt.PdtTargetMarket;
import irille.shop.plt.PltCountry;

/** @author liyichao */
public class PdtTargetMarketServiceImpl implements IPdtTargetMarketService {

  @Override
  public void ins(PdtProduct pdt, String countries) {
    if (null != countries) {
      if (!ValidRegex.regMarch(Regular.REGULAR_PKEY_ARRS, countries)) {
        throw new WebMessageException(ReturnCode.failure, "非法参数");
      }

      SQL sql = new SQL();
      sql.DELETE_FROM(PdtTargetMarket.class)
          .WHERE(PdtTargetMarket.T.PRODUCT, " =? ", pdt.getPkey());
      Query.sql(sql).executeUpdate();

      List<PltCountry> css =
          BeanBase.list(
              PltCountry.class,
              PltCountry.T.PKEY.getFld().getCodeSqlField() + " in (" + countries + ")",
              false);
      List<PdtTargetMarket> mars = new ArrayList<>();
      for (PltCountry c : css) {
        PdtTargetMarket m = new PdtTargetMarket();
        m.setCountry(c.getPkey());
        m.setCountryName(c.getName());
        m.setProduct(pdt.getPkey());
        m.setRowVersion((short) 1);
        mars.add(m);
      }
      if (null != mars && mars.size() > 0) {
        BatchUtils.batchIns(PdtTargetMarket.class, mars);
      }
    }
  }
}
