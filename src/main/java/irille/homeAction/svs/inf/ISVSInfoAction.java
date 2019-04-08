package irille.homeAction.svs.inf;

import java.io.IOException;

public interface ISVSInfoAction {
  /**
   * 获取SVS认证等级；ROS分数，ROS星级(UsrSupplier表)
   *
   * @throws IOException
   * @date 2019/03/27 11:00
   * @author zjl
   */
  void getSvsRatingAndRos() throws IOException;
  /**
   * 获取SVS认证等级；ROS分数，ROS星级(UsrMain表)
   *
   * @throws IOException
   * @date 2019/03/27 14:24
   * @author zjl
   */
  void getSvsRatingAndRosMain() throws IOException;
}
