package irille.view.SVS.BaseScore;

public class ProductionLineQuantityHandler implements SVSHandler<Integer> {
	// 生产线数量基础分计算
	@Override
	public int getScore(Object v) {
		Integer value=(Integer)v;
		if (value == 2)
			return 3;
		else if (value >= 3)
			return 7;
		else
			return 0;
	}

}
