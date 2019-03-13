package com.xinlianshiye.shoestp.plat.service.pm.imp;

import java.util.Date;

import com.xinlianshiye.shoestp.plat.service.pm.IPMReadService;

import irille.Entity.pm.PMRead;

public class PMReadServiceImp implements IPMReadService {

  @Override
  public void read(Integer reader, Integer message) {
    PMRead read = new PMRead();
    read.setReadMessage(message);
    read.setReader(reader);
    read.setReadTime(new Date());
    read.ins();
  }
}
