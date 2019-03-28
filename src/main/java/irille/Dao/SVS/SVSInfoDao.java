package irille.Dao.SVS;

import java.util.List;
import java.util.Map;

import com.google.inject.ImplementedBy;

import irille.Dao.SVS.impl.SVSInfoDaoImpl;
import irille.Entity.SVS.SVSInfo;

@ImplementedBy(SVSInfoDaoImpl.class)
public interface SVSInfoDao {

  SVSInfo save(SVSInfo bean);

  SVSInfo findSVSInfoBySupplier(Integer supplier);

  List<Map<String, Object>> findSVSinfo(
      Integer start, Integer limit, String shopName, Byte status, Byte shopStatus, Byte grade);

  int count();
  /**
   * 获取SVS认证等级；ROS分数，ROS星级
   *
   * @param supplierId
   * @date 2019/03/27 11:00
   * @author zjl
   * @return
   */
  SVSInfo getSvsRatingAndRos(Integer supplierId);
  /**
   * 获取SVS认证等级；ROS分数，ROS星级(UsrMain表)
   *
   * @date 2019/03/27 14:24
   * @author zjl
   */
  SVSInfo getSvsRatingAndRosMain(Integer mainId);
}
