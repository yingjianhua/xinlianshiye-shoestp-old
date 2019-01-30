package irille.sellerAction.rfq;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Entity.RFQ.JSON.RFQConsultQuoteInfo;
import irille.Service.Manage.RFQ.IRFQManageService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.rfq.Imp.IRFQConsultAction;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 14:47
 */
public class RFQConsultAction extends SellerAction implements IRFQConsultAction {

    @Inject
    private IRFQManageService irfqManageService;

    @Inject
    private ObjectMapper objectMapper;


    @Setter
    private Integer start;
    @Setter
    private Integer limit;
    @Setter
    private String keyword;
    @Setter
    private Integer id;


    @Setter
    private String data;

    public void getRFQList() throws IOException {
        if (start == null) start = 0;
        if (limit == null) limit = 30;
        write(irfqManageService.getRFQList(start, limit, keyword, getSupplier().getPkey()));
    }

    public void getRFQInfo() throws IOException {
        write(irfqManageService.getRFQInfo(id, getSupplier().getPkey()));
    }

    public void putRFQQuoteInfo() throws IOException {
        RFQConsultQuoteInfo quoteInfo = objectMapper.readValue(data, RFQConsultQuoteInfo.class);
        irfqManageService.putRFQQuoteInfo(quoteInfo, getSupplier().getPkey());
        write();
    }
}
