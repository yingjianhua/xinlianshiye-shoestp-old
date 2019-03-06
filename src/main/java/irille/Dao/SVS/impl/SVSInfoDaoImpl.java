package irille.Dao.SVS.impl;

import irille.Dao.SVS.SVSInfoDao;
import irille.Entity.SVS.SVSInfo;
import irille.pub.bean.Query;
import irille.pub.bean.sql.SQL;

public class SVSInfoDaoImpl implements SVSInfoDao {

	/**
	 * 更新或者插入SVS对象
	 */
	@Override
	public void save(SVSInfo bean) {
		if (null == bean.getPkey()) {
			bean.ins();
		} else {
			bean.upd();
		}

	}

	/**
	 * 判断当前用户是否申请认证过
	 * 
	 * @author GS
	 */
	@Override
	public int findSVSInfoBySupplier(Integer supplier) {
		SQL sql = new SQL();
		sql.SELECT(SVSInfo.T.PKEY);
		sql.FROM(SVSInfo.class);
		sql.WHERE(SVSInfo.T.SUPPLIER, " =?", supplier);
		return Query.sql(sql).queryCount();
	}

}
