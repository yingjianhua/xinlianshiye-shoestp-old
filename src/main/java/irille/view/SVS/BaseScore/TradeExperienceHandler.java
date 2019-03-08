package irille.view.SVS.BaseScore;

public class TradeExperienceHandler implements SVSHandler<Integer> {
	// 外贸经验
	@Override
	public int getScore(Object v) {
		if (null == v)
			return 0;
		Integer value = (Integer)v;
		if (value < 5)
			return 1;
		else if (value >= 5 && 5 <= 10)
			return 3;
		else if (value > 10)
			return 6;
		else
			return 0;
	}

}
