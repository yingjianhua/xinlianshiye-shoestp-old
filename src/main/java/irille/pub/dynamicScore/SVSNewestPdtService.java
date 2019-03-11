package irille.pub.dynamicScore;

import com.google.inject.ImplementedBy;
import irille.pub.bean.sql.SQL;

import java.util.Date;
import java.util.List;

@ImplementedBy(SVSNewestPdtServiceImpl.class)
public interface SVSNewestPdtService {
    /**
     * 获取24小时
     *
     * @return
     */
    Date getDate();

    /**
     * 获取30天
     *
     * @return
     * @author zjl
     */
    Date getMonth();

    /**
     * 获取该供应商的产品数
     *
     * @param supId
     * @return
     * @author zjl
     */
    SQL getPdtFixedFraction(Integer supId);

    /**
     * 获取该供应商30天内的产品上新数
     *
     * @param supId
     * @return
     * @author zjl
     */
    SQL getNewPdtFraction(Integer supId);

    /**
     * 判断该供应商开店是否满足30天
     * true：满足30天，false：不满足
     *
     * @param supId
     * @return
     * @author zjl
     */
    Boolean isOpenThirty(Integer supId);

    /**
     * 获取该供应商的所有产品
     *
     * @param supId
     * @return
     * @author zjl
     */
    SQL getPdts(Integer supId);

    /**
     * 获取该供应商私人展厅数量
     *
     * @param supId
     * @return
     * @author zjl
     */
    SQL getPrivatePdt(Integer supId);

    /**
     * 获取该供应商参加活动次数
     *
     * @param supId
     * @return
     * @author zjl
     */
    SQL getO2OPdt(Integer supId);

    /**
     * 获取供应商的回复有效数
     *
     * @param supId
     * @return
     * @author zjl
     */
    Integer getInqReply(Integer supId);

    /**
     * 获取供应商RFQ报价数
     *
     * @param supId
     * @return
     */
    SQL getRFQQuote(Integer supId);

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
    SQL getSVSInfo(Integer supId);
}
