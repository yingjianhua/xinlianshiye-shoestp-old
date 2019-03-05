package irille.view.SVS;

import java.util.List;

import irille.view.BaseView;
import lombok.Data;

@Data
public class SVSInfoView implements BaseView {
	
	@Data
	public class research {
		// 研发能力
		Integer isTeam;    //是否拥有研发团队0代表没有 1代表有
		String certificate;// 证书图片
		Integer numOfShoes;// 鞋款数量
	}

	public class productionCapacity {
		// 生产能力
		String needleCartNum;// 针车数量
		String productionLineQuantity;// 生产线数量
		String exportVolume;// 年出口额
	}

	public class realFactory {
		// 真实工厂
		Integer isSystem;// 是否有系统0代表没有 1代表有
		String employeesNum;// 员工人数
		String licence;// 出口许可证
	}

	public class productQuality {
		// 产品质量
		String certificate; // 第三方认证证书
		String ISOCertificate;// ISO认证证书
		String testEquipment;// 测试设备
	}

	public class tradeTeam {
		// 外贸团队
		String teamSize;// 团队人数
		String experience;// 外贸经验
	}

	public class exhibitionAttended {
		// 参加过的展会信息
		List<SVSExhibitionView> exhibitionInfos;
	}

	public class partner {
		// 合作商信息
		List<SVSPartnerView> partnerInfos;

	}
}
