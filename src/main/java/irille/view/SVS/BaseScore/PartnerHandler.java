package irille.view.SVS.BaseScore;

import java.util.List;

import irille.view.SVS.SVSPartnerView;

public class PartnerHandler implements SVSHandler<List<SVSPartnerView>> {
	// 合作商信息基础分计算
	@Override
	public int getScore(Object v) {
		if (null == v)
			return 0;
		List<SVSPartnerView> value = (List<SVSPartnerView>)v;
		if (value.size() > 0)
			return 5;
		else
			return 0;
	}

}
