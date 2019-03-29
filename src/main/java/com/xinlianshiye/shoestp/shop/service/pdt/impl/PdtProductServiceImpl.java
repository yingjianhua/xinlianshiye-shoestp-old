/** */
package com.xinlianshiye.shoestp.shop.service.pdt.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.xinlianshiye.shoestp.shop.dao.PdtProductDao;
import com.xinlianshiye.shoestp.shop.service.pdt.PdtProductService;
import com.xinlianshiye.shoestp.shop.view.pdt.ProdSearchView;
import com.xinlianshiye.shoestp.shop.view.pdt.SortView;

import irille.Dao.SVS.SVSInfoService;
import irille.pub.tb.FldLanguage.Language;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;
import irille.view.pdt.PdtSearchView;
import irille.view.pub.PageSearch;

/** @author liyichao */
public class PdtProductServiceImpl implements PdtProductService {

  private irille.Dao.PdtProductDao pdtProductDao = new irille.Dao.PdtProductDao();

  @Inject PdtProductDao productDao;

  @Inject private SVSInfoService svsInfoService;

  @Override
  public Page list(
      UsrPurchase purchase,
      ProdSearchView search,
      List<SortView> sort,
      Integer start,
      Integer limit,
      Language language) {
    Page data = productDao.list(purchase, search, sort, start, limit, language);
    data.getItems().stream()
        .map(
            bean -> {
              PdtSearchView view = (PdtSearchView) bean;
              view.setSvsInfo(svsInfoService.getSvsRatingAndRos(view.getSupId()));
              return view;
            })
        .collect(Collectors.toList());
    PageSearch pageSearch = new PageSearch(data);
    if (null != search.getCategory() && search.getCategory() > 0) {
      pageSearch.setBreadcrumbnav(pdtProductDao.getBreadcrumbNav(search.getCategory()));
    }
    return pageSearch;
  }
}
