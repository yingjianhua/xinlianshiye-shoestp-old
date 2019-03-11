package com.xinlianshiye.shoestp.plat.dao.pm;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.plat.dao.pm.imp.PMMessageDaoImp;
import com.xinlianshiye.shoestp.plat.view.pm.MessageView;

import irille.Entity.pm.PM.ORCVRType;
import irille.view.Page;

@ImplementedBy(PMMessageDaoImp.class)
public interface IPMMessageDao {
	Page<MessageView> list(MessageView view,Integer start,Integer limit);
	
	Page<MessageView> list(Integer rec,ORCVRType type,Integer start,Integer limit);
}
