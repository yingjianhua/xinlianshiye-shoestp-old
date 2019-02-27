package irille.Dao.RFQ;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultServiceImpl;
import irille.platform.rfq.view.RFQConsultRelationView;
import irille.platform.rfq.view.RFQConsultView;
import irille.sellerAction.rfq.view.RFQConsultQuoteInfoView;
import irille.view.Page;

@ImplementedBy(RFQConsultServiceImpl.class)
public interface RFQConsultService {

	/**
	 * 分页查询询盘列表
	 * 
	 * @return
	 * @author Jianhua Ying
	 */
	Page<RFQConsultView> page(Integer start, Integer limit, RFQConsultView condition);
	
	/**
	 * 查询询盘的详细信息
	 * 
	 * @param condition
	 * @return
	 * @author Jianhua Ying
	 */
	RFQConsultView detail(RFQConsultView condition);

	
	/**
	 * 审核询盘
	 * 
	 * @param view 询盘
	 * @param verify true: 审核通过, false: 审核失败
	 * @author Jianhua Ying
	 */
	void approve(RFQConsultView view, Boolean verify);
	
	/**
	 * <h1>删除询盘</h1>
	 * 
	 * <p>
	 * 询盘只能在状态为已完成和已关闭的状态下删除, 目前由于聊天内容和询盘耦合在一起, 所以删除询盘只做标记删除, 删除后的询盘不能再继续被抢单, 已经被抢单的询盘不影响聊天以及其它功能;
	 * @param view 询盘
	 * @author Jianhua Ying
	 */
	void delete(RFQConsultView view);

	/**
	 * 是否推荐RFQ到首页
	 * @author zjl
	 * @Date 2019/2/27 13:23
	 * @param view
	 */
	void recommend(RFQConsultView view);
	
	/**
	 * 商家询盘报价信息
	 * 
	 * @param view 询盘关联表, 需要包含主键
	 * @return 报价信息
	 * @author Jianhua Ying
	 */
	RFQConsultQuoteInfoView relationInfo(RFQConsultRelationView view);
}
