package com.xinlianshiye.shoestp.plat.service.pm;

import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.service.pm.imp.VariableServiceImp;
import com.xinlianshiye.shoestp.plat.view.pm.VariableView;

import irille.Entity.pm.PM.OTempType;

@ImplementedBy(VariableServiceImp.class)
public interface IVariableService {

  List<String> loadByTempType(Integer type);

  String render(Map<String, VariableView> variableMap, String template, Object[] objects);

  Map<String, VariableView> getMap(OTempType tempType);

  boolean hasUnableVariable(String template, Byte type);

  List<String> varibaleFromContent(String template);
}
