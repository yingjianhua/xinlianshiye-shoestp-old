package irille.Dao.RFQ;

import java.util.Date;
import java.util.List;

import com.google.inject.ImplementedBy;

import irille.Dao.RFQ.impl.RFQConsultMessageDaoImpl;
import irille.Entity.RFQ.RFQConsultMessage;

@ImplementedBy(RFQConsultMessageDaoImpl.class)
public interface RFQConsultMessageDao {

  List<RFQConsultMessage> findAll(
      Integer start, Integer limit, Integer consultPkey, Integer supplierPkey);

  void save(RFQConsultMessage bean);

  /**
   * 统计有几个询盘有未读消息
   *
   * @param purchasePkey
   * @return
   */
  Integer countPurchaseUnreadByRelation_PurchaseGroupByRelation(Integer purchasePkey);

  /**
   * 通过uuid获取消息
   *
   * @param uuid 唯一标识
   * @return
   * @author Jianhua Ying
   */
  RFQConsultMessage findByUuid(String uuid);

  /**
   * 统一修改uuid相同的消息的content和validDate字段
   *
   * @param uuid uuid字段
   * @param validDate 过期时间
   * @param content 消息内容
   * @author Jianhua Ying
   */
  void updateValidDateAndContentByUuid(String uuid, Date validDate, String content);
}
