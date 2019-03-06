package irille.Dao.SVS.impl;

import com.google.inject.Inject;

import irille.Dao.SVS.SVSInfoDao;
import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;

public class SVSInfoServiceImpl implements SVSInfoService {
	@Inject
	private SVSInfoDao SVSInfoDao;

	/**
	 * 申请认证SVS
	 */
	@Override
	public void application(SVSInfo svs) throws Exception {
		if(SVSInfoDao.findSVSInfoBySupplier(svs.getSupplier())>0){
		throw new WebMessageException(ReturnCode.failure,"该用户已申请认证");
		}
		SVSInfoDao.save(svs);
	}

}
