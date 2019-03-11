package irille.pub.dynamicScore;

import irille.Entity.SVS.SVSInfo;
import irille.pub.bean.sql.SQL;

import java.util.List;

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
     * 获取SVS认证
     * 用于新增或修改SVS认证表中的动态分
     *
     * @param supId
     * @return
     */
    SVSInfo getSVSInfo(Integer supId);


}
