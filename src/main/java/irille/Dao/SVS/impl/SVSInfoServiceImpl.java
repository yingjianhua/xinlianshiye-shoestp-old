package irille.Dao.SVS.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import irille.Dao.SVS.SVSInfoDao;
import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;
import irille.pub.exception.ReturnCode;
import irille.pub.exception.WebMessageException;
import irille.view.SVS.SVSDetailedInfoView;

public class SVSInfoServiceImpl implements SVSInfoService {
	@Inject
	private SVSInfoDao SVSInfoDao;
	@Inject
	ObjectMapper om;

	/**
	 * 申请认证SVS
	 */
	@Override
	public SVSDetailedInfoView application(SVSInfo svs) throws Exception {
		if (SVSInfoDao.findSVSInfoBySupplier(svs.getSupplier()) > 0) {
			throw new WebMessageException(ReturnCode.failure, "该用户已申请认证");
		}
		SVSInfo info = SVSInfoDao.save(svs);
		SVSDetailedInfoView view = new SVSDetailedInfoView();
		view.setId(info.getPkey());
		view.setApplicationTime(info.getApplicationTime());
		view.setBaseScore(info.getBaseScore());
		view.setGrade(info.getGrade());
		view.setStatus(info.getStatus());
		view.setSupplier(info.gtSupplier());
		view.setCapacity(info.getProductionCapacity());
		view.setExhibition(info.getExhibitionAttended());
		view.setFactory(info.getRealFactory());
		view.setQuality(info.getProductQuality());
		view.setResearch(info.getResearch());
		view.setTeam(info.getForeignTradeTeam());
		view.setPartner(info.getPartner());
		return view;
	}

}
