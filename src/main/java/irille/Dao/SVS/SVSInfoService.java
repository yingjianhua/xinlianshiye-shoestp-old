package irille.Dao.SVS;

import com.google.inject.ImplementedBy;

import irille.Dao.SVS.impl.SVSInfoServiceImpl;
import irille.Entity.SVS.SVSInfo;
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
	public void application(SVSInfo svs) throws Exception;
}
