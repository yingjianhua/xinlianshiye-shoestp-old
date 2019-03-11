package com.xinlianshiye.shoestp.shop.service.rfq;

import java.util.Date;
import java.util.List;

import com.google.inject.ImplementedBy;
import com.xinlianshiye.shoestp.shop.service.rfq.impl.RFQConsultServiceImpl;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultRelationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQConsultView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQQuotationView;
import com.xinlianshiye.shoestp.shop.view.rfq.RFQUnreadCountView;

import irille.homeAction.rfq.view.RFQDetailsView;
import irille.homeAction.rfq.view.RFQListView;
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
     * 统计询盘的未读消息
     * @param purchase 采购商
     * @return
     * @author Jianhua Ying
     */
    RFQUnreadCountView countUnread(UsrPurchase purchase);
    
    /**
     * RFQ或询盘详情
     * @param purchase 采购商
     * @param consultPkey 询盘主键
     * @return
     * @author Jianhua Ying
     */
    RFQConsultView getDetail(UsrPurchase purchase, Integer consultPkey);
    
    /**
     * 给RFQ询盘添加额外的信息,更新有效时间
     * 
     * @param purchase 采购商
     * @param consultPkey 询盘主键
     * @param information 额外信息
     * @param validDate 有效期
     * @author Jianhua Ying
     */
    void addMoreInformation(UsrPurchase purchase, Integer consultPkey, String information, Date validDate);
    
    /**
     * 给询盘添加图片
     * <p>只有供应商询盘能在发布后继续添加图片, 上限五张
     * @param purchase 采购商
     * @param consultPkey 询盘主键
     * @param images 图片链接 多图 通过逗号分隔
     * @author Jianhua Ying
     */
    void addImage(UsrPurchase purchase, Integer consultPkey, String images);
    
    /**
     * 给询盘添加感兴趣的产品
     * <p>只有供应商询盘能添加感兴趣产品, 上限50个, 保存产品的主键,以即名称和图片作为冗余
     * @param purchase 采购商
     * @param consultPkey 询盘主键
     * @param products 产品主键, 多产品通过逗号分隔
     * @author Jianhua Ying
     */
    void addProductRequest(UsrPurchase purchase, Integer consultPkey, String products);

    /**
     * 关闭RFQ询盘
     * <p>可以在任何状态下关闭询盘,关闭之后 询盘不能进行编辑 增加额外信息和报价 以即再次关闭.
     * @param purchase 采购商
     * @param consultPkey 询盘主键
     * @author Jianhua Ying
     */
    void close(UsrPurchase purchase, Integer consultPkey);
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

	/**
	 * 获取RFQ列表
	 * @author zjl
	 * @Date 2019/2/27 14:35
	 * @return
	 */
	List<RFQListView> getRFQList();

	/**
	 * 获取RFQ详情
	 * @author zjl
	 * @Date 2019/2/27 16:13
	 * @return
	 */
	RFQDetailsView getRFQDetails(Integer pkey);
}
