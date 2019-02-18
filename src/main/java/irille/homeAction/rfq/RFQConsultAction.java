package irille.homeAction.rfq;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQConsultService;
import com.xinlianshiye.shoestp.shop.service.rfq.RFQPurchaseContactService;

import irille.Filter.svr.ItpCheckPurchaseLogin;
import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin;
import irille.Service.Pdt.IPdtProductService;
import irille.Service.Plt.PltService;
import irille.Service.RFQ.IRFQConsultService;
import irille.homeAction.HomeAction;
import irille.pub.tb.FldLanguage;
import irille.pub.util.ipUtils.City;
import irille.view.RFQ.PutInquiryView;
import irille.view.RFQ.PutRFQConsultView;
import irille.view.RFQ.RFQPdtInfo;
import irille.view.plt.CountryView;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 9:35
 */
public class RFQConsultAction extends HomeAction implements IRFQConsultAction {

    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private IRFQConsultService irfqConsultService;
    @Inject
    private RFQConsultService rFQConsultService;
    @Inject
    private RFQPurchaseContactService rFQPurchaseContactService;
    @Inject
    private IPdtProductService iPdtProductService;

    @Setter
    private String data;
    @Setter
    private Integer id;
    @Setter
    private int type;


    @Inject
    private PltService pltService;

    /**
     * @Description: RFQ询盘
     * @date 2019/1/30 11:10
     * @author lijie@shoestp.cn
     */
    @ItpCheckPurchaseLogin.NeedLogin
    public void putRFQInquiry() throws IOException {
        irfqConsultService.putRFQInquiry(objectMapper.readValue(getJsonBody(), PutRFQConsultView.class), getPurchase());
        write();
    }

    /**
     * @Description: 询盘
     * @date 2019/1/30 11:10
     * @author lijie@shoestp.cn
     */
    @ItpCheckPurchaseLogin.NeedLogin
    public void putInquiry() throws IOException {
        PutInquiryView view = objectMapper.readValue(getJsonBody(), PutInquiryView.class);
        String[] country = City.find(ServletActionContext.getRequest().getRemoteAddr());
        int countryId=-1;
        if (country != null && country.length > 0) {
            for (CountryView countryView : pltService.getCountryList(FldLanguage.Language.zh_CN)) {
                if (countryView.getName().indexOf(country[0]) != -1) {
                    countryId=countryView.getId();
                }
            }
        }
        irfqConsultService.putInquiry(view, getPurchase(),countryId);
        write();
    }

    public void getPdtInfo() throws IOException {
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
        irfqConsultService.putPrivateInquiry(objectMapper.readValue(getJsonBody(), PutInquiryView.class), getPurchase());
        write();
    }
    /**
     * @Description: 展会介绍页1
     * @author winson Zhang
     */
    public String exhibitionshow() {
        setResult("/html/exhibition/exhibitionLasVegas.jsp", false);
        return TRENDS;
    }
    /**
     * @Description: 展会介绍页2
     * @author winson Zhang
     */
    public String ExpoRivaSchuhshow() {
        setResult("/html/exhibition/ExpoRivaSchuh.jsp", false);
        return TRENDS;
    }
    /**
     * @Description: 展会介绍页3
     * @author winson Zhang
     */
    public String guangjiaohuishow() {
        setResult("/html/exhibition/guangjiaohui.jsp", false);
        return TRENDS;
    }
    
    private String keyword;
    private Byte t;
    private Boolean unread;
    private Integer start = 0;
    private Integer limit = 10;

	@Override
	@NeedLogin
	public void pageMine() throws IOException {
		write(rFQConsultService.pageMine(getPurchase(), t, keyword, unread, start, limit));
	}

	private Integer supplierPkey;
	
	@Override
	public void addContact() throws IOException {
		rFQPurchaseContactService.add(getPurchase(), supplierPkey);
		write();
	}

	@Override
	public void deleteContact() throws IOException {
		rFQPurchaseContactService.delete(getPurchase(), supplierPkey);
		write();
	}
    
}
