package irille.Service.Manage.RFQ;

import com.google.inject.ImplementedBy;
import irille.Entity.RFQ.JSON.RFQConsultQuoteInfo;
import irille.Service.Manage.RFQ.Imp.RFQManageServiceImp;
import irille.view.Manage.RFQ.RFQManageInfoView;
import irille.view.Manage.RFQ.RFQMyuoteInfo;
import irille.view.Manage.RFQ.RFQPdtInfo;
import irille.view.Page;

import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2019/1/30
 * Time: 14:57
 */

@ImplementedBy(RFQManageServiceImp.class)
public interface IRFQManageService {

    Page getRFQList(int start, int limit, String keyword, Integer supId);


    RFQManageInfoView getRFQInfo(int id, int supId);

    int putRFQQuoteInfo(RFQConsultQuoteInfo quoteInfo, Integer pkey);

    RFQPdtInfo getPdtInfo(Integer id, Integer pkey);

    Page getPdtList(Integer start, Integer limit, String keyword, Integer pkey);

    Page getMyRFQQuoteList(Integer start, Integer limit, Date date, String keyword, boolean flag, Integer status, Integer country, int supid) throws IOException;

    RFQMyuoteInfo getMyRFQQuoteInfo(Integer id, Integer pkey) throws IOException;

    /**
     * 分页查询询盘列表
     *
     * @param start      起始记录数
     * @param limit      每页记录数
     * @param keyword    搜索关键字, 关联到发件人或标题内容
     * @param groupId    供应商的文件分组
     * @param flagId     是否被供应商标记
     * @param type       询盘类型 1: RFQ询盘, 2: 普通询盘, 3: 私人展会询盘
     * @param haveNewMsg 是否有新消息
     * @param isDeleted  是否已删除
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @author Jianhua Ying
     */
    void page(Integer start, Integer limit, String keyword, Integer groupId, Boolean flagId, Byte type, Boolean haveNewMsg, Boolean isDeleted, Date startDate, Date endDate);

}
