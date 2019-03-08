package com.xinlianshiye.shoestp.plat.dao.pm;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.dao.pm.imp.PMTemplateDaoImp;

import irille.Entity.pm.PMTemplate;

@ImplementedBy(PMTemplateDaoImp.class)
public interface IPMTemplateDao {

	List<PMTemplate> list();

	void add(List<PMTemplate> addBeans);

	PMTemplate load(Integer pkey);

	void del(List<PMTemplate> delBeans);
	
}
