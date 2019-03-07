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
	public SVSInfo save(SVSInfo bean) {
		SVSInfo inf =new SVSInfo();
		if (null == bean.getPkey()) {
		 inf = bean.ins();
		} else {
		 inf = bean.upd();
		}
		return inf;

	}

	/**
	 * 判断当前用户是否申请认证过
	 * 
	 * @author GS
	 */
	@Override
	public SVSInfo findSVSInfoBySupplier(Integer supplier) {
		SQL sql = new SQL();
		sql.SELECT(SVSInfo.class);
		sql.FROM(SVSInfo.class);
		sql.WHERE(SVSInfo.T.SUPPLIER, " =?", supplier);
		return Query.sql(sql).query(SVSInfo.class);
	}

}
