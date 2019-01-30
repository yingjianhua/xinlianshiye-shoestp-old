package irille.Service.Manage.RFQ;

import com.google.inject.ImplementedBy;
import irille.Entity.RFQ.JSON.RFQConsultQuoteInfo;
import irille.Service.Manage.RFQ.Imp.RFQManageServiceImp123;
import irille.view.Manage.RFQ.RFQListBodyInfoView;
import irille.view.Manage.RFQ.RFQManageInfoView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 14:57
 */

@ImplementedBy(RFQManageServiceImp123.class)
public interface IRFQManageService123 {

    List<RFQListBodyInfoView> getRFQList(int start, int limit, String keyword, Integer supId);


    RFQManageInfoView getRFQInfo(int id, int supId);

    int putRFQQuoteInfo(RFQConsultQuoteInfo quoteInfo, Integer pkey);
}
