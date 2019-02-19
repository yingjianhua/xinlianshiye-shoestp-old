package com.xinlianshiye.shoestp.shop.service.rfq;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQConsultServiceImpl;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;

import irille.shop.usr.UsrPurchase;
import irille.view.Page;

@ImplementedBy(RFQConsultServiceImpl.class)
public interface RFQConsultService {
    
    /**
     * 分页查询我发布的RFQ和询盘
     * 
     * @param purchase 采购商
     * @param type 询盘类型
     * @param keyword 搜索关键字 匹配询盘标题和报价商家的名字
     * @param unread 未读
     * @param start 开始记录数
     * @param limit 每页记录数
     * @author Jianhua Ying
     */
    Page<RFQConsultView> pageMine(UsrPurchase purchase, Byte type, String keyword, Boolean unread, Integer start, Integer limit);
    
    /**
     * RFQ或询盘详情
     * @param purchase 采购商
     * @param consultPkey 询盘主键
     * @return
     * @author Jianhua Ying
     */
    RFQConsultView getDetail(UsrPurchase purchase, Integer consultPkey);

    /**
     * 查询RFQ的报价状况
     * @param consultPkey 询盘主键
     * @return
     * @author Jianhua Ying
     */
	List<RFQConsultRelationView> listRelation(Integer consultPkey);
	
	/**
	 * 采购商删除询盘报价
	 * <li>采购商将不能看到该报价以及该报价的相关内容,但是报价所占用的名额不会恢复
	 * <li>供应商仍然能够该报价的信息, 但是在通过该报价向采购商发送消息时 将提示 该报价已被采购商删除, 供应商可以再次删除该报价(供应商删除报价会彻底删除报价,报价占用名额也会恢复)
	 * 
	 * @param purchase 采购商
	 * @param relationPkey 报价主键
	 * @author Jianhua Ying
	 */
	void deleteQuotation(UsrPurchase purchase, Integer relationPkey);
	
	/**
	 * 报价信息
	 * @param purchase 采购商
	 * @param relationPkey 询盘报价主键
	 * @author Jianhua Ying
	 */
	RFQQuotationView getQuotation(UsrPurchase purchase, Integer relationPkey);
}
