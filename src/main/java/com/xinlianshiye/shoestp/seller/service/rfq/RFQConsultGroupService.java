package com.xinlianshiye.shoestp.seller.service.rfq;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.seller.service.rfq.impl.RFQConsultGroupServiceImpl;

import irille.sellerAction.rfq.view.RFQConsultGroupView;
import irille.shop.usr.UsrSupplier;

@ImplementedBy(RFQConsultGroupServiceImpl.class)
public interface RFQConsultGroupService {

	/**
	 * 新增询盘分组
	 * @param supplier 供应商
	 * @param view 询盘分组信息 包括分组名字
	 * @return
	 * @author Jianhua Ying
	 */
	RFQConsultGroupView create(UsrSupplier supplier, RFQConsultGroupView view);
    
	/**
	 * 编辑询盘分组
	 * @param supplier 供应商
	 * @param view 询盘分组信息 包括询盘分组名字和主键
	 * @return
	 * @author Jianhua Ying
	 */
	RFQConsultGroupView edit(UsrSupplier supplier, RFQConsultGroupView view);
	
	/**
	 * 询盘分组列表
	 * @param supplier 供应商
	 * @return
	 * @author Jianhua Ying
	 */
	List<RFQConsultGroupView> list(UsrSupplier supplier);
    
	/**
	 * 删除供应商的询盘分组
	 * @param supplier 供应商
	 * @param view 询盘分组信息 包括询盘分组的主键
	 * @author Jianhua Ying
	 */
    void delete(UsrSupplier supplier, RFQConsultGroupView view);
}
