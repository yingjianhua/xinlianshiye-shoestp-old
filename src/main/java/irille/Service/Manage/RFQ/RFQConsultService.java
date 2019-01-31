package irille.Service.Manage.RFQ;

import irille.shop.usr.UsrSupplier;

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
}
