package irille.Service.Manage.OdrMeeting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.ImplementedBy;
import irille.Entity.OdrerMeetings.Enums.OrderMeetingAuditStatus;
import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingManageServiceImp;
import irille.pub.idu.IduPage;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import irille.view.Manage.OdrMeeting.Sale.OdrMeetingSaleInfoView;
import java.util.List;

/**
 * 商家端 订购会接口
 */
@ImplementedBy(OdrMeetingManageServiceImp.class)
public interface IOdrMeetingManageService {


  /**
   * @Description: 添加订购会信息
   * @date 2018/11/14 13:30
   */
  void insOdrMeeting(OdrMeetingInfoView getOdrMeetingInfoView, int supplierId)
      throws JsonProcessingException;

  /**
   * @Description: 获取单个订购会信息
   * @date 2018/11/15 14:06
   */
  OdrMeetingInfoView getOdrMeetingInfo(int id);


  /**
   * @Description: 删除订购会信息  (逻辑删除) 待定
   * @date 2018/11/14 13:56
   */
  void removeOdrMeeting(int... id);

  /**
   * @Description:获取订购会详细信息
   * @date 2018/11/14 13:34
   */
  OdrMeetingInfoView getMyOdrMeetingInfo(IduPage iduPage, OrderMeetingAuditStatus status);

  /**
   * @Description:获取我发起的订购订购会 列表
   * @date 2018/11/14 13:30
   */
  OdrMeetingInfoView getMyOdrMeetingList(IduPage iduPage, OrderMeetingAuditStatus status);

  /**
   * @Description: 获取我加入的订购会 列表
   * @date 2018/11/14 13:32
   */
  OdrMeetingInfoView getMyJoinOdrMeetingList(IduPage iduPage, OrderMeetingAuditStatus status);

  /**
   * @Description: 获取我的其他订购会 列表
   * @date 2018/11/14 13:32
   */
  OdrMeetingInfoView getMyOtherOdrMeetingList(IduPage iduPage, OrderMeetingAuditStatus status);

  /**
   * @Description: 参加订购会
   * @date 2018/11/15 11:18
   */
  void joInOdrMeeting(int odrMeetIng, int supplier);


  /**
   * @Description: 获取某个订购会销售明细
   * @date 2018/11/15 15:06
   * @author lijie@shoestp.cn
   */

  List<OdrMeetingSaleInfoView> getMeetingSaleInfo(int start, int limit, int odrMeeting, int type,int supplierId);
}
