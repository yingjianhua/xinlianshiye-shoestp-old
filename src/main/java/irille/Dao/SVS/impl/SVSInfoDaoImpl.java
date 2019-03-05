package irille.Dao.SVS.impl;

import irille.Dao.SVS.SVSInfoDao;
import irille.Entity.SVS.SVSInfo;

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

}
