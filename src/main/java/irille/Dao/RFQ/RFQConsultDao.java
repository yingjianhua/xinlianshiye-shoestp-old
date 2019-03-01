package irille.Dao.RFQ;

import com.google.inject.ImplementedBy;
import irille.Dao.RFQ.impl.RFQConsultDaoImpl;
import irille.Entity.RFQ.RFQConsult;
import irille.Entity.RFQ.RFQConsultRelation;
import irille.platform.rfq.view.RFQConsultView;
import irille.view.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ImplementedBy(RFQConsultDaoImpl.class)
public interface RFQConsultDao {

    /**
     * 分页查询询盘列表
     *
     * @param start     起始记录数
     * @param limit     每页记录数
     * @param condition 查询条件 有效条件包括:
     *                  <ul>
     *                  <li>询盘名称
     *                  <li>采购商名称
     *                  <li>供应商名称
     *                  <li>产品名称
     *                  <li>国家
     *                  <li>询盘类型
     *                  <li>询盘的审核状态
     *                  </ul>
     * @return 分页结果
     * @author Jianhua Ying
     */
    Page<RFQConsultView> findAllView(Integer start, Integer limit, RFQConsultView condition);

    /**
     * 根据id查询询盘信息
     *
     * @param id 询盘主键
     * @return 查询结果
     * @author Jianhua Ying
     */
    RFQConsultView findViewById(Integer id);

    RFQConsult findById(Integer pkey);

    void save(RFQConsult bean);

    List<Map<String, Object>> getRFQList(int start, int limit, String keyword, int supId);

    RFQConsult getRFQInfo(int id);

    /**
     * @Description: 查询已经报价列表
     * @date 2019/2/1 9:49
     * @author lijie@shoestp.cn
     */
    List<Map<String, Object>> getRFQofferList(int id);


    //查询符合条件返回一个空的Relation对象
    RFQConsultRelation getRFQRelation(int rfqId, Integer pkey);

    Map<String, Object> getMyPdtInfo(Integer id, Integer pkey);

    int getRFQListCount(int start, int limit, String keyword, Integer supId);

    List getPdtList(Integer start, Integer limit, String keyword, Integer pkey);

    int getPdtListCount(Integer start, Integer limit, String keyword, Integer pkey);

    List<Map<String, Object>> getMyRFQQuoteList(Integer start, Integer limit, byte type, Date date, String keyword, boolean flag, Integer status, Integer country, int supId);

    Integer count(byte type, Date date, String keyword, boolean flag, Integer status, Integer country, int supId);

    Map<String, Object> getMyRFQQuoteInfo(Integer id, Integer pkey);

    Page getRFQMsgList(Integer id, Integer start, Integer limit);

    Page getMessage(Integer id, Integer start, Integer limit);

}
