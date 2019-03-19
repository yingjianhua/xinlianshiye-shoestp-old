package irille.pub.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.view.SVS.SVSInfoView;
import irille.view.SVS.SVSInfoView.*;

public class GetBaseScoreUtils {

  public static ObjectMapper om = new ObjectMapper();

  /**
   * 计算基础分
   *
   * @param research
   * @param productionCapacity
   * @param realFactory
   * @param productQuality
   * @param tradeTeam
   * @param exhibition
   * @param partner
   * @return
   * @throws IOException
   * @throws JsonMappingException
   * @throws JsonParseException
   */
  public static int getBaseScore(
      String res,
      String capacity,
      String factory,
      String quality,
      String team,
      String exhibition,
      String part) {
    try {
      SVSInfoView view =
          new SVSInfoView(
              om.readValue(res, research.class),
              om.readValue(capacity, productionCapacity.class),
              om.readValue(factory, realFactory.class),
              om.readValue(quality, productQuality.class),
              om.readValue(team, tradeTeam.class),
              om.readValue(exhibition, exhibitionAttended.class),
              om.readValue(part, partner.class));
      Integer score = view.countScore();
      return score;
    } catch (Exception e) {
      throw new WebMessageException(ReturnCode.failure, "数据格式有误");
    }
  }
}
