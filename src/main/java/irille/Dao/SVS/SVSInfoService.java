package irille.Dao.SVS;

import com.google.inject.ImplementedBy;

import irille.Dao.SVS.impl.SVSInfoServiceImpl;
import irille.Entity.SVS.SVSInfo;
import irille.view.SVS.SVSDetailedInfoView;
import irille.view.SVS.SVSInfoView.exhibitionAttended;
import irille.view.SVS.SVSInfoView.partner;
import irille.view.SVS.SVSInfoView.productQuality;
import irille.view.SVS.SVSInfoView.productionCapacity;
import irille.view.SVS.SVSInfoView.realFactory;
import irille.view.SVS.SVSInfoView.research;
import irille.view.SVS.SVSInfoView.tradeTeam;

@ImplementedBy(SVSInfoServiceImpl.class)
public interface SVSInfoService {

	/**
	 * 申请认证SVS
	 * 
	 * @author GS
	 * 
	 */
	public SVSDetailedInfoView application(SVSInfo svs) throws Exception;

	public SVSDetailedInfoView getSVSInfo(Integer supplierId) throws Exception;

	/**
	 * 修改认证信息
	 * 
	 * @param supplierId
	 * @param res
	 * @param capacity
	 * @param factory
	 * @param quality
	 * @param team
	 * @param exhibition
	 * @param part
	 * @return
	 * @throws Exception
	 */
	public SVSDetailedInfoView updSVSInfo(Integer supplierId, String res, String capacity, String factory,
			String quality, String team, String exhibition, String part) throws Exception;
}
