package com.xinlianshiye.shoestp.plat.service.usr;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.service.usr.imp.UsrServiceImp;

import irille.Entity.pm.PM.ORCVRType;

@ImplementedBy(UsrServiceImp.class)
public interface IUsrService {

  List<String> getMailsBy(ORCVRType type);

  Integer count(ORCVRType type);
}
