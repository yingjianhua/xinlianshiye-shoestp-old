package com.xinlianshiye.shoestp.shop.service.rfq;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQConsultServiceImpl;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;

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
     * 查询RFQ的报价状况
     * @param consultPkey 询盘主键
     * @return
     * @author Jianhua Ying
     */
	List<RFQConsultRelationView> listRelation(Integer consultPkey);
}
