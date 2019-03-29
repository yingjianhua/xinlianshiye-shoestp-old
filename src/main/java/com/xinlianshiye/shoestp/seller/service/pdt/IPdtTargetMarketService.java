/** */
package com.xinlianshiye.shoestp.seller.service.pdt;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.seller.service.pdt.impl.PdtTargetMarketServiceImpl;

import irille.shop.pdt.PdtProduct;

/** @author liyichao */
@ImplementedBy(PdtTargetMarketServiceImpl.class)
public interface IPdtTargetMarketService {

  /**
   * @auther liyichao
   * @param pdtPkey
   * @param countries
   */
  void ins(PdtProduct pdtPkey, String countries);
}
