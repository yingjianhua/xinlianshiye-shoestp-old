package irille.view.SVS.BaseScore;

public class TradeTeamSizeHandler implements SVSHandler<Integer> {
	// 外贸团队人数基础分计算
	@Override
	public int getScore(Object v) {
		if (null == v)
			return 0;
		Integer value = (Integer) v;
		if (value > 15)
			return 6;
		else if (value >= 10)
			return 3;
		else
			return 1;

	}

}
