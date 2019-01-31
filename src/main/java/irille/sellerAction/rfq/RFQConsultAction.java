package irille.sellerAction.rfq;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.JSON.RFQConsultQuoteInfo;
import irille.Service.Manage.RFQ.IRFQManageService;
import irille.Service.Manage.RFQ.RFQConsultService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.rfq.inf.IRFQConsultAction;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 14:47
 */
@Setter
public class RFQConsultAction extends SellerAction<RFQConsult> implements IRFQConsultAction {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private IRFQManageService irfqManageService;
    @Inject
    private RFQConsultService rFQConsultService;

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
    
    private Integer groupId;
    private Boolean flagId;
    private Byte type;
    private Boolean haveNewMsg;
    private Boolean isDeleted;
    private Date startDate;
    private Date endDate;
    
    private String ids;
    
    /**
     * 分页查询询盘信息
     * 查询条件
     * 询盘类型
     * 询盘标题
     * 询盘采购商名称
     * 
     * @author Jianhua Ying
     */
    @Override
    public void list() throws IOException {
    	//TODO 未完成
    	irfqManageService.page(start, limit, keyword, groupId, flagId, type, haveNewMsg, isDeleted, startDate, endDate);
    }

	@Override
	public void moveToRecycled() throws IOException {
		rFQConsultService.moveToRecycled(getSupplier(), ids, true);
		write();
	}

	@Override
	public void recovery() throws IOException {
		rFQConsultService.moveToRecycled(getSupplier(), ids, false);
		write();
	}

	@Override
	public void moveToGroup() throws IOException {
		rFQConsultService.moveToGroup(getSupplier(), ids, groupId);
		write();
	}
    
}
