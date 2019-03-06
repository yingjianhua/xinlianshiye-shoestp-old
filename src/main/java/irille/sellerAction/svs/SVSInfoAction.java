package irille.sellerAction.svs;

import java.util.Date;
import javax.inject.Inject;
import com.fasterxml.jackson.databind.ObjectMapper;
import irille.Dao.SVS.SVSInfoService;
import irille.Entity.SVS.SVSInfo;
import irille.Entity.SVS.Enums.SVSAuthenticationStatus;
import irille.Entity.SVS.Enums.SVSGradeType;
import irille.action.seller.SellerAction;
import irille.pub.util.GetBaseScoreUtils;
import irille.sellerAction.svs.inf.ISVSInfoAction;
import lombok.Data;

@Data
public class SVSInfoAction extends SellerAction<SVSInfo> implements ISVSInfoAction {
	@Inject
	SVSInfoService service;
	@Inject
	ObjectMapper om;

	private String search;
	private String capacity;
	private String factory;
	private String quality;
	private String team;
	private String exhibition;
	private String partner;

	// 申请认证
	@Override
	public void application() throws Exception {
		
		
		
		SVSInfo svs = new SVSInfo();
		int score = GetBaseScoreUtils.getBaseScore(search, capacity, factory, quality, team, exhibition, partner);
		System.out.println(score);
		if (score < 30) {
			writeErr("未满足银牌基础分值,无法提交认证");
			return;
		}
		if (score >= 30 && score <= 59)
			svs.stGrade(SVSGradeType.SILVER);
		if (score >= 60)
			svs.stGrade(SVSGradeType.GOLD);
		svs.stStatus(SVSAuthenticationStatus.ToBeAudited);
		svs.setBaseScore(score);
		svs.setApplicationTime(new Date());
		svs.setResearch(om.writeValueAsString(search));
		svs.setProductionCapacity(om.writeValueAsString(capacity));
		svs.setRealFactory(om.writeValueAsString(factory));
		svs.setProductQuality(om.writeValueAsString(quality));
		svs.setForeignTradeTeam(om.writeValueAsString(team));
		svs.setExhibitionAttended(om.writeValueAsString(exhibition));
		svs.setPartner(om.writeValueAsString(partner));
		svs.stSupplier(getSupplier());
		svs.setDynamicScore(0);
		write(service.application(svs));
		;
	}

	@Override
	public void updAutInfo() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAutInfo() throws Exception {
		// TODO Auto-generated method stub

	};
}
