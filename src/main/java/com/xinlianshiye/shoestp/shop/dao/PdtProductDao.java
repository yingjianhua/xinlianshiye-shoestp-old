/** */
package com.xinlianshiye.shoestp.shop.dao;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.dao.impl.PdtProductDaoImpl;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

/** @author liyichao */
@ImplementedBy(PdtProductDaoImpl.class)
public interface PdtProductDao {

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