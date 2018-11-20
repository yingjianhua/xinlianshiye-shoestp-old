package irille.Service.Manage.OdrMeeting;

import com.google.inject.ImplementedBy;

import com.fasterxml.jackson.core.JsonProcessingException;

import irille.view.Manage.OdrMeeting.initiatedActivity.OrderInformationView;
import org.json.JSONObject;

import java.util.List;

import irille.Service.Manage.OdrMeeting.Imp.OdrMeetingManageServiceImp;
import irille.view.Manage.OdrMeeting.OdrMeetingInfoView;
import irille.view.Manage.OdrMeeting.Sale.OdrMeetingSaleInfoView;
import irille.view.Page;

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

    OdrMeetingInfoView getMyOdrMeetingInfo(Integer pkey);

    /**
     * @Description:获取我发起的订购订购会 列表
     * @date 2018/11/14 13:30
     */
    Page getMyOdrMeetingList(Integer start, Integer limit, String name,Integer supstate, Integer state, Integer supplierpkey);

    /**
     * @Description: 获取我加入的订购会 列表
     * @date 2018/11/14 13:32
     */
    Page getMyJoinOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey);

    /**
     * @Description: 获取我的其他订购会 列表
     * @date 2018/11/14 13:32
     */
    Page getMyOtherOdrMeetingList(Integer start, Integer limit, String name, Integer state, Integer supplierpkey);

    /**
     * @Description: 获取订购会审核状态列表
     * @date 2018/11/15 11:33
     * @anthor wilson zhang
     */
    JSONObject loadsupstate() throws Exception;
    /**
     * @Description: 获取订购会状态列表
     * @date 2018/11/15 11:33
     * @anthor wilson zhang
     */
    JSONObject loadstate() throws Exception;

    /**
     * @Description:  发布者批量删除
     * @date 2018/11/15 18:04
     * @anthor wilson zhang
     */
    void batchdelete(String pkeys);

    /**
    *@Description:  添加商家参加订购会
    *@date 2018/11/19 20:19
    *@anthor wilson zhang
    */
    void insertjoinOdr(Integer OMTpkey,Integer supplierkey);

    /**
     * @Description: 参加者批量删除
     * @date 2018/11/15 18:04
     * @anthor wilson zhang
     */
    void joindelete(String pkeys);

    /**
     * @Description: 获取某个订购会销售明细
     * @date 2018/11/15 15:06
     * @author lijie@shoestp.cn
     */

    List<OdrMeetingSaleInfoView> getMeetingSaleInfo(int start, int limit, int odrMeeting, int type, int supplierId);

    /**
     * @Description: 订购会列表  修改状态
     * @date 2018/11/15 18:04
     * @anthor wilson zhang
     */

    void Meettingupdstate(Integer pkey, Integer state);

    /**
     * @Description: 参加订购会
     * @date 2018/11/16 10:44
     * @anthor lijie
     */
    void joInOdrMeeting(int odrMeetIngId, int supplier);

    /**
     * @Description: 获取订购会信息
     * @date 2018/11/19 14:20
     * @anthor zjl
     */
    OrderInformationView getorderInformation(Integer id);
}
