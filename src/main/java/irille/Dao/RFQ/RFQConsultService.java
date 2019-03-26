package irille.Dao.RFQ;

import java.io.IOException;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultServiceImpl;
import irille.platform.rfq.view.RFQConsultRelationView;
import irille.platform.rfq.view.RFQConsultView;
import irille.sellerAction.rfq.view.RFQConsultQuoteInfoView;
import irille.view.Page;
import irille.view.RFQ.InquirysView;
import org.json.JSONException;
import org.json.JSONObject;

@ImplementedBy(RFQConsultServiceImpl.class)
public interface RFQConsultService {

  /**
   * 分页查询询盘列表
   *
   * @return
   * @author Jianhua Ying
   */
  Page<RFQConsultView> page(Integer start, Integer limit, RFQConsultView condition);

  /**
   * 查询询盘的详细信息
   *
   * @param condition
   * @return
   * @author Jianhua Ying
   */
  RFQConsultView detail(RFQConsultView condition);

  /**
   * 审核询盘
   *
   * @param view 询盘
   * @param verify true: 审核通过, false: 审核失败
   * @author Jianhua Ying
   */
  void approve(RFQConsultView view, Boolean verify);

  /**
   *
   *
   * <h1>删除询盘</h1>
   *
   * <p>询盘只能在状态为已完成和已关闭的状态下删除, 目前由于聊天内容和询盘耦合在一起, 所以删除询盘只做标记删除, 删除后的询盘不能再继续被抢单, 已经被抢单的询盘不影响聊天以及其它功能;
   *
   * @param view 询盘
   * @author Jianhua Ying
   */
  void delete(RFQConsultView view);

  /**
   * 是否推荐RFQ到首页
   *
   * @param view
   * @author zjl @Date 2019/2/27 13:23
   */
  void recommend(RFQConsultView view);

  /**
   * 商家询盘报价信息
   *
   * @param view 询盘关联表, 需要包含主键
   * @return 报价信息
   * @author Jianhua Ying
   */
  RFQConsultQuoteInfoView relationInfo(RFQConsultRelationView view);

  /**
   * 获取RFQ聊天历史
   *
   * @param view
   * @return
   * @author zjl @Date 2019/2/28 10:31
   */
  Page getRFQMsgList(RFQConsultView view, Integer start, Integer limit);

  /**
   * 获取报价的详细聊天历史
   *
   * @author zjl @Date 2019/2/29 8:39
   */
  Page getMessage(Integer id, Integer start, Integer limit);
  /**
   * 获取询盘列表
   *
   * @throws IOException
   * @author zjl @Date 2019/3/21 18:56
   */
  Page getInqList(
      Integer start,
      Integer limit,
      Byte type,
      String supplierName,
      String purchaseName,
      String productName);
  /**
   * 获取询盘类型 获取供应商SVS等级
   *
   * @throws IOException
   * @author zjl @Date 2019/3/22 9:38
   */
  JSONObject getInqStatus() throws JSONException;
  /**
   * 获取询盘详情
   *
   * @throws IOException
   * @author zjl @Date 2019/3/22 11:51
   */
  InquirysView getInqDetail(Integer rfqPkey);
}
