package irille.view.SVS.BaseScore;

public class TradeTeamSizeHandler implements SVSHandler<Integer> {
	// 外贸团队人数基础分计算
	@Override
	public int getScore(Object v) {
		Integer value = (Integer) v;
		if (value < 10)
			return 1;
		else if (value >= 10 && value <= 15)
			return 3;
		else if (value > 15)
			return 6;
		else
			return 0;
	}

}
