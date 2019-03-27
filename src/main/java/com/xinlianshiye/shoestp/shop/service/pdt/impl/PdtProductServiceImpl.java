/** */
package com.xinlianshiye.shoestp.shop.service.pdt.impl;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.shop.dao.PdtProductDao;
import com.xinlianshiye.shoestp.shop.service.pdt.PdtProductService;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;

import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;

/** @author liyichao */
public class PdtProductServiceImpl implements PdtProductService {

  @Inject PdtProductDao productDao;

  @Override
  public Page list(
      UsrPurchase purchase,
      ProdSearchView search,
      Integer start,
      Integer limit,
      Language language) {
    return productDao.list(purchase, search, start, limit, language);
  }
}
