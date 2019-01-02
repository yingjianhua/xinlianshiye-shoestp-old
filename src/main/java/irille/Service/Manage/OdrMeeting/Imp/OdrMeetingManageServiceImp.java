package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.OdrMeetingDao;
import irille.Dao.Old.OdrMeeting.OdrMeetingAuditInsDao;
import irille.Dao.Old.OdrMeeting.OdrMeetingInsDao;
import irille.Dao.OrderMeetingAuditDao;
import irille.Dao.OrderMeetingAuditReleaseDao;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingStatus;
import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Entity.OdrerMeetings.OrderMeetingAudit;
import irille.Entity.OdrerMeetings.OrderMeetingAuditRelease;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.pub.util.SetBeans.SetBean.SetBeans;
import irille.pub.util.GetValue1;
import irille.shop.usr.Usr;
import irille.view.Manage.OdrMeeting.OdrMeetingAuditLogisticsView;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import irille.view.Manage.OdrMeeting.Sale.OdrMeetingSaleInfoView;
import irille.view.Manage.OdrMeeting.Sale.OdrMeetingSpecSaleInfoView;
import irille.view.Manage.OdrMeeting.initiatedActivity.LaunchlistMeettingView;
import irille.view.Manage.OdrMeeting.initiatedActivity.OrderInformationView;
import irille.view.Page;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/11/14 Time: 13:37
 */
public class OdrMeetingManageServiceImp implements IOdrMeetingManageService {

    @Inject
    private OdrMeetingDao odrMeetingDao;

    @Inject
    private OdrMeetingInsDao odrMeetingInsDao;

    @Inject
    private OdrMeetingAuditInsDao odrMeetingAuditInsDao;

    @Inject
    private OrderMeetingAuditDao orderMeetingAuditDao;

    /**
     * @Description: 添加订购会信息   name 为
     * @date 2018/11/15 11:10
     * @author lijie@shoestp.cn
     */
    @Override
    public void insOdrMeeting(OdrMeetingInfoView getOdrMeetingInfoView, int supplierId) {
        OrderMeeting orderMeeting = new OrderMeeting();
        orderMeeting.setSupplierid(supplierId);
        orderMeeting.setName(getOdrMeetingInfoView.getName());
        orderMeeting.stStatus(OrderMeetingStatus.DEFAULT);
        //如果外键小于1 那么即自定义展会地址
        if (getOdrMeetingInfoView.getExhibitionId() > 0) {
            orderMeeting.setExhibition(getOdrMeetingInfoView.getExhibitionId());
        } else {
            orderMeeting.setCustomExhibition(orderMeeting.getCustomExhibition());
        }
        orderMeeting.setCountry(getOdrMeetingInfoView.getCountry());
        orderMeeting.setLogo(getOdrMeetingInfoView.getLogo());
        orderMeeting.setStartTime(getOdrMeetingInfoView.getStart_time());
        orderMeeting.setEndTime(getOdrMeetingInfoView.getEnd_time());
        //JSON字段 因为mysql 能处理json字段.方便以后一些奇怪的需求
        orderMeeting.setMailaddress(getOdrMeetingInfoView.getMailAddress());
        orderMeeting.setMailafullddress(getOdrMeetingInfoView.getMailFulladdress());
        orderMeeting.setPostcode(getOdrMeetingInfoView.getPostcode());
        orderMeeting.setMailname(getOdrMeetingInfoView.getMailname());
        orderMeeting.setMailtel(getOdrMeetingInfoView.getMailtel());
        orderMeeting.setUpdatedTime(new Date());
        odrMeetingInsDao.setB(orderMeeting).commit();
    }

    @Override
    public OdrMeetingInfoView getOdrMeetingInfo(int id) {
//    OrderMeeting orderMeeting = odrMeetingDao.getOdrMeetingInfo(id);
//    OdrMeetingInfoView odrMeetingInfoView = new OdrMeetingInfoView();
//    odrMeetingInfoView.setId(orderMeeting.getPkey());
//    odrMeetingInfoView.setName(orderMeeting.getName());
//    odrMeetingInfoView.setExhibitionId(orderMeeting.getExhibition());
//    odrMeetingInfoView.setCustomExhibition(orderMeeting.getCustomExhibition());
//    odrMeetingInfoView.setCountry(orderMeeting.getCountry());
//    odrMeetingInfoView.setStatus(orderMeeting.gtStatus().name());
//    odrMeetingInfoView.setStatusId(Integer.valueOf(orderMeeting.getStatus()));
//    odrMeetingInfoView.setLogo(orderMeeting.getLogo());
//    odrMeetingInfoView.setStart_time(orderMeeting.getStartTime());
//    odrMeetingInfoView.setEnd_time(orderMeeting.getEndTime());
//    odrMeetingInfoView.setMailAddress(orderMeeting.getMailaddress());
//    odrMeetingInfoView.setMailFulladdress(orderMeeting.getMailafullddress());
//    odrMeetingInfoView.setPostcode(orderMeeting.getPostcode());
//    odrMeetingInfoView.setMailname(orderMeeting.getMailname());
//    odrMeetingInfoView.setMailtel(orderMeeting.getMailtel());
//    return odrMeetingInfoView;
        return null;
    }

    @Override
    public void removeOdrMeeting(int... id) {

    }

    @Override
    public OdrMeetingInfoView getMyOdrMeetingInfo(Integer pkeys) {
        return null;
    }

    @Override
    public Page getMyOdrMeetingList(Integer start, Integer limit, String name, Integer supstate, Integer state, Integer supplierpkey) {
        return odrMeetingDao.Launchlist(start, limit, name, supstate, state, supplierpkey);
    }

    @Override
    public Page getMyJoinOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey) {
        return odrMeetingDao.participatelist(start, limit, name, state, supplierpkey);
    }

    @Override
    public Page getMyOtherOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey) {
        return odrMeetingDao.Otherlist(start, limit, name, state, supplierpkey);
    }

    @Override
    public void joInOdrMeeting(int odrMeetIngId, int supplier) {
        if (odrMeetingDao.isJoinOdrMeeting(odrMeetIngId, supplier)) {
            OrderMeetingAudit audit = new OrderMeetingAudit();
            audit.setOdrmeeting(odrMeetIngId);
            audit.stStatus(OrderMeetingAuditStatus.DEFAULT);
            audit.setSupplierid(supplier);
            audit.setCreatedTime(new Date());
            odrMeetingAuditInsDao.setB(audit).commit();
        }
    }


    @Override
    public JSONObject loadsupstate() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        for (OrderMeetingAuditStatus o : OrderMeetingAuditStatus.values()) {
            if (o.getLine().getKey() == 4) {
                continue;
            }
            JSONObject lineJsona = new JSONObject();
            lineJsona.put("name", o.getLine().getName());
            lineJsona.put("id", o.getLine().getKey());
            ja.put(lineJsona);
        }
        json.put("STORE_ROOT", ja);
        return json;
    }

    public JSONObject isAuthStatus() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        for (Usr.OIsAuth o : Usr.OIsAuth.values()) {
            if (o.getLine().getKey() == 2) {
                continue;
            }
            JSONObject lineJsona = new JSONObject();
            lineJsona.put("name", o.getLine().getName());
            lineJsona.put("id", o.getLine().getKey());
            ja.put(lineJsona);
        }
        json.put("STORE_ROOT", ja);
        return json;
    }

    @Override
    public void updAudit(Integer omtId, Integer supplierId, String orderNumber, String remarks) {
        orderMeetingAuditDao.updAudit(omtId, supplierId, orderNumber, remarks);
    }

    @Override
    public JSONObject loadstate() throws Exception {
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        for (OrderMeetingStatus o : OrderMeetingStatus.values()) {
            if (o.getLine().getKey() == 9) {
                continue;
            }
            JSONObject lineJsona = new JSONObject();
            lineJsona.put("name", o.getLine().getName());
            lineJsona.put("id", o.getLine().getKey());
            ja.put(lineJsona);
        }
        json.put("STORE_ROOT", ja);
        return json;
    }

    @Override
    public Page getMeetingSaleInfo(int start, int limit, int odrMeeting,
                                   int supplierId) {
        Page maps = odrMeetingDao.getMeetingAllSaleInfo(start, limit, odrMeeting);
        List<OdrMeetingSaleInfoView> odrMeetingSaleInfoViews = new ArrayList<>();
        for (Object o : maps.getItems()) {
            Map map = (Map) o;
            OdrMeetingSaleInfoView saleInfoView = new OdrMeetingSaleInfoView();
            saleInfoView.setId(Integer.valueOf(String.valueOf(map.get("pkey"))));
            saleInfoView.setNewPrice(GetValue1.get(map, "newPrice", BigDecimal.class, BigDecimal.ZERO));
            saleInfoView.setPrice_total(GetValue1.get(map, "price_total", BigDecimal.class, BigDecimal.ZERO));
            saleInfoView.setPdtId(GetValue1.get(map, "pdtId", Integer.class, -1));
            saleInfoView.setPdtImage(GetValue1.get(map, "pdtImage", String.class, ""));
            saleInfoView.setQty(GetValue1.get(map, "qty", BigDecimal.class, BigDecimal.ZERO));
            saleInfoView.setType(map.get("pdtSup") == map.get("OmtSup"));
            saleInfoView.setPdtName(GetValue1.get(map, "pdtName", String.class, "No Data"));
            saleInfoView.setStatus(GetValue1.get(map, "status", Byte.class, (byte) 0) == 1);
            List<Map<String, Object>> map1 = odrMeetingDao.getMeetingSpecSaleInfo(odrMeeting, saleInfoView.getPdtId());
            List items = new ArrayList();
            map1.forEach(stringObjectMap -> {
                items.add(SetBeans.set(stringObjectMap, OdrMeetingSpecSaleInfoView.class));
            });
            saleInfoView.setItems(items);
            odrMeetingSaleInfoViews.add(saleInfoView);
        }
        maps.setItems(odrMeetingSaleInfoViews);
        return maps;
    }

    @Override
    public void batchdelete(String pkeys) {
        odrMeetingDao.batchdelete(pkeys);
    }

    @Override
    public void updaddress(OrderMeeting omt) {
        OdrMeetingDao.updaddress ud = new OdrMeetingDao.updaddress();
        ud.setB(omt);
        ud.commit();
    }

    @Override
    public void insertjoinOdr(Integer OMTpkey, Integer supplierkey) {
        OrderMeetingAuditDao.insertjoinOdr ij = new OrderMeetingAuditDao.insertjoinOdr();
        OrderMeetingAudit od = new OrderMeetingAudit();
        od.setOdrmeeting(OMTpkey);
        od.setSupplierid(supplierkey);
        ij.setB(od);
        ij.commit();
    }

    /**
     * @Description: 逻辑删除 参加订购会合作商
     * @date 2018/11/22 11:14
     * @anthor wilson zhang
     */
    @Override
    public void deletejoinOdr(Integer OdrAuditpkey) {
        OrderMeetingAuditDao.deletejoinOdr dj = new OrderMeetingAuditDao.deletejoinOdr();
        OrderMeetingAudit oma = new OrderMeetingAudit();
        oma.setPkey(OdrAuditpkey);
        oma.setStatus(OrderMeetingAuditStatus.DELETE.getLine().getKey());
        dj.setB(oma);
        dj.commit();
    }


    @Override
    public void joindelete(String pkeys) {
        odrMeetingDao.joindelete(pkeys);
    }

    @Override
    public void Meettingupdstate(Integer pkey, Integer state) {
        OdrMeetingDao.Meettingupdstate mgd = new OdrMeetingDao.Meettingupdstate();
        OrderMeeting om = new OrderMeeting();
        om.setPkey(pkey);
        om.setStatus(state.byteValue());
        mgd.setB(om);
        mgd.commit();

    }

    @Override
    public OrderInformationView getorderInformation(Integer id) {
//        return null;
        return odrMeetingDao.getorderInformation(id);
    }

    @Override//添加发布订购会
    public void insOdrmeetting(LaunchlistMeettingView lmv) {
        OdrMeetingDao.insertomt itt = new OdrMeetingDao.insertomt();
        OrderMeeting omt = new OrderMeeting();
        omt.setSupplierid(lmv.getSupplierid());
        omt.setName(lmv.getOrderingTitle());
        if (lmv.getExhibitionname() != null && !lmv.getExhibitionname().equals("")) {
            omt.setCustomExhibition(lmv.getExhibitionname());
        } else {
            omt.setExhibition(lmv.getExhibition());
        }
        omt.setCountry(lmv.getCountry());
        omt.setLogo(lmv.getCoverImage());
        omt.setStartTime(lmv.getOrderStartTime());
        omt.setEndTime(lmv.getOrderEndTime());
        omt.setMailafullddress(lmv.getAddress());
        omt.setPostcode(lmv.getZip());
        omt.setMailname(lmv.getReceiver());
        omt.setMailtel(lmv.getContactNumber());
        itt.setB(omt);
        itt.commit();
        OrderMeetingAuditReleaseDao.insertOdr omar = new OrderMeetingAuditReleaseDao.insertOdr();
        OrderMeetingAuditRelease omare = new OrderMeetingAuditRelease();
        omare.setOdrmeeting(itt.getB().getPkey());
        omar.setB(omare).commit();
    }

    @Override
    public Page cooperationsupplier(Integer start, Integer limit, Integer status, String name, Integer omtid) {
        return orderMeetingAuditDao.cooperationsupplier(start, limit, status, name, omtid);
    }

    @Override
    public void updLoadSupStatus(Integer id, Integer status) {
        orderMeetingAuditDao.updLoadSupStatus(id, status);
    }

    public OdrMeetingAuditLogisticsView getLogistics(Integer omtId, Integer supplierId) {
        return orderMeetingAuditDao.getLogistics(omtId, supplierId);
    }
}
