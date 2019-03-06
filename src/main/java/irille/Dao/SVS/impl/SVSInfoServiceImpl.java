package irille.Dao.SVS.impl;

import com.google.inject.Inject;

import irille.Dao.SVS.SVSInfoDao;
import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;

public class SVSInfoServiceImpl implements SVSInfoService {
	@Inject
	private SVSInfoDao SVSInfoDao;

	/**
	 * 申请认证SVS
	 */
	@Override
	public void application(SVSInfo svs) throws Exception {

		SVSInfoDao.save(svs);
	}

}
