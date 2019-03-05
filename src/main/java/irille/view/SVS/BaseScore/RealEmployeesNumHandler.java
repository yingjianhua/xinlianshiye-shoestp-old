package irille.view.SVS.BaseScore;

public class RealEmployeesNumHandler implements SVSHandler<Integer> {
	// 员工人数基础分计算
	@Override
	public int getScore(Object v) {
		Integer value=(Integer)v;
		if (value < 200)
			return 1;
		else if (value >= 200 && value <= 300)
			return 3;
		else if (value > 300)
			return 8;
		else
			return 0;
	}

}
