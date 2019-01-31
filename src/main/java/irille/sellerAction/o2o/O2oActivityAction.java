package irille.sellerAction.o2o;

import irille.Entity.O2O.Enums.O2O_ProductStatus;
import irille.Entity.O2O.O2O_Activity;
import irille.Service.Manage.O2O.IO2OActicityServer;
import irille.Service.Manage.O2O.IO2OPdtServer;
import irille.sellerAction.SellerAction;
import irille.sellerAction.o2o.inf.IO2oActivityAction;
import lombok.Setter;
import org.json.JSONException;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/28
 * Time: 9:53
 */
public class O2oActivityAction extends SellerAction<O2O_Activity> implements IO2oActivityAction {

    @Inject
    private IO2OPdtServer o2oPdtServer;

    @Inject
    private IO2OActicityServer o2oActicityServer;

    @Setter
    private Date startDate;
    @Setter
    private Date endDate;
    @Setter
    private String keyword;
    @Setter
    private Integer countryId;
    @Setter
    private Integer id;
    @Setter
    private Integer status;
    @Setter
    private String name;
    @Setter
    private String tel;
    @Setter
    private String pdts;
    @Setter
    private String reason;


    @Setter
    private int isMyjoin;

    /**
     * 获取活动列表
     * 当isMyJoin为0时获取所有活动
     * 否则获取当前登录用户参加的活动
     */
    public void getActivityList() throws IOException {
        if (getLimit() <= 0) setLimit(10);
        if (getStart() <= 0) setStart(0);
        int supId = isMyjoin == 0 ? -1 : getSupplier().getPkey();
        write(o2oPdtServer.getO2OActivityList(getSupplier(),getStart(), getLimit(), startDate, endDate, keyword,status, supId, countryId));
    }

    /**
     * 加载活动信息
     * @throws IOException
     */
    public void load() throws IOException {
        write(o2oPdtServer.load(getSupplier(),id));
    }

    @Override
    public void getActivityInfo() throws IOException {
        write(o2oPdtServer.getO2OActivityInfo(getStart(), getLimit(), id, getSupplier().getPkey()));
    }


    public void enroll() throws IOException, JSONException {
        o2oActicityServer.enroll(getSupplier(),id,name,tel,pdts);
        writeSuccess();
    }

    /**
     * 申请下架
     */
    public void lower() throws IOException, JSONException {
        o2oPdtServer.lowerAndUpper(id,reason, O2O_ProductStatus.OFF);
        writeSuccess();
    }
    /**
     * 申请下架
     */
    public void upper() throws IOException, JSONException {
        o2oPdtServer.lowerAndUpper(id,null, O2O_ProductStatus.ON);
        writeSuccess();
    }

    @Override
    public void listAllGeneral() throws IOException {
        write(o2oPdtServer.listAllGeneral(getSupplier(),id));
    }


}
