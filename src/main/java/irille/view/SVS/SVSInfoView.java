package irille.view.SVS;

import java.util.List;

import irille.view.BaseView;
import irille.view.SVS.BaseScore.Calulate;
import irille.view.SVS.BaseScore.Countable;
import irille.view.SVS.BaseScore.ExhibitionAttendedHandler;
import irille.view.SVS.BaseScore.NeedleCartNumHandler;
import irille.view.SVS.BaseScore.PartnerHandler;
import irille.view.SVS.BaseScore.ProductISOCertificateHandler;
import irille.view.SVS.BaseScore.ProductQualityCertificateHandler;
import irille.view.SVS.BaseScore.ProductTestEquipmentHandler;
import irille.view.SVS.BaseScore.ProductionExportVolumeHandler;
import irille.view.SVS.BaseScore.ProductionLineQuantityHandler;
import irille.view.SVS.BaseScore.RealEmployeesNumHandler;
import irille.view.SVS.BaseScore.RealIsSystemHandler;
import irille.view.SVS.BaseScore.RealLicenceHandler;
import irille.view.SVS.BaseScore.ResCertificateHandler;
import irille.view.SVS.BaseScore.ResNumOfShoesHandler;
import irille.view.SVS.BaseScore.ResearchIsTeamHandler;
import irille.view.SVS.BaseScore.TradeExperienceHandler;
import irille.view.SVS.BaseScore.TradeIsTeamHandler;
import irille.view.SVS.BaseScore.TradeTeamSizeHandler;
import lombok.Data;

@Data
public class SVSInfoView extends Countable implements BaseView {

	public SVSInfoView(research research, productionCapacity productionCapacity, realFactory realFactory,
			productQuality productQuality, tradeTeam tradeTeam, exhibitionAttended exhibitionAttended,
			partner partner) {
		super();
		this.research = research;
		this.productionCapacity = productionCapacity;
		this.realFactory = realFactory;
		this.productQuality = productQuality;
		this.tradeTeam = tradeTeam;
		this.exhibitionAttended = exhibitionAttended;
		this.partner = partner;
	}
	@Calulate
	private research research;
	private productionCapacity productionCapacity;
	private realFactory realFactory;
	private productQuality productQuality;
	private tradeTeam tradeTeam;
	private exhibitionAttended exhibitionAttended;
	private partner partner;

	@Data
	public static class research extends Countable {
		// 研发能力
		@Calulate(handleClass = ResearchIsTeamHandler.class)
		Integer isTeam; // 是否拥有研发团队0代表没有 1代表有
		@Calulate(handleClass = ResCertificateHandler.class)
		String certificate;// 证书图片
		@Calulate(handleClass = ResNumOfShoesHandler.class)
		Integer numOfShoes;// 鞋款数量
	}

	public static class productionCapacity {
		// 生产能力
		@Calulate(handleClass = NeedleCartNumHandler.class)
		String needleCartNum;// 针车数量
		@Calulate(handleClass = ProductionLineQuantityHandler.class)
		Integer productionLineQuantity;// 生产线数量
		@Calulate(handleClass = ProductionExportVolumeHandler.class)
		Integer exportVolume;// 年出口额
	}

	public static class realFactory {
		// 真实工厂
		@Calulate(handleClass = RealIsSystemHandler.class)
		Integer isSystem;// 是否有系统0代表没有 1代表有
		@Calulate(handleClass = RealEmployeesNumHandler.class)
		Integer employeesNum;// 员工人数
		@Calulate(handleClass = RealLicenceHandler.class)
		String licence;// 出口许可证
	}

	public static class productQuality {
		// 产品质量
		@Calulate(handleClass = ProductQualityCertificateHandler.class)
		String certificate; // 第三方认证证书
		@Calulate(handleClass = ProductISOCertificateHandler.class)
		String ISOCertificate;// ISO认证证书
		@Calulate(handleClass = ProductTestEquipmentHandler.class)
		Integer testEquipment;// 测试设备
	}

	public static class tradeTeam {
		// 外贸团队
		@Calulate(handleClass = TradeIsTeamHandler.class)
		Integer isTeam;
		@Calulate(handleClass = TradeTeamSizeHandler.class)
		Integer teamSize;// 团队人数
		@Calulate(handleClass = TradeExperienceHandler.class)
		Integer experience;// 外贸经验
	}

	public static class exhibitionAttended {
		// 参加过的展会信息
		@Calulate(handleClass = ExhibitionAttendedHandler.class)
		List<SVSExhibitionView> exhibitionInfos;
	}

	public static class partner {
		// 合作商信息
		@Calulate(handleClass = PartnerHandler.class)
		List<SVSPartnerView> partnerInfos;

	}
}
