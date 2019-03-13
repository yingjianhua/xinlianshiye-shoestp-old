package irille.Service.Manage.OdrMeeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.ImplementedBy;

import irille.Entity.OdrerMeetings.OrderMeeting;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingManageServiceImp;
import irille.view.Manage.OdrMeeting.OdrMeetingAuditLogisticsView;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import irille.view.Manage.OdrMeeting.initiatedActivity.LaunchlistMeettingView;
import irille.view.Manage.OdrMeeting.initiatedActivity.OrderInformationView;
import irille.view.Page;
import org.json.JSONObject;

/** 商家端 订购会接口 */
@ImplementedBy(OdrMeetingManageServiceImp.class)
public interface IOdrMeetingManageService {

  /**
   * @Description: 添加订购会信息
   *
   * @date 2018/11/14 13:30
   */
  void insOdrMeeting(OdrMeetingInfoView getOdrMeetingInfoView, int supplierId)
      throws JsonProcessingException;

  /**
   * @Description: 获取单个订购会信息
   *
   * @date 2018/11/15 14:06
   */
  OdrMeetingInfoView getOdrMeetingInfo(int id);

  /**
   * @Description: 删除订购会信息 (逻辑删除) 待定
   *
   * @date 2018/11/14 13:56
   */
  void removeOdrMeeting(int... id);

  /**
   * @Description:获取订购会详细信息
   *
   * @date 2018/11/14 13:34
   */
  OdrMeetingInfoView getMyOdrMeetingInfo(Integer pkey);

  /**
   * @Description:获取我发起的订购订购会 列表
   *
   * @date 2018/11/14 13:30
   */
  Page getMyOdrMeetingList(
      Integer start,
      Integer limit,
      String name,
      Integer supstate,
      Integer state,
      Integer supplierpkey);

  /**
   * @Description: 获取我加入的订购会 列表
   *
   * @date 2018/11/14 13:32
   */
  Page getMyJoinOdrMeetingList(
      Integer start, Integer limit, String name, Integer state, Integer supplierpkey);

  /**
   * @Description: 获取我的其他订购会 列表
   *
   * @date 2018/11/14 13:32
   */
  Page getMyOtherOdrMeetingList(
      Integer start, Integer limit, String name, Integer state, Integer supplierpkey);

  /**
   * @Description: 获取订购会审核状态列表
   *
   * @date 2018/11/15 11:33
   * @anthor wilson zhang
   */
  JSONObject loadsupstate() throws Exception;

  /**
   * @Description: 获取订购会状态列表
   *
   * @date 2018/11/15 11:33
   * @anthor wilson zhang
   */
  JSONObject loadstate() throws Exception;

  /**
   * @Description: 发布者批量删除
   *
   * @date 2018/11/15 18:04
   * @anthor wilson zhang
   */
  void batchdelete(String pkeys);

  /**
   * @Description: 订购会地址修改
   *
   * @date 2018/11/21 16:37
   * @anthor wilson zhang
   */
  void updaddress(OrderMeeting omt);

  /**
   * @Description: 添加商家参加订购会
   *
   * @date 2018/11/19 20:19
   * @anthor wilson zhang
   */
  void insertjoinOdr(Integer OMTpkey, Integer supplierkey);

  /**
   * @Description: 删除合作商 和对应的商品
   *
   * @date 2018/11/22 11:11
   * @anthor wilson zhang
   */
  void deletejoinOdr(Integer OdrAuditpkey);

  /**
   * @Description: 参加者批量删除
   *
   * @date 2018/11/15 18:04
   * @anthor wilson zhang
   */
  void joindelete(String pkeys);

  /**
   * @Description: 获取某个订购会销售明细
   *
   * @date 2018/11/15 15:06
   * @author lijie@shoestp.cn
   */
  Page getMeetingSaleInfo(int start, int limit, int odrMeeting, int supplierId);

  /**
   * @Description: 订购会列表 修改状态
   *
   * @date 2018/11/15 18:04
   * @anthor wilson zhang
   */
  void Meettingupdstate(Integer pkey, Integer state);

  /**
   * @Description: 参加订购会
   *
   * @date 2018/11/16 10:44
   * @anthor lijie
   */
  void joInOdrMeeting(int odrMeetIngId, int supplier);

  /**
   * @Description: 获取订购会信息
   *
   * @date 2018/11/19 14:20
   * @anthor zjl
   */
  OrderInformationView getorderInformation(Integer id);

  /**
   * @Description: 发布订购会
   *
   * @date 2018/11/20 15:58
   * @anthor wilson zhang
   */
  void insOdrmeetting(LaunchlistMeettingView lmv);

  /**
   * @Description: 获取合作商列表 2018/12/12 14:15
   *
   * @anthor zjl
   */
  Page cooperationsupplier(
      Integer start, Integer limit, Integer status, String name, Integer omtid);

  /**
   * @Description: 审核合作商(修改状态) 2018/12/12 14:25
   *
   * @anthor zjl
   */
  void updLoadSupStatus(Integer id, Integer status);

  /**
   * @Description: 获取合作商是否认证状态
   *
   * @date 2018/12/12 14:46
   * @anthor zjl
   */
  JSONObject isAuthStatus() throws Exception;

  /**
   * @Description: 合作商发送样品(更新信息)
   *
   * @date 2018/12/13 10:56
   * @anthor zjl
   */
  void updAudit(Integer omtId, Integer supplierId, String orderNumber, String remarks);

  /**
   * @Description: 获取合作商样品发送物流信息
   *
   * @date 2018/12/14 9:51
   * @anthor zjl
   */
  OdrMeetingAuditLogisticsView getLogistics(Integer omtId, Integer supplierId);
}
