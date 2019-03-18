package irille.pub.dynamicScore;

import java.util.List;

import irille.Entity.SVS.SVSInfo;
import irille.pub.bean.sql.SQL;

public interface ISVSNewestPdtAction {
  /**
   * 获取该供应商动态分总分
   *
   * @param supId
   * @return
   */
  Integer getTotalScore(Integer supId);

  /**
   * 转化SQL，获取数量
   *
   * @param sql
   * @return
   */
  Integer quantity(SQL sql);

  /**
   * 获取供应商的产品固定分
   *
   * @param supId
   * @return
   */
  Integer getPdtFixedFraction(Integer supId);

  /**
   * 获取30内该供应商上新产品的分数
   *
   * @param supId
   * @return
   * @author zjl
   */
  Integer getNewPdtFraction(Integer supId);

  /**
   * 获取该供应商的私人展厅产品分数
   *
   * @param supId
   * @return
   * @author zjl
   */
  Double getPrivatePdtFraction(Integer supId);

  /**
   * 获取该供应商的O2O产品分数
   *
   * @param supId
   * @return
   */
  Integer getO2OPdtFraction(Integer supId);

  /**
   * 获取该供应商的回复询盘分数
   *
   * @param supId
   * @return
   */
  Integer getInqFraction(Integer supId);

  /**
   * 获取该供应商的有效RFQ报价分数
   *
   * @param supId
   * @return
   */
  Integer getRFQQuoteFraction(Integer supId);

  /**
   * 获取SVS认证为金牌和钻石的商家
   *
   * @return
   */
  List<Integer> getSupDiamondsAndGold();

  /**
   * 获取已认证的所有商家 每个月20号进行计算级别
   *
   * @return
   */
  List<Integer> getSVSInfoSupIds();

  /**
   * 获取SVS认证 用于新增或修改SVS认证表中的动态分
   *
   * @param supIds
   * @return
   */
  List<SVSInfo> getSVSInfos(String supIds);

  /**
   * SVS认证商家等级自动审核
   *
   * @return
   */
  void updSVSGrade();

  /**
   * 用于修改SVS认证表
   *
   * @param supIds:需要修改的采购商pkey
   * @return
   */
  List<SVSInfo> getSVSInfos(List<Integer> supIds);
}
