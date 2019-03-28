/** */
package com.xinlianshiye.shoestp.shop.service.pdt;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.pdt.impl.PdtProductServiceImpl;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

/** @author liyichao */
@ImplementedBy(PdtProductServiceImpl.class)
public interface PdtProductService {

  /**
   * @auther liyichao
   * @param purchase
   * @param search
   * @param start
   * @param limit
   * @param language
   * @return
   */
  Page list(
      UsrPurchase purchase, ProdSearchView search, Integer start, Integer limit, Language language);
}
