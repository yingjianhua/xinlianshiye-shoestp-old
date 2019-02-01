package irille.sellerAction.rfq;

import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Entity.RFQ.RFQConsult;
import irille.Service.Manage.RFQ.IRFQManageService;
import irille.Service.Manage.RFQ.RFQConsultService;
import irille.sellerAction.SellerAction;
import irille.sellerAction.rfq.inf.IRFQConsultAction;
import irille.sellerAction.rfq.view.RFQConsultQuoteInfoView;
import lombok.Setter;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

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


    private Integer start;
    private Integer limit;
    private String keyword;
    private Date date;
    private boolean flag;
    private Integer status;
    private Integer country;
    private Integer id;
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
        RFQConsultQuoteInfoView quoteInfo = objectMapper.readValue(data, RFQConsultQuoteInfoView.class);
        irfqManageService.putRFQQuoteInfo(quoteInfo, getSupplier().getPkey());
        write();
    }

    public void getPdtInfo() throws IOException {
        write(irfqManageService.getPdtInfo(id, getSupplier().getPkey()));
    }

    public void getPdtList() throws IOException {
        if (start == null) start = 0;
        if (limit == null) limit = 5;
        write(irfqManageService.getPdtList(start, limit, keyword, getSupplier().getPkey()));
    }

    @Override
    public void getMyRFQQuoteList() throws IOException {
        if (start == null) start = 0;
        if (limit == null) limit = 5;
        write(irfqManageService.getMyRFQQuoteList(start, limit, type,date, keyword, flag, status, country, getSupplier().getPkey()));
    }

    public void getMyRFQQuoteInfo() throws IOException {
        if (start == null) start = 0;
        if (limit == null) limit = 5;
        write(irfqManageService.getMyRFQQuoteInfo(id, getSupplier().getPkey()));
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
