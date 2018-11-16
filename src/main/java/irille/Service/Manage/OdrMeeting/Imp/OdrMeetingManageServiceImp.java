package irille.Service.Manage.OdrMeeting.Imp;

import irille.Dao.OdrMeetingDao;
import irille.Entity.OdrerMeetings.Enums.OrdrerMettingAuditStatus;
import irille.Service.Manage.OdrMeeting.IOdrMeetingManageService;
import irille.pub.idu.IduPage;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import javax.inject.Inject;

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
    OrderMeeting orderMeeting = odrMeetingDao.getOdrMeetingInfo(id);
    OdrMeetingInfoView odrMeetingInfoView = new OdrMeetingInfoView();
    odrMeetingInfoView.setId(orderMeeting.getPkey());
    odrMeetingInfoView.setName(orderMeeting.getName());
    odrMeetingInfoView.setExhibitionId(orderMeeting.getExhibition());
    odrMeetingInfoView.setCustomExhibition(orderMeeting.getCustomExhibition());
    odrMeetingInfoView.setCountry(orderMeeting.getCountry());
    odrMeetingInfoView.setStatus(orderMeeting.gtStatus().name());
    odrMeetingInfoView.setStatusId(Integer.valueOf(orderMeeting.getStatus()));
    odrMeetingInfoView.setLogo(orderMeeting.getLogo());
    odrMeetingInfoView.setStart_time(orderMeeting.getStartTime());
    odrMeetingInfoView.setEnd_time(orderMeeting.getEndTime());
    odrMeetingInfoView.setMailAddress(orderMeeting.getMailaddress());
    odrMeetingInfoView.setMailFulladdress(orderMeeting.getMailafullddress());
    odrMeetingInfoView.setPostcode(orderMeeting.getPostcode());
    odrMeetingInfoView.setMailname(orderMeeting.getMailname());
    odrMeetingInfoView.setMailtel(orderMeeting.getMailtel());
    return odrMeetingInfoView;
  }

  @Override
  public void removeOdrMeeting(int... id) {

  }

  @Override
  public OdrMeetingInfoView getMyOdrMeetingInfo(Integer pkeys) {
    return null;
  }

  @Override
  public Page getMyOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey) {
    return OdrMeetingDao.Launchlist(start,limit,name,state,supplierpkey);
  }

  @Override
  public Page getMyJoinOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey) {
    return OdrMeetingDao.participatelist(start,limit,name,state,supplierpkey);
  }

  @Override
  public  Page getMyOtherOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey) {
    return OdrMeetingDao.Otherlist(start,limit,name,state,supplierpkey);
  }

  @Override
  public void joInOdrMeeting(int odrMeetIngId, int supplier) {
    if (odrMeetingDao.isJoinOdrMeeting(odrMeetIngId, supplier)) {
      OrderMeetingAudit audit = new OrderMeetingAudit();
      audit.setOrdermeeting(odrMeetIngId);
      audit.stStatus(OrderMeetingAuditStatus.DEFAULT);
      audit.stType(OrderMeetingAuditType.SUPPLIER);
      audit.setSupplierid(supplier);
      audit.setCreatedTime(new Date());
      odrMeetingAuditInsDao.setB(audit).commit();
    }
  }
  @Override
  public JSONObject loadstate() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    System.out.println("进入状态");
    for(OrdrerMettingAuditStatus o:OrdrerMettingAuditStatus.values())
    {
      System.out.println(o.getLine().getKey()+"当前状态");
      if(o.getLine().getKey()==6){
        continue;
      }
      JSONObject lineJsona = new JSONObject();
      lineJsona.put("name", o.getLine().getName());
      lineJsona.put("id", o.getLine().getKey());
      ja.put(lineJsona);
    }
    json.put("STORE_ROOT",ja);
   return json;
  }

  @Override
  public List<OdrMeetingSaleInfoView> getMeetingSaleInfo(int start, int limit, int odrMeeting,
      int type, int supplierId) {
    return odrMeetingDao.getMeetingAllSaleInfo(start, limit, odrMeeting);
  }
  @Override
  public void batchdelete(String pkeys) {
    OdrMeetingDao.batchdelete(pkeys);
  }

  @Override
  public void Meettingupdstate(Integer pkey, Integer state) {
    OdrMeetingDao.Meettingupdstate mgd=  new OdrMeetingDao.Meettingupdstate();
    OrderMeeting om=new OrderMeeting();
    om.setPkey(pkey);
    om.setStatus(state.byteValue());
    mgd.setB(om);
    mgd.commit();

  }


}
