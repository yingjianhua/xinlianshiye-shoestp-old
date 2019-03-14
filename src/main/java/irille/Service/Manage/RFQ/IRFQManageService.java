package irille.Service.Manage.RFQ;

import java.io.IOException;
import java.util.Date;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.RFQ.Imp.RFQManageServiceImp;
import irille.sellerAction.rfq.view.RFQConsultQuoteInfoView;
import irille.view.Manage.RFQ.RFQManageInfoView;
import irille.view.Manage.RFQ.RFQMyuoteInfo;
import irille.view.Manage.RFQ.RFQPdtInfo;
import irille.view.Page;

/** Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2019/1/30 Time: 14:57 */
@ImplementedBy(RFQManageServiceImp.class)
public interface IRFQManageService {

  Page getRFQList(int start, int limit, String keyword, Integer supId);

  RFQManageInfoView getRFQInfo(int id, int supId);

  int putRFQQuoteInfo(RFQConsultQuoteInfoView quoteInfo, Integer pkey);

  RFQPdtInfo getPdtInfo(Integer id, Integer pkey);

  Page getPdtList(Integer start, Integer limit, String keyword, Integer pkey);

  Page getMyRFQQuoteList(
      Integer start,
      Integer limit,
      Byte type,
      Date date,
      String keyword,
      boolean flag,
      Integer status,
      Integer country,
      int supid,
      Integer userCountry)
      throws IOException;

  RFQMyuoteInfo getMyRFQQuoteInfo(Integer id, Integer pkey) throws IOException;
}
