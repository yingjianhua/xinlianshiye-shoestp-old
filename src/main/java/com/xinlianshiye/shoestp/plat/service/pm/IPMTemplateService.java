package com.xinlianshiye.shoestp.plat.service.pm;

import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.service.pm.imp.PMTemplateServiceImp;
import com.xinlianshiye.shoestp.plat.view.pm.TemplateView;

import irille.Entity.pm.PMTemplate;

@ImplementedBy(PMTemplateServiceImp.class)
public interface IPMTemplateService {
  void initTemp();

  List<TemplateView> list();

  void upd(TemplateView view);

  void enable(TemplateView view);

  Map<Integer, PMTemplate> getTemplateMap();
}
