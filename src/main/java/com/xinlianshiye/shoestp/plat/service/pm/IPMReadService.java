package com.xinlianshiye.shoestp.plat.service.pm;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMReadServiceImp;

@ImplementedBy(PMReadServiceImp.class)
public interface IPMReadService {
  public void read(Integer reader, Integer message);
}
