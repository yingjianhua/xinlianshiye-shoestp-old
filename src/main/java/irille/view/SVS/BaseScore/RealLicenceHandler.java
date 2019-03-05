package irille.view.SVS.BaseScore;

public class RealLicenceHandler implements SVSHandler<String> {
	// 出口许可证分值计算
	@Override
	public int getScore(Object v) {
		String value=(String)v;
		if (value != null && !value.equals(""))
			return 8;
		else
			return 0;
	}

}
