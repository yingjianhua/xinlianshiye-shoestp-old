package irille.homeAction.rfq;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Filter.svr.ItpCheckPurchaseLogin;
import irille.Service.Pdt.IPdtProductService;
import irille.Service.RFQ.IRFQConsultService;
import irille.homeAction.HomeAction;
import irille.view.RFQ.PutInquiryView;
import irille.view.RFQ.PutRFQConsultView;
import irille.view.RFQ.RFQPdtInfo;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 9:35
 */
public class RFQConsultAction extends HomeAction {

    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private IRFQConsultService irfqConsultService;
    @Inject
    private IPdtProductService iPdtProductService;

    @Setter
    private String data;
    @Setter
    private Integer id;
    @Setter
    private int type;

    /**
     * @Description: RFQ询盘
     * @date 2019/1/30 11:10
     * @author lijie@shoestp.cn
     */
    @ItpCheckPurchaseLogin.NeedLogin
    public void putRFQInquiry() throws IOException {
        irfqConsultService.putRFQInquiry(objectMapper.readValue(data, PutRFQConsultView.class), getPurchase());
        write();
    }

    /**
     * @Description: 询盘
     * @date 2019/1/30 11:10
     * @author lijie@shoestp.cn
     */
    @ItpCheckPurchaseLogin.NeedLogin
    public void putInquiry() throws IOException {
        irfqConsultService.putInquiry(objectMapper.readValue(data, PutInquiryView.class), getPurchase());
        write();
    }

    public void loadPdtInfo() throws IOException {
        RFQPdtInfo rfqPdtInfo = iPdtProductService.getInquiryPdtInfo(id);
        if (type == 1 && id != null && id > 0) {

        }
        write(rfqPdtInfo);
    }

    /**
     * @Description: 私人展会询盘
     * @date 2019/1/30 11:10
     * @author lijie@shoestp.cn
     */
    @ItpCheckPurchaseLogin.NeedLogin
    public void putPrivateInquiry() throws IOException {
        irfqConsultService.putPrivateInquiry(objectMapper.readValue(data, PutInquiryView.class), getPurchase());
        write();
    }
}
