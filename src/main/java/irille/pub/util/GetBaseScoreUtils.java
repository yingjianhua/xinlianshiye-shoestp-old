package irille.pub.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  /**
   * ROS分值计算
   *
   * @throws IOException
   * @throws JsonMappingException
   * @throws JsonParseException
   */
  public static Map<String, Integer> countROS(String search, String capacity, String factory) {

    research res;
    int researchBase = 0;
    int capacityBase = 0;
    int factoryBase = 0;
    try {
      res = om.readValue(search, research.class);
      if (null != res) {
        // 获取研发能力分值
        if (1 == res.getIsTeam()) researchBase += 4;
        if (null != res.getCertificate()) {
          String[] array = res.getCertificate().split(",");
          if (array.length == 1) researchBase += 1;
          else if (array.length > 1 && array.length <= 3) researchBase += 3;
          else if (array.length > 3) researchBase += 6;
        }
        if (null != res.getNumOfShoes()) {
          if (res.getNumOfShoes() > 1500) researchBase += 10;
          else if (res.getNumOfShoes() > 1000) researchBase += 6;
          else researchBase += 3;
        }
      }
    } catch (Exception e) {
      throw new WebMessageException(ReturnCode.failure, "数据格式错误");
    }

    productionCapacity capacityObj;
    try {
      capacityObj = om.readValue(capacity, productionCapacity.class);
      // 获取生产能力
      if (null != capacityObj) {
        if (null != capacityObj.getExportVolume()) {
          int value = capacityObj.getExportVolume();
          if (value > 500) capacityBase += 6;
          else if (value > 200) capacityBase += 3;
          else capacityBase += 1;
        }

        if (null != capacityObj.getNeedleCartNum()) {
          int value = capacityObj.getExportVolume();
          if (value < 50) {
            capacityBase += 1;
          } else if (value < 70) {
            capacityBase += 2;
          } else {
            capacityBase += 3;
          }
        }
        if (null != capacityObj.getProductionLineQuantity()) {
          int value = capacityObj.getProductionLineQuantity();
          if (value == 2) capacityBase += 3;
          else if (value >= 3) capacityBase += 7;
          else capacityBase += 0;
        }
      }
    } catch (Exception e) {
      throw new WebMessageException(ReturnCode.failure, "数据格式错误");
    }

    realFactory factoryObj;
    try {
      factoryObj = om.readValue(factory, realFactory.class);
      if (null != factoryObj) {
        if (null != factoryObj.getIsSystem()) {
          if (1 == factoryObj.getIsSystem()) factoryBase += 4;
          if (null != factoryObj.getEmployeesNum()) {
            int value = factoryObj.getIsSystem();
            if (value > 300) factoryBase += 8;
            else if (value > 200) factoryBase += 3;
            else factoryBase += 1;
          }
          if (null != factoryObj.getLicence()) {

            if (factoryObj.getLicence() != null && !factoryObj.getLicence().equals(""))
              factoryBase += 8;
          }
        }
      }
    } catch (Exception e) {
      throw new WebMessageException(ReturnCode.failure, "数据格式错误");
    }
    Map<String, Integer> resulutMap = new HashMap<>();
    resulutMap.put("researchBase", researchBase);
    resulutMap.put("factoryBase", factoryBase);
    resulutMap.put("capacityBase", capacityBase);
    System.out.println(resulutMap);
    return resulutMap;
  }
}
