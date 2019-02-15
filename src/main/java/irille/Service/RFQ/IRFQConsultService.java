package irille.Service.RFQ;

import com.google.inject.ImplementedBy;

import irille.Service.RFQ.Imp.RFQConsultServiceImp;
import irille.homeAction.rfq.view.RFQConsultView;
import irille.shop.usr.UsrPurchase;
import irille.view.Page;
import irille.view.RFQ.PutInquiryView;
import irille.view.RFQ.PutRFQConsultView;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 9:52
 */
@ImplementedBy(RFQConsultServiceImp.class)
public interface IRFQConsultService {
    void putRFQInquiry(PutRFQConsultView rfqConsultView, UsrPurchase usrPurchase);

    void putInquiry(PutInquiryView readValue, UsrPurchase purchase,int countryId);

    void putPrivateInquiry(PutInquiryView readValue, UsrPurchase purchase);
    
    /**
     * 分页查询我发布的RFQ和询盘
     * 
     * @param purchase 采购商
     * @param type 询盘类型
     * @param keyword 搜索关键字
     * @param unread 未读
     * @param start 开始记录数
     * @param limit 每页记录数
     * @author Jianhua Ying
     */
    Page<RFQConsultView> pageMine(UsrPurchase purchase, Byte type, String keyword, Boolean unread, Integer start, Integer limit);
}
