package irille.Service.Manage.RFQ;

import java.util.Date;

import com.google.inject.ImplementedBy;

import irille.Service.Manage.RFQ.Imp.RFQConsultServiceImpl;
import irille.shop.usr.UsrSupplier;

@ImplementedBy(RFQConsultServiceImpl.class)
public interface RFQConsultService {

	/**
	 * 移动询盘到指定分组下
	 * 
	 * @param supplier 供应商
	 * @param consultPkeys 询盘主键 多主键根据英文逗号分隔
	 * @param groupPkey 询盘分组主键
	 * 
	 * @author Jianhua Ying
	 */
	void moveToGroup(UsrSupplier supplier, String consultPkeys, Integer groupPkey);
	
	/**
	 * 询盘的删除和恢复操作
	 * 
	 * @param supplier 供应商
	 * @param consultPkeys 询盘的主键  多主键通过英文逗号分隔
	 * @param isToRecycled 是否删除 true: 移动到回收站, false: 从回收站恢复
	 * 
	 * @author Jianhua Ying
	 */
	void moveToRecycled(UsrSupplier supplier, String consultPkeys, Boolean isToRecycled);
	
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
